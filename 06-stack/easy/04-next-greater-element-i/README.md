# Next Greater Element I

## Problem Statement
The next greater element of some element `x` in an array is the first greater element that is to the right of `x` in the same array.

You are given two distinct 0-indexed integer arrays `nums1` and `nums2`, where `nums1` is a subset of `nums2`.

For each `0 <= i < nums1.length`, find the index `j` such that `nums1[i] == nums2[j]` and determine the next greater element of `nums2[j]` in `nums2`. If there is no next greater element, then the answer for this query is `-1`.

Return an array `ans` of length `nums1.length` such that `ans[i]` is the next greater element as described above.

**Example 1:**
```
Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
```

## Theory & Data Structures

### Monotonic Stack
This problem uses a **monotonic stack** (specifically, a decreasing stack) to find the next greater element efficiently.

#### Key Insight: Process Right to Left
- **Direction**: Process nums2 from right to left
- **Stack property**: Maintain decreasing stack (larger elements at bottom)
- **Logic**: For each element, pop smaller elements from stack, then top is next greater
- **Store**: Use HashMap to store next greater for each element

#### Building Monotonic Stack from Scratch (Conceptual)
```java
// Conceptual implementation of next greater element
class NextGreaterFinder {
    
    // Find next greater elements
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // HashMap to store next greater for each element in nums2
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        
        // Monotonic stack (decreasing)
        Stack<Integer> stack = new Stack<>();
        
        // Process nums2 from right to left
        for (int i = nums2.length - 1; i >= 0; i--) {
            int current = nums2[i];
            
            // Pop all elements smaller than current
            // These elements have current as their next greater
            while (!stack.isEmpty() && stack.peek() <= current) {
                stack.pop();
            }
            
            // Top of stack is next greater element (or -1 if empty)
            nextGreaterMap.put(current, stack.isEmpty() ? -1 : stack.peek());
            
            // Push current element to stack
            stack.push(current);
        }
        
        // Build result array for nums1
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterMap.get(nums1[i]);
        }
        
        return result;
    }
    
    // Why process right to left?
    // Because we need to find elements to the RIGHT
    // By processing right to left, we've already seen future elements
    // Stack maintains elements we've seen, in decreasing order
    
    // Alternative: Process left to right (less intuitive)
    public int[] nextGreaterElementLeftToRight(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        
        // Process left to right
        for (int num : nums2) {
            // While stack not empty and current > top
            // Current is next greater for top
            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        
        // Remaining elements in stack have no next greater
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }
        
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }
        
        return result;
    }
}
```

### Why Monotonic Stack?
- **Efficiency**: O(n) time to find next greater for all elements
- **Decreasing stack**: Maintains elements in decreasing order
- **Optimal**: Best possible time complexity

### Time & Space Complexity

#### Approach: Monotonic Stack
- **Time Complexity**: O(n + m) - Process both arrays
  - Process nums2: O(n) where n = nums2.length
  - Process nums1: O(m) where m = nums1.length
  - Total: O(n + m)
- **Space Complexity**: O(n) - Stack and HashMap
  - Stack: O(n) worst case
  - HashMap: O(n)
  - Total: O(n)

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find next greater element for each element in nums1, where next greater is defined in nums2."

**Candidate**: "I'll use a monotonic stack on nums2 to find the next greater element for all elements in nums2. I'll store the results in a HashMap, then look up values for nums1. I'll process nums2 from right to left, maintaining a decreasing stack."

**Interviewer**: "Why process from right to left?"

**Candidate**: "Because we need to find elements to the right. By processing right to left, we've already seen future elements. The stack maintains elements we've seen, and when we encounter a smaller element, we know the stack top is its next greater."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For nums2=[1,3,4,2], I process from right: Start with 2, stack=[], map[2]=-1, push 2, stack=[2]. Process 4: pop 2 (2<4), stack=[], map[4]=-1, push 4, stack=[4]. Process 3: 3<4, map[3]=4, push 3, stack=[4,3]. Process 1: pop 3 (3>1), map[1]=3, push 1, stack=[4,3,1]. For nums1=[4,1,2], result=[-1,3,-1]."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n + m) where n is nums2.length and m is nums1.length. Space complexity is O(n) for the stack and HashMap."

### Follow-up Questions

**Interviewer**: "What if we need next greater for all elements in nums2?"

**Candidate**: "Same approach, we'd just return the results for all elements in nums2 instead of looking up nums1 values."

**Interviewer**: "Can you process left to right instead?"

**Candidate**: "Yes, we can process left to right. For each element, while the stack is not empty and current element is greater than stack top, current is the next greater for stack top. We pop and record, then push current. This also works and is sometimes more intuitive."

**Interviewer**: "What if nums2 has duplicates?"

**Candidate**: "The problem states distinct integers, but if there were duplicates, we'd need to handle them. We might need to store indices instead of values, or use a different data structure."

### Tricky Edge Cases

1. **No greater element**: Return -1
2. **All decreasing**: All -1
3. **Single element**: Return -1
4. **Last element**: Return -1
5. **All increasing**: Each has next greater
6. **nums1 empty**: Return []
7. **nums2 empty**: Return array of -1
8. **nums1 not in nums2**: Handle lookup

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll create a HashMap to store next greater elements and a stack for the monotonic stack. I'll process nums2 from right to left. For each element, I'll pop all elements from the stack that are smaller than or equal to the current element. Then the top of the stack (if not empty) is the next greater element for the current element. I'll store this in the HashMap and push the current element. After processing nums2, I'll build the result array by looking up each element of nums1 in the HashMap."

## Solution Approaches

### Approach 1: Monotonic Stack Right to Left (Recommended)
Process nums2 right to left, maintain decreasing stack. O(n+m) time, O(n) space.

**Algorithm:**
1. Create HashMap and stack
2. Process nums2 from right to left:
   - Pop smaller elements from stack
   - Top is next greater (or -1)
   - Store in HashMap
   - Push current
3. Build result from HashMap lookups

**Advantages:**
- O(n+m) time complexity
- Clear logic
- Efficient

### Approach 2: Monotonic Stack Left to Right
Process nums2 left to right. O(n+m) time, O(n) space.

**Similar complexity, different processing order**

## Key Takeaways

1. **Monotonic stack** for next greater element problems
2. **Process right to left** (or left to right)
3. **Maintain decreasing** stack
4. **HashMap** for lookups
5. **O(n+m) time, O(n) space**
6. **Foundation for** next greater in circular array
7. **Classic stack problem** - important pattern
8. **Edge cases matter** - no greater element, all decreasing
