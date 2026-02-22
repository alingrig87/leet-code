# Binary Search

## Problem Statement
Given an array of integers `nums` which is sorted in ascending order, and an integer `target`, write a function to search `target` in `nums`. If `target` exists, then return its index. Otherwise, return `-1`.

You must write an algorithm with `O(log n)` runtime complexity.

**Example 1:**
```
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
```

**Example 2:**
```
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1
```

**Example 3:**
```
Input: nums = [5], target = 5
Output: 0
```

## Theory & Data Structures

### Binary Search Algorithm
**Binary Search** is a divide-and-conquer algorithm that efficiently searches for a target value in a sorted array by repeatedly dividing the search space in half.

#### How Binary Search Works Internally
1. **Initialization**: Set left boundary to start (0) and right boundary to end (length - 1)
2. **Middle Calculation**: Calculate middle index: `mid = left + (right - left) / 2`
3. **Comparison**: Compare target with element at middle index
4. **Decision**: 
   - If target equals middle element → Found! Return index
   - If target < middle element → Search left half (right = mid - 1)
   - If target > middle element → Search right half (left = mid + 1)
5. **Termination**: Continue until left > right (target not found) or target found

#### Why Binary Search is O(log n)
- Each iteration eliminates half of the remaining search space
- After 1 iteration: n/2 elements remain
- After 2 iterations: n/4 elements remain
- After k iterations: n/(2^k) elements remain
- When n/(2^k) = 1: k = log₂(n)
- Therefore, at most log₂(n) iterations needed

#### Building Binary Search from Scratch (Conceptual)
```java
// Conceptual implementation of binary search
class BinarySearcher {
    private int[] array;
    
    BinarySearcher(int[] array) {
        this.array = array;
    }
    
    // Search for target in sorted array
    public int search(int target) {
        // Initialize search boundaries
        int left = 0;                    // Left boundary (inclusive)
        int right = array.length - 1;    // Right boundary (inclusive)
        
        // Continue searching while search space is valid
        while (left <= right) {
            // Calculate middle index
            // Use left + (right - left) / 2 to avoid integer overflow
            // This is equivalent to (left + right) / 2 but safer
            int mid = left + (right - left) / 2;
            
            // Get element at middle index
            int midValue = array[mid];
            
            // Compare target with middle element
            if (target == midValue) {
                // Found target! Return its index
                return mid;
            } else if (target < midValue) {
                // Target is smaller, search left half
                // Since array is sorted ascending, target must be in [left, mid-1]
                right = mid - 1;
            } else {
                // Target is larger, search right half
                // Since array is sorted ascending, target must be in [mid+1, right]
                left = mid + 1;
            }
        }
        
        // Target not found in array
        return -1;
    }
    
    // Recursive version
    public int searchRecursive(int target, int left, int right) {
        // Base case: search space is invalid
        if (left > right) {
            return -1;
        }
        
        // Calculate middle
        int mid = left + (right - left) / 2;
        
        // Found target
        if (array[mid] == target) {
            return mid;
        }
        
        // Search left half
        if (target < array[mid]) {
            return searchRecursive(target, left, mid - 1);
        }
        
        // Search right half
        return searchRecursive(target, mid + 1, right);
    }
}
```

### Integer Overflow Prevention
When calculating the middle index, using `(left + right) / 2` can cause integer overflow if left and right are large. The safe formula is:
```java
int mid = left + (right - left) / 2;
```
This avoids overflow because `(right - left)` is always smaller than `(left + right)`.

### Time & Space Complexity

#### Approach: Iterative Binary Search
- **Time Complexity**: O(log n) - Each iteration eliminates half of the search space
  - Best case: O(1) - Target at middle of array
  - Average case: O(log n)
  - Worst case: O(log n) - Target not found or at boundaries
- **Space Complexity**: O(1) - Only using a few variables (left, right, mid)

#### Approach: Recursive Binary Search
- **Time Complexity**: O(log n) - Same as iterative
- **Space Complexity**: O(log n) - Recursion stack depth is log n

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given a sorted array and a target, find the target's index with O(log n) time complexity."

**Candidate**: "I'll use binary search. Since the array is sorted, I can repeatedly divide the search space in half. I'll compare the target with the middle element, and based on the comparison, eliminate half of the remaining elements."

**Interviewer**: "Can you walk me through your approach?"

**Candidate**: "Sure. I'll initialize two pointers: left at 0 and right at the last index. In a loop, I'll calculate the middle index using `left + (right - left) / 2` to avoid overflow. I'll compare the target with the element at the middle index. If they're equal, I return the middle index. If the target is smaller, I search the left half by setting right to mid - 1. If the target is larger, I search the right half by setting left to mid + 1. I continue until left exceeds right, at which point the target isn't in the array, so I return -1."

**Interviewer**: "Why use `left + (right - left) / 2` instead of `(left + right) / 2`?"

**Candidate**: "To prevent integer overflow. If left and right are both close to Integer.MAX_VALUE, adding them could overflow. The formula `left + (right - left) / 2` is mathematically equivalent but avoids this issue."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(log n) because we eliminate half of the search space in each iteration. Space complexity is O(1) for the iterative approach since we only use a few variables."

