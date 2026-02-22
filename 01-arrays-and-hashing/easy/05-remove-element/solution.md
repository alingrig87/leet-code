# Solution Explanation: Remove Element

## Approach: Two Pointers

### Intuition
Use two pointers to separate valid elements (not equal to val) from invalid ones. Write valid elements to the front of the array.

### Algorithm
1. Initialize write pointer at index 0
2. Iterate through array with read pointer
3. For each element:
   - If element != val: write it at write pointer, increment write pointer
   - If element == val: skip it (don't write)
4. Return write pointer (count of valid elements)

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - in-place

## Why This Works

- Write pointer always points to next position for valid element
- We only write elements that are not equal to val
- Final array has all valid elements at the front
- Write pointer value equals count of valid elements
