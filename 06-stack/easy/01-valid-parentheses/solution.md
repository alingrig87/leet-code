# Solution Explanation: Valid Parentheses

## Approach: Stack

### Intuition
Use stack to track opening brackets. When closing bracket found, check if it matches top of stack.

### Algorithm
1. Create stack
2. For each character:
   - If opening bracket: push to stack
   - If closing bracket:
     - If stack empty or doesn't match: return false
     - Else: pop from stack
3. Return true if stack empty

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(n) - stack

## Why Stack Works

- Last opened bracket must close first (LIFO)
- Stack naturally handles nested brackets
- Efficient and intuitive solution
