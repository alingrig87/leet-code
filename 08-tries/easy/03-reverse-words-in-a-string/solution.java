import java.util.*;

/**
 * LeetCode 151: Reverse Words in a String
 * 
 * Problem: Reverse order of words in string.
 * 
 * Solution Approach: Split and reverse
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
class Solution {
    public String reverseWords(String s) {
        // Trim and split by spaces
        String[] words = s.trim().split("\\s+");
        
        // Reverse array
        Collections.reverse(Arrays.asList(words));
        
        // Join with single space
        return String.join(" ", words);
    }
}
