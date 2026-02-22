# Solution Explanation: Squares of a Sorted Array

## Approach: Two Pointers from Ends

### Intuition
Since array is sorted, largest squares are at the ends. Use two pointers from both ends, compare squares, place larger at end of result.

### Algorithm
1. Create result array of same size
2. Two pointers: left = 0, right = n-1
3. Result pointer: resultIndex = n-1 (fill from end)
4. While left <= right:
   - Compare squares of nums[left] and nums[right]
   - Place larger square at result[resultIndex]
   - Move pointer of chosen element towards center
   - Decrement resultIndex
5. Return result

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(n) - result array

## Why This Works

- Sorted array means extremes are at ends
- Largest squares come from extremes
- Filling backwards naturally sorts result
- Each element processed exactly once
