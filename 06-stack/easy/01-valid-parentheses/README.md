# Valid Parentheses

## Problem Statement
Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:
1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

**Example 1:**
```
Input: s = "()"
Output: true
```

## Theory & Data Structures

### Stack
Use stack to track opening brackets. When closing bracket found, check if it matches top of stack.

### Time & Space Complexity

#### Approach: Stack
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(n) - Stack storage

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if parentheses are valid."

**Candidate**: "I'll use a stack. For opening brackets, push to stack. For closing brackets, check if stack is empty or top doesn't match. If matches, pop. At end, stack should be empty."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n) time and O(n) space for the stack."

### Follow-up Questions

**Interviewer**: "Can you do it with O(1) space?"

**Candidate**: "Not for general case. But if only one type of parentheses, we can use a counter."

### Tricky Edge Cases

1. **Empty string**: Return true
2. **Only opening**: Return false
3. **Only closing**: Return false
4. **Mismatched types**: Return false
5. **Nested correctly**: Return true

## Solution Approaches

### Approach: Stack (Optimal)
Use stack to match brackets. O(n) time, O(n) space.

## Key Takeaways

1. **Stack** for matching problems
2. **Push opening**, **pop on match**
3. **Check empty** at end
4. **Classic stack problem**
