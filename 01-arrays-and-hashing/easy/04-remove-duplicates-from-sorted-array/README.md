# Remove Duplicates from Sorted Array

## Problem Statement
Given an integer array `nums` sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array `nums`. More formally, if there are `k` elements after removing the duplicates, then the first `k` elements of `nums` should hold the final result.

Return `k` after placing the final result in the first `k` slots of `nums`.

**Example 1:**
```
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
```

**Example 2:**
```
Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
```

## Theory & Data Structures

### Two Pointers Technique
This problem uses the **two pointers** technique, specifically the "slow and fast pointers" pattern. This is a fundamental technique for in-place array modifications.

#### How Two Pointers Work for This Problem
1. **Slow Pointer (write index)**: Points to the position where the next unique element should be written. This maintains the result array.
2. **Fast Pointer (read index)**: Iterates through the entire array to find unique elements.
3. **Key Insight**: Since the array is sorted, duplicates are adjacent. When we encounter a new unique element, it's guaranteed to be different from the last written element.

#### Building Two Pointers from Scratch (Conceptual)
```java
// Conceptual implementation of two pointers for removing duplicates
class DuplicateRemover {
    private int[] array;
    
    DuplicateRemover(int[] array) {
        this.array = array;
    }
    
    // Remove duplicates using two pointers
    public int removeDuplicates() {
        // Edge case: empty array
        if (array.length == 0) {
            return 0;
        }
        
        // Slow pointer: tracks position for next unique element
        // Starts at 1 because first element is always unique
        int writeIndex = 1;
        
        // Fast pointer: scans through array
        // Starts at 1 because we compare with previous element
        for (int readIndex = 1; readIndex < array.length; readIndex++) {
            // Since array is sorted, duplicates are adjacent
            // If current element is different from previous unique element,
            // it's a new unique element
            if (array[readIndex] != array[writeIndex - 1]) {
                // Found new unique element - write it at writeIndex
                array[writeIndex] = array[readIndex];
                // Move write pointer forward
                writeIndex++;
            }
            // If elements are equal, we skip (don't write duplicate)
        }
        
        // writeIndex now represents the number of unique elements
        return writeIndex;
    }
    
    // Alternative: compare with last written element
    public int removeDuplicatesAlternative() {
        if (array.length == 0) {
            return 0;
        }
        
        int uniqueCount = 1;  // First element is always unique
        
        for (int i = 1; i < array.length; i++) {
            // Compare with last unique element (at index uniqueCount - 1)
            if (array[i] != array[uniqueCount - 1]) {
                array[uniqueCount] = array[i];
                uniqueCount++;
            }
        }
        
        return uniqueCount;
    }
}
```

### In-Place Modification
Since the array is sorted, duplicates are adjacent. This allows us to:
1. Modify the array in-place without needing extra space
2. Use O(1) space complexity (only a few variables)
3. Maintain relative order of elements naturally

### Why Sorting is Critical
- **Sorted array**: Duplicates are adjacent → O(n) time, O(1) space solution possible
- **Unsorted array**: Duplicates can be anywhere → Need HashSet (O(n) space) or O(n²) time

### Time & Space Complexity

#### Approach: Two Pointers
- **Time Complexity**: O(n) - Single pass through array
  - Best case: O(n) - All elements unique
  - Average case: O(n) - Some duplicates
  - Worst case: O(n) - Many duplicates (still one pass)
- **Space Complexity**: O(1) - Only using a few variables (writeIndex, readIndex)
  - No additional data structures needed
  - Array modification is in-place

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given a sorted array, remove duplicates in-place and return the new length."

**Candidate**: "Since the array is sorted, duplicates will be adjacent. I'll use two pointers - one to track the position where unique elements should go, and another to iterate through the array."

**Interviewer**: "Can you walk me through your approach step by step?"

**Candidate**: "Sure. I'll start with the write pointer at index 1, since the first element is always unique. The read pointer also starts at index 1. As I iterate through the array with the read pointer, I'll compare each element with the last unique element (at writeIndex - 1). If they're different, I've found a new unique element, so I'll write it at the writeIndex position and increment the write pointer. If they're the same, I'll just skip it and continue."

**Interviewer**: "Why start both pointers at index 1?"

**Candidate**: "The first element is always unique - there's nothing before it to compare with. So we can keep it as is and start looking for the next unique element from index 1."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we make a single pass through the array. Space complexity is O(1) since we only use a few variables and modify the array in-place."

### Follow-up Questions

**Interviewer**: "What if the array isn't sorted?"

**Candidate**: "Then we'd need a different approach. We could use a HashSet to track seen elements, which would require O(n) extra space. Or we could sort first, but that changes the time complexity to O(n log n). The two-pointer approach only works efficiently because duplicates are adjacent in a sorted array."

**Interviewer**: "Can you do this with O(1) space if the array isn't sorted?"

