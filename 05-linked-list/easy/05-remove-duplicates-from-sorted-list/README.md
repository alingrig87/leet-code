# Remove Duplicates from Sorted List

## Problem Statement
Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

**Example 1:**
```
Input: head = [1,1,2]
Output: [1,2]
```

**Example 2:**
```
Input: head = [1,1,2,3,3]
Output: [1,2,3]
```

## Theory & Data Structures

### Single Pointer Traversal
This problem uses a **single pointer** to traverse the sorted list. Since the list is sorted, duplicates are adjacent, so we can skip duplicate nodes by linking over them.

#### Key Insight: Sorted Property
- **Duplicates are adjacent**: In a sorted list, all duplicates appear consecutively
- **Skip duplicates**: If current node's value equals next node's value, skip the next node
- **Link over**: Set current.next = current.next.next to skip duplicate

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of duplicate removal
class DuplicateRemover {
    
    // Remove duplicates from sorted linked list
    public ListNode deleteDuplicates(ListNode head) {
        // Edge case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Current pointer for traversal
        ListNode current = head;
        
        // Traverse the list
        while (current != null && current.next != null) {
            // If current value equals next value, skip next node
            if (current.val == current.next.val) {
                // Skip the duplicate node
                current.next = current.next.next;
                // Don't move current - check if next is also duplicate
            } else {
                // Values are different, move to next node
                current = current.next;
            }
        }
        
        return head;
    }
    
    // Why don't we move current when we find a duplicate?
    // Because the next node might also be a duplicate
    // Example: [1,1,1,2] - we need to skip all 1s before moving
    
    // Alternative: More explicit version
    public ListNode deleteDuplicatesExplicit(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode current = head;
        
        while (current.next != null) {
            if (current.val == current.next.val) {
                // Remove duplicate
                current.next = current.next.next;
            } else {
                // Move to next distinct value
                current = current.next;
            }
        }
        
        return head;
    }
}
```

### Why Single Pointer Works
- **Sorted list**: Duplicates are always adjacent
- **Skip duplicates**: Link over duplicate nodes
- **No need for extra pointer**: Current pointer is sufficient

### Time & Space Complexity

#### Approach: Single Pointer
- **Time Complexity**: O(n) - Single pass through list
  - Best case: O(n) - No duplicates
  - Average case: O(n) - Some duplicates
  - Worst case: O(n) - Many duplicates
- **Space Complexity**: O(1) - Only using current pointer
  - No additional data structures
  - Constant space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Remove duplicates from a sorted linked list."

**Candidate**: "Since the list is sorted, duplicates are adjacent. I'll traverse the list with a single pointer. If the current node's value equals the next node's value, I'll skip the next node by linking current.next to current.next.next. Otherwise, I'll move to the next node."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For [1,1,2,3,3], I start with current at 1. current.val (1) == current.next.val (1), so I set current.next = current.next.next, getting [1,2,3,3]. Current is still at 1. current.val (1) != current.next.val (2), so I move current to 2. current.val (2) != current.next.val (3), so I move current to 3. current.val (3) == current.next.val (3), so I set current.next = current.next.next, getting [1,2,3]. Done."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we make a single pass through the list. Space complexity is O(1) since we only use a single pointer."

### Follow-up Questions

**Interviewer**: "What if the list isn't sorted?"

**Candidate**: "Then we'd need a HashSet to track seen values. We'd check if a value has been seen before, and if so, skip it. This would require O(n) space."

**Interviewer**: "What if we need to remove all occurrences of duplicates, not just keep one?"

**Candidate**: "That's a different problem. We'd need to track which values are duplicates, then remove all nodes with those values. We'd need two passes or additional space."

**Interviewer**: "Can you do this recursively?"

**Candidate**: "Yes, but the iterative approach is preferred for O(1) space. Recursive would use O(n) space for the call stack."

### Tricky Edge Cases

1. **No duplicates**: `[1,2,3]` → Return `[1,2,3]` (no change)
2. **All duplicates**: `[1,1,1]` → Return `[1]` (single node)
3. **Duplicates at start**: `[1,1,2,3]` → Return `[1,2,3]`
4. **Duplicates at end**: `[1,2,3,3]` → Return `[1,2,3]`
5. **Duplicates in middle**: `[1,2,2,3]` → Return `[1,2,3]`
6. **Multiple duplicate groups**: `[1,1,2,2,3,3]` → Return `[1,2,3]`
7. **Empty list**: `null` → Return `null`
8. **Single node**: `[1]` → Return `[1]` (no change)
9. **Two nodes, duplicates**: `[1,1]` → Return `[1]`
10. **Two nodes, no duplicates**: `[1,2]` → Return `[1,2]`

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll handle the edge case first - if head is null or head.next is null, return head. Then I'll initialize current to head. I'll use a while loop that continues while current and current.next are not null. Inside the loop, if current.val equals current.next.val, I'll skip the duplicate by setting current.next to current.next.next. I won't move current in this case because the next node might also be a duplicate. If the values are different, I'll move current to current.next. After the loop, I'll return head."

**Interviewer**: "Why don't you move current when you find a duplicate?"

**Candidate**: "Because there might be multiple consecutive duplicates. For example, if we have [1,1,1,2], when current is at the first 1 and we find that current.next is also 1, we skip it. But we need to check if the new current.next (which is the third 1) is also a duplicate. So we stay at current and check again."

## Solution Approaches

### Approach 1: Single Pointer (Optimal)
Traverse list, skip duplicates. O(n) time, O(1) space.

**Algorithm:**
1. Handle edge cases (null, single node)
2. Initialize current = head
3. While current != null && current.next != null:
   - If current.val == current.next.val: skip duplicate
   - Else: move current forward
4. Return head

**Advantages:**
- O(1) space complexity
- O(n) time complexity
- Simple and efficient
- Optimal solution

### Approach 2: Using Extra Space (Not Recommended)
Store seen values in HashSet. O(n) time, O(n) space.

**Disadvantages:**
- O(n) space complexity
- Not needed for sorted list
- Less efficient

## Key Takeaways

1. **Sorted list** enables O(1) space solution
2. **Skip duplicates** by linking over them
3. **Don't move current** when skipping - check again
4. **O(n) time, O(1) space** - optimal solution
5. **Simple traversal** with single pointer
6. **Duplicates are adjacent** in sorted list
7. **Edge cases matter** - empty, single node, all duplicates
8. **Foundation for** more complex list problems
9. **In-place modification** - no new nodes created
10. **Classic problem** - good for understanding list manipulation
