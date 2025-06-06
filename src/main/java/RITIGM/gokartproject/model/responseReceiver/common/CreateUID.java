package RITIGM.gokartproject.model.responseReceiver.common;

public class CreateUID {
    private String email;
    private String username;
    private String password;
    private Integer uid;
    

    public CreateUID(String email, String username, String password, Integer uid) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.uid = uid;
    }
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
