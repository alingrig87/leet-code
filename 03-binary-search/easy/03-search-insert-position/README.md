# Search Insert Position

## Problem Statement
Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with `O(log n)` runtime complexity.

**Example 1:**
```
Input: nums = [1,3,5,6], target = 5
Output: 2
```

**Example 2:**
```
Input: nums = [1,3,5,6], target = 2
Output: 1
```

**Example 3:**
```
Input: nums = [1,3,5,6], target = 7
Output: 4
```

**Example 4:**
```
Input: nums = [1,3,5,6], target = 0
Output: 0
```

## Theory & Data Structures

### Binary Search for Insertion Position
This problem combines binary search with finding the insertion position. The key insight is that when the target is not found, the `left` pointer naturally points to the insertion position.

#### Key Insight: Left Pointer is Insertion Position
- **When target is found**: Return the index directly
- **When target is not found**: The `left` pointer ends up at the first position where the element is >= target
- **This is exactly the insertion position**: Where we would insert target to maintain sorted order

#### Building Binary Search for Insertion Position from Scratch (Conceptual)
```java
// Conceptual implementation of binary search for insertion position
class InsertPositionFinder {
    private int[] nums;
    
    InsertPositionFinder(int[] nums) {
        this.nums = nums;
    }
    
    // Find insertion position using binary search
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        // Standard binary search loop
        while (left <= right) {
            // Calculate middle (avoid overflow)
            int mid = left + (right - left) / 2;
            
            // If target found, return index
            if (nums[mid] == target) {
                return mid;
            }
            
            // If target is smaller, search left half
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                // If target is larger, search right half
                left = mid + 1;
            }
        }
        
        // When loop ends, target not found
        // Left pointer points to insertion position
        // This is the first position where element >= target
        return left;
    }
    
    // Why does left point to insertion position?
    // - When target < nums[mid]: right = mid - 1
    //   Left stays at position where we would insert
    // - When target > nums[mid]: left = mid + 1
    //   Left moves to position after mid
    // - When loop ends: left is first position where element >= target
    
    // Alternative: More explicit version
    public int searchInsertExplicit(int[] nums, int target) {
        int left = 0;
        int right = nums.length;  // Note: right = length, not length - 1
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;  // Keep mid in search space
            }
        }
        
        return left;
    }
}
```

### Why Left Pointer Works
When binary search ends without finding the target:
1. **Target < all elements**: left = 0 (insert at beginning)
2. **Target > all elements**: left = n (insert at end)
3. **Target between elements**: left points to first element >= target

### Time & Space Complexity

#### Approach: Binary Search
- **Time Complexity**: O(log n) - Binary search halves search space each iteration
  - Best case: O(1) - Target at middle
  - Average case: O(log n)
  - Worst case: O(log n) - Target not found
- **Space Complexity**: O(1) - Only using variables (left, right, mid)
  - Iterative approach uses O(1) space
  - Recursive approach would use O(log n) space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given a sorted array and a target, find the insertion position if target is not found, or return the index if found."

**Candidate**: "I'll use binary search. If the target is found, I'll return its index. If not found, when the binary search loop ends, the left pointer will point to the insertion position - the first position where the element is greater than or equal to the target."

**Interviewer**: "Why does the left pointer point to the insertion position?"

**Candidate**: "During binary search, when target is smaller than nums[mid], we set right = mid - 1, and left stays at a position where we would insert. When target is larger, we set left = mid + 1, moving left to the position after mid. When the loop ends, left is at the first position where the element is >= target, which is exactly where we'd insert to maintain sorted order."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For nums = [1,3,5,6], target = 2. Start: left=0, right=3. Mid=1, nums[1]=3. Since 2 < 3, right=0. Now left=0, right=0. Mid=0, nums[0]=1. Since 2 > 1, left=1. Loop ends (left > right). Left=1 is the insertion position, which is correct - we'd insert 2 at index 1."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(log n) since we use binary search. Space complexity is O(1) since we only use a few variables."

### Follow-up Questions

**Interviewer**: "What if the array has duplicates?"

**Candidate**: "If we want the first occurrence when target is found, we'd modify the algorithm to continue searching left when we find a match. For insertion position, if target equals an element, we'd typically insert before the first occurrence, so we'd need to adjust the logic slightly."

**Interviewer**: "What if we need to insert after duplicates?"

**Candidate**: "Then we'd want to find the last occurrence of target, or the first position after all duplicates. We'd modify the binary search to continue searching right when we find a match, or adjust the insertion logic."

