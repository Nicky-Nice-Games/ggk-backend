package RITIGM.gokartproject.persistence.gameService.dao;

import java.sql.Connection;

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


    @Override
    public PlayerInfo getPlayerInfo(int playerID) {
        try {
            PlayerInfo player = null;


            // String query = "SEL"

            return player;
        } catch (Exception e) {
            System.err.println("Something wrong while fetching player info: " + e);
            return null;
        }
    }
    
}
