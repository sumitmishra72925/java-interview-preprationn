package topic.dsa.array_string;

import java.util.HashSet;
import java.util.Set;

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

    public static void main(String[] args) {
        String s = "abxcblimza";
        System.out.println(firstToAppearTwiceUsingSet(s));
    }


}
