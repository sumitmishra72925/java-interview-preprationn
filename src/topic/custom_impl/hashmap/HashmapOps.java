package topic.custom_impl.hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashmapOps {
    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put(null, "Null Value");
        hashMap.put("a", "A val");
        hashMap.put("b", "B val");
        hashMap.put(null, null);

        // There are 2 way's to iterate hashmap:
        //1. Using entry set

        System.out.println("==== Using Entry Set ====");
        for(Map.Entry<String, String> ele : hashMap.entrySet()){
            System.out.println("Key: "+ele.getKey()+", Value: "+ele.getValue());
        }

        System.out.println("==== Using Key Set ====");
        //Using Key set
        for(String key : hashMap.keySet()){
            System.out.println("Key: "+key+", Value: "+hashMap.get(key));
        }

        System.out.println(hashMap.remove("qw"));

        HashMap<Boolean, Boolean> booleanHashMap = new HashMap<>();

        booleanHashMap.put(true, false);
        booleanHashMap.put(false, true);

        System.out.println(booleanHashMap);



    }
}
