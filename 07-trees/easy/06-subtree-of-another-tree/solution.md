# Solution Explanation: Subtree of Another Tree

## Approach: DFS with Same Tree Check

### Intuition
For each node in root, check if subtree starting from that node matches subRoot.

### Algorithm
1. Traverse root tree
2. For each node, check if subtree matches subRoot
3. Use same tree comparison logic
4. Return true if any match found

### Complexity
- **Time**: O(n * m) - check each node, compare trees
- **Space**: O(h) - recursion stack

## Why This Works

- Need to check if subRoot appears anywhere in root
- Checking at each node finds all possibilities
- Reusing same tree logic is efficient
