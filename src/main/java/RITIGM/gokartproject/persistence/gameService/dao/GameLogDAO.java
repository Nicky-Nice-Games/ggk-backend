package RITIGM.gokartproject.persistence.gameService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import RITIGM.gokartproject.connection.Conn;
import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;
import RITIGM.gokartproject.persistence.gameService.interfaces.GameLogInterface;


/**
 * Handles reading and writing of data to databsae
 * as it pertains to game logs
 */
@Component
public class GameLogDAO implements GameLogInterface {

    private Conn connCls = null;
    private Connection conn = null;

    /**
     * Cosmntructor
     */
    public GameLogDAO() {
        this.connCls = new Conn();
        this.conn = this.connCls.getConnection();

    }

    /**
     * Handles query calls to database to retreive and write data for the purpose of
     * accessing game log data
     */
    @Override
    public boolean addGameLog(RaceLog raceLog) throws SQLException{
            // Update the main table of race log

            String query = 
            """
            INSERT INTO racelog 
            (pid, racestarttime, racetime, racepos, mapraced, characterused, collisionwithplayers, collisionwithwalls,
            fellofmap,speedboost1,speedboost2,speedboost3, puck1, puck2, oilspill1, oilspill2) 
            VALUES 
            (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);        
            """;
            PreparedStatement stmtUpdateMainLog = conn.prepareStatement(query);
            stmtUpdateMainLog.setString(1,raceLog.getPid());
            stmtUpdateMainLog.setTimestamp(2, raceLog.getRaceStartTime());
            stmtUpdateMainLog.setInt(3, raceLog.getRaceTime());
            stmtUpdateMainLog.setInt(4,raceLog.getRacePos());
            stmtUpdateMainLog.setInt(5, raceLog.getMapRaced());
            stmtUpdateMainLog.setInt(6, raceLog.getCharacterUsed());
            stmtUpdateMainLog.setInt(7, raceLog.getCollisionWithPlayer());
            stmtUpdateMainLog.setInt(8, raceLog.getCollisionWithWall());
            stmtUpdateMainLog.setInt(9, raceLog.getFelloffmap());
            stmtUpdateMainLog.setInt(10, raceLog.getBoostStat().getSpeedBoost1());
            stmtUpdateMainLog.setInt(11, raceLog.getBoostStat().getSpeedBoost2());
            stmtUpdateMainLog.setInt(12, raceLog.getBoostStat().getSpeedBoost3());
            stmtUpdateMainLog.setInt(13, raceLog.getOffenseStat().getPuck1());
            stmtUpdateMainLog.setInt(14, raceLog.getOffenseStat().getPuck2());
            stmtUpdateMainLog.setInt(15, raceLog.getTrapUsage().getOilSpill1());
            stmtUpdateMainLog.setInt(16, raceLog.getTrapUsage().getOilSpill1());

            return (stmtUpdateMainLog.executeUpdate() == 1) ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<RaceLog> getRaceByPlayer(String pid) throws SQLException{
        // try {
            ArrayList<RaceLog> returnLog = new ArrayList<>();
            String query = 
            """
            SELECT * FROM racelog WHERE pid = ?;
            """;


            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, pid);

            ResultSet result = stmt.executeQuery();

            while(result.next()){
                BoostUsage boostUsageData = new BoostUsage(
                    result.getInt("speedboost1"),
                    result.getInt("speedboost2"),
                    result.getInt("speedboost3"));

                OffenseUsage offenseUsageData = new OffenseUsage(
                    result.getInt("puck1"), 
                    result.getInt("puck2"));

                TrapUsage trapUsage = new TrapUsage(
                    result.getInt("oilspill1"),
                    result.getInt("oilspill2"));

                RaceLog addedLog = new RaceLog(
                    result.getString("pid"),
                    result.getTimestamp("racestarttime"),
                    result.getInt("racetime"),
                    result.getInt("racepos"),
                    result.getInt("mapraced"),
                    result.getInt("characterused"),
                    result.getInt("collisionwithplayers"),
                    result.getInt("collisionwithwalls"),
                    result.getInt("fellofmap"),
                    boostUsageData,
                    offenseUsageData, 
                    trapUsage);

                returnLog.add(addedLog);
            }
            return returnLog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceLog getRaceInfo(int raceID) {
        try {
            RaceLog returnLog = null;
            String query = 
            """
            SELECT * FROM racelog WHERE raceid = ?;
            """;


            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, raceID);

            ResultSet result = stmt.executeQuery();

            if(result.next()){
                BoostUsage boostUsageData = new BoostUsage(
                    result.getInt("speedboost1"),
                    result.getInt("speedboost2"),
                    result.getInt("speedboost3"));

                OffenseUsage offenseUsageData = new OffenseUsage(
                    result.getInt("puck1"), 
                    result.getInt("puck2"));

                TrapUsage trapUsage = new TrapUsage(
                    result.getInt("oilspill1"),
                    result.getInt("oilspill2"));

                returnLog = new RaceLog(
                    result.getString("pid"),
                    result.getTimestamp("racestarttime"),
                    result.getInt("racetime"),
                    result.getInt("racepos"),
                    result.getInt("mapraced"),
                    result.getInt("characterused"),
                    result.getInt("collisionwithplayers"),
                    result.getInt("collisionwithwalls"),
                    result.getInt("fellofmap"),
                    boostUsageData,
                    offenseUsageData, 
                    trapUsage);
            }
            return returnLog;
        } catch (SQLException e) {
            System.err.println("There is an error query the database: " + e);
            return null;
        }
    }
}
