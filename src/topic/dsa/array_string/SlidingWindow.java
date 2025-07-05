package topic.dsa.array_string;

public class SlidingWindow {

    /** Minimum Size Subarray Sum </br> </br>
     * Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is <b>greater than or equal to target. </b> </br>
     * If there is no such subarray, return 0 instead.
     * </br>
     * </br>
     *
     * Example 1: </br>
     * Input: target = 7, nums = [2,3,1,2,4,3]</br>
     * Output: 2</br>
     * Explanation: The subarray [4,3] has the minimal length under the problem constraint.</br></br>
     * Example 2:
     *</br>
     * Input: target = 4, nums = [1,4,4]</br>
     * Output: 1</br></br>
     * Example 3:</br>
     *
     * Input: target = 11, nums = [1,1,1,1,1,1,1,1]</br>
     * Output: 0
     * </br> </br>
     * Solution: </br>
     *Time Complexity: O(n^2)</br>
     * Space Complexity: O(1)
     * */
    public static int minimumSizeSubArraySumBruteForce(int [] nums, int target){
        int ans = 0;
        for(int i = 0; i < nums.length; i++){

            if(nums[i] >= target){
                return 1;
            }

            int sumSoFar = nums[i];
            for(int j = i + 1; j < nums.length; j++){
                sumSoFar += nums[j];
                if(sumSoFar >= target){
                    int curr = j - i + 1;
                    if(ans == 0){
                        ans = curr;
                    }else{
                        ans = Math.min(ans, curr);
                    }
                    break;
                }
            }

        }
        return ans;
    }

    /** This approach provides better time complexity </br> </br> Time Complexity: O(n) </br> Space Complexity: O(1) */

    public static int minimumSizeSubArraySumSlidingWindow(int [] nums, int target){
        int left = 0;
        int currentWindowSum = 0;
        int res = Integer.MAX_VALUE;

        for(int right = 0; right < nums.length; right++){
            currentWindowSum += nums[right];
            while(currentWindowSum >= target){
                res = Math.min(res, right - left + 1);
                currentWindowSum -= nums[left];
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }


    /**
     *Maximum Number of Vowels in a Substring of Given Length </br> </br>
     *Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
     *</br>
     * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
     *</br></br>
     * Example 1:
     *</br>
     * Input: s = "abciiidef", k = 3</br>
     * Output: 3</br>
     * Explanation: The substring "iii" contains 3 vowel letters.</br></br>
     * Example 2:
     *</br>
     * Input: s = "aeiou", k = 2</br>
     * Output: 2</br>
     * Explanation: Any substring of length 2 contains 2 vowels.</br></br>
     * Example 3:
     *</br>
     * Input: s = "leetcode", k = 3</br>
     * Output: 2</br>
     * Explanation: "lee", "eet" and "ode" contain 2 vowels.</br></br></br>
     * Constraints:
     *</br>
     * 1 <= s.length <= 105</br>
     * s consists of lowercase English letters.</br>
     * 1 <= k <= s.length</br>
     * </br></br>
     * Solution: </br>
     * Time Complexity: O(n^2)
     * </br>
     * Space Complexity: O(n) : Due to extra memory to store charArray. </br> We can s.charAt() method to make space O(1)
     * */
    public static int maxVowelCountBruteForce(String s, int k){
        int ans = 0;
        char [] charArray = s.toCharArray();
        for(int i = 0; i <= charArray.length - k; i++ ){
            int vowelCount = 0;
            for(int j = i; j < i + k; j++){
                if(charArray[j] == 'a' || charArray[j] == 'e' || charArray[j] == 'i' || charArray[j] == 'o' || charArray[j] == 'u'){
                    vowelCount++;
                }
            }
            ans = Math.max(vowelCount, ans);
        }

        return ans;
    }

    /**
     * It's a fixed sliding window problem. </br>
     *
     * Time Complexity: O(n) </br>
     * Space Complexity: O(1): No extra space
     *
     *
     */

    public static int maxVowelSlidingWindow(String s, int k ){
        int res = 0;
        int currentVowelCount = 0;

        for(int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) {
                currentVowelCount++;
            }
        }

        res = Math.max(currentVowelCount, res);

        for(int i = k; i < s.length(); i++){
            if(isVowel(s.charAt(i - k))){
                currentVowelCount--;
            }

            if(isVowel(s.charAt(i))){
                currentVowelCount++;
            }

            res = Math.max(currentVowelCount, res);
        }


        return res;
    }

    public static boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }


