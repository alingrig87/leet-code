/**
 * LeetCode 42: Trapping Rain Water
 * 
 * Problem: Calculate trapped rainwater.
 * 
 * Solution Approach: Two pointers
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        
        int left = 0;
        int right = height.length - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int water = 0;
        
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= maxLeft) {
                    maxLeft = height[left];
                } else {
                    water += maxLeft - height[left];
                }
                left++;
            } else {
                if (height[right] >= maxRight) {
                    maxRight = height[right];
                } else {
                    water += maxRight - height[right];
                }
                right--;
            }
        }
        
        return water;
    }
}
