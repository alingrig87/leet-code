# Solution Explanation: Remove Duplicates from Sorted Array

## Approach: Two Pointers

### Intuition
Since the array is sorted, all duplicates are adjacent. We can use two pointers:
- Slow pointer: Position where next unique element should be placed
- Fast pointer: Scans through array to find next unique element

### Algorithm
1. Start slow pointer at index 1 (first element is always unique)
2. Fast pointer starts at index 1
3. For each element at fast pointer:
   - If it's different from previous element (nums[fast] != nums[fast-1]):
     - Place it at slow pointer position
     - Increment slow pointer
   - Increment fast pointer
4. Return slow pointer (number of unique elements)

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only variables

## Why This Works

- Array is sorted, so duplicates are adjacent
- We only need to compare with the previous element
- Slow pointer maintains the "result array" in-place
- Fast pointer finds the next unique element
