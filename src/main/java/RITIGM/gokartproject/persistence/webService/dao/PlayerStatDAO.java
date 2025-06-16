package RITIGM.gokartproject.persistence.webService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import RITIGM.gokartproject.connection.Conn;
import RITIGM.gokartproject.model.PlayerStat;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;
import RITIGM.gokartproject.persistence.webService.interfaces.PlayerStatInterface;

@Component
public class PlayerStatDAO implements PlayerStatInterface{
    private Conn connCls;
    private Connection conn; 

    public PlayerStatDAO(){
        this.connCls = new Conn();
        this.conn = connCls.getConnection();
    }

    @Override
    public PlayerStat getPlayerStat(String pid) throws SQLException {
        String mainQuery = 
        """
            SELECT
                p.pid, p.Email, p.Password, p.uid, p.username,
                SUM(r.collisionwithplayers) AS totalplayercollision,
                SUM(r.collisionwithwalls) AS totalwallcollision,
                SUM(r.fellofmap) AS totalfellofmap,
                SUM(r.speedboost1) AS totalspeedboost1,
                SUM(r.speedboost2) AS totalspeedboost2,
                SUM(r.speedboost3) AS totalspeedboost3,
                SUM(r.puck1) AS totalpuck1,
                SUM(r.puck2) AS totalpuck2,
                SUM(r.oilspill1) AS totaloilspill1,
                SUM(r.oilspill2) AS totaloilspill2
            FROM
                gokart.racelog r
            JOIN
                gokart.players p ON r.pid = p.pid
            WHERE
                p.pid = ?
            GROUP BY
                p.pid, p.Email, p.Password, p.uid, p.username;
        """;

        PreparedStatement mainstmt = conn.prepareStatement(mainQuery);
        mainstmt.setString(1, pid);

        ResultSet data = mainstmt.executeQuery();
        PlayerStat returnStat;

        if(data.next()){
            returnStat = new PlayerStat(
                data.getString("pid"),
                data.getString("Email"),
                data.getString("Password"),
                data.getInt("uid"),
                data.getString("username"),
                data.getInt("totalwallcollision"),
                data.getInt("totalplayercollision"),
                data.getInt("totalfellofmap"),
                0,0, //Init the fastest time and fav character
                new OffenseUsage(
                    data.getInt("totalpuck1"), 
                    data.getInt("totalpuck2")),
                new TrapUsage(
                    data.getInt("totaloilspill1"),
                    data.getInt("totaloilspill2")),
                new BoostUsage(
                    data.getInt("totalspeedboost1"),
                    data.getInt("totalspeedboost2"),
                    data.getInt("totalspeedboost3")),
                    0.0,
                0.0);
        } else{
            return null;
        }

        String queryfirst = 
        """
        SELECT
            COALESCE(COUNT(r.racepos), 0) AS totalpodium
        FROM
            gokart.players p
        LEFT JOIN
            gokart.racelog r ON p.pid = r.pid AND r.racepos = 1
        WHERE
            p.pid = ?;        
        """;

        PreparedStatement firststmt = conn.prepareStatement(queryfirst);
        firststmt.setString(1, pid);
        ResultSet firstdata = firststmt.executeQuery();

        if (firstdata.next() == true){
            returnStat.setFirstPlace(firstdata.getDouble("totalpodium"));
        } else{
            throw new SQLException("Error in fetching first place");
        }

        String queryPodium = 
        """
        SELECT
            COALESCE(COUNT(r.racepos), 0) AS totalpodium
        FROM
            gokart.players p
        LEFT JOIN
            gokart.racelog r ON p.pid = r.pid AND r.racepos >= 1 AND r.racepos <= 3
        WHERE
            p.pid = ?;        
        """;

        PreparedStatement podiumstmt = conn.prepareStatement(queryPodium);
        podiumstmt.setString(1, pid);
        ResultSet podiumdata = podiumstmt.executeQuery();

        if (podiumdata.next() == true){
            returnStat.setPodium(podiumdata.getDouble("totalpodium"));
        } else{
            throw new SQLException("Error in fetching podium");
        }
        


        String fastestLap = 
        """
            SELECT MIN(racelog.racetime) AS fastest
            FROM racelog
            WHERE racelog.pid = ?;
        
        """;
        
        PreparedStatement stmt = conn.prepareStatement(fastestLap);
        stmt.setString(1, pid);
        ResultSet fastestTime = stmt.executeQuery();

        if(fastestTime.next()){
            returnStat.setFastestTime(fastestTime.getInt("fastest"));
        } else{
            throw new SQLException("Error in fetching fastest time");
        }

        String favoriteCharacter = 
        """
            SELECT characterused,
                COUNT(racelog.characterused) as favchara

            FROM racelog
            WHERE racelog.pid = ?
            GROUP BY characterused
            ORDER BY favchara DESC
            LIMIT 1;        
        """;
        
        PreparedStatement charastmt = conn.prepareStatement(favoriteCharacter);
        charastmt.setString(1, pid);

        ResultSet favCharaSet = charastmt.executeQuery();

        if(favCharaSet.next()){
            returnStat.setFavoriteChara(favCharaSet.getInt("favchara"));
        } else{
            throw new SQLException("Error in fetching favchara");
        }
        
        return returnStat;
    }
}
