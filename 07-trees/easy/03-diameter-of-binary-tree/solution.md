# Solution Explanation: Diameter of Binary Tree

## Approach: DFS

### Intuition
For each node, diameter passing through it = height of left subtree + height of right subtree. Track maximum.

### Algorithm
1. Use helper function that returns height
2. At each node, calculate diameter = left height + right height
3. Update maximum diameter
4. Return height of current subtree

### Complexity
- **Time**: O(n) - visit each node once
- **Space**: O(h) - recursion stack

## Why This Works

- Longest path through node uses deepest leaves from both sides
- Sum of subtree heights gives this length
- Checking all nodes finds global maximum
