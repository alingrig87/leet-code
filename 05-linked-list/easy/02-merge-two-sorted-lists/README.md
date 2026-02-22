# Merge Two Sorted Lists

## Problem Statement
You are given the heads of two sorted linked lists `list1` and `list2`.

Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

**Example 1:**
```
Input: list1 = [1,2,4], list2 = [2,3,4]
Output: [1,2,2,3,4,4]
```

**Example 2:**
```
Input: list1 = [], list2 = [0]
Output: [0]
```

**Example 3:**
```
Input: list1 = [], list2 = []
Output: []
```

## Theory & Data Structures

### Two Pointers Technique
This problem uses the **two pointers** technique, with one pointer for each list. We compare values and link the smaller node to the result.

#### Key Insight: Dummy Node Pattern
- **Problem**: We need to build a new list, but don't know the head initially
- **Solution**: Use a dummy node to simplify edge cases
- **Benefit**: Avoids special case handling for empty result list
- **Result**: Return dummy.next (the actual head)

#### Building Merge from Scratch (Conceptual)
```java
// Conceptual implementation of merging two sorted lists
class ListMerger {
    
    // Merge two sorted linked lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Dummy node to simplify edge cases
        // We'll build the result list after dummy
        ListNode dummy = new ListNode(0);
        
        // Current pointer for building result list
        ListNode current = dummy;
        
        // Two pointers for the two input lists
        ListNode ptr1 = list1;
        ListNode ptr2 = list2;
        
        // Merge while both lists have nodes
        while (ptr1 != null && ptr2 != null) {
            // Compare values
            if (ptr1.val <= ptr2.val) {
                // list1's node is smaller or equal
                // Link it to result
                current.next = ptr1;
                // Move list1 pointer forward
                ptr1 = ptr1.next;
            } else {
                // list2's node is smaller
                // Link it to result
                current.next = ptr2;
                // Move list2 pointer forward
                ptr2 = ptr2.next;
            }
            // Move result pointer forward
            current = current.next;
        }
        
        // One list is exhausted, append remaining nodes
        // Only one of these will execute (the non-null list)
        if (ptr1 != null) {
            current.next = ptr1;
        }
        if (ptr2 != null) {
            current.next = ptr2;
        }
        
        // Return the head of merged list (after dummy)
        return dummy.next;
    }
    
    // Alternative: Without dummy node (more complex)
    public ListNode mergeTwoListsNoDummy(ListNode list1, ListNode list2) {
        // Handle edge cases
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        // Determine head
        ListNode head;
        if (list1.val <= list2.val) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }
        
        ListNode current = head;
        
        // Merge remaining nodes
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
        
        // Append remaining
        if (list1 != null) current.next = list1;
        if (list2 != null) current.next = list2;
        
        return head;
    }
}
```

### Why Dummy Node?
The dummy node pattern simplifies the code by:
1. **Avoiding null checks**: We always have a node to link to
2. **Unifying logic**: Same code path for all cases
3. **Cleaner code**: No special handling for first node

### Time & Space Complexity

#### Approach: Two Pointers with Dummy
- **Time Complexity**: O(n + m) - Process all nodes from both lists
  - Best case: O(min(n, m)) - One list much shorter
  - Average case: O(n + m)
  - Worst case: O(n + m) - Process all nodes
- **Space Complexity**: O(1) - Only using pointers
  - Dummy node is constant space
  - No additional data structures
  - We're reusing existing nodes, not creating new ones

## Interview Simulation

### Initial Discussion

**Interviewer**: "Merge two sorted linked lists."

**Candidate**: "I'll use a dummy node to simplify the code. I'll have two pointers, one for each list. I'll compare the values at both pointers, link the smaller node to the result, and advance that pointer. I'll continue until one list is exhausted, then append the remaining nodes from the other list."

**Interviewer**: "Why use a dummy node?"

**Candidate**: "The dummy node simplifies the code by avoiding special cases. Without it, we'd need to check if the result list is empty and handle the first node separately. With a dummy node, we can always link to dummy.next, making the code cleaner and more uniform."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For list1 = [1,2,4] and list2 = [2,3,4], I create a dummy node. Compare 1 and 2: 1 is smaller, link 1 to dummy, move ptr1 to 2. Compare 2 and 2: equal, link 2 from list1, move ptr1 to 4. Compare 4 and 2: 2 is smaller, link 2 from list2, move ptr2 to 3. Compare 4 and 3: 3 is smaller, link 3, move ptr2 to 4. Compare 4 and 4: equal, link 4 from list1, move ptr1 to null. Append remaining 4 from list2. Result: [1,2,2,3,4,4]."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n + m) since we process each node from both lists exactly once. Space complexity is O(1) since we only use a few pointers and the dummy node. We're reusing the existing nodes, not creating new ones."

