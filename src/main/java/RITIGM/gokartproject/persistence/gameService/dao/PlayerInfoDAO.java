package RITIGM.gokartproject.persistence.gameService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import RITIGM.gokartproject.connection.Conn;
import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.persistence.gameService.interfaces.PlayerInfoInterface;

/**
 * {@inheritDoc}
 */
@Component
public class PlayerInfoDAO implements PlayerInfoInterface{

    

    private Conn connCls = null;
    private Connection conn = null;

    public PlayerInfoDAO(){
        try {
            this.connCls = new Conn();
            this.conn = this.connCls.getConnection();
        } catch (Exception e) {
            System.err.println("Error in init a new connection: " + e);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerInfo getPlayerInfo(String playerID) throws SQLException{
       
            PlayerInfo player = null;
            
            String query = "SELECT * FROM players WHERE pid = ?";
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, playerID);

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
    @Override
    public PlayerInfo createUser(String email, String pw, String username) throws SQLException{
        
            String checkpw = Integer.toString(pw.hashCode());
            PlayerInfo returnPlayer = null;

            String query = "INSERT INTO players (Email, Password, username) VALUE (?, ?, ?);";
            PreparedStatement stmt = this.conn.prepareStatement(query);
            
            stmt.setString(1, email);
            stmt.setString(2, checkpw);
            stmt.setString(3, username);

            int result = stmt.executeUpdate();

            if (result != 1){
                System.err.println("Something gone wrong while update");
            }

            String queryLookUp = "SELECT * FROM players WHERE Email = ? AND Password = ? AND username = ?;";
            PreparedStatement stmtCheck = this.conn.prepareStatement(queryLookUp);
            stmtCheck.setString(1, email);
            stmtCheck.setString(2, checkpw);
            stmtCheck.setString(3, username);

            ResultSet check = stmtCheck.executeQuery();
            if(check.next()){
                returnPlayer = new PlayerInfo(check.getString("pid"),
                check.getString("Email"),check.getString("Password"),
                check.getInt("uid"),
                check.getString("username"));

            }

            return returnPlayer;
      
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerInfo createUser(String email, String pw, int uid, String username) throws SQLException{

            String checkpw = Integer.toString(pw.hashCode());
            PlayerInfo returnPlayer = null;

            String query = "INSERT INTO players (Email, Password, username, uid) VALUE (?, ?, ?, ?);";
            PreparedStatement stmt = this.conn.prepareStatement(query);
            
            stmt.setString(1, email);
            stmt.setString(2, checkpw);
            stmt.setString(3, username);
            stmt.setInt(4, uid);
            int result = stmt.executeUpdate();

            if (result != 1){
                System.err.println("Something gone wrong while update");
            }

            String queryLookUp = "SELECT * FROM players WHERE Email = ? AND Password = ? AND username = ? AND uid = ?;";
            PreparedStatement stmtCheck = this.conn.prepareStatement(queryLookUp);
            stmtCheck.setString(1, email);
            stmtCheck.setString(2, checkpw);
            stmtCheck.setString(3, username);
            stmtCheck.setInt(4, uid);

            ResultSet check = stmtCheck.executeQuery();
            if(check.next()){
                returnPlayer = new PlayerInfo(check.getString("pid"),
                check.getString("Email"),check.getString("Password"),
                check.getInt("uid"),
                check.getString("username"));
            }

            return returnPlayer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerInfo getPlayerInfoWithUsername(String username, String pw) throws SQLException {
        
            PlayerInfo returnInfo = null;
            String hashedPw = Integer.toString(pw.hashCode());
            String query = "SELECT * FROM players WHERE username = ? AND Password = ?;";
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, hashedPw);

            ResultSet result = stmt.executeQuery();

            if(result.next()){
                returnInfo = new PlayerInfo(
                    result.getString("pid"),
                    result.getString("Email"),
                    result.getString("Password"),
                    result.getInt("uid"),
                    result.getString("username"));
            }
            return returnInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerInfo loginWithUID(int uid) throws SQLException {
        PlayerInfo info = null;
            String query = "SELECT * FROM players WHERE uid = ?";
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, uid);
            ResultSet result = stmt.executeQuery();

            if(result.next()){
                info = new PlayerInfo(
                    result.getString("pid"),
                    result.getString("Email"),
                    result.getString("Password"),
                    result.getInt("uid"),
                    result.getString("username"));
            }

            if (result.next()){
                throw new SQLException("Duplicate UID in the codebase");
            }
            return info;

    }

    /**
     * 
     */
    @Override
    public boolean verifyEmail(String email) throws SQLException{

        String query = "SELECT EXISTS (\n" + //
                        "  SELECT 1\n" + //
                        "  FROM players\n" + //
                        "  WHERE Email = ?\n" + //
                        ") AS EmailExists;";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, email);
        ResultSet check = stmt.executeQuery();
        if(check.next()){
            return check.getBoolean("EmailExists");
        }
        return false;
    }
}
