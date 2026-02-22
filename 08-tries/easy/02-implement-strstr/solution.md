# Solution Explanation: Implement strStr()

## Approach: Sliding Window

### Intuition
Use sliding window of needle length. Check if substring matches needle at each position.

### Algorithm
1. If needle is empty, return 0
2. For each position i in haystack:
   - If remaining length < needle length, break
   - Check if substring starting at i matches needle
   - If match, return i
3. Return -1

### Complexity
- **Time**: O(n * m) - check each position
- **Space**: O(1) - only variables

## Why This Works

- Need to check all possible starting positions
- Sliding window checks each position
- Simple and straightforward
