# Solution Explanation: Find K Closest Elements

## Approach: Binary Search + Two Pointers

### Intuition
Find position of x using binary search, then use two pointers to expand and collect k closest elements.

### Algorithm
1. Binary search to find x position (or insertion point)
2. Initialize left and right pointers
3. While we need more elements:
   - Compare distances
   - Move pointer with larger distance (or left if equal)
   - Collect element
4. Sort and return result

### Complexity
- **Time**: O(log n + k) - search + expansion
- **Space**: O(1) - excluding result

## Why This Works

- Binary search finds starting position
- Two pointers efficiently expand
- Distance comparison ensures closest elements
- Efficient O(log n + k) solution
