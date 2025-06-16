package RITIGM.gokartproject.persistence.webService.interfaces;

import java.sql.SQLException;

import RITIGM.gokartproject.model.PlayerStat;

public interface PlayerStatInterface {
    
    /**
     * Retrieves a players stats
     * @param pid player id
     * @return returns all asociated player statistic attached to a particular profile
     * @throws SQLException internal retrival error from database (aka not my problem)
     */
    abstract PlayerStat getPlayerStat(String pid) throws SQLException;
}
