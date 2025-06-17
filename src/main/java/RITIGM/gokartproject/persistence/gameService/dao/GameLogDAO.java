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
import RITIGM.gokartproject.model.usage.DefenseUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;
import RITIGM.gokartproject.persistence.gameService.interfaces.GameLogInterface;


/**
 * Handles reading and writing of data to databsae
 * as it pertains to game logs
 */
@Component
public class GameLogDAO implements GameLogInterface {
    private Conn connCls;
    private Connection conn; 

    /**
     * Cosmntructor
     */
    public GameLogDAO() {
        this.connCls = new Conn();
        this.conn = connCls.getConnection();
    }

    /**
     * Handles query calls to database to retreive and write data for the purpose of
     * accessing game log data
     */
    @Override
    public boolean addGameLog(RaceLog raceLog) throws SQLException{
            // Update the main table of race log

            Double score = RaceCalculationDAO.raceLogCalculation(raceLog);

            String query = 
            """
           INSERT INTO racelog 
                (pid, racestarttime, racetime, racepos, mapraced, characterused, collisionwithplayers, collisionwithwalls,
                fellofmap,speedboost1,speedboost2,speedboost3, speedboost4,
                puck1, puck2,puck3, puck4, oilspill1, brickwall,
                confuseritchie,fakepowerupblock,defense1,defense2,defense3,defense4,score) 
                VALUES 
            (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);          
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
            stmtUpdateMainLog.setInt(12, raceLog.getBoostStat().getSpeedBoost4());
            stmtUpdateMainLog.setInt(13, raceLog.getOffenseStat().getPuck1());
            stmtUpdateMainLog.setInt(14, raceLog.getOffenseStat().getPuck2());
            stmtUpdateMainLog.setInt(14, raceLog.getOffenseStat().getPuck3());
            stmtUpdateMainLog.setInt(14, raceLog.getOffenseStat().getPuck4());
            stmtUpdateMainLog.setInt(15, raceLog.getTrapUsage().getOilSpill1());
            stmtUpdateMainLog.setInt(16, raceLog.getTrapUsage().getBrickwall());
            stmtUpdateMainLog.setInt(16, raceLog.getTrapUsage().getConfuseritchie());
            stmtUpdateMainLog.setInt(16, raceLog.getTrapUsage().getFakepowerupbrickl());
            stmtUpdateMainLog.setInt(16, raceLog.getDefenseUsage().getDefense1());
            stmtUpdateMainLog.setInt(16, raceLog.getDefenseUsage().getDefense2());
            stmtUpdateMainLog.setInt(16, raceLog.getDefenseUsage().getDefense3());
            stmtUpdateMainLog.setInt(16, raceLog.getDefenseUsage().getDefense4());
            stmtUpdateMainLog.setDouble(17, score);
            

            boolean check = (stmtUpdateMainLog.executeUpdate() == 1) ? true : false;
            if(check){
                RaceCalculationDAO a = new RaceCalculationDAO();
                a.profileRecalculation(raceLog.getPid());
            } else{
                return false;
            }
            return true;
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

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, pid);

            ResultSet result = stmt.executeQuery();

            while(result.next()){
                BoostUsage boostUsageData = new BoostUsage(
                    result.getInt("speedboost1"),
                    result.getInt("speedboost2"),
                    result.getInt("speedboost3"),
                    result.getInt("speedboost4"));

                OffenseUsage offenseUsageData = new OffenseUsage(
                    result.getInt("puck1"), 
                    result.getInt("puck2"),
                    result.getInt("puck3"),
                    result.getInt("puck4"));

                TrapUsage trapUsage = new TrapUsage(
                    result.getInt("oilspill1"),
                    result.getInt("brickwall"),
                    result.getInt("confuseritchie"),
                    result.getInt("fakepowerupbrick"));

                DefenseUsage defenseUsage = new DefenseUsage(
                    result.getInt("defense1"), 
                    result.getInt("defense2"), 
                    result.getInt("defense3"), 
                    result.getInt("defense4"));

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
                    trapUsage,
                    defenseUsage);

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

            
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, raceID);

            ResultSet result = stmt.executeQuery();

            if(result.next()){
                BoostUsage boostUsageData = new BoostUsage(
                    result.getInt("speedboost1"),
                    result.getInt("speedboost2"),
                    result.getInt("speedboost3"),
                    result.getInt("speedboost4"));

                OffenseUsage offenseUsageData = new OffenseUsage(
                    result.getInt("puck1"), 
                    result.getInt("puck2"),
                    result.getInt("puck3"),
                    result.getInt("puck4"));

                TrapUsage trapUsage = new TrapUsage(
                    result.getInt("oilspill1"),
                    result.getInt("brickwall"),
                    result.getInt("confuseritchie"),
                    result.getInt("fakepowerupbrick"));

                DefenseUsage defenseUsage = new DefenseUsage(
                    result.getInt("defense1"), 
                    result.getInt("defense2"), 
                    result.getInt("defense3"), 
                    result.getInt("defense4"));

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
                    trapUsage,
                    defenseUsage);
            }

            return returnLog;
        } catch (SQLException e) {
            System.err.println("There is an error query the database: " + e);
            return null;
        }
    }
}
