# Find All Anagrams in a String

## Problem Statement
Given two strings `s` and `p`, return an array of all the start indices of `p`'s anagrams in `s`. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

**Example 1:**
```
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
```

## Theory & Data Structures

### Sliding Window with Frequency Map
Use sliding window of size p.length(). Maintain frequency map of characters in window, compare with p's frequency map.

### Time & Space Complexity

#### Approach: Sliding Window
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Fixed size frequency arrays (26 letters)

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find all anagram start positions."

**Candidate**: "I'll use sliding window of size p.length(). Maintain frequency map of window characters, compare with p's frequency. Slide window and update frequency map."

**Interviewer**: "How do you compare frequencies?"

**Candidate**: "I'll use array of size 26 for lowercase letters. Compare arrays to check if window is anagram of p."

### Follow-up Questions

**Interviewer**: "What if strings have uppercase and lowercase?"

**Candidate**: "We'd need to handle both cases, maybe use HashMap or larger array."

### Tricky Edge Cases

1. **No anagrams**: Return empty list
2. **Multiple anagrams**: Return all positions
3. **p longer than s**: Return empty
4. **Overlapping anagrams**: Handle correctly

## Solution Approaches

### Approach: Sliding Window (Optimal)
Fixed-size window with frequency map. O(n) time, O(1) space.

## Key Takeaways

1. **Sliding window** for substring problems
2. **Frequency map** for anagram checking
3. **Fixed-size window** of p.length()
4. **Efficient O(n)** solution
