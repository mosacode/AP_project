package VendorTypes;

import DataTypes.DataTypeEnum;
import EmbeddingTools.EmbeddingService;
import EmbeddingTools.OllamaEmbeddingTool;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RawTextOrg extends VendorTypeClass{

    ///
    public RawTextOrg(String urlAddress){
        super();
        datatTypes = new DataTypeEnum[2];
        datatTypes[0] = DataTypeEnum.Text;
        try{
            url = new URL(urlAddress);
        }catch (MalformedURLException e){
            System.err.printf("Bad URL while making RawTextOrg-%d!\n" + e);
        }
    }
    public String getText(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String content = new String();
            String line;
            while ((line = reader.readLine()) != null) {
                content = content.concat(line + "\n");
            }
            return content;
        }catch(Exception e){
            System.err.printf(e + "\n");
        }
        return url.getFile();
    }
    public ArrayList<Float> getTextEmbedding(){
        return embeddingService.getEmbedding(getText());
    }
    public ArrayList<Float> getGeneralEmbedding(boolean explicit){
        if(explicit)
            generalEmbedding = new Pair<>(embeddingService.getEmbedder().hashCode(),getTextEmbedding());
        return generalEmbedding.getValue();
    }
}
