# Solution Explanation: Two Sum II

## Approach: Two Pointers

### Intuition
Since array is sorted, use two pointers from ends. Adjust pointers based on sum comparison with target.

### Algorithm
1. Initialize left = 0, right = n-1
2. While left < right:
   - Calculate sum = numbers[left] + numbers[right]
   - If sum == target: return [left+1, right+1]
   - If sum < target: left++ (need larger sum)
   - If sum > target: right-- (need smaller sum)
3. Return empty if no solution

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only pointers

## Why This Works

- Sorted array means moving left increases sum
- Moving right decreases sum
- Pointers converge to solution
- Guaranteed to find if solution exists
