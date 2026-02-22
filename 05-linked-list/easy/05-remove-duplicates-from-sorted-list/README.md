# Remove Duplicates from Sorted List

## Problem Statement
Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

**Example 1:**
```
Input: head = [1,1,2]
Output: [1,2]
```

## Theory & Data Structures

### Single Pointer Traversal
Traverse list, if current value equals next value, skip next node.

### Time & Space Complexity

#### Approach: Single Pointer
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Only pointers

## Interview Simulation

### Initial Discussion

**Interviewer**: "Remove duplicates from sorted linked list."

**Candidate**: "I'll traverse the list. If current node's value equals next node's value, I'll skip the next node by linking current to next.next."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n) time and O(1) space - single pass with constant space."

### Follow-up Questions

**Interviewer**: "What if list isn't sorted?"

**Candidate**: "Then we'd need HashSet to track seen values, which is O(n) space."

### Tricky Edge Cases

1. **No duplicates**: Return list as is
2. **All duplicates**: Return single node
3. **Duplicates at end**: Handle correctly
4. **Empty list**: Return null

## Solution Approaches

### Approach: Single Pointer (Optimal)
Traverse, skip duplicates. O(n) time, O(1) space.

## Key Takeaways

1. **Sorted list** enables O(1) space
2. **Skip duplicates** by linking over them
3. **O(n) time, O(1) space**
4. **Simple traversal**
