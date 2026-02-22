# Max Consecutive Ones

## Problem Statement
Given a binary array `nums`, return the maximum number of consecutive `1`s in the array.

**Example 1:**
```
Input: nums = [1,1,0,1,1,1]
Output: 3
```

## Theory & Data Structures

### Simple Traversal
Count consecutive ones, reset on zero, track maximum.

### Time & Space Complexity

#### Approach: One Pass
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Only variables

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find maximum consecutive ones."

**Candidate**: "I'll traverse array, count consecutive ones, reset count on zero, track maximum count."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n) time and O(1) space - single pass with constant space."

### Follow-up Questions

**Interviewer**: "What if we can flip k zeros to ones?"

**Candidate**: "Then we'd use sliding window to find longest subarray with at most k zeros."

### Tricky Edge Cases

1. **All ones**: Return array length
2. **All zeros**: Return 0
3. **Single one**: Return 1
4. **Empty array**: Return 0

## Solution Approaches

### Approach: One Pass (Optimal)
Count consecutive ones, reset on zero. O(n) time, O(1) space.

## Key Takeaways

1. **Simple traversal** with counter
2. **Reset on zero**, track maximum
3. **O(n) time, O(1) space**
4. **Straightforward solution**
