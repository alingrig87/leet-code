# Solution Explanation: Maximum Subarray (Kadane's Algorithm)

## Approach 1: Kadane's Algorithm (Recommended)

### Intuition
At each position, decide whether to start a new subarray or extend the previous one. If previous sum is negative, starting fresh is better.

### Algorithm
1. Initialize currentSum = nums[0], maxSum = nums[0]
2. For each element from index 1:
   - currentSum = max(nums[i], currentSum + nums[i])
   - maxSum = max(maxSum, currentSum)
3. Return maxSum

### Why This Works
- If currentSum + nums[i] < nums[i], previous sum was negative
- Starting fresh (nums[i]) is better than extending
- Otherwise, extending gives larger sum
- This greedy choice is optimal

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only variables

## Approach 2: Divide and Conquer

### Intuition
Divide array in half. Maximum subarray is either:
- Entirely in left half
- Entirely in right half
- Crossing the middle

### Algorithm
1. Divide array in half
2. Recursively find max in left and right
3. Find max crossing middle (O(n) work)
4. Return maximum of three

### Complexity
- **Time**: O(n log n) - T(n) = 2T(n/2) + O(n)
- **Space**: O(log n) - recursion stack

## Why Kadane's is Better

- O(n) vs O(n log n) time
- O(1) vs O(log n) space
- Simpler implementation
- More intuitive
