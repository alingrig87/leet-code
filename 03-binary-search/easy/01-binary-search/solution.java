/**
 * LeetCode 704: Binary Search
 * 
 * Problem: Search target in sorted array with O(log n) time.
 * 
 * Solution Approach: Binary search
 * Time Complexity: O(log n)
 * Space Complexity: O(1) iterative
 */
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            // Calculate middle index
            // Use left + (right - left) / 2 to avoid integer overflow
            // Instead of (left + right) / 2 which can overflow
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid; // Found target
            } else if (nums[mid] < target) {
                // Target is in right half
                left = mid + 1;
            } else {
                // Target is in left half
                right = mid - 1;
            }
        }
        
        return -1; // Target not found
    }
}
