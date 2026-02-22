# Invert Binary Tree

## Problem Statement
Given the root of a binary tree, invert the tree, and return its root.

**Example 1:**
```
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
```

## Theory & Data Structures

### Recursive Approach
Recursively invert left and right subtrees, then swap left and right children.

### Iterative Approach
Use queue/stack for level-order or DFS traversal, swap children at each node.

### Time & Space Complexity

#### Approach: Recursive
- **Time Complexity**: O(n) - Visit each node once
- **Space Complexity**: O(h) - Recursion stack, h is height

## Interview Simulation

### Initial Discussion

**Interviewer**: "Invert a binary tree."

**Candidate**: "I'll recursively invert left and right subtrees, then swap the children of current node."

**Interviewer**: "Can you do it iteratively?"

**Candidate**: "Yes, using queue for level-order or stack for DFS, swap children at each node."

### Follow-up Questions

**Interviewer**: "What's the space complexity?"

**Candidate**: "O(h) for recursive where h is height, O(n) worst case for iterative queue."

### Tricky Edge Cases

1. **Empty tree**: Return null
2. **Single node**: Return as is
3. **Skewed tree**: Handle correctly
4. **Balanced tree**: Works fine

## Solution Approaches

### Approach: Recursive (Optimal)
Recursively invert subtrees, swap children. O(n) time, O(h) space.

## Key Takeaways

1. **Recursive** approach is clean
2. **Swap children** after inverting subtrees
3. **O(n) time** - visit each node
4. **Classic tree problem**
