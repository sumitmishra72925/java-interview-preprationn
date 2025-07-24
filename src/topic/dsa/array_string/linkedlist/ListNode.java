package topic.dsa.array_string.linkedlist;

import sun.awt.image.ImageWatched;

public class ListNode<V> {
    public V val;
    public ListNode<V> next;

    public ListNode(V val, ListNode<V> next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(V val) {
        this.val = val;
    }

    public ListNode() {
    }

    public void printLinkedList(ListNode<V> head){
        while (head != null){
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public ListNode<V> createLinkedList(V [] arr){
        ListNode<V> head = new ListNode<>(arr[0]);

        ListNode<V> ref = head;

        for(int i = 1; i < arr.length; i++ ){
            ListNode<V> newNode = new ListNode<>(arr[i]);
            ref.next = newNode;
            ref = newNode;
        }
        ref.next = null;
        return head;
    }

    public ListNode<V> createCyclicLL(V [] arr, int pos){
        ListNode<V> cycleNode = null;
        ListNode<V> head = new ListNode<>(arr[0]);
        if(pos == 0){
            cycleNode = head;
        }

        ListNode<V> ref = head;

        for(int i = 1; i < arr.length; i++){
            ListNode<V> newNode = new ListNode<>(arr[i]);
            ref.next = newNode;
            ref = newNode;
            if(i == pos){
                cycleNode = newNode;
            }
        }

        ref.next = cycleNode;
        return head;
    }

}
