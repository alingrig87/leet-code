# Reverse Linked List

## Problem Statement
Given the head of a singly linked list, reverse the list, and return the head of the reversed list.

**Example 1:**
```
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
```

**Example 2:**
```
Input: head = [1,2]
Output: [2,1]
```

**Example 3:**
```
Input: head = []
Output: []
```

## Theory & Data Structures

### Linked List Structure
A **singly linked list** is a data structure where each node contains:
- **Data**: The value stored in the node
- **Next**: A pointer/reference to the next node
- **Last node**: Points to null

#### Building Linked List from Scratch (Conceptual)
```java
// Conceptual implementation of linked list node
class ListNode {
    int val;
    ListNode next;
    
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { 
        this.val = val; 
        this.next = next; 
    }
}
```

### Iterative Approach: Three Pointers
The iterative approach uses **three pointers** to reverse the links as we traverse:
1. **prev**: Points to the previous node (initially null)
2. **curr**: Points to the current node (starts at head)
3. **next**: Points to the next node (to preserve the link)

#### Building Iterative Reversal from Scratch (Conceptual)
```java
// Conceptual implementation of iterative reversal
class LinkedListReverser {
    
    // Reverse linked list iteratively
    public ListNode reverseList(ListNode head) {
        // Three pointers for reversal
        ListNode prev = null;    // Previous node (starts as null)
        ListNode curr = head;    // Current node (starts at head)
        
        // Traverse the list
        while (curr != null) {
            // Save next node before reversing link
            // This is crucial - we need to preserve the next node
            ListNode next = curr.next;
            
            // Reverse the link: point current node to previous
            curr.next = prev;
            
            // Move pointers forward
            prev = curr;  // Previous becomes current
            curr = next;  // Current becomes next (saved earlier)
        }
        
        // When loop ends, curr is null, prev is the new head
        return prev;
    }
    
    // Step-by-step visualization:
    // Initial: null <- 1 -> 2 -> 3 -> 4 -> 5 -> null
    // After 1st iteration: null <- 1  2 -> 3 -> 4 -> 5 -> null
    //                       prev  curr next
    // After 2nd iteration: null <- 1 <- 2  3 -> 4 -> 5 -> null
    //                            prev  curr next
    // Continue until: null <- 1 <- 2 <- 3 <- 4 <- 5  null
    //                                          prev  curr
    // Return prev (which is 5, the new head)
}
```

### Recursive Approach
The recursive approach reverses the list by:
1. Recursively reversing the rest of the list
2. Reversing the current node's link
3. Returning the new head

#### Building Recursive Reversal from Scratch (Conceptual)
```java
// Conceptual implementation of recursive reversal
class LinkedListReverserRecursive {
    
    // Reverse linked list recursively
    public ListNode reverseList(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursively reverse the rest of the list
        // newHead is the head of the reversed rest
        ListNode newHead = reverseList(head.next);
        
        // Reverse the link: point next node back to current
        head.next.next = head;
        
        // Set current node's next to null (it will be set by previous call)
        head.next = null;
        
        // Return the new head (from the recursive call)
        return newHead;
    }
    
    // How it works:
    // reverseList(1->2->3->4->5)
    //   reverseList(2->3->4->5)
    //     reverseList(3->4->5)
    //       reverseList(4->5)
    //         reverseList(5) -> returns 5
    //       Now: 4->5, reverse: 5->4, return 5
    //     Now: 3->4->5->4, reverse: 4->3, return 5
    //   Continue until all reversed
}
```

### Time & Space Complexity

#### Approach 1: Iterative
- **Time Complexity**: O(n) - Single pass through list
  - Best case: O(n)
  - Average case: O(n)
  - Worst case: O(n)
- **Space Complexity**: O(1) - Only using three pointers
  - Constant space regardless of list size
  - Optimal space complexity

#### Approach 2: Recursive
- **Time Complexity**: O(n) - Single pass through list
  - Same as iterative
- **Space Complexity**: O(n) - Recursion stack depth
  - Each recursive call uses stack space
  - Stack depth equals list length
  - Not optimal for space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Reverse a linked list."

**Candidate**: "I'll use three pointers: prev, curr, and next. As I traverse the list, I'll reverse the link from curr to prev, then move all pointers forward. The prev pointer will become the new head when we're done."

**Interviewer**: "Can you walk me through the algorithm step by step?"

**Candidate**: "Sure. I'll initialize prev to null and curr to head. In a loop, I'll save curr.next in a next variable (to preserve it), then set curr.next to prev (reversing the link). Then I'll move prev to curr and curr to next. When curr becomes null, prev is the new head of the reversed list."

**Interviewer**: "Why do we need to save next before reversing?"

**Candidate**: "Because once we reverse the link (curr.next = prev), we lose the reference to the original next node. By saving it first, we can continue traversing the list."

