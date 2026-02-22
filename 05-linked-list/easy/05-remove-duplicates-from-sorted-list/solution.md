# Solution Explanation: Remove Duplicates from Sorted List

## Approach: Single Pointer

### Intuition
Traverse list, if current value equals next value, skip next node by linking current to next.next.

### Algorithm
1. Initialize current = head
2. While current != null and current.next != null:
   - If current.val == current.next.val:
     - Skip duplicate: current.next = current.next.next
   - Else:
     - Move current forward
3. Return head

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only pointers

## Why This Works

- Sorted list means duplicates are adjacent
- Skip duplicates by linking over them
- Each node processed once
- Efficient O(n) solution
