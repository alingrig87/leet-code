# Kth Largest Element in a Stream

## Problem Statement
Design a class to find the `k`th largest element in a stream. Note that it is the `k`th largest element in the sorted order, not the `k`th distinct element.

Implement `KthLargest` class:
- `KthLargest(int k, int[] nums)` Initializes the object with the integer `k` and the stream of integers `nums`.
- `int add(int val)` Appends the integer `val` to the stream and returns the element representing the `k`th largest element in the stream.

**Example 1:**
```
Input
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
Output
[null, 4, 5, 5, 8, 8]
Explanation
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3);   // return 4
kthLargest.add(5);   // return 5
kthLargest.add(10);  // return 5
kthLargest.add(9);   // return 8
kthLargest.add(4);   // return 8
```

## Theory & Data Structures

### Min Heap (Priority Queue)
This problem uses a **min heap** of size `k` to efficiently track the `k` largest elements. The key insight is that the smallest element among the `k` largest elements is the `k`th largest element.

#### Key Insight: Min Heap for Kth Largest
- **Min heap of size k**: Maintains the k largest elements seen so far
- **Top of heap**: The smallest among k largest = kth largest element
- **When adding**: If heap size < k, add element. If element > heap top, replace top
- **Efficiency**: O(log k) per operation

#### Building Min Heap Solution from Scratch (Conceptual)
```java
// Conceptual implementation of KthLargest
class KthLargest {
    private int k;
    private PriorityQueue<Integer> minHeap;
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        // Min heap: smallest element at top
        this.minHeap = new PriorityQueue<>();
        
        // Add initial elements
        for (int num : nums) {
            add(num);
        }
    }
    
    public int add(int val) {
        // If heap size < k, add element
        if (minHeap.size() < k) {
            minHeap.offer(val);
        } 
        // If element > heap top, replace top
        else if (val > minHeap.peek()) {
            minHeap.poll();  // Remove smallest
            minHeap.offer(val);  // Add new element
        }
        // Otherwise, ignore (element is too small)
        
        // Top of min heap is kth largest
        return minHeap.peek();
    }
    
    // Why min heap?
    // - We want kth largest, which is the smallest among k largest
    // - Min heap keeps smallest at top
    // - When size = k, top is kth largest
    
    // Alternative: Max heap (less efficient)
    // Would need to maintain all elements, O(n) space
}
```

### Time & Space Complexity

#### Approach: Min Heap
- **Time Complexity**: O(n log k) - n insertions into heap of size k
  - Constructor: O(n log k) - n elements, each O(log k)
  - add(): O(log k) - heap operations
- **Space Complexity**: O(k) - Heap stores k elements
  - Optimal space usage

## Interview Simulation

### Initial Discussion

**Interviewer**: "Design a class to find the kth largest element in a stream."

**Candidate**: "I'll use a min heap of size k. The min heap maintains the k largest elements seen so far, with the smallest of those at the top, which is the kth largest. When adding a new element, if the heap size is less than k, I add it. Otherwise, if the new element is larger than the heap top, I replace the top. Otherwise, I ignore it."

**Interviewer**: "Why min heap instead of max heap?"

**Candidate**: "A min heap of size k keeps the k largest elements, with the smallest at the top. That smallest element among the k largest is exactly the kth largest. With a max heap, we'd need to maintain all elements, which is O(n) space instead of O(k)."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For k=3, initial [4,5,8,2]. After adding all: heap=[4,5,8], top=4 (3rd largest). Add 3: 3<4, ignore, return 4. Add 5: 5>4, replace 4 with 5, heap=[5,5,8], top=5, return 5. Add 10: 10>5, replace 5 with 10, heap=[5,8,10], top=5, return 5."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n log k) for the constructor where n is the number of initial elements, and O(log k) for each add operation. Space complexity is O(k) for the heap."

### Follow-up Questions

**Interviewer**: "What if k is very large, close to n?"

**Candidate**: "Then heap operations become more expensive, but still O(log k). If k is very large, we might consider using a max heap and maintaining only the n-k+1 smallest elements, but the min heap approach is generally better."

**Interviewer**: "What if we need the kth smallest instead?"

**Candidate**: "Then we'd use a max heap of size k, keeping the k smallest elements with the largest at the top."

### Tricky Edge Cases

1. **k = 1**: Return maximum element (heap of size 1)
2. **Stream smaller than k**: Handle initial state correctly
3. **All same elements**: Handle correctly
4. **Decreasing stream**: Maintain heap correctly
5. **Increasing stream**: Replace top repeatedly

## Solution Approaches

### Approach: Min Heap (Optimal)
Maintain min heap of size k. O(n log k) time, O(k) space.

**Algorithm:**
1. Initialize min heap
2. For each element:
   - If heap size < k: add element
   - Else if element > heap top: replace top
   - Return heap top

**Advantages:**
- O(k) space complexity
- O(log k) per operation
- Optimal solution

## Key Takeaways

1. **Min heap** for kth largest
2. **Maintain size k** in heap
3. **Top is kth largest**
4. **Efficient for streams** - O(log k) per add
5. **O(k) space** - optimal
6. **Foundation for** heap-based problems
