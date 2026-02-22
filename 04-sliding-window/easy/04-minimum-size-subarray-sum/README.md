# Minimum Size Subarray Sum

## Problem Statement
Given an array of positive integers `nums` and a positive integer `target`, return the minimal length of a contiguous subarray whose sum is greater than or equal to `target`. If there is no such subarray, return `0`.

**Example 1:**
```
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
```

**Example 2:**
```
Input: target = 4, nums = [1,4,4]
Output: 1
```

**Example 3:**
```
Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0
```

## Theory & Data Structures

### Sliding Window (Variable Size)
This problem uses a **variable-size sliding window**. We expand the window by moving the right pointer, and shrink it by moving the left pointer when the sum is >= target.

#### Key Insight: Expand and Shrink
- **Expand**: Move right pointer to include more elements (increase sum)
- **Shrink**: Move left pointer to exclude elements (decrease sum, find minimum length)
- **Goal**: Find minimum length subarray with sum >= target

#### Building Sliding Window from Scratch (Conceptual)
```java
// Conceptual implementation of variable-size sliding window
class MinimumSubarrayFinder {
    
    // Find minimum length subarray with sum >= target
    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        
        // Expand window by moving right pointer
        for (int right = 0; right < nums.length; right++) {
            // Add current element to sum
            sum += nums[right];
            
            // Shrink window while sum >= target
            while (sum >= target) {
                // Update minimum length
                minLength = Math.min(minLength, right - left + 1);
                
                // Remove left element and move left pointer
                sum -= nums[left];
                left++;
            }
        }
        
        // Return 0 if no valid subarray found
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    
    // Why does this work?
    // - We expand until sum >= target
    // - Then we shrink to find minimum length
    // - Each element is visited at most twice (once by right, once by left)
    // - This gives us O(n) time complexity
}
```

### Why Sliding Window Works
- **All positive numbers**: Sum only increases when expanding, decreases when shrinking
- **Optimal substructure**: If a subarray starting at i has sum >= target, we don't need to check subarrays starting before i
- **Efficiency**: Each element visited at most twice

### Time & Space Complexity

#### Approach: Sliding Window
- **Time Complexity**: O(n) - Each element visited at most twice
  - Right pointer: visits each element once
  - Left pointer: visits each element at most once
  - Total: O(n)
- **Space Complexity**: O(1) - Only using variables
  - sum, left, right, minLength
  - Constant space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find the minimum length of a subarray with sum >= target."

**Candidate**: "I'll use a sliding window approach. I'll expand the window by moving the right pointer and adding elements to the sum. When the sum becomes >= target, I'll shrink the window from the left to find the minimum length, tracking the minimum length seen so far."

**Interviewer**: "Why does sliding window work here?"

**Candidate**: "Since all numbers are positive, the sum only increases when we expand and decreases when we shrink. This means we can use a two-pointer approach to efficiently find the minimum length subarray. Each element is visited at most twice, giving us O(n) time."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For target=7, nums=[2,3,1,2,4,3]. Start: left=0, right=0, sum=2. Expand: right=1, sum=5. Expand: right=2, sum=6. Expand: right=3, sum=8 >= 7, length=4, shrink: left=1, sum=6. Expand: right=4, sum=10 >= 7, length=4, shrink: left=2, sum=7 >= 7, length=3, shrink: left=3, sum=6. Expand: right=5, sum=9 >= 7, length=3, shrink: left=4, sum=7 >= 7, length=2, shrink: left=5, sum=3. Min length = 2."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since each element is visited at most twice. Space complexity is O(1) since we only use a few variables."

### Follow-up Questions

**Interviewer**: "What if the array contains negative numbers?"

**Candidate**: "Then sliding window wouldn't work because the sum could decrease when expanding. We'd need a different approach, like using prefix sums with a data structure to find the optimal subarray, or dynamic programming."

**Interviewer**: "What if we need to find all subarrays with sum >= target?"

**Candidate**: "We'd modify the algorithm to continue shrinking and count all valid subarrays, or use a different approach to enumerate all possibilities."

**Interviewer**: "Can you optimize this further?"

**Candidate**: "The sliding window approach is already optimal - O(n) time and O(1) space. We can't do better since we need to examine each element at least once."

### Tricky Edge Cases

1. **No valid subarray**: `target=11, nums=[1,1,1,1]` → Return `0`
2. **Single element valid**: `target=4, nums=[1,4,4]` → Return `1`
3. **All elements needed**: `target=10, nums=[1,2,3,4]` → Return `4`
4. **Target at start**: `target=2, nums=[2,1,1]` → Return `1`
5. **Target at end**: `target=3, nums=[1,1,3]` → Return `1`
6. **Multiple valid subarrays**: Find minimum length
7. **Large target**: No valid subarray
8. **Small target**: First element might be enough

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll initialize minLength to Integer.MAX_VALUE, left pointer to 0, and sum to 0. I'll iterate with right pointer from 0 to end. For each right position, I'll add nums[right] to sum. Then I'll use a while loop to shrink the window while sum >= target. Inside the while loop, I'll update minLength with the current window length (right - left + 1), then subtract nums[left] from sum and increment left. After the loop, if minLength is still Integer.MAX_VALUE, I'll return 0; otherwise, I'll return minLength."

**Interviewer**: "Why use a while loop for shrinking instead of an if statement?"

**Candidate**: "Because after shrinking once, the sum might still be >= target. We need to continue shrinking until the sum is less than target to find the minimum length. An if statement would only shrink once, missing the optimal solution."

## Solution Approaches

### Approach 1: Sliding Window (Optimal)
Variable-size window, expand and shrink. O(n) time, O(1) space.

**Algorithm:**
1. Initialize minLength = MAX, left = 0, sum = 0
2. For right from 0 to n-1:
   - Add nums[right] to sum
   - While sum >= target:
     - Update minLength = min(minLength, right - left + 1)
     - Subtract nums[left] from sum, increment left
3. Return minLength (or 0 if no valid subarray)

**Advantages:**
- O(n) time complexity
- O(1) space complexity
- Optimal solution

### Approach 2: Brute Force (Not Recommended)
Check all subarrays. O(n²) time, O(1) space.

**Disadvantages:**
- O(n²) time (much slower)
- Not optimal

## Key Takeaways

1. **Sliding window** for subarray problems with positive numbers
2. **Expand and shrink** to find optimal length
3. **O(n) time** - each element visited at most twice
4. **O(1) space** - only variables needed
5. **While loop for shrinking** - not just if statement
6. **Works only with positive numbers** - negatives break the approach
7. **Track minimum length** during shrinking
8. **Classic sliding window** problem
9. **Foundation for** more complex subarray problems
10. **Edge cases matter** - no valid subarray, single element, etc.
