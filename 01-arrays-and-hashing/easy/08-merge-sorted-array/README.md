# Merge Sorted Array

## Problem Statement
You are given two integer arrays `nums1` and `nums2`, sorted in non-decreasing order, and two integers `m` and `n`, representing the number of elements in `nums1` and `nums2` respectively.

Merge `nums2` into `nums1` in sorted order. The final sorted array should not be returned by the function, but instead be stored inside the array `nums1`. To accommodate this, `nums1` has a length of `m + n`, where the first `m` elements denote the elements that should be merged, and the last `n` elements are set to `0` and should be ignored. `nums2` has a length of `n`.

**Example 1:**
```
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
```

**Example 2:**
```
Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: nums2 is empty, so nums1 remains unchanged.
```

**Example 3:**
```
Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: nums1 has no elements, so we just copy nums2 into nums1.
```

## Theory & Data Structures

### Two Pointers from End
This problem uses the **two pointers** technique, but with a crucial insight: merge from right to left (largest to smallest) to avoid overwriting unprocessed elements.

#### Why Merge from End?
1. **Extra Space at End**: `nums1` has `n` extra spaces at the end (filled with 0s)
2. **Avoid Overwriting**: If we merge from the start, we'd overwrite elements in `nums1` before processing them
3. **Use Extra Space First**: By merging from the end, we use the extra space first, so we never overwrite unprocessed data

#### Building Merge from End from Scratch (Conceptual)
```java
// Conceptual implementation of merging from end
class ArrayMerger {
    private int[] nums1;
    private int[] nums2;
    private int m, n;
    
    ArrayMerger(int[] nums1, int m, int[] nums2, int n) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.m = m;
        this.n = n;
    }
    
    // Merge nums2 into nums1 from right to left
    public void merge() {
        // Pointers for nums1 and nums2, starting from the end
        int i = m - 1;      // Last valid element in nums1
        int j = n - 1;      // Last element in nums2
        int k = m + n - 1;  // Last position in nums1 (where to write)
        
        // Merge while both arrays have elements
        while (i >= 0 && j >= 0) {
            // Compare elements from the end
            if (nums1[i] > nums2[j]) {
                // nums1[i] is larger, place it at position k
                nums1[k] = nums1[i];
                i--;  // Move nums1 pointer left
            } else {
                // nums2[j] is larger or equal, place it at position k
                nums1[k] = nums2[j];
                j--;  // Move nums2 pointer left
            }
            k--;  // Move write position left
        }
        
        // If nums2 has remaining elements, copy them
        // (nums1 remaining elements are already in place)
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
        
        // Note: We don't need to copy remaining nums1 elements
        // because they're already in the correct positions
    }
    
    // Alternative: More explicit version
    public void mergeExplicit() {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        
        // Process from right to left
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        
        // Copy remaining elements from nums2 (if any)
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
        
        // No need to copy remaining nums1 elements - they're already in place
    }
}
```

### In-Place Merging
The key insight is using the extra space at the end of `nums1`:
- Start merging from the largest elements (right side)
- Work backwards to smallest elements (left side)
- This ensures we never overwrite unprocessed elements

### Time & Space Complexity

#### Approach: Two Pointers from End
- **Time Complexity**: O(m + n) - Each element is processed exactly once
  - Best case: O(m + n)
  - Average case: O(m + n)
  - Worst case: O(m + n)
- **Space Complexity**: O(1) - Only using a few variables (i, j, k)
  - No additional data structures needed
  - In-place modification

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given two sorted arrays, merge the second into the first in-place."

**Candidate**: "Since nums1 has extra space at the end, I'll merge from right to left. I'll compare the largest elements from both arrays and place the larger one at the end of nums1, working backwards. This way, I use the extra space first and never overwrite unprocessed elements."

**Interviewer**: "Why merge from the end instead of the beginning?"

**Candidate**: "If we merge from the start, we'd overwrite elements in nums1 before we've processed them. For example, if nums1 = [1,2,3,0,0,0] and nums2 = [2,5,6], merging from the start would overwrite the 1 before we compare it with 2. By starting from the end, we use the extra space (the zeros) first, so we never overwrite valid data."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For nums1 = [1,2,3,0,0,0], m=3, nums2 = [2,5,6], n=3, I start with i=2 (last of nums1), j=2 (last of nums2), k=5 (last position). Compare 3 and 6: 6 is larger, so nums1[5]=6, j=1, k=4. Compare 3 and 5: 5 is larger, so nums1[4]=5, j=0, k=3. Compare 3 and 2: 3 is larger, so nums1[3]=3, i=1, k=2. Compare 2 and 2: equal, so nums1[2]=2, j=-1 (nums2 done), k=1. Copy remaining nums1: nums1[1]=2, nums1[0]=1. Result: [1,2,2,3,5,6]."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(m + n) since we process each element exactly once. Space complexity is O(1) since we only use a few variables and modify nums1 in-place."

### Follow-up Questions

**Interviewer**: "What if nums1 doesn't have extra space?"

**Candidate**: "Then we'd need to create a new array of size m+n, or use O(m) extra space to store nums1's elements temporarily before merging. The in-place approach wouldn't work."

**Interviewer**: "What if nums2 is empty?"

