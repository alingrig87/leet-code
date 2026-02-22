import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1: Two Sum
 * 
 * Problem: Given an array of integers nums and an integer target, 
 * return indices of the two numbers such that they add up to target.
 * 
 * Solution Approach: Use HashMap to store number-index pairs
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(n) - HashMap stores at most n elements
 */
class Solution {
    
    /**
     * Main solution using HashMap (One Pass)
     * 
     * The key insight is: if we're looking for a + b = target,
     * then for each 'a', we need to find 'b' where b = target - a.
     * 
     * We use HashMap to store numbers we've seen along with their indices.
     * As we iterate, for each number, we check if its complement exists.
     * 
     * @param nums The input array of integers
     * @param target The target sum we're looking for
     * @return Array of two indices [i, j] where nums[i] + nums[j] = target
     */
    public int[] twoSum(int[] nums, int target) {
        // Edge case: array must have at least 2 elements
        if (nums == null || nums.length < 2) {
            return new int[0]; // Return empty array
        }
        
        // HashMap to store: number -> index mapping
        // Key: the number we've seen
        // Value: the index where we saw it
        // This allows O(1) lookup for complement
        Map<Integer, Integer> map = new HashMap<>();
        
        // Iterate through the array once
        // Time: O(n) where n is the length of the array
        for (int i = 0; i < nums.length; i++) {
            // Calculate the complement: what number do we need to reach target?
            // If nums[i] + complement = target, then complement = target - nums[i]
            int complement = target - nums[i];
            
            // Check if we've seen the complement before
            // HashMap.containsKey() is O(1) average case
            if (map.containsKey(complement)) {
                // Found the pair! Return indices
                // map.get(complement) gives us the index where we saw the complement
                // i is the current index
                return new int[]{map.get(complement), i};
            }
            
            // We haven't seen the complement yet
            // Store current number and its index for future lookups
            // HashMap.put() is O(1) average case
            map.put(nums[i], i);
        }
        
        // According to problem statement, there's always exactly one solution
        // So we should never reach here, but return empty array for safety
        return new int[0];
    }
}

/**
 * Alternative Solution: Brute Force (Not Recommended)
 * 
 * Time Complexity: O(n²) - nested loops
 * Space Complexity: O(1) - no extra space
 * 
 * This approach checks all possible pairs.
 * Only use if memory is extremely constrained.
 */
class SolutionBruteForce {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        
        // Check every pair of indices
        for (int i = 0; i < nums.length; i++) {
            // Start j from i+1 to avoid using same element twice
            for (int j = i + 1; j < nums.length; j++) {
                // Check if this pair sums to target
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        
        return new int[0];
    }
}

/**
 * Alternative Solution: Two Pointers (For Sorted Array)
 * 
 * Time Complexity: O(n) if sorted, O(n log n) if need to sort
 * Space Complexity: O(1) if in-place sort
 * 
 * This approach works well when array is already sorted.
 * Note: This returns indices in sorted array, not original array!
 */
class SolutionTwoPointers {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        
        // Create array of pairs: (value, original_index)
        // We need to preserve original indices since we'll sort
        int[][] numsWithIndex = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            numsWithIndex[i][0] = nums[i]; // value
            numsWithIndex[i][1] = i;        // original index
        }
        
        // Sort by value
        Arrays.sort(numsWithIndex, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Two pointers approach
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int sum = numsWithIndex[left][0] + numsWithIndex[right][0];
            
            if (sum == target) {
                // Return original indices
                return new int[]{
                    Math.min(numsWithIndex[left][1], numsWithIndex[right][1]),
                    Math.max(numsWithIndex[left][1], numsWithIndex[right][1])
                };
            } else if (sum < target) {
                // Sum is too small, need larger number - move left pointer right
                left++;
            } else {
                // Sum is too large, need smaller number - move right pointer left
                right--;
            }
        }
        
        return new int[0];
    }
}

/**
 * Follow-up: What if we need to return the numbers, not indices?
 * 
 * We can modify to return the actual numbers instead of indices.
 */
class SolutionReturnNumbers {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        
        // Set to store numbers we've seen
        Set<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                // Return the numbers
                return new int[]{complement, num};
            }
            seen.add(num);
        }
        
        return new int[0];
    }
}

/**
 * Follow-up: What if there can be multiple solutions?
 * 
 * Return all pairs that sum to target.
 */
class SolutionMultipleAnswers {
    public List<int[]> twoSumAll(int[] nums, int target) {
        List<int[]> result = new ArrayList<>();
        
        if (nums == null || nums.length < 2) {
            return result;
        }
        
        // Map: number -> list of indices where it appears
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        // First pass: build the map
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        
        // Second pass: find all pairs
        Set<String> used = new HashSet<>(); // To avoid duplicates
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (map.containsKey(complement)) {
                for (int j : map.get(complement)) {
                    // Make sure we don't use same element twice
                    if (i != j) {
                        String pair = i < j ? i + "," + j : j + "," + i;
                        if (!used.contains(pair)) {
                            result.add(new int[]{i, j});
                            used.add(pair);
                        }
                    }
                }
            }
        }
        
        return result;
    }
}
