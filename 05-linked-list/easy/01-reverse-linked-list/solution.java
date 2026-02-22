/**
 * LeetCode 206: Reverse Linked List
 * 
 * Problem: Reverse a singly linked list.
 * 
 * Solution Approach: Iterative with three pointers
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        // Edge case: empty or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Three pointers for reversal
        ListNode prev = null;  // Previous node (starts as null)
        ListNode curr = head; // Current node
        ListNode next = null;  // Next node (will be set in loop)
        
        // Traverse and reverse links
        while (curr != null) {
            // Store next node before reversing link
            next = curr.next;
            
            // Reverse the link: point current to previous
            curr.next = prev;
            
            // Move pointers forward
            prev = curr;  // Previous becomes current
            curr = next;  // Current becomes next
        }
        
        // prev is now the new head (last node of original list)
        return prev;
    }
}

/**
 * Alternative: Recursive approach
 * 
 * Time: O(n), Space: O(n) for recursion stack
 */
class SolutionRecursive {
    public ListNode reverseList(ListNode head) {
        // Base case: empty or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursively reverse the rest of the list
        ListNode newHead = reverseList(head.next);
        
        // Reverse current node's link
        // head.next is now the last node of reversed rest
        // Point it back to head
        head.next.next = head;
        head.next = null; // Break original link
        
        return newHead; // Return new head of reversed list
    }
}
