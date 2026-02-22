# Solution Explanation: Maximum Depth of Binary Tree

## Approach: Recursive

### Intuition
Maximum depth = 1 + max(depth of left subtree, depth of right subtree).

### Algorithm
1. Base case: if root is null, return 0
2. Recursively find depth of left subtree
3. Recursively find depth of right subtree
4. Return 1 + max(left depth, right depth)

### Complexity
- **Time**: O(n) - visit each node
- **Space**: O(h) - recursion stack

## Why This Works

- Depth at current node is 1 + maximum of children depths
- Recursive approach naturally handles this
- Base case handles empty tree
