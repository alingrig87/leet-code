# Solution Explanation: Move Zeroes

## Approach 1: Two Pointers with Fill

### Intuition
First pass: move all non-zero elements to the front. Second pass: fill remaining positions with zeros.

### Algorithm
1. Use write pointer starting at 0
2. Iterate through array
3. For each non-zero element: place at write pointer, increment write pointer
4. After first pass, fill remaining positions with zeros

### Complexity
- **Time**: O(n) - two passes
- **Space**: O(1)

## Approach 2: Two Pointers with Swap

### Intuition
Swap non-zero elements to front as we encounter them.

### Algorithm
1. Write pointer tracks next position for non-zero
2. When we find non-zero, swap with write position
3. Increment both pointers

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1)

## Why Swap is Better

- Single pass instead of two
- Fewer operations (no need to fill zeros explicitly)
- More efficient overall
