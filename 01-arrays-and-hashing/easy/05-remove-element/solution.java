/**
 * LeetCode 27: Remove Element
 * 
 * Problem: Remove all occurrences of val from array in-place.
 * Return the number of elements not equal to val.
 * 
 * Solution Approach: Two pointers technique
 * Time Complexity: O(n) - single pass
 * Space Complexity: O(1) - in-place modification
 */
class Solution {
    
    /**
     * Main solution using two pointers
     * 
     * Key insight: Use write pointer to track where valid elements go.
     * Only write elements that are not equal to val.
     * 
     * @param nums Input array
     * @param val Value to remove
     * @return Number of elements not equal to val
     */
    public int removeElement(int[] nums, int val) {
        // Edge case: empty array
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Write pointer: tracks the position where next valid element should be written
        // Valid elements are those not equal to val
        int writeIndex = 0;
        
        // Read pointer: iterates through entire array
        // We check each element and decide whether to keep it
        for (int readIndex = 0; readIndex < nums.length; readIndex++) {
            // If current element is not equal to val, it's a valid element
            // We want to keep this element in our result
            if (nums[readIndex] != val) {
                // Write this valid element at writeIndex position
                // This moves all valid elements to the front of the array
                nums[writeIndex] = nums[readIndex];
                
                // Move write pointer forward for next valid element
                writeIndex++;
            }
            // If nums[readIndex] == val, we skip it (don't write it)
            // This effectively removes it from the result
        }
        
        // Write pointer now points to position after last valid element
        // So writeIndex equals the count of valid elements
        return writeIndex;
    }
}

/**
 * Alternative: Using while loop with two pointers from both ends
 * 
 * This approach can be more efficient when val appears rarely,
 * as we can swap elements from the end.
 */
class SolutionTwoEnds {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Left pointer: scans from start, finds elements equal to val
        int left = 0;
        // Right pointer: scans from end, finds elements not equal to val
        int right = nums.length - 1;
        
        while (left <= right) {
            // If left element is val, we want to remove it
            if (nums[left] == val) {
                // Swap with right element (which is not val)
                // This moves val to the end
                nums[left] = nums[right];
                // Decrement right since we've used that position
                right--;
                // Don't increment left - we need to check the swapped element
            } else {
                // Left element is valid, keep it and move forward
                left++;
            }
        }
        
        // Left now points to first position after valid elements
        return left;
    }
}

/**
 * Follow-up: What if we need to return the modified array?
 */
class SolutionReturnArray {
    public int[] removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int writeIndex = 0;
        for (int readIndex = 0; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != val) {
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }
        
        // Return copy of first k elements
        int[] result = new int[writeIndex];
        System.arraycopy(nums, 0, result, 0, writeIndex);
        return result;
    }
}
