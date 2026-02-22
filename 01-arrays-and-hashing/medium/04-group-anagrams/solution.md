# Solution Explanation: Group Anagrams

## Approach 1: HashMap with Sorted Key

### Intuition
Anagrams have the same characters. If we sort the characters, all anagrams will have the same sorted string, which we can use as a key.

### Algorithm
1. Create HashMap: key = sorted string, value = list of anagrams
2. For each string:
   - Sort its characters
   - Use sorted string as key
   - Add original string to list at that key
3. Return all values from HashMap

### Complexity
- **Time**: O(n * k log k) - sort each string
- **Space**: O(n * k) - store all strings

## Approach 2: HashMap with Frequency String

### Intuition
Instead of sorting, count character frequencies and create a unique key.

### Algorithm
1. For each string, count character frequencies
2. Create key like "a2b1c3" (character + count)
3. Group strings with same frequency key

### Complexity
- **Time**: O(n * k) - count characters
- **Space**: O(n * k)

## Why Frequency String Can Be Better

- O(n * k) vs O(n * k log k) time
- No sorting needed
- More efficient for long strings
