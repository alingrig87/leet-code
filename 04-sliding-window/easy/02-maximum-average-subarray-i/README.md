# Maximum Average Subarray I

## Problem Statement
You are given an integer array `nums` consisting of `n` elements, and an integer `k`.

Find a contiguous subarray whose length is equal to `k` that has the maximum average value and return this value. Any answer with a calculation error less than 10^-5 will be accepted.

**Example 1:**
```
Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
```

**Example 2:**
```
Input: nums = [5], k = 1
Output: 5.00000
```

## Theory & Data Structures

### Sliding Window Technique
This problem uses the **sliding window** technique with a fixed window size. Instead of recalculating the sum for each window (which would be O(n*k)), we reuse the previous sum by sliding the window.

#### Key Insight: Reuse Previous Sum
- **First window**: Calculate sum of first k elements
- **Slide window**: Subtract leftmost element, add rightmost element
- **Track maximum**: Keep track of maximum sum seen
- **Calculate average**: Divide maximum sum by k

#### Building Sliding Window from Scratch (Conceptual)
```java
// Conceptual implementation of sliding window
class MaximumAverageFinder {
    
    // Find maximum average using sliding window
    public double findMaxAverage(int[] nums, int k) {
        // Calculate sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        
        // Initialize maximum sum with first window
        int maxSum = windowSum;
        
        // Slide window from position k to end
        for (int i = k; i < nums.length; i++) {
            // Remove leftmost element (going out of window)
            windowSum -= nums[i - k];
            // Add rightmost element (entering window)
            windowSum += nums[i];
            
            // Update maximum sum
            maxSum = Math.max(maxSum, windowSum);
        }
        
        // Return average (convert to double)
        return (double) maxSum / k;
    }
    
    // Alternative: More explicit version
    public double findMaxAverageExplicit(int[] nums, int k) {
        // Edge case: k equals array length
        if (k == nums.length) {
            long sum = 0;
            for (int num : nums) {
                sum += num;
            }
            return (double) sum / k;
        }
        
        // Calculate initial window sum
        long windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        
        long maxSum = windowSum;
        
        // Slide window
        int left = 0;
        int right = k;
        
        while (right < nums.length) {
            // Remove left element, add right element
            windowSum = windowSum - nums[left] + nums[right];
            maxSum = Math.max(maxSum, windowSum);
            
            left++;
            right++;
        }
        
        return (double) maxSum / k;
    }
}
```

### Why Sliding Window?
- **Efficiency**: O(n) time instead of O(n*k)
- **Reuse calculation**: Don't recalculate entire sum each time
- **Fixed size**: Window size k is constant
- **Optimal**: Best possible time complexity for this problem

### Time & Space Complexity

#### Approach: Sliding Window
- **Time Complexity**: O(n) - Single pass through array
  - Initial window: O(k)
  - Sliding: O(n - k)
  - Total: O(n)
- **Space Complexity**: O(1) - Only using variables
  - windowSum, maxSum, loop variables
  - Constant space regardless of input size

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find the maximum average of any contiguous subarray of length k."

**Candidate**: "I'll use a sliding window approach. First, I'll calculate the sum of the first k elements. Then I'll slide the window by subtracting the leftmost element and adding the rightmost element. I'll track the maximum sum, and at the end, I'll divide by k to get the average."

**Interviewer**: "Why use sliding window instead of checking each subarray separately?"

**Candidate**: "If we check each subarray separately, we'd recalculate the sum for each window, which would be O(n*k) time. With sliding window, we reuse the previous sum by just subtracting one element and adding another, giving us O(n) time."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For nums = [1,12,-5,-6,50,3], k = 4. First window [1,12,-5,-6]: sum = 2, maxSum = 2. Slide: remove 1, add 50, sum = 51, maxSum = 51. Slide: remove 12, add 3, sum = 42, maxSum = 51. Return 51/4 = 12.75."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we make a single pass through the array. Space complexity is O(1) since we only use a few variables."

### Follow-up Questions

**Interviewer**: "What if k is very large, close to n?"

