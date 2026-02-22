/**
 * LeetCode 28: Implement strStr()
 * 
 * Problem: Find first occurrence of needle in haystack.
 * 
 * Solution Approach: Sliding window
 * Time Complexity: O(n * m)
 * Space Complexity: O(1)
 */
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        
        int n = haystack.length();
        int m = needle.length();
        
        // Check each possible starting position
        for (int i = 0; i <= n - m; i++) {
            // Check if substring matches needle
            if (haystack.substring(i, i + m).equals(needle)) {
                return i;
            }
        }
        
        return -1; // Not found
    }
}
