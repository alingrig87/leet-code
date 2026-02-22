/**
 * LeetCode 283: Move Zeroes
 * 
 * Problem: Move all zeros to end while maintaining non-zero order.
 * 
 * Solution Approach: Two pointers technique
 * Time Complexity: O(n) - single or double pass
 * Space Complexity: O(1) - in-place modification
 */
class Solution {
    
    /**
     * Main solution using two pointers with swap
     * 
     * Key insight: Use write pointer to track where non-zero elements should go.
     * When we find a non-zero, swap it to the write position.
     * This automatically moves zeros to the end.
     * 
     * @param nums Array with zeros to move
     */
    public void moveZeroes(int[] nums) {
        // Edge case: empty or single element
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        // Write pointer: tracks the position where next non-zero should be placed
        int writeIndex = 0;
        
        // Read pointer: iterates through entire array
        for (int readIndex = 0; readIndex < nums.length; readIndex++) {
            // If current element is non-zero, we want to move it to the front
            if (nums[readIndex] != 0) {
                // Swap current element with element at writeIndex
                // This moves non-zero to front and zero to current position
                // If writeIndex == readIndex, swap is no-op (same element)
                int temp = nums[writeIndex];
                nums[writeIndex] = nums[readIndex];
                nums[readIndex] = temp;
                
                // Move write pointer forward
                // Now writeIndex points to next position for non-zero element
                writeIndex++;
            }
            // If nums[readIndex] == 0, we skip it (don't increment writeIndex)
            // This leaves zeros in their positions, which will be swapped later
            // or remain at the end
        }
        // After loop, all non-zeros are at positions [0, writeIndex)
        // All zeros are at positions [writeIndex, nums.length)
    }
}

/**
 * Alternative: Two passes (move then fill)
 * 
 * First pass: move non-zeros to front
 * Second pass: fill remaining with zeros
 */
class SolutionTwoPass {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        // First pass: move all non-zero elements to the front
        int writeIndex = 0;
        for (int readIndex = 0; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != 0) {
                // Place non-zero at writeIndex
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }
        
        // Second pass: fill remaining positions with zeros
        // All positions from writeIndex to end should be zero
        for (int i = writeIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}

/**
 * Alternative: Optimized swap (only swap when needed)
 * 
 * Only swap when writeIndex != readIndex to avoid unnecessary operations.
 */
class SolutionOptimizedSwap {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int writeIndex = 0;
        for (int readIndex = 0; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != 0) {
                // Only swap if positions are different
                // This avoids unnecessary swap operations
                if (writeIndex != readIndex) {
                    nums[writeIndex] = nums[readIndex];
                    nums[readIndex] = 0;
                }
                writeIndex++;
            }
        }
    }
}

/**
 * Follow-up: What if we need to move zeros to the beginning?
 * 
 * Process from right to left instead.
 */
class SolutionMoveZerosToStart {
    public void moveZeroesToStart(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        // Write pointer starts from the end
        int writeIndex = nums.length - 1;
        
        // Process from right to left
        for (int readIndex = nums.length - 1; readIndex >= 0; readIndex--) {
            if (nums[readIndex] != 0) {
                // Swap non-zero to the end (which becomes "front" in reverse)
                if (writeIndex != readIndex) {
                    int temp = nums[writeIndex];
                    nums[writeIndex] = nums[readIndex];
                    nums[readIndex] = temp;
                }
                writeIndex--;
            }
        }
    }
}
