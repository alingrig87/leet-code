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
Compare left and right subtrees as if they're two separate trees, but with mirrored comparison.

### Time & Space Complexity

#### Approach: Recursive
- **Time Complexity**: O(n) - Visit each node
- **Space Complexity**: O(h) - Recursion stack

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if tree is symmetric."

**Candidate**: "I'll compare left and right subtrees. For symmetry, left's left should match right's right, and left's right should match right's left."

**Interviewer**: "What's the base case?"

**Candidate**: "If both null, return true. If one null, return false. If values differ, return false."

### Follow-up Questions

**Interviewer**: "Can you do it iteratively?"

**Candidate**: "Yes, using two queues for simultaneous traversal of left and right subtrees."

### Tricky Edge Cases

1. **Empty tree**: Return true
2. **Single node**: Return true
3. **Asymmetric values**: Return false
4. **Asymmetric structure**: Return false

## Solution Approaches

### Approach: Recursive (Optimal)
Compare left and right with mirrored logic. O(n) time, O(h) space.

## Key Takeaways

1. **Mirrored comparison**: left.left vs right.right, left.right vs right.left
2. **Handle null** cases
3. **O(n) time** - visit each node
4. **Elegant recursive** solution