    /**
     * Get Equal Substrings Within Budget </br> </br>
     *
     * You are given two strings s and t of the same length and an integer maxCost. </br>
     *
     * You want to change s to t. Changing the ith character of s to ith character of t costs |s[i] - t[i]| (i.e., the absolute difference between the ASCII values of the characters). </br></br>
     *
     * Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of t with a cost less than or equal to maxCost. If there is no substring from s that can be changed to its corresponding substring from t, return 0.</br></br>
     *
     * Example 1:</br>
     *
     * Input: s = "abcd", t = "bcdf", maxCost = 3</br>
     * Output: 3</br>
     * Explanation: "abc" of s can change to "bcd".
     * That costs 3, so the maximum length is 3.</br></br>
     * Example 2:</br>
     *
     * Input: s = "abcd", t = "cdef", maxCost = 3</br>
     * Output: 1</br>
     * Explanation: Each character in s costs 2 to change to character in t,  so the maximum length is 1.</br></br>
     * Example 3:</br>
     *
     * Input: s = "abcd", t = "acde", maxCost = 0</br>
     * Output: 1</br>
     * Explanation: You cannot make any change, so the maximum length is 1.</br></br>
     *
     * Constraints:</br>
     *
     * 1 <= s.length <= 105</br>
     * t.length == s.length</br>
     * 0 <= maxCost <= 106</br>
     * s and t consist of only lowercase English letters.</br></br></br>
     *
     * Solution: </br>
     * Time Complexity: O(n^2)</br>
     * Space Complexity: O(1)
     * */
    public static int equalSubStringWithinBudgetBruteForce(String s, String t, int maxCost){
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            int curMax = 0;
            for(int j = i; j < s.length(); j++){

                curMax += Math.abs(s.charAt(j) - t.charAt(j));
                if(curMax <= maxCost){
                    res = Math.max(res, j - i + 1);
                }else{
                 break;
                }
            }
        }
        return res;
    }

    /**
     * Time Complexity: O(n)</br>
     * Space Complexity: O(1)
     * */
    public static int equalSubStringWithinBudgetSlidingWindow(String s, String t, int maxCost){

        int left = 0;
        int curMax = 0;
        int res = 0;

        for(int right = 0; right < s.length(); right++){
            curMax += Math.abs(s.charAt(right) - t.charAt(right));

            while(curMax > maxCost){
                curMax -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    /**
     * Find the Highest Altitude </br> </br>
     * There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes. </br> The biker starts his trip on point 0 with altitude equal 0. </br></br>

     * You are given an integer array gain of length n where gain[i] is the net gain in altitude between points i and i + 1 for all (0 <= i < n). Return the highest altitude of a point.</br></br>

     * Example 1:</br>

     * Input: gain = [-5,1,5,0,-7]</br>
     * Output: 1</br>
     * Explanation: The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.</br></br>
     * Example 2:</br>
     *
     * Input: gain = [-4,-3,-2,-1,4,3,2]</br>
     * Output: 0</br>
     * Explanation: The altitudes are [0,-4,-7,-9,-10,-6,-3,-1]. The highest is 0.</br></br>
     *
     *
     * Constraints:</br>
     *
     * n == gain.length</br>
     * 1 <= n <= 100</br>
     * -100 <= gain[i] <= 100</br>
     * </br>
     * Solution: </br> </br>
     * This can be solved by taking the maximum altitudes at each step in the journey. </br> <b> The altitude at a   step can be determined as the altitude at the previous step plus the gain at the current step </b>.</br> Hence, we will start from 0 and keep adding the gain in altitude to it at each step, and after each addition, we will update the maximum altitude we have seen so far.
     </br></br>
     Time Complexity: O(n)</br>
     Space Complexity: O(1)

     * */
    public static int highestAltitude(int [] gains){
        int maxGain = 0;
        int currentGain = 0;

        for (int gain : gains) {
            currentGain += gain;
            maxGain = Math.max(maxGain, currentGain);
        }
        return maxGain;
    }



    public static void main(String[] args) {
        int [] num1 = {2, 3, 1, 2, 4, 3};
       // System.out.println(minimumSizeSubArraySumSlidingWindow(num1, 7));
        //System.out.println(maxVowelSlidingWindow("aeiou", 2));
        System.out.println(equalSubStringWithinBudgetSlidingWindow("abcd", "bcdf", 3));
    }
}
