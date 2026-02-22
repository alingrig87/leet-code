import java.util.*;

/**
 * LeetCode 435: Non-overlapping Intervals
 * 
 * Problem: Given an array of intervals, return the minimum number of intervals
 * you need to remove to make the rest of the intervals non-overlapping.
 * 
 * Solution Approach: Greedy algorithm with end-time sorting
 * Time Complexity: O(n log n) - sorting dominates
 * Space Complexity: O(1) - only using variables, excluding input/output
 */
class Solution {
    
    /**
     * Main solution using greedy approach
     * 
     * Key insight: This is an interval scheduling problem. The optimal greedy strategy
     * is to always keep the interval that ends earliest, as it leaves the most room
     * for subsequent intervals. This is known as the "earliest finish time" algorithm.
     * 
     * Algorithm:
     * 1. Sort intervals by end time (ascending)
     * 2. Keep track of the end time of the last kept interval
     * 3. For each interval:
     *    - If it doesn't overlap with last kept (start >= last end): keep it
     *    - If it overlaps (start < last end): remove it (we prefer the earlier ending one)
     * 
     * Why this works: By always keeping the interval that ends earliest, we maximize
     * the remaining time available for other intervals. This is a classic greedy
     * algorithm proof - any optimal solution can be transformed to use this strategy.
     * 
     * @param intervals Array of intervals [start, end]
     * @return Minimum number of intervals to remove
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        // Edge case: empty or single interval
        if (intervals == null || intervals.length <= 1) {
            return 0; // No removals needed
        }
        
        // Step 1: Sort intervals by end time (ascending)
        // This is crucial - we want to process intervals in order of when they finish
        // Comparator: (a, b) -> a[1] - b[1] compares end times
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        // Step 2: Greedy selection
        // Track the end time of the last interval we decided to keep
        // Initialize with first interval's end time (we always keep the first one)
        int lastEnd = intervals[0][1];
        
        // Count of intervals we need to remove
        int removeCount = 0;
        
        // Process remaining intervals
        for (int i = 1; i < intervals.length; i++) {
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];
            
            // Check if current interval overlaps with last kept interval
            // Overlap condition: currentStart < lastEnd
            // If current starts before last ends, they overlap
            if (currentStart < lastEnd) {
                // Overlap detected - we need to remove one of them
                // Greedy choice: remove current interval (keep the one that ends earlier)
                // This is optimal because the earlier-ending interval leaves more room
                removeCount++;
                // Don't update lastEnd - we're keeping the previous interval
            } else {
                // No overlap - we can keep this interval
                // Update lastEnd to current interval's end time
                lastEnd = currentEnd;
            }
        }
        
        return removeCount;
    }
}

/**
 * Alternative Solution: More explicit version
 * 
 * This version makes the greedy choice more explicit by tracking which intervals we keep.
 */
class SolutionExplicit {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return 0;
        }
        
        // Sort by end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        // List to track kept intervals (for clarity, though we only need count)
        List<int[]> kept = new ArrayList<>();
        kept.add(intervals[0]);
        
        for (int i = 1; i < intervals.length; i++) {
            int[] lastKept = kept.get(kept.size() - 1);
            int[] current = intervals[i];
            
            // If no overlap, keep current interval
            if (current[0] >= lastKept[1]) {
                kept.add(current);
            }
            // If overlap, we remove current (greedy: keep earlier ending)
        }
        
        // Number to remove = total - kept
        return intervals.length - kept.size();
    }
}

/**
 * Alternative: Sort by start time (less optimal)
 * 
 * This approach sorts by start time and uses different logic, but it's more complex
 * and less intuitive. The end-time sorting is the standard approach.
 */
class SolutionStartTime {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return 0;
        }
        
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        int removeCount = 0;
        int lastEnd = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < lastEnd) {
                // Overlap - remove the one that ends later
                removeCount++;
                lastEnd = Math.min(lastEnd, intervals[i][1]);
            } else {
                lastEnd = intervals[i][1];
            }
        }
        
        return removeCount;
    }
}

/**
 * Follow-up: What if we need to return which intervals to remove?
 * 
 * We can track the indices of intervals to remove.
 */
class SolutionReturnRemoved {
    public List<int[]> eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return new ArrayList<>();
        }
        
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        List<int[]> kept = new ArrayList<>();
        List<int[]> removed = new ArrayList<>();
        
        kept.add(intervals[0]);
        int lastEnd = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < lastEnd) {
                removed.add(intervals[i]);
            } else {
                kept.add(intervals[i]);
                lastEnd = intervals[i][1];
            }
        }
        
        return removed;
    }
}

/**
 * Follow-up: What if intervals have weights and we want to maximize total weight?
 * 
 * This becomes a weighted interval scheduling problem, which requires dynamic programming.
 * Time: O(n²) or O(n log n) with binary search optimization.
 */
class SolutionWeighted {
    public int maxWeight(int[][] intervals, int[] weights) {
        // This requires DP approach - different problem
        // Sort by end time
        // For each interval, find last non-overlapping interval
        // DP[i] = max(DP[i-1], weight[i] + DP[lastNonOverlapping])
        return 0; // Placeholder
    }
}
