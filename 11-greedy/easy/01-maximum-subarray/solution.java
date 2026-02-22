/**
 * LeetCode 53: Maximum Subarray
 * 
 * Problem: Find the contiguous subarray with the largest sum.
 * 
 * Solution Approach: Kadane's Algorithm (Dynamic Programming)
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(1) - only using two variables
 */
class Solution {
    
    /**
     * Main solution using Kadane's Algorithm
     * 
     * Kadane's Algorithm is a dynamic programming approach that solves
     * the maximum subarray problem efficiently. The key insight is:
     * 
     * At each position i, we decide whether to:
     * 1. Start a new subarray from nums[i]
     * 2. Extend the previous subarray ending at i-1
     * 
     * We choose the option that gives maximum sum. This decision is optimal
     * because if the previous sum is negative, starting fresh is always better.
     * 
     * Algorithm:
     * - currentSum: maximum sum of subarray ending at current position
     * - maxSum: maximum sum seen so far (our answer)
     * 
     * For each element:
     *   currentSum = max(nums[i], currentSum + nums[i])
     *   maxSum = max(maxSum, currentSum)
     * 
     * @param nums Input array of integers (can contain negatives)
     * @return Maximum sum of contiguous subarray
     */
    public int maxSubArray(int[] nums) {
        // Edge case: empty array
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Edge case: single element
        if (nums.length == 1) {
            return nums[0];
        }
        
        // Initialize with first element
        // currentSum: maximum sum ending at current position
        // Start with first element as the maximum sum ending at index 0
        int currentSum = nums[0];
        
        // maxSum: global maximum sum seen so far
        // This will be our final answer
        int maxSum = nums[0];
        
        // Process remaining elements
        // Starting from index 1 because we've already processed index 0
        for (int i = 1; i < nums.length; i++) {
            // Decision point: start fresh or extend previous subarray?
            // 
            // Option 1: Start fresh from nums[i]
            //   - This is better if previous sum (currentSum) is negative
            //   - Because: negative + nums[i] < nums[i]
            //
            // Option 2: Extend previous subarray
            //   - This is better if previous sum is positive
            //   - Because: positive + nums[i] > nums[i]
            //
            // We choose the maximum of these two options
            // This is the greedy choice that leads to optimal solution
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            
            // Update global maximum if current sum is larger
            // This tracks the best subarray we've seen so far
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
}

/**
 * Alternative: More explicit version with early termination optimization
 * 
 * If we find that currentSum becomes negative, we can reset it to 0
 * (or current element) more explicitly. This version makes the logic clearer.
 */
class SolutionExplicit {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int currentSum = nums[0];
        int maxSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // If previous sum is negative, it's better to start fresh
            // Reset currentSum to current element (starting new subarray)
            if (currentSum < 0) {
                currentSum = nums[i];
            } else {
                // Previous sum is positive, extend the subarray
                currentSum += nums[i];
            }
            
            // Update global maximum
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
}

/**
 * Alternative: Divide and Conquer Approach
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(log n) for recursion stack
 * 
 * This approach divides the problem into smaller subproblems:
 * - Maximum subarray in left half
 * - Maximum subarray in right half
 * - Maximum subarray crossing the middle
 * 
 * The crossing case requires O(n) work, leading to O(n log n) overall.
 */
class SolutionDivideConquer {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return maxSubArrayHelper(nums, 0, nums.length - 1);
    }
    
    private int maxSubArrayHelper(int[] nums, int left, int right) {
        // Base case: single element
        if (left == right) {
            return nums[left];
        }
        
        int mid = left + (right - left) / 2;
        
        // Maximum in left half
        int leftMax = maxSubArrayHelper(nums, left, mid);
        
        // Maximum in right half
        int rightMax = maxSubArrayHelper(nums, mid + 1, right);
        
        // Maximum crossing middle
        int crossMax = maxCrossing(nums, left, mid, right);
        
        // Return maximum of three
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }
    
    private int maxCrossing(int[] nums, int left, int mid, int right) {
        // Find maximum sum in left half ending at mid
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }
        
        // Find maximum sum in right half starting from mid+1
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }
        
        return leftSum + rightSum;
    }
}

/**
 * Follow-up: What if we need to return the actual subarray?
 * 
 * We track the start and end indices of the maximum subarray.
 */
class SolutionReturnSubarray {
    public int[] maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int currentSum = nums[0];
        int maxSum = nums[0];
        int start = 0;
        int end = 0;
        int tempStart = 0;
        
        for (int i = 1; i < nums.length; i++) {
            if (currentSum < 0) {
                // Start fresh subarray
                currentSum = nums[i];
                tempStart = i;
            } else {
                // Extend previous subarray
                currentSum += nums[i];
            }
            
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }
        
        // Return subarray from start to end
        int[] result = new int[end - start + 1];
        System.arraycopy(nums, start, result, 0, result.length);
        return result;
    }
}

/**
 * Follow-up: What if we need maximum sum of exactly k elements?
 * 
 * This becomes a sliding window problem.
 */
class SolutionKElements {
    public int maxSubArray(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return 0;
        }
        
        // Calculate sum of first k elements
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        
        int maxSum = windowSum;
        
        // Slide window
        for (int i = k; i < nums.length; i++) {
            windowSum = windowSum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return maxSum;
    }
}
