# Plus One

## Problem Statement
You are given a large integer represented as an integer array `digits`, where each `digits[i]` is the `i`th digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading zeros.

Increment the large integer by one and return the resulting array of digits.

**Example 1:**
```
Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123. Incrementing by one gives 123 + 1 = 124.
```

**Example 2:**
```
Input: digits = [9]
Output: [1,0]
Explanation: The array represents the integer 9. Incrementing by one gives 9 + 1 = 10.
```

## Theory & Data Structures

### Array Manipulation
We need to handle:
1. Normal increment: `[1,2,3]` → `[1,2,4]`
2. Carry propagation: `[1,9,9]` → `[2,0,0]`
3. Array expansion: `[9]` → `[1,0]`

### Time & Space Complexity

#### Approach: Reverse Iteration
- **Time Complexity**: O(n) - Worst case, iterate through entire array
- **Space Complexity**: O(1) if no expansion, O(n) if new array needed

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given an array representing a number, add one to it."

**Candidate**: "I'll iterate from right to left. Add one to the last digit. If it becomes 10, set it to 0 and carry 1 to the next digit. If all digits become 9, we need a new array with 1 followed by zeros."

**Interviewer**: "What about edge cases?"

**Candidate**: "The main edge case is when all digits are 9, like [9,9,9], which becomes [1,0,0,0]. We need to create a new array in this case."

### Follow-up Questions

**Interviewer**: "What if we need to add a different number, not just one?"

**Candidate**: "We'd use the same carry mechanism, but add the entire number digit by digit from right to left."

**Interviewer**: "Can you optimize for the case when no carry is needed?"

**Candidate**: "Yes, if the last digit is not 9, we can just increment it and return immediately without checking other digits."

### Tricky Edge Cases

1. **All nines**: `[9,9,9]` → `[1,0,0,0]` (array expansion)
2. **Single nine**: `[9]` → `[1,0]`
3. **No carry**: `[1,2,3]` → `[1,2,4]` (early exit possible)
4. **Multiple carries**: `[1,9,9]` → `[2,0,0]`

## Solution Approaches

### Approach: Reverse Iteration with Carry
Process from right to left, handle carries. O(n) time, O(1) or O(n) space.

## Key Takeaways

1. **Process from right to left** for number operations
2. **Handle carry propagation** carefully
3. **Array expansion** needed when all digits are 9
4. **Early exit** optimization when no carry
