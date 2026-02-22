# Remove Element

## Problem Statement
Given an integer array `nums` and an integer `val`, remove all occurrences of `val` in `nums` in-place. The order of the elements may be changed. Then return the number of elements in `nums` which are not equal to `val`.

Consider the number of elements in `nums` which are not equal to `val` be `k`, to get accepted, you need to do the following things:
- Change the array `nums` such that the first `k` elements of `nums` contain the elements which are not equal to `val`.
- The elements beyond the first `k` elements are not important.
- Return `k`.

**Example 1:**
```
Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).
```

**Example 2:**
```
Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 3, 0, and 4.
Note that the order of those five elements can be arbitrary.
It does not matter what you leave beyond the returned k (hence they are underscores).
```

## Theory & Data Structures

### Two Pointers Technique
This problem uses the **two pointers** technique, specifically the "read and write pointers" pattern. This is similar to removing duplicates but with a different condition.

#### How Two Pointers Work for This Problem
1. **Write Pointer**: Tracks the position where the next valid (non-val) element should be written
2. **Read Pointer**: Scans through the entire array to find valid elements
3. **Key Insight**: We only write elements that are not equal to `val`. Elements equal to `val` are skipped.

#### Building Two Pointers from Scratch (Conceptual)
```java
// Conceptual implementation of two pointers for removing element
class ElementRemover {
    private int[] array;
    
    ElementRemover(int[] array) {
        this.array = array;
    }
    
    // Remove all occurrences of val using two pointers
    public int removeElement(int val) {
        // Write pointer: tracks position for next valid element
        // Starts at 0 because we write from the beginning
        int writeIndex = 0;
        
        // Read pointer: scans through entire array
        for (int readIndex = 0; readIndex < array.length; readIndex++) {
            // If current element is not equal to val, it's valid
            if (array[readIndex] != val) {
                // Write valid element at writeIndex
                array[writeIndex] = array[readIndex];
                // Move write pointer forward
                writeIndex++;
            }
            // If element equals val, we skip it (don't write)
        }
        
        // writeIndex now represents number of valid elements
        return writeIndex;
    }
    
    // Alternative: Two pointers from both ends (when order doesn't matter)
    // This approach swaps elements and can be more efficient when val is rare
    public int removeElementSwap(int val) {
        int left = 0;
        int right = array.length - 1;
        
        while (left <= right) {
            // If left element equals val, swap with right element
            if (array[left] == val) {
                array[left] = array[right];
                right--;  // Decrease right boundary
                // Don't increment left - check swapped element
            } else {
                // Left element is valid, keep it
                left++;
            }
        }
        
        // left is the number of valid elements
        return left;
    }
}
```

### In-Place Modification
We modify the array in-place by:
1. Writing valid elements to the front of the array
2. Skipping elements equal to `val`
3. Not caring about elements beyond the first `k` positions

### Order Preservation
- **Problem allows order change**: Elements can be in any order
- **Our approach preserves order**: We write elements in the order we encounter them
- **Alternative approach**: Can swap from both ends for efficiency when `val` is rare

### Time & Space Complexity

#### Approach 1: Two Pointers (Read-Write)
- **Time Complexity**: O(n) - Single pass through array
  - Best case: O(n) - No elements equal to val
  - Average case: O(n) - Some elements equal to val
  - Worst case: O(n) - All elements equal to val (still one pass)
- **Space Complexity**: O(1) - Only using a few variables (writeIndex, readIndex)

#### Approach 2: Two Pointers (Swap from Ends)
- **Time Complexity**: O(n) - Each element visited at most once
  - More efficient when `val` is rare (fewer writes)
- **Space Complexity**: O(1) - Only using variables (left, right)

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given an array and a value, remove all occurrences of that value in-place and return the new length."

**Candidate**: "I'll use two pointers. One pointer will track where valid elements should be written, and another will scan through the array. When I encounter an element that's not equal to the value, I'll write it at the write position and increment the write pointer."

**Interviewer**: "Can you walk me through your approach?"

**Candidate**: "Sure. I'll initialize the write pointer at 0. Then I'll iterate through the array with a read pointer. For each element, if it's not equal to val, I'll write it at the writeIndex position and increment writeIndex. If it equals val, I'll just skip it. After processing all elements, writeIndex represents the number of valid elements."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we make a single pass through the array. Space complexity is O(1) since we only use a few variables and modify the array in-place."

### Follow-up Questions

**Interviewer**: "What if we need to preserve the order of elements?"

**Candidate**: "The approach I described already preserves order - we write elements in the order we encounter them. If order didn't matter, we could use a two-pointer approach from both ends, swapping elements when we find val, which could be more efficient when val is rare."

