# Solution Explanation: Assign Cookies

## Approach: Greedy with Sorting

### Intuition
Sort both arrays. Match smallest cookie to smallest greed it can satisfy. This maximizes number of satisfied children.

### Algorithm
1. Sort children by greed factor (ascending)
2. Sort cookies by size (ascending)
3. Use two pointers:
   - For each cookie, find smallest child it can satisfy
   - If found, assign and move both pointers
   - Otherwise, move cookie pointer only
4. Return count of assignments

### Complexity
- **Time**: O(n log n + m log m) - sorting
- **Space**: O(1) - only pointers

## Why This Works

- Using smallest cookie that works leaves larger cookies for higher greed
- Greedy choice maximizes number of satisfied children
- Optimal solution
