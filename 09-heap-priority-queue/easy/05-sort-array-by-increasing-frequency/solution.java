import java.util.*;

/**
 * LeetCode 1636: Sort Array by Increasing Frequency
 * 
 * Problem: Sort array by frequency, then by value for ties.
 * 
 * Solution Approach: HashMap + custom sort
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */
class Solution {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        
        // Count frequencies
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        // Convert to Integer array for sorting
        Integer[] numsInteger = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        
        // Sort with custom comparator
        Arrays.sort(numsInteger, (a, b) -> {
            int freqA = freq.get(a);
            int freqB = freq.get(b);
            
            // First compare by frequency (ascending)
            if (freqA != freqB) {
                return freqA - freqB;
            }
            
            // If frequencies equal, compare by value (descending)
            return b - a;
        });
        
        // Convert back to int array
        return Arrays.stream(numsInteger).mapToInt(i -> i).toArray();
    }
}
