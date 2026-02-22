# Solution Explanation: Reverse String

## Approach: Two Pointers

### Intuition
Use two pointers from both ends. Swap characters, then move pointers towards center.

### Algorithm
1. Initialize left = 0, right = n-1
2. While left < right:
   - Swap s[left] and s[right]
   - Increment left, decrement right
3. Array is reversed

### Complexity
- **Time**: O(n) - n/2 swaps
- **Space**: O(1) - only temporary variable

## Why This Works

- Swapping from both ends naturally reverses
- Process continues until pointers meet
- Each character moved exactly once
- In-place modification
