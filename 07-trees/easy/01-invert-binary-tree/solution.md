# Solution Explanation: Invert Binary Tree

## Approach: Recursive

### Intuition
Recursively invert left and right subtrees, then swap left and right children of current node.

### Algorithm
1. Base case: if root is null, return null
2. Recursively invert left subtree
3. Recursively invert right subtree
4. Swap left and right children
5. Return root

### Complexity
- **Time**: O(n) - visit each node once
- **Space**: O(h) - recursion stack, h is height

## Why This Works

- Inverting subtrees first ensures correct order
- Swapping children completes inversion
- Recursive approach is natural and clean
