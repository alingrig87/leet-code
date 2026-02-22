import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 217: Contains Duplicate
 * 
 * Problem: Given an integer array nums, return true if any value appears 
 * at least twice in the array, and return false if every element is distinct.
 * 
 * Solution Approach: Use HashSet to track seen elements
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(n) - HashSet can store all elements in worst case
 */
class Solution {
    
    /**
     * Main solution method using HashSet
     * 
     * The idea is to use a HashSet to store elements we've encountered.
     * As we iterate, if we find an element already in the set, we have a duplicate.
     * 
     * @param nums The input array of integers
     * @return true if duplicates exist, false otherwise
     */
    public boolean containsDuplicate(int[] nums) {
        // Edge case: if array is empty or has only one element, no duplicates possible
        if (nums == null || nums.length <= 1) {
            return false;
        }
        
        // Create a HashSet to store unique elements we've seen
        // HashSet provides O(1) average-case lookup and insertion
        // We use Integer wrapper class since HashSet works with objects
        Set<Integer> seen = new HashSet<>();
        
        // Iterate through each element in the array
        // Time: O(n) where n is the length of the array
        for (int num : nums) {
            // Check if current element already exists in the set
            // HashSet.contains() is O(1) average case
            if (seen.contains(num)) {
                // Duplicate found! Return immediately (early exit optimization)
                return true;
            }
            
            // Add current element to the set for future lookups
            // HashSet.add() is O(1) average case
            seen.add(num);
        }
        
        // If we've iterated through entire array without finding duplicates
        return false;
    }
}

/**
 * Alternative Solution 1: Using Sorting
 * 
 * Time Complexity: O(n log n) - sorting dominates
 * Space Complexity: O(1) if in-place sort, O(n) if creating new array
 * 
 * This approach is better when memory is constrained but time is less critical.
 */
class SolutionSorting {
    public boolean containsDuplicate(int[] nums) {
        // Edge case handling
        if (nums == null || nums.length <= 1) {
            return false;
        }
        
        // Sort the array - duplicates will be adjacent after sorting
        // Arrays.sort() uses Dual-Pivot Quicksort: O(n log n) average case
        Arrays.sort(nums);
        
        // Check adjacent pairs for duplicates
        // After sorting, if duplicates exist, they'll be next to each other
        for (int i = 0; i < nums.length - 1; i++) {
            // Compare current element with next element
            if (nums[i] == nums[i + 1]) {
                return true; // Found duplicate
            }
        }
        
        return false; // No duplicates found
    }
}

/**
 * Alternative Solution 2: Brute Force (Not Recommended)
 * 
 * Time Complexity: O(n²) - nested loops
 * Space Complexity: O(1) - no extra space
 * 
 * This is inefficient but uses minimal memory.
 * Only use if memory is extremely constrained.
 */
class SolutionBruteForce {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        
        // Compare each element with all subsequent elements
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // If we find a match, we have a duplicate
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        
        return false;
    }
}

/**
 * Follow-up: What if we need to return the duplicate value?
 * 
 * We can modify the solution to return the first duplicate found.
 */
class SolutionWithReturnValue {
    public Integer findDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return null; // No duplicates possible
        }
        
        Set<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            if (seen.contains(num)) {
                return num; // Return the duplicate value
            }
            seen.add(num);
        }
        
        return null; // No duplicates found
    }
}

/**
 * Follow-up: What if we need to find all duplicates?
 * 
 * We can use a HashSet to track seen elements and another to track duplicates.
 */
class SolutionFindAllDuplicates {
    public List<Integer> findAllDuplicates(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();
        
        if (nums == null || nums.length <= 1) {
            return duplicates; // Empty list
        }
        
        // Set to track elements we've seen
        Set<Integer> seen = new HashSet<>();
        
        // Set to track duplicates (to avoid adding same duplicate multiple times)
        Set<Integer> duplicateSet = new HashSet<>();
        
        for (int num : nums) {
            if (seen.contains(num)) {
                // Found a duplicate, add to duplicate set if not already added
                if (!duplicateSet.contains(num)) {
                    duplicateSet.add(num);
                    duplicates.add(num);
                }
            } else {
                seen.add(num);
            }
        }
        
        return duplicates;
    }
}
