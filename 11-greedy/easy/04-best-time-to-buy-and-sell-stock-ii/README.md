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
The key insight: **Buy before every price increase, sell before every price decrease.** Since we can make multiple transactions, we should capture every price increase.

#### Key Insight: Sum All Positive Differences
- **Multiple transactions allowed**: We can buy and sell multiple times
- **Optimal strategy**: Capture every price increase
- **Mathematical proof**: Buying at valley and selling at peak equals sum of all day-to-day increases
- **Simple solution**: Sum all positive day-to-day differences

#### Mathematical Proof
If prices are: `[a, b, c, d]` where `a < b < c < d`
- **Strategy 1**: Buy at a, sell at d → Profit = d - a
- **Strategy 2**: Buy at a, sell at b, buy at b, sell at c, buy at c, sell at d
  - Profit = (b-a) + (c-b) + (d-c) = d - a (same!)

Therefore, we can simply sum all positive day-to-day differences.

#### Building Greedy Solution from Scratch (Conceptual)
```java
// Conceptual implementation of stock trading II
class StockTraderII {
    
    // Greedy approach: sum all positive differences
    public int maxProfit(int[] prices) {
        int profit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            // If price increased, add to profit
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        
        return profit;
    }
    
    // Why does this work?
    // - We can make unlimited transactions
    // - Optimal is to capture every price increase
    // - Sum of all increases equals maximum profit
    
    // Alternative: Peak-valley approach (same result)
    public int maxProfitPeakValley(int[] prices) {
        int i = 0;
        int profit = 0;
        
        while (i < prices.length - 1) {
            // Find valley (local minimum)
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            int valley = prices[i];
            
            // Find peak (local maximum)
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            int peak = prices[i];
            
            profit += peak - valley;
        }
        
        return profit;
    }
}
```

### Time & Space Complexity

#### Approach: Greedy (Sum Positive Differences)
- **Time Complexity**: O(n) - Single pass through array
  - Check each day once
  - O(1) work per day
- **Space Complexity**: O(1) - Only variables
  - Single profit counter
  - Constant space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find maximum profit with unlimited transactions."

**Candidate**: "Since we can make multiple transactions, the optimal strategy is to buy before every price increase and sell before every decrease. This means I can simply sum all positive day-to-day price differences."

**Interviewer**: "Why does this work?"

**Candidate**: "If prices go from a to d with intermediate values b and c where a < b < c < d, buying at a and selling at d gives profit d-a. But I can also buy at a, sell at b, buy at b, sell at c, buy at c, sell at d, which gives (b-a)+(c-b)+(d-c) = d-a, the same profit. So capturing every increase individually is optimal."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For [7,1,5,3,6,4], differences: -6, +4, -2, +3, -2. Sum positive: 4+3=7. This means buy at 1, sell at 5 (profit 4), buy at 3, sell at 6 (profit 3), total 7."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n) since we make one pass through the array. Space complexity is O(1) since we only use a profit counter."

### Follow-up Questions

**Interviewer**: "What if there's a transaction fee?"

**Candidate**: "Then we'd need dynamic programming. We'd track two states: holding stock or not holding stock. At each day, we decide whether to buy, sell, or hold, considering the fee. The greedy approach no longer works because fees make it suboptimal to trade on every small increase."

**Interviewer**: "What if there's a cooldown period after selling?"

**Candidate**: "Also requires DP. We'd track states: can buy, just sold (in cooldown), holding. It becomes a state machine problem."

**Interviewer**: "What if we can only make k transactions?"

**Candidate**: "Then we'd use DP with state [day][transactions][holding]. It's a 3D DP problem."

### Tricky Edge Cases

1. **All increasing**: Sum all differences → Maximum profit
2. **All decreasing**: Return 0 (no profit possible)
3. **Flat prices**: Return 0
4. **Single day**: Return 0 (can't sell)
5. **Alternating**: Capture each increase
6. **Large price swings**: Handle correctly

## Solution Approaches

### Approach: Greedy (Optimal)
Sum all positive day-to-day differences. O(n) time, O(1) space.

**Algorithm:**
1. Initialize profit = 0
2. For i from 1 to n-1:
   - If prices[i] > prices[i-1]: add difference to profit
3. Return profit

**Advantages:**
- O(n) time complexity
- O(1) space complexity
- Simple one-liner solution
- Optimal solution

## Key Takeaways

1. **Greedy approach** - capture every increase
2. **Sum positive differences** equals optimal trading
3. **O(n) time, O(1) space** - very efficient
4. **Simple one-liner** solution possible
5. **Foundation for** more complex stock trading problems
6. **Unlimited transactions** simplifies the problem
