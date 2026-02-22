# Merge Intervals

## Problem Statement
Given an array of intervals where `intervals[i] = [starti, endi]`, merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

**Example 1:**
```
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
```

**Example 2:**
```
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
```

## Theory & Data Structures

### Sorting + Merge
This problem uses **sorting** followed by a **merge pass**. After sorting by start time, we can merge overlapping intervals in a single pass.

#### Key Insight: Merge Adjacent Overlapping Intervals
- **Sort by start time**: Ensures intervals are in order
- **Check overlap**: If current.start <= last.end, they overlap
- **Merge**: Update last.end = max(last.end, current.end)
- **Add new**: If no overlap, add current as new interval

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of merge intervals
class MergeIntervals {
    
    public int[][] merge(int[][] intervals) {
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        List<int[]> merged = new ArrayList<>();
        
        for (int[] interval : intervals) {
            // If list is empty or no overlap, add interval
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                // Merge: update end time
                int[] last = merged.get(merged.size() - 1);
                last[1] = Math.max(last[1], interval[1]);
            }
        }
        
        return merged.toArray(new int[merged.size()][]);
    }
    
    // Why sort first?
    // - After sorting, if interval i doesn't overlap with i-1,
    //   it won't overlap with any earlier interval
    // - We only need to check and merge with the last merged interval
    // - This makes merging O(n) after O(n log n) sort
    
    // Alternative: Without sorting (O(n²))
    // Would need to check all pairs, much less efficient
}
```

### Time & Space Complexity

#### Approach: Sorting + Merge
- **Time Complexity**: O(n log n) - Sorting dominates
  - Sorting: O(n log n)
  - Merging: O(n)
  - Total: O(n log n)
- **Space Complexity**: O(n) - Result array
  - In worst case, no merges, need O(n) space
  - Excluding input, O(n) for result

## Interview Simulation

### Initial Discussion

**Interviewer**: "Merge all overlapping intervals."

**Candidate**: "I'll sort the intervals by start time. Then I'll iterate through them. For each interval, if it overlaps with the last merged interval (current.start <= last.end), I'll merge by updating the last interval's end to the maximum of the two ends. Otherwise, I'll add it as a new interval."

**Interviewer**: "Why does sorting help?"

**Candidate**: "After sorting, if an interval doesn't overlap with the previous one, it won't overlap with any earlier interval. This means I only need to check and merge with the last merged interval, making the merge pass O(n) instead of O(n²)."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For [[1,3],[2,6],[8,10],[15,18]], after sorting: [[1,3],[2,6],[8,10],[15,18]]. Process [1,3]: add to result. Process [2,6]: overlaps with [1,3] (2<=3), merge to [1,6]. Process [8,10]: no overlap (8>6), add [8,10]. Process [15,18]: no overlap, add [15,18]. Result: [[1,6],[8,10],[15,18]]."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n log n) for sorting, then O(n) for merging, so overall O(n log n). Space complexity is O(n) for the result array."

### Follow-up Questions

**Interviewer**: "What if intervals are already sorted?"

**Candidate**: "Then it's O(n) time - just one pass to merge, no sorting needed."

**Interviewer**: "What if we need to merge intervals from multiple sources?"

**Candidate**: "We'd still sort all intervals together, then merge. The algorithm remains the same."

**Interviewer**: "What if intervals can be very large?"

**Candidate**: "The algorithm still works efficiently. We might need to consider integer overflow if start/end values are very large, but the logic remains the same."

### Tricky Edge Cases

1. **No overlaps**: Return all intervals (possibly merged if adjacent)
2. **All overlap**: Return single merged interval
3. **Adjacent intervals**: Merge them (end == start is overlap)
4. **Empty array**: Return empty array
5. **Single interval**: Return as is
6. **Nested intervals**: Handle correctly (merge to outer)

## Solution Approaches

### Approach: Sorting + Merge (Optimal)
Sort by start, merge overlapping. O(n log n) time, O(n) space.

**Algorithm:**
1. Sort intervals by start time
2. Initialize result list
3. For each interval:
   - If no overlap with last: add as new
   - Else: merge by updating last.end
4. Return result

**Advantages:**
- O(n log n) time complexity
- Simple and efficient
- Optimal solution

## Key Takeaways

1. **Sort by start time** first
2. **Merge if overlapping** (current.start <= last.end)
3. **Update end** when merging (max of both ends)
4. **O(n log n)** solution
5. **Foundation for** interval problems
6. **Classic problem** - important to know
