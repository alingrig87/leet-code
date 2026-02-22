# Longest Common Prefix

## Problem Statement
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string `""`.

**Example 1:**
```
Input: strs = ["flower","flow","flight"]
Output: "fl"
```

**Example 2:**
```
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
```

## Theory & Data Structures

### Vertical Scanning
This problem uses **vertical scanning** - comparing characters at the same position across all strings. We stop when we find a mismatch or reach the end of the shortest string.

#### Key Insight: Character-by-Character Comparison
- **Vertical scanning**: Compare characters at index i across all strings
- **Stop conditions**: Mismatch found or shortest string ends
- **Efficiency**: Stop early when mismatch found
- **Alternative**: Horizontal scanning (compare first string with others)

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of longest common prefix
class LongestCommonPrefix {
    
    // Vertical scanning approach
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        // Use first string as reference
        String first = strs[0];
        
        // Compare each character position
        for (int i = 0; i < first.length(); i++) {
            char c = first.charAt(i);
            
            // Check if all other strings have same character at position i
            for (int j = 1; j < strs.length; j++) {
                // If string is too short or character differs, return prefix so far
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return first.substring(0, i);
                }
            }
        }
        
        // First string is the common prefix
        return first;
    }
    
    // Horizontal scanning approach
    public String longestCommonPrefixHorizontal(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        String prefix = strs[0];
        
        // Compare prefix with each string
        for (int i = 1; i < strs.length; i++) {
            // Find common prefix between current prefix and current string
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        
        return prefix;
    }
    
    // Divide and conquer approach
    public String longestCommonPrefixDivide(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return divideAndConquer(strs, 0, strs.length - 1);
    }
    
    private String divideAndConquer(String[] strs, int left, int right) {
        if (left == right) {
            return strs[left];
        }
        
        int mid = (left + right) / 2;
        String leftPrefix = divideAndConquer(strs, left, mid);
        String rightPrefix = divideAndConquer(strs, mid + 1, right);
        
        return commonPrefix(leftPrefix, rightPrefix);
    }
    
    private String commonPrefix(String s1, String s2) {
        int minLen = Math.min(s1.length(), s2.length());
        for (int i = 0; i < minLen; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return s1.substring(0, i);
            }
        }
        return s1.substring(0, minLen);
    }
}
```

### Time & Space Complexity

#### Approach: Vertical Scanning
- **Time Complexity**: O(S) where S is sum of all characters
  - Best case: O(minLen * n) where minLen is shortest string
  - Average case: O(S)
  - Worst case: O(S) - All strings identical
- **Space Complexity**: O(1) - Only variables (excluding result)
  - Result string: O(minLen) but this is output

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find the longest common prefix among an array of strings."

**Candidate**: "I'll use vertical scanning - compare characters at the same position across all strings. I'll iterate through character positions, and for each position, check if all strings have the same character. I'll stop when I find a mismatch or reach the end of the shortest string."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For ['flower','flow','flight'], I check position 0: all have 'f', continue. Position 1: all have 'l', continue. Position 2: 'o' vs 'o' vs 'i' - mismatch at 'flight', so I return 'fl'."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(S) where S is the sum of all characters. In the worst case, we compare all characters. Space complexity is O(1) excluding the result string."

### Follow-up Questions

**Interviewer**: "What if strings are very long?"

**Candidate**: "Vertical scanning is still efficient because we stop as soon as we find a mismatch. We don't need to process the entire strings if the common prefix is short."

**Interviewer**: "Can you use a different approach?"

**Candidate**: "Yes, I could use horizontal scanning - start with the first string as prefix, then compare it with each subsequent string, shortening the prefix when there's a mismatch. Or I could use divide and conquer, but vertical scanning is usually most efficient."

### Tricky Edge Cases

1. **Empty array**: Return ""
2. **Single string**: Return that string
3. **No common prefix**: Return ""
4. **All same**: Return any string
5. **Empty strings**: Handle correctly
6. **One empty string**: Return ""

## Solution Approaches

### Approach 1: Vertical Scanning (Recommended)
Compare characters at same position. O(S) time, O(1) space.

**Advantages:**
- Efficient - stops early
- Simple implementation
- Optimal for most cases

### Approach 2: Horizontal Scanning
Compare first string with others. O(S) time, O(1) space.

**Similar complexity, different approach**

## Key Takeaways

1. **Vertical scanning** is efficient
2. **Stop on mismatch** or end of shortest
3. **O(S) time** solution
4. **Simple implementation**
5. **Early termination** when mismatch found
6. **Foundation for** string matching problems
