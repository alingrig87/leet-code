# Implement strStr()

## Problem Statement
Given two strings `needle` and `haystack`, return the index of the first occurrence of `needle` in `haystack`, or `-1` if `needle` is not part of `haystack`.

**Example 1:**
```
Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6. The first occurrence is at index 0, so we return 0.
```

**Example 2:**
```
Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.
```

## Theory & Data Structures

### Sliding Window Approach
This problem uses a **sliding window** of size `needle.length()` to check each position in `haystack` for a match.

#### Key Insight: Character-by-Character Comparison
- **Window size**: Fixed at `needle.length()`
- **Slide through haystack**: Check each position
- **Compare**: Check if substring matches needle
- **Early exit**: Return index as soon as match found

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of strStr
class StrStrImplementation {
    
    // Simple sliding window approach
    public int strStr(String haystack, String needle) {
        int haystackLen = haystack.length();
        int needleLen = needle.length();
        
        // Edge case: empty needle
        if (needleLen == 0) {
            return 0;
        }
        
        // Edge case: needle longer than haystack
        if (needleLen > haystackLen) {
            return -1;
        }
        
        // Slide window through haystack
        for (int i = 0; i <= haystackLen - needleLen; i++) {
            // Check if substring starting at i matches needle
            if (haystack.substring(i, i + needleLen).equals(needle)) {
                return i;
            }
        }
        
        return -1;
    }
    
    // Optimized: Character-by-character comparison
    public int strStrOptimized(String haystack, String needle) {
        int haystackLen = haystack.length();
        int needleLen = needle.length();
        
        if (needleLen == 0) {
            return 0;
        }
        
        if (needleLen > haystackLen) {
            return -1;
        }
        
        for (int i = 0; i <= haystackLen - needleLen; i++) {
            int j = 0;
            // Compare characters one by one
            while (j < needleLen && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            // If all characters matched, return index
            if (j == needleLen) {
                return i;
            }
        }
        
        return -1;
    }
    
    // KMP Algorithm (advanced, O(n+m) time)
    // Uses prefix function to avoid re-checking characters
    // More complex but optimal for repeated patterns
}
```

### Time & Space Complexity

#### Approach: Sliding Window
- **Time Complexity**: O(n * m) - n is haystack length, m is needle length
  - Best case: O(n) - Match at start
  - Average case: O(n * m)
  - Worst case: O(n * m) - Check all positions
- **Space Complexity**: O(1) - Only variables
  - No extra data structures needed

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find the first occurrence of needle in haystack."

**Candidate**: "I'll use a sliding window approach. I'll iterate through haystack, and for each position, check if the substring of needle length starting at that position matches needle. I'll return the index as soon as I find a match."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For haystack='sadbutsad', needle='sad', I check position 0: substring 'sad' matches, return 0. If it didn't match, I'd check position 1, then 2, and so on."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n * m) in the worst case where n is haystack length and m is needle length. We check each position and potentially compare m characters. Space complexity is O(1) since we only use variables."

### Follow-up Questions

**Interviewer**: "Can you optimize this?"

**Candidate**: "Yes, I could use the KMP (Knuth-Morris-Pratt) algorithm which gives O(n + m) time complexity. It uses a prefix function to avoid re-checking characters we've already seen. However, for this problem, the sliding window approach is simpler and acceptable."

**Interviewer**: "What if needle is very long?"

**Candidate**: "KMP would be more beneficial in that case, as it avoids redundant comparisons. The sliding window approach might do a lot of repeated work."

### Tricky Edge Cases

1. **Needle not found**: Return -1
2. **Empty needle**: Return 0 (empty string is at index 0)
3. **Needle longer than haystack**: Return -1
4. **Multiple occurrences**: Return first index
5. **Needle equals haystack**: Return 0
6. **Single character needle**: Handle correctly
7. **Repeated characters**: Handle correctly

## Solution Approaches

### Approach 1: Sliding Window (Simple)
Check each position. O(n*m) time, O(1) space.

**Algorithm:**
1. Check edge cases (empty needle, needle too long)
2. For each position i from 0 to haystack.length() - needle.length():
   - Check if substring matches needle
   - Return i if match found
3. Return -1 if no match

**Advantages:**
- Simple implementation
- Easy to understand
- O(1) space

### Approach 2: KMP Algorithm (Advanced)
Use prefix function. O(n+m) time, O(m) space.

**Advantages:**
- Optimal time complexity
- Better for repeated patterns

**Disadvantages:**
- More complex implementation
- Requires understanding of prefix function

## Key Takeaways

1. **Sliding window** for substring search
2. **Check each position** in haystack
3. **Simple implementation** for basic cases
4. **KMP algorithm** for optimization if needed
5. **O(n*m) time** for simple approach
6. **O(1) space** for simple approach
7. **Edge cases matter** - empty needle, needle too long
8. **Foundation for** string matching algorithms
