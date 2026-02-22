# Find All Anagrams in a String

## Problem Statement
Given two strings `s` and `p`, return an array of all the start indices of `p`'s anagrams in `s`. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

**Example 1:**
```
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram "abc".
```

**Example 2:**
```
Input: s = "abab", p = "ab"
Output: [0,1,2]
```

## Theory & Data Structures

### Sliding Window with Frequency Map
This problem combines **sliding window** with **frequency counting**. We maintain a fixed-size window (size = p.length()) and track character frequencies.

#### Key Insight: Fixed-Size Window with Frequency Matching
- **Window size**: Fixed at p.length()
- **Frequency map**: Track character frequencies in current window
- **Compare**: Check if window frequencies match p's frequencies
- **Slide**: Move window one position at a time, update frequencies

#### Building Sliding Window with Frequency from Scratch (Conceptual)
```java
// Conceptual implementation of anagram finding
class AnagramFinder {
    
    // Find all anagram start positions
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        // Edge case: p is longer than s
        if (p.length() > s.length()) {
            return result;
        }
        
        // Frequency arrays for p and current window
        int[] pFreq = new int[26];
        int[] windowFreq = new int[26];
        
        // Count frequencies in p
        for (char c : p.toCharArray()) {
            pFreq[c - 'a']++;
        }
        
        // Initialize window (first p.length() characters)
        for (int i = 0; i < p.length(); i++) {
            windowFreq[s.charAt(i) - 'a']++;
        }
        
        // Check if first window is anagram
        if (Arrays.equals(pFreq, windowFreq)) {
            result.add(0);
        }
        
        // Slide window
        for (int i = p.length(); i < s.length(); i++) {
            // Remove leftmost character (going out of window)
            windowFreq[s.charAt(i - p.length()) - 'a']--;
            
            // Add rightmost character (entering window)
            windowFreq[s.charAt(i) - 'a']++;
            
            // Check if current window is anagram
            if (Arrays.equals(pFreq, windowFreq)) {
                result.add(i - p.length() + 1);
            }
        }
        
        return result;
    }
    
    // Alternative: More efficient comparison
    public List<Integer> findAnagramsOptimized(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        if (p.length() > s.length()) {
            return result;
        }
        
        int[] pFreq = new int[26];
        int[] windowFreq = new int[26];
        
        // Count p's frequencies
        for (char c : p.toCharArray()) {
            pFreq[c - 'a']++;
        }
        
        // Use counter to track matching characters
        int matchCount = 0;
        for (int i = 0; i < 26; i++) {
            if (pFreq[i] == 0) matchCount++;
        }
        
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowFreq[c - 'a']++;
            
            if (windowFreq[c - 'a'] == pFreq[c - 'a']) {
                matchCount++;
            } else if (windowFreq[c - 'a'] == pFreq[c - 'a'] + 1) {
                matchCount--;
            }
            
            // Window size is p.length()
            if (right - left + 1 == p.length()) {
                if (matchCount == 26) {
                    result.add(left);
                }
                
                // Remove left character
                char leftChar = s.charAt(left);
                windowFreq[leftChar - 'a']--;
                if (windowFreq[leftChar - 'a'] == pFreq[leftChar - 'a']) {
                    matchCount++;
                } else if (windowFreq[leftChar - 'a'] == pFreq[leftChar - 'a'] - 1) {
                    matchCount--;
                }
                left++;
            }
        }
        
        return result;
    }
}
```

### Time & Space Complexity

#### Approach: Sliding Window with Frequency Array
- **Time Complexity**: O(n) - Single pass through s
  - Build frequency arrays: O(m) where m = p.length()
  - Slide window: O(n - m)
  - Compare frequencies: O(26) per window (constant)
  - Total: O(n)
- **Space Complexity**: O(1) - Fixed size arrays (26 letters)
  - pFreq: O(26)
  - windowFreq: O(26)
  - Total: O(1) - constant space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find all start positions where an anagram of p appears in s."

