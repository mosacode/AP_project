package VendorTypes;

import DataTypes.*;

import java.net.URL;
import java.util.ArrayList;

public interface VendorTypeInterface {
    public default ArrayList<Float> getEmbedding(){
        return new ArrayList<Float>(256);
    }
    public default DataTypeEnum[] getDataTypes(){
        DataTypeEnum[] ret = new DataTypeEnum[1];
        ret[0] = DataTypeEnum.Null;
        return ret;
    }
    public default String describe(){
        return "";
    }
    public default String getUnifier(){
        return "Interface>not implemented.";
    }
    public default String getText(){
        return null;
    }
    public default Image getImage(){
        return null;
    }
    public default Video getVideo(){
        return null;
    }
    public default Audio getAudio(){
        return null;
    }
    public default GIF getGIF(){
        return null;
    }
    public default URL getURL(){
        return null;
    }
    public default ArrayList<Float> getTextEmbedding(){
        return null;
    }
    public default ArrayList<Float> getImageEmbedding(){
        return null;
    }
    public default ArrayList<Float> getVideoEmbedding(){
        return null;
    }
    public default ArrayList<Float> getAudioEmbedding(){
        return null;
    }
    public default ArrayList<Float> getGIFEmbedding(){
        return null;
    }
    public default ArrayList<Float> getGeneralEmbedding(boolean explicit){
        return null;
    }
}
