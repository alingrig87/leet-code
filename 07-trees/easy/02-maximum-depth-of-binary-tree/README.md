# Maximum Depth of Binary Tree

## Problem Statement
Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

**Example 1:**
```
Input: root = [3,9,20,null,null,15,7]
Output: 3
Explanation: The maximum depth is 3, which is the path 3 -> 20 -> 7.
```

**Example 2:**
```
Input: root = [1,null,2]
Output: 2
```

## Theory & Data Structures

### Recursive Approach
The recursive approach calculates depth by:
1. Base case: If node is null, return 0
2. Recursively find depth of left subtree
3. Recursively find depth of right subtree
4. Return 1 + max(left depth, right depth)

#### Building Recursive Depth Calculation from Scratch (Conceptual)
```java
// Conceptual implementation of maximum depth calculation
class DepthCalculator {
    
    // Find maximum depth recursively
    public int maxDepth(TreeNode root) {
        // Base case: empty tree has depth 0
        if (root == null) {
            return 0;
        }
        
        // Recursively find depth of left subtree
        int leftDepth = maxDepth(root.left);
        
        // Recursively find depth of right subtree
        int rightDepth = maxDepth(root.right);
        
        // Current node contributes 1 to depth
        // Take maximum of left and right subtrees
        return 1 + Math.max(leftDepth, rightDepth);
    }
    
    // More concise version
    public int maxDepthConcise(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
```

### Iterative Approach (BFS)
The iterative approach uses level-order traversal (BFS) to count levels.

#### Building Iterative Depth Calculation from Scratch (Conceptual)
```java
// Conceptual implementation of iterative depth calculation
class DepthCalculatorIterative {
    
    // Find maximum depth using BFS (level-order traversal)
    public int maxDepthBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        
        while (!queue.isEmpty()) {
            // Process all nodes at current level
            int levelSize = queue.size();
            depth++;  // Increment depth for each level
            
            // Process all nodes at this level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                // Add children to queue for next level
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        
        return depth;
    }
    
    // Alternative: Using DFS with stack
    public int maxDepthDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depths = new Stack<>();
        stack.push(root);
        depths.push(1);
        
        int maxDepth = 0;
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int currentDepth = depths.pop();
            
            maxDepth = Math.max(maxDepth, currentDepth);
            
            if (node.left != null) {
                stack.push(node.left);
                depths.push(currentDepth + 1);
            }
            if (node.right != null) {
                stack.push(node.right);
                depths.push(currentDepth + 1);
            }
        }
        
        return maxDepth;
    }
}
```

### Time & Space Complexity

#### Approach 1: Recursive
- **Time Complexity**: O(n) - Visit each node once
  - Best case: O(n)
  - Average case: O(n)
  - Worst case: O(n)
- **Space Complexity**: O(h) - Recursion stack depth
  - Best case: O(log n) - Balanced tree
  - Average case: O(log n)
  - Worst case: O(n) - Skewed tree

#### Approach 2: Iterative BFS
- **Time Complexity**: O(n) - Visit each node once
- **Space Complexity**: O(n) - Queue can contain all nodes at last level
  - Best case: O(1) - Single node
  - Average case: O(n/2) - Last level has most nodes
  - Worst case: O(n) - Complete tree

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find the maximum depth of a binary tree."

**Candidate**: "I'll use a recursive approach. For each node, I'll recursively find the depth of the left and right subtrees, then return 1 plus the maximum of both. The base case is when the node is null, which returns 0."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For a tree with root 3, left child 9, right child 20 which has children 15 and 7. I'll recursively find depth of subtree at 9 (which is 1), and depth of subtree at 20. For subtree at 20, I'll find depth of 15 (1) and 7 (1), take max (1), add 1 to get 2. Then for root 3, I take max of 1 and 2, add 1 to get 3."

**Interviewer**: "Can you do it iteratively?"

