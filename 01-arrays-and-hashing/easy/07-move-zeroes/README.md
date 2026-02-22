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

**Example 3:**
```
Input: nums = [0,0,1]
Output: [1,0,0]
```

## Theory & Data Structures

### Two Pointers Technique
This problem uses the **two pointers** technique, specifically the "read-write pointers" pattern. This is similar to removing elements but with a different goal.

#### How Two Pointers Work for This Problem
1. **Write Pointer**: Tracks the position where the next non-zero element should be written
2. **Read Pointer**: Scans through the entire array to find non-zero elements
3. **Key Insight**: We write all non-zero elements first, then fill the remaining positions with zeros

#### Building Two Pointers from Scratch (Conceptual)
```java
// Conceptual implementation of two pointers for moving zeros
class ZeroMover {
    private int[] array;
    
    ZeroMover(int[] array) {
        this.array = array;
    }
    
    // Approach 1: Two passes - move non-zeros, then fill zeros
    public void moveZeroes() {
        // First pass: move all non-zero elements to front
        int writeIndex = 0;
        
        // Read pointer scans through array
        for (int readIndex = 0; readIndex < array.length; readIndex++) {
            // If element is non-zero, write it at writeIndex
            if (array[readIndex] != 0) {
                array[writeIndex] = array[readIndex];
                writeIndex++;
            }
            // If element is zero, skip it (don't write)
        }
        
        // Second pass: fill remaining positions with zeros
        while (writeIndex < array.length) {
            array[writeIndex] = 0;
            writeIndex++;
        }
    }
    
    // Approach 2: One pass with swap (more efficient)
    public void moveZeroesSwap() {
        int writeIndex = 0;
        
        for (int readIndex = 0; readIndex < array.length; readIndex++) {
            // If we find a non-zero element
            if (array[readIndex] != 0) {
                // Swap with writeIndex position (only if different)
                if (readIndex != writeIndex) {
                    int temp = array[writeIndex];
                    array[writeIndex] = array[readIndex];
                    array[readIndex] = temp;
                }
                writeIndex++;
            }
            // If element is zero, just continue (don't increment writeIndex)
        }
        // Zeros are already at the end after swapping
    }
}
```

### In-Place Modification
We modify the array in-place by:
1. Writing non-zero elements to the front
2. Filling remaining positions with zeros
3. Or using swap to move non-zeros forward

### Order Preservation
- **Non-zero elements**: Maintain their relative order (stable operation)
- **Zero elements**: Order doesn't matter (all zeros are the same)

### Time & Space Complexity

#### Approach 1: Two Passes (Write then Fill)
- **Time Complexity**: O(n) - Two passes through array
  - First pass: O(n) to move non-zeros
  - Second pass: O(k) where k is number of zeros
  - Total: O(n)
- **Space Complexity**: O(1) - Only using variables

#### Approach 2: One Pass with Swap
- **Time Complexity**: O(n) - Single pass through array
  - More efficient: fewer operations when zeros are rare
- **Space Complexity**: O(1) - Only using variables

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given an array, move all zeros to the end while keeping the relative order of non-zero elements."

**Candidate**: "I'll use two pointers. One pointer will track where non-zero elements should be written, and another will scan through the array. I'll first move all non-zero elements to the front, then fill the remaining positions with zeros."

**Interviewer**: "Can you do it in one pass?"

**Candidate**: "Yes, I can use a swap approach. When I find a non-zero element, I'll swap it with the element at the write pointer position, then increment both pointers. This way, zeros naturally end up at the end without needing a second pass."

**Interviewer**: "Walk me through an example."

**Candidate**: "For [0,1,0,3,12], I start with writeIndex=0, readIndex=0. At index 0, element is 0, so I skip. At index 1, element is 1 (non-zero), I swap with writeIndex (0), getting [1,0,0,3,12], increment both. At index 2, element is 0, skip. At index 3, element is 3, swap with writeIndex (1), getting [1,3,0,0,12], increment both. At index 4, element is 12, swap with writeIndex (2), getting [1,3,12,0,0]. Done."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we make a single pass through the array. Space complexity is O(1) since we only use a few variables and modify the array in-place."

### Follow-up Questions

**Interviewer**: "What if we need to preserve zeros' relative order too?"

**Candidate**: "If zeros need to maintain order among themselves, we'd need a stable sort or a different approach that tracks zero positions. However, since all zeros are identical, this requirement is unusual. If needed, we could use a stable partitioning algorithm."

**Interviewer**: "What's the minimum number of operations?"

**Candidate**: "The swap approach minimizes operations - we only swap when necessary (when readIndex != writeIndex). If the array already has zeros at the end, we do fewer operations. The two-pass approach always does n writes in the first pass plus k writes in the second pass."

