# Encode and Decode Strings

## Problem Statement
Design an algorithm to encode a list of strings to a string and decode it back to the original list of strings.

**Example 1:**
```
Input: ["neet","code","love","you"]
Output: ["neet","code","love","you"]
```

## Theory & Data Structures

### Length Prefix Encoding
Encode each string as "length#string". When decoding, read length, then read that many characters.

### Time & Space Complexity

#### Approach: Length Prefix
- **Time Complexity**: O(n) - Single pass for encode/decode
- **Space Complexity**: O(n) - Encoded string

## Interview Simulation

### Initial Discussion

**Interviewer**: "Design encode/decode for list of strings."

**Candidate**: "I'll use length prefix encoding. For each string, encode as 'length#string'. When decoding, read until '#', get length, then read that many characters."

**Interviewer**: "Why length prefix?"

**Candidate**: "It handles strings containing any characters, including delimiters. The length tells us exactly how many characters to read."

### Follow-up Questions

**Interviewer**: "What if strings are very long?"

**Candidate**: "Length prefix still works, just need to handle large numbers in the prefix."

### Tricky Edge Cases

1. **Empty strings**: `[""]` → Encode as "0#"
2. **Strings with #**: Length prefix handles this
3. **Very long strings**: Need to handle large length values

## Solution Approaches

### Approach: Length Prefix (Optimal)
Encode as "length#string". O(n) time, O(n) space.

## Key Takeaways

1. **Length prefix** handles any characters
2. **Delimiter (#)** separates length from string
3. **Simple and robust** encoding scheme
