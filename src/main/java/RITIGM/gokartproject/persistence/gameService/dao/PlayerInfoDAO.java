package RITIGM.gokartproject.persistence.gameService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import RITIGM.gokartproject.connection.Conn;
import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.persistence.gameService.interfaces.PlayerInfoInterface;

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

    private void closeConnection(){
        this.connCls.closeConnection();
    }

    @Override
    public PlayerInfo getPlayerInfo(String playerID) {
        try {
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

        } catch (Exception e) {
            System.err.println("Something wrong while fetching player info: " + e);
            return null;
        }
    }

    @Override
    public PlayerInfo createUser(String email, String pw, String username) {
        try {
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
        } catch (SQLException e) {
            System.err.println("Something gone wrong while create the user: " + e);
            return null;
        }
    }

    @Override
    public PlayerInfo createUser(String email, String pw, int uid, String username) {
        try {
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
        } catch (SQLException e) {
            System.err.println("Something gone wrong while create the user: " + e);
            return null;
        }
    }

    @Override
    public PlayerInfo getPlayerInfoWithUsername(String username, String pw) {
        // TODO Auto-generated method stub
        return null;
    }


    public static void main(String[] args) {
        //Test get player by PID
        PlayerInfoDAO check = new PlayerInfoDAO();

        check.closeConnection();
    }
    
}
