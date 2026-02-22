# Subtree of Another Tree

## Problem Statement
Given the roots of two binary trees `root` and `subRoot`, return `true` if there is a subtree of `root` with the same structure and node values of `subRoot` and `false` otherwise.

A subtree of a binary tree `tree` is a tree that consists of a node in `tree` and all of this node's descendants.

**Example 1:**
```
Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true
```

## Theory & Data Structures

### DFS with Same Tree Check
This problem combines **DFS traversal** with **same tree comparison**. For each node in root, we check if the subtree starting from that node matches subRoot.

#### Key Insight: Check at Every Node
- **Traverse root**: Visit each node in root
- **Check subtree**: For each node, check if subtree matches subRoot
- **Reuse logic**: Use same tree comparison logic
- **Early exit**: Return true as soon as we find a match

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of subtree check
class SubtreeChecker {
    
    // Check if subRoot is subtree of root
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // Base case: root is null
        if (root == null) {
            return false;
        }
        
        // Check if subtree starting from current node matches subRoot
        if (isSameTree(root, subRoot)) {
            return true;
        }
        
        // Recursively check left and right subtrees
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
    
    // Helper: Check if two trees are same
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    // Why check at every node?
    // Because subRoot could start at any node in root
    // We need to check all possible starting positions
}
```

### Time & Space Complexity

#### Approach: DFS with Same Tree
- **Time Complexity**: O(n * m) - Check each node, compare trees
  - n = number of nodes in root
  - m = number of nodes in subRoot
  - For each node in root, potentially compare entire subRoot
- **Space Complexity**: O(h) - Recursion stack
  - h = height of root

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if subRoot is a subtree of root."

**Candidate**: "I'll traverse root using DFS. For each node, I'll check if the subtree starting from that node matches subRoot using the same tree comparison logic. If I find a match, I return true immediately."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n * m) where n is nodes in root and m in subRoot. For each node in root, we potentially compare the entire subRoot tree. Space complexity is O(h) for the recursion stack."

### Follow-up Questions

**Interviewer**: "Can you optimize?"

**Candidate**: "We could use tree serialization and string matching, but worst-case complexity is similar. The current approach is straightforward and efficient for most cases."

### Tricky Edge Cases

1. **subRoot is root**: Return true
2. **subRoot in left subtree**: Handle correctly
3. **subRoot in right subtree**: Handle correctly
4. **No match**: Return false
5. **subRoot is leaf**: Handle correctly

## Solution Approaches

### Approach: DFS with Same Tree (Optimal)
For each node, check if subtree matches. O(n*m) time, O(h) space.

**Algorithm:**
1. If root is null, return false
2. Check if subtree from root matches subRoot
3. Recursively check left and right subtrees
4. Return true if any match

**Advantages:**
- Straightforward
- Reuses same tree logic
- Optimal for this problem

## Key Takeaways

1. **Check at each node** if subtree matches
2. **Reuse same tree** logic
3. **O(n*m) time** in worst case
4. **Straightforward** approach
5. **Foundation for** more complex tree problems
6. **Early exit** when match found
7. **DFS traversal** with nested comparison
8. **Important interview problem** - tests tree manipulation
9. **Can be optimized** with tree serialization
10. **Classic problem** - appears in variations
