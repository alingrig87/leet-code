import java.util.*;

/**
 * LeetCode 49: Group Anagrams
 * 
 * Problem: Group strings that are anagrams of each other.
 * 
 * Solution Approach: HashMap with sorted string as key
 * Time Complexity: O(n * k log k) where n is strings count, k is avg length
 * Space Complexity: O(n * k) - HashMap stores all strings
 */
class Solution {
    
    /**
     * Main solution using HashMap with sorted key
     * 
     * Key insight: Anagrams have the same characters.
     * If we sort the characters, all anagrams will have the same sorted string.
     * We use this sorted string as a key in HashMap to group anagrams.
     * 
     * @param strs Array of strings to group
     * @return List of groups, where each group contains anagrams
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // Edge case: empty input
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        // HashMap: key = sorted string (represents anagram pattern)
        //          value = list of strings that are anagrams
        // All anagrams will have the same sorted key, so they'll be grouped together
        Map<String, List<String>> map = new HashMap<>();
        
        // Process each string
        for (String str : strs) {
            // Convert string to character array for sorting
            char[] chars = str.toCharArray();
            
            // Sort the characters
            // After sorting, all anagrams will have the same sorted string
            // Example: "eat" -> ['a','e','t'], "tea" -> ['a','e','t']
            Arrays.sort(chars);
            
            // Convert sorted array back to string to use as key
            String key = new String(chars);
            
            // Get or create the list for this anagram group
            // If key doesn't exist, create new list
            // If key exists, get existing list
            map.putIfAbsent(key, new ArrayList<>());
            
            // Add original string to the group
            // All strings with same sorted key are anagrams
            map.get(key).add(str);
        }
        
        // Return all groups (all values from HashMap)
        // Each value is a list of anagrams
        return new ArrayList<>(map.values());
    }
}

/**
 * Alternative Solution: Using frequency string as key
 * 
 * Instead of sorting, count character frequencies and create a key.
 * This is O(n * k) time instead of O(n * k log k).
 */
class SolutionFrequencyString {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // Create frequency array for 26 lowercase letters
            int[] freq = new int[26];
            
            // Count frequency of each character
            for (char c : str.toCharArray()) {
                freq[c - 'a']++;
            }
            
            // Build key string from frequency array
            // Format: "a2b1c3" means 2 a's, 1 b, 3 c's
            // This creates a unique key for each anagram pattern
            StringBuilder keyBuilder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (freq[i] > 0) {
                    keyBuilder.append((char)('a' + i));
                    keyBuilder.append(freq[i]);
                }
            }
            String key = keyBuilder.toString();
            
            // Group strings with same frequency pattern
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
}

/**
 * Alternative: Using character count array as key (more efficient)
 * 
 * Convert frequency array to a more compact representation.
 */
class SolutionCompactKey {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // Count character frequencies
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            
            // Create key by converting count array to string
            // Use delimiter to separate counts: "#2#0#1#..." 
            // This ensures unique representation
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
}

/**
 * Follow-up: What if we need to return in a specific order?
 * 
 * We can sort the groups or individual strings within groups.
 */
class SolutionSorted {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        
        // Sort each group
        List<List<String>> result = new ArrayList<>(map.values());
        for (List<String> group : result) {
            Collections.sort(group);
        }
        
        // Sort groups by first element
        result.sort((a, b) -> a.get(0).compareTo(b.get(0)));
        
        return result;
    }
}