**Candidate**: "The algorithm still works efficiently. The initial window calculation takes O(k) time, and the sliding takes O(n-k) time. Total is still O(n). If k equals n, we just calculate the sum of all elements once."

**Interviewer**: "What if we need to find maximum average for any subarray length, not just k?"

**Candidate**: "That's a different problem. We'd need to check all possible subarray lengths, which would be O(n²) or O(n³) depending on the approach. We might use dynamic programming or other techniques."

**Interviewer**: "What if the array contains very large numbers?"

**Candidate**: "We need to be careful with integer overflow. We could use long for the sum calculations to prevent overflow. The average calculation should use double to maintain precision."

**Interviewer**: "Can you optimize this further?"

**Candidate**: "The sliding window approach is already optimal - O(n) time and O(1) space. We can't do better since we need to examine each element at least once. We could combine the initial window calculation and sliding into one loop, but that's a minor optimization."

### Tricky Edge Cases

1. **k equals array length**: `nums=[1,2,3], k=3` → Return average of all elements
2. **k = 1**: `nums=[1,2,3], k=1` → Return maximum element (3.0)
3. **All negative**: `nums=[-1,-2,-3], k=2` → Return maximum average (-1.5)
4. **All positive**: `nums=[1,2,3], k=2` → Return maximum average (2.5)
5. **Mixed signs**: `nums=[1,12,-5,-6,50,3], k=4` → Return 12.75
6. **Single element**: `nums=[5], k=1` → Return 5.0
7. **Large numbers**: Handle with long to prevent overflow
8. **Precision**: Return double, handle floating point precision

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll first calculate the sum of the first k elements to initialize the window. I'll store this as both windowSum and maxSum. Then I'll iterate from index k to the end of the array. For each position, I'll subtract the element that's leaving the window (at index i-k) and add the element that's entering the window (at index i). I'll update maxSum if the new windowSum is larger. After processing all windows, I'll return maxSum divided by k as a double."

**Interviewer**: "Why track sum instead of average directly?"

**Candidate**: "Because we can compare sums directly without division, which is more efficient and avoids floating point precision issues. We only divide once at the end to get the final average. Also, comparing sums is equivalent to comparing averages when the denominators are the same (k)."

**Interviewer**: "What if we need to handle integer overflow?"

**Candidate**: "We'd use long for windowSum and maxSum instead of int. This prevents overflow when dealing with large numbers. The division by k would still give us a double result."

## Solution Approaches

### Approach 1: Sliding Window (Optimal)
Fixed-size window, update sum by sliding. O(n) time, O(1) space.

**Algorithm:**
1. Calculate sum of first k elements
2. Initialize maxSum = windowSum
3. For i from k to n-1:
   - Subtract nums[i-k] (left element)
   - Add nums[i] (right element)
   - Update maxSum
4. Return maxSum / k

**Advantages:**
- O(n) time complexity
- O(1) space complexity
- Simple and efficient
- Optimal solution

### Approach 2: Brute Force (Not Recommended)
Check each subarray separately. O(n*k) time, O(1) space.

**Disadvantages:**
- O(n*k) time (much slower)
- Redundant calculations
- Not optimal

### Approach 3: Prefix Sum (Alternative)
Use prefix sum array. O(n) time, O(n) space.

**Algorithm:**
1. Build prefix sum array
2. For each window, calculate sum using prefix sums
3. Track maximum average

**Disadvantages:**
- O(n) extra space
- More complex than sliding window
- Not needed for this problem

## Key Takeaways

1. **Sliding window** for fixed-size subarrays
2. **Reuse previous sum** for efficiency
3. **O(n) time** instead of O(n*k) with brute force
4. **O(1) space** - only variables needed
5. **Track sum, not average** - divide at the end
6. **Handle overflow** with long for large numbers
7. **Simple and efficient** - optimal solution
8. **Classic sliding window** problem
9. **Foundation for** variable-size sliding window problems
10. **Edge cases matter** - k=1, k=n, all negative, etc.
