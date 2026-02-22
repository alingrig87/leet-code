/**
 * LeetCode 69: Sqrt(x)
 * 
 * Problem: Find square root rounded down.
 * 
 * Solution Approach: Binary search
 * Time Complexity: O(log x)
 * Space Complexity: O(1)
 */
class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        
        int left = 0;
        int right = x;
        int result = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Use long to avoid integer overflow
            long square = (long) mid * mid;
            
            if (square == x) {
                return mid; // Perfect square
            } else if (square < x) {
                // Mid*mid < x, so mid is a valid candidate
                // Try larger values
                result = mid; // Update result
                left = mid + 1;
            } else {
                // Mid*mid > x, try smaller values
                right = mid - 1;
            }
        }
        
        return result;
    }
}
