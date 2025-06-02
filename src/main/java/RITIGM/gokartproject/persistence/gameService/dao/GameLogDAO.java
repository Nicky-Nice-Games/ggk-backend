package RITIGM.gokartproject.persistence.gameService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import RITIGM.gokartproject.connection.Conn;
import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.CollisionStat;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;
import RITIGM.gokartproject.persistence.gameService.interfaces.GameLogInterface;

public class GameLogDAO implements GameLogInterface {

    private Conn connCls = null;
    private Connection conn = null;

    public GameLogDAO() {
        try {
            this.connCls = new Conn();
            this.conn = this.connCls.getConnection();
        } catch (Exception e) {
            System.err.println("Error in init a new connection: " + e);
        }
    }

    private void closeConnection(){
        this.connCls.closeConnection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addGameLog(RaceLog raceLog) {
        try{

            Integer raceID = -1; // race ID for the function

            // Update the main table of race log

            String query = """
                            INSERT INTO racelog
                                (pid, racestarttime, racetime, racepos, mapraced, characterused)
                            VALUES
                                (?,?,?,?,?,?);
                            """;
            PreparedStatement stmtUpdateMainLog = conn.prepareStatement(query);
            stmtUpdateMainLog.setInt(1,raceLog.getPid());
            stmtUpdateMainLog.setTimestamp(2, raceLog.getRaceStartTime());
            stmtUpdateMainLog.setTime(3, raceLog.getRaceTime());
            stmtUpdateMainLog.setInt(4,raceLog.getRacePos());
            stmtUpdateMainLog.setInt(5, raceLog.getMapRaced());
            stmtUpdateMainLog.setInt(6, raceLog.getCharacterUsed());

            Integer updateCheck = stmtUpdateMainLog.executeUpdate();
            Boolean check = (updateCheck == 1) ? true : false;
            
            if (!check){
                return false;
            }

            //Get the most recent ID of the race for the other table foreign key

            PreparedStatement stmtGetRaceID = conn.prepareStatement(
                "SELECT LAST_INSERT_ID() as 'raceId' from racelog LIMIT 1");
            ResultSet raceIDResultSet = stmtGetRaceID.executeQuery();

            if (raceIDResultSet.next()){
                raceID = raceIDResultSet.getInt("raceId");
            } else{
                return false;
            }

            // Insert boost from race data into table

            String queryInsertBoostStat = """
                    INSERT INTO boostatt VALUES (?,?,?,?,?);
                    """;

            PreparedStatement stmtBoostStat = this.conn.prepareStatement(queryInsertBoostStat);
            stmtBoostStat.setInt(1, raceID);
            stmtBoostStat.setInt(2, raceLog.getBoostStat().getBoostItem1());
            stmtBoostStat.setInt(3, raceLog.getBoostStat().getBoostItem2());
            stmtBoostStat.setInt(4, raceLog.getBoostStat().getBoostItem3());
            stmtBoostStat.setInt(5, raceLog.getBoostStat().getBoostItem4());

            Integer boostUpdateCheck = stmtBoostStat.executeUpdate();
            
            if(boostUpdateCheck != 1){
                return false;
            }

            // Insert collision from race data into table

            String queryInsertCollision = "INSERT INTO collision VALUES (?,?,?);";

            PreparedStatement stmtCollision = this.conn.prepareStatement(queryInsertCollision);
            stmtCollision.setInt(1, raceID);
            stmtCollision.setInt(2, raceLog.getCollisionStat().getWallCollision());
            stmtCollision.setInt(3, raceLog.getCollisionStat().getPlayerCollision());

            Integer collisionCheck = stmtCollision.executeUpdate();
            
            if(collisionCheck != 1){
                return false;
            }


            // Insert offense from race data into table
            String queryOffense = "INSERT INTO offsensestat VALUES(?,?,?,?,?);";
            PreparedStatement stmtOffense = this.conn.prepareStatement(queryOffense);
            stmtOffense.setInt(1, raceID);
            stmtOffense.setInt(2,raceLog.getOffenseStat().getBoostItem1());
            stmtOffense.setInt(3,raceLog.getOffenseStat().getBoostItem2());
            stmtOffense.setInt(4,raceLog.getOffenseStat().getBoostItem3());
            stmtOffense.setInt(5,raceLog.getOffenseStat().getBoostItem4());


            Integer offenseCheck = stmtOffense.executeUpdate();
            
            if(offenseCheck != 1){
                return false;
            }

            // Insert trap from race data into table
            String queryTrap = "INSERT INTO trapatt VALUES(?,?,?,?,?);";
            PreparedStatement stmtTrap = this.conn.prepareStatement(queryTrap);
            stmtTrap.setInt(1, raceID);
            stmtTrap.setInt(2,raceLog.getTrapUsage().getTrapItem1());
            stmtTrap.setInt(3,raceLog.getTrapUsage().getTrapItem2());
            stmtTrap.setInt(4,raceLog.getTrapUsage().getTrapItem3());
            stmtTrap.setInt(5,raceLog.getTrapUsage().getTrapItem4());


            Integer trapCheck = stmtTrap.executeUpdate();
            
            if(trapCheck != 1){
                return false;
            }

            return true;
            
        } catch(SQLException e){
            System.err.println("Error insert into SQL: "+ e);
            return false;
        }
    }

    @Override
    public RaceLog[] getRaceByPlayer(int pid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RaceLog getRaceInfo(int raceID) {
        // TODO Auto-generated method stub
        return null;
    }


    public static void main(String[] args) {
        //Testing for the new race insertion
        BoostUsage boostTest = new BoostUsage(1, 2, 3, 4);
        CollisionStat collisiontest = new CollisionStat(1, 2);
        OffenseUsage offenseTest = new OffenseUsage(1, 2, 3, 4);
        TrapUsage trapTest = new TrapUsage(1, 4, 2, 3);

        Date date = new Date();
        Timestamp raceStartTime = new Timestamp(date.getTime());
        Time raceTime = Time.valueOf("01:22:30"); 

        RaceLog checker = new RaceLog(1, raceStartTime, raceTime, 1, 2, 4, 
        boostTest, collisiontest, offenseTest, trapTest);


        GameLogDAO test = new GameLogDAO();

        System.out.println(test.addGameLog(checker));
        test.closeConnection();
        test = null;

        
    }
}
