/**
 * LeetCode 344: Reverse String
 * 
 * Problem: Reverse a string in-place with O(1) extra memory.
 * 
 * Solution Approach: Two pointers from ends
 * Time Complexity: O(n) - n/2 swaps
 * Space Complexity: O(1) - only temporary variable
 */
class Solution {
    
    /**
     * Main solution using two pointers
     * 
     * Key insight: Use two pointers from both ends.
     * Swap characters at each position, then move pointers towards center.
     * 
     * @param s Array of characters to reverse
     */
    public void reverseString(char[] s) {
        // Edge case: empty or single character
        if (s == null || s.length <= 1) {
            return;
        }
        
        // Two pointers: one from left, one from right
        int left = 0;
        int right = s.length - 1;
        
        // Continue until pointers meet
        while (left < right) {
            // Swap characters at left and right positions
            // This moves characters from ends towards center
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            
            // Move pointers towards center
            left++;
            right--;
        }
        // After loop, array is reversed
    }
}

/**
 * Alternative: Using XOR swap (no temporary variable)
 * 
 * More complex but doesn't use extra variable.
 */
class SolutionXOR {
    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        
        int left = 0;
        int right = s.length - 1;
        
        while (left < right) {
            // XOR swap: a ^= b; b ^= a; a ^= b;
            s[left] ^= s[right];
            s[right] ^= s[left];
            s[left] ^= s[right];
            
            left++;
            right--;
        }
    }
}

/**
 * Alternative: Recursive approach
 * 
 * Uses O(n) stack space, so not O(1) space.
 */
class SolutionRecursive {
    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        reverseHelper(s, 0, s.length - 1);
    }
    
    private void reverseHelper(char[] s, int left, int right) {
        if (left >= right) {
            return;
        }
        
        // Swap
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
        
        // Recurse
        reverseHelper(s, left + 1, right - 1);
    }
}
