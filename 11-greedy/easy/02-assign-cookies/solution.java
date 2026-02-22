import java.util.*;

/**
 * LeetCode 455: Assign Cookies
 * 
 * Problem: Maximize number of content children by assigning cookies.
 * Each child has a greed factor, each cookie has a size.
 * Child is content if cookie size >= greed factor.
 * 
 * Solution Approach: Greedy algorithm with sorting
 * Time Complexity: O(n log n + m log m) - sorting both arrays
 * Space Complexity: O(1) - only using pointers
 */
class Solution {
    
    /**
     * Main solution using greedy approach
     * 
     * Key insight: This is a greedy assignment problem. The optimal strategy is:
     * 1. Sort children by greed factor (ascending)
     * 2. Sort cookies by size (ascending)
     * 3. Match smallest cookie to smallest greed it can satisfy
     * 
     * Why this works: By using the smallest cookie that satisfies a child,
     * we leave larger cookies available for children with higher greed factors.
     * This greedy choice maximizes the number of satisfied children.
     * 
     * Algorithm:
     * - Sort both arrays
     * - Use two pointers: one for children, one for cookies
     * - For each cookie, try to satisfy the smallest unsatisfied child
     * - If cookie satisfies child, assign it and move both pointers
     * - Otherwise, cookie is too small, move to next cookie
     * 
     * @param g Array of greed factors for children
     * @param s Array of cookie sizes
     * @return Maximum number of content children
     */
    public int findContentChildren(int[] g, int[] s) {
        // Edge case: no children or no cookies
        if (g == null || s == null || g.length == 0 || s.length == 0) {
            return 0;
        }
        
        // Step 1: Sort children by greed factor (ascending)
        // This allows us to process children in order of increasing greed
        // We want to satisfy children with lower greed first when possible
        Arrays.sort(g);
        
        // Step 2: Sort cookies by size (ascending)
        // This allows us to process cookies in order of increasing size
        // We want to use smallest cookies first to leave larger ones for higher greed
        Arrays.sort(s);
        
        // Step 3: Greedy matching using two pointers
        int childIndex = 0; // Pointer for children array
        int cookieIndex = 0; // Pointer for cookies array
        int contentCount = 0; // Count of satisfied children
        
        // Process until we run out of children or cookies
        while (childIndex < g.length && cookieIndex < s.length) {
            // Check if current cookie can satisfy current child
            // Cookie size must be >= child's greed factor
            if (s[cookieIndex] >= g[childIndex]) {
                // Cookie satisfies child - assign it
                contentCount++;
                childIndex++; // Move to next child
                cookieIndex++; // Move to next cookie (cookie is used)
            } else {
                // Cookie is too small for current child
                // Try next cookie (maybe it's larger and can satisfy this or future child)
                cookieIndex++;
            }
        }
        
        return contentCount;
    }
}

/**
 * Alternative: More explicit version
 */
class SolutionExplicit {
    public int findContentChildren(int[] g, int[] s) {
        if (g == null || s == null || g.length == 0 || s.length == 0) {
            return 0;
        }
        
        Arrays.sort(g);
        Arrays.sort(s);
        
        int satisfied = 0;
        int i = 0; // child index
        int j = 0; // cookie index
        
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                // Assign cookie j to child i
                satisfied++;
                i++; // Child satisfied, move to next
            }
            j++; // Always move to next cookie
        }
        
        return satisfied;
    }
}

/**
 * Follow-up: What if we need to maximize total satisfaction (sum of cookie_size - greed_factor)?
 * 
 * Then we'd assign largest cookies to children with highest greed.
 */
class SolutionMaximizeSatisfaction {
    public int findContentChildren(int[] g, int[] s) {
        if (g == null || s == null || g.length == 0 || s.length == 0) {
            return 0;
        }
        
        // Sort in descending order for maximizing satisfaction
        Arrays.sort(g);
        Arrays.sort(s);
        
        // Reverse to get descending order
        reverse(g);
        reverse(s);
        
        int satisfied = 0;
        int i = 0, j = 0;
        
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                satisfied++;
                i++;
            }
            j++;
        }
        
        return satisfied;
    }
    
    private void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
