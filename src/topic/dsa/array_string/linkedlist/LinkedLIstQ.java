package topic.dsa.array_string.linkedlist;

import com.sun.istack.internal.NotNull;
import jdk.nashorn.internal.ir.LiteralNode;

import java.util.*;

public class LinkedLIstQ {
    static ListNode<Integer> ll = new ListNode<>();

    static Random random = new Random();

    public static Integer[] generateRandomArray(Integer size, Integer minVal, Integer maxVal) {
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(maxVal - minVal + 1) + minVal;
        }
        return arr;
    }

    // Generate sorted array
    public static int[] generateSortedArray(int size, int start, int step) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = start + i * step;
        }
        return arr;
    }

    public static int[] generateArrayWithDuplicates(int size, int numUniqueValues) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(numUniqueValues) + 1;
        }
        return arr;
    }


    public static void main(String[] args) {

        Integer [] oddSizeArray = generateRandomArray(3, 1, 1);



        //System.out.println(Arrays.toString(oddSizeArray));

        ListNode<Integer> linkedList = ll.createLinkedList(oddSizeArray);
        ListNode<Integer> cyclicList = ll.createCyclicLL(oddSizeArray, 2);
        //ll.printLinkedList(linkedList);

       // printMiddleNode(linkedList);

        //System.out.println(isCyclicList(cyclicList));

       // System.out.println(kthFromLastBruteForce(linkedList, 2));
        //System.out.println(kThFromLastUsingF_STechnique(linkedList, 2));

        Integer [] customArray = {1,2,3,4,5,6};

        ListNode<Integer> x = ll.createLinkedList(customArray);
        ll.printLinkedList(x);
      //  ListNode<Integer> newHead = swapNodes(x);

        //ll.printLinkedList(newHead);

        System.out.println(maximumTwinSumOfLinkedList(x));
    }

    public static int maximumTwinSumOfLinkedList(ListNode<Integer> head){
        int ans = 0;

        ListNode<Integer> fast = head;
        ListNode<Integer> slow = head;

        int count = 0;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            count ++;
        }

        int n = count * 2;

        System.out.println("total size is: "+n);

        //need to revers the elements from slow to fast.



        ll.printLinkedList(head);

        slow = head;
        fast = head;

        for(int i = 0; i < n / 2; i++){
            fast = fast.next;
        }

        System.out.println("Fast is positioned at: "+fast.val);

        while (fast != null && fast.next != null){
            int sum = slow.val + fast.val;

            ans = Math.max(ans, sum);

            slow = slow.next;
            fast = fast.next;

        }

        return ans;
    }


    public static ListNode<Integer> swapNodes(ListNode<Integer> head){

        if(head == null || head.next == null){
            return head;
        }
        ListNode<Integer> dummy =  new ListNode<>(0);
        dummy.next = head;
        ListNode<Integer> prev = dummy;

        while(prev.next != null && prev.next.next != null){

            ListNode<Integer> first = prev.next;
            ListNode<Integer> second = prev.next.next;

            prev.next = second;
            first.next = second.next;
            second.next = first;

            prev = first;
        }


        return dummy.next;
    }

    public static ListNode<Integer> swapJustValues(ListNode<Integer> head){



        ListNode<Integer> cur = head;
        ListNode<Integer> prev = head;

        while(cur != null && cur.next != null){
            cur = cur.next;

            int temp = cur.val;
            cur.val = prev.val;
            prev.val = temp;

            cur = cur.next;
            prev = cur;
        }


        return head;
    }

    public static ListNode<Integer> reverse(ListNode<Integer> head){

        ListNode<Integer> cur = head;
        ListNode<Integer> prev = null;

        while(cur != null){
            ListNode<Integer> curNext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = curNext;
        }
        return prev;
    }




    public static ListNode<Integer> removeFirst(ListNode<Integer> head){
        if(head == null){
            throw new RuntimeException("The head is null!");
        }
        return head.next;
    }

    public static ListNode<Integer> removeLast(ListNode<Integer> head){

        if(head == null || head.next == null){
            return null;
        }

        ListNode<Integer> curr = head;
        while (curr.next.next != null){
            curr = curr.next;
        }
        curr.next = null;
        return head;
    }

    public static ListNode<Integer> removeAt(ListNode<Integer> head, int pos){

        if(head == null || pos < 0){
            return head;
        }

        if(pos == 0){
            return head.next;
        }

        ListNode<Integer> curr = head;

        for(int i = 1; i < pos - 1; i++){
            curr = curr.next;
        }


        if(curr == null || curr.next == null){
            throw new RuntimeException("Node doesn't exists!!");
        }

        System.out.println(curr.val);

        curr.next = curr.next.next;

        return head;
    }


    public static ListNode<Integer> insertAtLast(ListNode<Integer> head, Integer val){
        ListNode<Integer> newNode = new ListNode<>(val);
        ListNode<Integer> curr = head;

        while (curr.next != null){
            curr = curr.next;
        }

        curr.next = newNode;
        return head;
    }



    public static ListNode<Integer> insertAtPosition(ListNode<Integer> head, int pos, Integer val){
        ListNode<Integer> newNode = new ListNode<>(val);
        ListNode<Integer> curr = head;

        for(int i = 1; i < pos - 1; i++){
            curr = curr.next;
        }

        newNode.next = curr.next;
        curr.next = newNode;

        return head;

    }

    public static ListNode<Integer> insertAtFirst(ListNode<Integer> head, Integer val){

        ListNode<Integer> newNode = new ListNode<>(val);

        newNode.next = head;
        head = newNode;
        return head;
    }

    public static ListNode<Integer> removeDuplicatesFRomSortedLL(ListNode<Integer> head){

        ListNode<Integer> curr = head;

        while (curr != null){
            int val = curr.val;
            ListNode<Integer> curNext = curr.next;

            if(curNext != null && curNext.val == val){
                curr.next = curNext.next;
            }else {
                curr = curr.next;
            }
        }



        return head;
    }


    public static int  kThFromLastUsingF_STechnique(ListNode<Integer> head, int k ){

        if(head == null){
            return -1;
        }

        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head;

        for(int i = 0; i < k; i++){
            fast = fast.next;
        }

        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        return slow.val;

    }


    public static int kthFromLastBruteForce(ListNode<Integer> head , int k){

        ListNode<Integer> ref = head;
        int size = 0;

        while (ref != null){
            ref = ref.next;
            size++;
        }

        if(k > size || k < 0){
            return -1;
        }
        int kthEle = size - k;
        ref = head;
        for(int i =0; i < kthEle; i++){
            ref = ref.next;
        }

        return ref.val;
    }

    public static boolean isCyclicList(ListNode<Integer> head){

        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                return true;
            }
        }

        return false;
    }



    public static void printMiddleNode(ListNode<Integer> head){

        if(head == null) return;

        ListNode<Integer> fast = head;
        ListNode<Integer> slow = head;

        while (fast!= null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        System.out.println("Value using fast & slow: "+slow.val);

    }


    public static void printMiddleNodeIterative(ListNode<Integer> head){

        ListNode<Integer> ref = head;
        int size = 0;

        while (ref != null){
            size++;
            ref = ref.next;
        }

        int middle = size / 2;

        int i = 0;

        ListNode<Integer> middleNode = head;

        while(i < middle ){
           middleNode = middleNode.next;
           i++;
        }

        System.out.println("Value using middle/ 2: "+middleNode.val);

    }






}
