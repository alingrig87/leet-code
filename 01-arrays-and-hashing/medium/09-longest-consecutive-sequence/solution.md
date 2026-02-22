# Solution Explanation: Longest Consecutive Sequence

## Approach: HashSet with Sequence Expansion

### Intuition
Convert array to HashSet for O(1) lookups. For each number, if it's the start of a sequence (number-1 doesn't exist), expand the sequence and track maximum length.

### Algorithm
1. Convert array to HashSet
2. For each number in HashSet:
   - Check if number-1 exists
   - If not, this is a sequence start
   - Expand sequence by checking number+1, number+2, etc.
   - Track maximum sequence length
3. Return maximum length

### Complexity
- **Time**: O(n) - Each number visited at most twice
- **Space**: O(n) - HashSet

## Why This is O(n)

- Each number is checked as potential sequence start: O(n)
- Each number is part of at most one sequence expansion: O(n)
- Total: O(n) not O(n²)

## Why Check Only Sequence Starts

- If we check every number, sequences are processed multiple times
- By checking only starts, each sequence processed once
- More efficient and still O(n)
