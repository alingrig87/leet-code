/**
 * LeetCode 141: Linked List Cycle
 * 
 * Problem: Detect if linked list has cycle.
 * 
 * Solution Approach: Floyd's cycle detection (tortoise and hare)
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        // Edge case: empty or single node
        if (head == null || head.next == null) {
            return false;
        }
        
        // Floyd's cycle detection: two pointers
        // slow moves 1 step, fast moves 2 steps
        ListNode slow = head;
        ListNode fast = head;
        
        // Move pointers until fast reaches end or they meet
        while (fast != null && fast.next != null) {
            slow = slow.next;        // Move slow one step
            fast = fast.next.next;   // Move fast two steps
            
            // If they meet, cycle exists
            if (slow == fast) {
                return true;
            }
        }
        
        // Fast reached end, no cycle
        return false;
    }
}

/**
 * Alternative: Using HashSet (O(n) space)
 * 
 * Store visited nodes, if we see a node twice, cycle exists.
 */
class SolutionHashSet {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        
        while (head != null) {
            if (visited.contains(head)) {
                return true; // Cycle found
            }
            visited.add(head);
            head = head.next;
        }
        
        return false; // No cycle
    }
}
