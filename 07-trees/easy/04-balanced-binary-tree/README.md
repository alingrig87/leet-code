# Balanced Binary Tree

## Problem Statement
Given a binary tree, determine if it is height-balanced.

A height-balanced binary tree is a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

**Example 1:**
```
Input: root = [3,9,20,null,null,15,7]
Output: true
```

**Example 2:**
```
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false
```

## Theory & Data Structures

### DFS with Height and Early Termination
This problem uses **DFS** to calculate heights and check balance. The key optimization is using a sentinel value (-1) to signal that a subtree is unbalanced, allowing early termination.

#### Key Insight: Early Termination
- **Calculate height**: Recursively calculate heights of subtrees
- **Check balance**: If height difference > 1, tree is unbalanced
- **Early termination**: Return -1 if unbalanced (sentinel value)
- **Efficiency**: Once we find an unbalanced subtree, we can stop checking

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of balanced tree check
class BalancedTreeChecker {
    
    // Check if tree is balanced
    public boolean isBalanced(TreeNode root) {
        return calculateHeight(root) != -1;
    }
    
    // Calculate height, return -1 if unbalanced
    private int calculateHeight(TreeNode root) {
        // Base case: null node has height 0
        if (root == null) {
            return 0;
        }
        
        // Recursively calculate heights
        int leftHeight = calculateHeight(root.left);
        // Early termination: if left subtree is unbalanced
        if (leftHeight == -1) {
            return -1;
        }
        
        int rightHeight = calculateHeight(root.right);
        // Early termination: if right subtree is unbalanced
        if (rightHeight == -1) {
            return -1;
        }
        
        // Check if current node is balanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;  // Unbalanced
        }
        
        // Return height of current node
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    // Why return -1?
    // - Normal heights are non-negative (0 or positive)
    // - -1 is a sentinel value indicating "unbalanced"
    // - Allows early termination without checking all nodes
    // - More efficient than calculating all heights then checking
}
```

### Time & Space Complexity

#### Approach: DFS with Early Termination
- **Time Complexity**: O(n) - Visit each node once
  - Best case: O(1) - Unbalanced at root
  - Average case: O(n)
  - Worst case: O(n) - Balanced tree (must check all)
- **Space Complexity**: O(h) - Recursion stack depth
  - Best case: O(log n) - Balanced tree
  - Average case: O(log n)
  - Worst case: O(n) - Skewed tree

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if a binary tree is height-balanced."

**Candidate**: "I'll use DFS to calculate heights. At each node, I'll check if the height difference between left and right subtrees is at most 1. If any node is unbalanced, the entire tree is unbalanced. I'll use -1 as a sentinel value to indicate unbalanced, which allows early termination."

**Interviewer**: "Why use -1 instead of returning a boolean?"

**Candidate**: "Using -1 allows us to combine height calculation and balance checking in one pass. If we return -1, we know the tree is unbalanced and can stop checking. If we used separate functions, we'd need to calculate all heights first, then check balance, which is less efficient."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For a balanced tree, at each node we calculate left and right heights, check if difference <= 1, and return 1 + max(heights). For an unbalanced tree, when we find height difference > 1, we return -1 immediately, and all parent calls also return -1."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) in the worst case when we need to check all nodes. Space complexity is O(h) for the recursion stack."

### Follow-up Questions

**Interviewer**: "What if we can't modify the return type?"

**Candidate**: "We could use a wrapper class or pass a boolean by reference. Or we could calculate all heights first, then check balance in a separate pass, but that's less efficient."

**Interviewer**: "Can you optimize this further?"

**Candidate**: "The -1 approach already provides early termination, which is the main optimization. Without it, we'd need to check all nodes even if we find an unbalanced subtree early."

### Tricky Edge Cases

1. **Empty tree**: `null` → Return `true`
2. **Single node**: `[1]` → Return `true`
3. **Unbalanced at root**: Return `false`
4. **Unbalanced in subtree**: Return `false`
5. **Just balanced**: Height diff = 1 → Return `true`

## Solution Approaches

### Approach: DFS with Early Termination (Optimal)
Calculate height, return -1 if unbalanced. O(n) time, O(h) space.

**Algorithm:**
1. Helper function returns height or -1
2. Base case: null returns 0
3. Calculate left and right heights
4. If either is -1, return -1
5. If height diff > 1, return -1
6. Return 1 + max(heights)
7. Main function: return height != -1

**Advantages:**
- Early termination
- O(n) time complexity
- Efficient

## Key Takeaways

1. **Height difference** must be <= 1 at every node
2. **Check all nodes**, not just root
3. **Early termination** with -1 sentinel
4. **O(n) time, O(h) space**
5. **Efficient solution** with optimization
