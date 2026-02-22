# Solution Explanation: First Bad Version

## Approach: Binary Search

### Intuition
Since all versions after first bad are bad, use binary search. If mid is bad, first bad is at mid or left. If good, first bad is to the right.

### Algorithm
1. Initialize left = 1, right = n
2. While left < right:
   - mid = left + (right - left) / 2
   - If isBadVersion(mid): right = mid
   - Else: left = mid + 1
3. Return left (first bad version)

### Complexity
- **Time**: O(log n) - binary search
- **Space**: O(1) - iterative

## Why left < right

- When left == right, we've found first bad
- right = mid (not mid - 1) because mid might be first bad
- left = mid + 1 because mid is confirmed good
