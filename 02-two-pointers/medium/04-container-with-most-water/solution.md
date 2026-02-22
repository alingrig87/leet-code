# Solution Explanation: Container With Most Water

## Approach: Two Pointers

### Intuition
Use two pointers from ends. Always move pointer with smaller height, as moving larger one can only decrease area.

### Algorithm
1. Initialize left = 0, right = n-1
2. While left < right:
   - Calculate area = min(height[left], height[right]) * (right - left)
   - Update maximum area
   - Move pointer with smaller height
3. Return maximum area

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only pointers

## Why This Works

- Area limited by smaller height
- Moving larger pointer reduces width, can't increase height
- Moving smaller pointer might find larger height
- Greedy choice is optimal
