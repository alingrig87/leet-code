/**
 * LeetCode 88: Merge Sorted Array
 * 
 * Problem: Merge nums2 into nums1 in sorted order in-place.
 * nums1 has length m+n with first m elements valid.
 * 
 * Solution Approach: Two pointers from end
 * Time Complexity: O(m + n) - single pass
 * Space Complexity: O(1) - in-place merge
 */
class Solution {
    
    /**
     * Main solution using two pointers from end
     * 
     * Key insight: Merge from right to left (largest to smallest).
     * This uses the extra space at the end of nums1 first,
     * so we never overwrite unprocessed elements.
     * 
     * @param nums1 First sorted array with extra space at end
     * @param m Number of valid elements in nums1
     * @param nums2 Second sorted array
     * @param n Number of elements in nums2
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Edge case: nums2 is empty, nothing to merge
        if (n == 0) {
            return; // nums1 already has the result
        }
        
        // Edge case: nums1 has no valid elements, just copy nums2
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }
        
        // Three pointers:
        // i: points to last valid element in nums1 (from the m valid elements)
        // j: points to last element in nums2
        // k: points to last position in nums1 (where we'll place merged elements)
        int i = m - 1;  // Last index of valid data in nums1
        int j = n - 1;  // Last index in nums2
        int k = m + n - 1;  // Last index in nums1 (total length)
        
        // Merge from right to left (largest to smallest)
        // Continue until we've processed all elements from both arrays
        while (i >= 0 && j >= 0) {
            // Compare the largest remaining elements from both arrays
            if (nums1[i] > nums2[j]) {
                // nums1[i] is larger, place it at position k
                // This is safe because k is always >= i (we're going backwards)
                nums1[k] = nums1[i];
                i--;  // Move to next element in nums1
            } else {
                // nums2[j] is larger or equal, place it at position k
                nums1[k] = nums2[j];
                j--;  // Move to next element in nums2
            }
            k--;  // Move to next position in result
        }
        
        // After main loop, one of the arrays is exhausted
        // If nums2 still has elements, copy them to nums1
        // (If nums1 has remaining elements, they're already in correct positions)
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
        // Note: We don't need to handle remaining nums1 elements
        // because they're already in their correct positions
    }
}

/**
 * Alternative: More explicit version
 */
class SolutionExplicit {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }
        
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        
        // Merge while both arrays have elements
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
        
        // Remaining nums1 elements are already in place
    }
}

/**
 * Follow-up: What if we need to merge into a new array?
 * 
 * We can create a new array and merge normally.
 */
class SolutionNewArray {
    public int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m + n];
        int i = 0, j = 0, k = 0;
        
        // Merge from left to right (smallest to largest)
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                result[k++] = nums1[i++];
            } else {
                result[k++] = nums2[j++];
            }
        }
        
        // Copy remaining elements
        while (i < m) {
            result[k++] = nums1[i++];
        }
        while (j < n) {
            result[k++] = nums2[j++];
        }
        
        return result;
    }
}
