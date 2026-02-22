import java.util.*;

/**
 * LeetCode 1046: Last Stone Weight
 * 
 * Problem: Simulate stone smashing game.
 * 
 * Solution Approach: Max heap
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */
class Solution {
    public int lastStoneWeight(int[] stones) {
        // Max heap (use negative values for min heap to simulate max heap)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        
        // Add all stones to heap
        for (int stone : stones) {
            maxHeap.offer(stone);
        }
        
        // Smash stones until one or zero remain
        while (maxHeap.size() > 1) {
            int first = maxHeap.poll(); // Heaviest
            int second = maxHeap.poll(); // Second heaviest
            
            int difference = first - second;
            
            // If stones don't completely destroy, add difference back
            if (difference > 0) {
                maxHeap.offer(difference);
            }
        }
        
        // Return last stone weight or 0
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
}
