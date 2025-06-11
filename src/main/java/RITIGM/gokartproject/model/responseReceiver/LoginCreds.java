package RITIGM.gokartproject.model.responseReceiver;


/**
 * Acceptance template for username and password for the project
 */
public class LoginCreds {
    private String username;
    private String password;

    /**
     * Contructor to make a new username and password to login
     * @param username
     * @param password
     */
    public LoginCreds(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Setters and getters for the object
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
