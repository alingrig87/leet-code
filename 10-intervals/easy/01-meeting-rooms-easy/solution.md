# Solution Explanation: Meeting Rooms (Easy)

## Approach: Sorting

### Intuition
Sort intervals by start time. Check if any interval overlaps with next (start < previous end).

### Algorithm
1. Sort intervals by start time
2. For each interval (except first):
   - If start < previous end: overlap, return false
3. Return true if no overlaps

### Complexity
- **Time**: O(n log n) - sorting
- **Space**: O(1) - excluding input

## Why This Works

- Sorting by start time orders meetings
- Overlap means start < previous end
- Checking adjacent intervals is sufficient
