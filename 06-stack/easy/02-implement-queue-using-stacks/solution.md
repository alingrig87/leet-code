# Solution Explanation: Implement Queue using Stacks

## Approach: Two Stacks

### Intuition
Use two stacks: input for push, output for pop/peek. Transfer elements when output is empty.

### Algorithm
- **push(x)**: Push to input stack
- **pop()**: If output empty, transfer from input. Pop from output.
- **peek()**: If output empty, transfer from input. Peek output.
- **empty()**: Check if both stacks empty

### Complexity
- **Time**: O(1) amortized - each element moved twice
- **Space**: O(n) - two stacks

## Why This Works

- Stack is LIFO, queue is FIFO
- Transferring reverses order
- Output stack maintains queue order
- Amortized efficient
