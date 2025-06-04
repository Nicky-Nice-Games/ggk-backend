package RITIGM.gokartproject.persistence.gameService.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import RITIGM.gokartproject.model.RaceLog;

public interface GameLogInterface {
    
    /**
     * Add the new race into the game log
     * @param raceLog the game info of the player
     * @return if the game added successfully
     */
    abstract boolean addGameLog(RaceLog raceLog) throws SQLException;

    /**
     * Get the raceLog by raceID
     * @param raceID the raceID
     * @return the race corresponding to that id, null of playerID is not exsits
     */
    abstract RaceLog getRaceInfo(int raceID) ;

    /**
     * Get the raceLog by playerID
     * @param pid the playerID
     * @return the race corresponding to that playerID, 
     * null if playerID is not exsits,
     * emtpy if there is not race corresponding to that account.
     */
    abstract ArrayList<RaceLog> getRaceByPlayer(String pid) throws SQLException;
}
