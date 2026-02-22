/**
 * LeetCode 485: Max Consecutive Ones
 * 
 * Problem: Find maximum consecutive 1s in binary array.
 * 
 * Solution Approach: One pass with counter
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int maxCount = 0;
        
        for (int num : nums) {
            if (num == 1) {
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                count = 0; // Reset on zero
            }
        }
        
        return maxCount;
    }
}
