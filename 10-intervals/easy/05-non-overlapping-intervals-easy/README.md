# Non-overlapping Intervals

## Problem Statement
Given an array of intervals `intervals` where `intervals[i] = [starti, endi]`, return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

**Example 1:**
```
Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
```

**Example 2:**
```
Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest non-overlapping.
```

**Example 3:**
```
Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
```

## Theory & Data Structures

### Greedy Algorithm - Interval Scheduling
This is a classic **interval scheduling** problem. The optimal solution uses a greedy approach known as the **"Earliest Finish Time"** algorithm.

### Why Greedy Works
The key insight is that if we always choose the interval that ends earliest, we maximize the remaining time available for other intervals. This greedy choice is proven to be optimal.

#### Mathematical Proof (Intuitive)
- Suppose we have an optimal solution that doesn't use the earliest-finishing interval
- We can always replace the first interval in that solution with the earliest-finishing one
- This replacement doesn't reduce the number of intervals we can keep
- Therefore, the greedy solution is optimal

### Sorting in Java
Java's `Arrays.sort()` uses **Dual-Pivot Quicksort** for primitives and **Timsort** (adaptive merge sort) for objects:
- **Average case**: O(n log n)
- **Worst case**: O(n log n) for Timsort, O(n²) for Quicksort
- **Space**: O(1) for primitives, O(n) for objects

### Time & Space Complexity

#### Approach: Greedy with End-Time Sorting
- **Time Complexity**: O(n log n) - Sorting dominates, then O(n) for single pass
- **Space Complexity**: O(1) - Only using variables (excluding input/output)

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given intervals, find the minimum number to remove so remaining don't overlap."

**Candidate**: "This is an interval scheduling problem. I'll use a greedy approach - sort intervals by end time, then keep intervals that don't overlap with the last kept interval."

**Interviewer**: "Why sort by end time specifically?"

**Candidate**: "The greedy strategy is to always keep the interval that ends earliest. This maximizes the remaining time for other intervals. If I sort by end time, I can process them in this optimal order."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For [[1,2],[2,3],[3,4],[1,3]], after sorting by end: [[1,2],[2,3],[1,3],[3,4]]. I keep [1,2] (ends at 2). [2,3] starts at 2, which is >= 2, so no overlap - keep it. [1,3] starts at 1, which is < 3, so overlap - remove it. [3,4] starts at 3, which is >= 3, so keep it. Total removed: 1."

### Follow-up Questions

**Interviewer**: "What if we sort by start time instead?"

**Candidate**: "That would be less optimal. If we sort by start time, we might keep an interval that starts early but ends late, blocking many other intervals. The end-time sorting ensures we always free up time as early as possible."

**Interviewer**: "What's the time complexity?"

**Candidate**: "O(n log n) for sorting, then O(n) for the single pass through intervals, so overall O(n log n). Space is O(1) excluding input/output."

**Interviewer**: "What if intervals have different priorities or weights?"

**Candidate**: "Then it becomes a weighted interval scheduling problem, which requires dynamic programming. We'd need to consider all possibilities, not just greedy choices. That would be O(n²) or O(n log n) with binary search optimization."

**Interviewer**: "What about edge cases?"

**Candidate**: "Empty array returns 0. Single interval returns 0. If all intervals overlap, we remove all but one. Adjacent intervals (end == start) don't count as overlapping, so we can keep both."

### Tricky Edge Cases

1. **Empty array**: `[]` → Return 0 (nothing to remove)
2. **Single interval**: `[[1,2]]` → Return 0 (no overlaps possible)
3. **No overlaps**: `[[1,2],[3,4]]` → Return 0 (already non-overlapping)
4. **All overlap**: `[[1,3],[2,4],[3,5]]` → Return 2 (keep only one)
5. **Adjacent intervals**: `[[1,2],[2,3]]` → Return 0 (end == start is not overlap)
6. **Nested intervals**: `[[1,5],[2,3]]` → Remove outer one `[1,5]` (greedy keeps `[2,3]`)
7. **Duplicate intervals**: `[[1,2],[1,2],[1,2]]` → Return 2 (keep only one)
8. **Same start, different ends**: `[[1,3],[1,2]]` → After sorting by end: keep `[1,2]`, remove `[1,3]`

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "First, I'll handle edge cases - if array is null or has ≤1 interval, return 0. Then I sort by end time using a comparator that compares the end values (index 1). I initialize lastEnd with the first interval's end - we always keep the first one. Then I iterate through remaining intervals. For each, if it starts before lastEnd, there's an overlap, so I increment the removal count. Otherwise, I update lastEnd to this interval's end since we're keeping it."

**Interviewer**: "Why do you always keep the first interval?"

**Candidate**: "After sorting by end time, the first interval ends earliest. By the greedy principle, we should keep it. Also, we need at least one interval to start with, so keeping the first one is the right choice."

## Solution Approaches

### Approach 1: Greedy with End-Time Sorting (Optimal)
Sort intervals by end time. Keep intervals that don't overlap with the last kept interval. O(n log n) time, O(1) space.

**Why this is optimal**: The earliest-finish-time algorithm is proven to maximize the number of intervals we can keep.

### Approach 2: Dynamic Programming (For Weighted Version)
If intervals have weights, use DP to maximize total weight. O(n²) or O(n log n) with optimization.

### Approach 3: Graph-based (Overkill)
Build conflict graph, find minimum vertex cover. Much more complex, not needed for this problem.

## Key Takeaways

1. **Greedy algorithm** is optimal for unweighted interval scheduling
2. **Sort by end time** to implement earliest-finish-time strategy
3. **Overlap condition**: `currentStart < lastEnd` means overlap
4. **Adjacent intervals** (end == start) are NOT overlapping
5. **O(n log n) time** is optimal for comparison-based sorting
6. **Classic problem** that appears in many variations
