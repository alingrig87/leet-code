# Solution Explanation: Longest Common Prefix

## Approach: Vertical Scanning

### Intuition
Compare characters at same position across all strings. Stop when mismatch found.

### Algorithm
1. If empty array, return ""
2. For each character position:
   - Check if all strings have this character
   - If mismatch or end of string, stop
   - Add character to result
3. Return result

### Complexity
- **Time**: O(S) - S is sum of all characters
- **Space**: O(1) - excluding result

## Why This Works

- Common prefix means same characters at same positions
- Vertical scanning checks this efficiently
- Stops early on mismatch
