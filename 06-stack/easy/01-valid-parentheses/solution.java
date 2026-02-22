import java.util.*;

/**
 * LeetCode 20: Valid Parentheses
 * 
 * Problem: Check if parentheses string is valid.
 * 
 * Solution Approach: Stack
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        // Stack to track opening brackets
        Stack<Character> stack = new Stack<>();
        
        // Map closing brackets to opening brackets for easy matching
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        
        for (char c : s.toCharArray()) {
            // If opening bracket, push to stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // Closing bracket
                // Check if stack is empty or brackets don't match
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false;
                }
            }
        }
        
        // Stack should be empty if all brackets matched
        return stack.isEmpty();
    }
}
