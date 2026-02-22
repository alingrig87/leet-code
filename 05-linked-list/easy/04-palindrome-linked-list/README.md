# Palindrome Linked List

## Problem Statement
Given the head of a singly linked list, return `true` if it is a palindrome or `false` otherwise.

**Example 1:**
```
Input: head = [1,2,2,1]
Output: true
```

## Theory & Data Structures

### Reverse Second Half
Find middle, reverse second half, compare with first half.

### Time & Space Complexity

#### Approach: Reverse Second Half
- **Time Complexity**: O(n) - Find middle, reverse, compare
- **Space Complexity**: O(1) - Only pointers

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if linked list is palindrome in O(1) space."

**Candidate**: "I'll find middle using slow/fast pointers, reverse second half, then compare first half with reversed second half."

**Interviewer**: "How do you find middle?"

**Candidate**: "Use slow and fast pointers. When fast reaches end, slow is at middle."

### Follow-up Questions

**Interviewer**: "What if we can't modify the list?"

**Candidate**: "Then we'd need O(n) space to store values and compare, or use recursion with O(n) stack space."

### Tricky Edge Cases

1. **Empty list**: Return true
2. **Single node**: Return true
3. **Even length**: Handle correctly
4. **Odd length**: Middle node doesn't need comparison

## Solution Approaches

### Approach: Reverse Second Half (Optimal)
Find middle, reverse second half, compare. O(n) time, O(1) space.

## Key Takeaways

1. **Find middle** with slow/fast pointers
2. **Reverse second half** in-place
3. **Compare** first and second halves
4. **O(1) space** solution
