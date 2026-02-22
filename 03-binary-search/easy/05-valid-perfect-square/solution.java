/**
 * LeetCode 367: Valid Perfect Square
 * 
 * Problem: Check if number is perfect square.
 * 
 * Solution Approach: Binary search
 * Time Complexity: O(log num)
 * Space Complexity: O(1)
 */
class Solution {
    public boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }
        
        long left = 1;
        long right = num;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;
            
            if (square == num) {
                return true; // Perfect square found
            } else if (square < num) {
                left = mid + 1; // Try larger value
            } else {
                right = mid - 1; // Try smaller value
            }
        }
        
        return false; // Not a perfect square
    }
}
