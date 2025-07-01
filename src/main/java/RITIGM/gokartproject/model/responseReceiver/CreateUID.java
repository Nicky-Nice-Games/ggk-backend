package RITIGM.gokartproject.model.responseReceiver;


/**
 * The request template body to create a new account with UID
 */
public class CreateUID {
    private String email;
    private String username;
    private String password;
    private Integer uid;
    private Integer pfp;
    

    /**
     * Contructor for make a new Object
     * @param email the email
     * @param username the username
     * @param password the password (in actual format, no hashcode)
     * @param uid (UID with the account)
     */
    public CreateUID(String email, String username, String password, Integer uid, Integer pfp) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.uid = uid;
        this.pfp = pfp;
    }

    public Integer getPfp() {
        return pfp;
    }

    public void setPfp(Integer pfp) {
        this.pfp = pfp;
    }

    // All of the setters and getter for the object
    /**
     * getter for email
     * @return player email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for email
     * @param email player email
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

    /**
     * getter for uid
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * setter for uid
     * @param uid uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }   
}
