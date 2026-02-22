# Solution Explanation: Valid Palindrome

## Approach: Two Pointers

### Intuition
Use two pointers from both ends. Skip non-alphanumeric characters, convert to lowercase, and compare. Move pointers towards center.

### Algorithm
1. Initialize left = 0, right = s.length() - 1
2. While left < right:
   - Skip non-alphanumeric from left
   - Skip non-alphanumeric from right
   - Compare lowercase characters
   - If different, return false
   - Move both pointers towards center
3. Return true if all comparisons passed

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only pointers

## Why This Works

- Two pointers naturally check palindrome property
- Skipping non-alphanumeric handles the cleaning requirement
- Case conversion handles case insensitivity
- O(1) space by processing in-place
