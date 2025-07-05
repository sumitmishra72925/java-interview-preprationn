package topic.dsa.array_string;

import java.util.Arrays;

public class PrefixSum {
    /**
     * @param num an array which needs to be split
     * @return total valid split possible, where leftSection >= rightSection
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     *
     * */
    public int splitArrayBruteForce(int [] num){
        int ans = 0;
        int leftSum = 0;
        for(int i = 0; i < num.length - 1; i++){
            leftSum += num[i];
            int rightSum = 0;
            for(int j = i + 1; j < num.length; j++){
                rightSum += num[j];
            }
            System.out.println("Left & right sum: "+leftSum+", "+rightSum);
            if(leftSum >= rightSum){
                ans++;
            }
        }
        return ans;
    }

    /**
     * @param num an array which needs to be split
     * @return total valid split possible, where leftSection >= rightSection
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */

    public int splitUsingPrefixSum(int [] num){
        //base case
        if(num.length == 0){
            return  -1;
        }

        int ans = 0;
        int [] prefixSum = new int[num.length];


        prefixSum[0] = num[0];
        for(int i = 1; i < num.length; i++){
            prefixSum[i] = prefixSum[i -1] + num[i];
        }

        for(int i = 0; i <num.length - 1; i++){
            int leftSection = prefixSum[i];
            int rightSection = prefixSum[num.length - 1] - prefixSum[i];
            System.out.println("Left & right sum: "+leftSection+", "+rightSection);
            if(leftSection >= rightSection){
                ans++;
            }
        }
        return ans;
    }

    /**
     * @param num an array which needs to be split
     * @return total valid split possible, where leftSection >= rightSection
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */

    public int splitUsingPrefixSumWithoutAdditionalArray(int [] num){
        //base case
        if(num.length == 0){
            return  -1;
        }

        int ans = 0;
        int totalSum = 0;
        for (int j : num) {
            totalSum += j;
        }

        int leftSection = 0;
        for(int i = 0; i <num.length - 1; i++){
            leftSection += num[i];
            int rightSection = totalSum - leftSection;
            System.out.println("Left & right sum: "+leftSection+", "+rightSection);
            if(leftSection >= rightSection){
                ans++;
            }
        }
        return ans;
    }

    /**
     * Given an array of integers nums, you start with an initial positive value startValue.
     * In each iteration, you calculate the step by step sum of startValue plus elements in nums (from left to right).
     * Return the minimum positive value of startValue such that the step by step sum is never less than 1.

     * Example 1:
     * Input: nums = [-3,2,-3,4,2]
     * Output: 5
     * Explanation: If you choose startValue = 4, in the third iteration your step by step sum is less than 1.
     * step by step sum
     * startValue = 4 | startValue = 5 | nums
     *   (4 -3 ) = 1  | (5 -3 ) = 2    |  -3
     *   (1 +2 ) = 3  | (2 +2 ) = 4    |   2
     *   (3 -3 ) = 0  | (4 -3 ) = 1    |  -3
     *   (0 +4 ) = 4  | (1 +4 ) = 5    |   4
     *   (4 +2 ) = 6  | (5 +2 ) = 7    |   2
     * */
    /*Thr brute approach tries to find every possible value that startValue can take and then return the actual values
    * which satisfy the constraint.
    * Time Complexity: O(s * n): S is number of increment Need to be made for startValue and n is array traversla time
    */
    public int minStartValueBruteForce(int[] num) {
        int startValue = 1;
        boolean notFound = false;
        while(true){
            int sum = startValue + num[0];
            if(sum <= 0){
                startValue++;
            }else{
                for(int i = 1; i < num.length; i++){
                    sum += num[i];
                    if(sum <= 0){
                        notFound = true;
                        startValue++;
                        break;
                    }
                    if( i == num.length - 1){
                        return startValue;
                    }
                }
                if(!notFound){
                    return startValue;
                }
            }
        }
    }

    /**
     * The optimal approach recognizes that we need to find the point where our running sum becomes most negative relative to our starting position.
     * Once we know this lowest point,we can calculate exactly what starting value we need to ensure we never drop below 1.
     * Time Complexity: O(n)
     * Space: O(1)
     * */
    public int minStartValueOptimal(int [] nums){
        int minPrefixSum = 0;      // Track the minimum running sum we encounter
        int currentSum = 0;        // Our running sum as we traverse the array

        // Single pass through the array to find the lowest point
        for (int num : nums) {
            currentSum += num;     // Add current element to our running total

            // Update minimum if current sum is lower than any we've seen
            minPrefixSum = Math.min(minPrefixSum, currentSum);
        }
        // Calculate the minimum startValue needed
        // If minPrefixSum is -5, we need startValue of 6 so that 6 + (-5) = 1
        return Math.max(1, 1 - minPrefixSum);
    }

    /**
     *   -:K Radius Subarray Averages:-
     * You are given a 0-indexed array nums of n integers, and an integer k.
     * The k-radius average for a subarray of nums centered at some index i with the radius k is the average of all elements in nums between the indices i - k and i + k (inclusive).
     * If there are less than k elements before or after the index i, then the k-radius average is -1.
     * Build and return an array avgs of length n where avgs[i] is the k-radius average for the subarray centered at index i.

     * Input: nums = [7,4,3,9,1,8,5,2,6], k = 3
     * Output: [-1,-1,-1,5,4,4,-1,-1,-1]

     * SOLUTION:
     * Time Complexity: O(n * k)
     * Spcae: O (1)

     *  */
    public int [] kRadiusSubArrayBruteForce(int [] num, int k){
        if(k == 0){
            return num;
        }
        int n = num.length - 1;
        int windowSize = k * 2 + 1;

        int [] avgs = new int[num.length];

        for(int i = 0; i < n; i++){

            if(i < k || n - i < k){
                avgs[i] = -1;
            }else {
                int sum = 0;
                for(int j = i - k; j <= i + k; j ++){
                    sum += num[j];
                }
                int avg = sum /windowSize;
                avgs[i] = avg;
            }
        }
        return avgs;
    }


