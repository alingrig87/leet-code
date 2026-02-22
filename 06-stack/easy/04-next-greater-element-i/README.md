# Next Greater Element I

## Problem Statement
The next greater element of some element `x` in an array is the first greater element that is to the right of `x` in the same array.

You are given two distinct 0-indexed integer arrays `nums1` and `nums2`, where `nums1` is a subset of `nums2`.

For each `0 <= i < nums1.length`, find the index `j` such that `nums1[i] == nums2[j]` and determine the next greater element of `nums2[j]` in `nums2`. If there is no next greater element, then the answer for this query is `-1`.

Return an array `ans` of length `nums1.length` such that `ans[i]` is the next greater element as described above.

**Example 1:**
```
Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
```

## Theory & Data Structures

### Monotonic Stack
Use stack to find next greater element. Process from right to left, maintain decreasing stack.

### Time & Space Complexity

#### Approach: Monotonic Stack
- **Time Complexity**: O(n + m) - Process both arrays
- **Space Complexity**: O(n) - Stack and HashMap

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find next greater element for each element in nums1."

**Candidate**: "I'll use a monotonic stack on nums2 to find next greater for all elements, store in HashMap, then lookup for nums1."

**Interviewer**: "How does monotonic stack work?"

**Candidate**: "Process from right to left. Maintain decreasing stack. For each element, pop smaller elements, then top is next greater. Push current element."

### Follow-up Questions

**Interviewer**: "What if we need next greater for all elements?"

**Candidate**: "Same approach, just process entire nums2 array."

### Tricky Edge Cases

1. **No greater element**: Return -1
2. **All decreasing**: All -1
3. **Single element**: Return -1
4. **Last element**: Return -1

## Solution Approaches

### Approach: Monotonic Stack (Optimal)
Use stack to find next greater, store in map. O(n+m) time, O(n) space.

## Key Takeaways

1. **Monotonic stack** for next greater
2. **Process right to left**
3. **Maintain decreasing** stack
4. **HashMap** for lookups
