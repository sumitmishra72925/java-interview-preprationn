package topic.dsa.array_string;

import java.util.*;

public class CountingSort_Applications {
    
    // BASIC COUNTING SORT TEMPLATE for integers
    public static int[] countingSort(int[] arr, int maxVal) {
        int[] count = new int[maxVal + 1];
        int[] result = new int[arr.length];
        
        // Count frequencies
        for (int num : arr) {
            count[num]++;
        }
        
        // Build result array
        int index = 0;
        for (int i = 0; i <= maxVal; i++) {
            for (int j = 0; j < count[i]; j++) {
                result[index++] = i;
            }
        }
        
        return result;
    }
    
    // APPLICATION 1: SORTING GRADES (0-100)
    public static void sortGrades() {
        System.out.println("=== APPLICATION 1: SORTING GRADES ===");
        int[] grades = {85, 92, 78, 95, 88, 92, 76, 85, 90, 78};
        
        System.out.println("Original grades: " + Arrays.toString(grades));
        
        int[] sortedGrades = countingSort(grades, 100);
        System.out.println("Sorted grades: " + Arrays.toString(sortedGrades));
        
        // Also show frequency distribution
        int[] count = new int[101];
        for (int grade : grades) {
            count[grade]++;
        }
        
        System.out.println("Grade distribution:");
        for (int i = 0; i <= 100; i++) {
            if (count[i] > 0) {
                System.out.printf("Grade %d: %d students%n", i, count[i]);
            }
        }
        System.out.println("Time: O(n + k) where n=" + grades.length + ", k=100");
        System.out.println();
    }
    
    // APPLICATION 2: SORTING AGES
    public static void sortAges() {
        System.out.println("=== APPLICATION 2: SORTING AGES ===");
        int[] ages = {25, 30, 22, 35, 28, 30, 22, 40, 25, 33};
        
        System.out.println("Original ages: " + Arrays.toString(ages));
        
        int[] sortedAges = countingSort(ages, 120); // Assuming max age 120
        System.out.println("Sorted ages: " + Arrays.toString(sortedAges));
        
        System.out.println("Perfect for HR systems, demographics analysis!");
        System.out.println();
    }
    
    // APPLICATION 3: SORTING DICE ROLLS
    public static void sortDiceRolls() {
        System.out.println("=== APPLICATION 3: DICE ROLL ANALYSIS ===");
        int[] diceRolls = {3, 6, 1, 4, 6, 2, 5, 3, 1, 6, 4, 2, 3, 5};
        
        System.out.println("Dice rolls: " + Arrays.toString(diceRolls));
        
        // Count frequency of each outcome
        int[] frequency = new int[7]; // 1-6 (index 0 unused)
        for (int roll : diceRolls) {
            frequency[roll]++;
        }
        
        System.out.println("Frequency analysis:");
        for (int i = 1; i <= 6; i++) {
            System.out.printf("Face %d: %d times (%.1f%%)%n", 
                            i, frequency[i], (frequency[i] * 100.0) / diceRolls.length);
        }
        
        int[] sorted = countingSort(diceRolls, 6);
        System.out.println("Sorted: " + Arrays.toString(sorted));
        System.out.println();
    }
    
    // APPLICATION 4: PRIORITY LEVELS (0-5)
    public static void sortPriorities() {
        System.out.println("=== APPLICATION 4: TASK PRIORITY SORTING ===");
        
        // Simulating task priorities: 0=Low, 1=Normal, 2=High, 3=Urgent, 4=Critical
        int[] priorities = {2, 0, 4, 1, 3, 2, 4, 0, 1, 3, 2};
        String[] priorityNames = {"Low", "Normal", "High", "Urgent", "Critical"};
        
        System.out.println("Original priorities: " + Arrays.toString(priorities));
        
        int[] sortedPriorities = countingSort(priorities, 4);
        System.out.println("Sorted priorities: " + Arrays.toString(sortedPriorities));
        
        // Show distribution
        int[] count = new int[5];
        for (int p : priorities) {
            count[p]++;
        }
        
        System.out.println("Priority distribution:");
        for (int i = 0; i < 5; i++) {
            if (count[i] > 0) {
                System.out.printf("%s: %d tasks%n", priorityNames[i], count[i]);
            }
        }
        System.out.println("Great for task management systems!");
        System.out.println();
    }
    
    // APPLICATION 5: RADIX SORT FOUNDATION
    public static void radixSortExample() {
        System.out.println("=== APPLICATION 5: RADIX SORT (USING COUNTING SORT) ===");
        
        int[] numbers = {329, 457, 657, 839, 436, 720, 355};
        System.out.println("Original: " + Arrays.toString(numbers));
        
        // Radix sort uses counting sort for each digit
        System.out.println("Radix sort sorts digit by digit using counting sort:");
        System.out.println("1. Sort by ones digit (0-9)");
        System.out.println("2. Sort by tens digit (0-9)");  
        System.out.println("3. Sort by hundreds digit (0-9)");
        System.out.println("Each step uses counting sort with range 0-9!");
        
        // Simple radix sort implementation
        int[] sorted = radixSort(numbers);
        System.out.println("Final result: " + Arrays.toString(sorted));
        System.out.println();
    }
    
