# Solution Explanation: Plus One

## Approach: Reverse Iteration with Carry

### Intuition
Add one to the last digit. If it becomes 10, set to 0 and carry 1. Continue leftward. If all digits were 9, create new array.

### Algorithm
1. Start from rightmost digit
2. Add 1 to current digit
3. If digit < 10: return array (no carry)
4. If digit == 10: set to 0, carry 1 to left
5. If we reach leftmost and still have carry: create new array [1,0,0,...,0]

### Complexity
- **Time**: O(n) worst case, O(1) best case (no carry)
- **Space**: O(1) if no expansion, O(n) if new array needed

## Why This Works

- We process from least significant to most significant
- Carry propagates naturally from right to left
- Early exit when no carry needed (optimization)
- New array only needed when all digits were 9
