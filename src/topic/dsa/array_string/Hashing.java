package topic.dsa.array_string;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Hashing {

    /**
     * First Letter to Appear Twice </br>
     *</br>
     * Given a string s, return the first character to appear twice. </br>It is guaranteed that the input will have a duplicate character.</br></br>
     * Note:
     *</br>
     * A letter a appears twice before another letter b if the second occurrence of a is before the second occurrence of b.</br>
     * s will contain at least one letter that appears twice.</br>
     *</br>
     * Example 1:</br>
     *
     * Input: s = "abccbaacz"</br>
     * Output: "c"</br>
     * Explanation:</br>
     * The letter 'a' appears on the indexes 0, 5 and 6.</br>
     * The letter 'b' appears on the indexes 1 and 4.</br>
     * The letter 'c' appears on the indexes 2, 3 and 7.</br>
     * The letter 'z' appears on the index 8.</br>
     * The letter 'c' is the first letter to appear twice, because out of all the letters the index of its second occurrence is the smallest.</br></br>
     * Example 2:</br>
     *
     * Input: s = "abcdd"</br>
     * Output: "d"</br>
     * Explanation:</br>
     * The only letter that appears twice is 'd' so we return 'd'.</br></br>
     *
     *</br>
     * Constraints:</br>
     *
     * 2 <= s.length <= 100</br>
     * s consists of lowercase English letters.</br>
     * s has at least one repeated letter.</br></br>
     *
     * Solution: </br>
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * */
    public static char firstToAppearTwiceBruteForceMethod1(String s){

        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < i; j++){
                if(s.charAt(i) == s.charAt(j)){
                    return s.charAt(i);
                }
            }
        }

        return ' ';
    }

    /**Solution: </br> </br>
     * Time Complexity: O(26*n) = O(n) </br>
     * Space Complexity: O(1)
     * */
    public static char firstToAppearTwiceBruteForceMethod2(String s){
        char result = 'a';
        int secondOccurrence = Integer.MAX_VALUE;
        for(char c = 'a'; c <= 'z'; c++){

            int firstOccurrence = -1;
            for(int j = 0; j < s.length(); j++){
                if(c == s.charAt(j)){
                    if(firstOccurrence == -1){
                        firstOccurrence = j;
                    }else {
                        if(secondOccurrence > j){
                            secondOccurrence = j;
                            result = c;
                        }

                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Solution: </br>
     * Time Complexity: O(n) </br>
     * Space Complexity: O(1)
     * </br> </br>
     *
     * Many people will argue that the space complexity is O(1) </br>
     * because the input can only have characters from the English alphabet, which is bounded by a constant (26). </br> This is very common with string problems and technically correct.
     * </br>In an interview setting, this is probably a safe answer,
     * </br>but you should also note that the space complexity could be O(m), where
     * m is the number of allowable characters in the input. </br> This is a more general answer and also technically correct.

     * */
    public static char firstToAppearTwiceUsingSet(String s){
        Set<Character> seen = new HashSet<>();

        for(int i = 0; i < s.length(); i++){
            if(seen.contains(s.charAt(i))){
                return s.charAt(i);
            }
            seen.add(s.charAt(i));
        }


        return ' ';
    }

    /**
     *  Check if the Sentence Is Pangram </br></br>
     *  A pangram is a sentence where every letter of the English alphabet appears at least once.
     *</br>
     * Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise. </br></br>
     *
     * Solution: </br>
     *
     * Time Complexity: O(26 *n) = O (n) </br>
     * Space Complexity: O(1)

     * */
    public static boolean isPangramBruteForce(String s){

        for(char c = 'a'; c <= 'z'; c++){
            boolean isFound = false;
            for(int i =0; i < s.length(); i++){
                if(c == s.charAt(i)){
                    isFound = true;
                    break;
                }
            }

            if(!isFound){
                return false;
            }

        }
        return true;
    }



    public static boolean isPangramUsingHashMap(String s){
        Set<Character> alphabateMap = new HashSet<>();

        for(int i = 0; i < s.length(); i++){
            alphabateMap.add(s.charAt(i));
        }

        return alphabateMap.size() == 26;
    }


    // Fatest approach, since array is faster than hashing
    //Time complexity: O(n)
    //Space Complexity: O(26) = o(1)
    public static boolean isPangramUsingBooleanArray(String s){
        boolean [] seen = new boolean[26];

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            seen[c - 'a'] = true;
        }

        for (boolean b : seen) {
            if (!b) {
                return false;
            }
        }

        return true;
    }

    /**
     * Missing Number </br> </br>
     *
     * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.</br> </br>
     * Example 1:
     *</br>
     * Input: nums = [3,0,1] </br>
     *</br>
     * Output: 2
     *</br>
     * Explanation:</br>
     *</br>
     * n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.</br>
     *</br>
     * Example 2:</br>
     *</br>
     * Input: nums = [0,1]</br>
     *</br>
     * Output: 2</br>
     *</br>
     * Explanation</br>
     * n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums</br>
     * </br></br>
     * Constraints:</br>
     *</br>
     * n == nums.length</br>
     * 1 <= n <= 104</br>
     * 0 <= nums[i] <= n</br>
     * All the numbers of nums are unique.</br>
     *
     * </br></br>
     *
     * Solution: </br>
     * Time Complexity: O(n^2) </br>
     * Space Complexity: O(1);
     * */
    public static int missingNumberBruteForce(int [] nums){
        int n = nums.length;
        for(int i = 0; i < n + 1; i++){
            boolean isFound = false;
            for (int num : nums) {
                if (i == num) {
                    isFound = true;
                    break;
                }
            }
            if(!isFound){
                return  i;
            }
        }
        return -1;
    }

    /**
     * Missing Number using HashSet </br>
     * </br>
     * We can use a HashSet to store the numbers in the array and then check for the missing number.
     *</br>
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int missingNumberUsingHasSet(int [] nums){
        Set<Integer> numSet = new HashSet<>();

        for (int num : nums) {
            numSet.add(num);
        }

        for(int i = 0; i < nums.length + 1; i++){
            if(!numSet.contains(i)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Missing Number using Gauss Formula </br>
     * </br>
     * The sum of the first n natural numbers is given by the formula: n(n + 1) / 2.
     * </br>
     * We can use this formula to find the expected sum of numbers from 0 to n, and then subtract the actual sum of the numbers in the array to find the missing number.
     *</br>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int missingNumberUsingGaussFormula(int [] nums){
        int n = nums.length;
        int expectedSum = (n * (n + 1)) / 2;
        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }

    /**
     * Counting Elements </br>
     *
     * Given an integer array arr, count how many elements x there are, such that x + 1 is also in arr. </br> If there are duplicates in arr, count them separately.
     * Example 1:</br>
     * Input: arr = [1,2,3] </br>
     * Output: 2   </br>
     * Explanation: 1 and 2 are counted cause 2 and 3 are in arr. </br>
     *
     * Constraints:</br>
     * 1 <= arr.length <= 1000</br>
     * 0 <= arr[i] <= 1000  </br>
     *
     * Solution: </br>
     * Time Complexity: O(n^2) </br>
     * Space Complexity: O(1)
     * */
    public static int countElementBruteForce(int [] nums){

        int count = 0;
        for (int num : nums) {
            int numToLook = num + 1;
            for (int i : nums) {
                if (numToLook == i) {
                    count++;
                    break; // We can break here because we only need to count the first occurrence of numToLook
                }
            }
        }
        return count;
    }

    /**
     * Count Elements using HashSet </br>
     * </br>
     * This approach uses a HashSet to store the elements of the array and then checks for each element if its successor exists in the set.
     * *</br>
     * Time Complexity: O(n)
     *  Space Complexity: O(n)
     * */
    public static int countELementUsingHasSet(int [] arr){
        Set<Integer> successor = new HashSet<>();
        for (int j : arr) {
            successor.add(j);
        }

        int count = 0;
        for (int j : arr) {
            if (successor.contains(j + 1)) {
                count++;
            }
        }

        return count;


    }

    /**
     * Distinct Character in a window. </br> </br>
     * You are given a string s and an integer k.</br> Find the length of the longest substring that contains at most k distinct characters.</br>
     *
     * For example,  given </br>  s = "eceba" </br> k = 2, </br> return 3.  </br>The longest substring with at most 2 distinct characters is "ece".
     * */
    public static int distinctCharacterBruteForce(String s, int k){
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            String subString = s.substring(0, i + 1);
            if(subString.length() >= k){
                int distinct = findDistinct(subString);
                if(distinct == k){
                    res = Math.max(res,  i + 1);
                    System.out.println("longest substring is: "+subString);
                }
            }

        }
        return res;
    }

    private static int findDistinct(String substring) {
        System.out.println("Recieved substring: "+substring);
        int count = 0;
        for(int i = 0; i < substring.length(); i++){
            char c = substring.charAt(i);
            boolean isFound = false;
            for(int j = i + 1; j < substring.length(); j++){
                if(substring.charAt(j) == c){
                    isFound = true;
                    break;
                }
            }

            if(!isFound){
                count++;
            }
        }
        System.out.println("Distinct elements are: "+count);
       return count;
    }


    /**
     * Distinct Character Sliding Window </br>
     * </br>
     * The below method make use of sliding window and HashMap to find the length of the longest substring that contains at most k distinct characters. </br>
     *
     * Solution: </br>
     * Time Complexity: O(n) </br>
     * Space Complexity: O(k) </br>
     */

    public static int distinctCharacterSlidingWindow(String s, int k){

        int ans = 0;
        int left = 0;
        Map<Character, Integer> windowFreqMap = new HashMap<>();

        for(int right =0; right < s.length(); right++){

           windowFreqMap.put(s.charAt(right), windowFreqMap.getOrDefault(s.charAt(right), 0) + 1);

           while (windowFreqMap.size() > k){
               char c = s.charAt(left);
               windowFreqMap.put(c, windowFreqMap.getOrDefault(c, 0) - 1);
               if(windowFreqMap.get(c) == 0){
                   windowFreqMap.remove(c);
               }
               left++;
           }

           ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }

    public static int distinctCharacterSlidingWindowWithoutExtraSpace(String s, int k) {
        if (s == null || s.isEmpty() || k == 0) return 0;

        // Fixed space - doesn't grow with input size
        boolean[] present = new boolean[256]; // Track which characters are in window
        int[] frequency = new int[256];       // Track frequency of each character
        int distinctCount = 0;                // Current number of distinct characters

        int left = 0;
        int maxLength = 0;

        // Expand window with right pointer
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);

            // Add character to window - O(1) operation
            if (!present[rightChar]) {
                present[rightChar] = true;
                distinctCount++;           // New distinct character found
            }
            frequency[rightChar]++;

            // Contract window if we exceed k distinct characters
            while (distinctCount > k) {
                char leftChar = s.charAt(left);
                frequency[leftChar]--;

                // Remove character completely if frequency reaches zero
                if (frequency[leftChar] == 0) {
                    present[leftChar] = false;
                    distinctCount--;       // One less distinct character
                }
                left++;
            }

            // Update maximum length when window is valid
            maxLength = Math.max(maxLength, right - left + 1);
        }

        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        set.remove(1);

        return maxLength;
    }



    /**
     * Find Players With Zero or One Losses </br> </br>
     *
     * You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match. </br>
     *
     * Return a list answer of size 2 where: </br> </br>
     *
     * answer[0] is a list of all players that have not lost any matches.</br>
     * answer[1] is a list of all players that have lost exactly one match. </br>
     * The values in the two lists should be returned in increasing order. </br>
     *</br></br></br>
     * Note:</br></br>
     *
     * You should only consider the players that have played at least one match.</br>
     * The testcases will be generated such that no two matches will have the same outcome.</br>
     * </br></br>
     *
     * Solution: </br>
     * Time Complexity: O(n log n): Due to sorting </br>
     * Space Complexity: O(n) Due to hashmap </br>
     *
     * */
    public static void findWinners(int [] [] matches){

        Map<Integer, Integer> winnerMap = new HashMap<>();
        Map<Integer, Integer> looserMap = new HashMap<>();

        for (int[] match : matches) {

            int winner = match[0];
            int looser = match[1];

            winnerMap.put(winner, winnerMap.getOrDefault(winner, 0) + 1);
            looserMap.put(looser, looserMap.getOrDefault(looser, 0) + 1);
        }

        ArrayList<Integer> winnerList = new ArrayList<>();
        ArrayList<Integer> looserList = new ArrayList<>();

        for(Integer i : winnerMap.keySet()){
            if(!looserMap.containsKey(i)){
                winnerList.add(i);
            }
        }

        for(Integer i : looserMap.keySet()){
            if(looserMap.get(i) == 1){
                looserList.add(i);
            }
        }

        Collections.sort(winnerList);
        Collections.sort(looserList);

        System.out.println(winnerList);
        System.out.println(looserList);


    }





    public static void main(String[] args) {
        //String s = "abxcblimza";
        //System.out.println(firstToAppearTwiceUsingSet(s));

        //System.out.println(countELementUsingHasSet(new int[]{1,1,3,3,5,5,7,7}));

        int [] [] matches  = {
                {1, 3},
                {2, 3},
                {3, 6},
                {5, 6},
                {5, 7},
                {4, 5},
                {4, 8},
                {4, 9},
                {10,4},
                {10, 9}

        };

        //System.out.println(distinctCharacterBruteForce("abcaacscdtusr", 3));

        findWinners(matches);
    }


}
