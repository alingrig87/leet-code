# 3Sum

## Problem Statement
Given an integer array `nums`, return all the triplets `[nums[i], nums[j], nums[k]]` such that `i != j`, `i != k`, and `j != k`, and `nums[i] + nums[j] + nums[k] == 0`.

Notice that the solution set must not contain duplicate triplets.

**Example 1:**
```
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
```

## Theory & Data Structures

### Sort + Two Pointers
Sort array first. For each element, use two pointers to find pairs that sum to negative of that element.

### Time & Space Complexity

#### Approach: Sort + Two Pointers
- **Time Complexity**: O(n²) - Sort O(n log n) + nested loops O(n²)
- **Space Complexity**: O(1) - Excluding output array

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find all unique triplets that sum to zero."

**Candidate**: "I'll sort the array first. For each element, I'll use two pointers to find pairs that sum to the negative of that element. I'll skip duplicates to avoid duplicate triplets."

**Interviewer**: "How do you avoid duplicates?"

**Candidate**: "After finding a valid triplet, skip all duplicate values for the current pointer positions before moving to next iteration."

### Follow-up Questions

**Interviewer**: "What if we need to find triplets summing to k instead of 0?"

**Candidate**: "Same approach, but look for pairs summing to k - nums[i]."

### Tricky Edge Cases

1. **No solution**: No triplets sum to zero
2. **All zeros**: `[0,0,0]` → One triplet
3. **Duplicates**: Need careful skipping
4. **Negative and positive**: Need to handle both

## Solution Approaches

### Approach: Sort + Two Pointers
Sort, then for each element use two pointers. O(n²) time, O(1) space.

## Key Takeaways

1. **Sorting enables two pointers**
2. **Skip duplicates** carefully
3. **Fix one element**, find pairs for remaining
4. **O(n²) solution** optimal
