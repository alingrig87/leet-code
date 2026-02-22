# Same Tree

## Problem Statement
Given the roots of two binary trees `p` and `q`, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

**Example 1:**
```
Input: p = [1,2,3], q = [1,2,3]
Output: true
```

## Theory & Data Structures

### Recursive Comparison
Compare roots, then recursively compare left and right subtrees.

### Time & Space Complexity

#### Approach: Recursive
- **Time Complexity**: O(n) - Visit each node
- **Space Complexity**: O(h) - Recursion stack

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if two trees are identical."

**Candidate**: "I'll compare roots. If both null, return true. If one null, return false. If values differ, return false. Otherwise recursively check left and right subtrees."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n) time where n is minimum number of nodes, O(h) space for recursion."

### Follow-up Questions

**Interviewer**: "Can you do it iteratively?"

**Candidate**: "Yes, using two stacks or queues for simultaneous traversal."

### Tricky Edge Cases

1. **Both empty**: Return true
2. **One empty**: Return false
3. **Different structures**: Return false
4. **Same structure, different values**: Return false

## Solution Approaches

### Approach: Recursive (Optimal)
Compare roots, recursively compare subtrees. O(n) time, O(h) space.

## Key Takeaways

1. **Compare roots** first
2. **Handle null** cases
3. **Recursively check** subtrees
4. **Simple and efficient**
