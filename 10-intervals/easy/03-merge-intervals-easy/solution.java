import java.util.*;

/**
 * LeetCode 56: Merge Intervals
 * 
 * Problem: Merge all overlapping intervals.
 * 
 * Solution Approach: Sort by start, merge overlapping
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        
        for (int i = 1; i < intervals.length; i++) {
            int[] last = merged.get(merged.size() - 1);
            int[] current = intervals[i];
            
            // If current overlaps with last, merge
            if (current[0] <= last[1]) {
                // Merge by updating end to maximum
                last[1] = Math.max(last[1], current[1]);
            } else {
                // No overlap, add as new interval
                merged.add(current);
            }
        }
        
        return merged.toArray(new int[merged.size()][]);
    }
}
