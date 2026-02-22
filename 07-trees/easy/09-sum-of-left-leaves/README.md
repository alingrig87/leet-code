# Sum of Left Leaves

## Problem Statement
Given the root of a binary tree, return the sum of all left leaves.

A leaf is a node with no children. A left leaf is a leaf that is the left child of its parent.

**Example 1:**
```
Input: root = [3,9,20,null,null,15,7]
Output: 24
Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
```

## Theory & Data Structures

### DFS with Parent Information
This problem uses **DFS** to traverse the tree while tracking whether the current node is a left child. If a node is a leaf and a left child, we add its value to the sum.

#### Key Insight: Track Left Child Status
- **Flag parameter**: Pass information about whether current node is left child
- **Check leaf**: Node with no children
- **Check left**: Must be left child of parent
- **Sum**: Add value if both conditions met

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of left leaves sum
class LeftLeavesSum {
    
    // Sum all left leaves
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }
    
    // DFS with isLeft flag
    private int dfs(TreeNode node, boolean isLeft) {
        if (node == null) {
            return 0;
        }
        
        // If leaf and left child, add value
        if (node.left == null && node.right == null && isLeft) {
            return node.val;
        }
        
        // Recursively sum left and right subtrees
        return dfs(node.left, true) + dfs(node.right, false);
    }
    
    // Alternative: Without helper, check parent
    public int sumOfLeftLeavesAlternative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int sum = 0;
        
        // Check if left child is leaf
        if (root.left != null && 
            root.left.left == null && 
            root.left.right == null) {
            sum += root.left.val;
        }
        
        // Recursively check subtrees
        sum += sumOfLeftLeavesAlternative(root.left);
        sum += sumOfLeftLeavesAlternative(root.right);
        
        return sum;
    }
}
```

### Time & Space Complexity

#### Approach: DFS
- **Time Complexity**: O(n) - Visit each node once
- **Space Complexity**: O(h) - Recursion stack depth

## Interview Simulation

### Initial Discussion

**Interviewer**: "Sum all left leaves."

**Candidate**: "I'll use DFS to traverse the tree. I'll pass a flag indicating whether the current node is a left child. If a node is a leaf (no children) and is a left child, I'll add its value to the sum."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n) since we visit each node once. Space complexity is O(h) for the recursion stack."

### Follow-up Questions

**Interviewer**: "Can you do it iteratively?"

**Candidate**: "Yes, I can use a stack or queue, storing nodes along with a flag indicating if they're left children. When I pop a node, I check if it's a leaf and left child, then add to sum."

### Tricky Edge Cases

1. **No left leaves**: Return 0
2. **Only left leaves**: Sum all
3. **Root is leaf**: Not counted (no parent)
4. **Right leaves**: Not counted
5. **Single node**: Return 0

## Solution Approaches

### Approach: DFS with Flag (Optimal)
DFS with left child flag. O(n) time, O(h) space.

**Algorithm:**
1. DFS function with isLeft parameter
2. If null, return 0
3. If leaf and left child, return value
4. Return sum of left and right subtrees

**Advantages:**
- Simple and clear
- O(n) time complexity
- Efficient solution

## Key Takeaways

1. **Track if left child** with flag
2. **Check if leaf** (no children)
3. **Add value** if left leaf
4. **Simple DFS** traversal
5. **O(n) time, O(h) space**
6. **Foundation for** tree traversal problems
