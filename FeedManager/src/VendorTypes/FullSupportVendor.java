package VendorTypes;

import DataTypes.Image;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FullSupportVendor extends VendorTypeClass{
    public FullSupportVendor(){
        super();
    }
    public String getText(URL url){
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
    public Image getImageLocal(){
        byte[] localData;
        try{
            localData = Files.readAllBytes(Paths.get("src/1610833547635842.jpg"));
            if(localData.length == 0){
                throw new Exception("image was not loaded!");
            }
        }catch (Exception e){
            System.err.printf(e+"\n");
            return null;
        }
        return new Image(localData);
    }
    public Image getImageURL(URL url){
        return null;
    }
}
