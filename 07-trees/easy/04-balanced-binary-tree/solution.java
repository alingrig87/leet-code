/**
 * LeetCode 110: Balanced Binary Tree
 * 
 * Problem: Check if binary tree is height-balanced.
 * 
 * Solution Approach: DFS with early termination
 * Time Complexity: O(n)
 * Space Complexity: O(h) where h is height
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = height(node.left);
        if (leftHeight == -1) return -1; // Early termination
        
        int rightHeight = height(node.right);
        if (rightHeight == -1) return -1; // Early termination
        
        // Check if balanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Unbalanced
        }
        
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
