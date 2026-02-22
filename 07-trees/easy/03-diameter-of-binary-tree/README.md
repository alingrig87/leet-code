# Diameter of Binary Tree

## Problem Statement
Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

**Example 1:**
```
Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
```

## Theory & Data Structures

### DFS with Height Calculation
This problem uses **DFS** to calculate heights and track diameter. The key insight is that the diameter passing through a node equals the sum of heights of its left and right subtrees.

#### Key Insight: Diameter Through Node
- **Diameter through node**: height(left) + height(right)
- **Maximum diameter**: Maximum of all diameters through all nodes
- **Height calculation**: Standard recursive height calculation
- **Track maximum**: Update maximum diameter during traversal

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of diameter calculation
class DiameterCalculator {
    private int maxDiameter = 0;
    
    // Calculate diameter of binary tree
    public int diameterOfBinaryTree(TreeNode root) {
        calculateHeight(root);
        return maxDiameter;
    }
    
    // Calculate height and update diameter
    private int calculateHeight(TreeNode root) {
        // Base case: null node has height -1 (or 0, depending on definition)
        if (root == null) {
            return 0;  // Height of null is 0 (number of nodes) or -1 (number of edges)
        }
        
        // Recursively calculate heights of subtrees
        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);
        
        // Diameter passing through current node
        // If height is number of edges: leftHeight + rightHeight
        // If height is number of nodes: leftHeight + rightHeight - 2
        // Common definition: height is number of edges, so:
        int diameterThroughNode = leftHeight + rightHeight;
        
        // Update maximum diameter
        maxDiameter = Math.max(maxDiameter, diameterThroughNode);
        
        // Return height of current node
        // Height = 1 + max(left height, right height)
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    // Alternative: Return both height and diameter
    class Result {
        int height;
        int diameter;
        Result(int h, int d) { height = h; diameter = d; }
    }
    
    private Result calculateDiameterAndHeight(TreeNode root) {
        if (root == null) {
            return new Result(0, 0);
        }
        
        Result left = calculateDiameterAndHeight(root.left);
        Result right = calculateDiameterAndHeight(root.right);
        
        int diameterThroughNode = left.height + right.height;
        int maxDiameter = Math.max(Math.max(left.diameter, right.diameter), diameterThroughNode);
        int height = 1 + Math.max(left.height, right.height);
        
        return new Result(height, maxDiameter);
    }
}
```

### Time & Space Complexity

#### Approach: DFS
- **Time Complexity**: O(n) - Visit each node once
  - Best case: O(n)
  - Average case: O(n)
  - Worst case: O(n)
- **Space Complexity**: O(h) - Recursion stack depth
  - Best case: O(log n) - Balanced tree
  - Average case: O(log n)
  - Worst case: O(n) - Skewed tree

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find the diameter of a binary tree."

**Candidate**: "The diameter is the longest path between any two nodes. For each node, the diameter passing through it is the sum of heights of its left and right subtrees. I'll use DFS to calculate heights at each node and track the maximum diameter."

**Interviewer**: "Why sum of heights?"

**Candidate**: "The longest path through a node goes from the deepest leaf in the left subtree to the deepest leaf in the right subtree. The height of left subtree gives us the distance from node to deepest left leaf, and height of right subtree gives distance to deepest right leaf. Their sum is the diameter through that node."

**Interviewer**: "What if the diameter doesn't pass through the root?"

**Candidate**: "That's why we check the diameter at every node, not just the root. The maximum diameter might be in a subtree. By calculating diameter at each node during DFS, we ensure we find the global maximum."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we visit each node once. Space complexity is O(h) for the recursion stack, where h is the height of the tree."

### Follow-up Questions

**Interviewer**: "Can you do this iteratively?"

**Candidate**: "Yes, we could use iterative DFS with a stack, but it would be more complex. The recursive approach is cleaner and equally efficient."

**Interviewer**: "What if we need to return the actual path, not just the length?"

**Candidate**: "We'd need to track the nodes along the path. We'd store the deepest nodes in left and right subtrees, and reconstruct the path. This would require more space and complexity."

### Tricky Edge Cases

1. **Empty tree**: `null` → Return `0`
2. **Single node**: `[1]` → Return `0` (no edges)
3. **Two nodes**: `[1,2]` → Return `1` (one edge)
4. **Skewed tree**: Diameter equals height
5. **Balanced tree**: Check all nodes
6. **Diameter in subtree**: Not through root

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll use a class variable or pass by reference to track the maximum diameter. I'll create a helper function that calculates height. In the helper, if root is null, I return 0. Otherwise, I recursively calculate left and right heights. The diameter through the current node is leftHeight + rightHeight. I update the maximum diameter. Then I return 1 + max(leftHeight, rightHeight) as the height of the current node."

## Solution Approaches

### Approach: DFS with Height (Optimal)
Calculate height at each node, track maximum diameter. O(n) time, O(h) space.

**Algorithm:**
1. Initialize maxDiameter = 0
2. DFS function that returns height:
   - Base case: return 0 for null
   - Calculate left and right heights
   - Update maxDiameter = max(maxDiameter, leftHeight + rightHeight)
   - Return 1 + max(leftHeight, rightHeight)
3. Return maxDiameter

**Advantages:**
- O(n) time complexity
- O(h) space complexity
- Optimal solution

## Key Takeaways

1. **Diameter through node** = left height + right height
2. **Check all nodes**, not just root
3. **DFS with height calculation**
4. **O(n) time, O(h) space**
5. **Track maximum** during traversal
6. **Foundation for** more complex tree problems
