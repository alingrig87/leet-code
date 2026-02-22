# Valid Sudoku

## Problem Statement
Determine if a `9 x 9` Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

1. Each row must contain the digits `1-9` without repetition.
2. Each column must contain the digits `1-9` without repetition.
3. Each of the nine `3 x 3` sub-boxes of the grid must contain the digits `1-9` without repetition.

**Note:**
- A Sudoku board (partially filled) could be valid but is not necessarily solvable.
- Only the filled cells need to be validated.

**Example:**
```
Input: board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true
```

## Theory & Data Structures

### HashSet for Validation
Use HashSet to track seen digits in rows, columns, and 3x3 boxes.

### Array of HashSets
Maintain separate HashSet for each row, column, and box to check for duplicates.

### String Encoding for Boxes
Encode box position as string like "row/3,col/3" to identify which 3x3 box a cell belongs to.

### Time & Space Complexity

#### Approach: HashSet Validation
- **Time Complexity**: O(1) - Fixed 9x9 board, so O(81) = O(1)
- **Space Complexity**: O(1) - Fixed number of HashSets

## Interview Simulation

### Initial Discussion

**Interviewer**: "Validate a Sudoku board."

**Candidate**: "I'll use HashSets to track seen digits in each row, column, and 3x3 box. For each cell, I'll check if the digit already exists in its row, column, or box HashSet. If yes, it's invalid."

**Interviewer**: "How do you identify which 3x3 box a cell belongs to?"

**Candidate**: "Box index = (row / 3) * 3 + (col / 3). This gives us boxes 0-8."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(1) since the board is fixed 9x9. We process 81 cells, each with O(1) HashSet operations."

### Follow-up Questions

**Interviewer**: "What if the board was n x n?"

**Candidate**: "Then it would be O(n²) time and O(n²) space. We'd need to generalize the box calculation."

**Interviewer**: "Can you optimize space?"

**Candidate**: "We could use bit masks instead of HashSets - use an integer where each bit represents a digit. This reduces space but code is more complex."

### Tricky Edge Cases

1. **Empty cells**: Represented by '.' - skip validation
2. **Invalid digits**: Characters other than '1'-'9' or '.'
3. **Duplicate in row**: Same digit appears twice in same row
4. **Duplicate in column**: Same digit appears twice in same column
5. **Duplicate in box**: Same digit appears twice in same 3x3 box

## Solution Approaches

### Approach: HashSet Validation
Use HashSet for each row, column, and box. Check duplicates. O(1) time, O(1) space.

## Key Takeaways

1. **HashSet for duplicate detection** is key
2. **Box calculation** using integer division
3. **Three separate checks** for row, column, box
4. **Skip empty cells** ('.')
