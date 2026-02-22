# Solution Explanation: Contains Duplicate

## Approach 1: HashSet (Recommended)

### Intuition
The most efficient approach is to use a HashSet to keep track of elements we've already seen. As we iterate through the array, if we encounter an element that's already in the set, we immediately know there's a duplicate.

### Algorithm
1. Create an empty HashSet
2. Iterate through each element in the array
3. For each element:
   - Check if it exists in the HashSet
   - If yes, return `true` (duplicate found)
   - If no, add it to the HashSet
4. If we finish iteration without finding duplicates, return `false`

### Complexity
- **Time**: O(n) - Single pass through the array
- **Space**: O(n) - HashSet can contain all elements in worst case

## Approach 2: Sorting

### Intuition
If we sort the array, duplicates will be adjacent to each other. We can then check adjacent pairs.

### Algorithm
1. Sort the array
2. Iterate through the array
3. Compare each element with its next neighbor
4. If any pair is equal, return `true`
5. Otherwise, return `false`

### Complexity
- **Time**: O(n log n) - Sorting dominates
- **Space**: O(1) - If using in-place sort, or O(n) if creating new array

## Approach 3: Brute Force (Not Recommended)

### Intuition
Compare every element with every other element.

### Algorithm
1. For each element at index i
2. Compare with all elements at indices j > i
3. If any match, return `true`
4. Return `false` if no matches found

### Complexity
- **Time**: O(n²) - Nested loops
- **Space**: O(1) - No extra data structures

## Why HashSet is Best

1. **Optimal Time**: O(n) is the best possible for this problem
2. **Simple Implementation**: Clean and readable code
3. **Handles All Cases**: Works with any integer values
4. **Early Exit**: Can return immediately when duplicate is found
