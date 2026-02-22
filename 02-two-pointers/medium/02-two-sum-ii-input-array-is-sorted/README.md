# Two Sum II - Input Array Is Sorted

## Problem Statement
Given a 1-indexed array of integers `numbers` that is already sorted in non-decreasing order, find two numbers such that they add up to a specific `target` number. Let these two numbers be `numbers[index1]` and `numbers[index2]` where `1 <= index1 < index2 <= numbers.length`.

Return the indices of the two numbers, `index1` and `index2`, added by one as an integer array `[index1, index2]` of length 2.

**Example 1:**
```
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
```

## Theory & Data Structures

### Two Pointers Technique
Since array is sorted, use two pointers from both ends. If sum is too small, move left pointer right. If too large, move right pointer left.

### Time & Space Complexity

#### Approach: Two Pointers
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Only pointers

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find two numbers that sum to target in sorted array."

**Candidate**: "Since array is sorted, I'll use two pointers from both ends. If sum is less than target, move left pointer right. If greater, move right pointer left."

**Interviewer**: "Why does this work?"

**Candidate**: "Sorted array means moving left pointer right increases sum, moving right pointer left decreases sum. This allows us to converge to the target."

### Follow-up Questions

**Interviewer**: "What if array isn't sorted?"

**Candidate**: "Then we'd use HashMap approach like regular Two Sum, O(n) time and O(n) space."

### Tricky Edge Cases

1. **No solution**: Array doesn't contain pair
2. **Same element twice**: Not allowed (index1 < index2)
3. **Negative numbers**: Works fine with sorted array

## Solution Approaches

### Approach: Two Pointers (Optimal for sorted)
Two pointers from ends, adjust based on sum. O(n) time, O(1) space.

## Key Takeaways

1. **Sorted array** enables two-pointer approach
2. **O(1) space** vs O(n) for HashMap
3. **Convergence property** of sorted arrays
4. **Efficient single pass**
