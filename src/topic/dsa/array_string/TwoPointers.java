package topic.dsa.array_string;

import java.util.Arrays;

public class TwoPointers {


    /**
     * Reverse Words in a String III </br> </br>
     * Given a string <b>s</b>, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
     * </br>
     * <b>Example 1:</b>
     * Input: s = "Let's take LeetCode contest"
     * Output: "s'teL ekat edoCteeL tsetnoc"
     </br>
     * <b>Example 2:</b>
     * Input: s = "Mr Ding"
     * Output: "rM gniD"
     </br>

     </br>
     * Constraints:

     * 1 <= s.length <= 5 * 104 </br>
     * s contains printable ASCII characters. </br>
     * s does not contain any leading or trailing spaces. </br>
     * There is at least one word in s.</br>
     * All the words in s are separated by a single space.
     *
     * </br>
     *
     * Solution: </br>
     * Time Complexity: O(n)
     * Space Complexity: O(n) // Since we are storing at max n character in string builder.
     * */
    public static String reverseWordInAStringBruteForce(String string){

        StringBuilder res = new StringBuilder();
        StringBuilder word = new StringBuilder();

        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) == ' '){
                for(int j = word.length() - 1; j >= 0; j--){
                    res.append(word.charAt(j));
                }
                word.setLength(0);
                res.append(" ");
            }else {
                word.append(string.charAt(i));
            }
        }

        if(word.length() > 0){
            for(int j = word.length() - 1; j >= 0; j--){
                res.append(word.charAt(j));
            }
        }
        return res.toString();
    }


    /**
     * This approach uses the two pointers approach and swap in elements in place to reverse the string.
     </br>

     Time Complexity: O(n)
     Space Complexity: O(1)
     </br>
     The charArray will not be counted as space since it's output buffer.
     * */

    public static String reverseWordInAStringTwoPointers(String string){
        char [] charArray = string.toCharArray();
        int start = 0;

        for(int end = 0; end < charArray.length; end++){

            if(charArray[end] == ' '){

                reverseString(start, end - 1, charArray);

                start = end + 1;

            }
        }
        reverseString(start, charArray.length - 1, charArray);

        return new String(charArray);
    }

    private static void reverseString(int start, int end, char[] charArray) {
        while (start < end){
            char temp = charArray[start];
            charArray[start] = charArray[end];
            charArray[end] = temp;
            start++;
            end --;
        }
    }


    /**Reverse Only Letters </br> </br>
     * Given a string <b>s</b>, reverse the string according to the following rules:
     </br>
     * All the characters that are not English letters remain in the same position.
     * </br>
     * All the English letters (lowercase or uppercase) should be reversed.
     * </br>
     * Return s after reversing it.
     * </br>
     *
     * Example 1:
     </br>
     * Input: s = "ab-cd"
     * Output: "dc-ba"
     * </br>
     * Example 2:
     </br>
     * Input: s = "a-bC-dEf-ghIj"
     * Output: "j-Ih-gfE-dCba"
     * </br>
     * Solution:
     * </br>
     * Time Complexity: O(n)
     * Space Complexity = O(1)
     * */
    private static String reverseOnlyLetters(String s){
        int left = 0;
        int right = s.length() - 1;
        char [] charArray = s.toCharArray();

        while( left < right){
            if(!Character.isLetter(charArray[left])){
                left++;
            }else if(!Character.isLetter(charArray[right])){
                right--;
            }else{
                char temp = charArray[left];
                charArray[left] = charArray[right];
                charArray[right] = temp;

                left++;
                right--;
            }
        }

        return new String(charArray);

    }

    /** Minimum Common Value </br> </br>
     * Given two integer arrays <b>nums1</b> and <b>nums2</b>, sorted in <b>non-decreasing order</b>, return the minimum integer common to both arrays. </br>
     * If there is no common integer amongst nums1 and nums2, return -1. </br>
     </br>
     * Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence of that integer.
     *</br>
     * </br>
     * Example 1:
     *</br>
     * Input: nums1 = [1,2,3], nums2 = [2,4] </br>
     * Output: 2</br>
     * Explanation: The smallest element common to both arrays is 2, so we return 2.
     * </br>
     * </br>
     * Time Complexity: O(n^2)</br>
     * Space Complexity:  O(1)
     * */
    public static int minimumCommonSumBruteForce(int [] num1, int [] num2){
        for (int k : num1) {
            for (int i : num2) {
                if (k == i) {
                    return k;
                }
                if (i > k) {
                    break;
                }
            }
        }

        return -1;
    }

    /**
     * Time complexity: O(n + m) </br>
     * Space Complexity: O(1)
     * */
    public static int minimumCommonSumTwoPointers(int [] nums1, int [] nums2){

        int left = 0;
        int right = 0;

        while(left < nums1.length && right < nums2.length){
            if(nums1[left ] == nums2[right]){
                return nums1[left];
            }
            if(nums1[left] > nums2[right]){
                right++;
            }else {
                left++;
            }
        }


        return -1;
    }

    /** Move Zeroes </br> </br>
     * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * </br>
     * Note that you must do this in-place without making a copy of the array.
     * </br>
     *
     * Example 1:
     * Input: nums = [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * </br>
     * Constraints:
     * 1 <= nums.length <= 104
     * -231 <= nums[i] <= 231 - 1
     * */
    public  static void moveZeroBruteForce(int [] nums){

        for(int i = 0; i < nums.length - 1; i ++){
            if(nums[i] == 0){

                int j = i + 1;

                while (j < nums.length && nums[j] == 0 ){
                    j++;
                }
                if(j < nums.length){
                    nums[i] = nums[j];
                    nums[j] = 0;
                }else {
                    return;
                }

            }
        }
    }

    /**
     * Two pointer approach provides best time & space complexity by doing the following.
     * It's simply checking if right of the array is a non-zero element & left & right not pointing to the same index, swap and move the pointer(left) to next zero.
     * </br>
     * Time Complexity: O(n)</br>
     * Space complexity: O(1)
     * */
    public static void moveZeroTwoPointers(int [] nums){
        int left = 0;

        for(int right = 0; right < nums.length; right++){
            if(nums[right] != 0){
                if(left != right){
                    nums[left] = nums[right];
                    nums[right] = 0;
                }
                left++;
            }
        }

        System.out.println(Arrays.toString(nums));
    }


    /**
     * Given a 0-indexed string word and a character ch, reverse the segment of word that starts at index 0 and ends at the index of the first occurrence of ch (inclusive).</br>
     * If the character ch does not exist in word, do nothing. </br> </br>
     * For example, if word = "abcdefd" and ch = "d", then you should reverse the segment that starts at 0 and ends at 3 (inclusive). </br> The resulting string will be "dcbaefd".
     * </br> </br>
     * Time complexity: O(n) </br>
     * Space Complexity: O(n) due to output buffer.
     * */
    public static String reversePrefixOfWord(String s, char ch){
        int left = 0;

        char [] charArray = s.toCharArray();

        for(int right = 0; right < charArray.length; right++ ){
            if(charArray[right] == ch){
                while (left < right){
                    char temp = charArray[left];
                    charArray[left] = charArray[right];
                    charArray[right] = temp;
                    left++;
                    right--;
                }

                break;
            }

        }
        return new String(charArray);
    }


    public static void main(String[] args) {
        int [] num1 = {0, 0, 0, 0, 1};
        int [] num2 ={2,3,4,5};

      //  moveZeroTwoPointers(num1);

        System.out.println(reversePrefixOfWord("abcdefg", 'c'));

    }








}