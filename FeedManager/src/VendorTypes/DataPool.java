package VendorTypes;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DataPool {

    private static final DataPool dataPoolInstance = new DataPool();
    HashMap<Integer, VendorTypeClass> pool;
    private final AtomicInteger idCounter;

    private DataPool(){
        idCounter = new AtomicInteger(0);
        pool = new HashMap<>();
    }

    public static DataPool getInstance(){
        return dataPoolInstance;
    }

    /// Attention! unsafe function addInstance(VendorTypeClass data): if the id is redundant might trigger undefined behaviour.
    public void addInstance(int id, VendorTypeClass data){
        if(pool.containsKey(id))
            System.err.printf("ERROR: redundant id while adding instance-%d to pool!\n", id);
        pool.put(id, data);
    }

    public void addInstanceRawTextOrg(String urlAddress){
        // constructor might be slow (internet, heavy file, etc)
        // so we implement it in a way that we would be able to multi-thread this part if needed
        int id = idCounter.addAndGet(1);
        addInstance(id, new RawTextOrg(urlAddress));
    }

    public VendorTypeClass get(int id){
        return pool.get(id);
    }

    public void clearPool(){
        idCounter.set(0);
        pool.clear();
    }

}
