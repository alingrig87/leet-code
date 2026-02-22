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

**Example 3:**
```
Input: digits = [9,9,9]
Output: [1,0,0,0]
Explanation: The array represents the integer 999. Incrementing by one gives 999 + 1 = 1000.
```

## Theory & Data Structures

### Array Manipulation with Carry
This problem simulates adding 1 to a number represented as an array of digits. The key challenge is handling carry propagation, similar to how we add numbers manually.

#### How Carry Propagation Works
1. **Start from rightmost digit**: Add 1 to the last digit
2. **Handle carry**: If a digit becomes 10, set it to 0 and carry 1 to the next digit
3. **Propagate carry**: Continue propagating carry leftward until no more carry
4. **Array expansion**: If carry reaches the leftmost digit and it becomes 10, create a new array with 1 followed by zeros

#### Building Carry Mechanism from Scratch (Conceptual)
```java
// Conceptual implementation of adding one with carry
class NumberIncrementer {
    private int[] digits;
    
    NumberIncrementer(int[] digits) {
        this.digits = digits;
    }
    
    // Add one to the number represented by digits array
    public int[] plusOne() {
        // Start from rightmost digit (least significant)
        for (int i = digits.length - 1; i >= 0; i--) {
            // Add one to current digit
            digits[i]++;
            
            // If digit is less than 10, no carry needed
            if (digits[i] < 10) {
                return digits;  // Early exit - no more carry
            }
            
            // Digit is 10, set to 0 and carry 1
            digits[i] = 0;
            // Carry will be handled in next iteration (i-1)
        }
        
        // If we reach here, all digits were 9
        // Need to create new array: [1, 0, 0, ..., 0]
        int[] result = new int[digits.length + 1];
        result[0] = 1;  // First digit is 1
        // Rest are zeros (default initialization)
        return result;
    }
    
    // Alternative: More explicit carry handling
    public int[] plusOneExplicit() {
        int carry = 1;  // Start with carry of 1 (adding one)
        
        // Process from right to left
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;  // Current digit
            carry = sum / 10;       // Carry for next digit
            
            // Early exit if no more carry
            if (carry == 0) {
                return digits;
            }
        }
        
        // If carry remains, need new array
        if (carry > 0) {
            int[] result = new int[digits.length + 1];
            result[0] = carry;  // carry is 1
            return result;
        }
        
        return digits;
    }
}
```

### Reverse Iteration
We process digits from right to left (least significant to most significant) because:
1. Addition starts from the rightmost digit
2. Carry propagates leftward
3. This matches how humans add numbers

### Array Expansion
When all digits are 9 (e.g., [9,9,9]), adding 1 results in [1,0,0,0]:
- Original: 999
- After adding 1: 1000
- Array size increases from 3 to 4

### Time & Space Complexity

#### Approach: Reverse Iteration with Carry
- **Time Complexity**: O(n) - Worst case, iterate through entire array
  - Best case: O(1) - Last digit is not 9 (early exit)
  - Average case: O(k) where k is number of trailing 9s
  - Worst case: O(n) - All digits are 9
- **Space Complexity**: 
  - O(1) if no array expansion (most digits not 9)
  - O(n) if array expansion needed (all digits are 9)

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given an array representing a number, add one to it and return the result as an array."

**Candidate**: "I'll iterate from right to left, starting with the last digit. I'll add one to it. If it becomes 10, I'll set it to 0 and carry 1 to the next digit. I'll continue this process. If all digits become 9, I'll need to create a new array with 1 followed by zeros."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For [1,2,3], I start at index 2 (digit 3). I add 1, getting 4. Since 4 < 10, no carry, so I return [1,2,4]. For [9,9,9], I start at index 2 (digit 9). I add 1, getting 10. I set it to 0 and move left. At index 1, I add carry 1 to 9, getting 10. Set to 0, move left. At index 0, add carry 1 to 9, getting 10. Since we're at the start and still have carry, I create a new array [1,0,0,0]."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) in the worst case when all digits are 9. In the best case, it's O(1) if the last digit is not 9. Space complexity is O(1) if no expansion is needed, or O(n) if we need to create a new array when all digits are 9."

### Follow-up Questions

**Interviewer**: "Can you optimize for the case when the last digit is not 9?"

**Candidate**: "Yes, we can add 1 to the last digit and check if it's less than 10. If it is, we can return immediately without checking other digits. This gives us O(1) time in the best case."

**Interviewer**: "What if we need to add a different number, not just one?"

**Candidate**: "We'd use the same carry mechanism, but add the entire number digit by digit from right to left. We'd need to handle cases where the numbers have different lengths."

