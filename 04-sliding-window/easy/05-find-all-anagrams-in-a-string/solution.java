import java.util.*;

/**
 * LeetCode 438: Find All Anagrams in a String
 * 
 * Problem: Find all start indices of anagrams of p in s.
 * 
 * Solution Approach: Sliding window with frequency map
 * Time Complexity: O(n)
 * Space Complexity: O(1) - fixed size arrays
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        if (s.length() < p.length()) {
            return result;
        }
        
        // Frequency map for p
        int[] pFreq = new int[26];
        for (char c : p.toCharArray()) {
            pFreq[c - 'a']++;
        }
        
        // Frequency map for sliding window
        int[] windowFreq = new int[26];
        
        // Initialize window
        for (int i = 0; i < p.length(); i++) {
            windowFreq[s.charAt(i) - 'a']++;
        }
        
        // Check first window
        if (Arrays.equals(pFreq, windowFreq)) {
            result.add(0);
        }
        
        // Slide window
        for (int i = p.length(); i < s.length(); i++) {
            // Remove left character
            windowFreq[s.charAt(i - p.length()) - 'a']--;
            // Add right character
            windowFreq[s.charAt(i) - 'a']++;
            
            // Check if window is anagram
            if (Arrays.equals(pFreq, windowFreq)) {
                result.add(i - p.length() + 1);
            }
        }
        
        return result;
    }
}
