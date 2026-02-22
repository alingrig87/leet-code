/**
 * LeetCode 167: Two Sum II - Input Array Is Sorted
 * 
 * Problem: Find two numbers in sorted array that sum to target.
 * 
 * Solution Approach: Two pointers from ends
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return new int[0];
    }
}
