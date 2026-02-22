# Solution Explanation: Lemonade Change

## Approach: Greedy Change Making

### Intuition
Track count of $5 and $10 bills. For each payment, give change using greedy strategy: prefer $10+$5 for $20, preserve $5 bills.

### Algorithm
1. Initialize fiveCount = 0, tenCount = 0
2. For each bill:
   - $5: fiveCount++
   - $10: If fiveCount > 0: fiveCount--, tenCount++. Else: return false
   - $20: Prefer tenCount > 0 && fiveCount > 0: both--. Else if fiveCount >= 3: fiveCount -= 3. Else: return false
3. Return true if all processed

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only counters

## Why Greedy Works

- $5 bills are most versatile
- Using $10+$5 for $20 preserves $5 bills
- Greedy choice is optimal for this problem