### Follow-up Questions

**Interviewer**: "What if the lists are very long?"

**Candidate**: "The same approach works. We process each node once, so the time complexity remains O(n + m). The space complexity is still O(1) since we only use pointers."

**Interviewer**: "Can you do this recursively?"

**Candidate**: "Yes, we can recursively merge. The base case is when one list is null, return the other. Otherwise, compare heads, link the smaller one, and recursively merge the rest. However, recursive uses O(n + m) space for the call stack, so iterative is preferred."

**Interviewer**: "What if we need to merge k sorted lists?"

**Candidate**: "We could use a min-heap (priority queue) to always get the smallest node from all lists. Or we could merge lists pairwise. The heap approach is O(n log k) where n is total nodes and k is number of lists."

**Interviewer**: "What if the lists have duplicates?"

**Candidate**: "The algorithm handles duplicates correctly. When values are equal, we can link either one first. The relative order of duplicates is preserved within each list."

**Interviewer**: "Can you optimize this further?"

**Candidate**: "The algorithm is already optimal - O(n + m) time and O(1) space. We can't do better since we need to process each node. The dummy node pattern is already an optimization that simplifies the code."

### Tricky Edge Cases

1. **One list empty**: `list1=[], list2=[1,2]` → Return `[1,2]`
2. **Both empty**: `list1=[], list2=[]` → Return `null` or `[]`
3. **All elements from one list**: `list1=[1,2,3], list2=[]` → Return `[1,2,3]`
4. **Alternating elements**: `list1=[1,3,5], list2=[2,4,6]` → Return `[1,2,3,4,5,6]`
5. **Duplicates**: `list1=[1,2,2], list2=[2,3]` → Return `[1,2,2,2,3]`
6. **Single node each**: `list1=[1], list2=[2]` → Return `[1,2]`
7. **One list all smaller**: `list1=[1,2], list2=[3,4]` → Return `[1,2,3,4]`
8. **One list all larger**: `list1=[3,4], list2=[1,2]` → Return `[1,2,3,4]`
9. **Interleaved**: `list1=[1,3,5], list2=[2,4]` → Return `[1,2,3,4,5]`
10. **Large lists**: Works with any size

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll create a dummy node to simplify edge cases. I'll initialize current to dummy, and have two pointers ptr1 and ptr2 pointing to the heads of both lists. In a while loop, I'll continue while both lists have nodes. Inside, I'll compare the values. If ptr1's value is smaller or equal, I'll link ptr1 to current.next, then move ptr1 forward. Otherwise, I'll link ptr2. After linking, I'll move current forward. When one list is exhausted, I'll append the remaining nodes from the other list. Finally, I'll return dummy.next, which is the head of the merged list."

**Interviewer**: "Why link to current.next instead of current?"

**Candidate**: "Because current is the last node in the result list so far. To add a new node, we need to set current.next to point to it, then move current forward to become the new last node. This builds the list correctly."

**Interviewer**: "What happens if we don't use a dummy node?"

**Candidate**: "We'd need to handle the first node separately - check which list has the smaller first node, set that as head, then continue merging. We'd also need to handle the case where one or both lists are empty. The dummy node pattern eliminates all these special cases."

## Solution Approaches

### Approach 1: Two Pointers with Dummy Node (Recommended)
Use dummy node, compare and link. O(n+m) time, O(1) space.

**Algorithm:**
1. Create dummy node
2. Initialize current = dummy, ptr1 = list1, ptr2 = list2
3. While ptr1 != null and ptr2 != null:
   - If ptr1.val <= ptr2.val: link ptr1, move ptr1
   - Else: link ptr2, move ptr2
   - Move current forward
4. Append remaining nodes
5. Return dummy.next

**Advantages:**
- Simple and clean code
- Handles all edge cases uniformly
- O(1) space complexity
- Optimal time complexity

### Approach 2: Without Dummy Node
Handle first node separately. O(n+m) time, O(1) space.

**Disadvantages:**
- More complex code
- Special case handling needed
- Less elegant

### Approach 3: Recursive
Recursively merge lists. O(n+m) time, O(n+m) space.

**Disadvantages:**
- O(n+m) space for recursion stack
- Less efficient
- Potential stack overflow for long lists

## Key Takeaways

1. **Dummy node** simplifies code and handles edge cases
2. **Two pointers** - one for each list
3. **Compare and link** smaller node
4. **Append remaining** when one list exhausted
5. **O(n+m) time, O(1) space** - optimal solution
6. **Reuse existing nodes** - no new nodes created
7. **Classic pattern** for merging sorted structures
8. **Foundation for** merging k sorted lists
9. **Handles duplicates** correctly
10. **Clean and elegant** solution with dummy node
