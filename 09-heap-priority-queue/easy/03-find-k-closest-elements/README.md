# Find K Closest Elements

## Problem Statement
Given a sorted integer array `arr`, two integers `k` and `x`, return the `k` closest integers to `x` in the array. The result should also be sorted in ascending order.

An integer `a` is closer to `x` than an integer `b` if:
- `|a - x| < |b - x|`, or
- `|a - x| == |b - x|` and `a < b`

**Example 1:**
```
Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Explanation: The 4 closest elements to 3 are [1,2,3,4]. Note that [2,3,4,5] is also valid but [1,2,3,4] is smaller.
```

**Example 2:**
```
Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]
```

## Theory & Data Structures

### Binary Search + Two Pointers
This problem uses **binary search** to find the position of `x` (or the closest position), then uses **two pointers** to expand and collect the `k` closest elements.

#### Key Insight: Expand from Closest Position
- **Binary search**: Find insertion position of x
- **Two pointers**: Start from closest position, expand left and right
- **Compare distances**: Move pointer with larger distance
- **Tie-breaking**: If distances equal, prefer smaller value (move left pointer)

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of k closest elements
class KClosestElements {
    
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Binary search to find position of x
        int left = 0;
        int right = arr.length - k;  // Start position for window
        
        // Binary search for optimal starting position
        while (left < right) {
            int mid = left + (right - left) / 2;
            // If x is closer to arr[mid+k], move left
            // Otherwise, move right
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        // Collect k elements starting from left
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }
        
        return result;
    }
    
    // Alternative: Two pointers approach
    public List<Integer> findClosestElementsTwoPointers(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;
        
        // Shrink window until size is k
        while (right - left >= k) {
            // Compare distances, move pointer with larger distance
            if (Math.abs(arr[left] - x) > Math.abs(arr[right] - x)) {
                left++;
            } else {
                right--;
            }
        }
        
        // Collect elements from left to right
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }
        
        return result;
    }
    
    // Why binary search works?
    // - We're finding the optimal starting position for a window of size k
    // - The optimal position minimizes the distance from x
    // - Binary search finds this position efficiently
}
```

### Time & Space Complexity

#### Approach: Binary Search + Window
- **Time Complexity**: O(log n + k) - Binary search + collecting k elements
  - Binary search: O(log n)
  - Collecting: O(k)
  - Total: O(log n + k)
- **Space Complexity**: O(1) - Excluding result
  - Only pointers
  - Result: O(k) but this is output

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find k closest elements to x in sorted array."

**Candidate**: "I'll use binary search to find the optimal starting position for a window of size k. The optimal position is where the window minimizes the total distance from x. Then I'll collect k elements from that position."

**Interviewer**: "How do you determine the optimal position?"

**Candidate**: "I compare the distance from x to the left end of the window versus the right end. If x is closer to the right end, I move the window left. Otherwise, I move it right. This binary search finds the optimal starting position."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For arr=[1,2,3,4,5], k=4, x=3. Binary search finds starting position. Compare distances: at position 0, window is [1,2,3,4]. Distance from 3 to 1 is 2, to 4 is 1. Since 1>2, we'd move, but actually we check if x-arr[mid] > arr[mid+k]-x. For mid=0: 3-1=2, 4-3=1, so 2>1, move left to 1. But wait, let me reconsider. Actually, the binary search finds where to start the window."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(log n + k) - O(log n) for binary search, O(k) for collecting elements. Space complexity is O(1) excluding the result."

### Follow-up Questions

**Interviewer**: "What if x is not in the array?"

**Candidate**: "Binary search finds the insertion position, which is the closest position. The algorithm still works correctly."

**Interviewer**: "What if k equals the array length?"

**Candidate**: "Then we return the entire array. The binary search would find position 0 as the starting position."

### Tricky Edge Cases

1. **x at start**: Take first k elements
2. **x at end**: Take last k elements
3. **x in middle**: Expand both sides
4. **k equals array length**: Return all
5. **x outside array range**: Handle correctly
6. **Equal distances**: Prefer smaller values

## Solution Approaches

### Approach: Binary Search + Window (Optimal)
Find optimal starting position, collect k elements. O(log n + k) time, O(1) space.

**Algorithm:**
1. Binary search for optimal starting position
2. Compare distances to window ends
3. Move window based on distances
4. Collect k elements from optimal position

**Advantages:**
- O(log n + k) time complexity
- Efficient binary search
- Optimal solution

## Key Takeaways

1. **Binary search** to find optimal window position
2. **Compare distances** to window ends
3. **O(log n + k)** efficient solution
4. **Two pointers** alternative also works
5. **Foundation for** sliding window problems
