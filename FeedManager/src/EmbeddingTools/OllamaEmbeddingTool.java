package EmbeddingTools;

import java.net.*;
import java.net.http.*;
import java.util.ArrayList;

import DataTypes.DataTypeEnum;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class OllamaEmbeddingTool extends EmbeddingToolsClass {
    private final DataTypeEnum[] SupportedDataTypes = new DataTypeEnum[1];

    private static OllamaEmbeddingTool instance = null;
    public static OllamaEmbeddingTool getInstance() {
        if(instance == null)
            instance = new OllamaEmbeddingTool();
        return instance;
    }
    public static OllamaEmbeddingTool getInstance(String url) {
        instance = new OllamaEmbeddingTool(url);
        return instance;
    }
    public static OllamaEmbeddingTool getInstance(URL url) {
        instance = new OllamaEmbeddingTool();
        return instance;
    }
    private OllamaEmbeddingTool(){
        SupportedDataTypes[0] = DataTypeEnum.Text;
    }
    private   OllamaEmbeddingTool(String url){
        SupportedDataTypes[0] = DataTypeEnum.Text;
        connectOverNetwork(url);
    }
    private   OllamaEmbeddingTool(URL url){
        SupportedDataTypes[0] = DataTypeEnum.Text;
        connectOverNetwork(url);
    }

    public ArrayList<Float> getEmbedding(String text) {
        try {
            String formatJson = "{ \"model\": \"nomic-embed-text\", \"prompt\": \"%s\" }";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url.toString()))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(String.format(formatJson, text)))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200){
                JSONTokener tokener = new JSONTokener(response.body());
                JSONObject jsonObject = new JSONObject(tokener);
                int sz = ((JSONArray)jsonObject.get("embedding")).toList().size();
                ArrayList<Float> embedding = new ArrayList<>(sz);
                for(int i=0; i<sz; i++){
                    embedding.add(((JSONArray)jsonObject.get("embedding")).getFloat(i));
                }
                return embedding;
            }
        }catch(Exception e){
            System.err.printf(e.toString());
        }
        return null;
    }

    public DataTypeEnum[] getDataTypes() {
        return SupportedDataTypes;
    }
}
