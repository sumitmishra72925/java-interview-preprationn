package topic.dsa.array_string;

import java.util.*;

public class AnagramAllSolutions {
    
    // BRUTE FORCE SOLUTION - O(n² × m log m)
    public static List<List<String>> groupAnagramsBruteForce(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        boolean[] used = new boolean[strs.length];
        
        System.out.println("=== BRUTE FORCE APPROACH ===");
        System.out.println("Input: " + Arrays.toString(strs));
        System.out.println();
        
        for (int i = 0; i < strs.length; i++) {
            if (used[i]) continue;  // Already grouped
            
            List<String> currentGroup = new ArrayList<>();
            currentGroup.add(strs[i]);
            used[i] = true;
            
            System.out.printf("Starting new group with '%s':%n", strs[i]);
            
            // Compare with all remaining strings
            for (int j = i + 1; j < strs.length; j++) {
                if (!used[j] && areAnagrams(strs[i], strs[j])) {
                    currentGroup.add(strs[j]);
                    used[j] = true;
                    System.out.printf("  Found anagram: '%s'%n", strs[j]);
                }
            }
            
            result.add(currentGroup);
            System.out.println("  Group: " + currentGroup);
            System.out.println();
        }
        
        return result;
    }
    
    // Helper method to check if two strings are anagrams
    private static boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        
        // Sort both strings and compare
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        
        boolean result = Arrays.equals(chars1, chars2);
        System.out.printf("    Checking '%s' vs '%s': %s%n", s1, s2, result);
        