**Interviewer**: "Can you optimize for the case when there are no zeros?"

**Candidate**: "Yes, we can add a check at the beginning to see if there are any zeros. If not, we can return immediately. However, checking for zeros might take O(n) time anyway, so the optimization might not help much."

**Interviewer**: "What if the array is very large?"

**Candidate**: "The two-pointer approach is still efficient - O(n) time and O(1) space. It's optimal for this problem. The swap approach is slightly more efficient as it avoids the second pass."

**Interviewer**: "Can you solve this without modifying the original array?"

**Candidate**: "We could create a new array, but that would use O(n) space. The problem specifically asks for in-place modification, which is more space-efficient."

### Tricky Edge Cases

1. **All zeros**: `[0,0,0]` → `[0,0,0]` (no change needed)
2. **No zeros**: `[1,2,3]` → `[1,2,3]` (no change needed)
3. **Zeros at start**: `[0,0,1,2]` → `[1,2,0,0]`
4. **Zeros at end**: `[1,2,0,0]` → `[1,2,0,0]` (already correct)
5. **Zeros in middle**: `[1,0,2,0,3]` → `[1,2,3,0,0]`
6. **Single zero**: `[1,0,2]` → `[1,2,0]`
7. **Single non-zero**: `[0,0,1]` → `[1,0,0]`
8. **Alternating pattern**: `[0,1,0,1,0]` → `[1,1,0,0,0]`
9. **All non-zeros except one**: `[1,2,3,0]` → `[1,2,3,0]`
10. **Large numbers**: `[Integer.MAX_VALUE, 0, Integer.MIN_VALUE]` → `[Integer.MAX_VALUE, Integer.MIN_VALUE, 0]`
11. **Negative numbers**: `[-1,0,1,0,-2]` → `[-1,1,-2,0,0]`
12. **Empty array**: `[]` → `[]` (edge case)

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll use the swap approach for efficiency. I'll initialize writeIndex at 0. Then I'll iterate through the array with readIndex. For each element, if it's non-zero, I'll swap it with the element at writeIndex (if they're different positions), then increment writeIndex. If it's zero, I'll just continue. After the loop, all non-zero elements will be at the front, and zeros will be at the end."

**Interviewer**: "Why check if readIndex != writeIndex before swapping?"

**Candidate**: "To avoid unnecessary swaps. If readIndex equals writeIndex, the element is already in the correct position, so swapping would be redundant. This optimization reduces operations when many elements are already in place."

**Interviewer**: "What's the difference between the two-pass and one-pass approaches?"

**Candidate**: "The two-pass approach first moves all non-zeros to the front, then fills the rest with zeros. The one-pass swap approach moves non-zeros forward as it encounters them, naturally leaving zeros behind. The swap approach is more efficient as it avoids the second pass, though both are O(n) time."

## Solution Approaches

### Approach 1: Two Passes (Write then Fill)
First pass: move non-zeros to front. Second pass: fill remaining with zeros. O(n) time, O(1) space.

**Algorithm:**
1. Initialize writeIndex = 0
2. First pass: iterate through array, write non-zeros at writeIndex
3. Second pass: fill positions from writeIndex to end with zeros

**Advantages:**
- Simple and intuitive
- Easy to understand
- Guaranteed O(n) operations

**Disadvantages:**
- Two passes (though still O(n))
- More writes than necessary

### Approach 2: One Pass with Swap (Recommended)
Swap non-zeros to front as encountered. O(n) time, O(1) space, fewer operations.

**Algorithm:**
1. Initialize writeIndex = 0
2. Iterate through array with readIndex
3. If array[readIndex] != 0, swap with array[writeIndex] (if different), increment writeIndex
4. Zeros naturally end up at the end

**Advantages:**
- Single pass
- Fewer operations (only swap when needed)
- More efficient

**Disadvantages:**
- Slightly more complex logic

### Approach 3: Using Extra Array (Not Recommended)
Create new array, place non-zeros first, then zeros. O(n) time, O(n) space.

**Disadvantages:**
- Uses O(n) extra space
- Doesn't meet in-place requirement

## Key Takeaways

1. **Two pointers technique** for in-place array rearrangement
2. **Separate non-zeros first**, then handle zeros
3. **Swap approach** is more efficient (one pass)
4. **Order preservation** - non-zero elements maintain relative order
5. **O(1) space** - only using variables
6. **O(n) time** - single or double pass
7. **Edge cases matter** - all zeros, no zeros, zeros at boundaries
8. **Optimization** - check if swap is needed before swapping
9. **In-place modification** is space-efficient
10. **Stable operation** - relative order of non-zeros preserved
