# Sqrt(x)

## Problem Statement
Given a non-negative integer `x`, return the square root of `x` rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

**Example 1:**
```
Input: x = 4
Output: 2
```

**Example 2:**
```
Input: x = 8
Output: 2
```

## Theory & Data Structures

### Binary Search
Search space is [0, x]. Use binary search to find largest integer whose square <= x.

### Time & Space Complexity

#### Approach: Binary Search
- **Time Complexity**: O(log x) - Binary search
- **Space Complexity**: O(1) - Iterative

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find square root without built-in functions."

**Candidate**: "I'll use binary search in range [0, x]. Find largest integer whose square <= x. If mid*mid <= x, try larger values. Otherwise, try smaller."

**Interviewer**: "What about overflow?"

**Candidate**: "I'll use long for mid*mid calculation to avoid integer overflow, then cast back to int."

### Follow-up Questions

**Interviewer**: "What if we need decimal precision?"

**Candidate**: "We'd use binary search with decimal precision, checking if mid*mid is close enough to x within epsilon."

### Tricky Edge Cases

1. **x = 0**: Return 0
2. **x = 1**: Return 1
3. **Perfect square**: Return exact root
4. **Large x**: Handle overflow with long

## Solution Approaches

### Approach: Binary Search (Optimal)
Binary search in [0, x]. O(log x) time, O(1) space.

## Key Takeaways

1. **Binary search** for square root
2. **Handle overflow** with long
3. **Find largest** valid value
4. **O(log x)** solution
