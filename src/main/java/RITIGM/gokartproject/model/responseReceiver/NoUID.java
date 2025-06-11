package RITIGM.gokartproject.model.responseReceiver;

/**
 * Contructor for the template of creating a new account with no UID
 */
public class NoUID {
    private String email;
    private String username;
    private String password;

    
    /**
     * Contructor to make a new account with to UID
     * @param email
     * @param username
     * @param password
     */
    public NoUID(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    //Setter and getter for the new account object
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
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
