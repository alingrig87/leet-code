# Solution Explanation: Merge Sorted Array

## Approach: Two Pointers from End

### Intuition
Since nums1 has extra space at the end, merge from right to left. Compare largest elements and place at end, working backwards.

### Algorithm
1. Three pointers: i = m-1 (end of nums1 data), j = n-1 (end of nums2), k = m+n-1 (end of nums1)
2. While both arrays have elements:
   - Compare nums1[i] and nums2[j]
   - Place larger at nums1[k]
   - Decrement appropriate pointers
3. Copy remaining elements from nums2 if any

### Complexity
- **Time**: O(m + n) - process each element once
- **Space**: O(1) - no extra space

## Why This Works

- Merging from end uses extra space first
- Never overwrites unprocessed elements
- Handles all edge cases naturally
- Efficient single-pass solution
