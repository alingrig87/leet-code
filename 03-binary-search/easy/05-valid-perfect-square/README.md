# Valid Perfect Square

## Problem Statement
Given a positive integer `num`, return `true` if `num` is a perfect square or `false` otherwise.

A perfect square is an integer that is the square of an integer. In other words, it is the product of some integer with itself.

You must not use any built-in library function, such as `sqrt`.

**Example 1:**
```
Input: num = 16
Output: true
Explanation: We return true because 4 * 4 = 16 and 4 is an integer.
```

**Example 2:**
```
Input: num = 14
Output: false
Explanation: We return false because 3.742 * 3.742 = 14 and 3.742 is not an integer.
```

## Theory & Data Structures

### Binary Search Approach
This problem uses **binary search** to check if a number is a perfect square. The key insight is that if `num` is a perfect square, there exists an integer `x` such that `x * x = num`.

#### Key Insight: Search Space
- **Lower bound**: 1 (smallest perfect square is 1)
- **Upper bound**: num (sqrt(num) <= num, actually <= num/2 for num > 1)
- **Target**: Find integer `x` such that `x * x == num`

#### Building Binary Search from Scratch (Conceptual)
```java
// Conceptual implementation of perfect square check
class PerfectSquareChecker {
    
    // Check if num is perfect square using binary search
    public boolean isPerfectSquare(int num) {
        // Edge cases
        if (num == 1) {
            return true;  // 1 is a perfect square
        }
        
        // Search space: [1, num]
        // Actually, we can optimize: sqrt(num) <= num/2 for num > 1
        long left = 1;
        long right = num;
        
        while (left <= right) {
            // Calculate middle
            long mid = left + (right - left) / 2;
            long square = mid * mid;  // Use long to avoid overflow
            
            if (square == num) {
                // Found exact match - num is perfect square
                return true;
            } else if (square < num) {
                // mid*mid < num, so sqrt(num) > mid
                // Try larger values
                left = mid + 1;
            } else {
                // mid*mid > num, so sqrt(num) < mid
                // Try smaller values
                right = mid - 1;
            }
        }
        
        // No integer found whose square equals num
        return false;
    }
    
    // Optimized: Use division to avoid overflow
    public boolean isPerfectSquareOptimized(int num) {
        if (num == 1) {
            return true;
        }
        
        long left = 1;
        long right = num;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            
            // Use division to avoid overflow: mid == num / mid
            // But we also need to check mid * mid == num
            long quotient = num / mid;
            long remainder = num % mid;
            
            if (quotient == mid && remainder == 0) {
                // mid * mid == num (exactly)
                return true;
            } else if (quotient >= mid) {
                // mid is too small, try larger
                left = mid + 1;
            } else {
                // mid is too large, try smaller
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    // Alternative: Check if sqrt is integer
    public boolean isPerfectSquareAlternative(int num) {
        if (num == 1) {
            return true;
        }
        
        int left = 1;
        int right = num;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Check if mid is the square root
            if (mid == num / mid && num % mid == 0) {
                return true;
            } else if (mid < num / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
}
```

### Overflow Prevention
When calculating `mid * mid`, we must prevent integer overflow:
- **Problem**: For large num, `mid * mid` can exceed `Integer.MAX_VALUE`
- **Solution**: Use `long` for mid and square calculations
- **Alternative**: Use division: check `mid == num / mid && num % mid == 0`

### Time & Space Complexity

#### Approach: Binary Search
- **Time Complexity**: O(log num) - Binary search halves search space each iteration
  - Best case: O(1) - num is 1 or a small perfect square
  - Average case: O(log num)
  - Worst case: O(log num) - Not a perfect square
- **Space Complexity**: O(1) - Only using variables (left, right, mid)
  - Iterative approach uses O(1) space
  - Recursive approach would use O(log num) space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if a number is a perfect square without using sqrt function."

**Candidate**: "I'll use binary search in the range [1, num]. I'll check if mid*mid equals num. If it does, num is a perfect square. If mid*mid is less than num, I'll try larger values. If it's greater, I'll try smaller values."

**Interviewer**: "What about integer overflow?"

**Candidate**: "When calculating mid*mid, if mid is large, the result can overflow. I'll use long for the mid and square calculations to prevent overflow. Alternatively, I could use division to check if mid == num/mid and num % mid == 0."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For num = 16, I start with left=1, right=16. Mid=8, 8*8=64 > 16, so right=7. Mid=4, 4*4=16 == 16, so return true. For num = 14, I start with left=1, right=14. Mid=7, 7*7=49 > 14, so right=6. Mid=3, 3*3=9 < 14, so left=4. Mid=5, 5*5=25 > 14, so right=4. Now left=4 > right=4, loop ends. Return false."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(log num) since we use binary search. Space complexity is O(1) since we only use a few variables."

