package RITIGM.gokartproject.model.responseReceiver;


/**
 * The request template body to create a new account with UID
 */
public class CreateUID {
    private String email;
    private String username;
    private String password;
    private Integer uid;
    

    /**
     * Contructor for make a new Object
     * @param email the email
     * @param username the username
     * @param password the password (in actual format, no hashcode)
     * @param uid (UID with the account)
     */
    public CreateUID(String email, String username, String password, Integer uid) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.uid = uid;
    }

    // All of the setters and getter for the object
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
    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }   
}
