# Sqrt(x)

## Problem Statement
Given a non-negative integer `x`, return the square root of `x` rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

**Example 1:**
```
Input: x = 4
Output: 2
Explanation: The square root of 4 is 2.
```

**Example 2:**
```
Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
```

**Example 3:**
```
Input: x = 0
Output: 0
```

## Theory & Data Structures

### Binary Search for Square Root
This problem uses **binary search** to find the square root. The key insight is that the square root of x must be in the range [0, x], and we can use binary search to find the largest integer whose square is <= x.

#### Key Insight: Search Space
- **Lower bound**: 0 (square root of 0 is 0)
- **Upper bound**: x (square root of x is at most x, actually at most x/2 for x > 1, but x is safe)
- **Target**: Largest integer `ans` such that `ans * ans <= x`

#### Building Binary Search for Square Root from Scratch (Conceptual)
```java
// Conceptual implementation of binary search for square root
class SquareRootFinder {
    
    // Find square root using binary search
    public int mySqrt(int x) {
        // Edge cases
        if (x == 0) return 0;
        if (x == 1) return 1;
        
        // Search space: [0, x]
        // Actually, we can optimize: sqrt(x) <= x/2 for x > 1
        // But using x is safe and simpler
        int left = 1;
        int right = x;
        
        while (left <= right) {
            // Calculate middle
            // Use long to avoid overflow when mid*mid might exceed Integer.MAX_VALUE
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;  // Cast to long to prevent overflow
            
            if (square == x) {
                // Found exact square root
                return mid;
            } else if (square < x) {
                // mid*mid < x, so sqrt(x) >= mid
                // Try larger values
                left = mid + 1;
            } else {
                // mid*mid > x, so sqrt(x) < mid
                // Try smaller values
                right = mid - 1;
            }
        }
        
        // When loop ends, right < left
        // right is the largest integer whose square <= x
        return right;
    }
    
    // Alternative: More optimized version
    public int mySqrtOptimized(int x) {
        if (x == 0) return 0;
        
        int left = 1;
        int right = x;
        int result = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Check if mid is valid (mid*mid <= x)
            // Use division to avoid overflow: mid <= x / mid
            if (mid <= x / mid) {
                // mid is valid, try larger values
                result = mid;  // Update result
                left = mid + 1;
            } else {
                // mid is too large, try smaller values
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    // Why use long or division?
    // - When mid is large, mid*mid can overflow int
    // - Example: mid = 46341, mid*mid > Integer.MAX_VALUE
    // - Solution: Use long for square, or use division (mid <= x/mid)
}
```

### Overflow Prevention
When calculating `mid * mid`, we must prevent integer overflow:
- **Problem**: For large x, `mid * mid` can exceed `Integer.MAX_VALUE`
- **Solution 1**: Use `long` for the square calculation
- **Solution 2**: Use division: check `mid <= x / mid` instead of `mid * mid <= x`

### Time & Space Complexity

#### Approach: Binary Search
- **Time Complexity**: O(log x) - Binary search halves search space each iteration
  - Best case: O(log x) - Perfect square found quickly
  - Average case: O(log x)
  - Worst case: O(log x) - Not a perfect square
- **Space Complexity**: O(1) - Only using variables (left, right, mid)
  - Iterative approach uses O(1) space
  - Recursive approach would use O(log x) space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find the square root of x without using built-in functions, rounded down."

**Candidate**: "I'll use binary search in the range [0, x]. I'll find the largest integer whose square is less than or equal to x. If mid*mid equals x, I return mid. If mid*mid is less than x, I try larger values. If it's greater, I try smaller values."

**Interviewer**: "What about integer overflow?"

**Candidate**: "When calculating mid*mid, if mid is large, the result can overflow. I'll use long for the square calculation, or alternatively check mid <= x/mid to avoid the multiplication."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For x = 8, I start with left=1, right=8. Mid=4, 4*4=16 > 8, so right=3. Mid=2, 2*2=4 < 8, so left=3. Mid=3, 3*3=9 > 8, so right=2. Now left=3 > right=2, loop ends. Right=2 is the answer, which is correct."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(log x) since we use binary search. Space complexity is O(1) since we only use a few variables."

### Follow-up Questions

**Interviewer**: "What if we need decimal precision?"

**Candidate**: "We'd modify the binary search to work with decimals. We'd use a precision threshold (epsilon), and continue searching until the difference between mid*mid and x is within epsilon. We'd return a double instead of int."

**Interviewer**: "Can you optimize the search space?"

**Candidate**: "Yes, for x > 1, we know sqrt(x) <= x/2. So we could set right = x/2 initially. However, for simplicity and correctness, using x as the upper bound is fine and doesn't significantly impact performance."

