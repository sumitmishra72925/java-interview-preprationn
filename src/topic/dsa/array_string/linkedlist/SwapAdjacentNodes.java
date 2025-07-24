package topic.dsa.array_string.linkedlist;

public class SwapAdjacentNodes {
    
    public static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
    
    public static ListNode swapPairs(ListNode head) {
        // Create a dummy node to handle edge cases easily
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        // Continue while we have at least 2 nodes to swap
        while (prev.next != null && prev.next.next != null) {
            // Identify the two nodes to swap
            ListNode first = prev.next;
            ListNode second = prev.next.next;
            
            // Perform the swap
            prev.next = second;        // Connect previous to second
            first.next = second.next;  // Connect first to what comes after second
            second.next = first;       // Connect second to first
            
            // Move prev to the end of swapped pair for next iteration
            prev = first;
        }
        
        return dummy.next;
    }
    
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" -> ");
            curr = curr.next;
        }
        System.out.println(" -> null");
    }
    
    public static void main(String[] args) {
        System.out.println("=== EXAMPLE 1: [1, 2, 3, 4] ===");
        
        // Create list: 1 -> 2 -> 3 -> 4
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        
        System.out.print("Before: ");
        printList(head1);
        
        head1 = swapPairs(head1);
        
        System.out.print("After:  ");
        printList(head1);
        
        System.out.println("\n=== EXAMPLE 2: [1, 2, 3] ===");
        
        // Create list: 1 -> 2 -> 3
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        
        System.out.print("Before: ");
        printList(head2);
        
        head2 = swapPairs(head2);
        
        System.out.print("After:  ");
        printList(head2);
        
        System.out.println("\n=== STEP BY STEP TRACE ===");
        traceAlgorithm();
    }
    
    public static void traceAlgorithm() {
        System.out.println("Let's trace through [1, 2, 3, 4] step by step:\n");
        
        System.out.println("Initial state:");
        System.out.println("dummy -> 1 -> 2 -> 3 -> 4 -> null");
        System.out.println("prev = dummy");
        System.out.println();
        
        System.out.println("ITERATION 1:");
        System.out.println("prev.next = 1, prev.next.next = 2 (both exist, so continue)");
        System.out.println("first = 1, second = 2");
        System.out.println();
        
        System.out.println("Step 1: prev.next = second");
        System.out.println("dummy -> 2    1 -> 2 -> 3 -> 4 -> null");
        System.out.println("         ^");
        System.out.println();
        
        System.out.println("Step 2: first.next = second.next");
        System.out.println("dummy -> 2    1 -> 3 -> 4 -> null");
        System.out.println("              ^    2 -> 3 -> 4 -> null");
        System.out.println();
        
        System.out.println("Step 3: second.next = first");
        System.out.println("dummy -> 2 -> 1 -> 3 -> 4 -> null");
        System.out.println();
        
        System.out.println("Move prev to first (which is now 1):");
        System.out.println("dummy -> 2 -> 1 -> 3 -> 4 -> null");
        System.out.println("              ^");
        System.out.println("              prev");
        System.out.println();
        
        System.out.println("ITERATION 2:");
        System.out.println("prev.next = 3, prev.next.next = 4 (both exist, so continue)");
        System.out.println("first = 3, second = 4");
        System.out.println();
        
        System.out.println("Step 1: prev.next = second");
        System.out.println("dummy -> 2 -> 1 -> 4    3 -> 4 -> null");
        System.out.println("                   ^");
        System.out.println();
        
        System.out.println("Step 2: first.next = second.next");
        System.out.println("dummy -> 2 -> 1 -> 4    3 -> null");
        System.out.println("                        ^    4 -> null");
        System.out.println();
        
        System.out.println("Step 3: second.next = first");
        System.out.println("dummy -> 2 -> 1 -> 4 -> 3 -> null");
        System.out.println();
        
        System.out.println("Move prev to first (which is now 3):");
        System.out.println("dummy -> 2 -> 1 -> 4 -> 3 -> null");
        System.out.println("                         ^");
        System.out.println("                         prev");
        System.out.println();
        
        System.out.println("ITERATION 3:");
        System.out.println("prev.next = null, so loop ends");
        System.out.println();
        
        System.out.println("FINAL RESULT:");
        System.out.println("2 -> 1 -> 4 -> 3 -> null");
    }
}