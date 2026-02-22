# Solution Explanation: Two Sum

## Approach 1: HashMap (One Pass) - Recommended

### Intuition
Instead of checking every pair (O(n²)), we can use a HashMap to store numbers we've seen along with their indices. For each number, we check if its complement (target - current number) exists in the map.

### Algorithm
1. Create an empty HashMap to store number → index mappings
2. Iterate through the array once
3. For each number at index i:
   - Calculate complement = target - nums[i]
   - If complement exists in HashMap, return [map.get(complement), i]
   - Otherwise, store (nums[i], i) in HashMap
4. Since problem guarantees a solution, we'll always find it

### Why This Works
- We're looking for two numbers: a + b = target
- This means: b = target - a
- As we iterate, for each 'a', we check if 'b' (complement) was seen before
- If yes, we found our pair

### Complexity
- **Time**: O(n) - Single pass
- **Space**: O(n) - HashMap stores at most n elements

## Approach 2: Two Pointers (For Sorted Array)

### Intuition
If array is sorted, we can use two pointers from both ends and move them based on sum comparison.

### Algorithm
1. Sort the array (if not already sorted)
2. Use two pointers: left = 0, right = n-1
3. While left < right:
   - Calculate sum = nums[left] + nums[right]
   - If sum == target: return [left, right]
   - If sum < target: left++ (need larger sum)
   - If sum > target: right-- (need smaller sum)

### Complexity
- **Time**: O(n log n) if sorting needed, O(n) if already sorted
- **Space**: O(1) if in-place sort, O(n) if new array

## Approach 3: Brute Force

### Intuition
Check all possible pairs.

### Algorithm
1. For each index i
2. For each index j > i
3. If nums[i] + nums[j] == target, return [i, j]

### Complexity
- **Time**: O(n²)
- **Space**: O(1)

## Comparison

| Approach | Time | Space | When to Use |
|----------|------|-------|-------------|
| HashMap | O(n) | O(n) | General case (recommended) |
| Two Pointers | O(n) | O(1) | When array is sorted |
| Brute Force | O(n²) | O(1) | Never (too slow) |
