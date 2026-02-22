# Sort Array by Increasing Frequency

## Problem Statement
Given an array of integers `nums`, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

**Example 1:**
```
Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
```

## Theory & Data Structures

### HashMap + Custom Sort
Count frequencies using HashMap, then sort by frequency (ascending) and value (descending for ties).

### Time & Space Complexity

#### Approach: HashMap + Sort
- **Time Complexity**: O(n log n) - Sorting
- **Space Complexity**: O(n) - HashMap and result

## Interview Simulation

### Initial Discussion

**Interviewer**: "Sort array by frequency, then by value for ties."

**Candidate**: "I'll count frequencies using HashMap, then sort array using custom comparator that compares frequencies first, then values in reverse order for ties."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n log n) time for sorting, O(n) space for HashMap."

### Follow-up Questions

**Interviewer**: "Can you do it without sorting?"

**Candidate**: "We could use bucket sort if frequencies are bounded, but general case needs sorting."

### Tricky Edge Cases

1. **All same frequency**: Sort by value descending
2. **All unique**: Sort by value descending
3. **Mixed frequencies**: Sort by frequency ascending
4. **Empty array**: Return empty

## Solution Approaches

### Approach: HashMap + Custom Sort (Optimal)
Count frequencies, sort with custom comparator. O(n log n) time, O(n) space.

## Key Takeaways

1. **HashMap** for frequency counting
2. **Custom comparator** for sorting
3. **Frequency first**, then value
4. **O(n log n)** solution
