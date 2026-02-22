# Solution Explanation: Insert Interval

## Approach: Three Phases

### Intuition
Add intervals before newInterval, merge newInterval with overlapping intervals, add intervals after.

### Algorithm
1. Add intervals that end before newInterval starts
2. Merge intervals that overlap with newInterval
3. Add intervals that start after newInterval ends

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(n) - result

## Why This Works

- Three phases handle all cases
- Merging updates newInterval bounds
- Efficient O(n) solution
