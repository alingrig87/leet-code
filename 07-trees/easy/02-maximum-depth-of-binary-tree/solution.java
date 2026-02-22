/**
 * LeetCode 104: Maximum Depth of Binary Tree
 * 
 * Problem: Find maximum depth of binary tree.
 * 
 * Solution Approach: Recursive
 * Time Complexity: O(n)
 * Space Complexity: O(h) where h is height
 */
class Solution {
    public int maxDepth(TreeNode root) {
        // Base case: empty tree has depth 0
        if (root == null) {
            return 0;
        }
        
        // Recursively find depth of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        // Depth at current node is 1 + maximum of children depths
        return 1 + Math.max(leftDepth, rightDepth);
    }
}

/**
 * Alternative: Iterative using level-order traversal
 */
class SolutionIterative {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return depth;
    }
}
