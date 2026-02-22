# Solution Explanation: Maximum Average Subarray I

## Approach: Sliding Window

### Intuition
Use fixed-size sliding window. Calculate sum of first window, then slide by subtracting left and adding right element.

### Algorithm
1. Calculate sum of first k elements
2. Initialize maxSum = current sum
3. Slide window from k to n-1:
   - Subtract nums[i-k], add nums[i]
   - Update maxSum
4. Return maxSum / k

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only variables

## Why This Works

- Fixed-size window means we can reuse sum
- Sliding avoids recalculating entire window
- Each element added/subtracted once
- Optimal O(n) solution
