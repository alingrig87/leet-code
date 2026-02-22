# Implement strStr()

## Problem Statement
Given two strings `needle` and `haystack`, return the index of the first occurrence of `needle` in `haystack`, or `-1` if `needle` is not part of `haystack`.

**Example 1:**
```
Input: haystack = "sadbutsad", needle = "sad"
Output: 0
```

## Theory & Data Structures

### Sliding Window
Use sliding window of needle length. Check if substring matches needle.

### KMP Algorithm
More efficient for repeated patterns, but sliding window is simpler for this problem.

### Time & Space Complexity

#### Approach: Sliding Window
- **Time Complexity**: O(n * m) - n is haystack length, m is needle length
- **Space Complexity**: O(1) - Only variables

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find first occurrence of needle in haystack."

**Candidate**: "I'll use sliding window. For each position in haystack, check if substring of needle length matches needle."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n * m) worst case where we check each position and compare needle length."

### Follow-up Questions

**Interviewer**: "Can you optimize?"

**Candidate**: "KMP algorithm gives O(n + m) time, but is more complex. For this problem, sliding window is acceptable."

### Tricky Edge Cases

1. **Needle not found**: Return -1
2. **Empty needle**: Return 0
3. **Needle longer than haystack**: Return -1
4. **Multiple occurrences**: Return first

## Solution Approaches

### Approach: Sliding Window (Simple)
Check each position. O(n*m) time, O(1) space.

## Key Takeaways

1. **Sliding window** for substring search
2. **Check each position** in haystack
3. **Simple implementation**
4. **KMP** for optimization if needed
