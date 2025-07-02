package RITIGM.gokartproject.persistence.webService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import RITIGM.gokartproject.connection.Conn;
import RITIGM.gokartproject.model.PlayerStat;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.DefenseUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;
import RITIGM.gokartproject.persistence.webService.interfaces.PlayerStatInterface;

/**
 * Handles all the database shazzam for player stat objects and api calls
 */
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
                p.pid, p.Email, p.Password, p.uid, p.username, p.profile,
                SUM(r.collisionwithplayers) AS totalplayercollision,
                SUM(r.collisionwithwalls) AS totalwallcollision,
                SUM(r.fellofmap) AS totalfellofmap,
                SUM(r.speedboost1) AS totalspeedboost1,
                SUM(r.speedboost2) AS totalspeedboost2,
                SUM(r.speedboost3) AS totalspeedboost3,
                SUM(r.speedboost4) AS totalspeedboost4,
                SUM(r.puck1) AS totalpuck1,
                SUM(r.puck2) AS totalpuck2,
                SUM(r.puck2) AS totalpuck3,
                SUM(r.puck2) AS totalpuck4,
                SUM(r.oilspill1) AS totaloilspill1,
                SUM(r.brickwall) AS totalbrickwall,
                SUM(r.confuseritchie) AS totalconfuseritchie,
                SUM(r.fakepowerupblock) AS totalfakepowerupblock,
                SUM(r.defense1) AS totaldefense1,
                SUM(r.defense2) AS totaldefense2,
                SUM(r.defense3) AS totaldefense3,
                SUM(r.defense4) AS totaldefense4

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
                data.getInt("profile"),
                data.getInt("totalwallcollision"),
                data.getInt("totalplayercollision"),
                data.getInt("totalfellofmap"),
                0,0, 0, //Init the fastest time and fav character and fav track
                new OffenseUsage(
                    data.getInt("totalpuck1"), 
                    data.getInt("totalpuck2"),
                    data.getInt("totalpuck3"),
                    data.getInt("totalpuck4")),
                new TrapUsage(
                    data.getInt("totaloilspill1"),
                    data.getInt("totalbrickwall"),
                    data.getInt("totalconfuseritchie"),
                    data.getInt("totalfakepowerupblock")),
                new BoostUsage(
                    data.getInt("totalspeedboost1"),
                    data.getInt("totalspeedboost2"),
                    data.getInt("totalspeedboost3"),
                    data.getInt("totalspeedboost3")),
                new DefenseUsage(
                    data.getInt("totaldefense1"),
                    data.getInt("totaldefense2"),
                    data.getInt("totaldefense3"),
                    data.getInt("totaldefense4")),
                    0.0,
                0.0,
                0);
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

        String totalRaceQuery = "SELECT COUNT(pid) as totalrace\n" + //
                        "FROM racelog\n" + //
                        "WHERE pid = ?";

        PreparedStatement totalstmt = conn.prepareStatement(totalRaceQuery);
        totalstmt.setString(1, pid);

        ResultSet totalRaceSet = totalstmt.executeQuery();
        if(totalRaceSet.next()){
            returnStat.setTotalRaces(totalRaceSet.getInt("totalrace"));
        } else{
            throw new SQLException("Error fetching totalrace");
        }

        String favRaceQuery = "SELECT MAX(racePermap.mapcount) as countfavmap, mapraced\n" + //
                        "FROM \n" + //
                        "    (\n" + //
                        "      SELECT COUNT(racelog.mapraced) as mapcount, mapraced\n" + //
                        "      FROM racelog\n" + //
                        "      WHERE pid = ?\n" + //
                        "      GROUP BY  mapraced\n" + //
                        "     ) as racePermap;";

        PreparedStatement favracestmt = conn.prepareStatement(favRaceQuery);
        favracestmt.setString(1, pid);

        ResultSet favRaceSet = favracestmt.executeQuery();
        if(favRaceSet.next()){
            returnStat.setFavoriteTrack(favRaceSet.getInt("countfavmap"));
        }else{
            throw new SQLException("Error retreiving countfavmap");
        }
        
        return returnStat;
    }
}
