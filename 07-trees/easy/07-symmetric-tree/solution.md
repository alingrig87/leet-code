# Solution Explanation: Symmetric Tree

## Approach: Recursive with Mirrored Comparison

### Intuition
Compare left and right subtrees. For symmetry, left's left matches right's right, and left's right matches right's left.

### Algorithm
1. Helper function compares two nodes
2. If both null, return true
3. If one null or values differ, return false
4. Recursively check: left.left with right.right, left.right with right.left

### Complexity
- **Time**: O(n) - visit each node
- **Space**: O(h) - recursion stack

## Why Mirrored Comparison

- Symmetry means left subtree mirrors right
- Left's left corresponds to right's right
- Left's right corresponds to right's left
