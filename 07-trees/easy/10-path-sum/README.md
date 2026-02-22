# Path Sum

## Problem Statement
Given the root of a binary tree and an integer `targetSum`, return `true` if the tree has a root-to-leaf path such that adding up all the values along the path equals `targetSum`.

A leaf is a node with no children.

**Example 1:**
```
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The path is 5->4->11->2, which sums to 22.
```

## Theory & Data Structures

### DFS with Sum Tracking
This problem uses **DFS** to traverse the tree while tracking the current sum. At each leaf node, we check if the sum equals the targetSum.

#### Key Insight: Subtract from Target
- **Subtract approach**: Subtract node value from target as we traverse
- **Check at leaf**: If remaining sum is 0, path exists
- **Early termination**: Return true immediately if found
- **Alternative**: Track cumulative sum, compare at leaf

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of path sum check
class PathSumChecker {
    
    // Check if path sum equals target
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // Base case: null node
        if (root == null) {
            return false;
        }
        
        // Subtract current value from target
        targetSum -= root.val;
        
        // If leaf and remaining sum is 0, path found
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }
        
        // Recursively check left and right subtrees
        return hasPathSum(root.left, targetSum) || 
               hasPathSum(root.right, targetSum);
    }
    
    // Alternative: Track cumulative sum
    public boolean hasPathSumCumulative(TreeNode root, int targetSum) {
        return dfs(root, 0, targetSum);
    }
    
    private boolean dfs(TreeNode node, int currentSum, int targetSum) {
        if (node == null) {
            return false;
        }
        
        currentSum += node.val;
        
        if (node.left == null && node.right == null) {
            return currentSum == targetSum;
        }
        
        return dfs(node.left, currentSum, targetSum) || 
               dfs(node.right, currentSum, targetSum);
    }
}
```

### Time & Space Complexity

#### Approach: DFS
- **Time Complexity**: O(n) - Visit each node once
  - Best case: O(h) - Path found early
  - Average case: O(n)
  - Worst case: O(n) - Must check all paths
- **Space Complexity**: O(h) - Recursion stack depth

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if there's a root-to-leaf path with sum equal to target."

**Candidate**: "I'll use DFS, subtracting the node value from the target as I traverse. At a leaf node, if the remaining sum is 0, I return true. Otherwise, I recursively check the left and right subtrees."

**Interviewer**: "Why subtract instead of adding?"

**Candidate**: "It's cleaner - we check if the remaining sum is 0 at the leaf, rather than tracking a cumulative sum and comparing. Both approaches work, but subtracting is more elegant."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n) in the worst case when we need to check all paths. Space complexity is O(h) for the recursion stack."

### Follow-up Questions

**Interviewer**: "What if we need to return all paths with the target sum?"

**Candidate**: "Then we'd track the current path, and when we find a valid path at a leaf, we'd add it to the result list. We'd use backtracking to remove nodes from the path after processing."

**Interviewer**: "What if values can be negative?"

**Candidate**: "The algorithm still works. Negative values don't break the logic - we still check if the remaining sum is 0 at leaves."

### Tricky Edge Cases

1. **Empty tree**: Return false
2. **Single node**: Check if value equals target
3. **Negative values**: Handle correctly
4. **No valid path**: Return false
5. **Target is 0**: Handle correctly
6. **Multiple paths**: Return true if any path matches

## Solution Approaches

### Approach: DFS with Subtraction (Optimal)
DFS with sum tracking. O(n) time, O(h) space.

**Algorithm:**
1. If root null, return false
2. Subtract root value from target
3. If leaf and target is 0, return true
4. Recursively check left and right subtrees
5. Return true if any subtree has path

**Advantages:**
- Simple and elegant
- O(n) time complexity
- Early termination possible

## Key Takeaways

1. **Subtract from target** as we traverse
2. **Check at leaf** if remaining is 0
3. **Early termination** if found
4. **Simple DFS** solution
5. **O(n) time, O(h) space**
6. **Foundation for** path sum II and other path problems
