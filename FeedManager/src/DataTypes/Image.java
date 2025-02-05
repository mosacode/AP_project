package DataTypes;

public class Image {
    private byte[] imageData;
    public Image(){
    }
    public Image(byte[] imageData){
        set(imageData);
    }
    public byte[] getImageData(){
        return imageData;
    }
    public void set(byte[] imageData){
        this.imageData = imageData;
    }
}
