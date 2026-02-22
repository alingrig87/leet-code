/**
 * LeetCode 543: Diameter of Binary Tree
 * 
 * Problem: Find diameter (longest path) of binary tree.
 * 
 * Solution Approach: DFS with height calculation
 * Time Complexity: O(n)
 * Space Complexity: O(h) where h is height
 */
class Solution {
    private int maxDiameter = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return maxDiameter;
    }
    
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        
        // Diameter passing through current node
        int diameter = leftHeight + rightHeight;
        maxDiameter = Math.max(maxDiameter, diameter);
        
        // Return height of current subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