    /*
    * Time Complexity: O(n)
    * Space Complexity: O(n)
    * */
    public int [] kRadiusUsingPrefixSum(int [] num, int k){

        if(k == 0){
            return num;
        }
        int windowSize = k * 2 + 1;
        int[] avgs = new int[num.length];

        int [] prefixSum = new int[num.length];
        prefixSum[0] = num[0];
        for(int i = 1; i < num.length; i ++){
            prefixSum[i] = num[i] + prefixSum[i - 1];
        }

        for(int i = 0; i < num.length; i++){

            if(i < k || num.length - i - 1 < k){
                avgs[i] = -1;
            }else {
                int leftPosition = i - k;
                int rightPosition = i + k;
                int sum = prefixSum[rightPosition] - prefixSum[leftPosition] + num[leftPosition];
                //System.out.println("Sum at index "+i+" is: "+sum);
                int avg = sum /windowSize;
                avgs[i] = avg;
            }
        }
        return avgs;

    }

    public int [] kRadiusUsingSlidingWindow(int [] num, int k){

        if(k == 0 ){
            return num;
        }

        int n = num.length;
        int windowSize = k * 2 + 1;
        int [] avgs = new int[n];
        Arrays.fill(avgs, -1);

        if(k > n -1){
            return avgs;
        }

        int sum = 0;
        for(int i = 0; i < windowSize; i++){
            sum += num[i];
        }

        int average = sum /windowSize;

        avgs[k] = average;


        for(int center = k + 1; center < n - k; center++) {
            // Remove element leaving the window, add element entering the window
            sum = sum - num[center - k - 1] + num[center + k];
            avgs[center] = sum / windowSize;
        }
        return avgs;
    }

    /**
     * Find Pivot Index </br></br>
     * Given an array of integers nums, calculate the pivot index of this array.
     *</br>
     * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
     *</br>
     * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
     *</br>
     * Return the leftmost pivot index. If no such index exists, return -1.
     *</br></br>
     * Example 1:
     *</br>
     * Input: nums = [1,7,3,6,5,6]</br>
     * Output: 3</br>
     * Explanation:</br>
     * The pivot index is 3.</br>
     * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
     * Right sum = nums[4] + nums[5] = 5 + 6 = 11</br></br>
     * Example 2:</br>
     *
     * Input: nums = [1,2,3]</br>
     * Output: -1</br>
     * Explanation:</br>
     * There is no index that satisfies the conditions in the problem statement.</br></br>
     * Example 3:</br>
     *
     * Input: nums = [2,1,-1]</br>
     * Output: 0</br>
     * Explanation:
     * The pivot index is 0.</br>
     * Left sum = 0 (no elements to the left of index 0)</br>
     * Right sum = nums[1] + nums[2] = 1 + -1 = 0</br></br>
     *
     * Solution: </br></br>
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * */
    public static int pivotSumBruteForce(int [] nums){
        int res = -1;
        for(int i =0; i < nums.length; i++){
            int lSum = 0;

            for(int l= 0; l < i; l++){
                lSum += nums[l];
            }

            int rSum = 0;
            for(int r =0; r < nums.length; r++){
                rSum += nums[rSum];
            }

            if(lSum == rSum){
                res = i;
                break;
            }
        }
        return res;
    }


    /**
     * Time Complexity: O(n) </br>
     * Space Complexity: O(n)
     * */
    public static int pivotSumPrefixSum(int [] nums){
        int res = -1;

        int [] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];

        for(int i = 1; i < nums.length; i++){
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        System.out.println(Arrays.toString(prefixSum));

        for(int i = 0; i < prefixSum.length; i++){
            int leftSum;
            int rightSum;

            if(i == 0){
                leftSum = 0;
            }else {
                leftSum = prefixSum[i - 1];
            }

            if(i == prefixSum.length - 1){
                rightSum = 0;
            }else {
                rightSum = prefixSum[nums.length - 1] - prefixSum[i + 1] + nums[i + 1];
            }
            if(leftSum == rightSum){
                res = i;
                break;
            }
        }
        return res;
    }

    /**
     * Time Complexity: O(n)
     * </br>
     * Space Complexity: O(1)
     * */
    public static int pivotSumWithExtraSpace(int [] nums){
        int total = 0;

        for (int num : nums) {
            total += num;
        }

        int left = 0;
        for(int i = 0; i < nums.length; i++){
            int right = total - nums[i] - left;
            if(right == left){
                return i;
            }
            left += nums[i];
        }




        return  -1;
    }






    public static void main(String[] args) {
//        PrefixSum prefixSum = new PrefixSum();
        int [] input = {1,7,3,6,5,6};
//        int [] avg = prefixSum.kRadiusUsingSlidingWindow(input, 3);
//        System.out.println(Arrays.toString(avg));
//


//        String s = "ABC";
//        StringBuilder sb = new StringBuilder();
//        sb.append("S");
//        sb.append("U");
//        sb.append("M");
//        sb.append("I");
//        sb.append("T");
//
//       sb.setCharAt(0, 'a');
//
//        System.out.println( sb.reverse());;

        System.out.println(pivotSumPrefixSum(input));


    }


}
