package RITIGM.gokartproject.persistence.gameService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public static void main(String[] args) {
        //Test get player by PID
        PlayerInfoDAO check = new PlayerInfoDAO();
        System.out.println(check.getPlayerInfo("26ec3c18-3fe5-11f0-8cc9-ac1f6bbcd350"));
        check.closeConnection();
    }
    
}
