# Solution Explanation: Best Time to Buy and Sell Stock II

## Approach: Greedy - Sum Positive Differences

### Intuition
Since unlimited transactions allowed, buy before every price increase, sell before every decrease. This equals summing all positive day-to-day differences.

### Algorithm
1. Initialize profit = 0
2. For each day from 1 to n-1:
   - Calculate difference = prices[i] - prices[i-1]
   - If difference > 0: add to profit
3. Return profit

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only variables

## Why This Works

- Any sequence of trades can be decomposed into day-to-day transactions
- Sum of positive differences equals maximum profit
- Greedy choice (capture every increase) is optimal
