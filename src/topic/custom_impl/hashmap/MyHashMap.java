package topic.custom_impl.hashmap;

import java.util.ArrayList;
import java.util.LinkedList;

public class MyHashMap<K, V> {

    private class Node{
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private int CAPACITY;
    private int TOTAL_ELEMENTS;
    private LinkedList<Node> [] bucket;

    public MyHashMap() {
        this(4);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int CAPACITY) {
        this.CAPACITY = CAPACITY;

        TOTAL_ELEMENTS = 0;

        bucket = new LinkedList[CAPACITY];

        for(int i = 0; i < CAPACITY; i++){
            bucket[i] = new LinkedList<>();
        }

        System.out.println("The c-HashMap has been initialized with initial capacity with: "+ CAPACITY);
    }



    public void put(K key, V value){
        int bucketIndex_BI = generateHash(key);
        int dataIndex_DI = searchLL(bucketIndex_BI, key);

        if(dataIndex_DI == -1){
            Node node = new Node(key, value);
            bucket[bucketIndex_BI].add(node);
            TOTAL_ELEMENTS++;
        }else {
           bucket[bucketIndex_BI].get(dataIndex_DI).value = value;
        }

        double lambda = (double) TOTAL_ELEMENTS / CAPACITY;
        if(lambda > 2){
            //System.out.println("Threshold reached...Creating new array(Re-hashing..starting)");
            reHash();
        }

    }

    public boolean containsKey(K key){
        int bucketIndex_BI = generateHash(key);
        int dataIndex_DI = searchLL(bucketIndex_BI, key);
        return dataIndex_DI != -1;
    }

    public V remove(K key){
        int bucketIndex_BI = generateHash(key);
        int dataIndex_DI = searchLL(bucketIndex_BI, key);

        if(dataIndex_DI == -1){
            return null;
        }else {
           Node node =  bucket[bucketIndex_BI].remove(dataIndex_DI);
           TOTAL_ELEMENTS--;
           return node.value;
        }
    }

    public int size(){
        return TOTAL_ELEMENTS;
    }


    public ArrayList<K> keySet(){

        ArrayList<K> keySet = new ArrayList<>();

        for(LinkedList<Node> nodes : bucket){
            for(Node node : nodes){
                keySet.add(node.key);
            }
        }
        return keySet;
    }


    @SuppressWarnings("unchecked")
    private void reHash() {
        LinkedList<Node> [] oldBucket = bucket;

        CAPACITY *= 2;
        TOTAL_ELEMENTS = 0;

        bucket = new LinkedList[CAPACITY];

        for(int i = 0; i < CAPACITY; i++){
            bucket[i] = new LinkedList<>();
        }

        for (LinkedList<Node> ll : oldBucket) {
            for (Node node : ll) {
                int bi = generateHash(node.key);
                bucket[bi].add(new Node(node.key, node.value));
                TOTAL_ELEMENTS++;
            }

        }

    }

    private int searchLL(int bucketIndexBi, K key) {
        LinkedList<Node> bucketNode = bucket[bucketIndexBi];
        for (int i = 0; i < bucketNode.size(); i++){
            Node currentNode = bucketNode.get(i);
            if((key == null && currentNode.key == null) || (key != null && key.equals(currentNode.key)) ){
                return i;
            }
        }
        return -1;
    }

    private int generateHash(K key) {
        if(key == null)
            return 0;
        int has =  key.hashCode();
        return Math.abs(has) % CAPACITY;
    }

    public V get(K key){
        int bucketIndex_BI = generateHash(key);
        int dataIndex_DI = searchLL(bucketIndex_BI, key);

        if(dataIndex_DI == -1){
            return null;
        }else {
             return bucket[bucketIndex_BI].get(dataIndex_DI).value;
        }
    }


}
