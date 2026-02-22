# Max Consecutive Ones

## Problem Statement
Given a binary array `nums`, return the maximum number of consecutive `1`s in the array.

**Example 1:**
```
Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The maximum number of consecutive 1s is 3.
```

**Example 2:**
```
Input: nums = [1,0,1,1,0,1]
Output: 2
```

## Theory & Data Structures

### Simple Traversal with Counter
This problem uses a simple one-pass traversal with a counter. We count consecutive ones, reset the counter when we encounter a zero, and track the maximum count.

#### Key Insight: Reset and Track
- **Count consecutive ones**: Increment counter for each 1
- **Reset on zero**: Set counter to 0 when encountering 0
- **Track maximum**: Keep track of maximum count seen so far
- **Single pass**: Process each element once

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of consecutive ones counter
class ConsecutiveOnesCounter {
    
    // Find maximum consecutive ones
    public int findMaxConsecutiveOnes(int[] nums) {
        // Counter for current consecutive ones
        int currentCount = 0;
        
        // Maximum consecutive ones seen so far
        int maxCount = 0;
        
        // Traverse array
        for (int num : nums) {
            if (num == 1) {
                // Increment counter for consecutive ones
                currentCount++;
                // Update maximum
                maxCount = Math.max(maxCount, currentCount);
            } else {
                // Reset counter when encountering zero
                currentCount = 0;
            }
        }
        
        return maxCount;
    }
    
    // Alternative: More explicit version
    public int findMaxConsecutiveOnesExplicit(int[] nums) {
        int maxCount = 0;
        int currentCount = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                currentCount++;
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                }
            } else {
                // Reset when we hit a zero
                currentCount = 0;
            }
        }
        
        return maxCount;
    }
    
    // Using while loop (alternative style)
    public int findMaxConsecutiveOnesWhile(int[] nums) {
        int maxCount = 0;
        int i = 0;
        
        while (i < nums.length) {
            if (nums[i] == 1) {
                int count = 0;
                // Count consecutive ones
                while (i < nums.length && nums[i] == 1) {
                    count++;
                    i++;
                }
                maxCount = Math.max(maxCount, count);
            } else {
                i++;
            }
        }
        
        return maxCount;
    }
}
```

### Time & Space Complexity

#### Approach: One Pass
- **Time Complexity**: O(n) - Single pass through array
  - Best case: O(n)
  - Average case: O(n)
  - Worst case: O(n)
- **Space Complexity**: O(1) - Only using variables
  - currentCount, maxCount
  - Constant space regardless of array size

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find the maximum number of consecutive 1s in a binary array."

**Candidate**: "I'll traverse the array once. I'll maintain a counter for current consecutive ones and reset it to 0 when I encounter a 0. I'll also track the maximum count seen so far and update it whenever the current count exceeds it."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For [1,1,0,1,1,1], I start with currentCount=0, maxCount=0. First element 1: currentCount=1, maxCount=1. Second element 1: currentCount=2, maxCount=2. Third element 0: currentCount=0, maxCount=2. Fourth element 1: currentCount=1, maxCount=2. Fifth element 1: currentCount=2, maxCount=2. Sixth element 1: currentCount=3, maxCount=3. Return 3."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we make a single pass through the array. Space complexity is O(1) since we only use a few variables."

### Follow-up Questions

**Interviewer**: "What if we can flip at most k zeros to ones? Find the maximum consecutive ones."

**Candidate**: "That's a different problem - it becomes a sliding window problem. We'd find the longest subarray that contains at most k zeros. We'd use two pointers and expand/contract the window based on the number of zeros."

**Interviewer**: "What if the array is very large?"

**Candidate**: "The one-pass approach is still efficient - O(n) time and O(1) space. It's optimal for this problem since we need to examine each element at least once."

**Interviewer**: "Can you solve this with divide and conquer?"

**Candidate**: "Yes, but it would be more complex and still O(n) time. The divide and conquer approach would find max consecutive ones in left half, right half, and across the middle. But the simple one-pass approach is much cleaner and equally efficient."

**Interviewer**: "What if we need to find all segments of consecutive ones?"

**Candidate**: "We'd modify the algorithm to track segments. When we reset the counter, we'd record the previous segment's length and start position. We'd maintain a list of all segments."

### Tricky Edge Cases

1. **All ones**: `[1,1,1,1]` → Return `4` (array length)
2. **All zeros**: `[0,0,0]` → Return `0`
3. **Single one**: `[0,1,0]` → Return `1`
4. **Single zero**: `[1,0,1]` → Return `1`
5. **Empty array**: `[]` → Return `0`
6. **Alternating**: `[1,0,1,0,1]` → Return `1`
7. **Ones at start**: `[1,1,0,1]` → Return `2`
8. **Ones at end**: `[0,1,1,1]` → Return `3`
9. **Ones in middle**: `[0,1,1,0]` → Return `2`
10. **Single element one**: `[1]` → Return `1`
11. **Single element zero**: `[0]` → Return `0`

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll initialize currentCount and maxCount to 0. I'll iterate through each element in the array. If the element is 1, I'll increment currentCount and update maxCount if currentCount is larger. If the element is 0, I'll reset currentCount to 0. After processing all elements, I'll return maxCount."

**Interviewer**: "Why update maxCount inside the if block for 1s?"

**Candidate**: "We update maxCount whenever we see a 1 because that's when currentCount increases. We could also update it when we see a 0 (before resetting), but updating inside the 1 block is cleaner and ensures we always have the latest maximum."

**Interviewer**: "What if we need to also return the positions of the maximum consecutive ones?"

**Candidate**: "We'd track the start and end positions of the current segment. When we update maxCount, we'd also update the start and end positions of the maximum segment. We'd need additional variables to track these positions."

## Solution Approaches

### Approach 1: One Pass with Counter (Optimal)
Count consecutive ones, reset on zero, track maximum. O(n) time, O(1) space.

**Algorithm:**
1. Initialize currentCount = 0, maxCount = 0
2. For each element:
   - If element is 1: increment currentCount, update maxCount
   - Else: reset currentCount to 0
3. Return maxCount

**Advantages:**
- Simple and intuitive
- O(n) time complexity
- O(1) space complexity
- Optimal solution

### Approach 2: Using Regular Expressions (Not Recommended)
Convert to string, use regex. O(n) time, O(n) space.

**Disadvantages:**
- Requires conversion to string
- O(n) extra space
- Overkill for this problem

### Approach 3: Divide and Conquer
Recursively find max in left, right, and across middle. O(n) time, O(log n) space.

**Disadvantages:**
- More complex
- O(log n) space for recursion
- Not needed for this problem

## Key Takeaways

1. **Simple traversal** with counter
2. **Reset counter** on zero
3. **Track maximum** count seen
4. **O(n) time, O(1) space** - optimal solution
5. **Single pass** through array
6. **Foundation for** sliding window problems
7. **Edge cases matter** - all ones, all zeros, empty array
8. **Straightforward solution** - no complex data structures
9. **Classic problem** - good for understanding basic traversal
10. **Foundation for** more complex consecutive problems
