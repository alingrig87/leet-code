# Solution Explanation: Can Place Flowers

## Approach: Greedy Placement

### Intuition
Plant flowers as early as possible when a valid spot is found. Check current position and neighbors are empty.

### Algorithm
1. For each position i:
   - Check if flowerbed[i] is 0 (empty)
   - Check if left neighbor (i-1) is empty or doesn't exist
   - Check if right neighbor (i+1) is empty or doesn't exist
   - If all conditions met: plant flower, increment count
2. Return count >= n

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only variables

## Why Greedy Works

- Planting early maximizes remaining spots
- Greedy choice is optimal
- Simple and efficient
