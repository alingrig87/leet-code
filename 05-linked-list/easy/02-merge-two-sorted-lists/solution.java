/**
 * LeetCode 21: Merge Two Sorted Lists
 * 
 * Problem: Merge two sorted linked lists.
 * 
 * Solution Approach: Two pointers with dummy node
 * Time Complexity: O(n + m)
 * Space Complexity: O(1)
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Edge case: one or both lists empty
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        // Dummy node to simplify code
        // We'll build result starting from dummy.next
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Merge while both lists have nodes
        while (list1 != null && list2 != null) {
            // Compare values, link smaller node
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next; // Move result pointer
        }
        
        // Append remaining nodes from non-empty list
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        
        return dummy.next; // Return merged list
    }
}
