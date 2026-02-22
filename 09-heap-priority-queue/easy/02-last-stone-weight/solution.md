# Solution Explanation: Last Stone Weight

## Approach: Max Heap

### Intuition
Use max heap to always get two heaviest stones. Smash them, add result back if non-zero.

### Algorithm
1. Build max heap from stones
2. While heap has at least 2 stones:
   - Extract two largest
   - Calculate difference
   - If non-zero, add back to heap
3. Return remaining stone weight or 0

### Complexity
- **Time**: O(n log n) - heap operations
- **Space**: O(n) - heap

## Why Max Heap

- Need to repeatedly get two largest
- Max heap provides O(log n) extraction
- Efficient for this problem
