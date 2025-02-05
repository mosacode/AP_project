package UserProfile;

public class UserProfile {
    private String username = "john_temple_bell";
    private String password = "nothing_is_obvious";
    public UserProfile(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String toString(){
        return username + '\n' + password;
    }
}
