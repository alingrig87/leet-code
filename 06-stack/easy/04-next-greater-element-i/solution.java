import java.util.*;

/**
 * LeetCode 496: Next Greater Element I
 * 
 * Problem: Find next greater element for nums1 in nums2.
 * 
 * Solution Approach: Monotonic stack
 * Time Complexity: O(n + m)
 * Space Complexity: O(n)
 */
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        
        // Process nums2 from right to left
        for (int i = nums2.length - 1; i >= 0; i--) {
            // Pop smaller elements
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            
            // Next greater is stack top (or -1 if empty)
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            
            // Push current element
            stack.push(nums2[i]);
        }
        
        // Lookup for nums1
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }
        
        return result;
    }
}
