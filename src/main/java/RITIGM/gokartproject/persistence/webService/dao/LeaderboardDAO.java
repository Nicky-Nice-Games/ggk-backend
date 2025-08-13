package RITIGM.gokartproject.persistence.webService.dao;

import RITIGM.gokartproject.connection.Conn;
import RITIGM.gokartproject.model.LeaderboardData;

import RITIGM.gokartproject.persistence.webService.interfaces.LeaderboardInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

/**
 * handles reading and writing of data from the database
 * as it pertains to the leaderboard
 */
@Component
public class LeaderboardDAO implements LeaderboardInterface{
    private Conn connCls;
    private Connection conn; 

    /**
     * CONSTRUCTOR
     */
    public LeaderboardDAO(){
        this.connCls = new Conn();
        this.conn = connCls.getConnection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<LeaderboardData> getMapLeaderboard(int mapID) throws SQLException{
        ArrayList<LeaderboardData> returnData = new ArrayList<>();
        
        String query =
            """
            SELECT
                p.pid, racelog.raceid, p.username, racelog.racestarttime,MIN(racelog.racetime) AS leaderboardtime, mapraced, racelog.score, p.profile
            FROM racelog
                INNER JOIN gokart.players p on racelog.pid = p.pid
            WHERE mapraced = ?
            GROUP BY p.pid
            ORDER BY leaderboardtime;
            """;
        
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, mapID);

        ResultSet updateCheck = stmt.executeQuery();
        while(updateCheck.next()){
            returnData.add(new LeaderboardData(
                updateCheck.getString("pid"),
                updateCheck.getInt("raceid"),
                updateCheck.getString("username"),
                updateCheck.getTimestamp("racestarttime"),
                updateCheck.getInt("leaderboardtime"),
                updateCheck.getDouble("score"),
                updateCheck.getInt("profile")));            
        }

        return returnData;
    }
    
}
