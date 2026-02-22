# Solution Explanation: Best Time to Buy and Sell Stock

## Approach: One Pass

### Intuition
Track minimum price seen so far. For each day, calculate profit if selling today, update maximum.

### Algorithm
1. Initialize minPrice = prices[0], maxProfit = 0
2. For each price:
   - Update minPrice = min(minPrice, price)
   - Calculate profit = price - minPrice
   - Update maxProfit = max(maxProfit, profit)
3. Return maxProfit

### Complexity
- **Time**: O(n) - single pass
- **Space**: O(1) - only variables

## Why This Works

- We want to buy at minimum price before selling
- Tracking minimum ensures best buy price
- Calculating profit each day finds best sell day
- One pass is sufficient
