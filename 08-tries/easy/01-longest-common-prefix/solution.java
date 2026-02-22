/**
 * LeetCode 14: Longest Common Prefix
 * 
 * Problem: Find longest common prefix among strings.
 * 
 * Solution Approach: Vertical scanning
 * Time Complexity: O(S) where S is sum of all characters
 * Space Complexity: O(1) excluding result
 */
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        // Use first string as reference
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            
            // Check if all strings have this character at position i
            for (int j = 1; j < strs.length; j++) {
                // If string is too short or character doesn't match
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        
        // First string is the common prefix
        return strs[0];
    }
}
