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
```

## Theory & Data Structures

### Stack
Use stack to track scores. Apply operations: push integer, pop on 'C', double top on 'D', sum top two on '+'.

### Time & Space Complexity

#### Approach: Stack
- **Time Complexity**: O(n) - Process each operation
- **Space Complexity**: O(n) - Stack storage

## Interview Simulation

### Initial Discussion

**Interviewer**: "Calculate baseball game score with special operations."

**Candidate**: "I'll use a stack. For integer, push it. For 'C', pop. For 'D', push 2*top. For '+', push sum of top two."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n) time and O(n) space for the stack."

### Follow-up Questions

**Interviewer**: "What if operations are invalid?"

**Candidate**: "Problem assumes valid operations, but we should handle edge cases like empty stack."

### Tricky Edge Cases

1. **Empty operations**: Return 0
2. **Multiple 'C'**: Handle correctly
3. **'D' on empty**: Shouldn't happen per problem
4. **'+' with one element**: Shouldn't happen per problem

## Solution Approaches

### Approach: Stack (Optimal)
Use stack to track scores, apply operations. O(n) time, O(n) space.

## Key Takeaways

1. **Stack** for score tracking
2. **Apply operations** as specified
3. **Handle edge cases**
4. **Sum at end**