**Interviewer**: "Can you handle empty arrays?"

**Candidate**: "Yes, if the array is empty, left would be 0, which is correct - we'd insert at the beginning. The algorithm handles this naturally."

**Interviewer**: "What if target is very large or very small?"

**Candidate**: "If target is smaller than all elements, left ends up at 0 (correct). If target is larger than all elements, left ends up at nums.length (correct). The algorithm handles these edge cases naturally."

**Interviewer**: "Can you optimize this further?"

**Candidate**: "The binary search approach is already optimal - O(log n) time and O(1) space. We could add an early exit if target is found, but that's already in the algorithm. The solution is already efficient."

### Tricky Edge Cases

1. **Target at start**: `nums=[1,3,5], target=1` → Return `0` (found)
2. **Target at end**: `nums=[1,3,5], target=5` → Return `2` (found)
3. **Target smaller than all**: `nums=[1,3,5], target=0` → Return `0` (insert at start)
4. **Target larger than all**: `nums=[1,3,5], target=7` → Return `3` (insert at end)
5. **Target in middle, not found**: `nums=[1,3,5], target=2` → Return `1` (insert between 1 and 3)
6. **Single element, found**: `nums=[5], target=5` → Return `0`
7. **Single element, not found (smaller)**: `nums=[5], target=3` → Return `0`
8. **Single element, not found (larger)**: `nums=[5], target=7` → Return `1`
9. **Empty array**: `nums=[], target=5` → Return `0` (insert at start)
10. **Two elements**: `nums=[1,3], target=2` → Return `1`
11. **Duplicates (if allowed)**: Depends on requirements - insert before or after
12. **Negative numbers**: Works correctly with any integers

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll initialize left to 0 and right to the last index. I'll use a standard binary search loop with condition `left <= right`. Inside the loop, I'll calculate mid. If nums[mid] equals target, I'll return mid immediately. If target is smaller than nums[mid], I'll search the left half by setting right = mid - 1. If target is larger, I'll search the right half by setting left = mid + 1. When the loop ends, if we haven't returned, the target wasn't found. In this case, left points to the insertion position, so I'll return left."

**Interviewer**: "Why does left point to the insertion position when target is not found?"

**Candidate**: "During the binary search, when we move left (target > nums[mid]), we're moving to positions where elements are larger. When we move right (target < nums[mid]), left stays at a position where we would insert. When the loop ends, left is at the first position where the element is >= target, which is exactly where we'd insert to maintain sorted order."

**Interviewer**: "What if we use `left < right` instead of `left <= right`?"

**Candidate**: "We could use `left < right` with a slightly different approach. We'd set right = nums.length initially, and when target < nums[mid], we'd set right = mid (keeping mid in search space). This also works, but the `left <= right` approach is more standard and easier to understand."

## Solution Approaches

### Approach 1: Binary Search (Optimal)
Use binary search, return left when not found. O(log n) time, O(1) space.

**Algorithm:**
1. Initialize left = 0, right = nums.length - 1
2. While left <= right:
   - Calculate mid = left + (right - left) / 2
   - If nums[mid] == target: return mid
   - If target < nums[mid]: right = mid - 1
   - Else: left = mid + 1
3. Return left (insertion position)

**Advantages:**
- O(log n) time complexity
- O(1) space complexity
- Simple and intuitive
- Optimal solution

### Approach 2: Linear Search (Not Recommended)
Check each element until we find target or position to insert. O(n) time, O(1) space.

**Disadvantages:**
- O(n) time (much slower)
- Doesn't meet O(log n) requirement
- Not optimal

### Approach 3: Using Arrays.binarySearch() (Java)
Use Java's built-in binary search. O(log n) time, O(1) space.

**Advantages:**
- No need to implement
- Well-tested

**Disadvantages:**
- Returns negative insertion point if not found (need to handle)
- Less educational value

## Key Takeaways

1. **Binary search** for finding insertion position in sorted array
2. **Left pointer** naturally points to insertion position when target not found
3. **O(log n) solution** - optimal for sorted arrays
4. **Handle boundaries** correctly - left <= right pattern
5. **Early exit** when target found - return immediately
6. **Edge cases matter** - target at boundaries, empty array
7. **Overflow prevention** - use `left + (right - left) / 2`
8. **Iterative preferred** - O(1) space vs O(log n) for recursive
9. **Insertion position** is first position where element >= target
10. **Works naturally** - no special handling needed for insertion position
