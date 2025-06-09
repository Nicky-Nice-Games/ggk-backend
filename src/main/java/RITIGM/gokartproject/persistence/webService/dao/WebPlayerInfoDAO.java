package RITIGM.gokartproject.persistence.webService.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import RITIGM.gokartproject.connection.Conn;
import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.persistence.webService.interfaces.WebPlayerInfoInterface;

/**
 * {@inheritDoc}
 */ 
@Component
public class WebPlayerInfoDAO implements WebPlayerInfoInterface{

    private Conn connCls = null;
    private Connection conn = null;

    public WebPlayerInfoDAO(){
        try {
            this.connCls = new Conn();
            this.conn = this.connCls.getConnection();
        } catch (Exception e) {
            System.err.println("Error in init a new connection: " + e);
        }
    }

    private void closeConnection(){
        connCls.closeConnection();
    }

    /**
     * {@inheritDoc}
     */
    public PlayerInfo getPlayerInfo(String playerID) throws SQLException{

        PlayerInfo player = null;

        String query = "SELECT * FROM players WHERE pid = ?";
        PreparedStatement stmt = conn.prepareStatement(query);

        ResultSet result = stmt.executeQuery();
        if(result.next()){
                player = new PlayerInfo(
                result.getString("pid"),
                result.getString("Email"), 
                result.getString("Password"),
                result.getInt("uid"), 
                result.getString("username"));
            } else {
                return null;
            }

        return player;
    }

    /**
     * {@inheritDoc}
     */
    public PlayerInfo getPlayerInfoWithUsername(String username, String password) throws SQLException{

        String checkPw =  Integer.toString(password.hashCode());
        PlayerInfo player = null;

        String query = "SELECT * FROM players WHERE username = ? AND Password = ?;";
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet result = stmt.executeQuery();
        if(result.next()){
                player = new PlayerInfo(
                result.getString("pid"),
                result.getString("Email"), 
                result.getString("Password"),
                result.getInt("uid"), 
                result.getString("username"));
            } else {
                return null;
            }

        return player;
    }

    /**
     * {@inheritDoc}
     */
    public PlayerInfo createUser(String email, String password, String username) throws SQLException{

        String checkPw =  Integer.toString(password.hashCode());
        PlayerInfo returnPlayer = null;

        String query = "INSERT INTO players (Email, Password, username) VALUE (?, ?, ?);";
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setString(1, email);
        stmt.setString(2, checkPw);
        stmt.setString(3, username);

        int result = stmt.executeUpdate();

        if(result != 1){
            System.err.println("Something went wrong while updating");
        }

        String queryLookUp = "SELECT * FROM players WHERE Email = ? AND Password = ? AND username = ?;";
        PreparedStatement stmtCheck = conn.prepareStatement(queryLookUp);
        stmt.setString(1, email);
        stmt.setString(2, checkPw);
        stmt.setString(3, username);

        ResultSet check = stmtCheck.executeQuery();
        if(check.next()){
            returnPlayer = new PlayerInfo(check.getString("pid"),
            check.getString("Email"),check.getString("Password"),
            check.getInt("uid"),
            check.getString("username"));

        }
        else{
            return null;
        }

        return returnPlayer;
    }

    /**
     * {@inheritDoc}
     */
    public PlayerInfo createUser(String email, String password,int uid, String username) throws SQLException{
        String checkPw =  Integer.toString(password.hashCode());
        PlayerInfo returnPlayer = null;

        String query = "INSERT INTO players (Email, Password, username, uid) VALUE (?, ?, ?, ?);";
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setString(1, email);
        stmt.setString(2, checkPw);
        stmt.setString(3, username);
        stmt.setInt(4, uid);

        int result = stmt.executeUpdate();

        if(result != 1){
            System.err.println("Something went wrong while updating");
        }

        String queryLookUp = "SELECT * FROM players WHERE Email = ? AND Password = ? AND username = ? AND uid = ?;";
        PreparedStatement stmtCheck = conn.prepareStatement(queryLookUp);
        stmt.setString(1, email);
        stmt.setString(2, checkPw);
        stmt.setString(3, username);
        stmt.setInt(4, uid);

        ResultSet check = stmtCheck.executeQuery();
        if(check.next()){
            returnPlayer = new PlayerInfo(check.getString("pid"),
            check.getString("Email"),check.getString("Password"),
            check.getInt("uid"),
            check.getString("username"));

        }
        else{
            return null;
        }

        return returnPlayer;
    }

    
}
