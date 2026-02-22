/**
 * LeetCode 209: Minimum Size Subarray Sum
 * 
 * Problem: Find minimum length subarray with sum >= target.
 * 
 * Solution Approach: Sliding window
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right]; // Expand window
            
            // Shrink window while sum >= target
            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left]; // Remove left element
                left++; // Shrink window
            }
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
