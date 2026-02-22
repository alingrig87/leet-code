# Palindrome Linked List

## Problem Statement
Given the head of a singly linked list, return `true` if it is a palindrome or `false` otherwise.

**Example 1:**
```
Input: head = [1,2,2,1]
Output: true
```

**Example 2:**
```
Input: head = [1,2]
Output: false
```

## Theory & Data Structures

### Reverse Second Half Approach
This problem combines several techniques:
1. **Find middle**: Use slow/fast pointers (Floyd's algorithm)
2. **Reverse second half**: Reverse the second half of the list
3. **Compare**: Compare first half with reversed second half

#### Key Insight: O(1) Space Solution
- **Challenge**: Can't use O(n) space (like storing values in array)
- **Solution**: Modify list in-place by reversing second half
- **Trade-off**: Modifies original list (but can restore if needed)

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of palindrome check
class PalindromeChecker {
    
    // Check if linked list is palindrome
    public boolean isPalindrome(ListNode head) {
        // Edge cases
        if (head == null || head.next == null) {
            return true;  // Empty or single node is palindrome
        }
        
        // Step 1: Find middle using slow/fast pointers
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Step 2: Reverse second half
        // slow.next is the start of second half
        ListNode secondHalf = reverseList(slow.next);
        
        // Step 3: Compare first half with reversed second half
        ListNode firstHalf = head;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        return true;
    }
    
    // Helper: Reverse linked list
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;  // New head of reversed list
    }
    
    // Alternative: More explicit version
    public boolean isPalindromeExplicit(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find middle
        ListNode slow = head;
        ListNode fast = head;
        
        // Move fast pointer twice as fast
        // When fast reaches end, slow is at middle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // For odd length, slow is at middle node
        // For even length, slow is at end of first half
        // Reverse from slow.next (start of second half)
        ListNode secondHalf = reverseList(slow.next);
        slow.next = null;  // Break the list (optional)
        
        // Compare
        ListNode p1 = head;
        ListNode p2 = secondHalf;
        
        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        
        return true;
    }
}
```

### Finding Middle with Slow/Fast Pointers
- **Slow pointer**: Moves one step at a time
- **Fast pointer**: Moves two steps at a time
- **When fast reaches end**: Slow is at middle (or end of first half for even length)

### Time & Space Complexity

#### Approach: Reverse Second Half
- **Time Complexity**: O(n) - Three passes
  - Find middle: O(n)
  - Reverse second half: O(n/2)
  - Compare: O(n/2)
  - Total: O(n)
- **Space Complexity**: O(1) - Only using pointers
  - No additional data structures
  - Optimal space complexity

#### Alternative: Using Array
- **Time Complexity**: O(n) - Two passes
- **Space Complexity**: O(n) - Store all values
- **Not optimal** for space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if a linked list is a palindrome. Can you do it in O(1) space?"

**Candidate**: "I'll use a three-step approach. First, I'll find the middle of the list using slow and fast pointers. Then I'll reverse the second half of the list. Finally, I'll compare the first half with the reversed second half. If they match, it's a palindrome."

**Interviewer**: "How do you find the middle?"

**Candidate**: "I'll use two pointers - slow moves one step, fast moves two steps. When fast reaches the end, slow is at the middle. For even-length lists, slow will be at the end of the first half, so I'll reverse from slow.next."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For [1,2,2,1], I find middle: slow and fast both start at 1. After one iteration, slow=2, fast=2. After another, slow=2 (second 2), fast=null. I reverse from slow.next (which is 1), getting [1]. Then I compare: first half [1,2] with reversed second half [1,2]. They match, so return true."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) - we make three passes: one to find middle, one to reverse, one to compare. Space complexity is O(1) since we only use pointers and modify the list in-place."

### Follow-up Questions

**Interviewer**: "What if we can't modify the original list?"

**Candidate**: "Then we'd need O(n) space. We could store all values in an array and check if the array is a palindrome. Or we could use recursion with O(n) stack space to compare nodes from both ends."

**Interviewer**: "Can you restore the list after checking?"

**Candidate**: "Yes, we can reverse the second half again after comparison to restore the original list. This would add one more O(n/2) pass, but total time would still be O(n)."

**Interviewer**: "What if the list is very long?"

**Candidate**: "The algorithm still works efficiently - O(n) time and O(1) space. The three passes are all linear, so it scales well. The in-place modification is memory-efficient."

**Interviewer**: "Can you do it with only one pass?"

**Candidate**: "Not with O(1) space. We could use recursion to compare nodes from both ends, but that uses O(n) stack space. The three-pass approach is optimal for O(1) space."

### Tricky Edge Cases

1. **Empty list**: `head = null` → Return `true` (empty is palindrome)
2. **Single node**: `[1]` → Return `true`
3. **Two nodes, palindrome**: `[1,1]` → Return `true`
4. **Two nodes, not palindrome**: `[1,2]` → Return `false`
5. **Even length, palindrome**: `[1,2,2,1]` → Return `true`
6. **Even length, not palindrome**: `[1,2,3,4]` → Return `false`
7. **Odd length, palindrome**: `[1,2,1]` → Return `true` (middle node ignored)
8. **Odd length, not palindrome**: `[1,2,3]` → Return `false`
9. **All same values**: `[1,1,1,1]` → Return `true`
10. **Large list**: Works with any size

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll first handle edge cases - if head is null or head.next is null, return true. Then I'll find the middle using slow and fast pointers. Slow starts at head, fast starts at head. I'll move slow one step and fast two steps until fast reaches the end. Then I'll reverse the second half starting from slow.next. I'll use the standard reversal algorithm with prev, curr, and next pointers. After reversing, I'll compare the first half (starting from head) with the reversed second half. If all values match, return true; otherwise, return false."

**Interviewer**: "How do you handle odd-length lists?"

**Candidate**: "For odd-length lists, the middle node doesn't need to be compared. When we reverse the second half, we start from slow.next, which skips the middle node. When we compare, we only compare until the second half is exhausted, which naturally skips the middle node."

**Interviewer**: "What if you need to restore the list?"

**Candidate**: "I'd reverse the second half again after comparison. I'd need to store a reference to the start of the second half before reversing, then reverse it again to restore the original structure."

## Solution Approaches

### Approach 1: Reverse Second Half (Optimal)
Find middle, reverse second half, compare. O(n) time, O(1) space.

**Algorithm:**
1. Handle edge cases (null, single node)
2. Find middle using slow/fast pointers
3. Reverse second half (from slow.next)
4. Compare first half with reversed second half
5. Return true if all match, false otherwise

**Advantages:**
- O(1) space complexity
- O(n) time complexity
- In-place modification
- Optimal solution

### Approach 2: Using Array
Store values in array, check if array is palindrome. O(n) time, O(n) space.

**Algorithm:**
1. Traverse list, store values in array
2. Check if array is palindrome using two pointers

**Disadvantages:**
- O(n) space complexity
- Not optimal

### Approach 3: Recursive
Use recursion to compare nodes from both ends. O(n) time, O(n) space.

**Disadvantages:**
- O(n) space for recursion stack
- More complex
- Not optimal for space

## Key Takeaways

1. **Find middle** with slow/fast pointers (Floyd's algorithm)
2. **Reverse second half** in-place
3. **Compare** first and second halves
4. **O(1) space** solution - optimal
5. **O(n) time** - three passes but all linear
6. **Handles odd/even length** correctly
7. **Can restore list** by reversing again
8. **Classic problem** combining multiple techniques
9. **Foundation for** more complex list problems
10. **Edge cases matter** - empty, single node, even/odd length
