package RITIGM.gokartproject.model;


/**
 * Return template class for the Player Info
 */
public class PlayerInfo {

    //Private fields for player related data
    private String pid;
    private String email;
    private String pw;
    private Integer uid;
    private String username;

    private static final String TO_STRING_FORMAT = "\nPlayer Info:\r\n" + //
                                                        "\tPID = %s,\r\n" + //
                                                        "\tEmail = %s,\r\n" + //
                                                        "\tpw = %s,\r\n" + //
                                                        "\tUID = %d,\r\n" + //
                                                        "\tUsername = %s,";
    
    /**
     * The template for the player data
     * @param pidm the player id
     * @param email the player email
     * @param pw the player email (ONLY THE HASH CODE)
     * @param uid the player uid 
     * @param username the player username
     */
    public PlayerInfo(String pid, String email, String pw, Integer uid, String username){
        this.pid = pid;
        this.email = email;
        this.pw = pw;
        this.uid = uid;
        this.username = username;
    }

    /**
     * Get the player ID
     * @return the player id
     */
    public String getPid() {
        return pid;
    }

    /**
     * Set the new player ID
     * @param pid
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * Get the player's email
     * @return the player's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the player's email
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the player (HASHCODE ONLY)
     * @return the player password (HASHCODE)
     */
    public String getPw() {
        return pw;
    }

    /**
     * Set the new user password (HASHCODE ONLY)
     * @param pw set the user password field (HASHCODE ONLY)
     */
    public void setPw(String pw) {
        this.pw = pw;
    }

    /**
     * Get the user ID
     * @return the user ID
     */
    public int getUid() {
        return uid;
    }

    /**
     * Set new user ID
     * @param uid the new user ID
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * Get the player's username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the new username
     * @param username new username
     */
    public void setUsername(String username) {
        this.username = username;
    }    

    /**
     * The to string formatting for the object
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.pid, this.email, this.pw, this.uid, this.username);
    }
}