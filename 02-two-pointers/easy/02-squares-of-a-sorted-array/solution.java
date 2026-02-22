/**
 * LeetCode 977: Squares of a Sorted Array
 * 
 * Problem: Square each number in sorted array and return sorted result.
 * 
 * Solution Approach: Two pointers from ends
 * Time Complexity: O(n) - single pass
 * Space Complexity: O(n) - result array
 */
class Solution {
    
    /**
     * Main solution using two pointers from ends
     * 
     * Key insight: Since array is sorted, the largest squares
     * will be at the ends - either most negative or most positive numbers.
     * Use two pointers from both ends, compare squares,
     * and place larger square at end of result (working backwards).
     * 
     * @param nums Sorted array (can contain negatives)
     * @return Array of squares in sorted order
     */
    public int[] sortedSquares(int[] nums) {
        // Edge case: empty array
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n];
        
        // Two pointers: one from left, one from right
        int left = 0;
        int right = n - 1;
        
        // Result pointer: fill from end (largest squares first)
        int resultIndex = n - 1;
        
        // Process until pointers meet
        while (left <= right) {
            // Calculate squares
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            
            // Compare squares
            // Larger square goes at end of result (since we want descending order)
            if (leftSquare > rightSquare) {
                // Left square is larger, place it at end
                result[resultIndex] = leftSquare;
                left++; // Move left pointer right
            } else {
                // Right square is larger or equal, place it at end
                result[resultIndex] = rightSquare;
                right--; // Move right pointer left
            }
            
            // Move result pointer backwards (towards start)
            resultIndex--;
        }
        
        return result;
    }
}

/**
 * Alternative: More explicit version
 */
class SolutionExplicit {
    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        int index = n - 1;
        
        while (left <= right) {
            int leftVal = Math.abs(nums[left]);
            int rightVal = Math.abs(nums[right]);
            
            if (leftVal > rightVal) {
                result[index--] = nums[left] * nums[left];
                left++;
            } else {
                result[index--] = nums[right] * nums[right];
                right--;
            }
        }
        
        return result;
    }
}

/**
 * Alternative: Square then sort (less efficient)
 * 
 * Time: O(n log n), Space: O(n)
 */
class SolutionSort {
    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        // Square all elements
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        
        // Sort the squared array
        Arrays.sort(nums);
        
        return nums;
    }
}

/**
 * Follow-up: What if we need to do this in-place?
 * 
 * We can square in-place, but sorting still needed.
 */
class SolutionInPlace {
    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        // Square in-place
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        
        // Sort in-place
        Arrays.sort(nums);
        
        return nums;
    }
}
