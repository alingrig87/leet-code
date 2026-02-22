/**
 * LeetCode 404: Sum of Left Leaves
 * 
 * Problem: Sum all left leaves in binary tree.
 * 
 * Solution Approach: DFS with left child flag
 * Time Complexity: O(n)
 * Space Complexity: O(h) where h is height
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }
    
    private int dfs(TreeNode node, boolean isLeft) {
        if (node == null) {
            return 0;
        }
        
        // If leaf and left child, return value
        if (node.left == null && node.right == null && isLeft) {
            return node.val;
        }
        
        // Recursively sum left and right subtrees
        return dfs(node.left, true) + dfs(node.right, false);
    }
}
