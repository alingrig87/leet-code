/**
 * LeetCode 88: Merge Sorted Array
 * 
 * Problem: Merge nums2 into nums1 in sorted order in-place.
 * 
 * Solution Approach: Two pointers from end
 * Time Complexity: O(m + n)
 * Space Complexity: O(1)
 */
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }
        
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}
