/**
 * LeetCode 26: Remove Duplicates from Sorted Array
 * 
 * Problem: Given a sorted array, remove duplicates in-place.
 * Return the new length after removing duplicates.
 * 
 * Solution Approach: Two pointers technique
 * Time Complexity: O(n) - single pass
 * Space Complexity: O(1) - in-place modification
 */
class Solution {
    
    /**
     * Main solution using two pointers
     * 
     * Key insight: Since array is sorted, duplicates are adjacent.
     * We use slow pointer to track where unique elements should go,
     * and fast pointer to scan through the array.
     * 
     * @param nums Sorted array with duplicates
     * @return Length of array after removing duplicates
     */
    public int removeDuplicates(int[] nums) {
        // Edge case: empty array
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Edge case: single element (always unique)
        if (nums.length == 1) {
            return 1;
        }
        
        // Slow pointer: tracks the position where next unique element should be placed
        // Start at index 1 because first element (index 0) is always unique
        int slow = 1;
        
        // Fast pointer: iterates through entire array to find unique elements
        // Start at index 1 to compare with previous element
        for (int fast = 1; fast < nums.length; fast++) {
            // Check if current element is different from previous element
            // Since array is sorted, if nums[fast] != nums[fast-1],
            // then nums[fast] is a new unique element
            if (nums[fast] != nums[fast - 1]) {
                // Place this unique element at slow pointer position
                // This maintains the relative order of unique elements
                nums[slow] = nums[fast];
                
                // Move slow pointer forward for next unique element
                slow++;
            }
            // If nums[fast] == nums[fast-1], it's a duplicate, so we skip it
            // Fast pointer will continue to next element automatically
        }
        
        // Slow pointer now points to the position after last unique element
        // So slow is the count of unique elements
        return slow;
    }
}

/**
 * Alternative: More explicit version
 */
class SolutionExplicit {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Index for placing next unique element
        int writeIndex = 1;
        
        // Iterate through array starting from second element
        for (int readIndex = 1; readIndex < nums.length; readIndex++) {
            // If current element is different from the last written element
            // (which is at writeIndex - 1), it's a new unique element
            if (nums[readIndex] != nums[writeIndex - 1]) {
                // Write this unique element
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }
        
        return writeIndex;
    }
}

/**
 * Follow-up: What if we need to return the modified array?
 * 
 * We can return a copy of the first k elements.
 */
class SolutionReturnArray {
    public int[] removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int slow = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        
        // Return copy of first k elements
        int[] result = new int[slow];
        System.arraycopy(nums, 0, result, 0, slow);
        return result;
    }
}
