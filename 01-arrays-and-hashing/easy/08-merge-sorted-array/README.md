# Merge Sorted Array

## Problem Statement
You are given two integer arrays `nums1` and `nums2`, sorted in non-decreasing order, and two integers `m` and `n`, representing the number of elements in `nums1` and `nums2` respectively.

Merge `nums2` into `nums1` in sorted order. The final sorted array should not be returned by the function, but instead be stored inside the array `nums1`. To accommodate this, `nums1` has a length of `m + n`, where the first `m` elements denote the elements that should be merged, and the last `n` elements are set to `0` and should be ignored. `nums2` has a length of `n`.

**Example 1:**
```
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
```

**Example 2:**
```
Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
```

## Theory & Data Structures

### Two Pointers from End
Since `nums1` has extra space at the end, we can merge from right to left (largest to smallest) without overwriting unprocessed elements.

### Time & Space Complexity

#### Approach: Two Pointers from End
- **Time Complexity**: O(m + n) - Single pass
- **Space Complexity**: O(1) - No extra space needed

## Interview Simulation

### Initial Discussion

**Interviewer**: "Merge two sorted arrays into the first one in-place."

**Candidate**: "Since nums1 has extra space at the end, I'll merge from right to left. I'll compare the largest elements from both arrays and place the larger one at the end of nums1, working backwards."

**Interviewer**: "Why from the end?"

**Candidate**: "If we merge from the start, we'd overwrite elements in nums1 before we've processed them. Starting from the end uses the extra space first, so we never overwrite unprocessed data."

### Follow-up Questions

**Interviewer**: "What if nums1 doesn't have extra space?"

**Candidate**: "Then we'd need to create a new array or use O(m) extra space to store nums1's elements temporarily."

**Interviewer**: "What's the time complexity?"

**Candidate**: "O(m + n) since we process each element exactly once."

### Tricky Edge Cases

1. **nums2 is empty**: `nums1=[1,2,3], m=3, nums2=[], n=0` → No change
2. **nums1 is empty**: `nums1=[0,0], m=0, nums2=[1,2], n=2` → Copy nums2
3. **All nums2 larger**: `nums1=[1,2,0,0], m=2, nums2=[3,4], n=2` → Append nums2
4. **All nums2 smaller**: `nums1=[3,4,0,0], m=2, nums2=[1,2], n=2` → Prepend nums2

## Solution Approaches

### Approach: Two Pointers from End (Optimal)
Merge from right to left, using extra space at end. O(m+n) time, O(1) space.

## Key Takeaways

1. **Merge from end** to avoid overwriting
2. **Use extra space** in nums1 efficiently
3. **Handle remaining elements** after main loop