### Follow-up Questions

**Interviewer**: "What if the array has duplicates?"

**Candidate**: "Standard binary search will find one occurrence, but we can't guarantee which one. If we need the first occurrence, we modify the algorithm to continue searching left when we find a match. If we need the last occurrence, we continue searching right."

**Interviewer**: "Can you implement it recursively?"

**Candidate**: "Yes, but the iterative version is preferred because it uses O(1) space versus O(log n) space for the recursion stack. The recursive version would have the same time complexity but worse space complexity."

**Interviewer**: "What if the array isn't sorted?"

**Candidate**: "Binary search requires a sorted array. If it's not sorted, we'd need to sort it first (O(n log n)) or use linear search (O(n)). Binary search won't work correctly on an unsorted array."

**Interviewer**: "What if we need to find the insertion point for a target that doesn't exist?"

**Candidate**: "We can modify binary search to find the leftmost position where we could insert the target. When the loop ends, `left` will point to the insertion position. This is useful for problems like 'Search Insert Position'."

**Interviewer**: "Can binary search work on other data structures?"

**Candidate**: "Yes, binary search can work on any sorted sequence, not just arrays. It works on sorted lists, and the concept extends to binary search trees. The key requirement is that we can access the middle element and compare it with the target."

### Tricky Edge Cases

1. **Target not found**: `nums = [1,2,3], target = 4` → Return `-1`
2. **Target at first position**: `nums = [1,2,3], target = 1` → Return `0`
3. **Target at last position**: `nums = [1,2,3], target = 3` → Return `2`
4. **Single element array**: `nums = [5], target = 5` → Return `0`
5. **Single element, not found**: `nums = [5], target = 3` → Return `-1`
6. **Empty array**: `nums = [], target = 1` → Return `-1` (array length is 0, loop never executes)
7. **Target smaller than all**: `nums = [1,2,3], target = 0` → Return `-1`
8. **Target larger than all**: `nums = [1,2,3], target = 4` → Return `-1`
9. **Two elements**: `nums = [1,2], target = 1` → Return `0`
10. **Two elements, not found**: `nums = [1,2], target = 3` → Return `-1`
11. **Duplicates present**: `nums = [1,2,2,2,3], target = 2` → Returns one of the indices (2, 3, or 4)
12. **Negative numbers**: `nums = [-5,-2,0,3], target = -2` → Return `1`
13. **Large numbers**: `nums = [Integer.MAX_VALUE-2, Integer.MAX_VALUE-1, Integer.MAX_VALUE], target = Integer.MAX_VALUE` → Return `2` (overflow-safe calculation important)

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll start by checking if the array is empty - if so, return -1 immediately. Then I'll initialize left to 0 and right to the last index. I'll create a while loop that continues while left is less than or equal to right. Inside the loop, I'll calculate the middle index using the overflow-safe formula. I'll compare the target with the middle element. If they're equal, I return the middle index. If the target is smaller, I update right to mid - 1 to search the left half. If the target is larger, I update left to mid + 1 to search the right half. After the loop, if we haven't returned, the target isn't in the array, so I return -1."

**Interviewer**: "Why use `<=` in the while condition instead of `<`?"

**Candidate**: "Using `<=` ensures we check the case when left equals right, which happens when we're down to a single element. If we used `<`, we might miss checking that last element. For example, if we're searching for an element in a single-element array, we need to check that element."

**Interviewer**: "What happens if we use `right = mid` instead of `right = mid - 1`?"

**Candidate**: "That would create an infinite loop. If the target is not in the array and we're down to two elements, we'd keep setting right to mid without making progress. The `-1` and `+1` are crucial for ensuring the search space shrinks in each iteration."

## Solution Approaches

### Approach 1: Iterative Binary Search (Recommended)
Use while loop with two pointers. O(log n) time, O(1) space.

**Advantages:**
- O(1) space complexity
- No recursion overhead
- Easy to understand and implement
- Efficient

### Approach 2: Recursive Binary Search
Use recursion to implement binary search. O(log n) time, O(log n) space.

**Advantages:**
- More elegant code
- Natural divide-and-conquer structure

**Disadvantages:**
- O(log n) space for recursion stack
- Potential stack overflow for very large arrays
- Slightly slower due to function call overhead

### Approach 3: Using Arrays.binarySearch()
Java's built-in method. O(log n) time, O(1) space.

**Advantages:**
- No need to implement
- Well-tested and optimized

**Disadvantages:**
- Returns negative insertion point if not found (need to handle)
- Less educational value

## Key Takeaways

1. **Binary search requires sorted array** - Won't work on unsorted data
2. **O(log n) time complexity** - Very efficient for large arrays
3. **Avoid integer overflow** - Use `left + (right - left) / 2`
4. **Iterative preferred** - O(1) space vs O(log n) for recursive
5. **Use `<=` in loop condition** - Ensures we check all elements
6. **Update boundaries correctly** - Use `mid - 1` and `mid + 1` to avoid infinite loops
7. **Early termination** - Return immediately when target found
8. **Handles edge cases** - Empty array, single element, target not found
