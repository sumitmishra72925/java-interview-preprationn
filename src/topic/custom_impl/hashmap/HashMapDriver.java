package topic.custom_impl.hashmap;

import java.util.ArrayList;

public class HashMapDriver {
    public static void main(String[] args) {
        HashMapWithRehashing<String, String> myHashMap = new HashMapWithRehashing<>(3);
        myHashMap.put("Ping", "Pong");

        System.out.println(myHashMap.get("Ping"));
        System.out.println(myHashMap.getSize());
        System.out.println(myHashMap.remove("Ping"));
        System.out.println(myHashMap.getSize());


        myHashMap.put("Eva", "1.5 yrs");
        myHashMap.put("Diva", "1 yrs");
        myHashMap.put("UNKNOWN", "0.6 mnths");
        myHashMap.put("Khushi", "5 Yrs");
        myHashMap.put("Sumit", "30 yrs");
        myHashMap.put("Anjali", "25 yrs");
        myHashMap.put("X", "y");


        System.out.println(myHashMap.containsKey("Eva"));

        System.out.println("Final size: "+myHashMap.getSize());
        System.out.println(myHashMap.remove("X"));
        System.out.println(myHashMap.getSize());

        myHashMap.put(null, "a");
    }
}
