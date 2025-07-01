package RITIGM.gokartproject.model.responseReceiver;

/**
 * Contructor for the template of creating a new account with no UID
 */
public class NoUID {
    private String email;
    private String username;
    private String password;
    private Integer pfp;

    
    /**
     * Contructor to make a new account with to UID
     * @param email
     * @param username
     * @param password
     */
    public NoUID(String email, String username, String password, Integer pfp) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.pfp = pfp;
    }

    public Integer getPfp() {
        return pfp;
    }

    public void setPfp(Integer pfp) {
        this.pfp = pfp;
    }

    /**
     * getter for user email
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for user email
     * @param email user email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter for username
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
     * setter for password
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    

    
}
