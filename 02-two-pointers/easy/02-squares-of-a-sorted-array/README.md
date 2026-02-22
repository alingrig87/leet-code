# Squares of a Sorted Array

## Problem Statement
Given an integer array `nums` sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

**Example 1:**
```
Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
```

**Example 2:**
```
Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]
```

**Example 3:**
```
Input: nums = [-5,-3,-2,-1]
Output: [1,4,9,25]
```

## Theory & Data Structures

### Two Pointers from Ends
This problem uses the **two pointers** technique, but with a crucial insight: since the array is sorted, the largest squares are at the ends (either the most negative numbers or the most positive numbers).

#### Key Insight: Largest Squares at Ends
- **Negative numbers**: When squared, larger absolute values produce larger squares
  - Example: (-4)² = 16, (-1)² = 1
- **Positive numbers**: When squared, larger values produce larger squares
  - Example: 3² = 9, 10² = 100
- **Conclusion**: The largest squares come from the ends of the sorted array

#### Building Two Pointers from Ends from Scratch (Conceptual)
```java
// Conceptual implementation of two pointers from ends
class SquareSorter {
    private int[] nums;
    
    SquareSorter(int[] nums) {
        this.nums = nums;
    }
    
    // Square and sort using two pointers from ends
    public int[] sortedSquares() {
        int n = nums.length;
        int[] result = new int[n];
        
        // Two pointers: left at start, right at end
        int left = 0;
        int right = n - 1;
        
        // Fill result array from right to left (largest to smallest)
        // We fill backwards because we're finding largest squares first
        int index = n - 1;
        
        while (left <= right) {
            // Calculate squares
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            
            // Compare squares
            if (leftSquare > rightSquare) {
                // Left square is larger, place it at current index
                result[index] = leftSquare;
                left++;  // Move left pointer right
            } else {
                // Right square is larger or equal, place it at current index
                result[index] = rightSquare;
                right--;  // Move right pointer left
            }
            index--;  // Move to next position (going backwards)
        }
        
        return result;
    }
    
    // Alternative: More explicit version
    public int[] sortedSquaresExplicit() {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;
        int pos = n - 1;  // Position to fill in result (from end)
        
        while (left <= right) {
            int leftVal = nums[left];
            int rightVal = nums[right];
            
            // Compare absolute values (or squares directly)
            if (Math.abs(leftVal) > Math.abs(rightVal)) {
                result[pos--] = leftVal * leftVal;
                left++;
            } else {
                result[pos--] = rightVal * rightVal;
                right--;
            }
        }
        
        return result;
    }
}
```

### Why Fill Backwards?
We fill the result array from right to left (largest to smallest) because:
1. We're finding the largest squares first (from the ends)
2. By filling backwards, we naturally get a sorted result
3. This avoids needing to sort the result separately

### Time & Space Complexity

#### Approach: Two Pointers from Ends
- **Time Complexity**: O(n) - Single pass through array
  - Best case: O(n)
  - Average case: O(n)
  - Worst case: O(n)
- **Space Complexity**: O(n) - Need space for result array
  - Cannot be avoided - must return new array

#### Alternative: Square and Sort
- **Time Complexity**: O(n log n) - Sorting dominates
- **Space Complexity**: O(n) - Result array
- **Not recommended** - Slower than two-pointer approach

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given a sorted array, square each number and return a sorted array of squares."

**Candidate**: "Since the array is sorted, the largest squares will be at the ends - either the most negative numbers or the most positive numbers. I'll use two pointers from both ends, compare the squares, and place the larger square at the end of the result array, working backwards."

**Interviewer**: "Why work backwards in the result array?"

**Candidate**: "Because we're finding the largest squares first from the ends. By filling the result array from right to left, we naturally get a sorted result without needing to sort separately."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For [-4,-1,0,3,10], I start with left=0 (value -4), right=4 (value 10), index=4. Compare squares: (-4)²=16 and 10²=100. 100 is larger, so result[4]=100, right=3, index=3. Compare (-4)²=16 and 3²=9. 16 is larger, so result[3]=16, left=1, index=2. Compare (-1)²=1 and 3²=9. 9 is larger, so result[2]=9, right=2, index=1. Compare (-1)²=1 and 0²=0. 1 is larger, so result[1]=1, left=2, index=0. Finally, result[0]=0. Result: [0,1,9,16,100]."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we make a single pass through the array. Space complexity is O(n) for the result array, which is necessary since we need to return a new array."

### Follow-up Questions

**Interviewer**: "What if the array isn't sorted?"

