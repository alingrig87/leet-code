# Solution Explanation: Sqrt(x)

## Approach: Binary Search

### Intuition
Search space is [0, x]. Find largest integer whose square <= x using binary search.

### Algorithm
1. Initialize left = 0, right = x
2. While left <= right:
   - mid = left + (right - left) / 2
   - If mid*mid <= x: left = mid + 1 (try larger)
   - Else: right = mid - 1 (try smaller)
3. Return right (largest valid)

### Complexity
- **Time**: O(log x) - binary search
- **Space**: O(1) - iterative

## Why Return Right

- When loop ends, right is largest integer with square <= x
- left would be first integer with square > x
- right is the answer
