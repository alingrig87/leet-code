# Solution Explanation: Valid Palindrome

## Approach: Two Pointers

### Intuition
Use two pointers from both ends. Skip non-alphanumeric characters, convert to lowercase, compare.

### Algorithm
1. Initialize left = 0, right = n-1
2. While left < right:
   - Skip non-alphanumeric from left
   - Skip non-alphanumeric from right
   - Compare lowercase characters
   - If different, return false
3. Return true

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only pointers

## Why This Works

- Two pointers naturally check palindrome
- Skipping handles cleaning requirement
- Case conversion handles case insensitivity
