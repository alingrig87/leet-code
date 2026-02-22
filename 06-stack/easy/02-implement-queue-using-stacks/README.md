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
Use two stacks: one for input, one for output. When output stack is empty, transfer all from input to output.

### Time & Space Complexity

#### Approach: Two Stacks
- **Time Complexity**: O(1) amortized - Each element moved at most twice
- **Space Complexity**: O(n) - Two stacks

## Interview Simulation

### Initial Discussion

**Interviewer**: "Implement queue using stacks."

**Candidate**: "I'll use two stacks - input stack for push operations, output stack for pop/peek. When output is empty, transfer all elements from input to output. This reverses order, making FIFO behavior."

**Interviewer**: "What's the complexity?"

**Candidate**: "Amortized O(1) for all operations. Each element is pushed and popped at most twice - once in each stack."

### Follow-up Questions

**Interviewer**: "Can you do it with one stack?"

**Candidate**: "Not efficiently. We'd need recursion which uses O(n) stack space anyway."

### Tricky Edge Cases

1. **Empty queue**: Return appropriate values
2. **Single element**: Handle correctly
3. **Multiple operations**: Maintain correct order
4. **Transfer timing**: Only when output empty

## Solution Approaches

### Approach: Two Stacks (Optimal)
Input stack for push, output stack for pop/peek. Transfer when needed. O(1) amortized time.

## Key Takeaways

1. **Two stacks** simulate queue
2. **Transfer when needed** for efficiency
3. **Amortized O(1)** operations
4. **Classic data structure** problem
