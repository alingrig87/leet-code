# Solution Explanation: Search Insert Position

## Approach: Binary Search

### Intuition
Use binary search. If target found, return index. If not, left pointer points to insertion position.

### Algorithm
1. Initialize left = 0, right = n-1
2. While left <= right:
   - mid = left + (right - left) / 2
   - If nums[mid] == target: return mid
   - If nums[mid] < target: left = mid + 1
   - Else: right = mid - 1
3. Return left (insertion position)

### Complexity
- **Time**: O(log n) - binary search
- **Space**: O(1) - iterative

## Why Left is Insertion Position

- When target not found, left ends at first position >= target
- This is exactly where target should be inserted
- Maintains sorted order
