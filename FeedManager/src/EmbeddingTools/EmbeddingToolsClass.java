package EmbeddingTools;

import java.net.*;
import java.net.http.HttpClient;

public abstract class EmbeddingToolsClass implements EmbeddingToolsInterface {
    protected URL url = null;
    protected HttpClient httpClient = null;
    protected HttpURLConnection httpconnection;

    public static int getDimensions(){
        return 768;
    }
    public void connectOverNetwork(String url){
        try{
            connectOverNetwork(new URL(url));
        }catch (Exception e){
            System.err.printf("Unable to connect over network to embedding tool\nURL: \"%s\"\n%s\n", url, e);
        }
    }
    public void connectOverNetwork(URL url){
        httpClient = HttpClient.newHttpClient();
        this.url = url;
    }
}
