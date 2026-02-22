# Kth Largest Element in an Array

## Problem Statement
Given an integer array `nums` and an integer `k`, return the `k`th largest element in the array.

Note that it is the `k`th largest element in the sorted order, not the `k`th distinct element.

**Example 1:**
```
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Explanation: The sorted array is [1,2,3,4,5,6], so the 2nd largest is 5.
```

**Example 2:**
```
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
```

## Theory & Data Structures

### Min Heap Approach
This problem uses a **min heap** of size `k` to efficiently track the `k` largest elements. The smallest element among the `k` largest is the `k`th largest.

#### Key Insight: Min Heap for Kth Largest
- **Min heap of size k**: Maintains k largest elements
- **Top of heap**: Smallest among k largest = kth largest
- **When adding**: If element > heap top, replace top
- **Efficiency**: O(log k) per operation

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of kth largest
class KthLargestElement {
    
    // Min heap approach
    public int findKthLargest(int[] nums, int k) {
        // Min heap: smallest element at top
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            if (minHeap.size() < k) {
                // Add to heap if not full
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                // If larger than smallest in heap, replace
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        
        // Top of min heap is kth largest
        return minHeap.peek();
    }
    
    // Why min heap?
    // - We want kth largest, which is smallest among k largest
    // - Min heap keeps smallest at top
    // - When size = k, top is kth largest
    
    // Alternative: QuickSelect (O(n) average)
    public int findKthLargestQuickSelect(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        
        int pivotIndex = partition(nums, left, right);
        
        if (pivotIndex == k) {
            return nums[pivotIndex];
        } else if (pivotIndex < k) {
            return quickSelect(nums, pivotIndex + 1, right, k);
        } else {
            return quickSelect(nums, left, pivotIndex - 1, k);
        }
    }
    
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### Time & Space Complexity

#### Approach: Min Heap
- **Time Complexity**: O(n log k) - n insertions into heap of size k
  - Each insertion: O(log k)
  - Total: O(n log k)
- **Space Complexity**: O(k) - Heap stores k elements
  - Optimal space usage

#### Approach: QuickSelect
- **Time Complexity**: O(n) average, O(n²) worst case
- **Space Complexity**: O(1) - In-place partitioning

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find the kth largest element in an array."

**Candidate**: "I'll use a min heap of size k. I'll iterate through the array, adding elements to the heap. When the heap size reaches k, if a new element is larger than the heap top, I'll replace the top. After processing all elements, the heap top is the kth largest."

**Interviewer**: "Why min heap instead of max heap?"

**Candidate**: "A min heap of size k keeps the k largest elements, with the smallest of those at the top. That smallest element among the k largest is exactly the kth largest. With a max heap, we'd need to maintain all elements, which is O(n) space instead of O(k)."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n log k) since we make n insertions into a heap of size k. Space complexity is O(k) for the heap."

### Follow-up Questions

**Interviewer**: "Can you do better?"

**Candidate**: "Yes, QuickSelect gives O(n) average case time, but O(n²) worst case. The heap approach is more reliable and has better worst-case guarantees. For small k, the heap approach is very efficient."

**Interviewer**: "What if k is very large, close to n?"

**Candidate**: "Then heap operations become more expensive. In that case, QuickSelect might be better, or we could find the (n-k+1)th smallest element instead."

### Tricky Edge Cases

1. **k = 1**: Return maximum element
2. **k = n**: Return minimum element
3. **All same**: Return that value
4. **Duplicates**: Handle correctly
5. **k out of range**: Handle edge case

## Solution Approaches

### Approach 1: Min Heap (Recommended for small k)
Maintain min heap of size k. O(n log k) time, O(k) space.

**Advantages:**
- O(k) space complexity
- Simple implementation
- Good for small k

### Approach 2: QuickSelect (For large k)
Use partitioning algorithm. O(n) average, O(n²) worst case.

**Advantages:**
- O(n) average time
- O(1) space

**Disadvantages:**
- O(n²) worst case
- More complex implementation

## Key Takeaways

1. **Min heap** for kth largest
2. **Maintain size k** in heap
3. **Top is answer**
4. **Efficient for small k** - O(n log k)
5. **QuickSelect** alternative for large k
6. **Foundation for** selection algorithms
