# Solution Explanation: Binary Tree Paths

## Approach: DFS with Path Tracking

### Intuition
Use DFS, track current path. When reaching leaf, add path to result. Backtrack by removing current node.

### Algorithm
1. DFS helper with current path
2. Add current node to path
3. If leaf: add path to result
4. Recursively process left and right
5. Remove current node (backtrack)

### Complexity
- **Time**: O(n) - visit each node
- **Space**: O(h) - recursion stack and path

## Why Backtrack

- Need to reuse path for different branches
- Removing current node allows reuse
- Efficient space usage
