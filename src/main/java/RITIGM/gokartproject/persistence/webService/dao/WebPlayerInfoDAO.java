package RITIGM.gokartproject.persistence.webService.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import RITIGM.gokartproject.connection.Conn;
import RITIGM.gokartproject.model.AdminInfo;
import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;
import RITIGM.gokartproject.persistence.webService.interfaces.WebPlayerInfoInterface;

/**
 * Handles writing and Reading of data to the database
 * pertaining to player information as it is needed for webservice
 */ 
@Component
public class WebPlayerInfoDAO implements WebPlayerInfoInterface{
    private Conn connCls;
    private Connection conn; 

    public WebPlayerInfoDAO(){

        this.connCls = new Conn();
        this.conn = connCls.getConnection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerInfo getPlayerInfo(String playerID) throws SQLException{

        PlayerInfo player = null;

        String query = "SELECT * FROM players WHERE pid = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
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
    public PlayerInfo getPlayerInfoWithUsername(String username, String password) throws SQLException{

        String checkPw =  Integer.toString(password.hashCode());
        PlayerInfo player = null;

        String query = "SELECT * FROM players WHERE username = ? AND Password = ?;";

        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setString(1, username);
        stmt.setString(2, checkPw);

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
    public PlayerInfo createUser(String email, String password, String username) throws SQLException{
        System.err.println(password + " "  + email + " " + password);
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

        String queryLookUp =  "SELECT * FROM players WHERE Email = ? AND Password = ? AND username = ?;";
        PreparedStatement stmtCheck = conn.prepareStatement(queryLookUp);
        stmtCheck.setString(1, email);
        stmtCheck.setString(2, checkPw);
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
        stmtCheck.setString(1, email);
        stmtCheck.setString(2, checkPw);
        stmtCheck.setString(3, username);
        stmtCheck.setInt(4, uid);

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

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<RaceLog> getRecentGames(String pid) throws SQLException{
        
        ArrayList<RaceLog> recentRaces = new ArrayList<RaceLog>(5);
        String query = "SELECT *\n" + //
                        "FROM racelog\n" + //
                        "WHERE pid = ?\n" + //
                        "ORDER BY racestarttime, raceid DESC\n" + //
                        "LIMIT 5;";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, pid);
        ResultSet check = stmt.executeQuery();
        while(check.next()){
            recentRaces.add(
                new RaceLog(
                    check.getString("pid"), 
                    check.getTimestamp("racestarttime"), 
                    check.getInt("racetime"), 
                    check.getInt("racepos"), 
                    check.getInt("mapraced"), 
                    check.getInt("characterused"), 
                    check.getInt("collisionwithplayer"), 
                    check.getInt("collisionwithwall"), 
                    check.getInt("felloffmap"), 
                    new BoostUsage(
                        check.getInt("speedboost1"), 
                        check.getInt("speedboost2"), 
                        check.getInt("speedboost3")), 
                    new OffenseUsage(
                        check.getInt("puck1"), 
                        check.getInt("puck2")), 
                    new TrapUsage(
                        check.getInt("oilspill1"), 
                        check.getInt("oilspill2"))));
        }

        return recentRaces;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Integer> getSpecificTrackData(String pid, int trackId) throws SQLException{
        ArrayList<Integer> stats = new ArrayList<Integer>(2);

        //postiion retrieval
        String topQuery = "SELECT MIN(racelog.racepos) as topPos\n" + //
                        "FROM racelog\n" + //
                        "WHERE pid = ?\n" + //
                        "AND mapraced = ?;";
        PreparedStatement stmtTop = conn.prepareStatement(topQuery);
        stmtTop.setString(1, pid);
        stmtTop.setInt(2,trackId);
        ResultSet check = stmtTop.executeQuery();
        if(check.next()){
            stats.add(check.getInt("topPos"));
        }
        else{
            return null;
        }

        //Time retrieval
        String timeQuery = "SELECT MIN(racelog.racetime) as fastestTime\n" + //
                        "FROM racelog\n" + //
                        "WHERE pid = ?\n" + //
                        "AND mapraced = ?;";
        PreparedStatement stmtTime = conn.prepareStatement(timeQuery);
        stmtTime.setString(1, pid);
        stmtTime.setInt(2, trackId);
        check = stmtTime.executeQuery();
        if(check.next()){
            stats.add(check.getInt("fastestTime"));
        }
        else{
            return null;
        }
        return stats;
        //You've found the secret comment! 
    }

    public AdminInfo getAdminInfoWithUsername(String username, String password) throws SQLException{
        AdminInfo admin = null;
        String queryCheck = "SELECT * FROM players WHERE username = ? AND Password = ?";
        String checkPassword =  Integer.toString(password.hashCode());

        PreparedStatement stmtAdminCheck = conn.prepareStatement(queryCheck);
        stmtAdminCheck.setString(1, username);
        stmtAdminCheck.setString(2, checkPassword);
        ResultSet checkIfAdmin = stmtAdminCheck.executeQuery();

        if(checkIfAdmin.next()){
            admin = getAdminInfo(checkIfAdmin.getString("pid"));
        }

        return admin;

    }

    /**
     * Check if the provided player ID is also and admin of the system
     * 
     * Return null if there is not admin with those details
     * 
     * @throws SQLException if therer is an error getting info from the database
     */
    public AdminInfo getAdminInfo(String adminId) throws SQLException{
        AdminInfo admin = null;
        String query = "SELECT * FROM admin WHERE admin_id = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, adminId);

        ResultSet check = stmt.executeQuery();

        // Checking if an player is an admin
        if(check.next()){
            String infoQuery = "SELECT * FROM players WHERE pid = ?";

            // Getting admin information
            PreparedStatement stmtInfo = conn.prepareStatement(infoQuery);
            stmtInfo.setString(1, adminId);

            ResultSet adminInfo = stmtInfo.executeQuery();
            
            if(adminInfo.next()){
                admin = new AdminInfo(
                    adminInfo.getString("pid"),
                    adminInfo.getString("Email"),
                    adminInfo.getString("Password"),
                    adminInfo.getString("username"));
            } else{
                throw new SQLException();
            }
        }

        return admin;

    }

    
}
