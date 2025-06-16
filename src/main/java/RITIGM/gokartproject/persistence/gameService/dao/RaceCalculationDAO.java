package RITIGM.gokartproject.persistence.gameService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import RITIGM.gokartproject.connection.Conn;
import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.scoreCalculation.ProfileRecalculation;
import RITIGM.gokartproject.model.scoreCalculation.RaceScore;

public class RaceCalculationDAO{
    public static final int TOTAL_MAP = 1;
    Conn check = null;
    Connection conn = null;

    public static Double raceLogCalculation(RaceLog racelog){
        RaceScore a = new RaceScore(racelog);
        return a.scoreCalculation();
    }
    
    public RaceCalculationDAO(){
        this.check = new Conn();
        this.conn = check.getConnection();
    }

    public boolean profileRecalculation(String pid) throws SQLException{
        Double profileScore = 0.0;

        

        Double[][] scoreData = new Double[TOTAL_MAP][5];

        int mapCount = 1;

        while(mapCount <= TOTAL_MAP){
            String mapQuery = 
            """
            SELECT racelog.score as playscore
            FROM racelog
            WHERE pid = ? and mapraced = ?
            ORDER BY playscore DESC
            LIMIT 5;        
            """;

            int totalCountPerMap = 0;
            PreparedStatement stmt = conn.prepareStatement(mapQuery);
            stmt.setString(1, pid);
            stmt.setInt(2,mapCount);
            ResultSet data = stmt.executeQuery();
            while(data.next()){
                scoreData[mapCount-1][totalCountPerMap] = data.getDouble("playscore");
                totalCountPerMap += 1;
            }

            while(totalCountPerMap < 5){
                scoreData[mapCount-1][totalCountPerMap] = 0.0;
                totalCountPerMap += 1;
            }

            mapCount += 1;
        }
        ProfileRecalculation a = new ProfileRecalculation();
        profileScore = a.profileCalculation(scoreData);

        String setNewScore = 
            """
                UPDATE players
                SET score = ?
                WHERE pid = ?;
            """;

        PreparedStatement stmtUpdate = conn.prepareStatement(setNewScore);
        stmtUpdate.setDouble(1, profileScore);
        stmtUpdate.setString(2, pid);

        if(stmtUpdate.executeUpdate() != 1){
            return false;
        }
        return true;
    }

}
