# Solution Explanation: Non-overlapping Intervals

## Approach: Greedy

### Intuition
Sort by end time. Keep intervals with earlier end times, remove overlapping ones.

### Algorithm
1. Sort intervals by end time
2. Initialize end = -infinity, count = 0
3. For each interval:
   - If start >= end: keep it, update end
   - Else: remove it (increment count)
4. Return count

### Complexity
- **Time**: O(n log n) - sorting
- **Space**: O(1) - only variables

## Why Sort by End Time

- Greedy choice: earlier end leaves more room
- Maximizes number of intervals we can keep
- Optimal solution
