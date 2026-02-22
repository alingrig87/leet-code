# Solution Explanation: Valid Perfect Square

## Approach: Binary Search

### Intuition
Use binary search to find if there exists an integer whose square equals num.

### Algorithm
1. Initialize left = 1, right = num
2. While left <= right:
   - mid = left + (right - left) / 2
   - If mid*mid == num: return true
   - If mid*mid < num: left = mid + 1
   - Else: right = mid - 1
3. Return false

### Complexity
- **Time**: O(log num) - binary search
- **Space**: O(1) - iterative

## Why This Works

- Binary search finds integer square root if it exists
- If found and square equals num, it's perfect square
- Otherwise, not a perfect square