**Interviewer**: "Can you do it recursively?"

**Candidate**: "Yes. I'll recursively reverse the rest of the list, then reverse the current node's link. The base case is when head is null or head.next is null. However, the iterative approach is preferred because it uses O(1) space versus O(n) space for the recursion stack."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) for both approaches since we visit each node once. Space complexity is O(1) for iterative and O(n) for recursive due to the recursion stack."

### Follow-up Questions

**Interviewer**: "What if the list is empty or has one node?"

**Candidate**: "If the list is empty (head is null), we return null. If it has one node, we return head as is - no reversal needed. Both cases are handled naturally by the algorithm."

**Interviewer**: "What if we need to reverse only a portion of the list?"

**Candidate**: "We'd modify the algorithm to stop at a specific node. We'd track the node before the portion to reverse and the node after, then reverse the portion and reconnect the links."

**Interviewer**: "What if the list is circular?"

**Candidate**: "We'd need to detect the cycle first (using Floyd's cycle detection). Then we'd reverse carefully to avoid infinite loops. The standard reversal algorithm would cause issues with circular lists."

**Interviewer**: "Can you reverse a doubly linked list?"

**Candidate**: "Yes, it's similar but we need to reverse both next and prev pointers. The algorithm is slightly more complex but follows the same principle."

**Interviewer**: "What if we need to reverse in groups of k?"

**Candidate**: "We'd use a recursive or iterative approach that reverses k nodes at a time, then moves to the next group. We'd need to track group boundaries and reconnect them properly."

### Tricky Edge Cases

1. **Empty list**: `head = null` → Return `null`
2. **Single node**: `[1]` → Return `[1]` (no change)
3. **Two nodes**: `[1,2]` → Return `[2,1]`
4. **Long list**: `[1,2,3,4,5]` → Return `[5,4,3,2,1]`
5. **Already reversed**: Algorithm handles correctly
6. **Large list**: Works with any size
7. **Negative values**: Works with any integer values
8. **Duplicate values**: Works correctly (values don't matter, only structure)

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll initialize prev to null and curr to head. I'll use a while loop that continues while curr is not null. Inside the loop, I'll first save curr.next in a next variable - this is crucial because we'll lose this reference when we reverse the link. Then I'll reverse the link by setting curr.next to prev. After that, I'll move prev to curr and curr to next. When the loop ends, curr is null, which means we've processed all nodes. Prev is now pointing to what was the last node, which is the new head, so I'll return prev."

**Interviewer**: "Why is saving next before reversing so important?"

**Candidate**: "Because curr.next is our only way to access the next node. Once we set curr.next = prev, we've overwritten that reference. If we hadn't saved it, we'd lose our way to continue traversing the list. It's like crossing a bridge - we need to know where we're going before we destroy the bridge behind us."

**Interviewer**: "What happens if we forget to save next?"

**Candidate**: "We'd lose the reference to the rest of the list and couldn't continue. The algorithm would only reverse the first node and then stop, or potentially cause a null pointer exception."

## Solution Approaches

### Approach 1: Iterative with Three Pointers (Recommended)
Use three pointers to reverse links as we traverse. O(n) time, O(1) space.

**Algorithm:**
1. Initialize prev = null, curr = head
2. While curr != null:
   - Save next = curr.next
   - Reverse link: curr.next = prev
   - Move pointers: prev = curr, curr = next
3. Return prev (new head)

**Advantages:**
- O(1) space complexity
- Simple and intuitive
- Optimal solution
- Easy to understand and implement

### Approach 2: Recursive
Recursively reverse the rest, then reverse current link. O(n) time, O(n) space.

**Algorithm:**
1. Base case: if head == null or head.next == null, return head
2. Recursively reverse rest: newHead = reverseList(head.next)
3. Reverse current link: head.next.next = head
4. Set head.next = null
5. Return newHead

**Advantages:**
- Elegant and concise
- Natural recursive structure

**Disadvantages:**
- O(n) space for recursion stack
- Potential stack overflow for very long lists
- Less efficient than iterative

### Approach 3: Using Stack (Not Recommended)
Push all nodes onto stack, then pop and rebuild. O(n) time, O(n) space.

**Disadvantages:**
- Uses O(n) extra space
- More complex
- Not optimal

## Key Takeaways

1. **Three pointers** (prev, curr, next) for iterative reversal
2. **Save next** before reversing link - crucial step
3. **O(1) space** with iterative approach (optimal)
4. **O(n) space** with recursive approach (due to stack)
5. **Handle null cases** - empty list, single node
6. **Reverse links** as you traverse
7. **Return prev** when loop ends (new head)
8. **Iterative preferred** for space efficiency
9. **Recursive elegant** but less efficient
10. **Foundation for many** linked list problems