**Candidate**: "Not efficiently. We'd need to check each element against all previous elements, giving O(n²) time complexity. Or we could use a two-pass approach with marking, but that's still not optimal."

**Interviewer**: "What if we need to remove duplicates but keep at most k occurrences of each element?"

**Candidate**: "We can modify the algorithm. Instead of comparing with writeIndex - 1, we'd compare with writeIndex - k. This ensures we keep at most k occurrences. For example, if k=2, we compare with the element two positions back."

**Interviewer**: "What if the array is very large?"

**Candidate**: "The two-pointer approach is still efficient - O(n) time and O(1) space. It's optimal for this problem. The only consideration is that we're modifying the array in-place, which is what the problem requires."

**Interviewer**: "Can you solve this recursively?"

**Candidate**: "Yes, but it's not recommended. Recursive solution would use O(n) space for the call stack, while iterative uses O(1) space. The iterative approach is cleaner and more efficient."

### Tricky Edge Cases

1. **Empty Array**: `[]` → Return `0` (no elements, so no unique elements)
2. **Single Element**: `[1]` → Return `1` (one unique element)
3. **All Same Elements**: `[1,1,1,1]` → Return `1` (only one unique element)
4. **No Duplicates**: `[1,2,3,4]` → Return `4` (all elements are unique)
5. **Negative Numbers**: `[-1,-1,0,1]` → Return `3` (works fine with negatives)
6. **Mixed Signs**: `[-2,-1,0,0,1,2]` → Return `5` (handles mixed signs)
7. **Large Numbers**: `[Integer.MAX_VALUE, Integer.MAX_VALUE]` → Return `1`
8. **Single Duplicate**: `[1,2,2,3]` → Return `3`
9. **Duplicates at Start**: `[1,1,2,3]` → Return `3`
10. **Duplicates at End**: `[1,2,3,3]` → Return `3`
11. **Alternating Pattern**: `[1,1,2,2,3,3]` → Return `3`
12. **All Zeros**: `[0,0,0]` → Return `1`

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll start by handling the edge case of an empty array - if the array is empty, return 0. Then I'll initialize the write pointer at index 1, since the first element is always unique and doesn't need to be moved. I'll iterate through the array starting from index 1. For each element, I'll compare it with the last unique element (at writeIndex - 1). If they're different, I've found a new unique element, so I'll write it at the writeIndex position and increment writeIndex. If they're the same, I'll just continue to the next element. After the loop, writeIndex represents the number of unique elements, so I'll return it."

**Interviewer**: "Why compare with writeIndex - 1 instead of the previous element in the original array?"

**Candidate**: "Because writeIndex - 1 points to the last unique element we've written. Since the array is sorted and we're writing unique elements in order, comparing with writeIndex - 1 ensures we're comparing with the correct previous unique element, not just any previous element. This is crucial for correctness."

**Interviewer**: "What happens if we don't modify the array in-place?"

**Candidate**: "If we create a new array, we'd use O(n) space. The problem specifically asks for in-place modification, which is more space-efficient. The two-pointer technique is perfect for this because we can overwrite elements we've already processed."

## Solution Approaches

### Approach 1: Two Pointers (Optimal)
Use slow pointer (writeIndex) for result position, fast pointer (readIndex) to scan. O(n) time, O(1) space.

**Algorithm:**
1. Handle edge case: empty array returns 0
2. Initialize writeIndex = 1 (first element is always unique)
3. Iterate with readIndex from 1 to end
4. Compare array[readIndex] with array[writeIndex - 1]
5. If different, write at writeIndex and increment writeIndex
6. Return writeIndex

**Advantages:**
- O(1) space complexity
- Single pass through array
- In-place modification
- Optimal time complexity

### Approach 2: Using Extra Array (Not Recommended)
Create new array with unique elements. O(n) time, O(n) space.

**Disadvantages:**
- Uses O(n) extra space
- Doesn't meet in-place requirement
- Less efficient

### Approach 3: HashSet (For Unsorted Array)
Use HashSet to track seen elements. O(n) time, O(n) space.

**When to use:**
- Only if array is not sorted
- Requires O(n) space
- Doesn't preserve order (unless using LinkedHashSet)

## Key Takeaways

1. **Sorted array enables O(1) space** solution - duplicates are adjacent
2. **Two pointers technique** is powerful for in-place array modifications
3. **Slow pointer (writeIndex)** tracks result position
4. **Fast pointer (readIndex)** finds next unique element
5. **Compare with last unique element** (writeIndex - 1), not just previous element
6. **First element is always unique** - start pointers at index 1
7. **In-place modification** is space-efficient
8. **Single pass** through array gives O(n) time complexity
9. **Edge cases matter** - empty array, single element, all duplicates
10. **Sorting is critical** - algorithm relies on sorted property
