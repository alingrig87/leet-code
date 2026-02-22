# Best Time to Buy and Sell Stock II

## Problem Statement
You are given an integer array `prices` where `prices[i]` is the price of a given stock on the `i`th day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.

**Example 1:**
```
Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
```

**Example 2:**
```
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
```

## Theory & Data Structures

### Greedy Algorithm - Peak Valley Approach
The key insight: **Buy before every price increase, sell before every price decrease.**

Since we can make multiple transactions, we should capture every price increase. The profit from buying at valley and selling at peak equals the sum of all individual day-to-day increases.

### Mathematical Proof
If prices are: `[a, b, c, d]` where `a < b < c < d`
- Strategy 1: Buy at a, sell at d → Profit = d - a
- Strategy 2: Buy at a, sell at b, buy at b, sell at c, buy at c, sell at d
  - Profit = (b-a) + (c-b) + (d-c) = d - a (same!)

Therefore, we can simply sum all positive day-to-day differences.

### Time & Space Complexity

#### Approach: Greedy (Sum Positive Differences)
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Only variables

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find maximum profit with unlimited transactions."

**Candidate**: "Since we can make multiple transactions, the optimal strategy is to buy before every price increase and sell before every decrease. This means I can simply sum all positive day-to-day price differences."

**Interviewer**: "Why does this work?"

**Candidate**: "If prices go from a to d with intermediate values b and c, buying at a and selling at d gives the same profit as buying at a, selling at b, buying at b, selling at c, etc. So I can capture every increase individually."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n) time and O(1) space - just one pass summing positive differences."

### Follow-up Questions

**Interviewer**: "What if there's a transaction fee?"

**Candidate**: "Then we'd need dynamic programming. We'd track two states: holding stock or not, and decide whether to buy/sell/hold at each day considering the fee."

**Interviewer**: "What if there's a cooldown period?"

**Candidate**: "Also DP. We'd track states: can buy, just sold (cooldown), holding. More complex state machine."

### Tricky Edge Cases

1. **All increasing**: Sum all differences
2. **All decreasing**: Return 0 (no profit)
3. **Flat prices**: Return 0
4. **Single day**: Return 0
5. **Alternating**: Capture each increase

## Solution Approaches

### Approach: Greedy (Optimal)
Sum all positive day-to-day differences. O(n) time, O(1) space.

## Key Takeaways

1. **Greedy approach** - capture every increase
2. **Sum positive differences** is equivalent to optimal trading
3. **O(n) time, O(1) space** - very efficient
4. **Simple one-liner** solution possible
