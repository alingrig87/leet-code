/**
 * LeetCode 11: Container With Most Water
 * 
 * Problem: Find two lines that form container with most water.
 * 
 * Solution Approach: Two pointers with greedy
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        
        while (left < right) {
            int width = right - left;
            int currentArea = Math.min(height[left], height[right]) * width;
            maxArea = Math.max(maxArea, currentArea);
            
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
}