**Candidate**: "Then we'd need to square all elements and sort them, which would be O(n log n) time. The two-pointer approach only works because the array is sorted, which allows us to know that the largest squares are at the ends."

**Interviewer**: "Can you do it in-place?"

**Candidate**: "Not easily, because squares can be larger than the original numbers, and we need space for the sorted result. Even if we could modify the original array, we'd need extra space to handle the sorting. The problem requires returning a new array anyway."

**Interviewer**: "What if we need to handle very large numbers?"

**Candidate**: "The algorithm handles this correctly. We're just squaring integers, so as long as the squares don't overflow (which they might for very large integers), the algorithm works. If overflow is a concern, we'd need to use long for intermediate calculations."

**Interviewer**: "Can you optimize this further?"

**Candidate**: "The algorithm is already optimal - O(n) time and O(n) space. We could potentially avoid calculating squares twice by storing them, but that's a minor optimization. The current approach is clean and efficient."

**Interviewer**: "What if all numbers are positive?"

**Candidate**: "If all numbers are positive, the array is already sorted after squaring (since squaring preserves order for positive numbers). We could just square in place, but the two-pointer approach still works correctly and has the same complexity."

### Tricky Edge Cases

1. **All negative**: `[-3,-2,-1]` → `[1,4,9]` (squares reverse the order)
2. **All positive**: `[1,2,3]` → `[1,4,9]` (squares preserve order)
3. **Mixed with zero**: `[-2,0,3]` → `[0,4,9]` (zero is smallest square)
4. **Single element**: `[5]` → `[25]`
5. **Single negative**: `[-5]` → `[25]`
6. **Zero only**: `[0]` → `[0]`
7. **Symmetric around zero**: `[-2,-1,0,1,2]` → `[0,1,1,4,4]`
8. **Large negative, small positive**: `[-10,1]` → `[1,100]`
9. **Small negative, large positive**: `[-1,10]` → `[1,100]`
10. **All zeros**: `[0,0,0]` → `[0,0,0]`
11. **Duplicates**: `[-2,2,3]` → `[4,4,9]` (same square for -2 and 2)
12. **Large numbers**: Works with any integer values (watch for overflow)

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll create a result array of the same size. I'll initialize two pointers: left at 0 and right at the last index. I'll also initialize an index pointer at the last position of the result array (we'll fill backwards). In a loop, I'll compare the squares of the elements at left and right pointers. Whichever square is larger, I'll place it at the current index position in the result, then move the appropriate pointer and decrement the index. I'll continue until the pointers meet."

**Interviewer**: "Why compare squares instead of absolute values?"

**Candidate**: "We could compare absolute values and then square, which might be slightly more efficient (avoiding one multiplication), but comparing squares directly is clearer and the performance difference is negligible. Both approaches work correctly."

**Interviewer**: "What happens when left equals right?"

**Candidate**: "When left equals right, we're at the last element. We square it and place it at index 0 (the first position in the result). Then the loop condition left <= right becomes false, and we're done."

## Solution Approaches

### Approach 1: Two Pointers from Ends (Optimal)
Compare squares from ends, place larger at end of result. O(n) time, O(n) space.

**Algorithm:**
1. Create result array of size n
2. Initialize left = 0, right = n-1, index = n-1
3. While left <= right:
   - Compare squares of nums[left] and nums[right]
   - Place larger square at result[index]
   - Move appropriate pointer and decrement index
4. Return result

**Advantages:**
- O(n) time complexity
- Single pass through array
- No sorting needed
- Optimal solution

### Approach 2: Square and Sort
Square all elements, then sort. O(n log n) time, O(n) space.

**Algorithm:**
1. Create result array
2. Square each element
3. Sort the result array
4. Return result

**Disadvantages:**
- O(n log n) time (slower)
- Requires sorting
- Not optimal

### Approach 3: Using Priority Queue (Not Recommended)
Add squares to min-heap, then extract. O(n log n) time, O(n) space.

**Disadvantages:**
- O(n log n) time
- More complex
- Not optimal

## Key Takeaways

1. **Sorted array enables two-pointer approach** - largest squares at ends
2. **Largest squares at ends** is the key insight
3. **Fill result backwards** for natural sorting (largest to smallest)
4. **O(n) solution** possible with sorted input
5. **Cannot avoid O(n) space** - must return new array
6. **Two pointers from ends** pattern is powerful
7. **Compare squares directly** - clear and correct
8. **Edge cases matter** - all negative, all positive, mixed, zero
9. **Watch for overflow** - squares can be large
10. **Single pass** through array gives O(n) time
