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

## Theory & Data Structures

### Two Pointers from End
This problem uses **two pointers starting from the end** of both arrays. Since `nums1` has extra space at the end, we merge from right to left (largest to smallest) to avoid overwriting unprocessed elements.

#### Key Insight: Merge Backwards
- **Extra space at end**: nums1 has n extra spaces at the end
- **Merge from end**: Start with largest elements
- **Avoid overwriting**: By starting from end, we use extra space first
- **Three pointers**: One for nums1, one for nums2, one for result position

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of merge sorted arrays
class MergeSortedArrays {
    
    // Merge from end approach
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Pointers for nums1, nums2, and result position
        int i = m - 1;      // Last element in nums1
        int j = n - 1;      // Last element in nums2
        int k = m + n - 1;  // Last position in result array
        
        // Merge from end
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        
        // Copy remaining elements from nums2 (if any)
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
        
        // Note: Remaining elements in nums1 are already in place
    }
    
    // Why merge from end?
    // Because nums1 has extra space at the end
    // If we merge from start, we'd overwrite elements before processing them
    // By merging from end, we use extra space first
    
    // Alternative: If nums1 didn't have extra space
    public void mergeWithoutExtraSpace(int[] nums1, int m, int[] nums2, int n) {
        // Would need O(m) space to store nums1 temporarily
        int[] temp = new int[m];
        System.arraycopy(nums1, 0, temp, 0, m);
        
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (temp[i] <= nums2[j]) {
                nums1[k++] = temp[i++];
            } else {
                nums1[k++] = nums2[j++];
            }
        }
        
        while (i < m) nums1[k++] = temp[i++];
        while (j < n) nums1[k++] = nums2[j++];
    }
}
```

### Time & Space Complexity

#### Approach: Two Pointers from End
- **Time Complexity**: O(m + n) - Single pass
  - Compare and copy: O(m + n)
  - Copy remaining: O(n) worst case
  - Total: O(m + n)
- **Space Complexity**: O(1) - No extra space needed
  - Only pointers
  - In-place merge

## Interview Simulation

### Initial Discussion

**Interviewer**: "Merge two sorted arrays into the first one in-place."

**Candidate**: "Since nums1 has extra space at the end, I'll merge from right to left. I'll use three pointers: one for the last element in nums1, one for the last element in nums2, and one for the result position. I'll compare the largest elements from both arrays and place the larger one at the end, working backwards."

**Interviewer**: "Why merge from the end?"

**Candidate**: "If we merge from the start, we'd overwrite elements in nums1 before processing them. By starting from the end, we use the extra space first, so we don't overwrite unprocessed elements."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For nums1=[1,2,3,0,0,0], m=3, nums2=[2,5,6], n=3. Start: i=2 (nums1[2]=3), j=2 (nums2[2]=6), k=5. Compare 3 and 6: 6 is larger, place at k=5, j=1, k=4. Compare 3 and 5: 5 is larger, place at k=4, j=0, k=3. Compare 3 and 2: 3 is larger, place at k=3, i=1, k=2. Continue until done."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(m + n) since we process each element once. Space complexity is O(1) since we only use pointers."

### Follow-up Questions

**Interviewer**: "What if nums1 doesn't have extra space?"

**Candidate**: "Then we'd need O(m) extra space to store nums1's elements temporarily, then merge normally. The current approach takes advantage of the extra space in nums1."

**Interviewer**: "What if arrays have duplicates?"

**Candidate**: "The algorithm handles duplicates correctly - we compare and place elements, maintaining sorted order. Duplicates are preserved in the result."

### Tricky Edge Cases

1. **nums2 empty**: No change needed, nums1 already sorted
2. **nums1 empty (m=0)**: Copy all nums2 to nums1
3. **All nums2 larger**: Append nums2 to end
4. **All nums2 smaller**: Prepend nums2 (shift nums1)
5. **Equal elements**: Handle correctly, maintain order
6. **Single element each**: Simple comparison

## Solution Approaches

### Approach: Two Pointers from End (Optimal)
Merge from right to left, using extra space at end. O(m+n) time, O(1) space.

**Algorithm:**
1. Initialize i=m-1, j=n-1, k=m+n-1
2. While i>=0 and j>=0:
   - Compare nums1[i] and nums2[j]
   - Place larger at nums1[k]
   - Move pointers
3. Copy remaining nums2 elements (if any)
4. Note: Remaining nums1 elements already in place

**Advantages:**
- O(1) space complexity
- O(m+n) time complexity
- In-place merge
- Optimal solution

## Key Takeaways

1. **Merge from end** to avoid overwriting
2. **Use extra space** efficiently
3. **Handle remaining elements** after main loop
4. **O(m+n) time, O(1) space**
5. **Three pointers** for tracking
6. **Foundation for** merge sort and other merge problems
