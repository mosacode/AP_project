package Server;

import UserProfile.UserProfile;

import java.util.HashMap;

public class Server {
    private final static String API_KEY = "pcsk_77ib9Y_GKPh7Pk6et2bBhyezPSw1B97hLvFPx9DKouSQiYKJ2ruXQeBWDg15GDYT2Mi2eD";

    private HashMap<String, String> users;


    public Server(){
        users = new HashMap<>();
    }
    public String auth(UserProfile user) throws Exception {
        if(users.containsKey(user.getUsername()) && users.get(user.getUsername()).equals(user.getPassword()))
            return API_KEY;
        throw new Exception("ERROR: Unauthorized User!");
    }
    public void registerUser(UserProfile user){
        users.put(user.getUsername(), user.getPassword());
    }
}