**Candidate**: "If nums2 is empty, we don't need to do anything. nums1 already contains the correct elements, so we can return immediately. The algorithm handles this naturally - the while loop for nums2 won't execute."

**Interviewer**: "What if nums1 is empty (m=0)?"

**Candidate**: "Then we just copy all elements from nums2 into nums1. The algorithm handles this - the first while loop won't execute (since i < 0), and the second while loop will copy all elements from nums2."

**Interviewer**: "Can you optimize this further?"

**Candidate**: "The algorithm is already optimal - O(m + n) time and O(1) space. We could add early exits for edge cases (empty arrays), but the core algorithm is already efficient."

**Interviewer**: "What if the arrays have duplicates?"

**Candidate**: "The algorithm handles duplicates correctly. When elements are equal, we can place either one first. The relative order within each array is preserved, but the merge operation itself doesn't guarantee stability across arrays (though in this case, since we compare and place, it works correctly)."

### Tricky Edge Cases

1. **nums2 is empty**: `nums1=[1,2,3], m=3, nums2=[], n=0` → No change, return `[1,2,3]`
2. **nums1 is empty**: `nums1=[0,0], m=0, nums2=[1,2], n=2` → Copy nums2, result `[1,2]`
3. **All nums2 larger**: `nums1=[1,2,0,0], m=2, nums2=[3,4], n=2` → Result `[1,2,3,4]`
4. **All nums2 smaller**: `nums1=[3,4,0,0], m=2, nums2=[1,2], n=2` → Result `[1,2,3,4]`
5. **Single element each**: `nums1=[1,0], m=1, nums2=[2], n=1` → Result `[1,2]`
6. **Duplicates**: `nums1=[1,2,0,0], m=2, nums2=[2,3], n=2` → Result `[1,2,2,3]`
7. **nums1 all larger**: `nums1=[5,6,0,0], m=2, nums2=[1,2], n=2` → Result `[1,2,5,6]`
8. **nums1 all smaller**: `nums1=[1,2,0,0], m=2, nums2=[5,6], n=2` → Result `[1,2,5,6]`
9. **Interleaved**: `nums1=[1,3,0,0], m=2, nums2=[2,4], n=2` → Result `[1,2,3,4]`
10. **Negative numbers**: `nums1=[-2,0,0], m=1, nums2=[-1,1], n=2` → Result `[-2,-1,1]`
11. **Zeros in nums1**: `nums1=[0,1,0,0], m=2, nums2=[2,3], n=2` → Result `[0,1,2,3]`
12. **Large numbers**: Works with any integer values

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll initialize three pointers: i at m-1 (last valid element in nums1), j at n-1 (last element in nums2), and k at m+n-1 (last position in nums1 where we'll write). I'll merge from right to left. While both arrays have elements, I'll compare nums1[i] and nums2[j]. I'll place the larger one at position k, then decrement the appropriate pointers. After one array is exhausted, I'll copy remaining elements from the other array. Note that if nums1 has remaining elements, they're already in the correct positions, so I only need to copy remaining nums2 elements."

**Interviewer**: "Why don't we need to copy remaining nums1 elements?"

**Candidate**: "Because we're merging into nums1 itself. If nums1 has remaining elements after nums2 is exhausted, those elements are already in their correct final positions - they don't need to be moved. We only need to copy remaining nums2 elements into the empty spaces at the beginning of nums1."

**Interviewer**: "What happens if we merge from left to right?"

**Candidate**: "We'd overwrite elements in nums1 before processing them. For example, if we start merging from the left, we might overwrite nums1[0] before we've compared it with all elements in nums2. This would cause data loss and incorrect results."

## Solution Approaches

### Approach 1: Two Pointers from End (Optimal)
Merge from right to left, using extra space at end. O(m+n) time, O(1) space.

**Algorithm:**
1. Initialize i = m-1, j = n-1, k = m+n-1
2. While i >= 0 and j >= 0:
   - If nums1[i] > nums2[j], place nums1[i] at k, decrement i
   - Else, place nums2[j] at k, decrement j
   - Decrement k
3. Copy remaining nums2 elements (if any)
4. nums1 remaining elements are already in place

**Advantages:**
- O(1) space complexity
- Single pass through both arrays
- In-place modification
- Optimal time complexity

### Approach 2: Using Extra Array (Not Recommended)
Create new array, merge into it, copy back. O(m+n) time, O(m+n) space.

**Disadvantages:**
- Uses O(m+n) extra space
- Doesn't meet in-place requirement
- Less efficient

### Approach 3: Merge from Start with Extra Space (Not Recommended)
Store nums1 elements temporarily, merge from start. O(m+n) time, O(m) space.

**Disadvantages:**
- Uses O(m) extra space
- More complex
- Less efficient than merging from end

## Key Takeaways

1. **Merge from end** to avoid overwriting unprocessed elements
2. **Use extra space** in nums1 efficiently (the zeros at the end)
3. **Three pointers** - one for each array and one for write position
4. **Handle remaining elements** - copy remaining nums2, nums1 already in place
5. **O(1) space** - only using variables
6. **O(m+n) time** - process each element once
7. **Edge cases matter** - empty arrays, all elements from one array
8. **In-place modification** is space-efficient
9. **Right-to-left merging** is the key insight
10. **No need to copy remaining nums1** - already in correct positions
