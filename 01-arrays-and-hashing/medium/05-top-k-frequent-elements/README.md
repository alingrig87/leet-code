# Top K Frequent Elements

## Problem Statement
Given an integer array `nums` and an integer `k`, return the `k` most frequent elements. You may return the answer in any order.

**Example 1:**
```
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
```

**Example 2:**
```
Input: nums = [1], k = 1
Output: [1]
```

## Theory & Data Structures

### HashMap for Frequency Counting
Count frequency of each element using HashMap.

### Priority Queue (Min Heap)
Use min heap of size k to maintain top k frequent elements. When heap size exceeds k, remove the least frequent.

### Bucket Sort
Since frequencies are bounded by array length, we can use bucket sort - array of lists indexed by frequency.

### Time & Space Complexity

#### Approach 1: HashMap + Priority Queue
- **Time Complexity**: O(n log k) - n insertions into heap of size k
- **Space Complexity**: O(n) - HashMap and heap

#### Approach 2: HashMap + Bucket Sort
- **Time Complexity**: O(n) - counting and bucket traversal
- **Space Complexity**: O(n) - buckets and HashMap

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find the k most frequent elements."

**Candidate**: "I'll count frequencies using HashMap, then use a min heap of size k. For each element, if heap has less than k elements, add it. Otherwise, if current frequency is greater than minimum in heap, replace minimum."

**Interviewer**: "What's the time complexity?"

**Candidate**: "O(n log k) - we process n elements, each heap operation is O(log k)."

**Interviewer**: "Can you do better?"

**Candidate**: "Yes, we can use bucket sort. Since frequencies are at most n, we can create buckets indexed by frequency. This gives O(n) time."

### Follow-up Questions

**Interviewer**: "What if k is very large, close to n?"

**Candidate**: "Then we could use max heap and extract k elements, which is O(n log n). Or we could use QuickSelect for O(n) average case."

**Interviewer**: "What about space constraints?"

**Candidate**: "Bucket sort uses O(n) space. If space is tight, we might need to use the heap approach or process in chunks."

### Tricky Edge Cases

1. **k equals array length**: All elements are top k
2. **All elements same frequency**: Any k elements
3. **k = 1**: Return most frequent
4. **Ties in frequency**: Problem says any order is fine

## Solution Approaches

### Approach 1: HashMap + Min Heap
Count frequencies, use min heap of size k. O(n log k) time, O(n) space.

### Approach 2: HashMap + Bucket Sort (Optimal)
Count frequencies, use buckets indexed by frequency. O(n) time, O(n) space.

## Key Takeaways

1. **HashMap for counting** is standard
2. **Heap for top k** when k is small
3. **Bucket sort** when frequencies are bounded
4. **Trade-off** between time and implementation complexity
