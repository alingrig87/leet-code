# Solution Explanation: Path Sum

## Approach: DFS with Sum Tracking

### Intuition
Traverse tree, subtract node value from target. At leaf, check if remaining sum is 0.

### Algorithm
1. Base case: if node is null, return false
2. Subtract node value from target
3. If leaf and remaining sum is 0, return true
4. Recursively check left and right subtrees
5. Return OR of results

### Complexity
- **Time**: O(n) - visit each node
- **Space**: O(h) - recursion stack

## Why Subtract Works

- Instead of tracking cumulative sum, subtract from target
- At leaf, check if remaining is 0
- Cleaner implementation
