# Find K Closest Elements

## Problem Statement
Given a sorted integer array `arr`, two integers `k` and `x`, return the `k` closest integers to `x` in the array. The result should also be sorted in ascending order.

An integer `a` is closer to `x` than an integer `b` if:
- `|a - x| < |b - x|`, or
- `|a - x| == |b - x|` and `a < b`

**Example 1:**
```
Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
```

## Theory & Data Structures

### Binary Search + Two Pointers
Find position of x (or closest), use two pointers to expand and collect k closest elements.

### Time & Space Complexity

#### Approach: Binary Search + Two Pointers
- **Time Complexity**: O(log n + k) - Binary search + expansion
- **Space Complexity**: O(1) - Excluding result

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find k closest elements to x in sorted array."

**Candidate**: "I'll use binary search to find position of x, then use two pointers to expand left and right, collecting k closest elements based on distance."

**Interviewer**: "How do you decide which pointer to move?"

**Candidate**: "Compare distances. Move pointer with larger distance, or left pointer if distances are equal (prefer smaller value)."

### Follow-up Questions

**Interviewer**: "What if x is not in array?"

**Candidate**: "Binary search finds insertion position, then we expand from there."

### Tricky Edge Cases

1. **x at start**: Take first k elements
2. **x at end**: Take last k elements
3. **x in middle**: Expand both sides
4. **k equals array length**: Return all

## Solution Approaches

### Approach: Binary Search + Two Pointers (Optimal)
Find x position, expand to collect k closest. O(log n + k) time, O(1) space.

## Key Takeaways

1. **Binary search** to find x position
2. **Two pointers** to expand
3. **Compare distances** to decide direction
4. **O(log n + k)** efficient solution