**Interviewer**: "What if the array can have leading zeros?"

**Candidate**: "The problem states there are no leading zeros, but if there were, we'd need to handle them. Leading zeros don't affect the addition operation itself, but we might want to remove them from the result."

**Interviewer**: "Can you solve this without modifying the original array?"

**Candidate**: "We could create a new array, but that would use O(n) space even when not needed. The in-place approach is more space-efficient when no expansion is needed. However, when expansion is needed, we must create a new array anyway."

**Interviewer**: "What if the number is very large?"

**Candidate**: "The algorithm handles this correctly. Since we're working with an array of digits, we can represent arbitrarily large numbers. The only limitation is array size, which is typically very large in Java."

### Tricky Edge Cases

1. **All nines**: `[9,9,9]` → `[1,0,0,0]` (array expansion needed)
2. **Single nine**: `[9]` → `[1,0]` (array expansion)
3. **No carry needed**: `[1,2,3]` → `[1,2,4]` (early exit possible)
4. **Multiple carries**: `[1,9,9]` → `[2,0,0]` (carry propagates)
5. **Trailing nines**: `[1,2,9,9]` → `[1,3,0,0]` (partial carry)
6. **Single digit, not nine**: `[5]` → `[6]` (simple case)
7. **Large number**: `[9,9,9,9,9]` → `[1,0,0,0,0,0]` (expansion)
8. **Zero**: `[0]` → `[1]` (edge case)
9. **One**: `[1]` → `[2]` (simple)
10. **Mixed digits**: `[4,3,2,1]` → `[4,3,2,2]` (no carry)
11. **One trailing nine**: `[1,2,9]` → `[1,3,0]` (one carry)
12. **All zeros except last**: `[0,0,0,1]` → `[0,0,0,2]` (no carry)

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll iterate through the array from right to left, starting at the last index. For each digit, I'll add 1. If the result is less than 10, I can return immediately since there's no carry to propagate. If the result is 10, I'll set that digit to 0 and continue to the next digit (moving left). If I process all digits and still have a carry (meaning all digits were 9), I'll create a new array with length n+1, set the first element to 1, and the rest will be 0 by default."

**Interviewer**: "Why process from right to left?"

**Candidate**: "Because addition works from least significant digit (right) to most significant digit (left), and carry propagates leftward. This matches how humans add numbers and is the natural way to handle carries."

**Interviewer**: "Can you optimize the early exit?"

**Candidate**: "Yes, after adding 1 to a digit, if it's less than 10, we can return immediately. This avoids unnecessary iterations when the last few digits are not 9. This optimization is particularly important for performance when dealing with large arrays where only the last digit changes."

## Solution Approaches

### Approach 1: Reverse Iteration with Early Exit (Recommended)
Process from right to left, return early if no carry. O(n) worst case, O(1) best case. O(1) or O(n) space.

**Algorithm:**
1. Iterate from last index to first
2. Add 1 to current digit
3. If digit < 10, return digits (early exit)
4. Else, set digit to 0 and continue
5. If all digits processed, create new array [1, 0, 0, ..., 0]

**Advantages:**
- Early exit optimization
- Simple and intuitive
- Handles all cases correctly

### Approach 2: Explicit Carry Variable
Use explicit carry variable. O(n) time, O(1) or O(n) space.

**Algorithm:**
1. Initialize carry = 1
2. Iterate from right to left
3. Calculate sum = digits[i] + carry
4. Set digits[i] = sum % 10, carry = sum / 10
5. If carry remains, create new array

**Advantages:**
- More explicit about carry handling
- Easier to extend for adding other numbers

**Disadvantages:**
- Slightly more complex
- No early exit optimization

### Approach 3: Convert to Number (Not Recommended)
Convert array to number, add 1, convert back. O(n) time, O(n) space.

**Disadvantages:**
- Integer overflow for large numbers
- Inefficient
- Doesn't work for very large numbers

## Key Takeaways

1. **Process from right to left** - matches how addition works
2. **Handle carry propagation** - when digit becomes 10, set to 0 and carry 1
3. **Array expansion** - needed when all digits are 9
4. **Early exit optimization** - return immediately when no carry
5. **Edge cases matter** - all 9s, single 9, no carry needed
6. **O(1) best case** - when last digit is not 9
7. **O(n) worst case** - when all digits are 9
8. **Space complexity** - O(1) usually, O(n) when expansion needed
9. **In-place modification** - when possible, more space-efficient
10. **Simulates manual addition** - algorithm mirrors human addition process
