# Kth Largest Element in a Stream

## Problem Statement
Design a class to find the `k`th largest element in a stream. Note that it is the `k`th largest element in the sorted order, not the `k`th distinct element.

Implement `KthLargest` class:
- `KthLargest(int k, int[] nums)` Initializes the object with the integer `k` and the stream of integers `nums`.
- `int add(int val)` Appends the integer `val` to the stream and returns the element representing the `k`th largest element in the stream.

**Example 1:**
```
Input
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
Output
[null, 4, 5, 5, 8, 8]
```

## Theory & Data Structures

### Min Heap
Use min heap of size k. Maintain only k largest elements. Top of heap is kth largest.

### Time & Space Complexity

#### Approach: Min Heap
- **Time Complexity**: O(n log k) - n insertions into heap of size k
- **Space Complexity**: O(k) - Heap size

## Interview Simulation

### Initial Discussion

**Interviewer**: "Design class to find kth largest in stream."

**Candidate**: "I'll use a min heap of size k. When adding element, if heap size < k, add it. Otherwise, if element > heap top, replace top. Heap top is always kth largest."

**Interviewer**: "Why min heap?"

**Candidate**: "Min heap keeps k largest elements, with smallest of those at top, which is the kth largest."

### Follow-up Questions

**Interviewer**: "What if k is very large?"

**Candidate**: "Then heap operations become more expensive, but still O(log k) per operation."

### Tricky Edge Cases

1. **k = 1**: Return maximum element
2. **Stream smaller than k**: Handle initial state
3. **All same elements**: Handle correctly
4. **Decreasing stream**: Maintain heap correctly

## Solution Approaches

### Approach: Min Heap (Optimal)
Maintain min heap of size k. O(n log k) time, O(k) space.

## Key Takeaways

1. **Min heap** for kth largest
2. **Maintain size k** in heap
3. **Top is kth largest**
4. **Efficient for streams**
