package RITIGM.gokartproject.model;

public class PlayerInfo {

    private Integer pid;
    private String email;
    private String pw;
    private Integer uid;
    private String username;

    private static final String TO_STRING_FORMAT = "\nPlayer Info:\r\n" + //
                                                        "\tPID = %d,\r\n" + //
                                                        "\tEmail = %s,\r\n" + //
                                                        "\tpw = %s,\r\n" + //
                                                        "\tUID = %d,\r\n" + //
                                                        "\tUsername = %s,";
    public PlayerInfo(Integer pid, String email, String pw, Integer uid, String username){
        this.pid = pid;
        this.email = email;
        this.pw = pw;
        this.uid = uid;
        this.username = username;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }    

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.pid, this.email, this.pw, this.uid, this.username);
    }
    public static void main(String[] args) {
        PlayerInfo test = new PlayerInfo(1, "hello", "PW", 1234, "username");
        System.out.println(test);
    }
}