        return result;
    }
    
    // OPTIMIZED SOLUTION 1 - Using Sorting as Key - O(n × m log m)
    public static List<List<String>> groupAnagramsSorting(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        
        System.out.println("=== OPTIMIZED: SORTING AS KEY ===");
        System.out.println("Input: " + Arrays.toString(strs));
        System.out.println();
        
        for (String str : strs) {
            // Use sorted string as key
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
            
            System.out.printf("'%s' → key '%s' → group %s%n", 
                            str, key, groups.get(key));
        }
        
        System.out.println("\nFinal groups: " + new ArrayList<>(groups.values()));
        return new ArrayList<>(groups.values());
    }
    
    // OPTIMIZED SOLUTION 2 - Using Character Frequency as Key - O(n × m)
    public static List<List<String>> groupAnagramsFrequency(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        
        System.out.println("\n=== OPTIMIZED: FREQUENCY AS KEY ===");
        System.out.println("Input: " + Arrays.toString(strs));
        System.out.println();
        
        for (String str : strs) {
            String key = getFrequencyKey(str);
            
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
            
            System.out.printf("'%s' → key '%s' → group %s%n", 
                            str, key, groups.get(key));
        }
        
        System.out.println("\nFinal groups: " + new ArrayList<>(groups.values()));
        return new ArrayList<>(groups.values());
    }
    
    // Create frequency-based key (for lowercase letters only)
    private static String getFrequencyKey(String str) {
        int[] count = new int[26];
        
        for (char c : str.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Convert count array to string key
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                key.append((char)('a' + i)).append(count[i]);
            }
        }
        
        return key.toString();
    }
    
    // COMPLEXITY ANALYSIS
    public static void analyzeComplexity() {
        System.out.println("\n=== COMPLEXITY ANALYSIS ===");
        
        System.out.println("Given: n strings, each of average length m");
        System.out.println();
        
        System.out.println("1. BRUTE FORCE:");
        System.out.println("   - Nested loops: O(n²)");
        System.out.println("   - Each anagram check: O(m log m) for sorting");
        System.out.println("   - Total: O(n² × m log m)");
        System.out.println("   - Space: O(1) extra (not counting output)");
        
        System.out.println("\n2. SORTING AS KEY:");
        System.out.println("   - One pass: O(n)");
        System.out.println("   - Sort each string: O(m log m)");
        System.out.println("   - Total: O(n × m log m)");
        System.out.println("   - Space: O(n × m) for HashMap");
        
        System.out.println("\n3. FREQUENCY AS KEY:");
        System.out.println("   - One pass: O(n)");
        System.out.println("   - Count frequency: O(m + 26) = O(m)");
        System.out.println("   - Total: O(n × m)");
        System.out.println("   - Space: O(n × m) for HashMap");
        
        System.out.println("\n📊 EXAMPLE with n=1000, m=10:");
        System.out.println("   Brute Force: 1000² × 10×log(10) ≈ 33 million ops");
        System.out.println("   Sorting Key: 1000 × 10×log(10) ≈ 33 thousand ops");
        System.out.println("   Frequency:   1000 × 10 = 10 thousand ops");
        System.out.println("   → Frequency approach is ~3300x faster than brute force!");
    }
    
    // PERFORMANCE DEMONSTRATION
    public static void performanceDemo() {
        System.out.println("\n=== PERFORMANCE DEMONSTRATION ===");
        
        String[] smallTest = {"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] largerTest = {"abc", "bca", "cab", "xyz", "zyx", "yxz", 
                              "hello", "olleh", "world", "dlrow", "test", "sett"};
        
        System.out.println("Small test case:");
        measureTime("Brute Force", () -> groupAnagramsBruteForce(smallTest.clone()));
        measureTime("Sorting Key", () -> groupAnagramsSorting(smallTest.clone()));
        measureTime("Frequency", () -> groupAnagramsFrequency(smallTest.clone()));
        
        System.out.println("\nLarger test case:");
        measureTime("Brute Force", () -> groupAnagramsBruteForce(largerTest.clone()));
        measureTime("Sorting Key", () -> groupAnagramsSorting(largerTest.clone()));
        measureTime("Frequency", () -> groupAnagramsFrequency(largerTest.clone()));
    }
    
    private static void measureTime(String method, Runnable task) {
        long start = System.nanoTime();
        task.run();
        long duration = System.nanoTime() - start;
        System.out.printf("%s: %,d nanoseconds%n", method, duration);
    }
    
    // WHEN TO USE EACH APPROACH
    public static void whenToUseEach() {
        System.out.println("\n=== WHEN TO USE EACH APPROACH ===");
        
        System.out.println("🐌 BRUTE FORCE:");
        System.out.println("   ✅ When: Very small input (n < 10)");
        System.out.println("   ✅ When: Memory is extremely limited");
        System.out.println("   ✅ When: Code simplicity is paramount");
        System.out.println("   ❌ Avoid: For any production use");
        
        System.out.println("\n🚀 SORTING AS KEY:");
        System.out.println("   ✅ When: General purpose solution");
        System.out.println("   ✅ When: Strings contain mixed characters");
        System.out.println("   ✅ When: Unicode support needed");
        System.out.println("   ✅ Most common interview answer");
        
        System.out.println("\n⚡ FREQUENCY AS KEY:");
        System.out.println("   ✅ When: Only lowercase letters (a-z)");
        System.out.println("   ✅ When: Maximum performance needed");
        System.out.println("   ✅ When: Processing large datasets");
        System.out.println("   ❌ Avoid: For mixed case or special characters");
        
        System.out.println("\n🎯 RECOMMENDATION:");
        System.out.println("   • Interview: Start with sorting approach");
        System.out.println("   • Production: Use frequency if possible, else sorting");
        System.out.println("   • Learning: Implement brute force first to understand problem");
    }
    
    public static void main(String[] args) {
        String[] testInput = {"eat", "tea", "tan", "ate", "nat", "bat"};
        
        groupAnagramsBruteForce(testInput.clone());
        groupAnagramsSorting(testInput.clone());
        groupAnagramsFrequency(testInput.clone());
        
        analyzeComplexity();
        performanceDemo();
        whenToUseEach();
    }
}

/*
KEY INSIGHTS:

1. BRUTE FORCE APPROACH:
   - Compare every string with every other string
   - For each pair, check if they're anagrams by sorting
   - Very inefficient but conceptually simple

2. THE OPTIMIZATION:
   - Instead of comparing pairs, use a "signature" as key
   - Anagrams will have the same signature
   - Group by signature instead of comparing

3. TWO TYPES OF SIGNATURES:
   - Sorted string: "eat" → "aet"
   - Frequency string: "eat" → "a1e1t1"

4. WHY OPTIMIZATION WORKS:
   - Reduces O(n²) comparisons to O(n) grouping
   - Each string processed once instead of compared n times
   - HashMap provides O(1) average lookup

5. INTERVIEW STRATEGY:
   - Start with brute force to show understanding
   - Identify the inefficiency (repeated comparisons)
   - Propose using signatures/keys to group
   - Implement sorting-based solution first
   - Mention frequency optimization if time allows

The key insight is transforming a comparison problem into a grouping problem!
*/