# Reverse Linked List

## Problem Statement
Given the head of a singly linked list, reverse the list, and return the head of the reversed list.

**Example 1:**
```
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
```

## Theory & Data Structures

### Iterative Approach
Use three pointers: prev, curr, next. Reverse links as we traverse.

### Recursive Approach
Recursively reverse rest of list, then reverse current node's link.

### Time & Space Complexity

#### Approach: Iterative
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Only pointers

#### Approach: Recursive
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(n) - Recursion stack

## Interview Simulation

### Initial Discussion

**Interviewer**: "Reverse a linked list."

**Candidate**: "I'll use three pointers: prev, curr, next. As I traverse, I'll reverse the link from curr to prev, then move all pointers forward."

**Interviewer**: "Can you do it recursively?"

**Candidate**: "Yes, recursively reverse the rest, then reverse current node's link. But iterative is preferred for O(1) space."

### Follow-up Questions

**Interviewer**: "What if list is empty or has one node?"

**Candidate**: "Return head as is - no reversal needed."

### Tricky Edge Cases

1. **Empty list**: Return null
2. **Single node**: Return head
3. **Two nodes**: Reverse link
4. **Long list**: Handle correctly

## Solution Approaches

### Approach 1: Iterative (Optimal)
Three pointers, reverse links. O(n) time, O(1) space.

### Approach 2: Recursive
Recursively reverse rest. O(n) time, O(n) space.

## Key Takeaways

1. **Three pointers** for iterative reversal
2. **O(1) space** with iterative approach
3. **Handle null** cases
4. **Reverse links** as you traverse
