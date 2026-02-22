# Solution Explanation: Isomorphic Strings

## Approach: Two HashMaps

### Intuition
Use two HashMaps to track mapping from s to t and t to s. Check consistency at each step.

### Algorithm
1. If lengths differ, return false
2. Create two HashMaps
3. For each character pair:
   - Check if mapping exists and conflicts
   - If yes, return false
   - Otherwise, add mapping
4. Return true

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - fixed size maps

## Why Two Maps

- Need bidirectional mapping
- Ensures one-to-one correspondence
- Prevents multiple characters mapping to same
