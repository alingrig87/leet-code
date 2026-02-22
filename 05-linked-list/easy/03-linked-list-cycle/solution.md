# Solution Explanation: Linked List Cycle

## Approach: Floyd's Cycle Detection

### Intuition
Use two pointers: slow (1 step) and fast (2 steps). If cycle exists, they'll meet.

### Algorithm
1. Initialize slow = head, fast = head
2. While fast != null and fast.next != null:
   - Move slow one step
   - Move fast two steps
   - If slow == fast: return true (cycle found)
3. Return false (no cycle)

### Complexity
- **Time**: O(n) - at most n steps
- **Space**: O(1) - only two pointers

## Why This Works

- If no cycle, fast reaches null
- If cycle exists, fast enters cycle first
- Fast catches up to slow within cycle
- They meet at some point in cycle
