package RITIGM.gokartproject.persistence.gameService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

import RITIGM.gokartproject.connection.Conn;
import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.CollisionStat;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;
import RITIGM.gokartproject.persistence.gameService.interfaces.GameLogInterface;

@Component
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
            stmtUpdateMainLog.setString(1,raceLog.getPid());
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
                "SELECT LAST_INSERT_ID() as 'raceId' from racelog LIMIT 1;");
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
    public ArrayList<RaceLog> getRaceByPlayer(String pid) {
        try {
            ArrayList<RaceLog> returnLog = new ArrayList<>();
            String query = 
            """
            SELECT * from racelog
            INNER JOIN boostatt on racelog.raceid = boostatt.raceid
            INNER JOIN collision on racelog.raceid = collision.raceid
            INNER JOIN offsensestat on racelog.raceid = offsensestat.raceid
            INNER JOIN trapatt on racelog.raceid = trapatt.raceid
            WHERE pid = ?;      
            """;


            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, pid);

            ResultSet result = stmt.executeQuery();

            while(result.next()){
                //init all of the related attribue before the main one
                BoostUsage boostAtt = new BoostUsage(
                result.getInt("boostitem1"),
                result.getInt("boostitem2"),
                result.getInt("boostitem3"),
                result.getInt("boostitem4"));
                CollisionStat collisionAtt = new CollisionStat(
                    result.getInt("wallcol"), result.getInt("playercol"));
                OffenseUsage offenseAtt = new OffenseUsage(
                    result.getInt("offenseitem1"),
                    result.getInt("offenseitem2"), 
                    result.getInt("offenseitem3"),
                    result.getInt("offenseitem4"));

                TrapUsage trapAtt = new TrapUsage(
                    result.getInt("trapitem1"),
                    result.getInt("trapitem2"),
                    result.getInt("trapitem3"),
                    result.getInt("trapitem4")
                );

                RaceLog raceLog = new RaceLog(
                    result.getString("racelog.raceid"),
                    result.getTimestamp("racestarttime"),
                    result.getTime("racetime"), 
                    result.getInt("racepos"),
                    result.getInt("mapraced"),
                    result.getInt("characterused"),
                    boostAtt,
                    collisionAtt,
                    offenseAtt,
                    trapAtt);

                returnLog.add(raceLog);
            }
            return returnLog;
        } catch (SQLException e) {
            System.err.println("There is an error query the database: " + e);
            return null;
        }
    }

    @Override
    public RaceLog getRaceInfo(int raceID) {
        try {
            RaceLog returnLog = null;
            String query = 
            """
            SELECT * from racelog
            INNER JOIN boostatt on racelog.raceid = boostatt.raceid
            INNER JOIN collision on racelog.raceid = collision.raceid
            INNER JOIN offsensestat on racelog.raceid = offsensestat.raceid
            INNER JOIN trapatt on racelog.raceid = trapatt.raceid
            WHERE racelog.raceid = ?;     
            """;


            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, raceID);

            ResultSet result = stmt.executeQuery();

            if(result.next()){
                //init all of the related attribue before the main one
                BoostUsage boostAtt = new BoostUsage(
                result.getInt("boostitem1"),
                result.getInt("boostitem2"),
                result.getInt("boostitem3"),
                result.getInt("boostitem4"));
                CollisionStat collisionAtt = new CollisionStat(
                    result.getInt("wallcol"), result.getInt("playercol"));
                OffenseUsage offenseAtt = new OffenseUsage(
                    result.getInt("offenseitem1"),
                    result.getInt("offenseitem2"), 
                    result.getInt("offenseitem3"),
                    result.getInt("offenseitem4"));

                TrapUsage trapAtt = new TrapUsage(
                    result.getInt("trapitem1"),
                    result.getInt("trapitem2"),
                    result.getInt("trapitem3"),
                    result.getInt("trapitem4")
                );

                RaceLog raceLog = new RaceLog(
                    result.getString("racelog.raceid"),
                    result.getTimestamp("racestarttime"),
                    result.getTime("racetime"), 
                    result.getInt("racepos"),
                    result.getInt("mapraced"),
                    result.getInt("characterused"),
                    boostAtt,
                    collisionAtt,
                    offenseAtt,
                    trapAtt);

                returnLog = raceLog;
            }
            return returnLog;
        } catch (SQLException e) {
            System.err.println("There is an error query the database: " + e);
            return null;
        }
    }


    public static void main(String[] args) {
        // Testing for the new race insertion
        BoostUsage boostTest = new BoostUsage(1, 2, 3, 4);
        CollisionStat collisiontest = new CollisionStat(1, 2);
        OffenseUsage offenseTest = new OffenseUsage(1, 2, 3, 4);
        TrapUsage trapTest = new TrapUsage(1, 4, 2, 3);

        Date date = new Date();
        Timestamp raceStartTime = new Timestamp(date.getTime());
        Time raceTime = Time.valueOf("01:22:30"); 

        RaceLog checker = new RaceLog("26ec3c183fe511f08cc9ac1f6bbcd350", raceStartTime, raceTime, 1, 2, 4, 
        boostTest, collisiontest, offenseTest, trapTest);


        GameLogDAO test = new GameLogDAO();

        System.out.println(test.addGameLog(checker));
        test.closeConnection();
        test = null;   


        //Print all of the race log from a player
        GameLogDAO test2 = new GameLogDAO();

        ArrayList<RaceLog> raceLogs =  test2.getRaceByPlayer("26ec3c183fe511f08cc9ac1f6bbcd350");
        for(RaceLog race : raceLogs){
            System.out.println(race);
        }
        
        test2.closeConnection();
        test2 = null;  


        //Lookup race by raceID

        // GameLogDAO test3 = new GameLogDAO();

        // RaceLog data =  test3.getRaceInfo(6);
        // System.out.println(data);
        
        // test3.closeConnection();
        // test3 = null;  

    }
}
