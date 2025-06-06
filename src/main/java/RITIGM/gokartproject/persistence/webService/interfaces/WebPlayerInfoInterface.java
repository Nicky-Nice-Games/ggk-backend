package RITIGM.gokartproject.persistence.webService.interfaces;

import java.sql.SQLException;

import RITIGM.gokartproject.model.PlayerInfo;

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
    abstract PlayerInfo getPlayerInfoWithUsername(String username, String pw) throws SQLException;

    /**
     * 
     * @param email
     * @param pw
     * @param username
     * @return
     * @throws SQLException
     */
    abstract PlayerInfo createUser(String email, String pw, String username) throws SQLException;

    /**
     * 
     * @param email
     * @param pw
     * @param uid
     * @param username
     * @return
     * @throws SQLException
     */
    abstract PlayerInfo createUser(String email, String pw,int uid, String username) throws SQLException;


}
