# Solution Explanation: Binary Search

## Approach: Binary Search

### Intuition
Divide search space in half at each step. Compare target with middle element, eliminate half of remaining elements.

### Algorithm
1. Initialize left = 0, right = n-1
2. While left <= right:
   - Calculate mid = left + (right - left) / 2
   - If nums[mid] == target: return mid
   - If nums[mid] < target: left = mid + 1
   - If nums[mid] > target: right = mid - 1
3. Return -1 if not found

### Complexity
- **Time**: O(log n) - halves search space each step
- **Space**: O(1) - iterative, O(log n) - recursive

## Why This Works

- Sorted array enables elimination of half elements
- Each step reduces search space by half
- Logarithmic number of steps needed
- Optimal for sorted arrays
