# Valid Palindrome

## Problem Statement
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

**Example 1:**
```
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
```

**Example 2:**
```
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
```

**Example 3:**
```
Input: s = " "
Output: true
Explanation: After removing non-alphanumeric characters, we get an empty string which is a palindrome.
```

## Theory & Data Structures

### Two Pointers Technique
The **Two Pointers** technique is a powerful approach for solving problems involving arrays or strings. It uses two pointers that traverse the data structure, often from opposite ends or moving at different speeds.

#### How Two Pointers Work Internally
1. **Initialization**: Place two pointers at strategic positions (often at both ends)
2. **Movement**: Move pointers based on conditions (towards each other, same direction, or different speeds)
3. **Comparison**: Compare elements at pointer positions
4. **Termination**: Stop when pointers meet or cross, or when a condition is satisfied

#### Building Two Pointers from Scratch (Conceptual)
```java
// Conceptual implementation of two pointers pattern
class TwoPointers {
    // Left pointer starts at beginning
    private int left;
    
    // Right pointer starts at end
    private int right;
    
    // String or array to process
    private String data;
    
    TwoPointers(String data) {
        this.data = data;
        this.left = 0;
        this.right = data.length() - 1;
    }
    
    // Move left pointer forward (skip non-alphanumeric)
    public void moveLeft() {
        while (left < right && !isAlphanumeric(data.charAt(left))) {
            left++;
        }
    }
    
    // Move right pointer backward (skip non-alphanumeric)
    public void moveRight() {
        while (left < right && !isAlphanumeric(data.charAt(right))) {
            right--;
        }
    }
    
    // Check if pointers have met
    public boolean pointersMet() {
        return left >= right;
    }
    
    // Get characters at both pointers
    public char getLeftChar() {
        return Character.toLowerCase(data.charAt(left));
    }
    
    public char getRightChar() {
        return Character.toLowerCase(data.charAt(right));
    }
    
    // Compare characters at both pointers
    public boolean compare() {
        return getLeftChar() == getRightChar();
    }
    
    // Move both pointers towards center
    public void moveBoth() {
        left++;
        right--;
    }
    
    // Helper: check if character is alphanumeric
    private boolean isAlphanumeric(char c) {
        return Character.isLetterOrDigit(c);
    }
}
```

### Character Validation
We need to check if a character is alphanumeric (letter or digit). Java provides `Character.isLetterOrDigit()` which handles:
- Lowercase letters (a-z)
- Uppercase letters (A-Z)
- Digits (0-9)
- Unicode letters and digits

#### Manual Character Validation
```java
// Manual check for alphanumeric
private boolean isAlphanumeric(char c) {
    return (c >= 'a' && c <= 'z') || 
           (c >= 'A' && c <= 'Z') || 
           (c >= '0' && c <= '9');
}

// Using Character class (handles Unicode)
private boolean isAlphanumeric(char c) {
    return Character.isLetterOrDigit(c);
}
```

### Case Conversion
Java's `Character.toLowerCase()` converts uppercase to lowercase while preserving lowercase and non-letter characters.

### Time & Space Complexity

#### Approach: Two Pointers
- **Time Complexity**: O(n) - Each character is visited at most once
  - Left pointer: O(n) worst case
  - Right pointer: O(n) worst case
  - Total: O(n)
- **Space Complexity**: O(1) - Only using two integer pointers and a few variables

#### Alternative: Pre-process String
- **Time Complexity**: O(n) - One pass to clean, one pass to check
- **Space Complexity**: O(n) - Need space for cleaned string

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given a string, determine if it's a palindrome after removing non-alphanumeric characters and converting to lowercase."

**Candidate**: "I'll use the two pointers technique. I'll place one pointer at the start and one at the end of the string. I'll skip non-alphanumeric characters and compare characters at both pointers. If they match, I move both pointers towards the center. If they don't match, it's not a palindrome."

**Interviewer**: "Can you walk me through your approach step by step?"

**Candidate**: "Sure. I'll initialize left pointer at index 0 and right pointer at the last index. In a loop, I'll first skip any non-alphanumeric characters by moving the left pointer forward and right pointer backward. Then I'll compare the characters at both positions (converting to lowercase). If they match, I move both pointers towards center. If they don't match, I return false. The loop continues until the pointers meet or cross."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we visit each character at most once. Space complexity is O(1) since we only use two pointers and don't create any additional data structures."

### Follow-up Questions

