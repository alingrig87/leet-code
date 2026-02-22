# Solution Explanation: Balanced Binary Tree

## Approach: DFS with Early Termination

### Intuition
Calculate height at each node. If heights differ by more than 1, return -1 (unbalanced). Otherwise return height.

### Algorithm
1. Helper function returns height or -1
2. If node is null, return 0
3. Get left and right heights
4. If either is -1 or difference > 1, return -1
5. Otherwise return 1 + max(left, right)

### Complexity
- **Time**: O(n) - visit each node
- **Space**: O(h) - recursion stack

## Why -1 Works

- -1 signals unbalanced subtree
- Allows early termination
- More efficient than checking all nodes
