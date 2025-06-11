package RITIGM.gokartproject.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import io.github.cdimascio.dotenv.Dotenv;


/**
 * This is used to make the new connection to the database
 * Retrieve the credential for the connection by looking at the environment variable
 * 
 * @author Peter Dang
 */
public class Conn {
    private static Session session = null;
    private static Connection conn = null;
    
    
    /**
     * Init a new connection to the database via SSH tunneling
     */
    public Conn(){
        String sshUser = System.getenv("SSHUSER");
        String sshPassword = System.getenv("SSHPASSWORD");
        String sshHost = System.getenv("SSHHOST"); 
        Integer sshPort = 22;
        String remoteMySQLHost = System.getenv("REMOTEMYSQLHOST");
        Integer remoteMySQLPort = 3306;
        Integer localPort = getAvailablePort(3307);
        String dbUser = System.getenv("DBUSER");
        String dbPassword = System.getenv("DBPASSWORD");
        String dbName = System.getenv("DBNAME");


        try {
            if(sshUser == null){
                Dotenv dotenv = Dotenv.configure().load();
                sshUser = dotenv.get("SSHUSER");
                sshPassword = dotenv.get("SSHPASSWORD");
                sshHost = dotenv.get("SSHHOST"); 
                sshPort = 22;
                remoteMySQLHost = dotenv.get("REMOTEMYSQLHOST");
                remoteMySQLPort = 3306;
                localPort = getAvailablePort(3307);
                dbUser = dotenv.get("DBUSER");
                dbPassword = dotenv.get("DBPASSWORD");
                dbName = dotenv.get("DBNAME");
            }
        } catch (Exception e) {
            int a = 1;
        }


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

    /**
     * Close the current connection
     */
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

    /**
     * Get the current connection
     * @return the connection
     */
    public Connection getConnection(){
        return conn;
    }


    private int getAvailablePort(int preferredPort) {
        try (java.net.ServerSocket socket = new java.net.ServerSocket(preferredPort)) {
            return socket.getLocalPort(); // returns preferredPort if available
        } catch (Exception e) {
            try (java.net.ServerSocket socket = new java.net.ServerSocket(0)) {
                return socket.getLocalPort(); // fallback to any available port
            } catch (Exception ex) {
                throw new RuntimeException("No available port found", ex);
            }
        }
    }   
}
