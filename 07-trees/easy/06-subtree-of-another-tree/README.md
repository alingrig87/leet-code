# Subtree of Another Tree

## Problem Statement
Given the roots of two binary trees `root` and `subRoot`, return `true` if there is a subtree of `root` with the same structure and node values of `subRoot` and `false` otherwise.

A subtree of a binary tree `tree` is a tree that consists of a node in `tree` and all of this node's descendants.

**Example 1:**
```
Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true
```

## Theory & Data Structures

### DFS with Same Tree Check
For each node in root, check if subtree starting from that node matches subRoot using same tree logic.

### Time & Space Complexity

#### Approach: DFS
- **Time Complexity**: O(n * m) - Check each node, compare trees
- **Space Complexity**: O(h) - Recursion stack

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if subRoot is subtree of root."

**Candidate**: "I'll traverse root. For each node, check if subtree starting from that node matches subRoot using the same tree comparison logic."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n * m) where n is nodes in root, m in subRoot. For each node in root, we potentially compare entire subRoot tree."

### Follow-up Questions

**Interviewer**: "Can you optimize?"

**Candidate**: "We could use tree serialization and string matching, but worst case is still similar."

### Tricky Edge Cases

1. **subRoot is root**: Return true
2. **subRoot in left subtree**: Handle correctly
3. **subRoot in right subtree**: Handle correctly
4. **No match**: Return false

## Solution Approaches

### Approach: DFS with Same Tree (Optimal)
For each node, check if subtree matches. O(n*m) time, O(h) space.

## Key Takeaways

1. **Check at each node** if subtree matches
2. **Reuse same tree** logic
3. **O(n*m) time** in worst case
4. **Straightforward** approach
