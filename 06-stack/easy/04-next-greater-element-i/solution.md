# Solution Explanation: Next Greater Element I

## Approach: Monotonic Stack

### Intuition
Use monotonic stack on nums2 to find next greater for all elements, store in HashMap, lookup for nums1.

### Algorithm
1. Create HashMap and stack
2. Process nums2 from right to left:
   - Pop smaller elements from stack
   - If stack empty, next greater is -1
   - Else, next greater is stack top
   - Push current element
   - Store in HashMap
3. Lookup for nums1 elements

### Complexity
- **Time**: O(n + m) - process both arrays
- **Space**: O(n) - stack and map

## Why Monotonic Stack

- Need to find first greater to the right
- Stack maintains decreasing order
- Efficient O(n) solution
