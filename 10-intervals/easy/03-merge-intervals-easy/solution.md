# Solution Explanation: Merge Intervals

## Approach: Sorting + Merge

### Intuition
Sort by start time. For each interval, if it overlaps with last merged, merge. Otherwise, add as new.

### Algorithm
1. Sort intervals by start time
2. Initialize result with first interval
3. For each remaining interval:
   - If overlaps with last (start <= last end): merge
   - Else: add as new
4. Return result

### Complexity
- **Time**: O(n log n) - sorting
- **Space**: O(n) - result

## Why This Works

- Sorting ensures intervals in order
- Overlap means start <= previous end
- Merging updates end to maximum
- Efficient O(n log n) solution
