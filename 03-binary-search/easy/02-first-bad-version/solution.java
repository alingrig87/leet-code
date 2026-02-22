/**
 * LeetCode 278: First Bad Version
 * 
 * Problem: Find first bad version with minimal API calls.
 * 
 * Solution Approach: Binary search
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        
        // Use left < right (not <=) because we want to find first occurrence
        // When left == right, we've found the first bad version
        while (left < right) {
            // Calculate middle version
            // Use left + (right - left) / 2 to avoid overflow
            int mid = left + (right - left) / 2;
            
            if (isBadVersion(mid)) {
                // Mid is bad, so first bad version is at mid or to the left
                // Set right = mid (not mid - 1) because mid might be the first bad
                right = mid;
            } else {
                // Mid is good, so first bad version is to the right
                // Set left = mid + 1 because we know mid is good
                left = mid + 1;
            }
        }
        
        // When loop ends, left == right and points to first bad version
        return left;
    }
}
