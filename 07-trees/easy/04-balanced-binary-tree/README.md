# Balanced Binary Tree

## Problem Statement
Given a binary tree, determine if it is height-balanced.

A height-balanced binary tree is a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

**Example 1:**
```
Input: root = [3,9,20,null,null,15,7]
Output: true
```

## Theory & Data Structures

### DFS with Height
Calculate height at each node, check if balanced. Return -1 if unbalanced to signal early termination.

### Time & Space Complexity

#### Approach: DFS
- **Time Complexity**: O(n) - Visit each node
- **Space Complexity**: O(h) - Recursion stack

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if tree is height-balanced."

**Candidate**: "I'll calculate height at each node. If left and right heights differ by more than 1, return false. Use -1 as sentinel value for unbalanced."

**Interviewer**: "Why -1?"

**Candidate**: "It allows early termination - once we find unbalanced subtree, we can immediately return without checking rest."

### Follow-up Questions

**Interviewer**: "Can you optimize?"

**Candidate**: "The -1 approach already optimizes by early termination. Without it, we'd need to check all nodes."

### Tricky Edge Cases

1. **Empty tree**: Return true
2. **Single node**: Return true
3. **Unbalanced at root**: Return false
4. **Unbalanced in subtree**: Return false

## Solution Approaches

### Approach: DFS with Early Termination (Optimal)
Calculate height, return -1 if unbalanced. O(n) time, O(h) space.

## Key Takeaways

1. **Height difference** must be <= 1
2. **Check all nodes**, not just root
3. **Early termination** with -1
4. **Efficient O(n)** solution
