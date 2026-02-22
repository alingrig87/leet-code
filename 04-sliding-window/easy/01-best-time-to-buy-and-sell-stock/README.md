# Best Time to Buy and Sell Stock

## Problem Statement
You are given an array `prices` where `prices[i]` is the price of a given stock on the `i`th day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return `0`.

**Example 1:**
```
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
```

## Theory & Data Structures

### One Pass with Min Price
Track minimum price seen so far. For each day, calculate profit if selling today, update maximum profit.

### Time & Space Complexity

#### Approach: One Pass
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Only variables

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find maximum profit from buying and selling stock once."

**Candidate**: "I'll track minimum price seen so far. For each day, calculate profit if selling today (price - minPrice), and update maximum profit."

**Interviewer**: "Why track minimum?"

**Candidate**: "To maximize profit, we want to buy at lowest price before selling. Tracking minimum ensures we always consider the best buy price."

### Follow-up Questions

**Interviewer**: "What if we can buy and sell multiple times?"

**Candidate**: "Then we'd use greedy - buy before every price increase, sell before every price decrease."

### Tricky Edge Cases

1. **No profit possible**: All decreasing → Return 0
2. **Single day**: Return 0
3. **All same price**: Return 0
4. **Best at end**: Buy early, sell late

## Solution Approaches

### Approach: One Pass (Optimal)
Track min price, calculate profit each day. O(n) time, O(1) space.

## Key Takeaways

1. **Track minimum** price seen
2. **Calculate profit** at each step
3. **O(n) time, O(1) space**
4. **Simple one pass** solution
