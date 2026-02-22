import java.util.*;

/**
 * LeetCode 232: Implement Queue using Stacks
 * 
 * Problem: Implement FIFO queue using two stacks.
 * 
 * Solution Approach: Two stacks (input and output)
 * Time Complexity: O(1) amortized
 * Space Complexity: O(n)
 */
class MyQueue {
    private Stack<Integer> input;
    private Stack<Integer> output;
    
    public MyQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }
    
    public void push(int x) {
        // Push to input stack
        input.push(x);
    }
    
    public int pop() {
        // Transfer from input to output if output is empty
        if (output.isEmpty()) {
            transfer();
        }
        return output.pop();
    }
    
    public int peek() {
        // Transfer from input to output if output is empty
        if (output.isEmpty()) {
            transfer();
        }
        return output.peek();
    }
    
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }
    
    private void transfer() {
        // Transfer all elements from input to output
        // This reverses order, making FIFO behavior
        while (!input.isEmpty()) {
            output.push(input.pop());
        }
    }
}
