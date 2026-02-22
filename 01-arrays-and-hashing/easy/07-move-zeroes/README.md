# Move Zeroes

## Problem Statement
Given an integer array `nums`, move all `0`'s to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

**Example 1:**
```
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
```

**Example 2:**
```
Input: nums = [0]
Output: [0]
```

## Theory & Data Structures

### Two Pointers Technique
Use two pointers:
- **Write pointer**: Position for next non-zero element
- **Read pointer**: Scans through array

### In-Place Modification
Move non-zero elements to front, then fill remaining positions with zeros.

### Time & Space Complexity

#### Approach: Two Pointers
- **Time Complexity**: O(n) - Two passes at most
- **Space Complexity**: O(1) - Only variables

## Interview Simulation

### Initial Discussion

**Interviewer**: "Move all zeros to the end while keeping non-zero order."

**Candidate**: "I'll use two pointers. One to track where non-zero elements should go, another to scan. I'll move all non-zeros to the front, then fill the rest with zeros."

**Interviewer**: "Can you do it in one pass?"

**Candidate**: "Yes, I can use a swap approach. When I find a non-zero, I swap it with the write pointer position, then increment both pointers."

### Follow-up Questions

**Interviewer**: "What if we need to preserve zeros' relative order too?"

**Candidate**: "If zeros need to maintain order among themselves, we'd need a stable sort or a different approach that tracks zero positions."

**Interviewer**: "What's the minimum number of operations?"

**Candidate**: "The two-pointer approach minimizes operations - we only write when necessary and do one final pass to add zeros."

### Tricky Edge Cases

1. **All zeros**: `[0,0,0]` → `[0,0,0]`
2. **No zeros**: `[1,2,3]` → `[1,2,3]`
3. **Zeros at start**: `[0,0,1,2]` → `[1,2,0,0]`
4. **Zeros at end**: `[1,2,0,0]` → `[1,2,0,0]` (already correct)

## Solution Approaches

### Approach 1: Two Pointers with Fill
Move non-zeros to front, fill rest with zeros. O(n) time, O(1) space.

### Approach 2: Two Pointers with Swap
Swap non-zeros to front. O(n) time, O(1) space, fewer operations.

## Key Takeaways

1. **Two pointers** for in-place array rearrangement
2. **Separate non-zeros first**, then handle zeros
3. **Swap approach** can be more efficient
