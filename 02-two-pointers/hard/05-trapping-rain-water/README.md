# Trapping Rain Water

## Problem Statement
Given `n` non-negative integers representing an elevation map where the width of each bar is `1`, compute how much water it can trap after raining.

**Example 1:**
```
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
```

## Theory & Data Structures

### Two Pointers Approach
Use two pointers from ends. Track maximum heights from left and right. Water trapped at position i = min(maxLeft, maxRight) - height[i].

### Time & Space Complexity

#### Approach: Two Pointers
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Only pointers and variables

## Interview Simulation

### Initial Discussion

**Interviewer**: "Calculate trapped rainwater."

**Candidate**: "I'll use two pointers from ends. Track maximum heights from left and right. At each position, water trapped is min(maxLeft, maxRight) minus current height, if positive."

**Interviewer**: "How do you know maxLeft and maxRight?"

**Candidate**: "As we move pointers, we update maxLeft and maxRight. We process the side with smaller maximum first, since that's the limiting factor."

### Follow-up Questions

**Interviewer**: "What if we need to return positions where water is trapped?"

**Candidate**: "We'd track positions along with water amounts."

### Tricky Edge Cases

1. **No trapping**: All increasing or decreasing
2. **Flat surface**: No water trapped
3. **Single peak**: Water on both sides

## Solution Approaches

### Approach: Two Pointers (Optimal)
Two pointers, track max heights, process smaller side. O(n) time, O(1) space.

## Key Takeaways

1. **Two pointers** from ends
2. **Track max heights** from both sides
3. **Process smaller side** first
4. **O(n) time, O(1) space**
