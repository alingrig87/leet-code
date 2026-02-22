# Linked List Cycle

## Problem Statement
Given `head`, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer.

Return `true` if there is a cycle in the linked list. Otherwise, return `false`.

**Example 1:**
```
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
```

**Example 2:**
```
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
```

**Example 3:**
```
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.
```

## Theory & Data Structures

### Floyd's Cycle Detection Algorithm (Tortoise and Hare)
This problem uses **Floyd's cycle detection algorithm**, also known as the "tortoise and hare" algorithm. It uses two pointers moving at different speeds.

#### Key Insight: Two Pointers with Different Speeds
- **Slow pointer (tortoise)**: Moves one step at a time
- **Fast pointer (hare)**: Moves two steps at a time
- **If cycle exists**: Fast pointer will eventually catch up to slow pointer
- **If no cycle**: Fast pointer will reach null

#### Why This Works: Mathematical Proof
1. **No cycle**: Fast pointer reaches null → return false
2. **Cycle exists**: 
   - Fast pointer enters cycle first
   - Slow pointer enters cycle later
   - Since fast moves twice as fast, it will eventually catch up
   - They will meet inside the cycle

#### Building Floyd's Algorithm from Scratch (Conceptual)
```java
// Conceptual implementation of Floyd's cycle detection
class CycleDetector {
    
    // Detect cycle using Floyd's algorithm
    public boolean hasCycle(ListNode head) {
        // Edge case: empty list or single node without cycle
        if (head == null || head.next == null) {
            return false;
        }
        
        // Two pointers: slow and fast
        ListNode slow = head;   // Tortoise: moves 1 step
        ListNode fast = head;   // Hare: moves 2 steps
        
        // Move pointers until they meet or fast reaches null
        while (fast != null && fast.next != null) {
            // Move slow one step
            slow = slow.next;
            
            // Move fast two steps
            fast = fast.next.next;
            
            // If they meet, cycle exists
            if (slow == fast) {
                return true;
            }
        }
        
        // Fast reached null, no cycle
        return false;
    }
    
    // Why check fast.next != null?
    // Because we do fast.next.next, we need to ensure fast.next exists
    // Otherwise, we'd get NullPointerException
    
    // Finding the start of the cycle (follow-up)
    public ListNode detectCycleStart(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        // Step 1: Detect if cycle exists and find meeting point
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        
        if (!hasCycle) {
            return null;
        }
        
        // Step 2: Find cycle start
        // Reset one pointer to head, move both one step at a time
        // They will meet at cycle start
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;  // Cycle start
    }
}
```

### Why Two Different Speeds?
- **Same speed**: Both pointers would move together, never meeting in a cycle
- **Different speeds**: Creates relative motion, allowing fast to "lap" slow
- **Speed ratio 2:1**: Optimal - guarantees meeting if cycle exists, and in reasonable time

### Time & Space Complexity

#### Approach: Floyd's Algorithm
- **Time Complexity**: O(n) - At most n steps
  - Best case: O(1) - Cycle at head
  - Average case: O(n) - Cycle in middle
  - Worst case: O(n) - Cycle at end or no cycle
- **Space Complexity**: O(1) - Only using two pointers
  - Constant space regardless of list size
  - Optimal space complexity

#### Alternative: HashSet Approach
- **Time Complexity**: O(n) - Visit each node once
- **Space Complexity**: O(n) - Store all visited nodes
- **Not optimal** for space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Detect if a linked list has a cycle."

**Candidate**: "I'll use Floyd's cycle detection algorithm - also known as the tortoise and hare algorithm. I'll use two pointers: a slow pointer that moves one step at a time, and a fast pointer that moves two steps at a time. If there's a cycle, the fast pointer will eventually catch up to the slow pointer. If there's no cycle, the fast pointer will reach null."

**Interviewer**: "Why does this work?"

**Candidate**: "If a cycle exists, the fast pointer will enter the cycle first. Since it moves twice as fast, it will eventually catch up to the slow pointer inside the cycle. If there's no cycle, the fast pointer will reach the end (null) before they can meet."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For a list with cycle: 3->2->0->-4->2 (cycle back to node 2). Start: slow=3, fast=3. Step 1: slow=2, fast=0. Step 2: slow=0, fast=2. Step 3: slow=-4, fast=-4. They meet at -4, so cycle exists. For a list without cycle: 1->2->3->null. Start: slow=1, fast=1. Step 1: slow=2, fast=3. Step 2: slow=3, fast=null. Fast reaches null, no cycle."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since in the worst case we traverse the list once. Space complexity is O(1) since we only use two pointers."

