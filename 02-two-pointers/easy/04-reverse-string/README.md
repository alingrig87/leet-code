# Reverse String

## Problem Statement
Write a function that reverses a string. The input string is given as an array of characters `s`.

You must do this by modifying the input array in-place with `O(1)` extra memory.

**Example 1:**
```
Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
```

**Example 2:**
```
Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
```

## Theory & Data Structures

### Two Pointers Technique
This problem uses the **two pointers** technique from both ends. We swap characters at symmetric positions until the pointers meet.

#### Key Insight: Symmetric Swapping
- **Left pointer**: Starts at index 0
- **Right pointer**: Starts at last index (length - 1)
- **Swap**: Exchange characters at both pointers
- **Move**: Move left pointer right, right pointer left
- **Terminate**: When left >= right

#### Building Two Pointers Reversal from Scratch (Conceptual)
```java
// Conceptual implementation of string reversal
class StringReverser {
    
    // Reverse string using two pointers
    public void reverseString(char[] s) {
        // Two pointers: left at start, right at end
        int left = 0;
        int right = s.length - 1;
        
        // Swap characters until pointers meet
        while (left < right) {
            // Swap characters at left and right positions
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            
            // Move pointers towards center
            left++;
            right--;
        }
    }
    
    // Alternative: Using XOR swap (no temporary variable)
    public void reverseStringXOR(char[] s) {
        int left = 0;
        int right = s.length - 1;
        
        while (left < right) {
            // XOR swap: a ^= b; b ^= a; a ^= b;
            s[left] ^= s[right];
            s[right] ^= s[left];
            s[left] ^= s[right];
            
            left++;
            right--;
        }
    }
    
    // Recursive version (not O(1) space due to stack)
    public void reverseStringRecursive(char[] s, int left, int right) {
        // Base case: pointers have met or crossed
        if (left >= right) {
            return;
        }
        
        // Swap characters
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
        
        // Recursively reverse remaining
        reverseStringRecursive(s, left + 1, right - 1);
    }
}
```

### Why Two Pointers?
- **In-place**: Modifies array without extra space (except temp variable)
- **Efficient**: O(n) time, O(1) space
- **Simple**: Easy to understand and implement
- **Symmetric**: Naturally handles even and odd lengths

### Time & Space Complexity

#### Approach: Two Pointers
- **Time Complexity**: O(n) - Swap n/2 pairs of characters
  - Best case: O(n)
  - Average case: O(n)
  - Worst case: O(n)
- **Space Complexity**: O(1) - Only using a temporary variable for swap
  - Constant space regardless of string length
  - Optimal space complexity

## Interview Simulation

### Initial Discussion

**Interviewer**: "Reverse a string in-place with O(1) extra memory."

**Candidate**: "I'll use two pointers from both ends. I'll swap the characters at both pointer positions, then move the left pointer right and the right pointer left. I'll continue until the pointers meet or cross."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For ['h','e','l','l','o'], I start with left=0, right=4. Swap 'h' and 'o', getting ['o','e','l','l','h'], then left=1, right=3. Swap 'e' and 'l', getting ['o','l','l','e','h'], then left=2, right=2. Since left >= right, we stop. Result: ['o','l','l','e','h']."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we swap n/2 pairs of characters. Space complexity is O(1) since we only use a temporary variable for swapping."

### Follow-up Questions

**Interviewer**: "What if it's a String object in Java instead of a char array?"

**Candidate**: "Java Strings are immutable, so we can't modify them in-place. We'd need to convert to a char array first, reverse it, then create a new String from the reversed array. This would use O(n) extra space."

**Interviewer**: "Can you do it recursively?"

**Candidate**: "Yes, but recursion uses O(n) stack space for the call stack, so it's not O(1) space. The iterative approach is preferred for the O(1) space requirement."

**Interviewer**: "What if we need to reverse only a portion of the string?"

**Candidate**: "We'd modify the algorithm to start with left at the start index and right at the end index of the portion to reverse. The logic remains the same - swap until pointers meet."

**Interviewer**: "Can you optimize this further?"

**Candidate**: "The two-pointer approach is already optimal - O(n) time and O(1) space. We could use XOR swap to avoid the temporary variable, but that's a micro-optimization and makes the code less readable. The current approach is clear and efficient."

**Interviewer**: "What if the string contains Unicode characters?"

**Candidate**: "The algorithm works correctly with any characters, including Unicode. We're just swapping array elements, so the character encoding doesn't matter. However, if we're dealing with multi-byte characters and need to preserve character boundaries, we'd need a different approach."

### Tricky Edge Cases

1. **Empty array**: `[]` → No change (no elements to swap)
2. **Single character**: `["a"]` → No change (left = right = 0)
3. **Two characters**: `["a","b"]` → `["b","a"]`
4. **Even length**: `["a","b","c","d"]` → `["d","c","b","a"]`
5. **Odd length**: `["a","b","c"]` → `["c","b","a"]` (middle character stays)
6. **Palindrome**: `["a","b","a"]` → `["a","b","a"]` (same after reverse)
7. **All same characters**: `["a","a","a"]` → `["a","a","a"]` (no change)
8. **Unicode characters**: Works with any characters
9. **Large string**: Works with any size

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll initialize left pointer at 0 and right pointer at the last index (length - 1). I'll use a while loop with condition left < right. Inside the loop, I'll swap the characters at left and right positions using a temporary variable. Then I'll increment left and decrement right. When the loop ends (left >= right), all characters have been swapped, and the string is reversed."

**Interviewer**: "Why use left < right instead of left <= right?"

**Candidate**: "When left equals right, we're at the middle character (for odd-length strings). There's no need to swap a character with itself. Using left < right ensures we only swap when we have two different positions."

**Interviewer**: "What happens with an odd-length string?"

**Candidate**: "For an odd-length string like ['a','b','c'], we'll swap 'a' and 'c' when left=0 and right=2. Then left=1 and right=1. Since left is not less than right, the loop ends. The middle character 'b' stays in place, which is correct."

## Solution Approaches

### Approach 1: Two Pointers with Temp Variable (Recommended)
Swap characters using temporary variable. O(n) time, O(1) space.

**Algorithm:**
1. Initialize left = 0, right = length - 1
2. While left < right:
   - Swap s[left] and s[right] using temp variable
   - Increment left, decrement right
3. String is reversed

**Advantages:**
- Simple and readable
- O(1) space complexity
- Optimal time complexity
- Easy to understand

### Approach 2: XOR Swap
Use XOR to swap without temporary variable. O(n) time, O(1) space.

**Algorithm:**
1. Same as Approach 1
2. Use XOR operations: a ^= b; b ^= a; a ^= b;

**Advantages:**
- No temporary variable needed
- Still O(1) space

**Disadvantages:**
- Less readable
- Can be confusing
- Micro-optimization

### Approach 3: Recursive
Recursively swap characters. O(n) time, O(n) space.

**Disadvantages:**
- O(n) space for recursion stack
- Not O(1) space
- Potential stack overflow for long strings

## Key Takeaways

1. **Two pointers** perfect for in-place reversal
2. **Swap operation** is O(1)
3. **O(1) space** solution (except temp variable)
4. **O(n) time** - swap n/2 pairs
5. **Works for any length** - even, odd, empty
6. **Simple and efficient** - optimal solution
7. **In-place modification** - no extra array needed
8. **Classic problem** - fundamental to know
9. **Foundation for** more complex reversal problems
10. **Handles edge cases** naturally - empty, single char, etc.
