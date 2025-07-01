package RITIGM.gokartproject.persistence.webService.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import RITIGM.gokartproject.model.AdminInfo;
import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.RaceReport;

public interface WebPlayerInfoInterface {
    /**
     * Retrives player information using id
     * @param playerID player id
     * @return player information
     * @throws SQLException internal server error 
     */
    abstract PlayerInfo getPlayerInfo(String playerID) throws SQLException;

    /**
     * retreives player info using login data
     * @param username player username
     * @param password account password
     * @return profile information
     * @throws SQLException things went bad reading from database
     */
    abstract PlayerInfo getPlayerInfoWithUsername(String username, String password) throws SQLException;

    /**
     * creates a user without a uid
     * @param email new email
     * @param password new password
     * @param username new username
     * @return a new playerinfo object with new login credentials
     * @throws SQLException issue writing data to thr database
     */
    abstract PlayerInfo createUser(String email, String password, String username) throws SQLException;

    /**
     * Creates a user with a uid
     * @param email new email
     * @param password new password
     * @param uid new uid
     * @param username new username
     * @return a new PlayerInfo object with provided credetials
     * @throws SQLException issue writing new player to the database
     */
    abstract PlayerInfo createUser(String email, String password,int uid, String username) throws SQLException;

    /**
     * Verifys if an email is already in use by another account
     * @param email email to check
     * @return true if an email existds already, and false if it does not
     * @throws SQLException checking the email in the database went wrong somehow
     */
    abstract boolean verifyEmail(String email) throws SQLException;

    /**
     * Retreives the most recent games played by a player
     * @param pid player id
     * @return a list containing the most recent (up to 5) races played by a player
     * @throws SQLException
     */
    abstract ArrayList<RaceReport> getRecentGames(String pid) throws SQLException;

    /**
     * Retrives player stats from a specific track from the database
     * @param pid player id
     * @param trackId track id
     * @return Returns IN THIS ORDER the top postion earned on the track
     * and the fastest time for a player
     * IF EITHER PIECE OF DATA IS MISSING returns null to prevent formatting issues
     */
    abstract ArrayList<Integer> getSpecificTrackData(String pid, int trackId) throws SQLException;


    /**
     * 
     * @param username Admin username
     * @param password Admin password
     * @return Returns the login data for an admin
     * @throws SQLException something went wrong with retrieval
     */
    abstract AdminInfo getAdminInfoWithUsername(String username, String password) throws SQLException;

    /**
     * 
     * @param adminId Admin id
     * @return Returns Admin data
     * @throws SQLException retreival error
     */
    abstract AdminInfo getAdminInfo(String adminId) throws SQLException;

    /**
     * 
     * @param pfp new pfp number for a player
     * @param pid player ID
     * @return 
     * @throws SQLException
     */
    abstract boolean changePfp(int pfp, String pid) throws SQLException;


}
