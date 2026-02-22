# Search Insert Position

## Problem Statement
Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with `O(log n)` runtime complexity.

**Example 1:**
```
Input: nums = [1,3,5,6], target = 5
Output: 2
```

**Example 2:**
```
Input: nums = [1,3,5,6], target = 2
Output: 1
```

## Theory & Data Structures

### Binary Search
Use binary search to find insertion position. If target found, return index. If not, return left pointer position.

### Time & Space Complexity

#### Approach: Binary Search
- **Time Complexity**: O(log n) - Binary search
- **Space Complexity**: O(1) - Iterative

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find insertion position in sorted array."

**Candidate**: "I'll use binary search. If target found, return index. If not, the left pointer will point to insertion position when loop ends."

**Interviewer**: "Why left pointer?"

**Candidate**: "When target not found, left pointer ends up at first position where element is >= target, which is the insertion position."

### Follow-up Questions

**Interviewer**: "What if array has duplicates?"

**Candidate**: "If we want first occurrence, we'd adjust the algorithm to continue searching left when found."

### Tricky Edge Cases

1. **Target at start**: Return 0
2. **Target at end**: Return n
3. **Target smaller than all**: Return 0
4. **Target larger than all**: Return n

## Solution Approaches

### Approach: Binary Search (Optimal)
Binary search, return left when not found. O(log n) time, O(1) space.

## Key Takeaways

1. **Binary search** for sorted arrays
2. **Left pointer** gives insertion position
3. **O(log n)** solution
4. **Handle boundaries** correctly
