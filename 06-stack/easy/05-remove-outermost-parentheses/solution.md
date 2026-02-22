# Solution Explanation: Remove Outermost Parentheses

## Approach: Counter

### Intuition
Use counter to track depth. Skip parentheses when depth is 0 (outermost), include others.

### Algorithm
1. Initialize depth = 0, result = ""
2. For each character:
   - If '(': increment depth, add if depth > 1
   - If ')': add if depth > 1, decrement depth
3. Return result

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only counter

## Why This Works

- Depth 0 means outermost parentheses
- Skip those, include inner ones
- Counter tracks nesting level
- Efficient O(n) solution
