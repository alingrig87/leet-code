# Isomorphic Strings

## Problem Statement
Given two strings `s` and `t`, determine if they are isomorphic.

Two strings `s` and `t` are isomorphic if the characters in `s` can be replaced to get `t`.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

**Example 1:**
```
Input: s = "egg", t = "add"
Output: true
```

## Theory & Data Structures

### Two HashMaps
Use two HashMaps to track mapping from s to t and from t to s. Check consistency.

### Time & Space Complexity

#### Approach: Two HashMaps
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Fixed size maps (at most 256 characters)

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if two strings are isomorphic."

**Candidate**: "I'll use two HashMaps - one maps s to t, other maps t to s. For each character pair, check if mapping is consistent. If conflict, return false."

**Interviewer**: "Why two maps?"

**Candidate**: "To ensure one-to-one mapping - no two characters in s map to same in t, and vice versa."

### Follow-up Questions

**Interviewer**: "What if strings are very long?"

**Candidate**: "Same approach works, maps are bounded by character set size (256 for ASCII)."

### Tricky Edge Cases

1. **Different lengths**: Return false
2. **Same string**: Return true
3. **Multiple mappings**: Return false
4. **Circular mapping**: Handle correctly

## Solution Approaches

### Approach: Two HashMaps (Optimal)
Track mappings in both directions. O(n) time, O(1) space.

## Key Takeaways

1. **Two maps** for bidirectional mapping
2. **Check consistency** at each step
3. **One-to-one** mapping required
4. **O(n) time, O(1) space**
