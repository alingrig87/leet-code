# Reverse Words in a String

## Problem Statement
Given an input string `s`, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in `s` will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that `s` may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

**Example 1:**
```
Input: s = "the sky is blue"
Output: "blue is sky the"
```

**Example 2:**
```
Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
```

## Theory & Data Structures

### Two-Pass Approach
This problem uses a **two-pass approach**: first extract words, then reverse and join them.

#### Key Insight: Extract, Reverse, Join
- **Extract words**: Split string into words, handling multiple spaces
- **Reverse order**: Reverse the array of words
- **Join with space**: Concatenate words with single space
- **Handle edge cases**: Leading/trailing spaces, multiple spaces

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of word reversal
class WordReverser {
    
    // Two-pass approach
    public String reverseWords(String s) {
        // Trim and split by whitespace (handles multiple spaces)
        String[] words = s.trim().split("\\s+");
        
        // Reverse the array
        int left = 0, right = words.length - 1;
        while (left < right) {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }
        
        // Join with single space
        return String.join(" ", words);
    }
    
    // Alternative: Using StringBuilder
    public String reverseWordsStringBuilder(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
    
    // In-place approach (more complex)
    public String reverseWordsInPlace(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        
        // Step 1: Reverse entire string
        reverse(chars, 0, n - 1);
        
        // Step 2: Reverse each word
        int i = 0, j = 0;
        while (j < n) {
            // Skip spaces
            while (j < n && chars[j] == ' ') j++;
            if (j == n) break;
            
            // Find word end
            int start = j;
            while (j < n && chars[j] != ' ') j++;
            
            // Reverse word
            reverse(chars, start, j - 1);
            
            // Copy word to position i
            if (i > 0) chars[i++] = ' ';
            while (start < j) {
                chars[i++] = chars[start++];
            }
        }
        
        return new String(chars, 0, i);
    }
    
    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }
}
```

### Time & Space Complexity

#### Approach: Two-Pass
- **Time Complexity**: O(n) - Two passes through string
  - Split: O(n)
  - Reverse: O(k) where k is number of words
  - Join: O(n)
  - Total: O(n)
- **Space Complexity**: O(n) - Store words array
  - Words array: O(n)
  - Result string: O(n)

## Interview Simulation

### Initial Discussion

**Interviewer**: "Reverse the order of words in a string."

**Candidate**: "I'll split the string into words, handling multiple spaces by trimming and using regex. Then I'll reverse the array of words and join them with a single space."

**Interviewer**: "How do you handle multiple spaces?"

**Candidate**: "I'll use `trim()` to remove leading/trailing spaces, and `split("\\s+")` to split by one or more whitespace characters. This automatically handles multiple spaces."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For '  hello world  ', I trim to get 'hello world', split to get ['hello', 'world'], reverse to get ['world', 'hello'], then join with space to get 'world hello'."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n) since we make two passes through the string. Space complexity is O(n) for storing the words array and the result string."

### Follow-up Questions

**Interviewer**: "Can you do it in-place?"

**Candidate**: "Yes, but it's more complex. I'd reverse the entire string first, then reverse each word individually. I'd also need to handle spaces carefully to avoid extra spaces in the result. The two-pass approach is simpler and more readable."

**Interviewer**: "What if the string is very long?"

**Candidate**: "The two-pass approach is still efficient - O(n) time. The in-place approach would also be O(n) but more complex to implement correctly."

### Tricky Edge Cases

1. **Multiple spaces**: Handle correctly with regex
2. **Leading/trailing spaces**: Trim before processing
3. **Single word**: Return as is
4. **Empty string**: Return ""
5. **Only spaces**: Return ""
6. **No spaces**: Single word, return as is

## Solution Approaches

### Approach 1: Split and Reverse (Recommended)
Split into words, reverse, join. O(n) time, O(n) space.

**Algorithm:**
1. Trim string to remove leading/trailing spaces
2. Split by whitespace (handles multiple spaces)
3. Reverse array of words
4. Join with single space

**Advantages:**
- Simple and readable
- Handles edge cases well
- O(n) time complexity

### Approach 2: In-Place (Advanced)
Reverse entire string, then reverse each word. O(n) time, O(1) space.

**Advantages:**
- O(1) extra space (excluding input/output)
- More memory efficient

**Disadvantages:**
- More complex implementation
- Harder to get right

## Key Takeaways

1. **Split into words** first
2. **Reverse array** of words
3. **Join with space** to form result
4. **Handle edge cases** - multiple spaces, leading/trailing spaces
5. **O(n) time, O(n) space** for simple approach
6. **In-place possible** but more complex
7. **Regex split** handles multiple spaces elegantly
8. **Foundation for** string manipulation problems
