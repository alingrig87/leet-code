# Solution Explanation: Palindrome Linked List

## Approach: Reverse Second Half

### Intuition
Find middle, reverse second half, compare with first half.

### Algorithm
1. Find middle using slow/fast pointers
2. Reverse second half starting from middle
3. Compare first half with reversed second half
4. (Optional) Restore original list

### Complexity
- **Time**: O(n) - find middle, reverse, compare
- **Space**: O(1) - only pointers

## Why This Works

- Palindrome means first half equals reversed second half
- Reversing second half allows comparison
- O(1) space by modifying in-place
- Efficient solution
