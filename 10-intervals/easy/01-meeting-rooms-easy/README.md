# Meeting Rooms (Easy)

## Problem Statement
Given an array of meeting time intervals `intervals` where `intervals[i] = [starti, endi]`, determine if a person could attend all meetings.

**Example 1:**
```
Input: intervals = [[0,30],[5,10],[15,20]]
Output: false
```

## Theory & Data Structures

### Sorting
Sort intervals by start time. Check if any interval overlaps with next (start < previous end).

### Time & Space Complexity

#### Approach: Sorting
- **Time Complexity**: O(n log n) - Sorting
- **Space Complexity**: O(1) - Excluding input

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if person can attend all meetings."

**Candidate**: "I'll sort intervals by start time. Then check if any interval's start is before previous interval's end. If yes, there's overlap and person can't attend all."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n log n) time for sorting, O(1) space."

### Follow-up Questions

**Interviewer**: "What if we need minimum rooms?"

**Candidate**: "Then we'd use min heap to track end times, or use sweep line algorithm."

### Tricky Edge Cases

1. **No overlaps**: Return true
2. **All overlap**: Return false
3. **Adjacent meetings**: Return true (end == start is OK)
4. **Single meeting**: Return true

## Solution Approaches

### Approach: Sorting (Optimal)
Sort by start, check overlaps. O(n log n) time, O(1) space.

## Key Takeaways

1. **Sort by start time**
2. **Check overlaps** with next interval
3. **O(n log n)** solution
4. **Simple and efficient**
