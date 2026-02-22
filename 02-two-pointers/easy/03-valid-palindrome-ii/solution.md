# Solution Explanation: Valid Palindrome II

## Approach: Two Pointers with Helper

### Intuition
Use two pointers. When mismatch found, try deleting left or right character, then check if remaining substring is palindrome.

### Algorithm
1. Use two pointers from ends
2. While left < right:
   - If characters match, continue
   - If mismatch:
     - Try deleting left: check if s[left+1...right] is palindrome
     - Try deleting right: check if s[left...right-1] is palindrome
     - Return true if either works
3. Return true if no mismatches

### Complexity
- **Time**: O(n) - at most two passes
- **Space**: O(1) - only pointers

## Why This Works

- At most one deletion allowed
- When mismatch found, must delete one of the mismatched characters
- Check both possibilities
- Helper method checks if substring is palindrome
