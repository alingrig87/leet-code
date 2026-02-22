# Valid Parentheses

## Problem Statement
Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:
1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

**Example 1:**
```
Input: s = "()"
Output: true
```

**Example 2:**
```
Input: s = "()[]{}"
Output: true
```

**Example 3:**
```
Input: s = "(]"
Output: false
```

**Example 4:**
```
Input: s = "([)]"
Output: false
```

## Theory & Data Structures

### Stack Data Structure
A **Stack** is a Last-In-First-Out (LIFO) data structure. It supports two main operations:
- **Push**: Add an element to the top
- **Pop**: Remove and return the top element
- **Peek/Top**: View the top element without removing it

#### How Stack Works for This Problem
1. **Opening brackets**: Push to stack (we'll need to match them later)
2. **Closing brackets**: Check if stack is empty or top doesn't match
3. **Matching**: If closing bracket matches top, pop from stack
4. **Final check**: Stack should be empty if all brackets matched

#### Building Stack from Scratch (Conceptual)
```java
// Conceptual implementation of stack for parentheses matching
class ParenthesesValidator {
    
    // Validate parentheses using stack
    public boolean isValid(String s) {
        // Stack to store opening brackets
        Stack<Character> stack = new Stack<>();
        
        // Map closing brackets to their corresponding opening brackets
        // This makes matching easier
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        
        // Iterate through each character
        for (char c : s.toCharArray()) {
            if (map.containsValue(c)) {
                // c is an opening bracket (value in map)
                // Push to stack
                stack.push(c);
            } else if (map.containsKey(c)) {
                // c is a closing bracket (key in map)
                // Check if stack is empty or doesn't match
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false;
                }
            }
            // If c is not a bracket, we can ignore or handle as needed
        }
        
        // Stack should be empty if all brackets matched
        return stack.isEmpty();
    }
    
    // Alternative: Without HashMap (more explicit)
    public boolean isValidExplicit(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                // Opening bracket - push to stack
                stack.push(c);
            } else {
                // Closing bracket - check if matches
                if (stack.isEmpty()) {
                    return false;  // No opening bracket to match
                }
                
                char top = stack.pop();
                
                // Check if closing bracket matches opening bracket
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;  // Mismatch
                }
            }
        }
        
        // All brackets matched if stack is empty
        return stack.isEmpty();
    }
}
```

### Why Stack?
- **LIFO property**: The last opening bracket must match the first closing bracket
- **Nested brackets**: Stack naturally handles nested structures
- **Order matters**: Stack preserves the order of opening brackets

### Time & Space Complexity

#### Approach: Stack
- **Time Complexity**: O(n) - Single pass through string
  - Best case: O(1) - First character is invalid closing bracket
  - Average case: O(n)
  - Worst case: O(n) - Process entire string
- **Space Complexity**: O(n) - Stack can contain all opening brackets
  - Best case: O(1) - All closing brackets (early exit)
  - Average case: O(n/2) - Half opening, half closing
  - Worst case: O(n) - All opening brackets

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if a string of parentheses is valid."

**Candidate**: "I'll use a stack to track opening brackets. When I encounter an opening bracket, I'll push it to the stack. When I encounter a closing bracket, I'll check if the stack is empty or if the top doesn't match. If it matches, I'll pop from the stack. At the end, the stack should be empty if all brackets are properly matched."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For '()[]{}', I start with empty stack. '(' is opening, push. ')' is closing, matches top '(', pop. Stack empty. '[' is opening, push. ']' is closing, matches top '[', pop. Stack empty. '{' is opening, push. '}' is closing, matches top '{', pop. Stack empty. Return true."

**Interviewer**: "What about '([)]'?"

**Candidate**: "For '([)]', '(' is opening, push. '[' is opening, push. ')' is closing, but top is '[', not '(', so mismatch. Return false."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we make a single pass through the string. Space complexity is O(n) in the worst case when all characters are opening brackets."

### Follow-up Questions

**Interviewer**: "Can you do it with O(1) space?"

**Candidate**: "Not for the general case with multiple bracket types. However, if there's only one type of parentheses (e.g., only '(' and ')'), we can use a counter instead of a stack. We increment for opening and decrement for closing. If counter goes negative or isn't zero at the end, it's invalid."

**Interviewer**: "What if we need to find the longest valid parentheses substring?"

**Candidate**: "That's a different problem. We'd use dynamic programming or a stack-based approach that tracks positions. The stack would store indices instead of characters, and we'd calculate the length of valid substrings."

**Interviewer**: "What if brackets can be nested to any depth?"

**Candidate**: "The stack approach handles this naturally. Each opening bracket is pushed, and when we encounter a closing bracket, we match it with the most recent opening bracket (top of stack). This works for any nesting depth."

**Interviewer**: "Can you optimize this further?"

**Candidate**: "The stack approach is already optimal for time. For space, we could use early exit optimizations - if we encounter a closing bracket when the stack is empty, we can return false immediately. Also, if the string length is odd, it can't be valid, so we can return false early."

### Tricky Edge Cases

1. **Empty string**: `""` → Return `true` (no brackets, considered valid)
2. **Only opening**: `"((("` → Return `false` (stack not empty)
3. **Only closing**: `"))"` → Return `false` (stack empty when closing)
4. **Mismatched types**: `"(]"` → Return `false` (different types)
5. **Wrong order**: `"([)]"` → Return `false` (brackets not properly nested)
6. **Correct nesting**: `"([{}])"` → Return `true`
7. **Single pair**: `"()"` → Return `true`
8. **Multiple types**: `"()[]{}"` → Return `true`
9. **Nested same type**: `"((()))"` → Return `true`
10. **Mixed nesting**: `"([{()}])"` → Return `true`
11. **Unmatched opening**: `"((()"` → Return `false`
12. **Unmatched closing**: `"())"` → Return `false`

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll create a stack to store opening brackets. I'll also create a HashMap that maps closing brackets to their corresponding opening brackets - this makes matching easier. I'll iterate through each character in the string. If it's an opening bracket (a value in the map), I'll push it to the stack. If it's a closing bracket (a key in the map), I'll check if the stack is empty or if the popped element doesn't match the expected opening bracket. If either condition is true, I'll return false. After processing all characters, I'll return whether the stack is empty - if it is, all brackets matched correctly."

**Interviewer**: "Why use a HashMap for matching?"

**Candidate**: "The HashMap makes the code cleaner and more maintainable. Instead of having multiple if-else statements to check each bracket type, we can simply look up the expected opening bracket. It also makes it easier to extend if we need to support more bracket types in the future."

**Interviewer**: "What if we don't use a HashMap?"

**Candidate**: "We can use explicit if-else statements to check each bracket type. For closing brackets, we'd check if the popped character matches: if c is ')', check if top is '(', etc. This works but is more verbose and harder to maintain."

## Solution Approaches

### Approach 1: Stack with HashMap (Recommended)
Use stack to track opening brackets, HashMap for matching. O(n) time, O(n) space.

**Algorithm:**
1. Create stack and HashMap (closing -> opening)
2. For each character:
   - If opening: push to stack
   - If closing: check if stack empty or doesn't match, return false if so
3. Return stack.isEmpty()

**Advantages:**
- Clean and readable
- Easy to extend
- Optimal time complexity

### Approach 2: Stack without HashMap
Use explicit if-else for matching. O(n) time, O(n) space.

**Advantages:**
- No HashMap overhead
- More explicit

**Disadvantages:**
- More verbose
- Harder to maintain

### Approach 3: Counter (Only for Single Type)
Use counter for single bracket type. O(n) time, O(1) space.

**Limitations:**
- Only works for one bracket type
- Not applicable to this problem

## Key Takeaways

1. **Stack** is perfect for matching problems
2. **Push opening** brackets, **pop on match**
3. **Check stack empty** when encountering closing bracket
4. **Final check**: stack should be empty
5. **O(n) time, O(n) space** - optimal for general case
6. **HashMap** simplifies matching logic
7. **Early exit** optimizations possible
8. **Handles nested** brackets naturally
9. **Classic stack problem** - fundamental to know
10. **Foundation for** more complex bracket problems
