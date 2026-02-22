/**
 * LeetCode 101: Symmetric Tree
 * 
 * Problem: Check if binary tree is symmetric.
 * 
 * Solution Approach: Recursive with mirrored comparison
 * Time Complexity: O(n)
 * Space Complexity: O(h) where h is height
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }
    
    private boolean isMirror(TreeNode left, TreeNode right) {
        // Both null: symmetric
        if (left == null && right == null) {
            return true;
        }
        
        // One null: not symmetric
        if (left == null || right == null) {
            return false;
        }
        
        // Values must match
        if (left.val != right.val) {
            return false;
        }
        
        // Check mirrored positions
        // left's left should match right's right
        // left's right should match right's left
        return isMirror(left.left, right.right) && 
               isMirror(left.right, right.left);
    }
}
