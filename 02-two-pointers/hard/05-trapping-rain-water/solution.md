# Solution Explanation: Trapping Rain Water

## Approach: Two Pointers

### Intuition
Use two pointers from ends. Track max heights from left and right. Water at position = min(maxLeft, maxRight) - height[i].

### Algorithm
1. Initialize left = 0, right = n-1, maxLeft = 0, maxRight = 0
2. While left < right:
   - If height[left] < height[right]:
     - Update maxLeft
     - Add water at left: maxLeft - height[left]
     - left++
   - Else:
     - Update maxRight
     - Add water at right: maxRight - height[right]
     - right--
3. Return total water

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only variables

## Why This Works

- Water trapped limited by smaller of two max heights
- Processing smaller side ensures we have correct max
- Each position processed once
- Optimal O(n) solution
