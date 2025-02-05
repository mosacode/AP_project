package VendorTypes;

import DataTypes.DataTypeEnum;
import EmbeddingTools.EmbeddingService;
import EmbeddingTools.EmbeddingToolsClass;
import EmbeddingTools.OllamaEmbeddingTool;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;

public abstract class VendorTypeClass implements VendorTypeInterface{
    protected DataTypeEnum[] datatTypes;

    protected URL url;

    protected Pair<Integer,ArrayList<Float>> generalEmbedding = new Pair<>(null, null);

    protected static EmbeddingService embeddingService = new EmbeddingService(OllamaEmbeddingTool.getInstance("http://localhost:11434/api/embeddings"));

    protected VendorTypeClass(){
    }

    protected VendorTypeClass(DataTypeEnum[] datatTypes){
        this.datatTypes = datatTypes;
    }

    public String getUnifier(){
        return this.getClass().toString() + "\n" + url.toString() + "\n";
    }

    public String describe(){
        return "";
    }

    public URL getURL(){
        return url;
    }

    public ArrayList<Float> getGeneralEmbedding(){
        return this.getGeneralEmbedding(generalEmbedding.getKey() == null
                || generalEmbedding.getKey().hashCode() != embeddingService.getEmbedder().hashCode());
    }

    public static void setEmbeddingService(EmbeddingToolsClass embeddingTool) {
        VendorTypeClass.embeddingService.setEmbeddingTool(embeddingTool);
    }
}