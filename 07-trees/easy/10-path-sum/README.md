# Path Sum

## Problem Statement
Given the root of a binary tree and an integer `targetSum`, return `true` if the tree has a root-to-leaf path such that adding up all the values along the path equals `targetSum`.

A leaf is a node with no children.

**Example 1:**
```
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
```

## Theory & Data Structures

### DFS with Sum Tracking
Traverse tree, track current sum. At leaf, check if sum equals targetSum.

### Time & Space Complexity

#### Approach: DFS
- **Time Complexity**: O(n) - Visit each node
- **Space Complexity**: O(h) - Recursion stack

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if path sum equals target."

**Candidate**: "I'll use DFS, track current sum. At each node, subtract node value from target. At leaf, check if remaining sum is 0."

**Interviewer**: "Why subtract?"

**Candidate**: "It's cleaner - we check if remaining sum is 0 at leaf, rather than tracking cumulative sum."

### Follow-up Questions

**Interviewer**: "What if we need to return all paths?"

**Candidate**: "Then we'd track current path, add to result when sum matches at leaf."

### Tricky Edge Cases

1. **Empty tree**: Return false
2. **Single node**: Check if value equals target
3. **Negative values**: Handle correctly
4. **No valid path**: Return false

## Solution Approaches

### Approach: DFS (Optimal)
DFS with sum tracking. O(n) time, O(h) space.

## Key Takeaways

1. **Subtract from target** as we traverse
2. **Check at leaf** if remaining is 0
3. **Early termination** if found
4. **Simple DFS** solution
