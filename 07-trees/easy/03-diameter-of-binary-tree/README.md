# Diameter of Binary Tree

## Problem Statement
Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

**Example 1:**
```
Input: root = [1,2,3,4,5]
Output: 3
```

## Theory & Data Structures

### DFS with Height Calculation
For each node, diameter passing through it = height of left subtree + height of right subtree. Track maximum diameter.

### Time & Space Complexity

#### Approach: DFS
- **Time Complexity**: O(n) - Visit each node once
- **Space Complexity**: O(h) - Recursion stack

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find diameter of binary tree."

**Candidate**: "For each node, diameter passing through it is sum of heights of left and right subtrees. I'll use DFS, calculate height at each node, and track maximum diameter."

**Interviewer**: "Why sum of heights?"

**Candidate**: "The longest path through a node goes from deepest leaf in left subtree to deepest leaf in right subtree, which is sum of their heights."

### Follow-up Questions

**Interviewer**: "What if path doesn't pass through root?"

**Candidate**: "That's why we check diameter at every node, not just root. The maximum diameter might be in a subtree."

### Tricky Edge Cases

1. **Empty tree**: Return 0
2. **Single node**: Return 0 (no edges)
3. **Skewed tree**: Diameter is height
4. **Balanced tree**: Check all nodes

## Solution Approaches

### Approach: DFS (Optimal)
Calculate height at each node, track maximum diameter. O(n) time, O(h) space.

## Key Takeaways

1. **Diameter through node** = left height + right height
2. **Check all nodes**, not just root
3. **Calculate height** recursively
4. **Track maximum** diameter
