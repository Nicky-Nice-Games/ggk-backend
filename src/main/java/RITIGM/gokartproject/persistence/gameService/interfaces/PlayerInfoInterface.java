package RITIGM.gokartproject.persistence.gameService.interfaces;

import java.sql.SQLException;

import RITIGM.gokartproject.model.PlayerInfo;

public interface PlayerInfoInterface {
    /**
     * Get playerInfo by playerID
     * @param playerID the player ID
     * @return the player information corresponding to the ID provided. returns null if ID does not correspond
     * an existing player
     * @throws SQLException if things gone wrong while inserting into the database
     */
    abstract PlayerInfo getPlayerInfo(String playerID) throws SQLException;

    /**
     * Get the Player Info by Username and Password
     * @param username The player username
     * @param pw The Player Password
     * @return the player information corresponding to the info. returns null if the credential doesn't corr. to an account
     * @throws SQLException if things gone wrong while inserting into the database
     */
    abstract PlayerInfo getPlayerInfoWithUsername(String username, String pw) throws SQLException;

    /**
     * Create a user for the game
     * @param email email for the account
     * @param pw password for the account
     * @param username username for the account
     * @return the player info created
     * @throws SQLException if things gone wrong while inserting into the database
     */
    abstract PlayerInfo createUser(String email, String pw, String username) throws SQLException;

    /**
     * Create a user for the game
     * @param email email for the account
     * @param pw password for the account
     * @param username username for the account
     * @param uid uid for the account
     * @return the player info created
     * @throws SQLException if things gone wrong while inserting into the database
     */
    abstract PlayerInfo createUser(String email, String pw,int uid, String username) throws SQLException;

    /**
     * allows login with UID
     * @param uid player uid
     * @return player info corresponding to uid
     * @throws SQLException IT JUST DOESN'T WORK WITH THE DATABASE, STOP ASKING
     */
    abstract PlayerInfo loginWithUID(int uid) throws SQLException;

    /**
     * checks if an email is in use already
     * @param email potential email to check
     * @return logic gate (true false base on status of email existance)
     * @throws SQLException internal server error
     */
    abstract boolean verifyEmail(String email) throws SQLException;


}
