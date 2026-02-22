/**
 * LeetCode 680: Valid Palindrome II
 * 
 * Problem: Check if string can be palindrome after deleting at most one character.
 * 
 * Solution Approach: Two pointers with helper method
 * Time Complexity: O(n) - at most two passes
 * Space Complexity: O(1) - only pointers
 */
class Solution {
    
    /**
     * Main solution
     * 
     * Key insight: Use two pointers. When mismatch found,
     * try deleting left character or right character,
     * then check if remaining substring is palindrome.
     * 
     * @param s Input string
     * @return true if can be palindrome after at most one deletion
     */
    public boolean validPalindrome(String s) {
        // Edge case: empty or single character is palindrome
        if (s == null || s.length() <= 1) {
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        // Use two pointers to check palindrome
        while (left < right) {
            // If characters match, continue
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                // Mismatch found - try deleting one character
                // Option 1: Delete left character, check s[left+1...right]
                // Option 2: Delete right character, check s[left...right-1]
                // If either substring is palindrome, return true
                return isPalindrome(s, left + 1, right) || 
                       isPalindrome(s, left, right - 1);
            }
        }
        
        // No mismatches found, string is already palindrome
        return true;
    }
    
    /**
     * Helper method: Check if substring is palindrome
     * 
     * @param s String
     * @param left Start index (inclusive)
     * @param right End index (inclusive)
     * @return true if substring is palindrome
     */
    private boolean isPalindrome(String s, int left, int right) {
        // Check if substring from left to right is palindrome
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false; // Not palindrome
            }
            left++;
            right--;
        }
        return true; // Is palindrome
    }
}

/**
 * Alternative: More explicit version
 */
class SolutionExplicit {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // Try skipping left character
                boolean skipLeft = checkPalindrome(s, left + 1, right);
                
                // Try skipping right character
                boolean skipRight = checkPalindrome(s, left, right - 1);
                
                // Return true if either works
                return skipLeft || skipRight;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    private boolean checkPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
