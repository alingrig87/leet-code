# Solution Explanation: Reverse Words in a String

## Approach: Split and Reverse

### Intuition
Split string into words, reverse the array, join with single space.

### Algorithm
1. Trim and split string into words
2. Filter out empty strings (from multiple spaces)
3. Reverse array of words
4. Join with single space

### Complexity
- **Time**: O(n) - two passes
- **Space**: O(n) - store words

## Why This Works

- Split gives us words
- Reversing array reverses word order
- Joining creates result string
- Simple and efficient
