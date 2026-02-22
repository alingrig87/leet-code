/**
 * LeetCode 238: Product of Array Except Self
 * 
 * Problem: Return array where each element is product of all other elements.
 * Must be O(n) time and O(1) extra space (excluding output array).
 * 
 * Solution Approach: Two passes - prefix and suffix products
 * Time Complexity: O(n) - two passes through array
 * Space Complexity: O(1) - only output array (not counting input/output)
 */
class Solution {
    
    /**
     * Main solution using two passes
     * 
     * Key insight: For each position i, we need:
     * - Product of all elements to the left of i (prefix product)
     * - Product of all elements to the right of i (suffix product)
     * - Result[i] = prefix[i] * suffix[i]
     * 
     * We can calculate prefix products in first pass (left to right),
     * then multiply by suffix products in second pass (right to left).
     * 
     * @param nums Input array
     * @return Array where result[i] = product of all elements except nums[i]
     */
    public int[] productExceptSelf(int[] nums) {
        // Edge case: empty array
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n];
        
        // First pass: Calculate prefix products (left to right)
        // result[i] will store product of all elements to the left of i
        // For index 0, there are no elements to the left, so product is 1
        result[0] = 1;
        
        // For each position i, multiply previous prefix product by nums[i-1]
        // This gives us product of all elements from index 0 to i-1
        for (int i = 1; i < n; i++) {
            // Prefix product at i = prefix product at i-1 * element at i-1
            result[i] = result[i - 1] * nums[i - 1];
        }
        
        // Second pass: Multiply by suffix products (right to left)
        // We calculate suffix product as we go and multiply with prefix product
        // suffix variable tracks product of all elements to the right
        int suffix = 1; // Start with 1 (no elements to the right of last index)
        
        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Multiply current prefix product (stored in result[i]) by suffix product
            // This gives us: (product of left elements) * (product of right elements)
            result[i] = result[i] * suffix;
            
            // Update suffix product for next iteration (moving left)
            // Multiply by current element to include it in suffix product
            suffix = suffix * nums[i];
        }
        
        return result;
    }
}

/**
 * Alternative: More explicit version with separate prefix and suffix arrays
 * 
 * This version uses O(n) extra space but is easier to understand.
 */
class SolutionExplicit {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int n = nums.length;
        
        // Calculate prefix products: prefix[i] = product of nums[0] to nums[i-1]
        int[] prefix = new int[n];
        prefix[0] = 1; // No elements to the left of index 0
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        
        // Calculate suffix products: suffix[i] = product of nums[i+1] to nums[n-1]
        int[] suffix = new int[n];
        suffix[n - 1] = 1; // No elements to the right of last index
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }
        
        // Multiply prefix and suffix for each position
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = prefix[i] * suffix[i];
        }
        
        return result;
    }
}

/**
 * Follow-up: What if we could use division?
 * 
 * We could calculate total product and divide by each element.
 * But need to handle zeros carefully.
 */
class SolutionWithDivision {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n];
        
        // Count zeros
        int zeroCount = 0;
        int zeroIndex = -1;
        long totalProduct = 1; // Use long to avoid overflow
        
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                zeroIndex = i;
            } else {
                totalProduct *= nums[i];
            }
        }
        
        // If more than one zero, all results are zero
        if (zeroCount > 1) {
            return result; // All zeros
        }
        
        // If exactly one zero, only that position gets non-zero result
        if (zeroCount == 1) {
            result[zeroIndex] = (int)totalProduct;
            return result;
        }
        
        // No zeros, divide total product by each element
        for (int i = 0; i < n; i++) {
            result[i] = (int)(totalProduct / nums[i]);
        }
        
        return result;
    }
}
