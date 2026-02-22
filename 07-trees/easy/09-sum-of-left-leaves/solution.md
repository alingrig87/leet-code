# Solution Explanation: Sum of Left Leaves

## Approach: DFS with Left Child Flag

### Intuition
Traverse tree, track if current node is left child. If leaf and left child, add to sum.

### Algorithm
1. DFS helper with isLeft flag
2. If node is null, return 0
3. If leaf and isLeft, return node value
4. Recursively sum left and right subtrees
5. Return total sum

### Complexity
- **Time**: O(n) - visit each node
- **Space**: O(h) - recursion stack

## Why Flag Works

- Need to know if node is left child
- Flag passed from parent indicates this
- Only count leaves that are left children
