# Merge Two Sorted Lists II

## Problem Statement
Merge two sorted linked lists and return it as a sorted list. The list should be made by splicing together the nodes of the first two lists.

**Example 1:**
```
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
```

**Example 2:**
```
Input: list1 = [], list2 = []
Output: []
```

## Theory & Data Structures

### Two Pointers with Dummy Node
This problem uses **two pointers** to traverse both lists simultaneously, comparing nodes and linking them in sorted order. A **dummy node** simplifies the code by providing a starting point.

#### Key Insight: Dummy Node Pattern
- **Dummy node**: Creates a starting point, avoids special cases
- **Compare and link**: Compare current nodes, link smaller one
- **Move pointers**: Advance pointer of list we took from
- **Handle remaining**: Link remaining nodes after main loop

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of merge sorted lists
class MergeSortedLists {
    
    // Merge two sorted lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Dummy node to simplify code
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Compare and link nodes
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        
        // Link remaining nodes
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        
        return dummy.next;
    }
    
    // Why dummy node?
    // - Avoids special case for empty lists
    // - Provides starting point
    // - Simplifies code
    
    // Alternative: Recursive approach
    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        
        if (list1.val <= list2.val) {
            list1.next = mergeTwoListsRecursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoListsRecursive(list1, list2.next);
            return list2;
        }
    }
}
```

### Time & Space Complexity

#### Approach: Two Pointers with Dummy
- **Time Complexity**: O(n + m) - Visit each node once
  - n = length of list1
  - m = length of list2
- **Space Complexity**: O(1) - Only pointers
  - Dummy node: O(1)
  - No extra data structures

## Interview Simulation

### Initial Discussion

**Interviewer**: "Merge two sorted linked lists."

**Candidate**: "I'll use a dummy node to simplify the code. I'll compare nodes from both lists, link the smaller one, and advance the pointer. After the main loop, I'll link any remaining nodes from the list that wasn't exhausted."

**Interviewer**: "Why use a dummy node?"

**Candidate**: "The dummy node provides a starting point and avoids special cases. Without it, we'd need to check if the result list is empty before starting, which complicates the code."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n + m) since we visit each node once. Space complexity is O(1) since we only use pointers."

### Follow-up Questions

**Interviewer**: "Can you do this recursively?"

**Candidate**: "Yes, but the iterative approach is preferred for O(1) space. Recursive would use O(n + m) space for the call stack."

### Tricky Edge Cases

1. **Both empty**: Return null
2. **One empty**: Return other list
3. **All list1 smaller**: Link all list1, then list2
4. **All list2 smaller**: Link all list2, then list1
5. **Equal elements**: Handle correctly (use <=)

## Solution Approaches

### Approach: Two Pointers with Dummy (Optimal)
Compare and link nodes. O(n+m) time, O(1) space.

**Algorithm:**
1. Create dummy node
2. While both lists not null:
   - Compare current nodes
   - Link smaller one
   - Advance pointer
3. Link remaining nodes
4. Return dummy.next

**Advantages:**
- O(1) space complexity
- Simple and efficient
- Optimal solution

## Key Takeaways

1. **Dummy node** simplifies code
2. **Compare and link** smaller node
3. **O(n+m) time, O(1) space**
4. **Handle remaining** nodes
5. **Foundation for** merge sort on linked lists