**Candidate**: "Yes, I can use level-order traversal with a queue. I'll process nodes level by level, incrementing a depth counter for each level. I'll continue until the queue is empty."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) for both approaches since we visit each node once. Space complexity is O(h) for recursive where h is the height, and O(n) for iterative BFS in the worst case."

### Follow-up Questions

**Interviewer**: "What if the tree is very deep?"

**Candidate**: "For a very deep tree, the recursive approach might cause stack overflow. In that case, the iterative BFS approach would be better, though it uses more memory. For balanced trees, recursive is fine and more elegant."

**Interviewer**: "What if we need to find the minimum depth instead?"

**Candidate**: "Similar approach, but we'd return 1 + min(left depth, right depth) instead of max. We'd also need to handle the case where one child is null - in that case, we can't take min, we must take the depth of the non-null child."

**Interviewer**: "Can you find the depth without recursion?"

**Candidate**: "Yes, using iterative BFS as I mentioned, or using DFS with a stack. The BFS approach is more intuitive for counting levels."

**Interviewer**: "What's the difference between depth and height?"

**Candidate**: "Depth is measured from root to node (root has depth 0 or 1 depending on definition). Height is measured from node to farthest leaf. For the root, maximum depth equals height. The problem asks for maximum depth, which is the height of the root."

### Tricky Edge Cases

1. **Empty tree**: `root = null` → Return `0`
2. **Single node**: `[1]` → Return `1`
3. **Skewed tree**: `[1,2,null,3]` → Return `3` (depth equals number of nodes)
4. **Balanced tree**: `[3,9,20,null,null,15,7]` → Return `3`
5. **Only left children**: Depth equals number of nodes
6. **Only right children**: Depth equals number of nodes
7. **Complete tree**: All levels filled → Depth is log(n) + 1
8. **Large tree**: Works with any size

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll start with the base case - if root is null, return 0. Then I'll recursively find the maximum depth of the left subtree and store it. I'll recursively find the maximum depth of the right subtree and store it. Then I'll return 1 plus the maximum of both depths. The 1 represents the current node, and we take the max because we want the longest path."

**Interviewer**: "Why take the maximum instead of minimum?"

**Candidate**: "Because we're looking for the maximum depth - the longest path from root to any leaf. If we took the minimum, we'd get the shortest path, which is a different problem."

**Interviewer**: "What happens if one subtree is null?"

**Candidate**: "If one subtree is null, its depth is 0. So we'd get 1 + max(0, rightDepth) or 1 + max(leftDepth, 0), which correctly gives us the depth of the non-null subtree plus 1 for the current node."

## Solution Approaches

### Approach 1: Recursive (Recommended)
Recursively find depths of subtrees, return 1 + max. O(n) time, O(h) space.

**Algorithm:**
1. Base case: if root is null, return 0
2. Recursively find left depth
3. Recursively find right depth
4. Return 1 + max(left depth, right depth)

**Advantages:**
- Clean and elegant
- Easy to understand
- O(h) space for balanced trees
- Natural recursive structure

### Approach 2: Iterative BFS
Use level-order traversal, count levels. O(n) time, O(n) space.

**Algorithm:**
1. If root is null, return 0
2. Create queue, add root, depth = 0
3. While queue not empty:
   - Increment depth
   - Process all nodes at current level
   - Add children to queue
4. Return depth

**Advantages:**
- No recursion stack
- Avoids stack overflow for deep trees

**Disadvantages:**
- More code
- O(n) space in worst case

### Approach 3: Iterative DFS
Use stack for depth-first traversal. O(n) time, O(n) space.

**Similar to BFS but uses stack and tracks depth for each node**

## Key Takeaways

1. **Recursive approach** is simple and elegant
2. **Base case**: null returns 0
3. **1 + max(left, right)** formula for depth
4. **O(n) time** - visit each node once
5. **O(h) space** for recursive, O(n) for iterative
6. **Maximum depth** = height of root
7. **Classic tree problem** - fundamental to know
8. **Works for any tree structure** - balanced, skewed, etc.
9. **Foundation for** more complex tree problems
10. **Iterative BFS** useful for avoiding stack overflow
