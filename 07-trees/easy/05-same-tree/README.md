# Same Tree

## Problem Statement
Given the roots of two binary trees `p` and `q`, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

**Example 1:**
```
Input: p = [1,2,3], q = [1,2,3]
Output: true
```

**Example 2:**
```
Input: p = [1,2], q = [1,null,2]
Output: false
```

## Theory & Data Structures

### Recursive Comparison
This problem uses **recursive comparison** to check if two trees are identical. We compare nodes at the same positions in both trees.

#### Key Insight: Structural and Value Comparison
- **Structural check**: Both nodes must be null or both non-null
- **Value check**: If both non-null, values must be equal
- **Recursive check**: Left and right subtrees must also be same

#### Building Recursive Comparison from Scratch (Conceptual)
```java
// Conceptual implementation of same tree check
class SameTreeChecker {
    
    // Check if two trees are identical
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Base case 1: Both null - trees are same
        if (p == null && q == null) {
            return true;
        }
        
        // Base case 2: One null, one not - trees differ
        if (p == null || q == null) {
            return false;
        }
        
        // Base case 3: Values differ - trees differ
        if (p.val != q.val) {
            return false;
        }
        
        // Recursive case: Check left and right subtrees
        // Both must be same for trees to be same
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    // More concise version
    public boolean isSameTreeConcise(TreeNode p, TreeNode q) {
        // If both null, they're same
        if (p == null && q == null) {
            return true;
        }
        
        // If one is null or values differ, they're different
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        
        // Recursively check subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    // Iterative version using stacks
    public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        Stack<TreeNode> stackP = new Stack<>();
        Stack<TreeNode> stackQ = new Stack<>();
        
        stackP.push(p);
        stackQ.push(q);
        
        while (!stackP.isEmpty() && !stackQ.isEmpty()) {
            TreeNode nodeP = stackP.pop();
            TreeNode nodeQ = stackQ.pop();
            
            // Check if both null
            if (nodeP == null && nodeQ == null) {
                continue;
            }
            
            // Check if one null or values differ
            if (nodeP == null || nodeQ == null || nodeP.val != nodeQ.val) {
                return false;
            }
            
            // Push children (order matters for comparison)
            stackP.push(nodeP.right);
            stackP.push(nodeP.left);
            stackQ.push(nodeQ.right);
            stackQ.push(nodeQ.left);
        }
        
        // Both stacks should be empty
        return stackP.isEmpty() && stackQ.isEmpty();
    }
}
```

### Time & Space Complexity

#### Approach: Recursive
- **Time Complexity**: O(n) - Visit each node once
  - Best case: O(1) - Roots differ
  - Average case: O(min(n, m)) where n, m are tree sizes
  - Worst case: O(n) - Must check all nodes
- **Space Complexity**: O(h) - Recursion stack depth
  - Best case: O(log n) - Balanced trees
  - Average case: O(log n)
  - Worst case: O(n) - Skewed trees

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if two binary trees are identical."

**Candidate**: "I'll use recursive comparison. First, I'll check if both roots are null - if so, return true. If one is null and the other isn't, return false. If both are non-null, I'll check if their values are equal. If values differ, return false. Otherwise, I'll recursively check if left subtrees are same and right subtrees are same. Both must be true for trees to be identical."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For p=[1,2,3] and q=[1,2,3], I compare roots: both are 1, same. Compare left children: both are 2, same. Compare right children: both are 3, same. All comparisons pass, return true. For p=[1,2] and q=[1,null,2], roots are same (1), but left children differ (2 vs null), return false."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) where n is the minimum number of nodes in both trees. We visit each node once. Space complexity is O(h) for the recursion stack, where h is the height of the smaller tree."

### Follow-up Questions

**Interviewer**: "Can you do this iteratively?"

**Candidate**: "Yes, I can use two stacks or queues for simultaneous traversal. I'll push corresponding nodes from both trees, then pop and compare them. This uses O(n) space for the stacks but avoids recursion."

**Interviewer**: "What if the trees are very large?"

**Candidate**: "The recursive approach might cause stack overflow for very deep trees. In that case, the iterative approach with explicit stacks would be better, though it uses more memory."

**Interviewer**: "What if we need to find how many nodes differ?"

**Candidate**: "We'd modify the algorithm to count differences instead of returning false immediately. We'd traverse both trees and count nodes where values differ or structures differ."

### Tricky Edge Cases

1. **Both empty**: `p=null, q=null` → Return `true`
2. **One empty**: `p=null, q=[1]` → Return `false`
3. **Different structures**: `p=[1,2], q=[1,null,2]` → Return `false`
4. **Same structure, different values**: `p=[1,2], q=[1,3]` → Return `false`
5. **Identical trees**: Return `true`
6. **Single node each, same**: Return `true`
7. **Single node each, different**: Return `false`

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll start with base cases. If both p and q are null, return true. If one is null and the other isn't, return false. If both are non-null but their values differ, return false. If we pass all these checks, I'll recursively check if the left subtrees are the same and if the right subtrees are the same. Both must return true for the trees to be identical."

## Solution Approaches

### Approach 1: Recursive (Recommended)
Compare roots, recursively compare subtrees. O(n) time, O(h) space.

**Algorithm:**
1. If both null: return true
2. If one null: return false
3. If values differ: return false
4. Return isSameTree(left, left) && isSameTree(right, right)

**Advantages:**
- Simple and elegant
- O(n) time complexity
- Natural recursive structure

### Approach 2: Iterative
Use stacks for simultaneous traversal. O(n) time, O(n) space.

**Disadvantages:**
- O(n) space for stacks
- More complex code

## Key Takeaways

1. **Compare roots** first (structure and value)
2. **Handle null** cases carefully
3. **Recursively check** both subtrees
4. **O(n) time, O(h) space**
5. **Simple and efficient** solution
6. **Foundation for** subtree and other tree comparison problems
