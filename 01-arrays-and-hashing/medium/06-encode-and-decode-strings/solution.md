# Solution Explanation: Encode and Decode Strings

## Approach: Length Prefix

### Intuition
Encode each string as "length#string". When decoding, read length, then read that many characters.

### Algorithm
**Encode:**
1. For each string, append "length#string" to result

**Decode:**
1. Read until '#', get length
2. Read next 'length' characters as string
3. Repeat until end

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(n) - encoded string

## Why This Works

- Length prefix tells exactly how many characters to read
- Handles strings with any characters including '#'
- Simple and efficient encoding scheme
