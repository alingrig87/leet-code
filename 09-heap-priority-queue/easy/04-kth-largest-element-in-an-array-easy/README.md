# Kth Largest Element in an Array (Easy)

## Problem Statement
Given an integer array `nums` and an integer `k`, return the `k`th largest element in the array.

Note that it is the `k`th largest element in the sorted order, not the `k`th distinct element.

**Example 1:**
```
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
```

## Theory & Data Structures

### Min Heap
Use min heap of size k. Maintain k largest elements. Top is kth largest.

### QuickSelect
Use QuickSelect algorithm for O(n) average case.

### Time & Space Complexity

#### Approach: Min Heap
- **Time Complexity**: O(n log k) - n insertions
- **Space Complexity**: O(k) - Heap size

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find kth largest element."

**Candidate**: "I'll use a min heap of size k. Add elements, maintain only k largest. Top of heap is kth largest."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n log k) time and O(k) space."

### Follow-up Questions

**Interviewer**: "Can you do better?"

**Candidate**: "QuickSelect gives O(n) average case, but O(n²) worst case. Heap is more reliable."

### Tricky Edge Cases

1. **k = 1**: Return maximum
2. **k = n**: Return minimum
3. **All same**: Return that value
4. **Duplicates**: Handle correctly

## Solution Approaches

### Approach: Min Heap (Optimal for small k)
Maintain min heap of size k. O(n log k) time, O(k) space.

## Key Takeaways

1. **Min heap** for kth largest
2. **Maintain size k**
3. **Top is answer**
4. **Efficient for small k**
