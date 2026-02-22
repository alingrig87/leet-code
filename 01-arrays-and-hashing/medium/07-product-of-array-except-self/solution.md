# Solution Explanation: Product of Array Except Self

## Approach: Two Pass (Prefix and Suffix)

### Intuition
For each position i, result = (product of all elements to the left) × (product of all elements to the right).

### Algorithm
1. First pass (left to right):
   - Calculate prefix products, store in output array
   - For position i, output[i] = product of nums[0] to nums[i-1]
2. Second pass (right to left):
   - Calculate suffix product as we go
   - Multiply output[i] by suffix product
   - Update suffix product for next iteration

### Complexity
- **Time**: O(n) - two passes
- **Space**: O(1) - only output array (not counting input/output)

## Why This Works

- Prefix products give us left side products
- Suffix products give us right side products
- Multiplying them gives product except self
- Using output array for prefix saves space
