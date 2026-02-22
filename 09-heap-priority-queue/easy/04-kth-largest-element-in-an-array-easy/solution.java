import java.util.*;

/**
 * LeetCode 215: Kth Largest Element in an Array
 * 
 * Problem: Find kth largest element.
 * 
 * Solution Approach: Min heap of size k
 * Time Complexity: O(n log k)
 * Space Complexity: O(k)
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        
        return minHeap.peek();
    }
}
