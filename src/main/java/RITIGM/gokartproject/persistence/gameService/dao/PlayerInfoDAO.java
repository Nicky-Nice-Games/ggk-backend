package RITIGM.gokartproject.persistence.gameService.dao;

import java.sql.Connection;

import RITIGM.gokartproject.connection.Conn;
import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.persistence.gameService.interfaces.PlayerInfoInterface;

public class PlayerInfoDAO implements PlayerInfoInterface{

    private Conn connCls = null;
    private Connection conn = null;

    public PlayerInfoDAO() {
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

    /**
     * {@inheritdoc}
     */
    @Override
    public PlayerInfo getPlayerInfo(int playerID){
        try {
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
