/**
 * LeetCode 83: Remove Duplicates from Sorted List
 * 
 * Problem: Remove duplicates from sorted linked list.
 * 
 * Solution Approach: Single pointer traversal
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode current = head;
        
        // Traverse list
        while (current != null && current.next != null) {
            // If current value equals next value, skip next node
            if (current.val == current.next.val) {
                current.next = current.next.next; // Skip duplicate
            } else {
                current = current.next; // Move forward
            }
        }
        
        return head;
    }
}
