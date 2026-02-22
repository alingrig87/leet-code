# Insert Interval (Easy)

## Problem Statement
You are given an array of non-overlapping intervals `intervals` where `intervals[i] = [starti, endi]` represent the start and the end of the `i`th interval and `intervals` is sorted in ascending order by `starti`. You are also given an interval `newInterval = [start, end]` that represents the start and end of another interval.

Insert `newInterval` into `intervals` such that `intervals` is still sorted in ascending order by `starti` and `intervals` still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return `intervals` after the insertion.

**Example 1:**
```
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
```

## Theory & Data Structures

### Three Phases
1. Add intervals before newInterval
2. Merge newInterval with overlapping intervals
3. Add intervals after newInterval

### Time & Space Complexity

#### Approach: Three Phases
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(n) - Result array

## Interview Simulation

### Initial Discussion

**Interviewer**: "Insert interval into sorted non-overlapping intervals."

**Candidate**: "I'll do three phases: add intervals before newInterval, merge newInterval with any overlapping intervals, then add intervals after."

**Interviewer**: "How do you merge?"

**Candidate**: "While intervals overlap with newInterval, update newInterval's start and end to encompass them."

### Follow-up Questions

**Interviewer**: "What if intervals aren't sorted?"

**Candidate**: "Then we'd need to sort first, making it O(n log n) time."

### Tricky Edge Cases

1. **No overlaps**: Insert at correct position
2. **Multiple overlaps**: Merge all
3. **Insert at start**: Handle correctly
4. **Insert at end**: Handle correctly

## Solution Approaches

### Approach: Three Phases (Optimal)
Add before, merge overlapping, add after. O(n) time, O(n) space.

## Key Takeaways

1. **Three phases** approach
2. **Merge overlapping** intervals
3. **O(n) time** for sorted intervals
4. **Clean implementation**