**Interviewer**: "What if val appears many times?"

**Candidate**: "The algorithm handles this efficiently. We simply skip all occurrences of val and only write non-val elements. The time complexity remains O(n) regardless of how many times val appears."

**Interviewer**: "Can you optimize for the case when val is rare?"

**Candidate**: "Yes, we can use a two-pointer approach from both ends. When we find val at the left pointer, we swap it with the element at the right pointer and decrement right. This reduces the number of writes when val is rare, though it doesn't preserve order."

**Interviewer**: "What if the array is very large?"

**Candidate**: "The two-pointer approach is still efficient - O(n) time and O(1) space. It's optimal for this problem. The in-place modification is memory-efficient."

**Interviewer**: "Can you solve this without modifying the original array?"

**Candidate**: "We could create a new array, but that would use O(n) space. The problem specifically asks for in-place modification, which is more space-efficient."

### Tricky Edge Cases

1. **All elements are val**: `[3,3,3], val=3` → Return `0` (no valid elements)
2. **No elements are val**: `[1,2,3], val=4` → Return `3` (all elements valid)
3. **Empty array**: `[], val=1` → Return `0` (no elements)
4. **Val at boundaries**: `[1,2,1], val=1` → Return `1` (only middle element valid)
5. **Single element, equals val**: `[5], val=5` → Return `0`
6. **Single element, not val**: `[5], val=3` → Return `1`
7. **Val appears once**: `[1,2,3,4], val=2` → Return `3`
8. **Val appears multiple times**: `[1,2,2,3,2,4], val=2` → Return `3`
9. **Val at start and end**: `[3,1,2,3], val=3` → Return `2`
10. **Negative numbers**: `[-1,2,-1,3], val=-1` → Return `2`
11. **Zero as val**: `[0,1,0,2], val=0` → Return `2`
12. **Large numbers**: `[Integer.MAX_VALUE, 1, Integer.MAX_VALUE], val=Integer.MAX_VALUE` → Return `1`

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll start by initializing the write pointer at 0. This pointer tracks where the next valid element should be written. Then I'll iterate through the array with a read pointer. For each element, I'll check if it's not equal to val. If it's not equal to val, it's a valid element, so I'll write it at the writeIndex position and increment writeIndex. If it equals val, I'll just continue to the next element without writing. After processing all elements, writeIndex represents the number of valid elements, so I'll return it."

**Interviewer**: "Why do we write elements instead of just counting?"

**Candidate**: "The problem requires in-place modification - we need to place valid elements in the first k positions of the array. Just counting wouldn't satisfy this requirement. The two-pointer technique allows us to both count and rearrange elements in a single pass."

**Interviewer**: "What happens to elements beyond the first k positions?"

**Candidate**: "The problem states that elements beyond the first k positions don't matter. They can be anything - the original values, or we could leave them as is. The important part is that the first k elements contain all valid (non-val) elements."

## Solution Approaches

### Approach 1: Two Pointers (Read-Write) - Recommended
Use write pointer for result position, read pointer to scan. O(n) time, O(1) space. Preserves order.

**Algorithm:**
1. Initialize writeIndex = 0
2. Iterate with readIndex from 0 to end
3. If array[readIndex] != val, write at writeIndex and increment writeIndex
4. Return writeIndex

**Advantages:**
- O(1) space complexity
- Single pass through array
- Preserves order of elements
- Simple and intuitive

### Approach 2: Two Pointers (Swap from Ends)
Use left and right pointers, swap when finding val. O(n) time, O(1) space. Doesn't preserve order but fewer writes when val is rare.

**Algorithm:**
1. Initialize left = 0, right = length - 1
2. While left <= right:
   - If array[left] == val, swap with array[right] and decrement right
   - Else, increment left
3. Return left

**Advantages:**
- Fewer writes when val is rare
- Still O(n) time

**Disadvantages:**
- Doesn't preserve order
- More complex logic

### Approach 3: Using Extra Array (Not Recommended)
Create new array with valid elements. O(n) time, O(n) space.

**Disadvantages:**
- Uses O(n) extra space
- Doesn't meet in-place requirement

## Key Takeaways

1. **Two pointers technique** for in-place array modification
2. **Write pointer** maintains result array position
3. **Read pointer** scans for valid elements
4. **Skip unwanted elements** by not writing them
5. **Order preservation** - write elements in encounter order
6. **Single pass** through array gives O(n) time
7. **O(1) space** - only using variables
8. **Edge cases matter** - empty array, all val, no val
9. **In-place modification** is space-efficient
10. **Problem allows order change** - can optimize with swap approach if needed
