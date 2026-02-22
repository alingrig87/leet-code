# Solution Explanation: Sort Array by Increasing Frequency

## Approach: HashMap + Custom Sort

### Intuition
Count frequencies, then sort by frequency (ascending) and value (descending for ties).

### Algorithm
1. Count frequencies using HashMap
2. Convert to array of entries
3. Sort with custom comparator:
   - Compare frequencies (ascending)
   - If equal, compare values (descending)
4. Build result array

### Complexity
- **Time**: O(n log n) - sorting
- **Space**: O(n) - HashMap and result

## Why Custom Sort

- Need to sort by two criteria
- Frequency first (ascending)
- Value second (descending for ties)
- Custom comparator handles this
