/**
 * LeetCode 572: Subtree of Another Tree
 * 
 * Problem: Check if subRoot is subtree of root.
 * 
 * Solution Approach: DFS with same tree check
 * Time Complexity: O(n * m)
 * Space Complexity: O(h) where h is height
 */
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        
        // Check if subtree starting from current node matches
        if (isSameTree(root, subRoot)) {
            return true;
        }
        
        // Check left and right subtrees
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
    
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
