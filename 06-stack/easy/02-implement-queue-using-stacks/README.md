# Implement Queue using Stacks

## Problem Statement
Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (`push`, `peek`, `pop`, and `empty`).

**Example 1:**
```
Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]
```

## Theory & Data Structures

### Two Stacks Approach
This problem uses **two stacks** to simulate a queue. The key insight is that a stack reverses order, so using two stacks reverses the reversal, giving us FIFO behavior.

#### Key Insight: Reversing Order Twice
- **Stack 1 (input)**: Used for push operations (LIFO)
- **Stack 2 (output)**: Used for pop/peek operations (LIFO)
- **Transfer**: When output is empty, transfer all elements from input to output
- **Result**: Output stack has elements in reverse order of input, which is the correct queue order

#### Building Queue from Stacks from Scratch (Conceptual)
```java
// Conceptual implementation of queue using stacks
class MyQueue {
    private Stack<Integer> inputStack;   // For push operations
    private Stack<Integer> outputStack;  // For pop/peek operations
    
    public MyQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }
    
    // Push element to the back of queue
    public void push(int x) {
        // Simply push to input stack
        inputStack.push(x);
    }
    
    // Remove element from front of queue
    public int pop() {
        // Ensure output stack has elements
        if (outputStack.isEmpty()) {
            transferInputToOutput();
        }
        // Pop from output stack
        return outputStack.pop();
    }
    
    // Get front element
    public int peek() {
        // Ensure output stack has elements
        if (outputStack.isEmpty()) {
            transferInputToOutput();
        }
        // Peek at output stack top
        return outputStack.peek();
    }
    
    // Check if queue is empty
    public boolean empty() {
        // Queue is empty if both stacks are empty
        return inputStack.isEmpty() && outputStack.isEmpty();
    }
    
    // Helper: Transfer all elements from input to output
    private void transferInputToOutput() {
        // Transfer all elements from input stack to output stack
        // This reverses the order, giving us FIFO behavior
        while (!inputStack.isEmpty()) {
            outputStack.push(inputStack.pop());
        }
    }
    
    // Why does this work?
    // - Push: Add to input stack (maintains order)
    // - Pop/Peek: Use output stack (reversed order = queue order)
    // - Transfer only when output is empty (amortized efficiency)
    // - Each element moved at most twice (once in each stack)
}
```

### Amortized Analysis
- **Push**: O(1) - Just push to input stack
- **Pop/Peek**: O(1) amortized - Transfer happens only when output is empty
- **Each element**: Moved at most twice (once to input, once to output)
- **Amortized cost**: O(1) per operation

### Time & Space Complexity

#### Approach: Two Stacks
- **Time Complexity**: O(1) amortized per operation
  - Push: O(1)
  - Pop: O(1) amortized (transfer is O(n) but happens rarely)
  - Peek: O(1) amortized
  - Empty: O(1)
- **Space Complexity**: O(n) - Two stacks store all elements
  - Input stack: O(n) in worst case
  - Output stack: O(n) in worst case
  - Total: O(n)

## Interview Simulation

### Initial Discussion

**Interviewer**: "Implement a queue using only two stacks."

**Candidate**: "I'll use two stacks - an input stack for push operations and an output stack for pop and peek operations. When the output stack is empty and we need to pop or peek, I'll transfer all elements from the input stack to the output stack. This reverses the order twice, giving us FIFO behavior."

**Interviewer**: "Why transfer only when output is empty?"

**Candidate**: "For efficiency. If we transferred after every push, we'd be doing unnecessary work. By transferring only when needed (when output is empty), we get amortized O(1) operations. Each element is moved at most twice - once to input stack, once to output stack."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. Push 1: input=[1], output=[]. Push 2: input=[1,2], output=[]. Peek: output is empty, transfer: input=[], output=[2,1]. Peek returns 1. Pop: output=[2,1], pop returns 1, output=[2]. Push 3: input=[3], output=[2]. Pop: output not empty, pop returns 2, output=[]."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(1) amortized per operation. Push is always O(1). Pop and peek are O(1) amortized because transfer happens only when output is empty, and each element is transferred at most once. Space complexity is O(n) for the two stacks."

### Follow-up Questions

**Interviewer**: "Can you do it with one stack?"

**Candidate**: "Not efficiently. We could use recursion, but that uses O(n) stack space anyway, so we're not really saving space. The two-stack approach is the standard solution."

**Interviewer**: "What if we need to support operations in any order?"

**Candidate**: "The current implementation already supports operations in any order. The key is transferring only when output is empty, which maintains correctness regardless of the operation sequence."

**Interviewer**: "Can you optimize the transfer operation?"

**Candidate**: "The current transfer is already optimal - we transfer all elements at once when needed. We could potentially use a more sophisticated data structure, but for the constraint of using only stacks, this is optimal."

### Tricky Edge Cases

1. **Empty queue**: `empty()` → Return `true`
2. **Single element**: Push 1, pop → Returns 1
3. **Multiple pushes then pops**: Maintain FIFO order
4. **Interleaved operations**: Push, pop, push, pop → Correct order
5. **Peek without pop**: Peek returns front without removing
6. **Transfer timing**: Only when output empty
7. **Large number of operations**: Amortized O(1) holds

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll create two stacks - inputStack for push operations and outputStack for pop/peek operations. For push, I'll simply push to inputStack. For pop, I'll first check if outputStack is empty, and if so, transfer all elements from inputStack to outputStack. Then I'll pop from outputStack. For peek, I'll do the same transfer check, then peek at outputStack. For empty, I'll check if both stacks are empty."

**Interviewer**: "Why is the transfer operation crucial?"

**Candidate**: "The transfer operation is what makes this work. When we transfer from input to output, we're reversing the order. Since stacks reverse order, transferring reverses it again, giving us the original order - which is what we need for FIFO queue behavior."

## Solution Approaches

### Approach 1: Two Stacks (Optimal)
Input stack for push, output stack for pop/peek. Transfer when needed. O(1) amortized time.

**Algorithm:**
1. Create two stacks: inputStack, outputStack
2. Push: push to inputStack
3. Pop/Peek: if outputStack empty, transfer from inputStack, then pop/peek from outputStack
4. Empty: check if both stacks empty

**Advantages:**
- O(1) amortized operations
- Simple and elegant
- Optimal solution

### Approach 2: Transfer After Every Push (Not Recommended)
Transfer after each push. O(n) time per push.

**Disadvantages:**
- O(n) time for push
- Less efficient
- Not optimal

## Key Takeaways

1. **Two stacks** simulate queue behavior
2. **Transfer when needed** for amortized efficiency
3. **Amortized O(1)** operations
4. **Reversing twice** gives original order
5. **Input stack** for push, **output stack** for pop/peek
6. **Classic data structure** problem
7. **Foundation for** understanding amortized analysis
8. **Space-time trade-off** - O(n) space for O(1) amortized time
9. **Transfer optimization** - only when output empty
10. **Important interview problem** - tests understanding of stacks and queues
