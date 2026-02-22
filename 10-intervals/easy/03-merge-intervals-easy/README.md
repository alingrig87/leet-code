# Merge Intervals (Easy)

## Problem Statement
Given an array of intervals where `intervals[i] = [starti, endi]`, merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

**Example 1:**
```
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
```

## Theory & Data Structures

### Sorting + Merge
Sort by start time. For each interval, if it overlaps with last merged, merge them. Otherwise, add as new.

### Time & Space Complexity

#### Approach: Sorting + Merge
- **Time Complexity**: O(n log n) - Sorting
- **Space Complexity**: O(n) - Result array

## Interview Simulation

### Initial Discussion

**Interviewer**: "Merge overlapping intervals."

**Candidate**: "I'll sort by start time. Then iterate through intervals. If current overlaps with last merged (start <= last end), merge by updating last end. Otherwise, add as new interval."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n log n) time for sorting, O(n) space for result."

### Follow-up Questions

**Interviewer**: "What if intervals are already sorted?"

**Candidate**: "Then it's O(n) time - just one pass to merge."

### Tricky Edge Cases

1. **No overlaps**: Return all intervals
2. **All overlap**: Return single merged interval
3. **Adjacent intervals**: Merge them
4. **Empty array**: Return empty

## Solution Approaches

### Approach: Sorting + Merge (Optimal)
Sort by start, merge overlapping. O(n log n) time, O(n) space.

## Key Takeaways

1. **Sort by start time**
2. **Merge if overlapping**
3. **Update end** when merging
4. **O(n log n)** solution
