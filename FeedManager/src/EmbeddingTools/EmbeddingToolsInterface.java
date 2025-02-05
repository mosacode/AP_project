package EmbeddingTools;

import DataTypes.*;

import java.util.ArrayList;

public interface EmbeddingToolsInterface {
    public default ArrayList<Float> getEmbedding(String text){
        return null;
    }
    public default ArrayList<Float> getEmbedding(Image image){
        return null;
    }
    public default ArrayList<Float> getEmbedding(Audio audio){
        return null;
    }
    public default ArrayList<Float> getEmbedding(Video video){
        return null;
    }
    public default ArrayList<Float> getEmbedding(GIF gif){
        return null;
    }

    public default DataTypeEnum[] getDataTypes() {
        DataTypeEnum[] ret = new DataTypeEnum[1];
        ret[0] = DataTypeEnum.Null;
        return ret;
    }
}