**Interviewer**: "What if x is very large (close to Integer.MAX_VALUE)?"

**Candidate**: "We need to be careful with overflow. Using long for the square calculation is essential. Alternatively, using division (mid <= x/mid) avoids overflow entirely. The division approach is actually safer for very large x."

**Interviewer**: "Can you solve this with Newton's method?"

**Candidate**: "Yes, Newton's method can find square roots iteratively using the formula: next = (current + x/current) / 2. It converges very quickly, often in O(log log x) iterations, but binary search is simpler and more intuitive."

**Interviewer**: "What's the difference between rounding down and rounding to nearest?"

**Candidate**: "Rounding down (floor) means we always take the smaller integer. Rounding to nearest would require checking if the next integer is closer. The problem specifically asks for rounding down, which is what we get when the loop ends with right < left."

### Tricky Edge Cases

1. **x = 0**: Return `0` (edge case)
2. **x = 1**: Return `1` (perfect square)
3. **Perfect square**: `x = 4` → Return `2` (exact match)
4. **Not perfect square**: `x = 8` → Return `2` (rounded down)
5. **Large perfect square**: `x = 10000` → Return `100`
6. **Large non-perfect square**: `x = 9999` → Return `99`
7. **x = 2**: Return `1` (sqrt(2) ≈ 1.414, rounded down)
8. **x = 3**: Return `1` (sqrt(3) ≈ 1.732, rounded down)
9. **x = Integer.MAX_VALUE**: Handle overflow carefully
10. **x = 2147395599**: Large number, need to handle correctly
11. **Very small x**: `x = 1, 2, 3` → Handle correctly
12. **Boundary values**: Test edge cases carefully

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll handle edge cases first - if x is 0, return 0. I'll initialize left to 1 and right to x. In a binary search loop, I'll calculate mid. I'll compute mid*mid using long to prevent overflow. If the square equals x, I return mid. If it's less than x, I try larger values by setting left = mid + 1. If it's greater, I try smaller values by setting right = mid - 1. When the loop ends, right is the largest integer whose square is <= x, so I return right."

**Interviewer**: "Why return right instead of left?"

**Candidate**: "When the loop ends with left > right, right is the last valid value we checked (where right*right <= x). Left would be the first value where left*left > x. Since we want the largest integer whose square <= x, we return right."

**Interviewer**: "What if we use the division approach to avoid overflow?"

**Candidate**: "Instead of calculating mid*mid, we check if mid <= x/mid. This avoids overflow entirely. If mid <= x/mid, then mid*mid <= x, so mid is valid. This is safer for very large x values."

## Solution Approaches

### Approach 1: Binary Search with Long (Recommended)
Use binary search with long for square calculation. O(log x) time, O(1) space.

**Algorithm:**
1. Handle edge cases (x == 0, x == 1)
2. Initialize left = 1, right = x
3. While left <= right:
   - Calculate mid = left + (right - left) / 2
   - Calculate square = (long) mid * mid
   - If square == x: return mid
   - If square < x: left = mid + 1
   - Else: right = mid - 1
4. Return right (largest valid value)

**Advantages:**
- Simple and intuitive
- Handles overflow with long
- O(log x) time complexity

### Approach 2: Binary Search with Division
Use division to avoid overflow. O(log x) time, O(1) space.

**Algorithm:**
1. Initialize left = 1, right = x, result = 0
2. While left <= right:
   - Calculate mid
   - If mid <= x / mid: result = mid, left = mid + 1
   - Else: right = mid - 1
3. Return result

**Advantages:**
- No overflow risk
- Safer for very large x

**Disadvantages:**
- Slightly more complex logic

### Approach 3: Newton's Method
Use Newton's method for faster convergence. O(log log x) time typically, O(1) space.

**Algorithm:**
1. Start with guess = x
2. While guess * guess > x:
   - guess = (guess + x / guess) / 2
3. Return (int) guess

**Advantages:**
- Very fast convergence
- Elegant mathematical approach

**Disadvantages:**
- Less intuitive
- May need more iterations for precision

## Key Takeaways

1. **Binary search** for finding square root in range [0, x]
2. **Handle overflow** with long or division
3. **Find largest** integer whose square <= x
4. **Return right** when loop ends (not left)
5. **O(log x) solution** - optimal for this problem
6. **Edge cases matter** - x = 0, x = 1, perfect squares
7. **Overflow prevention** is critical for large x
8. **Division approach** is safer for very large numbers
9. **Newton's method** is alternative but less intuitive
10. **Rounding down** means return largest valid integer
