import java.util.*;

/**
 * LeetCode 682: Baseball Game
 * 
 * Problem: Calculate score with special operations.
 * 
 * Solution Approach: Stack
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
class Solution {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        
        for (String op : ops) {
            if (op.equals("C")) {
                // Invalidate previous score
                stack.pop();
            } else if (op.equals("D")) {
                // Double previous score
                stack.push(2 * stack.peek());
            } else if (op.equals("+")) {
                // Sum of previous two scores
                int top = stack.pop();
                int sum = top + stack.peek();
                stack.push(top);
                stack.push(sum);
            } else {
                // Integer score
                stack.push(Integer.parseInt(op));
            }
        }
        
        // Sum all scores
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        
        return sum;
    }
}
