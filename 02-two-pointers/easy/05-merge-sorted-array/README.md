# Merge Sorted Array

## Problem Statement
You are given two integer arrays `nums1` and `nums2`, sorted in non-decreasing order, and two integers `m` and `n`, representing the number of elements in `nums1` and `nums2` respectively.

Merge `nums2` into `nums1` in sorted order. The final sorted array should not be returned by the function, but instead be stored inside the array `nums1`. To accommodate this, `nums1` has a length of `m + n`, where the first `m` elements denote the elements that should be merged, and the last `n` elements are set to `0` and should be ignored. `nums2` has a length of `n`.

**Example 1:**
```
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
```

## Theory & Data Structures

### Two Pointers from End
Since nums1 has extra space at end, merge from right to left (largest to smallest) without overwriting unprocessed elements.

### Time & Space Complexity

#### Approach: Two Pointers from End
- **Time Complexity**: O(m + n) - Single pass
- **Space Complexity**: O(1) - No extra space needed

## Interview Simulation

### Initial Discussion

**Interviewer**: "Merge two sorted arrays into the first one in-place."

**Candidate**: "Since nums1 has extra space at the end, I'll merge from right to left. Compare largest elements from both arrays, place larger at end of nums1, work backwards."

**Interviewer**: "Why from the end?"

**Candidate**: "If we merge from start, we'd overwrite elements in nums1 before processing them. Starting from end uses extra space first."

### Follow-up Questions

**Interviewer**: "What if nums1 doesn't have extra space?"

**Candidate**: "Then we'd need O(m) extra space to store nums1's elements temporarily."

### Tricky Edge Cases

1. **nums2 empty**: No change needed
2. **nums1 empty**: Copy nums2
3. **All nums2 larger**: Append nums2
4. **All nums2 smaller**: Prepend nums2

## Solution Approaches

### Approach: Two Pointers from End (Optimal)
Merge from right to left, using extra space at end. O(m+n) time, O(1) space.

## Key Takeaways

1. **Merge from end** to avoid overwriting
2. **Use extra space** efficiently
3. **Handle remaining elements** after main loop
