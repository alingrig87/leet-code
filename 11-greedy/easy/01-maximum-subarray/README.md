# Maximum Subarray (Kadane's Algorithm)

## Problem Statement
Given an integer array `nums`, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

A subarray is a contiguous part of an array.

**Example 1:**
```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
```

**Example 2:**
```
Input: nums = [1]
Output: 1
```

**Example 3:**
```
Input: nums = [5,4,-1,7,8]
Output: 23
```

## Theory & Data Structures

### Kadane's Algorithm
**Kadane's Algorithm** is a dynamic programming approach that solves the maximum subarray problem in O(n) time. It's based on the insight that at each position, we either:
1. Start a new subarray from current element
2. Extend the previous subarray

### How Kadane's Algorithm Works
The algorithm maintains two variables:
- **currentSum**: Maximum sum ending at current position
- **maxSum**: Maximum sum seen so far

At each position i:
- `currentSum = max(nums[i], currentSum + nums[i])`
  - If `currentSum + nums[i] < nums[i]`, it's better to start fresh
  - Otherwise, extend the previous subarray
- `maxSum = max(maxSum, currentSum)`

### Mathematical Foundation
The algorithm is based on the **optimal substructure** property:
- If we know the maximum sum ending at position i-1
- Then maximum sum ending at i is either:
  - The element itself (if previous sum was negative)
  - Previous sum + current element (if previous sum was positive)

### Time & Space Complexity

#### Approach: Kadane's Algorithm
- **Time Complexity**: O(n) - Single pass through array
- **Space Complexity**: O(1) - Only using two variables

#### Approach: Divide and Conquer
- **Time Complexity**: O(n log n) - Recursive division
- **Space Complexity**: O(log n) - Recursion stack

#### Approach: Brute Force
- **Time Complexity**: O(n²) - Check all subarrays
- **Space Complexity**: O(1) - Only variables

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find the maximum sum of a contiguous subarray."

**Candidate**: "I'll use Kadane's algorithm. The key insight is that at each position, we decide whether to start a new subarray or extend the previous one. We maintain the maximum sum ending at current position and the global maximum."

**Interviewer**: "Can you explain why this works?"

**Candidate**: "It's based on optimal substructure. If the maximum sum ending at position i-1 is negative, it's better to start fresh from position i. Otherwise, we extend the previous subarray. This greedy choice is optimal."

**Interviewer**: "What's the time complexity?"

**Candidate**: "O(n) time and O(1) space - we only need one pass through the array with two variables."

### Follow-up Questions

**Interviewer**: "What if we need to return the actual subarray, not just the sum?"

**Candidate**: "We'd track the start and end indices. When we start a new subarray (currentSum becomes nums[i]), we update start index. When we update maxSum, we update end index."

**Interviewer**: "What if all numbers are negative?"

**Candidate**: "The algorithm still works. We'd return the least negative number, which is the maximum subarray (single element)."

**Interviewer**: "Can you solve this with divide and conquer?"

**Candidate**: "Yes, but it's O(n log n). We divide array in half, find max in left, right, and crossing middle. The crossing case requires O(n) work, leading to T(n) = 2T(n/2) + O(n) = O(n log n)."

**Interviewer**: "What about the 2D version - maximum sum rectangle?"

**Candidate**: "That's more complex. We'd fix left and right columns, then use Kadane's on the row sums, giving O(n³) or O(n²m) time."

### Tricky Edge Cases

1. **All negative**: `[-1,-2,-3]` → Return -1 (least negative)
2. **All positive**: `[1,2,3]` → Return 6 (entire array)
3. **Mixed with zeros**: `[-1,0,2]` → Return 2
4. **Single element**: `[5]` → Return 5
5. **Alternating positive/negative**: `[1,-2,3,-4,5]` → Return 5
6. **Large negative in middle**: `[1,2,-100,3,4]` → Return 7 (skip -100)

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "Walk me through your implementation."

**Candidate**: "I initialize currentSum and maxSum to nums[0]. For each subsequent element, I calculate currentSum as max of current element alone or currentSum + current element. This decides whether to start fresh or extend. Then I update maxSum. The key is that if previous sum is negative, starting fresh is always better."

**Interviewer**: "Why initialize to nums[0]?"

**Candidate**: "For the first element, the maximum sum ending at position 0 is just that element itself. Also handles the edge case of single-element array."

## Solution Approaches

### Approach 1: Kadane's Algorithm (Optimal)
Dynamic programming approach. O(n) time, O(1) space.

### Approach 2: Divide and Conquer
Recursive approach. O(n log n) time, O(log n) space.

### Approach 3: Brute Force
Check all subarrays. O(n²) time, O(1) space.

## Key Takeaways

1. **Kadane's algorithm** is the optimal solution
2. **Greedy choice**: Start fresh if previous sum is negative
3. **O(n) time, O(1) space** - very efficient
4. **Optimal substructure** property enables DP
5. **Classic algorithm** to know by heart
6. **Foundation for** many DP and greedy problems
7. **Can be extended** to 2D (maximum sum rectangle)
8. **Variations**: Maximum product subarray, circular subarray
9. **Interview favorite** - appears frequently
10. **Practice problem** for understanding DP concepts
