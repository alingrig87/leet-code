# Longest Common Prefix

## Problem Statement
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string `""`.

**Example 1:**
```
Input: strs = ["flower","flow","flight"]
Output: "fl"
```

## Theory & Data Structures

### Horizontal Scanning
Compare first string with others character by character. Stop when mismatch found.

### Vertical Scanning
Compare characters at same position across all strings.

### Time & Space Complexity

#### Approach: Vertical Scanning
- **Time Complexity**: O(S) where S is sum of all characters
- **Space Complexity**: O(1) - Only result string

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find longest common prefix."

**Candidate**: "I'll use vertical scanning - compare characters at same position across all strings. Stop when mismatch found or shortest string ends."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(S) time where S is total characters, O(1) space excluding result."

### Follow-up Questions

**Interviewer**: "What if strings are very long?"

**Candidate**: "Vertical scanning is still efficient. We stop as soon as mismatch found."

### Tricky Edge Cases

1. **Empty array**: Return ""
2. **Single string**: Return that string
3. **No common prefix**: Return ""
4. **All same**: Return any string

## Solution Approaches

### Approach: Vertical Scanning (Optimal)
Compare characters at same position. O(S) time, O(1) space.

## Key Takeaways

1. **Vertical scanning** is efficient
2. **Stop on mismatch** or end of shortest
3. **O(S) time** solution
4. **Simple implementation**
