# First Bad Version

## Problem Statement
You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all versions after a bad version are also bad.

Suppose you have `n` versions `[1, 2, ..., n]` and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API `bool isBadVersion(version)` which returns whether `version` is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

**Example 1:**
```
Input: n = 5, bad = 4
Output: 4
Explanation: call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true
Then 4 is the first bad version.
```

**Example 2:**
```
Input: n = 1, bad = 1
Output: 1
```

## Theory & Data Structures

### Binary Search for First Occurrence
This problem is a variation of binary search where we need to find the **first** occurrence of a condition (first bad version). The key difference from standard binary search is in how we handle the boundaries.

#### Key Insight: Sorted Property
- **All versions before first bad**: Good (isBadVersion returns false)
- **First bad version and all after**: Bad (isBadVersion returns true)
- **This creates a sorted property**: [good, good, ..., good, bad, bad, ..., bad]
- **We need to find the transition point**: The first bad version

#### Building Binary Search for First Occurrence from Scratch (Conceptual)
```java
// Conceptual implementation of binary search for first occurrence
class FirstBadVersionFinder {
    private int n;
    
    FirstBadVersionFinder(int n) {
        this.n = n;
    }
    
    // API method (provided by problem)
    private boolean isBadVersion(int version) {
        // Implementation provided by problem
        return false; // Placeholder
    }
    
    // Find first bad version using binary search
    public int firstBadVersion(int n) {
        // Left boundary: start at 1 (first version)
        int left = 1;
        // Right boundary: end at n (last version)
        int right = n;
        
        // Use left < right instead of left <= right
        // This ensures we find the FIRST occurrence
        while (left < right) {
            // Calculate middle (avoid overflow)
            int mid = left + (right - left) / 2;
            
            // Check if mid version is bad
            if (isBadVersion(mid)) {
                // Mid is bad, so first bad is at mid or to the left
                // Set right = mid (not mid - 1) to keep mid in search space
                right = mid;
            } else {
                // Mid is good, so first bad is to the right
                // Set left = mid + 1 (mid is definitely not bad)
                left = mid + 1;
            }
        }
        
        // When left == right, we've found the first bad version
        return left;
    }
    
    // Why left < right instead of left <= right?
    // - With left <= right: We might skip the first occurrence
    // - With left < right: We ensure we find the FIRST occurrence
    // - When left == right, we've narrowed down to one version
    
    // Why right = mid instead of right = mid - 1?
    // - Because mid might be the first bad version
    // - We want to keep mid in the search space
    // - Setting right = mid - 1 might skip the first bad version
}
```

### Boundary Handling: Left < Right Pattern
This is a crucial pattern for finding the **first** occurrence:
- **Use `left < right`** instead of `left <= right`
- **When `isBadVersion(mid)` is true**: Set `right = mid` (keep mid in search space)
- **When `isBadVersion(mid)` is false**: Set `left = mid + 1` (mid is definitely not bad)
- **Return `left`** when loop ends (left == right)

### Time & Space Complexity

#### Approach: Binary Search
- **Time Complexity**: O(log n) - Binary search halves search space each iteration
  - Best case: O(log n) - First bad version is in the middle
  - Average case: O(log n)
  - Worst case: O(log n) - First bad version is at an end
- **Space Complexity**: O(1) - Only using variables (left, right, mid)
  - Iterative approach uses O(1) space
  - Recursive approach would use O(log n) space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find the first bad version with minimal API calls."

**Candidate**: "Since all versions after the first bad are also bad, this creates a sorted property: [good, good, ..., good, bad, bad, ..., bad]. I'll use binary search to find the transition point. If mid is bad, the first bad is at mid or to the left. If mid is good, the first bad is to the right."

**Interviewer**: "How do you handle the boundary to find the FIRST bad version?"

**Candidate**: "I'll use `left < right` instead of `left <= right`. When mid is bad, I'll set `right = mid` (not `mid - 1`) to keep mid in the search space, since mid might be the first bad. When mid is good, I'll set `left = mid + 1` since mid is definitely not bad. When the loop ends, left equals right and points to the first bad version."

**Interviewer**: "Why `right = mid` instead of `right = mid - 1`?"

**Candidate**: "Because mid might be the first bad version. If we set right = mid - 1, we might skip the first bad version. By setting right = mid, we keep mid in the search space and ensure we find the first occurrence."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(log n) since we halve the search space each iteration. Space complexity is O(1) since we only use a few variables."

### Follow-up Questions

