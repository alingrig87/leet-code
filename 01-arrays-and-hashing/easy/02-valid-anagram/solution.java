import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * LeetCode 242: Valid Anagram
 * 
 * Problem: Given two strings s and t, return true if t is an anagram of s.
 * An Anagram is a word formed by rearranging letters of another word.
 * 
 * Solution Approach: Count character frequencies and compare
 * Time Complexity: O(n) - two passes through strings
 * Space Complexity: O(1) for array approach, O(k) for HashMap
 */
class Solution {
    
    /**
     * Main solution using array frequency counter (optimal for lowercase letters)
     * 
     * Key insight: Anagrams have the same characters with same frequencies.
     * We count frequencies in first string, then decrement for second string.
     * If all counts end at zero, strings are anagrams.
     * 
     * @param s First string
     * @param t Second string
     * @return true if t is anagram of s, false otherwise
     */
    public boolean isAnagram(String s, String t) {
        // Early exit: if lengths differ, they can't be anagrams
        // This optimization saves us from unnecessary processing
        if (s.length() != t.length()) {
            return false;
        }
        
        // Frequency array for 26 lowercase English letters (a-z)
        // Index 0 represents 'a', index 1 represents 'b', ..., index 25 represents 'z'
        // This gives us O(1) space complexity (fixed size array)
        int[] freq = new int[26];
        
        // First pass: count characters in string s
        // For each character, increment its frequency count
        // 'a' - 'a' = 0, 'b' - 'a' = 1, ..., 'z' - 'a' = 25
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        // Second pass: decrement counts for characters in string t
        // If we see a character in t that wasn't in s, count will go negative
        for (char c : t.toCharArray()) {
            // Decrement frequency for this character
            freq[c - 'a']--;
            
            // Early exit: if count goes negative, t has more of this character than s
            // This means they can't be anagrams
            if (freq[c - 'a'] < 0) {
                return false;
            }
        }
        
        // Third pass: verify all frequencies are zero
        // If any frequency is non-zero, strings are not anagrams
        // However, if we've reached here and no count went negative,
        // and lengths are equal, all counts must be zero
        // (We can skip this check since we already verified lengths and no negative counts)
        
        return true;
    }
}

/**
 * Alternative Solution: Using HashMap (for general character sets)
 * 
 * This approach works for any characters including Unicode, uppercase, etc.
 * Time Complexity: O(n)
 * Space Complexity: O(k) where k is number of unique characters
 */
class SolutionHashMap {
    public boolean isAnagram(String s, String t) {
        // Early exit optimization
        if (s.length() != t.length()) {
            return false;
        }
        
        // HashMap to store character frequencies
        // Key: character, Value: frequency count
        // This approach handles any character set (Unicode, mixed case, etc.)
        Map<Character, Integer> freqMap = new HashMap<>();
        
        // Count characters in first string
        // For each character, increment its count in the map
        for (char c : s.toCharArray()) {
            // getOrDefault returns current count or 0 if not present
            // Then we increment by 1
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        // Decrement counts for characters in second string
        for (char c : t.toCharArray()) {
            // Get current count
            int count = freqMap.getOrDefault(c, 0);
            
            // If count is 0 or negative, this character appears more in t than in s
            if (count <= 0) {
                return false;
            }
            
            // Decrement the count
            freqMap.put(c, count - 1);
        }
        
        // Verify all counts are zero
        // If any value is non-zero, strings are not anagrams
        for (int count : freqMap.values()) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }
}

/**
 * Alternative Solution: Sorting
 * 
 * If two strings are anagrams, their sorted versions will be identical.
 * Time Complexity: O(n log n) - sorting dominates
 * Space Complexity: O(n) - need space for sorted character arrays
 */
class SolutionSorting {
    public boolean isAnagram(String s, String t) {
        // Early exit
        if (s.length() != t.length()) {
            return false;
        }
        
        // Convert strings to character arrays
        // This allows us to sort the characters
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        
        // Sort both arrays
        // Arrays.sort() uses Dual-Pivot Quicksort: O(n log n) average case
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        
        // Compare sorted arrays
        // If they're anagrams, sorted versions will be identical
        return Arrays.equals(sArray, tArray);
    }
}

/**
 * Optimized HashMap Solution (One Pass)
 * 
 * We can optimize by using a single HashMap and checking in one pass.
 */
class SolutionOptimizedHashMap {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        
        // Process both strings in parallel
        // Increment for s, decrement for t
        for (int i = 0; i < s.length(); i++) {
            // Increment for character in s
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            
            // Decrement for character in t
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }
        
        // Check all values are zero
        for (int count : map.values()) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }
}

/**
 * Follow-up: Case-insensitive anagram check
 * 
 * Convert both strings to lowercase before comparing.
 */
class SolutionCaseInsensitive {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        // Convert to lowercase for case-insensitive comparison
        s = s.toLowerCase();
        t = t.toLowerCase();
        
        int[] freq = new int[26];
        
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                freq[c - 'a']++;
            }
        }
        
        for (char c : t.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                freq[c - 'a']--;
                if (freq[c - 'a'] < 0) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
