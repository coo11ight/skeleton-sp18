package hw3.hash;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;



public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        HashMap<Integer,List<Oomage>> hash = new HashMap<>();
        List<Oomage> list = new ArrayList<Oomage>();
        int bucketNumber;
        for (Oomage o : oomages){
            bucketNumber = (o.hashCode() & 0x7FFFFFFF) % M;
            if (hash.containsKey(bucketNumber)){
                list = hash.get(bucketNumber);
                list.add(o);
            }
            else{
                list = new ArrayList<Oomage>();
                list.add(o);
                hash.put(bucketNumber, list);
            }
        }

        double lowerLoadBound = oomages.size() / 50.0;
        double upperLoadBound = oomages.size() / 2.5;

        int load;
        for (int i = 0;i < M; i++){
            if (hash.containsKey(i)){
                load = hash.get(i).size();
                if ( lowerLoadBound <=load && load <= upperLoadBound) {
                    continue;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }

        }

        return true;



        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */


    }
}
