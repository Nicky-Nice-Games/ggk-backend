package RITIGM.gokartproject.persistence.gameService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import RITIGM.gokartproject.connection.Conn;
import RITIGM.gokartproject.model.RaceLog;
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

    @Override
    public boolean addGameLog(RaceLog raceLog) {
        try{

            Integer raceID = null; // race ID for the function

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

            PreparedStatement stmtGetRaceID = conn.prepareStatement("SELECT LAST_INSERT_ID() from racelog;");
            // ResultSet

            return false;


            
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

}
