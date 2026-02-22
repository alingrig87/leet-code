# Minimum Size Subarray Sum

## Problem Statement
Given an array of positive integers `nums` and a positive integer `target`, return the minimal length of a subarray whose sum is greater than or equal to `target`. If there is no such subarray, return `0`.

**Example 1:**
```
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
```

## Theory & Data Structures

### Sliding Window (Variable Size)
Use two pointers. Expand window by moving right pointer, shrink by moving left pointer when sum >= target.

### Time & Space Complexity

#### Approach: Sliding Window
- **Time Complexity**: O(n) - Each element visited at most twice
- **Space Complexity**: O(1) - Only pointers

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find minimum length subarray with sum >= target."

**Candidate**: "I'll use sliding window. Expand by moving right pointer, when sum >= target, shrink from left to find minimum length."

**Interviewer**: "Why sliding window?"

**Candidate**: "We need to find optimal subarray. Sliding window allows us to try all possibilities efficiently in O(n) time."

### Follow-up Questions

**Interviewer**: "What if array has negative numbers?"

**Candidate**: "Then we'd need different approach, maybe prefix sums or dynamic programming, as sliding window doesn't work with negatives."

### Tricky Edge Cases

1. **No valid subarray**: Return 0
2. **Single element**: Check if >= target
3. **All elements needed**: Return array length
4. **Target at start**: Shortest possible

## Solution Approaches

### Approach: Sliding Window (Optimal)
Variable-size window, expand and shrink. O(n) time, O(1) space.

## Key Takeaways

1. **Sliding window** for subarray problems
2. **Expand and shrink** to find optimal
3. **O(n) time** - each element visited twice
4. **Efficient solution**
