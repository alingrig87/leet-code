# Solution Explanation: Valid Anagram

## Approach 1: Array Frequency Counter (Recommended for lowercase)

### Intuition
If both strings are anagrams, they must have the same characters with the same frequencies. We can use an array to count character frequencies.

### Algorithm
1. Check if strings have same length (early exit)
2. Create frequency array of size 26 (for a-z)
3. Count characters in first string (increment)
4. Count characters in second string (decrement)
5. If any count goes negative, return false
6. Check all counts are zero

### Complexity
- **Time**: O(n) - Two passes through strings
- **Space**: O(1) - Fixed size array

## Approach 2: HashMap (General Case)

### Intuition
Use HashMap when character set is not limited to lowercase letters.

### Algorithm
1. Check lengths
2. Create HashMap for character frequencies
3. Count in first string
4. Decrement in second string
5. Verify all counts are zero

### Complexity
- **Time**: O(n)
- **Space**: O(k) where k is unique character count

## Approach 3: Sorting

### Intuition
If strings are anagrams, their sorted versions will be identical.

### Algorithm
1. Convert strings to char arrays
2. Sort both arrays
3. Compare sorted arrays

### Complexity
- **Time**: O(n log n) - Sorting
- **Space**: O(n) - Sorted arrays

## Why Array is Best (for lowercase)

1. **O(1) space** vs O(k) for HashMap
2. **Faster access** - array indexing vs hash lookup
3. **Simple implementation** - no hash collisions
4. **Cache friendly** - contiguous memory
