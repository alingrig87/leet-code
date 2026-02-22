# Valid Perfect Square

## Problem Statement
Given a positive integer `num`, return `true` if `num` is a perfect square or `false` otherwise.

A perfect square is an integer that is the square of an integer. In other words, it is the product of some integer with itself.

You must not use any built-in library function, such as `sqrt`.

**Example 1:**
```
Input: num = 16
Output: true
```

## Theory & Data Structures

### Binary Search
Search in [1, num] for integer whose square equals num.

### Time & Space Complexity

#### Approach: Binary Search
- **Time Complexity**: O(log num) - Binary search
- **Space Complexity**: O(1) - Iterative

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if number is perfect square."

**Candidate**: "I'll use binary search in range [1, num]. Check if mid*mid == num. If mid*mid < num, try larger. If greater, try smaller."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(log num) time and O(1) space."

### Follow-up Questions

**Interviewer**: "Can you optimize?"

**Candidate**: "We could use Newton's method for faster convergence, but binary search is simpler and still efficient."

### Tricky Edge Cases

1. **num = 1**: Return true
2. **Perfect square**: Return true
3. **Not perfect square**: Return false
4. **Large num**: Handle overflow with long

## Solution Approaches

### Approach: Binary Search (Optimal)
Binary search to find if square root is integer. O(log num) time, O(1) space.

## Key Takeaways

1. **Binary search** for perfect square check
2. **Handle overflow** with long
3. **Simple and efficient**
4. **O(log num)** solution
