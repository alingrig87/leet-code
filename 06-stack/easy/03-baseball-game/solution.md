# Solution Explanation: Baseball Game

## Approach: Stack

### Intuition
Use stack to track scores. Apply operations: push integer, pop on 'C', double on 'D', sum on '+'.

### Algorithm
1. Create stack
2. For each operation:
   - Integer: push to stack
   - 'C': pop from stack
   - 'D': push 2 * top
   - '+': push sum of top two
3. Sum all elements in stack

### Complexity
- **Time**: O(n) - process each operation
- **Space**: O(n) - stack

## Why Stack Works

- Need to access previous scores
- Stack provides LIFO access
- Natural fit for this problem
