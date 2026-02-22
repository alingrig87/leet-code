/**
 * LeetCode 226: Invert Binary Tree
 * 
 * Problem: Invert a binary tree.
 * 
 * Solution Approach: Recursive
 * Time Complexity: O(n)
 * Space Complexity: O(h) where h is height
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        // Base case: empty tree
        if (root == null) {
            return null;
        }
        
        // Recursively invert left and right subtrees
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        
        // Swap children
        root.left = right;
        root.right = left;
        
        return root;
    }
}

/**
 * Alternative: Iterative using queue
 */
class SolutionIterative {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            // Swap children
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            
            // Add children to queue
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        
        return root;
    }
}
