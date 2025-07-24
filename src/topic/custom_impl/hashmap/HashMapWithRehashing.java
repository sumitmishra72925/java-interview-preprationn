package topic.custom_impl.hashmap;

class HashMapWithRehashing<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Node<K, V>[] buckets;
    private int capacity = 16;
    private int size = 0;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    public int getSize() {
        return size;
    }

    public HashMapWithRehashing() {
     this(16);
    }

    @SuppressWarnings("unchecked")
    public HashMapWithRehashing(int capacity){
        this.capacity = capacity;
        buckets = new Node[capacity];
    }

    public boolean containsKey(K key) {
        int bucketIndex = hash(key);
        Node<K, V> head = buckets[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {  // ✅ Use .equals() for content equality
                return true;
            }
            head = head.next;
        }

        return false;
    }

    public V remove(K key) {
        int bucketIndex = hash(key);
        Node<K, V> head = buckets[bucketIndex];

        // Special case: removing the first node
        if (head != null && head.key.equals(key)) {
            buckets[bucketIndex] = head.next;  // Update bucket to point to next node
            size--;
            return head.value;
        }

        // General case: removing from middle or end
        Node<K, V> current = head;
        while (current != null && current.next != null) {
            if (current.next.key.equals(key)) {
                Node<K, V> nodeToRemove = current.next;
                current.next = nodeToRemove.next;
                size--;
                return nodeToRemove.value;
            }
            current = current.next;
        }

        return null;  // Key not found
    }
    
    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }
    
    private double getLoadFactor() {
        return (double) size / capacity;
    }
    
    public void put(K key, V value) {
        // Check if rehashing is needed BEFORE insertion
        if (getLoadFactor() >= LOAD_FACTOR_THRESHOLD) {
            rehash();
        }
        
        int index = hash(key);
        Node<K, V> head = buckets[index];
        
        // Check if key already exists
        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value; // Update existing
                return;
            }
            current = current.next;
        }
        
        // Add new node
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        buckets[index] = newNode;
        size++;
    }
    
    private void rehash() {
        System.out.println("Rehashing... Old capacity: " + capacity + ", Load factor: " + getLoadFactor());
        
        // Store old buckets
        Node<K, V>[] oldBuckets = buckets;
        int oldCapacity = capacity;
        
        // Double the capacity
        capacity *= 2;
        size = 0; // Reset size, will be recalculated during rehashing
        buckets = new Node[capacity];
        
        // Rehash all existing entries
        for (int i = 0; i < oldCapacity; i++) {
            Node<K, V> current = oldBuckets[i];
            while (current != null) {
                Node<K, V> next = current.next; // Store next before losing reference
                
                // Rehash current node
                int newIndex = hash(current.key);
                current.next = buckets[newIndex];
                buckets[newIndex] = current;
                size++;
                
                current = next;
            }
        }
        
        System.out.println("Rehashing complete. New capacity: " + capacity + ", New load factor: " + getLoadFactor());
    }
    
    public V get(K key) {
        int index = hash(key);
        Node<K, V> current = buckets[index];
        
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
    
    public void printStats() {
        System.out.println("Size: " + size + ", Capacity: " + capacity + ", Load Factor: " + getLoadFactor());
    }
}