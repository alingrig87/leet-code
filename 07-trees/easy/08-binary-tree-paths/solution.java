import java.util.*;

/**
 * LeetCode 257: Binary Tree Paths
 * 
 * Problem: Find all root-to-leaf paths.
 * 
 * Solution Approach: DFS with path tracking
 * Time Complexity: O(n)
 * Space Complexity: O(h) where h is height
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        dfs(root, "", result);
        return result;
    }
    
    private void dfs(TreeNode node, String path, List<String> result) {
        // Add current node to path
        String currentPath = path.isEmpty() ? String.valueOf(node.val) 
                                           : path + "->" + node.val;
        
        // If leaf, add path to result
        if (node.left == null && node.right == null) {
            result.add(currentPath);
            return;
        }
        
        // Recursively process children
        if (node.left != null) {
            dfs(node.left, currentPath, result);
        }
        if (node.right != null) {
            dfs(node.right, currentPath, result);
        }
    }
}
