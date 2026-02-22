# Linked List Cycle

## Problem Statement
Given `head`, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer.

Return `true` if there is a cycle in the linked list. Otherwise, return `false`.

**Example 1:**
```
Input: head = [3,2,0,-4], pos = 1
Output: true
```

## Theory & Data Structures

### Floyd's Cycle Detection (Tortoise and Hare)
Use two pointers: slow (moves 1 step) and fast (moves 2 steps). If cycle exists, they will meet.

### Time & Space Complexity

#### Approach: Floyd's Algorithm
- **Time Complexity**: O(n) - At most n steps
- **Space Complexity**: O(1) - Only two pointers

## Interview Simulation

### Initial Discussion

**Interviewer**: "Detect cycle in linked list."

**Candidate**: "I'll use Floyd's cycle detection - two pointers, slow moves one step, fast moves two steps. If cycle exists, they'll meet. If fast reaches null, no cycle."

**Interviewer**: "Why does this work?"

**Candidate**: "If cycle exists, fast pointer will eventually enter cycle. Since fast moves twice as fast, it will catch up to slow pointer within the cycle."

### Follow-up Questions

**Interviewer**: "Can you find the start of the cycle?"

**Candidate**: "Yes, after detecting cycle, reset one pointer to head, move both one step at a time. They'll meet at cycle start."

### Tricky Edge Cases

1. **No cycle**: Return false
2. **Single node cycle**: Detect correctly
3. **Cycle at head**: Handle correctly
4. **Empty list**: Return false

## Solution Approaches

### Approach: Floyd's Algorithm (Optimal)
Two pointers, slow and fast. O(n) time, O(1) space.

## Key Takeaways

1. **Floyd's algorithm** for cycle detection
2. **Two pointers** with different speeds
3. **O(1) space** solution
4. **Classic algorithm** to know
