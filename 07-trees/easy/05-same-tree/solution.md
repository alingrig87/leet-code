# Solution Explanation: Same Tree

## Approach: Recursive

### Intuition
Compare roots. If both null, return true. If one null or values differ, return false. Otherwise recursively compare subtrees.

### Algorithm
1. If both null, return true
2. If one null, return false
3. If values differ, return false
4. Recursively check left and right subtrees
5. Return AND of both results

### Complexity
- **Time**: O(n) - visit each node
- **Space**: O(h) - recursion stack

## Why This Works

- Trees are same if roots match and subtrees match
- Recursive comparison naturally handles this
- Base cases handle null trees