### Follow-up Questions

**Interviewer**: "Can you find the start of the cycle?"

**Candidate**: "Yes. After detecting the cycle (when slow and fast meet), I'll reset one pointer to head and move both pointers one step at a time. They will meet at the start of the cycle. This works because of the mathematical relationship between the distances."

**Interviewer**: "What if we use a HashSet to store visited nodes?"

**Candidate**: "That would work - we visit each node and check if it's in the set. If we encounter a node we've seen, there's a cycle. However, this uses O(n) space, while Floyd's algorithm uses O(1) space."

**Interviewer**: "What if the cycle is very long?"

**Candidate**: "Floyd's algorithm still works. The fast pointer will eventually catch up to the slow pointer, though it might take more steps. The time complexity remains O(n)."

**Interviewer**: "Can you prove that the algorithm will always find the cycle if it exists?"

**Candidate**: "Yes. If a cycle exists, the fast pointer will enter it first. Since it moves twice as fast, the distance between them decreases by 1 each step. Eventually, the distance becomes 0, meaning they meet. This is guaranteed to happen within the cycle length."

**Interviewer**: "What if we use different speed ratios?"

**Candidate**: "A 2:1 ratio is optimal. Other ratios like 3:1 would also work but might take longer or require more complex logic. The 2:1 ratio is simple and efficient."

### Tricky Edge Cases

1. **No cycle**: `[1,2,3]` → Return `false`
2. **Single node, no cycle**: `[1]` → Return `false`
3. **Single node cycle**: `[1]` with cycle → Return `true`
4. **Cycle at head**: `[1,2,3]` cycle back to 1 → Return `true`
5. **Cycle in middle**: `[1,2,3,4]` cycle at 2 → Return `true`
6. **Cycle at tail**: `[1,2,3]` cycle at 3 → Return `true`
7. **Empty list**: `[]` → Return `false`
8. **Two nodes, no cycle**: `[1,2]` → Return `false`
9. **Two nodes, cycle**: `[1,2]` cycle → Return `true`
10. **Long list, no cycle**: Works correctly
11. **Long list, cycle**: Works correctly

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll handle the edge case first - if head is null or head.next is null, return false (no cycle possible). I'll initialize both slow and fast pointers to head. In a while loop, I'll check if fast and fast.next are not null (to avoid null pointer exceptions). Inside the loop, I'll move slow one step and fast two steps. If they meet (slow == fast), I'll return true. If the loop ends (fast reached null), I'll return false."

**Interviewer**: "Why check fast.next != null?"

**Candidate**: "Because we do fast.next.next, we need to ensure fast.next exists. Otherwise, we'd get a NullPointerException. Checking fast != null and fast.next != null ensures we can safely access fast.next.next."

**Interviewer**: "What if the list has only one node?"

**Candidate**: "If there's only one node and it points to itself, that's a cycle. But if it points to null, there's no cycle. The edge case check handles the null case. For a self-referential node, the algorithm would detect it in the first iteration."

## Solution Approaches

### Approach 1: Floyd's Cycle Detection (Optimal)
Use two pointers with different speeds. O(n) time, O(1) space.

**Algorithm:**
1. Handle edge cases (null, single node)
2. Initialize slow = head, fast = head
3. While fast != null and fast.next != null:
   - Move slow one step
   - Move fast two steps
   - If slow == fast: return true
4. Return false (no cycle)

**Advantages:**
- O(1) space complexity
- O(n) time complexity
- Simple and elegant
- Optimal solution

### Approach 2: HashSet
Store visited nodes in HashSet. O(n) time, O(n) space.

**Algorithm:**
1. Create HashSet to store visited nodes
2. Traverse list
3. For each node, check if in set
4. If yes, cycle exists
5. If no, add to set and continue

**Disadvantages:**
- O(n) space complexity
- Not optimal

### Approach 3: Marking Nodes
Mark visited nodes (modifies list). O(n) time, O(1) space.

**Disadvantages:**
- Modifies original list
- Not always acceptable
- Requires special node structure

## Key Takeaways

1. **Floyd's algorithm** (tortoise and hare) for cycle detection
2. **Two pointers** with different speeds (1:2 ratio)
3. **O(1) space** solution - optimal
4. **O(n) time** complexity
5. **Classic algorithm** to know
6. **Mathematical proof** guarantees correctness
7. **Can find cycle start** with extension
8. **Handle edge cases** - empty list, single node
9. **Check fast.next** to avoid null pointer exceptions
10. **Foundation for** many cycle-related problems
