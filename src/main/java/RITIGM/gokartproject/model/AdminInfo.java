package RITIGM.gokartproject.model;

public class AdminInfo {

    private String adminId;
    private String email;
    private String password;
    private String username;


    private static final String TO_STRING_FORMAT = "\nPlayer Info:\r\n" + //
                                                        "\tPID = %s,\r\n" + //
                                                        "\tEmail = %s,\r\n" + //
                                                        "\tpw = %s,\r\n" + //
                                                        "\tUsername = %s,";

    /**
     * 
     * @param adminId Admin ID
     * @param email Admin Email (might not use)
     * @param password Admin Password
     * @param username Admin username
     */
    public AdminInfo (String adminId, String email, String password, String username){
        this.adminId = adminId;
        this.email = email;
        this.password = password;
        this.username = username;
    }





    public String getAdminId() {
        return adminId;
    }



    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * The to string formatting for the object
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.adminId, this.email, this.password,this.username);
    }

}