    // Simple radix sort using counting sort
    private static int[] radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(0);
        int[] result = arr.clone();
        
        for (int exp = 1; max / exp > 0; exp *= 10) {
            result = countingSortByDigit(result, exp);
        }
        
        return result;
    }
    
    private static int[] countingSortByDigit(int[] arr, int exp) {
        int[] count = new int[10]; // Digits 0-9
        int[] result = new int[arr.length];
        
        // Count occurrences of each digit
        for (int num : arr) {
            count[(num / exp) % 10]++;
        }
        
        // Convert to actual positions
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        
        // Build result array
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            result[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        
        return result;
    }
    
    // APPLICATION 6: VOTING SYSTEM
    public static void votingSystem() {
        System.out.println("=== APPLICATION 6: VOTING SYSTEM ===");
        
        // Candidate IDs: 1, 2, 3, 4
        int[] votes = {2, 1, 3, 2, 4, 1, 2, 3, 1, 4, 2, 1, 3, 2};
        String[] candidates = {"", "Alice", "Bob", "Charlie", "Diana"};
        
        System.out.println("Votes: " + Arrays.toString(votes));
        
        // Count votes for each candidate
        int[] voteCount = new int[5]; // Index 0 unused
        for (int vote : votes) {
            voteCount[vote]++;
        }
        
        System.out.println("Election Results:");
        for (int i = 1; i < candidates.length; i++) {
            System.out.printf("%s (ID %d): %d votes%n", candidates[i], i, voteCount[i]);
        }
        
        // Find winner
        int maxVotes = 0;
        int winner = 0;
        for (int i = 1; i < voteCount.length; i++) {
            if (voteCount[i] > maxVotes) {
                maxVotes = voteCount[i];
                winner = i;
            }
        }
        
        System.out.printf("Winner: %s with %d votes!%n", candidates[winner], maxVotes);
        System.out.println();
    }
    
    // WHEN TO USE COUNTING SORT
    public static void whenToUse() {
        System.out.println("=== WHEN TO USE COUNTING SORT ===");
        
        System.out.println("✅ PERFECT FOR:");
        System.out.println("• Small, known range of values (0-100, 0-255, etc.)");
        System.out.println("• Discrete integer data");
        System.out.println("• Frequency analysis needed");
        System.out.println("• Stable sorting required");
        System.out.println("• Maximum performance needed for specific range");
        
        System.out.println("\n❌ NOT SUITABLE FOR:");
        System.out.println("• Large ranges (e.g., 32-bit integers: 0 to 2^31)");
        System.out.println("• Floating-point numbers");
        System.out.println("• Unknown or unbounded ranges");
        System.out.println("• Sparse data (few values in large range)");
        
        System.out.println("\n📊 COMPLEXITY:");
        System.out.println("Time: O(n + k) where n=elements, k=range");
        System.out.println("Space: O(k) for counting array");
        System.out.println("Stable: Yes (maintains relative order)");
        
        System.out.println("\n🎯 COMMON RANGES:");
        System.out.println("• ASCII characters: k=128");
        System.out.println("• Grades/percentages: k=101 (0-100)");
        System.out.println("• Ages: k=120 (0-120)");
        System.out.println("• Priority levels: k=5-10");
        System.out.println("• Color values (RGB): k=256 per channel");
        System.out.println("• Dice/game outcomes: k=6-20");
    }
    
    public static void main(String[] args) {
        sortGrades();
        sortAges();
        sortDiceRolls();
        sortPriorities();
        radixSortExample();
        votingSystem();
        whenToUse();
    }
}

/*
KEY TAKEAWAYS:

1. COUNTING SORT IS NOT JUST FOR CHARACTERS!
   - Works for any discrete integer data with small range
   - Character sorting is just one application

2. REAL-WORLD APPLICATIONS:
   - Grading systems (0-100)
   - Age sorting (0-120)
   - Priority queues (1-5)
   - Voting systems
   - Game statistics
   - Color processing (0-255 RGB values)
   - Frequency analysis

3. PERFORMANCE ADVANTAGE:
   - O(n + k) vs O(n log n) for comparison-based sorts
   - When k << n log n, counting sort is much faster
   - Example: Sorting 1M grades (k=100) vs Arrays.sort()

4. FOUNDATION FOR OTHER ALGORITHMS:
   - Radix sort uses counting sort internally
   - Bucket sort can use counting sort for buckets
   - Stable sorting property is crucial

5. INDUSTRY USAGE:
   - Database indexing (for small domains)
   - Graphics processing (pixel values)
   - Network packet classification
   - Bioinformatics (DNA bases: A,T,G,C = k=4)
   - Financial systems (rating scales)

Remember: Counting sort shines when you have a small, known range!
*/