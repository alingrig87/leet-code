import java.util.*;

/**
 * LeetCode 128: Longest Consecutive Sequence
 * 
 * Problem: Find length of longest consecutive elements sequence in O(n) time.
 * 
 * Solution Approach: HashSet with sequence expansion
 * Time Complexity: O(n) - each number visited at most twice
 * Space Complexity: O(n) - HashSet storage
 */
class Solution {
    
    /**
     * Main solution using HashSet
     * 
     * Key insight: Convert array to HashSet for O(1) lookups.
     * For each number, check if it's the start of a sequence
     * (number-1 doesn't exist). If yes, expand the sequence
     * by checking consecutive numbers.
     * 
     * @param nums Unsorted array of integers
     * @return Length of longest consecutive sequence
     */
    public int longestConsecutive(int[] nums) {
        // Edge case: empty array
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Edge case: single element
        if (nums.length == 1) {
            return 1;
        }
        
        // Convert array to HashSet for O(1) membership testing
        // HashSet also automatically removes duplicates
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        int maxLength = 0; // Track maximum sequence length found
        
        // Iterate through each number in the set
        for (int num : numSet) {
            // Check if this number is the start of a sequence
            // A number is a sequence start if num-1 doesn't exist in the set
            // This ensures we only process each sequence once
            if (!numSet.contains(num - 1)) {
                // This is the start of a sequence
                // Now expand the sequence by checking consecutive numbers
                int currentNum = num;
                int currentLength = 1; // Start with length 1 (current number)
                
                // Expand sequence to the right (check num+1, num+2, etc.)
                // Continue as long as the next consecutive number exists
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;      // Move to next number
                    currentLength++;   // Increment sequence length
                }
                
                // Update maximum length if current sequence is longer
                maxLength = Math.max(maxLength, currentLength);
            }
            // If num-1 exists, this number is not a sequence start
            // It will be processed as part of another sequence
            // This avoids duplicate work
        }
        
        return maxLength;
    }
}

/**
 * Alternative: More explicit version with early exit
 */
class SolutionExplicit {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        int maxLength = 1; // At least one element exists
        
        for (int num : numSet) {
            // Only process if this is a sequence start
            if (!numSet.contains(num - 1)) {
                int length = 1;
                int next = num + 1;
                
                // Expand sequence
                while (numSet.contains(next)) {
                    length++;
                    next++;
                }
                
                maxLength = Math.max(maxLength, length);
            }
        }
        
        return maxLength;
    }
}

/**
 * Alternative: Using Union-Find (more complex, same complexity)
 * 
 * This approach uses Union-Find data structure but has same time complexity.
 * Less efficient in practice due to overhead.
 */
class SolutionUnionFind {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Map: number -> its parent in union-find
        Map<Integer, Integer> parent = new HashMap<>();
        
        // Initialize: each number is its own parent
        for (int num : nums) {
            parent.put(num, num);
        }
        
        // Union consecutive numbers
        for (int num : nums) {
            if (parent.containsKey(num + 1)) {
                union(parent, num, num + 1);
            }
        }
        
        // Count sizes of connected components
        Map<Integer, Integer> size = new HashMap<>();
        for (int num : parent.keySet()) {
            int root = find(parent, num);
            size.put(root, size.getOrDefault(root, 0) + 1);
        }
        
        // Return maximum size
        return size.values().stream().mapToInt(i -> i).max().orElse(0);
    }
    
    private int find(Map<Integer, Integer> parent, int x) {
        if (parent.get(x) != x) {
            parent.put(x, find(parent, parent.get(x)));
        }
        return parent.get(x);
    }
    
    private void union(Map<Integer, Integer> parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX != rootY) {
            parent.put(rootX, rootY);
        }
    }
}

/**
 * Follow-up: What if we need to return the actual sequence?
 * 
 * We can track the sequence start and end, then reconstruct.
 */
class SolutionReturnSequence {
    public List<Integer> longestConsecutiveSequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        int maxLength = 0;
        int sequenceStart = 0;
        int sequenceEnd = 0;
        
        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int length = 1;
                
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    length++;
                }
                
                if (length > maxLength) {
                    maxLength = length;
                    sequenceStart = num;
                    sequenceEnd = currentNum;
                }
            }
        }
        
        // Reconstruct sequence
        List<Integer> result = new ArrayList<>();
        for (int i = sequenceStart; i <= sequenceEnd; i++) {
            result.add(i);
        }
        
        return result;
    }
}
