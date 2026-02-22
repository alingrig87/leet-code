# Solution Explanation: Reverse Linked List

## Approach 1: Iterative

### Intuition
Use three pointers: prev, curr, next. Reverse link from curr to prev as we traverse.

### Algorithm
1. Initialize prev = null, curr = head
2. While curr != null:
   - Store next = curr.next
   - Reverse link: curr.next = prev
   - Move pointers: prev = curr, curr = next
3. Return prev (new head)

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only pointers

## Approach 2: Recursive

### Intuition
Recursively reverse rest of list, then reverse current node's link.

### Algorithm
1. Base case: if head == null or head.next == null, return head
2. Recursively reverse rest: newHead = reverse(head.next)
3. Reverse current link: head.next.next = head, head.next = null
4. Return newHead

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(n) - recursion stack

## Why Iterative is Better

- O(1) space vs O(n) for recursive
- No stack overflow risk
- More efficient in practice