**Candidate**: "I'll use a sliding window of size p.length(). I'll maintain frequency arrays for p and the current window. I'll slide the window through s, updating the frequency array by removing the leftmost character and adding the rightmost character. At each position, I'll check if the window frequencies match p's frequencies."

**Interviewer**: "How do you compare frequencies efficiently?"

**Candidate**: "I can use Arrays.equals() to compare the two frequency arrays, which is O(26) since we're only dealing with 26 lowercase letters. Alternatively, I can use a match counter that tracks how many character frequencies match, which allows for O(1) comparison."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For s='cbaebabacd', p='abc'. pFreq: a=1, b=1, c=1. First window 'cba': windowFreq matches pFreq, add index 0. Slide: remove 'c', add 'e', window='bae', doesn't match. Continue sliding. At index 6, window='bac', matches, add index 6. Result: [0,6]."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we make a single pass through s. Space complexity is O(1) since we use fixed-size arrays of 26 elements, regardless of input size."

### Follow-up Questions

**Interviewer**: "What if strings have uppercase and lowercase?"

**Candidate**: "We'd need to handle both cases. We could use a HashMap instead of an array, or use a larger array (52 for both cases, or 256 for all ASCII characters). The HashMap approach is more flexible."

**Interviewer**: "What if p is very long?"

**Candidate**: "The algorithm still works efficiently. The initial frequency counting takes O(m) time, and the sliding takes O(n) time. Total is O(n + m), which is optimal."

**Interviewer**: "Can you optimize the frequency comparison?"

**Candidate**: "Yes, instead of comparing arrays each time, we can maintain a match counter. We increment it when a character frequency matches, decrement when it doesn't. If matchCount equals 26, all frequencies match. This gives us O(1) comparison instead of O(26)."

### Tricky Edge Cases

1. **No anagrams**: `s="abc", p="def"` → Return `[]`
2. **Multiple anagrams**: `s="abab", p="ab"` → Return `[0,1,2]`
3. **p longer than s**: `s="ab", p="abc"` → Return `[]`
4. **Overlapping anagrams**: Handle correctly
5. **p equals s**: `s="abc", p="abc"` → Return `[0]`
6. **Single character p**: `s="aaa", p="a"` → Return `[0,1,2]`
7. **All same characters**: Handle correctly
8. **Empty strings**: Handle edge cases

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll first check if p is longer than s, and if so, return empty list. I'll create two frequency arrays of size 26 for p and the window. I'll count frequencies in p. Then I'll initialize the window with the first p.length() characters and check if it's an anagram. I'll slide the window by removing the leftmost character (decrement its frequency) and adding the rightmost character (increment its frequency). At each position, I'll check if frequencies match and add the start index if they do."

## Solution Approaches

### Approach 1: Sliding Window with Frequency Array (Optimal)
Fixed-size window, maintain frequency arrays. O(n) time, O(1) space.

**Algorithm:**
1. Check if p.length() > s.length(), return empty
2. Create frequency arrays for p and window
3. Count frequencies in p
4. Initialize window with first p.length() characters
5. Check first window
6. Slide window, update frequencies, check each position

**Advantages:**
- O(n) time complexity
- O(1) space complexity
- Simple and efficient

### Approach 2: Brute Force (Not Recommended)
Check each substring. O(n*m) time, O(m) space.

**Disadvantages:**
- O(n*m) time (much slower)
- Not optimal

## Key Takeaways

1. **Sliding window** for substring problems
2. **Frequency map** for anagram checking
3. **Fixed-size window** of p.length()
4. **O(n) time, O(1) space** - optimal solution
5. **Arrays.equals()** for frequency comparison
6. **Match counter** optimization possible
7. **Works with lowercase** letters (array of 26)
8. **Foundation for** more complex string problems
9. **Edge cases matter** - p longer than s, no anagrams
10. **Classic sliding window** problem
