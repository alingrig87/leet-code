# Invert Binary Tree

## Problem Statement
Given the root of a binary tree, invert the tree, and return its root.

**Example 1:**
```
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
```

**Example 2:**
```
Input: root = [2,1,3]
Output: [2,3,1]
```

**Example 3:**
```
Input: root = []
Output: []
```

## Theory & Data Structures

### Binary Tree Structure
A **binary tree** is a tree data structure where each node has at most two children:
- **Left child**: Points to left subtree
- **Right child**: Points to right subtree
- **Root**: The topmost node
- **Leaf**: Node with no children

#### Building Binary Tree from Scratch (Conceptual)
```java
// Conceptual implementation of binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
```

### Recursive Approach
The recursive approach inverts the tree by:
1. Recursively inverting the left subtree
2. Recursively inverting the right subtree
3. Swapping the left and right children of the current node

#### Building Recursive Inversion from Scratch (Conceptual)
```java
// Conceptual implementation of tree inversion
class TreeInverter {
    
    // Invert binary tree recursively
    public TreeNode invertTree(TreeNode root) {
        // Base case: empty tree
        if (root == null) {
            return null;
        }
        
        // Recursively invert left and right subtrees
        TreeNode invertedLeft = invertTree(root.left);
        TreeNode invertedRight = invertTree(root.right);
        
        // Swap the children
        root.left = invertedRight;
        root.right = invertedLeft;
        
        // Return root (now with inverted children)
        return root;
    }
    
    // More concise version (swap then recurse)
    public TreeNode invertTreeConcise(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        // Swap children first
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        // Then recursively invert subtrees
        invertTree(root.left);
        invertTree(root.right);
        
        return root;
    }
}
```

### Iterative Approach
The iterative approach uses a queue (BFS) or stack (DFS) to traverse the tree and swap children at each node.

#### Building Iterative Inversion from Scratch (Conceptual)
```java
// Conceptual implementation of iterative inversion
class TreeInverterIterative {
    
    // Invert using BFS (level-order traversal)
    public TreeNode invertTreeBFS(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            // Swap children
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            
            // Add children to queue
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        
        return root;
    }
    
    // Invert using DFS (stack)
    public TreeNode invertTreeDFS(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            
            // Swap children
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            
            // Push children to stack
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        
        return root;
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

#### Approach 2: Iterative (BFS/DFS)
- **Time Complexity**: O(n) - Visit each node once
- **Space Complexity**: O(n) - Queue/stack can contain all nodes
  - Best case: O(1) - Single node
  - Average case: O(n/2) - Last level has most nodes
  - Worst case: O(n) - All nodes in queue/stack

## Interview Simulation

### Initial Discussion

**Interviewer**: "Invert a binary tree - swap left and right children at every node."

**Candidate**: "I'll use a recursive approach. For each node, I'll recursively invert the left and right subtrees, then swap the left and right children of the current node. The base case is when the node is null."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For a tree with root 4, left child 2, right child 7. I'll recursively invert subtree rooted at 2, then subtree rooted at 7. After both subtrees are inverted, I'll swap the children of 4, so 2 becomes right child and 7 becomes left child."

**Interviewer**: "Can you do it iteratively?"

**Candidate**: "Yes, I can use a queue for level-order traversal or a stack for DFS. I'll process each node, swap its children, then add the children to the queue or stack for further processing."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) for both approaches since we visit each node once. Space complexity is O(h) for recursive where h is the height, and O(n) for iterative in the worst case when using a queue."

### Follow-up Questions

**Interviewer**: "What if the tree is very large?"

**Candidate**: "The recursive approach might cause stack overflow for very deep trees. In that case, the iterative approach with a queue or stack would be better, though it uses more memory. For balanced trees, recursive is fine and more elegant."

**Interviewer**: "What if we need to invert only a portion of the tree?"

**Candidate**: "We'd modify the algorithm to only process nodes within a certain range or depth. We'd add conditions to check if a node should be inverted before swapping its children."

**Interviewer**: "Can you invert in-place without using extra space?"

**Candidate**: "The recursive approach already does this - we're just swapping pointers, not creating new nodes. The only extra space is the recursion stack. For iterative, we need the queue/stack, but we're still modifying the tree in-place."

**Interviewer**: "What if the tree has a million nodes?"

**Candidate**: "For a balanced tree, recursive would use O(log n) stack space, which is manageable. For a skewed tree, it would use O(n) stack space, which might cause issues. Iterative with a queue would use O(n) space in the worst case, but avoids stack overflow."

### Tricky Edge Cases

1. **Empty tree**: `root = null` → Return `null`
2. **Single node**: `[1]` → Return `[1]` (no change, but still processed)
3. **Skewed tree**: `[1,2,null,3]` → Invert correctly
4. **Balanced tree**: `[4,2,7,1,3,6,9]` → Invert correctly
5. **Only left children**: Invert to only right children
6. **Only right children**: Invert to only left children
7. **Complete tree**: All levels filled → Invert correctly
8. **Large tree**: Works with any size

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll start with the base case - if root is null, return null. Then I'll recursively invert the left subtree and store the result. I'll recursively invert the right subtree and store the result. Then I'll swap the children by setting root.left to the inverted right subtree and root.right to the inverted left subtree. Finally, I'll return the root."

**Interviewer**: "Why swap after inverting subtrees?"

**Candidate**: "Actually, we can do it either way - swap then recurse, or recurse then swap. Both work correctly. Swapping after ensures we've already inverted the subtrees, so when we swap, we're swapping already-inverted subtrees. Swapping before and then recursing also works because we're inverting what we just swapped."

**Interviewer**: "What's the difference between the two approaches?"

**Candidate**: "Functionally, they're equivalent. The order doesn't matter because we're going to process all nodes anyway. Some prefer swapping first because it's more intuitive - swap, then process the swapped children."

## Solution Approaches

### Approach 1: Recursive (Recommended)
Recursively invert subtrees, then swap children. O(n) time, O(h) space.

**Algorithm:**
1. Base case: if root is null, return null
2. Recursively invert left subtree
3. Recursively invert right subtree
4. Swap left and right children
5. Return root

**Advantages:**
- Clean and elegant
- Easy to understand
- O(h) space for balanced trees
- Natural recursive structure

### Approach 2: Iterative BFS
Use queue for level-order traversal. O(n) time, O(n) space.

**Algorithm:**
1. If root is null, return null
2. Create queue, add root
3. While queue not empty:
   - Poll node
   - Swap its children
   - Add children to queue
4. Return root

**Advantages:**
- No recursion stack
- Avoids stack overflow for deep trees

**Disadvantages:**
- More code
- O(n) space in worst case

### Approach 3: Iterative DFS
Use stack for depth-first traversal. O(n) time, O(n) space.

**Similar to BFS but uses stack instead of queue**

## Key Takeaways

1. **Recursive approach** is clean and elegant
2. **Swap children** after inverting subtrees (or before)
3. **O(n) time** - visit each node once
4. **O(h) space** for recursive, O(n) for iterative
5. **Base case**: null node returns null
6. **In-place modification** - no new nodes created
7. **Classic tree problem** - fundamental to know
8. **Mirror image** - tree becomes its mirror
9. **Works for any tree structure** - balanced, skewed, etc.
10. **Foundation for** more complex tree problems
