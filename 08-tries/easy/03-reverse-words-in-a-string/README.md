# Reverse Words in a String

## Problem Statement
Given an input string `s`, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in `s` will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

**Example 1:**
```
Input: s = "the sky is blue"
Output: "blue is sky the"
```

## Theory & Data Structures

### Two-Pass Approach
First pass: extract words. Second pass: reverse order and join.

### Time & Space Complexity

#### Approach: Two-Pass
- **Time Complexity**: O(n) - Two passes
- **Space Complexity**: O(n) - Store words

## Interview Simulation

### Initial Discussion

**Interviewer**: "Reverse order of words in string."

**Candidate**: "I'll split string into words, reverse the array, then join with single space."

**Interviewer**: "What about multiple spaces?"

**Candidate**: "I'll trim and handle multiple spaces by splitting and filtering empty strings."

### Follow-up Questions

**Interviewer**: "Can you do it in-place?"

**Candidate**: "Yes, but more complex. Reverse entire string, then reverse each word."

### Tricky Edge Cases

1. **Multiple spaces**: Handle correctly
2. **Leading/trailing spaces**: Trim
3. **Single word**: Return as is
4. **Empty string**: Return ""

## Solution Approaches

### Approach: Split and Reverse (Optimal)
Split into words, reverse, join. O(n) time, O(n) space.

## Key Takeaways

1. **Split into words** first
2. **Reverse array** of words
3. **Join with space**
4. **Handle edge cases**
