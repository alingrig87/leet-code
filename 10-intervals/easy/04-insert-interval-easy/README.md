# Insert Interval

## Problem Statement
You are given an array of non-overlapping intervals `intervals` where `intervals[i] = [starti, endi]` represent the start and the end of the `i`th interval and `intervals` is sorted in ascending order by `starti`. You are also given an interval `newInterval = [start, end]` that represents the start and end of another interval.

Insert `newInterval` into `intervals` such that `intervals` is still sorted in ascending order by `starti` and `intervals` still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return `intervals` after the insertion.

**Example 1:**
```
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Explanation: [2,5] overlaps with [1,3], so merge to [1,5].
```

**Example 2:**
```
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: [4,8] overlaps with [3,5] and [6,7], so merge all to [3,10].
```

## Theory & Data Structures

### Three Phases Approach
This problem uses a **three-phase approach** to insert and merge intervals efficiently.

#### Key Insight: Three Phases
- **Phase 1**: Add all intervals that end before newInterval starts
- **Phase 2**: Merge newInterval with all overlapping intervals
- **Phase 3**: Add all intervals that start after merged interval ends

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of insert interval
class InsertInterval {
    
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        
        // Phase 1: Add intervals before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        
        // Phase 2: Merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            // Merge: update newInterval to encompass current
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);
        
        // Phase 3: Add intervals after merged interval
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }
        
        return result.toArray(new int[result.size()][]);
    }
    
    // Why three phases?
    // - Intervals are sorted, so we can process in order
    // - Phase 1: intervals that definitely don't overlap
    // - Phase 2: intervals that might overlap, merge them
    // - Phase 3: intervals that definitely don't overlap
    // - This avoids checking all pairs
}
```

### Time & Space Complexity

#### Approach: Three Phases
- **Time Complexity**: O(n) - Single pass through intervals
  - Each interval processed once
  - O(1) work per interval
- **Space Complexity**: O(n) - Result array
  - In worst case, no merges, need O(n) space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Insert an interval into sorted non-overlapping intervals, merging if necessary."

**Candidate**: "I'll use a three-phase approach. First, I'll add all intervals that end before the new interval starts. Then, I'll merge the new interval with any overlapping intervals by updating the new interval's start and end. Finally, I'll add all intervals that start after the merged interval ends."

**Interviewer**: "How do you determine overlap?"

**Candidate**: "An interval overlaps with newInterval if its start is <= newInterval's end. When merging, I update newInterval's start to the minimum of both starts, and end to the maximum of both ends."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For intervals=[[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval=[4,8]. Phase 1: [1,2] ends before 4, add it. Phase 2: [3,5] overlaps (3<=8), merge to [3,8]. [6,7] overlaps (6<=8), merge to [3,8]. [8,10] overlaps (8<=8), merge to [3,10]. Phase 3: [12,16] starts after 10, add it. Result: [[1,2],[3,10],[12,16]]."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n) since we make a single pass through the intervals. Space complexity is O(n) for the result array."

### Follow-up Questions

**Interviewer**: "What if intervals aren't sorted?"

**Candidate**: "Then we'd need to sort first, making it O(n log n) time. Or we could use the merge intervals approach - sort everything together, then merge."

**Interviewer**: "What if we need to insert multiple intervals?"

**Candidate**: "We could insert them one by one, but that would be O(n*m) where m is number of intervals to insert. Better to merge all new intervals with existing ones using the merge intervals algorithm."

### Tricky Edge Cases

1. **No overlaps**: Insert at correct position
2. **Multiple overlaps**: Merge all overlapping intervals
3. **Insert at start**: Handle correctly
4. **Insert at end**: Handle correctly
5. **Empty intervals**: Handle correctly
6. **newInterval encompasses all**: Return single interval

## Solution Approaches

### Approach: Three Phases (Optimal)
Add before, merge overlapping, add after. O(n) time, O(n) space.

**Algorithm:**
1. Phase 1: Add intervals ending before newInterval starts
2. Phase 2: Merge newInterval with overlapping intervals
3. Phase 3: Add intervals starting after merged interval
4. Return result

**Advantages:**
- O(n) time complexity
- Simple and efficient
- Optimal for sorted intervals

## Key Takeaways

1. **Three phases** approach
2. **Merge overlapping** intervals
3. **O(n) time** for sorted intervals
4. **Clean implementation**
5. **Foundation for** interval manipulation problems
