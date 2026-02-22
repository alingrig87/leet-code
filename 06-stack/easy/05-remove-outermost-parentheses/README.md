# Remove Outermost Parentheses

## Problem Statement
A valid parentheses string is either empty `""`, `"(" + A + ")"`, or `A + B`, where `A` and `B` are valid parentheses strings, and `+` represents string concatenation.

The outermost parentheses of a valid parentheses string can be removed if it is of the form `"(" + A + ")"`.

Given a valid parentheses string `s`, remove the outermost parentheses of every primitive string in it and return the result string.

**Example 1:**
```
Input: s = "(()())(())"
Output: "()()()"
Explanation: 
The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
```

**Example 2:**
```
Input: s = "()()"
Output: ""
Explanation: 
The input string is "()()", with primitive decomposition "()" + "()".
After removing outer parentheses of each part, this is "" + "" = "".
```

## Theory & Data Structures

### Counter Approach
This problem uses a **counter** to track the depth of parentheses. When depth is 0, we're at the outermost level and should skip those parentheses.

#### Key Insight: Depth Tracking
- **Counter**: Tracks current depth (number of open parentheses)
- **Depth 0**: We're at outermost level - skip these parentheses
- **Depth > 0**: We're inside - include these parentheses
- **Primitive strings**: Separated by depth returning to 0

#### Building Counter Solution from Scratch (Conceptual)
```java
// Conceptual implementation of outermost parentheses removal
class OutermostRemover {
    
    // Remove outermost parentheses using counter
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If depth > 0, we're not at outermost, include it
                if (depth > 0) {
                    result.append(c);
                }
                depth++;  // Increase depth
            } else {  // c == ')'
                depth--;  // Decrease depth first
                // If depth > 0, we're not at outermost, include it
                if (depth > 0) {
                    result.append(c);
                }
            }
        }
        
        return result.toString();
    }
    
    // Why check depth > 0?
    // - When depth is 0, we're at outermost level
    // - We skip outermost '(' (when depth becomes 1) and ')' (when depth becomes 0)
    // - For '(', we check before incrementing
    // - For ')', we check after decrementing
    
    // Alternative: More explicit version
    public String removeOuterParenthesesExplicit(String s) {
        StringBuilder sb = new StringBuilder();
        int openCount = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Only append if not outermost
                if (openCount > 0) {
                    sb.append(c);
                }
                openCount++;
            } else {
                openCount--;
                // Only append if not outermost
                if (openCount > 0) {
                    sb.append(c);
                }
            }
        }
        
        return sb.toString();
    }
}
```

### Time & Space Complexity

#### Approach: Counter
- **Time Complexity**: O(n) - Single pass through string
  - Best case: O(n)
  - Average case: O(n)
  - Worst case: O(n)
- **Space Complexity**: O(n) - StringBuilder for result
  - Result string can be up to n characters
  - O(n) space needed for output

## Interview Simulation

### Initial Discussion

**Interviewer**: "Remove outermost parentheses from each primitive string."

**Candidate**: "I'll use a counter to track the depth of parentheses. When I encounter '(', if depth is greater than 0, I'll include it (not outermost). I'll increment depth. When I encounter ')', I'll decrement depth first, then if depth is greater than 0, I'll include it. This way, I skip parentheses at depth 0, which are the outermost."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For '(()())(())', I start with depth=0. '(': depth=0, skip, depth=1. '(': depth>0, include, depth=2. ')': depth--, depth=1>0, include. '(': depth>0, include, depth=2. ')': depth--, depth=1>0, include. ')': depth--, depth=0, skip. '(': depth=0, skip, depth=1. '(': depth>0, include, depth=2. ')': depth--, depth=1>0, include. ')': depth--, depth=0, skip. Result: '()()()'."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we make a single pass through the string. Space complexity is O(n) for the result string, which is necessary since we need to return a new string."

### Follow-up Questions

**Interviewer**: "What if the string is invalid?"

**Candidate**: "The problem states the string is valid, but if we needed to handle invalid strings, we'd add validation - the counter should end at 0, and should never go negative."

**Interviewer**: "Can you do this in-place?"

**Candidate**: "Not easily, since we're removing characters. We'd need to shift characters, which would be O(n²). Using StringBuilder is more efficient."

### Tricky Edge Cases

1. **Single primitive**: `"()"` → Return `""`
2. **Multiple primitives**: `"()()"` → Return `""`
3. **Nested**: `"(()())"` → Return `"()()"`
4. **Empty**: `""` → Return `""`
5. **Deep nesting**: Handle correctly

## Solution Approaches

### Approach: Counter (Optimal)
Track depth, skip outermost. O(n) time, O(n) space.

**Algorithm:**
1. Initialize depth = 0, StringBuilder
2. For each character:
   - If '(', check depth > 0 before appending, increment depth
   - If ')', decrement depth, check depth > 0 before appending
3. Return result

**Advantages:**
- O(n) time complexity
- Simple and efficient
- Optimal solution

## Key Takeaways

1. **Counter** for depth tracking
2. **Skip outermost** parentheses (depth = 0)
3. **O(n) time, O(n) space**
4. **Simple traversal** with counter
5. **Foundation for** more complex parentheses problems
