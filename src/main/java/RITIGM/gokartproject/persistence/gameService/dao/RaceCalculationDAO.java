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


    public static Double raceLogCalculation(RaceLog racelog){
        RaceScore a = new RaceScore(racelog);
        return a.scoreCalculation();
    }

    public static void profileRecalculation(String pid) throws SQLException{
        Double profileScore = 0.0;

        Conn check = new Conn();
        Connection conn = check.getConnection();

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

        PreparedStatement stmt = conn.prepareStatement(setNewScore);
        stmt.setDouble(1, profileScore);
        stmt.setString(2, pid);

        if(stmt.executeUpdate() != 1){
            check.closeConnection();
            throw new SQLException("Error in updating database");
        }
        check.closeConnection();
    }



    public static void main(String[] args) {
        try{
            profileRecalculation("26ec3c18-3fe5-11f0-8cc9-ac1f6bbcd350");
        } catch(Exception e){
            System.err.println(e);
        }

    }

}
