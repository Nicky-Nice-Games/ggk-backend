package RITIGM.gokartproject.connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Connection;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import io.github.cdimascio.dotenv.Dotenv;

public class Conn {
    private Session session = null;
    private Connection conn = null;
    private Dotenv dotenv = null;
        
    public Conn(){
        dotenv = Dotenv.configure().load();
        String sshUser = dotenv.get("SSHUSER");
        String sshPassword = dotenv.get("SSHPASSWORD");
        String sshHost = dotenv.get("SSHHOST");
        Integer sshPort = 22;
        String remoteMySQLHost = dotenv.get("REMOTEMYSQLHOST");
        Integer remoteMySQLPort = Integer.parseInt(dotenv.get("REMOTEMYSQLPORT"));
        Integer localPort = Integer.parseInt(dotenv.get("LOCALPORT"));
        String dbUser = dotenv.get("DBUSER");
        String dbPassword = dotenv.get("DBPASSWORD");
        String dbName = dotenv.get("DBNAME");

        try {
        // Set up SSH tunnel
        JSch jsch = new JSch();
        session = jsch.getSession(sshUser, sshHost, sshPort);
        session.setPassword(sshPassword);

        // Avoid asking for key confirmation
        session.setConfig("StrictHostKeyChecking", "no");

        session.connect();
        System.out.println("SSH Connected");

        // Forward local port to remote MySQL port
        int assignedPort = session.setPortForwardingL(localPort, remoteMySQLHost, remoteMySQLPort);
        System.out.println("Port Forwarded: localhost:" + assignedPort + " -> " + remoteMySQLHost + ":" + remoteMySQLPort);

        // Connect to MySQL through the forwarded port
        String jdbcUrl = "jdbc:mysql://localhost:" + localPort + "/" + dbName + "?useSSL=false&serverTimezone=UTC";
        conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
        System.out.println("Database Connected!");


    }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
                if (conn != null) conn.close();
                if (session != null) session.disconnect();
                System.out.println("Connections closed.");
                conn = null;
                session = null;
        }   catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public Connection getConnection(){
        return this.conn;
    }


    public void testConnection(){
        try {
            String query = "SELECT * FROM test WHERE checkid = 1;";
            PreparedStatement stmt = this.conn.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            if(result.next()){
                Integer idCheck = result.getInt("checkid");
                String stringCheck = result.getString("checkstring");
                Integer intcheck = result.getInt("checkint");
                Timestamp timeCheck = result.getTimestamp("checktime");
                String stringTime = timeCheck.toString();

                System.out.println("TEST RETRIEVE DATA");
                System.out.println("check id : " + idCheck);
                System.out.println("check string : " + stringCheck);
                System.out.println("check int : " + intcheck);
                System.out.println("check time : " + stringTime);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    public static void main(String[] args) {
        
        Conn test = new Conn();
        test.testConnection();
        test.closeConnection();
        
    }
}
