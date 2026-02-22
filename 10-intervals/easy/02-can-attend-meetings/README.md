# Can Attend Meetings

## Problem Statement
Given an array of meeting time intervals `intervals` where `intervals[i] = [starti, endi]`, determine if a person could attend all meetings.

**Example 1:**
```
Input: intervals = [[0,30],[5,10],[15,20]]
Output: false
Explanation: Meetings [0,30] and [5,10] overlap, so person cannot attend all.
```

**Example 2:**
```
Input: intervals = [[7,10],[2,4]]
Output: true
Explanation: No overlaps, person can attend all.
```

## Theory & Data Structures

### Sorting Approach
This problem uses **sorting** to check for overlaps. After sorting intervals by start time, we only need to check if any interval overlaps with the next one.

#### Key Insight: Check Adjacent Intervals
- **Sort by start time**: Ensures we process intervals in chronological order
- **Check overlap**: If interval[i].start < interval[i-1].end, there's overlap
- **Adjacent is OK**: If end == start, no overlap (person can move between meetings)
- **Efficiency**: After sorting, only need one pass

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of can attend meetings
class CanAttendMeetings {
    
    public boolean canAttendMeetings(int[][] intervals) {
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // Check if any interval overlaps with next
        for (int i = 1; i < intervals.length; i++) {
            // If current start < previous end, there's overlap
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        
        return true;
    }
    
    // Why sort by start time?
    // - After sorting, if interval i doesn't overlap with i-1,
    //   it won't overlap with any earlier interval
    // - We only need to check adjacent intervals
    // - This reduces complexity from O(n²) to O(n log n)
    
    // Alternative: Check all pairs (O(n²))
    public boolean canAttendMeetingsBruteForce(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (overlaps(intervals[i], intervals[j])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean overlaps(int[] a, int[] b) {
        // Two intervals overlap if: a.start < b.end && b.start < a.end
        return a[0] < b[1] && b[0] < a[1];
    }
}
```

### Time & Space Complexity

#### Approach: Sorting
- **Time Complexity**: O(n log n) - Sorting dominates
  - Sorting: O(n log n)
  - Checking: O(n)
  - Total: O(n log n)
- **Space Complexity**: O(1) - Excluding input
  - Only variables for loop
  - O(1) if sorting in-place

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if a person can attend all meetings."

**Candidate**: "I'll sort the intervals by start time. Then I'll check if any interval's start is before the previous interval's end. If so, there's an overlap and the person can't attend all meetings."

**Interviewer**: "Why does sorting help?"

**Candidate**: "After sorting, if an interval doesn't overlap with the previous one, it won't overlap with any earlier interval. This means we only need to check adjacent intervals, making it O(n) after sorting instead of O(n²) for checking all pairs."

**Interviewer**: "What about adjacent meetings where one ends exactly when the next starts?"

**Candidate**: "That's fine - if end == start, there's no overlap. The person can finish one meeting and immediately start the next."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n log n) for sorting, then O(n) for checking, so overall O(n log n). Space complexity is O(1) if we sort in-place."

### Follow-up Questions

**Interviewer**: "What if we need to find the minimum number of rooms needed?"

**Candidate**: "Then we'd use a different approach - either a min heap to track end times of ongoing meetings, or a sweep line algorithm that processes start and end events. The complexity would still be O(n log n)."

**Interviewer**: "What if meetings can be rescheduled?"

**Candidate**: "Then it becomes a more complex optimization problem - we'd need to find if there's a way to reschedule meetings so all can be attended, which might involve graph coloring or other techniques."

### Tricky Edge Cases

1. **No overlaps**: Return true
2. **All overlap**: Return false
3. **Adjacent meetings**: Return true (end == start is OK)
4. **Single meeting**: Return true
5. **Empty intervals**: Return true
6. **Nested intervals**: Handle correctly

## Solution Approaches

### Approach: Sorting (Optimal)
Sort by start, check overlaps. O(n log n) time, O(1) space.

**Algorithm:**
1. Sort intervals by start time
2. For i from 1 to n-1:
   - If intervals[i].start < intervals[i-1].end: return false
3. Return true

**Advantages:**
- O(n log n) time complexity
- Simple and efficient
- Optimal solution

## Key Takeaways

1. **Sort by start time** to simplify checking
2. **Check overlaps** with next interval only
3. **O(n log n)** solution
4. **Simple and efficient**
5. **Foundation for** interval problems
6. **Same as Meeting Rooms** problem
