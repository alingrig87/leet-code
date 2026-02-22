# Binary Tree Paths

## Problem Statement
Given the root of a binary tree, return all root-to-leaf paths in any order.

A leaf is a node with no children.

**Example 1:**
```
Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]
```

## Theory & Data Structures

### DFS with Path Tracking
Use DFS to traverse tree, track current path. When reaching leaf, add path to result.

### Time & Space Complexity

#### Approach: DFS
- **Time Complexity**: O(n) - Visit each node
- **Space Complexity**: O(h) - Recursion stack and path

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find all root-to-leaf paths."

**Candidate**: "I'll use DFS, track current path. When I reach a leaf, I'll add the path to result. Backtrack by removing current node from path."

**Interviewer**: "How do you backtrack?"

**Candidate**: "After processing left and right subtrees, remove current node from path before returning."

### Follow-up Questions

**Interviewer**: "What if tree is very deep?"

**Candidate**: "We'd need O(h) space for path. For very deep trees, this could be significant."

### Tricky Edge Cases

1. **Empty tree**: Return empty list
2. **Single node**: Return single path
3. **Only left children**: Handle correctly
4. **Only right children**: Handle correctly

## Solution Approaches

### Approach: DFS (Optimal)
DFS with path tracking, add to result at leaves. O(n) time, O(h) space.

## Key Takeaways

1. **DFS traversal** with path tracking
2. **Add path** at leaf nodes
3. **Backtrack** after processing
4. **String building** for paths
