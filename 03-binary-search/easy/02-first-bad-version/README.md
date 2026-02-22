# First Bad Version

## Problem Statement
You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all versions after a bad version are also bad.

Suppose you have `n` versions `[1, 2, ..., n]` and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API `bool isBadVersion(version)` which returns whether `version` is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

**Example 1:**
```
Input: n = 5, bad = 4
Output: 4
```

## Theory & Data Structures

### Binary Search
Since versions are sorted (all bad versions come after first bad), use binary search to find first bad version.

### Time & Space Complexity

#### Approach: Binary Search
- **Time Complexity**: O(log n) - Binary search
- **Space Complexity**: O(1) - Iterative

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find first bad version with minimal API calls."

**Candidate**: "Since all versions after first bad are bad, this is a sorted problem. I'll use binary search. If mid is bad, first bad is at mid or left. If mid is good, first bad is to the right."

**Interviewer**: "How do you handle the boundary?"

**Candidate**: "I'll use left < right instead of left <= right, and return left when loop ends, as it will point to first bad version."

### Follow-up Questions

**Interviewer**: "What if we need to find last good version?"

**Candidate**: "Similar approach, but adjust the condition - if mid is good, last good is at mid or right."

### Tricky Edge Cases

1. **First version is bad**: Return 1
2. **Last version is bad**: Return n
3. **All versions bad**: Return 1
4. **No bad versions**: Not possible per problem

## Solution Approaches

### Approach: Binary Search (Optimal)
Binary search to find first bad version. O(log n) time, O(1) space.

## Key Takeaways

1. **Binary search** for sorted problems
2. **Left < right** pattern for finding first occurrence
3. **Minimize API calls** with binary search
4. **Boundary handling** is crucial
