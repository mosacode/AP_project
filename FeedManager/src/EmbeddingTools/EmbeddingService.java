package EmbeddingTools;

import DataTypes.*;

import java.util.ArrayList;

/**
 * this is a wrapper for all embedding tools implemented.
 * it provides with basic checks and so for more abstraction.
 * you can code abstract with this, and then use the same code for different tools by supplying a new entity to it.
 */

public class EmbeddingService extends EmbeddingToolsClass{
    EmbeddingToolsClass embedder = null;
    private boolean[] availableServices = new boolean[DataTypeEnum.values().length];
    public EmbeddingService(EmbeddingToolsClass embedder){
        setEmbeddingTool(embedder);
    }
    public EmbeddingToolsClass getEmbedder(){
        return embedder;
    }
    public void setEmbeddingTool(EmbeddingToolsClass embedder){
        for(int i=0; i<DataTypeEnum.values().length; i++)
            availableServices[i] = false;
        for(int i=0; i<embedder.getDataTypes().length; i++){
            availableServices[embedder.getDataTypes()[i].ordinal()] = true;
        }
        this.embedder = embedder;
    }
    public ArrayList<Float> getEmbedding(String text) {
        if(availableServices[DataTypeEnum.Text.ordinal()])
            return embedder.getEmbedding(text);
        return null;
    }
    public ArrayList<Float> getEmbedding(Image image){
        if(availableServices[DataTypeEnum.Image.ordinal()])
            return embedder.getEmbedding(image);
        return null;
    }
    public ArrayList<Float> getEmbedding(Audio audio){
        if(availableServices[DataTypeEnum.Audio.ordinal()])
            return embedder.getEmbedding(audio);
        return null;
    }
    public ArrayList<Float> getEmbedding(Video video){
        if(availableServices[DataTypeEnum.Video.ordinal()])
            return embedder.getEmbedding(video);
        return null;
    }
    public ArrayList<Float> getEmbedding(GIF gif){
        if(availableServices[DataTypeEnum.GIF.ordinal()])
            return embedder.getEmbedding(gif);
        return null;
    }
}
