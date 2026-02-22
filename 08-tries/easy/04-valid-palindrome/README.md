# Valid Palindrome

## Problem Statement
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

**Example 1:**
```
Input: s = "A man, a plan, a canal: Panama"
Output: true
```

## Theory & Data Structures

### Two Pointers
Use two pointers from both ends. Skip non-alphanumeric, compare lowercase characters.

### Time & Space Complexity

#### Approach: Two Pointers
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Only pointers

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if string is palindrome after cleaning."

**Candidate**: "I'll use two pointers from ends. Skip non-alphanumeric characters, convert to lowercase, compare."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n) time and O(1) space."

### Follow-up Questions

**Interviewer**: "Can you do it with O(1) space?"

**Candidate**: "Yes, the two-pointer approach already uses O(1) space."

### Tricky Edge Cases

1. **Empty string**: Return true
2. **Only non-alphanumeric**: Return true
3. **Mixed case**: Handle with lowercase
4. **Numbers**: Include in comparison

## Solution Approaches

### Approach: Two Pointers (Optimal)
Two pointers from ends, skip non-alphanumeric, compare. O(n) time, O(1) space.

## Key Takeaways

1. **Two pointers** for palindrome
2. **Skip non-alphanumeric**
3. **Case insensitive** comparison
4. **O(1) space** solution
