# Remove Duplicates from Sorted Array

## Problem Statement
Given an integer array `nums` sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array `nums`. More formally, if there are `k` elements after removing the duplicates, then the first `k` elements of `nums` should hold the final result.

Return `k` after placing the final result in the first `k` slots of `nums`.

**Example 1:**
```
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
```

**Example 2:**
```
Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
```

## Theory & Data Structures

### Two Pointers Technique
This problem uses the **two pointers** technique where we maintain:
- **Slow pointer**: Points to the last unique element in the result array
- **Fast pointer**: Iterates through the entire array

### In-Place Modification
Since the array is sorted, duplicates are adjacent. We can modify the array in-place without needing extra space.

### Time & Space Complexity

#### Approach: Two Pointers
- **Time Complexity**: O(n) - Single pass through array
- **Space Complexity**: O(1) - Only using a few variables

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given a sorted array, remove duplicates in-place and return the new length."

**Candidate**: "Since the array is sorted, duplicates will be adjacent. I'll use two pointers - one to track the position where unique elements should go, and another to iterate through the array."

**Interviewer**: "Walk me through your approach."

**Candidate**: "I'll start with both pointers at index 1. The slow pointer marks where the next unique element should go. The fast pointer scans ahead. When I find a new unique element, I place it at the slow pointer position and increment both pointers."

### Follow-up Questions

**Interviewer**: "What if the array isn't sorted?"

**Candidate**: "Then we'd need a HashSet to track seen elements, which would require O(n) extra space. Or we could sort first, but that changes the time complexity to O(n log n)."

**Interviewer**: "Can you do this with O(1) space if array isn't sorted?"

**Candidate**: "Not efficiently. We'd need to check each element against all previous elements, giving O(n²) time complexity."

### Tricky Edge Cases

1. **Empty Array**: `[]` → Return 0
2. **Single Element**: `[1]` → Return 1
3. **All Same**: `[1,1,1,1]` → Return 1
4. **No Duplicates**: `[1,2,3,4]` → Return 4
5. **Negative Numbers**: `[-1,-1,0,1]` → Works fine

## Solution Approaches

### Approach: Two Pointers (Optimal)
Use slow pointer for result position, fast pointer to scan. O(n) time, O(1) space.

## Key Takeaways

1. **Sorted array enables O(1) space** solution
2. **Two pointers** is powerful for in-place modifications
3. **Slow pointer** tracks result, **fast pointer** finds next unique element
