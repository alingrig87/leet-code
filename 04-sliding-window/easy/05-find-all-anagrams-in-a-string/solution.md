# Solution Explanation: Find All Anagrams in a String

## Approach: Sliding Window

### Intuition
Use fixed-size sliding window of p.length(). Maintain frequency map, compare with p's frequency to find anagrams.

### Algorithm
1. Build frequency map of p
2. Initialize window frequency map for first p.length() characters
3. For each position:
   - Compare window frequency with p's frequency
   - If match, add start index to result
   - Slide window: remove left, add right
   - Update window frequency
4. Return result

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - fixed size arrays

## Why This Works

- Fixed window size matches anagram length
- Frequency comparison detects anagrams
- Sliding efficiently checks all positions
- Optimal O(n) solution
