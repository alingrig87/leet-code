# Product of Array Except Self

## Problem Statement
Given an integer array `nums`, return an array `answer` such that `answer[i]` is equal to the product of all the elements of `nums` except `nums[i]`.

The product of any prefix or suffix of `nums` is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operator.

**Example 1:**
```
Input: nums = [1,2,3,4]
Output: [24,12,8,6]
```

**Example 2:**
```
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
```

## Theory & Data Structures

### Prefix and Suffix Products
For each position i, the result is:
- Product of all elements to the left of i (prefix product)
- Multiplied by product of all elements to the right of i (suffix product)

### Two Pass Approach
1. First pass: Calculate prefix products (left to right)
2. Second pass: Calculate suffix products (right to left) and multiply with prefix

### Time & Space Complexity

#### Approach: Two Pass with O(1) Extra Space
- **Time Complexity**: O(n) - Two passes through array
- **Space Complexity**: O(1) - Only output array (not counting input/output)

## Interview Simulation

### Initial Discussion

**Interviewer**: "Calculate product of all elements except self, without division."

**Candidate**: "I'll use prefix and suffix products. For each position, I need the product of all elements to the left and all elements to the right. I can calculate prefix products in one pass, then suffix products in another pass."

**Interviewer**: "Can you do it in O(1) extra space?"

**Candidate**: "Yes, I can use the output array to store prefix products, then multiply by suffix products in the second pass from right to left."

**Interviewer**: "What about zeros?"

**Candidate**: "If there's one zero, only that position gets non-zero result. If there are two or more zeros, all results are zero."

### Follow-up Questions

**Interviewer**: "What if we could use division?"

**Candidate**: "We'd calculate total product, then divide by each element. But we need to handle zeros carefully - if there's one zero, only that position is non-zero."

**Interviewer**: "What about integer overflow?"

**Candidate**: "The problem states products fit in 32-bit integer, but in general we'd need to check for overflow or use long."

### Tricky Edge Cases

1. **Single zero**: `[1,0,3,4]` → `[0,12,0,0]`
2. **Multiple zeros**: `[1,0,0,4]` → `[0,0,0,0]`
3. **Negative numbers**: `[-1,2,-3,4]` → Handle normally
4. **All ones**: `[1,1,1,1]` → `[1,1,1,1]`

## Solution Approaches

### Approach: Two Pass (Prefix and Suffix)
Calculate prefix products, then suffix products, multiply. O(n) time, O(1) space.

## Key Takeaways

1. **Prefix and suffix** products solve this elegantly
2. **Two passes** enable O(1) space solution
3. **Handle zeros** carefully
4. **Output array** can be used for intermediate storage
