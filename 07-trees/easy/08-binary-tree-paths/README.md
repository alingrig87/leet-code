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
This problem uses **DFS** to traverse the tree while tracking the current path. When we reach a leaf node, we add the path to the result.

#### Key Insight: Backtracking
- **Track path**: Maintain current path as we traverse
- **Add at leaf**: When reaching leaf, add path to result
- **Backtrack**: Remove current node from path before returning
- **String building**: Build path string efficiently

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of path finding
class BinaryTreePaths {
    
    // Find all root-to-leaf paths
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        // DFS with path tracking
        dfs(root, new StringBuilder(), result);
        return result;
    }
    
    private void dfs(TreeNode node, StringBuilder path, List<String> result) {
        // Save current path length for backtracking
        int len = path.length();
        
        // Add current node to path
        if (path.length() > 0) {
            path.append("->");
        }
        path.append(node.val);
        
        // If leaf node, add path to result
        if (node.left == null && node.right == null) {
            result.add(path.toString());
        } else {
            // Recursively traverse left and right
            if (node.left != null) {
                dfs(node.left, path, result);
            }
            if (node.right != null) {
                dfs(node.right, path, result);
            }
        }
        
        // Backtrack: restore path to original length
        path.setLength(len);
    }
    
    // Alternative: Using List for path
    private void dfsWithList(TreeNode node, List<Integer> path, List<String> result) {
        path.add(node.val);
        
        if (node.left == null && node.right == null) {
            // Build string from path
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                if (i > 0) sb.append("->");
                sb.append(path.get(i));
            }
            result.add(sb.toString());
        } else {
            if (node.left != null) {
                dfsWithList(node.left, path, result);
            }
            if (node.right != null) {
                dfsWithList(node.right, path, result);
            }
        }
        
        // Backtrack: remove current node
        path.remove(path.size() - 1);
    }
}
```

### Time & Space Complexity

#### Approach: DFS
- **Time Complexity**: O(n) - Visit each node once
  - Building strings: O(h) per path, but total is O(n)
- **Space Complexity**: O(h) - Recursion stack and path
  - Path: O(h) where h is height
  - Result: O(n) for all paths (but this is output)

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find all root-to-leaf paths."

**Candidate**: "I'll use DFS to traverse the tree, tracking the current path. When I reach a leaf node, I'll add the path to the result. I'll use backtracking - after processing a node's children, I'll remove it from the path before returning."

**Interviewer**: "How do you backtrack?"

**Candidate**: "I'll save the current path length before adding the node. After processing children, I'll restore the path to that length using StringBuilder.setLength(). Alternatively, I could use a List and remove the last element."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n) since we visit each node once. Space complexity is O(h) for the recursion stack and path tracking, where h is the height of the tree."

### Follow-up Questions

**Interviewer**: "What if the tree is very deep?"

**Candidate**: "We'd need O(h) space for the path. For very deep trees, this could be significant. We could use iterative DFS with explicit stack, but space complexity would be similar."

**Interviewer**: "Can you optimize string building?"

**Candidate**: "Using StringBuilder is already efficient. We could pre-allocate capacity if we know approximate path length, but for most cases, StringBuilder is optimal."

### Tricky Edge Cases

1. **Empty tree**: Return empty list
2. **Single node**: Return single path ["1"]
3. **Only left children**: Handle correctly
4. **Only right children**: Handle correctly
5. **Balanced tree**: All paths found

## Solution Approaches

### Approach: DFS with Backtracking (Optimal)
DFS with path tracking, add to result at leaves. O(n) time, O(h) space.

**Algorithm:**
1. If root null, return empty list
2. DFS function:
   - Add current node to path
   - If leaf, add path to result
   - Recursively process children
   - Backtrack: remove current node
3. Return result

**Advantages:**
- Simple and efficient
- O(n) time complexity
- Natural backtracking

## Key Takeaways

1. **DFS traversal** with path tracking
2. **Add path** at leaf nodes
3. **Backtrack** after processing
4. **String building** for paths
5. **O(n) time, O(h) space**
6. **Foundation for** path sum and other path problems
