package RITIGM.gokartproject.model.responseReceiver;


/**
 * defunct, please remove at some point
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

    /**
     * Getter for username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * setter for username
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter for password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter for pasword
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    
}