**Interviewer**: "Can you solve this with O(1) extra space?"

**Candidate**: "Yes, the two-pointer approach I described uses O(1) space - just two integer variables for the pointers. We don't need to create a cleaned version of the string."

**Interviewer**: "What if we need to preserve the original string?"

**Candidate**: "The two-pointer approach doesn't modify the original string, so it's already preserved. If we needed to create a cleaned version for other purposes, that would require O(n) space."

**Interviewer**: "What about Unicode characters?"

**Candidate**: "Java's `Character.isLetterOrDigit()` handles Unicode characters correctly. It recognizes letters and digits from various Unicode ranges, not just ASCII. For manual implementation, we'd need to check Unicode character properties, which is more complex."

**Interviewer**: "What if the string is very long?"

**Candidate**: "The two-pointer approach is efficient even for very long strings - O(n) time and O(1) space. We can also add early exit optimizations, though for palindrome checking we need to process the entire string in worst case."

**Interviewer**: "Can you optimize this further?"

**Candidate**: "We could combine the skip and compare operations, but the current approach is already quite efficient. One optimization is to check if pointers have met before doing character comparisons, but this is a minor improvement."

### Tricky Edge Cases

1. **Empty string**: `""` → Return `true` (empty string is a palindrome)
2. **Single character**: `"a"` → Return `true` (single character is a palindrome)
3. **Only non-alphanumeric**: `",!@#"` → Return `true` (empty after cleaning, which is a palindrome)
4. **Mixed case**: `"Aba"` → Return `true` (case-insensitive comparison)
5. **Numbers**: `"12321"` → Return `true` (numbers are alphanumeric)
6. **Mixed alphanumeric**: `"a1b2b1a"` → Return `true`
7. **Spaces only**: `"   "` → Return `true` (empty after cleaning)
8. **Single alphanumeric with non-alphanumeric**: `",a,"` → Return `true`
9. **Not palindrome**: `"race a car"` → Return `false` ("raceacar" → 'r' != 'r' at some point)
10. **Unicode characters**: `"café"` → Depends on implementation (Character.isLetterOrDigit handles Unicode)
11. **Very long palindrome**: Large string → Still O(n) time
12. **All same character**: `"aaaa"` → Return `true`

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll start by initializing two pointers - left at 0 and right at the last index. Then I'll create a while loop that continues while left is less than right. Inside the loop, I'll first skip non-alphanumeric characters by moving the left pointer forward while it's less than right and the character isn't alphanumeric. Similarly, I'll move the right pointer backward. After skipping, I'll check if the pointers have crossed - if so, we've processed everything. Then I'll compare the characters at both positions, converting to lowercase. If they don't match, return false. If they match, move both pointers towards center. After the loop, if we haven't returned false, it's a palindrome."

**Interviewer**: "Why convert to lowercase during comparison rather than at the start?"

**Candidate**: "Converting during comparison avoids creating a new string, saving O(n) space. We only convert the characters we're actually comparing, which is more memory-efficient."

**Interviewer**: "What if both pointers point to non-alphanumeric characters?"

**Candidate**: "The skip logic handles this - we move both pointers until they point to alphanumeric characters or until they meet. If they meet while skipping, we've processed all characters and it's a palindrome."

## Solution Approaches

### Approach 1: Two Pointers (Optimal)
Use two pointers from both ends, skip non-alphanumeric characters, compare lowercase characters. O(n) time, O(1) space.

**Advantages:**
- O(1) space complexity
- Single pass through string
- No string modification needed
- Early exit possible

### Approach 2: Pre-process and Check
Create a cleaned string first, then check if it's a palindrome. O(n) time, O(n) space.

**Advantages:**
- Simpler logic
- Easier to understand

**Disadvantages:**
- Uses O(n) extra space
- Two passes needed

### Approach 3: Reverse and Compare
Create cleaned string, reverse it, and compare with original. O(n) time, O(n) space.

**Advantages:**
- Very intuitive
- Easy to implement

**Disadvantages:**
- Uses O(n) extra space
- Less efficient than two pointers

## Key Takeaways

1. **Two pointers technique** is perfect for palindrome checking
2. **Skip non-alphanumeric** characters efficiently
3. **Case-insensitive comparison** using Character.toLowerCase()
4. **O(1) space solution** possible with two pointers
5. **Character.isLetterOrDigit()** handles Unicode correctly
6. **Early exit optimizations** can improve average-case performance
7. **No string modification** needed with two pointers approach
