# Reverse String

## Problem Statement
Write a function that reverses a string. The input string is given as an array of characters `s`.

You must do this by modifying the input array in-place with `O(1)` extra memory.

**Example 1:**
```
Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
```

**Example 2:**
```
Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
```

## Theory & Data Structures

### Two Pointers Technique
Use two pointers from both ends, swap characters, move towards center.

### Time & Space Complexity

#### Approach: Two Pointers
- **Time Complexity**: O(n) - Half the array length
- **Space Complexity**: O(1) - Only temporary variable for swap

## Interview Simulation

### Initial Discussion

**Interviewer**: "Reverse a string in-place with O(1) space."

**Candidate**: "I'll use two pointers from both ends. Swap characters at each position, then move pointers towards center until they meet."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n) time and O(1) space - we swap n/2 pairs of characters."

### Follow-up Questions

**Interviewer**: "What if it's a String object in Java?"

**Candidate**: "Java Strings are immutable, so we'd need to convert to char array first, reverse, then create new String."

**Interviewer**: "Can you do it recursively?"

**Candidate**: "Yes, but recursion uses O(n) stack space, so it's not O(1) space."

### Tricky Edge Cases

1. **Empty array**: `[]` → No change
2. **Single character**: `["a"]` → No change
3. **Even length**: `["a","b"]` → `["b","a"]`
4. **Odd length**: `["a","b","c"]` → `["c","b","a"]`

## Solution Approaches

### Approach: Two Pointers (Optimal)
Swap characters from ends, move towards center. O(n) time, O(1) space.

## Key Takeaways

1. **Two pointers** perfect for in-place reversal
2. **Swap operation** is O(1)
3. **O(1) space** solution
4. **Simple and efficient**