**Interviewer**: "What if we need to find the last good version instead?"

**Candidate**: "Similar approach, but we'd adjust the conditions. If mid is good, last good is at mid or to the right. If mid is bad, last good is to the left. We'd use a similar boundary pattern but with adjusted logic."

**Interviewer**: "Can you minimize API calls further?"

**Candidate**: "The binary search approach already minimizes API calls to O(log n), which is optimal. We can't do better than this without additional information about the distribution of bad versions."

**Interviewer**: "What if the API call is expensive?"

**Candidate**: "The binary search approach is still optimal - O(log n) calls is the best we can do. If API calls are expensive, we might want to cache results, but that doesn't change the asymptotic complexity."

**Interviewer**: "What if we need to handle concurrent API calls?"

**Candidate**: "We'd need to add synchronization mechanisms, but the core binary search algorithm remains the same. The O(log n) complexity still holds."

**Interviewer**: "Can you solve this recursively?"

**Candidate**: "Yes, but the iterative approach is preferred because it uses O(1) space versus O(log n) space for the recursion stack. The iterative version is also more efficient and easier to understand."

### Tricky Edge Cases

1. **First version is bad**: `n=5, bad=1` → Return `1` (all versions are bad)
2. **Last version is bad**: `n=5, bad=5` → Return `5` (only last is bad)
3. **All versions bad**: `n=5, bad=1` → Return `1` (all are bad)
4. **Single version**: `n=1, bad=1` → Return `1`
5. **Middle version bad**: `n=5, bad=3` → Return `3`
6. **Second version bad**: `n=5, bad=2` → Return `2`
7. **Large n**: Works with any n value (watch for integer overflow in mid calculation)
8. **Very large n**: Use `left + (right - left) / 2` to avoid overflow

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll initialize left to 1 (first version) and right to n (last version). I'll use a while loop with condition `left < right` (not `<=`). Inside the loop, I'll calculate mid using the overflow-safe formula. I'll check if mid is bad. If it is, I'll set right = mid (keeping mid in search space since it might be the first bad). If it's not bad, I'll set left = mid + 1 (mid is definitely not bad). When the loop ends, left equals right and points to the first bad version, so I'll return left."

**Interviewer**: "Why is the `left < right` pattern crucial here?"

**Candidate**: "The `left < right` pattern ensures we find the FIRST occurrence. With `left <= right`, we might skip the first bad version. The pattern works by narrowing the search space until left and right converge on the first bad version. When they're equal, we've found it."

**Interviewer**: "What happens if we use `right = mid - 1` when mid is bad?"

**Candidate**: "We might skip the first bad version. For example, if versions 4 and 5 are both bad, and we're checking version 4 (mid), if we set right = mid - 1, we'd skip version 4 and might return version 5, which is incorrect. By using right = mid, we keep version 4 in the search space."

## Solution Approaches

### Approach 1: Binary Search with Left < Right Pattern (Optimal)
Use binary search with careful boundary handling. O(log n) time, O(1) space.

**Algorithm:**
1. Initialize left = 1, right = n
2. While left < right:
   - Calculate mid = left + (right - left) / 2
   - If isBadVersion(mid): right = mid (keep mid in search space)
   - Else: left = mid + 1 (mid is definitely not bad)
3. Return left (first bad version)

**Advantages:**
- O(log n) time complexity
- O(1) space complexity
- Minimizes API calls
- Optimal solution

### Approach 2: Linear Search (Not Recommended)
Check each version from 1 to n. O(n) time, O(1) space.

**Disadvantages:**
- O(n) time (much slower)
- O(n) API calls (not optimal)
- Doesn't minimize API calls

### Approach 3: Recursive Binary Search
Use recursion instead of iteration. O(log n) time, O(log n) space.

**Disadvantages:**
- O(log n) space for recursion stack
- Less efficient than iterative
- Potential stack overflow for large n

## Key Takeaways

1. **Binary search** for finding first occurrence in sorted sequence
2. **Left < right pattern** is crucial for finding FIRST occurrence
3. **Right = mid** (not mid - 1) when condition is true - keeps mid in search space
4. **Minimize API calls** with binary search - O(log n) calls
5. **Boundary handling** is critical - different from standard binary search
6. **Overflow prevention** - use `left + (right - left) / 2`
7. **Iterative preferred** - O(1) space vs O(log n) for recursive
8. **Edge cases matter** - first version bad, last version bad, all bad
9. **Sorted property** - all bad versions come after first bad
10. **Return left** when loop ends - left == right == first bad version
