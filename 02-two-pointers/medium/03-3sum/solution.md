# Solution Explanation: 3Sum

## Approach: Sort + Two Pointers

### Intuition
Sort array. For each element, use two pointers to find pairs that sum to negative of that element.

### Algorithm
1. Sort array
2. For each index i:
   - Use two pointers left = i+1, right = n-1
   - Find pairs where nums[left] + nums[right] == -nums[i]
   - Skip duplicates after finding valid triplet
3. Return all unique triplets

### Complexity
- **Time**: O(n²) - Sort O(n log n) + nested loops
- **Space**: O(1) - excluding output

## Why This Works

- Sorting enables two-pointer technique
- Fixing one element reduces to two-sum problem
- Duplicate skipping ensures uniqueness
- Efficient O(n²) solution
