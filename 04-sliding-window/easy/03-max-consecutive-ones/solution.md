# Solution Explanation: Max Consecutive Ones

## Approach: One Pass

### Intuition
Traverse array, count consecutive ones, reset count on zero, track maximum.

### Algorithm
1. Initialize count = 0, maxCount = 0
2. For each element:
   - If 1: increment count, update maxCount
   - If 0: reset count to 0
3. Return maxCount

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only variables

## Why This Works

- Count consecutive ones as we traverse
- Reset on zero breaks the streak
- Track maximum throughout
- Simple and efficient
