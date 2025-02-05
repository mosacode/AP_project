package DataBase;

import VendorTypes.FullSupportVendor;
import VendorTypes.VendorTypeClass;

import java.util.ArrayList;

public abstract class DataBaseClass {
    public boolean isConnected(){
        return false;
    }
    public void createTable(String table, int dimensions){

    }
    public boolean insert(VendorTypeClass data, String table){
        return false;
    }
    public ArrayList<Response> get(VendorTypeClass data, String table, int k){
        return new ArrayList<>();
    }
}
