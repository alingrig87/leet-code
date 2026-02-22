/**
 * LeetCode 1021: Remove Outermost Parentheses
 * 
 * Problem: Remove outermost parentheses from each primitive string.
 * 
 * Solution Approach: Counter for depth
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Only add if not outermost (depth > 0)
                if (depth > 0) {
                    result.append(c);
                }
                depth++;
            } else {
                depth--;
                // Only add if not outermost (depth > 0)
                if (depth > 0) {
                    result.append(c);
                }
            }
        }
        
        return result.toString();
    }
}
