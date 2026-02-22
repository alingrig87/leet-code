/**
 * LeetCode 35: Search Insert Position
 * 
 * Problem: Find insertion position in sorted array.
 * 
 * Solution Approach: Binary search
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid; // Target found
            } else if (nums[mid] < target) {
                left = mid + 1; // Target is in right half
            } else {
                right = mid - 1; // Target is in left half
            }
        }
        
        // When loop ends, left points to insertion position
        // left is the first position where element >= target
        return left;
    }
}
