# Symmetric Tree

## Problem Statement
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

**Example 1:**
```
Input: root = [1,2,2,3,4,4,3]
Output: true
```

## Theory & Data Structures

### Two-Pointer Recursive
This problem uses **recursive comparison** with mirrored logic. We compare the left and right subtrees as if they're two separate trees, but with mirrored comparison.

#### Key Insight: Mirrored Comparison
- **Left's left vs Right's right**: These should be symmetric
- **Left's right vs Right's left**: These should be symmetric
- **Root comparison**: Root value doesn't matter for symmetry (single node)

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of symmetric tree check
class SymmetricTreeChecker {
    
    // Check if tree is symmetric
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // Compare left and right subtrees
        return isMirror(root.left, root.right);
    }
    
    // Check if two subtrees are mirror images
    private boolean isMirror(TreeNode left, TreeNode right) {
        // Both null - symmetric
        if (left == null && right == null) {
            return true;
        }
        
        // One null - not symmetric
        if (left == null || right == null) {
            return false;
        }
        
        // Values must be equal
        if (left.val != right.val) {
            return false;
        }
        
        // Mirrored comparison:
        // left's left should match right's right
        // left's right should match right's left
        return isMirror(left.left, right.right) && 
               isMirror(left.right, right.left);
    }
    
    // Iterative version using queues
    public boolean isSymmetricIterative(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            
            if (left == null && right == null) {
                continue;
            }
            
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            
            // Add children in mirrored order
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        
        return true;
    }
}
```

### Time & Space Complexity

#### Approach: Recursive
- **Time Complexity**: O(n) - Visit each node once
- **Space Complexity**: O(h) - Recursion stack depth

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if tree is symmetric."

**Candidate**: "I'll compare left and right subtrees. For symmetry, left's left should match right's right, and left's right should match right's left. I'll use recursive comparison with this mirrored logic."

**Interviewer**: "What's the base case?"

**Candidate**: "If both subtrees are null, return true. If one is null and the other isn't, return false. If values differ, return false."

### Follow-up Questions

**Interviewer**: "Can you do it iteratively?"

**Candidate**: "Yes, using two queues for simultaneous traversal of left and right subtrees in mirrored order."

### Tricky Edge Cases

1. **Empty tree**: Return true
2. **Single node**: Return true
3. **Asymmetric values**: Return false
4. **Asymmetric structure**: Return false

## Solution Approaches

### Approach: Recursive (Optimal)
Compare left and right with mirrored logic. O(n) time, O(h) space.

**Algorithm:**
1. If root null, return true
2. Compare left and right subtrees with isMirror
3. isMirror: check if left.left matches right.right and left.right matches right.left

**Advantages:**
- Elegant recursive solution
- O(n) time complexity
- Natural mirrored comparison

## Key Takeaways

1. **Mirrored comparison**: left.left vs right.right, left.right vs right.left
2. **Handle null** cases
3. **O(n) time** - visit each node
4. **Elegant recursive** solution
5. **Foundation for** more complex tree problems
6. **Iterative solution** possible with queues
7. **Important interview problem** - tests tree symmetry understanding
8. **Can be extended** to check if two trees are mirror images
9. **Classic problem** - good for understanding tree traversal
10. **Practice problem** for recursive thinking
