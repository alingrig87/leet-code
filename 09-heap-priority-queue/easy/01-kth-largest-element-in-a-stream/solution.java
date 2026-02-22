import java.util.*;

/**
 * LeetCode 703: Kth Largest Element in a Stream
 * 
 * Problem: Design class to find kth largest in stream.
 * 
 * Solution Approach: Min heap of size k
 * Time Complexity: O(n log k) for n additions
 * Space Complexity: O(k)
 */
class KthLargest {
    private PriorityQueue<Integer> minHeap;
    private int k;
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        
        // Add initial elements
        for (int num : nums) {
            add(num);
        }
    }
    
    public int add(int val) {
        // If heap has less than k elements, add it
        if (minHeap.size() < k) {
            minHeap.offer(val);
        } 
        // If new value is larger than smallest in heap, replace it
        else if (val > minHeap.peek()) {
            minHeap.poll(); // Remove smallest
            minHeap.offer(val); // Add new value
        }
        // If heap size is k, return kth largest (top of min heap)
        return minHeap.peek();
    }
}
