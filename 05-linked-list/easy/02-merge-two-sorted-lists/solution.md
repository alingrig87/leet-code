# Solution Explanation: Merge Two Sorted Lists

## Approach: Two Pointers with Dummy

### Intuition
Use dummy node, compare heads of both lists, link smaller node, advance pointer.

### Algorithm
1. Create dummy node, current = dummy
2. While both lists non-empty:
   - Compare list1.val and list2.val
   - Link smaller to current.next
   - Advance current and chosen list pointer
3. Append remaining nodes from non-empty list
4. Return dummy.next

### Complexity
- **Time**: O(n + m) - process all nodes
- **Space**: O(1) - only pointers

## Why Dummy Node

- Simplifies code by avoiding null checks
- Always have a node to link to
- Return dummy.next as result
- Cleaner implementation
