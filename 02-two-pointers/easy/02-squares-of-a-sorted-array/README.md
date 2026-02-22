# Squares of a Sorted Array

## Problem Statement
Given an integer array `nums` sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

**Example 1:**
```
Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
```

**Example 2:**
```
Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]
```

## Theory & Data Structures

### Two Pointers from Ends
Since array is sorted, largest squares are at the ends (most negative or most positive). Use two pointers from both ends, compare squares, place larger square at end of result.

### Time & Space Complexity

#### Approach: Two Pointers
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(n) - Result array

## Interview Simulation

### Initial Discussion

**Interviewer**: "Square each number and return sorted array."

**Candidate**: "Since the array is sorted, the largest squares will be at the ends - either the most negative numbers or the most positive. I'll use two pointers from both ends, compare squares, and place the larger square at the end of the result array, working backwards."

**Interviewer**: "Why work backwards?"

**Candidate**: "Because we want the largest squares first in the result, and we're finding them from the ends. By filling from the end, we naturally get a sorted result."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n) time and O(n) space for the result array."

### Follow-up Questions

**Interviewer**: "What if array isn't sorted?"

**Candidate**: "Then we'd need to square all elements and sort, which is O(n log n) time."

**Interviewer**: "Can you do it in-place?"

**Candidate**: "Not easily, because squares can be larger than original numbers, and we need space for the sorted result."

### Tricky Edge Cases

1. **All negative**: `[-3,-2,-1]` → `[1,4,9]`
2. **All positive**: `[1,2,3]` → `[1,4,9]`
3. **Mixed with zero**: `[-2,0,3]` → `[0,4,9]`
4. **Single element**: `[5]` → `[25]`

## Solution Approaches

### Approach: Two Pointers from Ends (Optimal)
Compare squares from ends, place larger at end of result. O(n) time, O(n) space.

## Key Takeaways

1. **Sorted array** enables two-pointer approach
2. **Largest squares at ends** is key insight
3. **Fill result backwards** for natural sorting
4. **O(n) solution** possible with sorted input
