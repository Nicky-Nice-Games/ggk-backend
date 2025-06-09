package RITIGM.gokartproject.persistence.webService.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import RITIGM.gokartproject.model.LeaderboardData;

public interface LeaderboardInterface {
    
    /**
     * Get race map data
     * @param mapID the map id for the map
     * @return leaderbaord data for the map
     */
    abstract ArrayList<LeaderboardData> getMapLeaderboard(int mapID) throws SQLException;
}
