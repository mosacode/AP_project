package DataBase;

// public key : pcsk_77ib9Y_GKPh7Pk6et2bBhyezPSw1B97hLvFPx9DKouSQiYKJ2ruXQeBWDg15GDYT2Mi2eD

import Server.Server;
import UserProfile.UserProfile;
import VendorTypes.VendorTypeClass;
import io.pinecone.clients.Index;
import io.pinecone.clients.Pinecone;
import io.pinecone.unsigned_indices_model.QueryResponseWithUnsignedIndices;
import org.openapitools.db_control.client.model.DeletionProtection;

import java.util.ArrayList;

public class PineConeDataBase extends DataBaseClass{
    private UserProfile user;
    private Pinecone pc = null;
    public PineConeDataBase(){
    }
    public PineConeDataBase(Server server, UserProfile user){
        connect(server, user);
    }
    public boolean connect(Server server, UserProfile user){
        try{
            String apikey = server.auth(user);
            pc = new Pinecone.Builder(apikey).build();
            this.user = user;
        }catch(Exception e){
            System.out.printf(e + "\n%s\n", user);
            return false;
        }
        return true;
    }
    public boolean isConnected(){
        return pc != null;
    }
    // Yes, this is a very slow, unsafe, unmaintainable and bad approach
    // these should be done on server side, but since the code would be almost the same and
    // we don't want to get into networking and security stuff much (yet), works just fine
    public void createTable(String table, int dimensions){
        pc.createServerlessIndex(table, "cosine", dimensions, "aws", "us-east-1", DeletionProtection.DISABLED);
    }
    public boolean insert(VendorTypeClass data, String table){
        Index index = pc.getIndexConnection(table);
        index.upsert(data.getUnifier(), data.getGeneralEmbedding(), user.getUsername());
        return true;
    }
    public ArrayList<Response> get(VendorTypeClass data, String table, int k){
        Index index = pc.getIndexConnection(table);
        QueryResponseWithUnsignedIndices response = index.queryByVector(k, data.getGeneralEmbedding());
        return new ArrayList<>();
    }
}