/**
 * LeetCode 643: Maximum Average Subarray I
 * 
 * Problem: Find maximum average of subarray of length k.
 * 
 * Solution Approach: Sliding window
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        // Calculate sum of first k elements
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        
        int maxSum = sum;
        
        // Slide window: remove left element, add right element
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i - k] + nums[i]; // Slide window
            maxSum = Math.max(maxSum, sum);
        }
        
        return (double) maxSum / k;
    }
}
