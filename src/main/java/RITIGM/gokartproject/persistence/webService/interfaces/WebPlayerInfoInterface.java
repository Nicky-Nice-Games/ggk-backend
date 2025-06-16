package RITIGM.gokartproject.persistence.webService.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.model.RaceLog;

public interface WebPlayerInfoInterface {
    /**
     * 
     * @param playerID
     * @return
     * @throws SQLException
     */
    abstract PlayerInfo getPlayerInfo(String playerID) throws SQLException;

    /**
     * 
     * @param username
     * @param pw
     * @return
     * @throws SQLException
     */
    abstract PlayerInfo getPlayerInfoWithUsername(String username, String password) throws SQLException;

    /**
     * 
     * @param email
     * @param pw
     * @param username
     * @return
     * @throws SQLException
     */
    abstract PlayerInfo createUser(String email, String password, String username) throws SQLException;

    /**
     * 
     * @param email
     * @param pw
     * @param uid
     * @param username
     * @return
     * @throws SQLException
     */
    abstract PlayerInfo createUser(String email, String password,int uid, String username) throws SQLException;

    /**
     * 
     * @param email
     * @return
     * @throws SQLException
     */
    abstract boolean verifyEmail(String email) throws SQLException;

    /**
     * 
     * @param pid
     * @return
     * @throws SQLException
     */
    abstract ArrayList<RaceLog> getRecentGames(String pid) throws SQLException;

    /**
     * Retrives player stats from a specific track from the database
     * @param pid player id
     * @param trackId track id
     * @return Returns IN THIS ORDER the top postion earned on the track
     * and the fastest time for a player
     * IF EITHER PIECE OF DATA IS MISSING returns null to prevent formatting issues
     */
    abstract ArrayList<Integer> getSpecificTrackData(String pid, int trackId) throws SQLException;


}
