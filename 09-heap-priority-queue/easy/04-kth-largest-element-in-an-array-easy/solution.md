# Solution Explanation: Kth Largest Element in an Array

## Approach: Min Heap

### Intuition
Use min heap of size k. Maintain only k largest elements. Top is kth largest.

### Algorithm
1. Create min heap
2. For each element:
   - If heap size < k: add element
   - Else if element > heap top: replace top
3. Return heap top

### Complexity
- **Time**: O(n log k) - n insertions
- **Space**: O(k) - heap size

## Why Min Heap

- Keeps k largest elements
- Smallest of those is kth largest
- Efficient when k is small
