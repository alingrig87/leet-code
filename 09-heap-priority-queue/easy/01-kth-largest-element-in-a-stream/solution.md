# Solution Explanation: Kth Largest Element in a Stream

## Approach: Min Heap

### Intuition
Use min heap of size k. Maintain only k largest elements. Top of heap is kth largest.

### Algorithm
1. Initialize: Add elements to heap, keep only k largest
2. Add operation:
   - If heap size < k: add element
   - Else if element > heap top: replace top
   - Return heap top

### Complexity
- **Time**: O(n log k) - n insertions
- **Space**: O(k) - heap size

## Why Min Heap

- Keeps k largest elements
- Smallest of those is at top
- That smallest is kth largest
- Efficient for streams
