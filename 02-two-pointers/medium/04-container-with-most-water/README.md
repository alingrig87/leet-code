# Container With Most Water

## Problem Statement
You are given an integer array `height` of length `n`. There are `n` vertical lines drawn such that the two endpoints of the `i`th line are `(i, 0)` and `(i, height[i])`.

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

**Example 1:**
```
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
```

## Theory & Data Structures

### Two Pointers with Greedy
Use two pointers from ends. Always move the pointer with smaller height, as moving the larger one can only decrease area.

### Time & Space Complexity

#### Approach: Two Pointers
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Only pointers

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find two lines that form container with most water."

**Candidate**: "I'll use two pointers from ends. Calculate area at each step. Always move the pointer with smaller height, since moving the larger one can only decrease area."

**Interviewer**: "Why move the smaller one?"

**Candidate**: "Area is limited by the smaller height. Moving the larger pointer reduces width but can't increase height, so area can only decrease. Moving smaller pointer might find larger height."

### Follow-up Questions

**Interviewer**: "What if we need to find all containers above a threshold?"

**Candidate**: "We'd need to check all pairs or use a different approach."

### Tricky Edge Cases

1. **All same height**: Area = height * width
2. **Increasing heights**: One end optimal
3. **Decreasing then increasing**: Need to check middle

## Solution Approaches

### Approach: Two Pointers (Optimal)
Two pointers from ends, move smaller height pointer. O(n) time, O(1) space.

## Key Takeaways

1. **Greedy approach** - always move smaller pointer
2. **Area calculation** - min(height) * width
3. **O(n) solution** possible
4. **Key insight** - moving larger pointer can't help
