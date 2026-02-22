# Solution Explanation: Minimum Size Subarray Sum

## Approach: Sliding Window

### Intuition
Use variable-size sliding window. Expand by moving right, shrink from left when sum >= target to find minimum length.

### Algorithm
1. Initialize left = 0, sum = 0, minLen = Integer.MAX_VALUE
2. Expand window (right pointer):
   - Add nums[right] to sum
   - While sum >= target:
     - Update minLen = min(minLen, right - left + 1)
     - Subtract nums[left] and move left
3. Return minLen (or 0 if no valid)

### Complexity
- **Time**: O(n) - each element visited twice
- **Space**: O(1) - only pointers

## Why This Works

- Expand to include more elements
- Shrink when sum sufficient to find minimum
- Each element added once, removed once
- Optimal O(n) solution
