/**
 * LeetCode 100: Same Tree
 * 
 * Problem: Check if two binary trees are identical.
 * 
 * Solution Approach: Recursive comparison
 * Time Complexity: O(n)
 * Space Complexity: O(h) where h is height
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Both null: trees are same
        if (p == null && q == null) {
            return true;
        }
        
        // One null: trees are different
        if (p == null || q == null) {
            return false;
        }
        
        // Values differ: trees are different
        if (p.val != q.val) {
            return false;
        }
        
        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
