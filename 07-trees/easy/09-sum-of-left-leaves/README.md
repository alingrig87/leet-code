# Sum of Left Leaves

## Problem Statement
Given the root of a binary tree, return the sum of all left leaves.

A leaf is a node with no children. A left leaf is a leaf that is the left child of its parent.

**Example 1:**
```
Input: root = [3,9,20,null,null,15,7]
Output: 24
```

## Theory & Data Structures

### DFS with Parent Information
Traverse tree, track if current node is left child. If it's a leaf and left child, add to sum.

### Time & Space Complexity

#### Approach: DFS
- **Time Complexity**: O(n) - Visit each node
- **Space Complexity**: O(h) - Recursion stack

## Interview Simulation

### Initial Discussion

**Interviewer**: "Sum all left leaves."

**Candidate**: "I'll use DFS, pass a flag indicating if current node is left child. If it's a leaf and left child, add its value to sum."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n) time and O(h) space for recursion."

### Follow-up Questions

**Interviewer**: "Can you do it iteratively?"

**Candidate**: "Yes, using stack or queue with parent information."

### Tricky Edge Cases

1. **No left leaves**: Return 0
2. **Only left leaves**: Sum all
3. **Root is leaf**: Not counted (no parent)
4. **Right leaves**: Not counted

## Solution Approaches

### Approach: DFS (Optimal)
DFS with left child flag. O(n) time, O(h) space.

## Key Takeaways

1. **Track if left child** with flag
2. **Check if leaf** (no children)
3. **Add value** if left leaf
4. **Simple DFS** traversal
