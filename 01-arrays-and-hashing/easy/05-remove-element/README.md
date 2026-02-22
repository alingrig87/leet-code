# Remove Element

## Problem Statement
Given an integer array `nums` and an integer `val`, remove all occurrences of `val` in-place. The order of the elements may be changed. Then return the number of elements in `nums` which are not equal to `val`.

Consider the number of elements in `nums` which are not equal to `val` be `k`, to get accepted, you need to do the following things:
- Change the array `nums` such that the first `k` elements of `nums` contain the elements which are not equal to `val`.
- Return `k`.

**Example 1:**
```
Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
```

**Example 2:**
```
Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
```

## Theory & Data Structures

### Two Pointers Technique
Similar to remove duplicates, we use two pointers:
- **Write pointer**: Position where next valid element should be written
- **Read pointer**: Scans through array

### In-Place Modification
We modify the array in-place, moving valid elements to the front.

### Time & Space Complexity

#### Approach: Two Pointers
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Only variables

## Interview Simulation

### Initial Discussion

**Interviewer**: "Remove all occurrences of a value from an array in-place."

**Candidate**: "I'll use two pointers. One to track where valid elements should go, another to scan through the array. When I find an element that's not equal to val, I place it at the write position."

**Interviewer**: "What's the time complexity?"

**Candidate**: "O(n) time and O(1) space since we're modifying in-place."

### Follow-up Questions

**Interviewer**: "What if we need to preserve order?"

**Candidate**: "Same approach works - we just write elements in the order we encounter them, which preserves their relative order."

**Interviewer**: "What if val appears many times?"

**Candidate**: "The algorithm handles this efficiently - we simply skip those elements and only write non-val elements."

### Tricky Edge Cases

1. **All elements are val**: `[3,3,3], val=3` → Return 0
2. **No elements are val**: `[1,2,3], val=4` → Return 3
3. **Empty array**: `[], val=1` → Return 0
4. **Val at boundaries**: `[1,2,1], val=1` → Return 1

## Solution Approaches

### Approach: Two Pointers (Optimal)
Write pointer tracks result position, read pointer scans. O(n) time, O(1) space.

## Key Takeaways

1. **Two pointers** for in-place array modification
2. **Write pointer** maintains result array
3. **Skip unwanted elements** by not writing them
