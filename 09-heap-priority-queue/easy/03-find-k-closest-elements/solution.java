import java.util.*;

/**
 * LeetCode 658: Find K Closest Elements
 * 
 * Problem: Find k closest elements to x in sorted array.
 * 
 * Solution Approach: Binary search + two pointers
 * Time Complexity: O(log n + k)
 * Space Complexity: O(1) excluding result
 */
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Binary search to find position of x
        int left = 0;
        int right = arr.length - k;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            // If x is closer to arr[mid+k], move left
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        // Collect k elements starting from left
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }
        
        return result;
    }
}
