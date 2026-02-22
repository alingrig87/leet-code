# Baseball Game

## Problem Statement
You are keeping the scores for a baseball game with strange rules. The game consists of several rounds, where the scores of past rounds may affect future rounds' scores.

You are given a string array `ops`, where `ops[i]` is the `i`th operation you must apply to the record and is one of the following:
- An integer `x` - Record a new score of `x`.
- `"+"` - Record a new score that is the sum of the previous two scores.
- `"D"` - Record a new score that is the double of the previous score.
- `"C"` - Invalidate the previous score, removing it from the record.

Return the sum of all the scores on the record.

**Example 1:**
```
Input: ops = ["5","2","C","D","+"]
Output: 30
Explanation:
"5" - Add 5 to the record, record is [5].
"2" - Add 2 to the record, record is [5, 2].
"C" - Invalidate and remove the previous score, record is [5].
"D" - Add 2 * 5 = 10 to the record, record is [5, 10].
"+" - Add 5 + 10 = 15 to the record, record is [5, 10, 15].
The total sum is 5 + 10 + 15 = 30.
```

## Theory & Data Structures

### Stack Data Structure
This problem uses a **stack** to track scores. The stack's LIFO property is perfect for accessing the most recent scores.

#### Key Insight: Stack for Recent Scores
- **Push**: Add new scores to stack
- **Pop**: Remove invalidated scores (operation 'C')
- **Peek**: Access most recent scores for 'D' and '+'
- **Sum**: Calculate sum of all scores at the end

#### Building Stack Solution from Scratch (Conceptual)
```java
// Conceptual implementation of baseball game scoring
class BaseballGame {
    
    // Calculate total score
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        
        for (String op : ops) {
            if (op.equals("C")) {
                // Invalidate previous score - pop from stack
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (op.equals("D")) {
                // Double previous score - peek and push 2*top
                if (!stack.isEmpty()) {
                    int top = stack.peek();
                    stack.push(2 * top);
                }
            } else if (op.equals("+")) {
                // Sum of previous two scores
                if (stack.size() >= 2) {
                    int top = stack.pop();
                    int secondTop = stack.peek();
                    stack.push(top);  // Restore top
                    stack.push(top + secondTop);
                }
            } else {
                // Integer - parse and push
                int score = Integer.parseInt(op);
                stack.push(score);
            }
        }
        
        // Calculate sum of all scores
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        
        return sum;
    }
    
    // Alternative: Calculate sum during traversal
    public int calPointsOptimized(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        
        for (String op : ops) {
            int newScore = 0;
            
            if (op.equals("C")) {
                // Remove last score
                if (!stack.isEmpty()) {
                    sum -= stack.pop();
                }
            } else if (op.equals("D")) {
                // Double last score
                if (!stack.isEmpty()) {
                    newScore = 2 * stack.peek();
                    stack.push(newScore);
                    sum += newScore;
                }
            } else if (op.equals("+")) {
                // Sum of last two scores
                if (stack.size() >= 2) {
                    int top = stack.pop();
                    int secondTop = stack.peek();
                    newScore = top + secondTop;
                    stack.push(top);
                    stack.push(newScore);
                    sum += newScore;
                }
            } else {
                // Integer
                newScore = Integer.parseInt(op);
                stack.push(newScore);
                sum += newScore;
            }
        }
        
        return sum;
    }
}
```

### Why Stack?
- **LIFO property**: Need to access most recent scores
- **Operations**: 'C', 'D', '+' all require recent scores
- **Natural fit**: Stack operations match problem requirements

### Time & Space Complexity

#### Approach: Stack
- **Time Complexity**: O(n) - Process each operation once
  - Parse operation: O(1)
  - Stack operations: O(1) each
  - Sum calculation: O(n) at end
  - Total: O(n)
- **Space Complexity**: O(n) - Stack stores all scores
  - Worst case: All operations are integers
  - Stack size: O(n)

## Interview Simulation

### Initial Discussion

**Interviewer**: "Calculate baseball game score with special operations."

**Candidate**: "I'll use a stack to track scores. For an integer, I'll parse it and push to stack. For 'C', I'll pop from stack. For 'D', I'll peek at top, double it, and push. For '+', I'll pop top, peek at second top, then push both back and push their sum. At the end, I'll sum all scores in the stack."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For ['5','2','C','D','+'], I start with empty stack. '5': push 5, stack=[5]. '2': push 2, stack=[5,2]. 'C': pop 2, stack=[5]. 'D': peek 5, push 10, stack=[5,10]. '+': pop 10, peek 5, push 10 back, push 15, stack=[5,10,15]. Sum = 5+10+15 = 30."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we process each operation once. Space complexity is O(n) for the stack that stores all scores."

### Follow-up Questions

**Interviewer**: "What if operations are invalid (e.g., 'D' on empty stack)?"

**Candidate**: "The problem states operations are valid, but we should add checks. For 'D' and '+', we'd check if stack has enough elements before proceeding."

**Interviewer**: "Can you optimize the sum calculation?"

**Candidate**: "Yes, we can maintain a running sum. When we push, add to sum. When we pop (for 'C'), subtract from sum. This gives us O(1) sum calculation instead of O(n) at the end."

**Interviewer**: "What if we need to support undo operations?"

**Candidate**: "We could use two stacks - one for scores and one for operations. When undoing, we'd reverse the last operation. This would require more complex logic."

### Tricky Edge Cases

1. **Empty operations**: `[]` → Return `0`
2. **Multiple 'C'**: Handle correctly, pop multiple times
3. **'D' on empty**: Shouldn't happen per problem, but handle
4. **'+' with one element**: Shouldn't happen per problem
5. **All integers**: Simple case
6. **All 'C'**: Stack becomes empty
7. **Large numbers**: Handle integer overflow if needed
8. **Negative scores**: Handle if allowed

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll create a stack to store scores. I'll iterate through each operation. If it's 'C', I'll pop from stack. If it's 'D', I'll peek at top, double it, and push. If it's '+', I'll pop the top, peek at the second top, push the top back, then push their sum. Otherwise, I'll parse it as an integer and push. After processing all operations, I'll sum all scores in the stack and return the sum."

## Solution Approaches

### Approach 1: Stack (Optimal)
Use stack to track scores, apply operations. O(n) time, O(n) space.

**Algorithm:**
1. Create stack
2. For each operation:
   - 'C': pop from stack
   - 'D': push 2*peek()
   - '+': push sum of top two
   - Integer: parse and push
3. Sum all scores in stack
4. Return sum

**Advantages:**
- Simple and intuitive
- O(n) time complexity
- Natural stack usage

### Approach 2: Stack with Running Sum
Maintain running sum during operations. O(n) time, O(n) space.

**Advantages:**
- O(1) sum calculation
- More efficient

## Key Takeaways

1. **Stack** for tracking recent scores
2. **LIFO property** matches problem requirements
3. **Handle operations** as specified
4. **O(n) time, O(n) space**
5. **Running sum** optimization possible
6. **Edge cases matter** - empty stack, invalid operations
7. **Simple problem** but tests stack understanding
8. **Foundation for** more complex stack problems
