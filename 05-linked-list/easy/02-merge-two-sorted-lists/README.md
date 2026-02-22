# Merge Two Sorted Lists

## Problem Statement
You are given the heads of two sorted linked lists `list1` and `list2`.

Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

**Example 1:**
```
Input: list1 = [1,2,4], list2 = [2,3,4]
Output: [1,2,2,3,4,4]
```

## Theory & Data Structures

### Two Pointers
Use two pointers, one for each list. Compare values, link smaller node, advance pointer.

### Dummy Node
Use dummy node to simplify edge cases and avoid checking if result list is empty.

### Time & Space Complexity

#### Approach: Two Pointers
- **Time Complexity**: O(n + m) - Process all nodes
- **Space Complexity**: O(1) - Only new pointers

## Interview Simulation

### Initial Discussion

**Interviewer**: "Merge two sorted linked lists."

**Candidate**: "I'll use a dummy node to simplify. Compare heads of both lists, link smaller node to result, advance that pointer. Continue until one list is exhausted, then append remaining."

**Interviewer**: "Why dummy node?"

**Candidate**: "It simplifies code by avoiding special case for empty result list. We can always link to dummy.next."

### Follow-up Questions

**Interviewer**: "What if lists are very long?"

**Candidate**: "Same approach works, just process all nodes. Time is still O(n+m)."

### Tricky Edge Cases

1. **One list empty**: Return other list
2. **Both empty**: Return null
3. **All elements from one list**: Append remaining
4. **Alternating elements**: Handle correctly

## Solution Approaches

### Approach: Two Pointers with Dummy (Optimal)
Dummy node, compare and link. O(n+m) time, O(1) space.

## Key Takeaways

1. **Dummy node** simplifies code
2. **Compare and link** smaller node
3. **Append remaining** when one list exhausted
4. **O(n+m) time, O(1) space**
