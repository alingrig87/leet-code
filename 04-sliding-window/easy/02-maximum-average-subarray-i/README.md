# Maximum Average Subarray I

## Problem Statement
You are given an integer array `nums` consisting of `n` elements, and an integer `k`.

Find a contiguous subarray whose length is equal to `k` that has the maximum average value and return this value.

**Example 1:**
```
Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
```

## Theory & Data Structures

### Sliding Window
Use fixed-size sliding window of length k. Calculate sum of first window, then slide and update sum.

### Time & Space Complexity

#### Approach: Sliding Window
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Only variables

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find maximum average of subarray of length k."

**Candidate**: "I'll use sliding window. Calculate sum of first k elements, then slide window by subtracting left element and adding right element. Track maximum sum, then divide by k."

**Interviewer**: "Why sliding window?"

**Candidate**: "Instead of recalculating sum for each window (O(n*k)), sliding window reuses previous sum (O(n))."

### Follow-up Questions

**Interviewer**: "What if k is very large?"

**Candidate**: "Sliding window still works efficiently, just one pass through array."

### Tricky Edge Cases

1. **k equals array length**: Return average of all elements
2. **All negative**: Still find maximum average
3. **k = 1**: Return maximum element

## Solution Approaches

### Approach: Sliding Window (Optimal)
Fixed-size window, update sum by sliding. O(n) time, O(1) space.

## Key Takeaways

1. **Sliding window** for fixed-size subarrays
2. **Reuse previous sum** for efficiency
3. **O(n) time** instead of O(n*k)
4. **Simple and efficient**
