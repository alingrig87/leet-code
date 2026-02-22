# Maximum Depth of Binary Tree

## Problem Statement
Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

**Example 1:**
```
Input: root = [3,9,20,null,null,15,7]
Output: 3
```

## Theory & Data Structures

### Recursive Approach
Maximum depth = 1 + max(depth of left subtree, depth of right subtree).

### Iterative Approach
Use queue for level-order traversal, count levels.

### Time & Space Complexity

#### Approach: Recursive
- **Time Complexity**: O(n) - Visit each node
- **Space Complexity**: O(h) - Recursion stack

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find maximum depth of binary tree."

**Candidate**: "I'll recursively find depth of left and right subtrees, return 1 + max of both."

**Interviewer**: "What's the base case?"

**Candidate**: "If root is null, return 0."

### Follow-up Questions

**Interviewer**: "Can you do it iteratively?"

**Candidate**: "Yes, using level-order traversal with queue, count number of levels."

### Tricky Edge Cases

1. **Empty tree**: Return 0
2. **Single node**: Return 1
3. **Skewed tree**: Depth equals number of nodes
4. **Balanced tree**: Depth is log(n)

## Solution Approaches

### Approach: Recursive (Optimal)
1 + max(left depth, right depth). O(n) time, O(h) space.

## Key Takeaways

1. **Recursive** approach is simple
2. **Base case**: null returns 0
3. **O(n) time** - visit each node
4. **Classic tree problem**
