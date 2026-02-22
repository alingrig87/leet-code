/**
 * LeetCode 112: Path Sum
 * 
 * Problem: Check if root-to-leaf path sums to target.
 * 
 * Solution Approach: DFS with sum tracking
 * Time Complexity: O(n)
 * Space Complexity: O(h) where h is height
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        
        // Subtract current node value from target
        targetSum -= root.val;
        
        // If leaf and remaining sum is 0, path found
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }
        
        // Recursively check left and right subtrees
        return hasPathSum(root.left, targetSum) || 
               hasPathSum(root.right, targetSum);
    }
}
