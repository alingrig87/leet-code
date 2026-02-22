# Remove Outermost Parentheses

## Problem Statement
A valid parentheses string is either empty `""`, `"(" + A + ")"`, or `A + B`, where `A` and `B` are valid parentheses strings, and `+` represents string concatenation.

The outermost parentheses of a valid parentheses string can be removed if it is of the form `"(" + A + ")"`.

Given a valid parentheses string `s`, remove the outermost parentheses of every primitive string in it and return the result string.

**Example 1:**
```
Input: s = "(()())(())"
Output: "()()()"
```

## Theory & Data Structures

### Counter Approach
Use counter to track depth. Skip outermost parentheses of each primitive string.

### Time & Space Complexity

#### Approach: Counter
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Only counter

## Interview Simulation

### Initial Discussion

**Interviewer**: "Remove outermost parentheses from each primitive string."

**Candidate**: "I'll use a counter to track depth. When depth is 0, we're at outermost. Skip those parentheses, include others."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n) time and O(1) space - single pass with counter."

### Follow-up Questions

**Interviewer**: "What if string is invalid?"

**Candidate**: "Problem states valid string, but we could add validation."

### Tricky Edge Cases

1. **Single primitive**: Remove outermost
2. **Multiple primitives**: Handle each separately
3. **Nested**: Handle correctly
4. **Empty**: Return empty

## Solution Approaches

### Approach: Counter (Optimal)
Track depth, skip outermost. O(n) time, O(1) space.

## Key Takeaways

1. **Counter** for depth tracking
2. **Skip outermost** parentheses
3. **O(1) space** solution
4. **Simple traversal**
