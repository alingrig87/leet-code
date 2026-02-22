import java.util.*;

/**
 * LeetCode 252: Meeting Rooms
 * 
 * Problem: Check if person can attend all meetings.
 * 
 * Solution Approach: Sort by start time, check overlaps
 * Time Complexity: O(n log n)
 * Space Complexity: O(1)
 */
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // Check for overlaps
        for (int i = 1; i < intervals.length; i++) {
            // If current meeting starts before previous ends, overlap exists
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        
        return true;
    }
}
