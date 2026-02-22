/**
 * LeetCode 125: Valid Palindrome
 * 
 * Problem: Check if string is palindrome after removing non-alphanumeric
 * and converting to lowercase.
 * 
 * Solution Approach: Two pointers from both ends
 * Time Complexity: O(n) - single pass through string
 * Space Complexity: O(1) - only using pointers
 */
class Solution {
    
    /**
     * Main solution using two pointers
     * 
     * Key insight: Use two pointers from both ends.
     * Skip non-alphanumeric characters, convert to lowercase,
     * and compare. Move pointers towards center.
     * 
     * @param s Input string
     * @return true if palindrome, false otherwise
     */
    public boolean isPalindrome(String s) {
        // Edge case: empty string is considered palindrome
        if (s == null || s.length() == 0) {
            return true;
        }
        
        // Two pointers: one from left, one from right
        int left = 0;
        int right = s.length() - 1;
        
        // Continue until pointers meet
        while (left < right) {
            // Skip non-alphanumeric characters from left
            // Move left pointer until we find alphanumeric character
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            
            // Skip non-alphanumeric characters from right
            // Move right pointer until we find alphanumeric character
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare characters at both pointers (case-insensitive)
            // Convert both to lowercase for comparison
            char leftChar = Character.toLowerCase(s.charAt(left));
            char rightChar = Character.toLowerCase(s.charAt(right));
            
            // If characters don't match, string is not palindrome
            if (leftChar != rightChar) {
                return false;
            }
            
            // Move both pointers towards center
            left++;
            right--;
        }
        
        // If we've compared all characters and they match, it's a palindrome
        return true;
    }
}

/**
 * Alternative: More explicit version with helper method
 */
class SolutionExplicit {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Find next alphanumeric from left
            while (left < right && !isAlphanumeric(s.charAt(left))) {
                left++;
            }
            
            // Find next alphanumeric from right
            while (left < right && !isAlphanumeric(s.charAt(right))) {
                right--;
            }
            
            // Compare (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) != 
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    private boolean isAlphanumeric(char c) {
        return (c >= 'a' && c <= 'z') || 
               (c >= 'A' && c <= 'Z') || 
               (c >= '0' && c <= '9');
    }
}

/**
 * Alternative: Clean string first, then check
 * 
 * This uses O(n) extra space but code is simpler.
 */
class SolutionCleanFirst {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        // Build cleaned string (only alphanumeric, lowercase)
        StringBuilder cleaned = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        
        // Check if cleaned string is palindrome
        String cleanedStr = cleaned.toString();
        int left = 0;
        int right = cleanedStr.length() - 1;
        
        while (left < right) {
            if (cleanedStr.charAt(left) != cleanedStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}

/**
 * Follow-up: What if we need to check palindrome with one deletion allowed?
 * 
 * We can use two pointers and allow one mismatch.
 */
class SolutionOneDeletion {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // Try deleting left character or right character
                return isPalindrome(s, left + 1, right) || 
                       isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