### Follow-up Questions

**Interviewer**: "Can you optimize the search space?"

**Candidate**: "Yes, for num > 1, we know sqrt(num) <= num/2. So we could set right = num/2 initially. However, for simplicity and correctness, using num as the upper bound is fine and doesn't significantly impact performance."

**Interviewer**: "What if num is very large (close to Integer.MAX_VALUE)?"

**Candidate**: "We need to be careful with overflow. Using long for mid and square is essential. The division approach (mid == num/mid && num % mid == 0) is actually safer for very large num."

**Interviewer**: "Can you solve this with Newton's method?"

**Candidate**: "Yes, Newton's method can find square roots iteratively using the formula: next = (current + num/current) / 2. It converges very quickly, often in O(log log num) iterations. However, binary search is simpler and more intuitive."

**Interviewer**: "What if we need to find the square root, not just check if it's a perfect square?"

**Candidate**: "We'd modify the algorithm to return the square root when found, or return -1 if not a perfect square. The binary search approach would work similarly, but we'd return mid instead of true when we find a match."

### Tricky Edge Cases

1. **num = 1**: Return `true` (1 is a perfect square)
2. **Perfect square**: `num = 16` → Return `true`
3. **Not perfect square**: `num = 14` → Return `false`
4. **Large perfect square**: `num = 10000` → Return `true` (100*100)
5. **Large non-perfect square**: `num = 9999` → Return `false`
6. **num = 4**: Return `true` (2*2)
7. **num = 9**: Return `true` (3*3)
8. **num = 2**: Return `false`
9. **num = Integer.MAX_VALUE**: Handle overflow carefully
10. **num = 2147395600**: Large perfect square (46340*46340)

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll handle the edge case first - if num is 1, return true. I'll initialize left to 1 and right to num. In a binary search loop, I'll calculate mid using the overflow-safe formula. I'll compute mid*mid using long to prevent overflow. If the square equals num, I return true. If it's less than num, I try larger values by setting left = mid + 1. If it's greater, I try smaller values by setting right = mid - 1. When the loop ends, if we haven't returned true, num is not a perfect square, so I return false."

**Interviewer**: "Why use long for mid and square?"

**Candidate**: "Because mid*mid can exceed Integer.MAX_VALUE for large num. For example, if num is close to Integer.MAX_VALUE and mid is around 46340, mid*mid would be around 2.1 billion, which exceeds Integer.MAX_VALUE (2.1 billion). Using long prevents this overflow."

**Interviewer**: "What if we use the division approach?"

**Candidate**: "Instead of calculating mid*mid, we check if mid == num/mid and num % mid == 0. This avoids overflow entirely because we're using division, which won't overflow. However, we need to be careful with the logic to ensure we're checking correctly."

## Solution Approaches

### Approach 1: Binary Search with Long (Recommended)
Use binary search with long to prevent overflow. O(log num) time, O(1) space.

**Algorithm:**
1. Handle edge case: if num == 1, return true
2. Initialize left = 1, right = num
3. While left <= right:
   - Calculate mid = left + (right - left) / 2
   - Calculate square = (long) mid * mid
   - If square == num: return true
   - If square < num: left = mid + 1
   - Else: right = mid - 1
4. Return false

**Advantages:**
- Simple and intuitive
- Handles overflow with long
- O(log num) time complexity

### Approach 2: Binary Search with Division
Use division to avoid overflow. O(log num) time, O(1) space.

**Algorithm:**
1. Handle edge case
2. Binary search with division check
3. Check if mid == num/mid && num % mid == 0

**Advantages:**
- No overflow risk
- Safer for very large num

**Disadvantages:**
- Slightly more complex logic

### Approach 3: Newton's Method
Use Newton's method for faster convergence. O(log log num) time typically, O(1) space.

**Advantages:**
- Very fast convergence
- Elegant mathematical approach

**Disadvantages:**
- Less intuitive
- May need more iterations for precision

## Key Takeaways

1. **Binary search** for perfect square check in range [1, num]
2. **Handle overflow** with long or division
3. **Check mid*mid == num** to determine if perfect square
4. **O(log num) solution** - optimal for this problem
5. **Edge cases matter** - num = 1, large numbers
6. **Overflow prevention** is critical for large num
7. **Division approach** is safer for very large numbers
8. **Newton's method** is alternative but less intuitive
9. **Simple problem** but tests binary search understanding
10. **Foundation for** square root calculation problems
