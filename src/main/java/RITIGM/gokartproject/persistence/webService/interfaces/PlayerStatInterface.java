package RITIGM.gokartproject.persistence.webService.interfaces;

import java.sql.SQLException;

import RITIGM.gokartproject.model.PlayerStat;

public interface PlayerStatInterface {
    
    abstract PlayerStat getPlayerStat(String pid) throws SQLException;
}
