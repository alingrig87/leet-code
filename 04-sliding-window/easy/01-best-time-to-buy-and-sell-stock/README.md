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
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
```

**Example 2:**
```
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
```

## Theory & Data Structures

### One Pass with Minimum Price Tracking
This problem uses a **greedy approach** with a single pass through the array. The key insight is to track the minimum price seen so far and calculate the maximum profit at each step.

#### Key Insight: Track Minimum Buy Price
- **Goal**: Maximize profit = sell price - buy price
- **Constraint**: Must buy before selling
- **Strategy**: For each day, consider selling today. To maximize profit, we should have bought at the minimum price seen so far.
- **Algorithm**: Track minimum price, calculate profit if selling today, update maximum profit.

#### Building One Pass Solution from Scratch (Conceptual)
```java
// Conceptual implementation of one pass solution
class StockProfitMaximizer {
    private int[] prices;
    
    StockProfitMaximizer(int[] prices) {
        this.prices = prices;
    }
    
    // Find maximum profit with one transaction
    public int maxProfit(int[] prices) {
        // Edge case: need at least 2 days to make a transaction
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        // Track minimum price seen so far
        // This is the best buy price we've encountered
        int minPrice = prices[0];
        
        // Track maximum profit seen so far
        int maxProfit = 0;
        
        // Iterate through prices starting from day 1
        // Day 0 is used as initial minPrice
        for (int i = 1; i < prices.length; i++) {
            // Calculate profit if we sell today
            // We would have bought at minPrice (best buy price so far)
            int profit = prices[i] - minPrice;
            
            // Update maximum profit if current profit is better
            maxProfit = Math.max(maxProfit, profit);
            
            // Update minimum price if today's price is lower
            // This ensures we always consider the best buy price
            minPrice = Math.min(minPrice, prices[i]);
        }
        
        return maxProfit;
    }
    
    // Alternative: More explicit version
    public int maxProfitExplicit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int price : prices) {
            // Update minimum price
            if (price < minPrice) {
                minPrice = price;
            }
            
            // Calculate profit if selling at current price
            int profit = price - minPrice;
            
            // Update maximum profit
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }
        
        return maxProfit;
    }
}
```

### Why This Works
1. **Greedy Choice**: At each day, we make the locally optimal choice (buy at minimum price seen so far)
2. **Optimal Substructure**: The maximum profit up to day i depends on the minimum price before day i
3. **Single Pass**: We only need to look at each price once

### Time & Space Complexity

#### Approach: One Pass
- **Time Complexity**: O(n) - Single pass through array
  - Best case: O(n)
  - Average case: O(n)
  - Worst case: O(n)
- **Space Complexity**: O(1) - Only using variables (minPrice, maxProfit)
  - No additional data structures needed
  - Constant space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find the maximum profit from buying and selling stock once."

**Candidate**: "I'll track the minimum price seen so far. For each day, I'll calculate the profit if selling today (current price - minimum price), and update the maximum profit. This ensures we always consider the best buy price when evaluating each sell opportunity."

**Interviewer**: "Why track the minimum price?"

**Candidate**: "To maximize profit, we want to buy at the lowest price before selling. By tracking the minimum price seen so far, we ensure that when we consider selling on any day, we're using the best possible buy price."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For [7,1,5,3,6,4], I start with minPrice=7, maxProfit=0. Day 1: price=1, minPrice=1, profit=0, maxProfit=0. Day 2: price=5, minPrice=1, profit=4, maxProfit=4. Day 3: price=3, minPrice=1, profit=2, maxProfit=4. Day 4: price=6, minPrice=1, profit=5, maxProfit=5. Day 5: price=4, minPrice=1, profit=3, maxProfit=5. Return 5."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we make a single pass through the array. Space complexity is O(1) since we only use a few variables."

### Follow-up Questions

**Interviewer**: "What if we can buy and sell multiple times?"

**Candidate**: "Then we'd use a greedy approach - buy before every price increase and sell before every price decrease. We'd sum up all positive price differences between consecutive days."

**Interviewer**: "What if we can make at most k transactions?"

**Candidate**: "That's a dynamic programming problem. We'd use DP with state [day][transactions][holding], where holding indicates if we own stock. The state transition would consider buying, selling, or holding."

**Interviewer**: "What if there's a transaction fee?"

**Candidate**: "We'd subtract the fee from each profit calculation. The algorithm structure remains similar, but we account for the fee when calculating profit."

**Interviewer**: "What if there's a cooldown period after selling?"

**Candidate**: "We'd need to modify the DP approach to skip a day after selling. The state would need to track whether we're in a cooldown period."

**Interviewer**: "Can you optimize this further?"

**Candidate**: "The one-pass approach is already optimal - O(n) time and O(1) space. We can't do better than this since we need to examine each price at least once."

### Tricky Edge Cases

1. **No profit possible**: `[7,6,4,3,1]` → Return `0` (all decreasing)
2. **Single day**: `[5]` → Return `0` (can't buy and sell)
3. **Two days, profit**: `[1,5]` → Return `4`
4. **Two days, loss**: `[5,1]` → Return `0`
5. **All same price**: `[5,5,5]` → Return `0` (no profit)
6. **Best at end**: `[1,2,3,4,5]` → Return `4` (buy first, sell last)
7. **Best in middle**: `[7,1,5,3,6,4]` → Return `5`
8. **Price drops then rises**: `[3,2,1,5]` → Return `4`
9. **Multiple peaks**: `[1,5,3,6,4]` → Return `5` (buy at 1, sell at 6)
10. **Large numbers**: Works with any integer prices
11. **Empty array**: Return `0`
12. **Single transaction optimal**: Algorithm handles this correctly

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll first handle edge cases - if the array is null or has less than 2 elements, return 0 (need at least 2 days for a transaction). I'll initialize minPrice to the first price and maxProfit to 0. Then I'll iterate through the array starting from index 1. For each price, I'll calculate the profit if selling today (price - minPrice). I'll update maxProfit if this profit is better. Then I'll update minPrice to be the minimum of current minPrice and today's price. After the loop, I'll return maxProfit."

**Interviewer**: "Why update minPrice after calculating profit?"

**Candidate**: "Actually, we can update minPrice either before or after calculating profit, as long as we're consistent. The key is that when we calculate profit for day i, we use the minimum price from days 0 to i-1. So we should update minPrice after using it for the current day's profit calculation, or we can update it at the end of the iteration. Both approaches work correctly."

**Interviewer**: "What if prices can be negative?"

**Candidate**: "The problem states prices are positive, but if they could be negative, the algorithm would still work. We'd just need to be careful about initialization - we might want to start with minPrice as the first price rather than Integer.MAX_VALUE."

## Solution Approaches

### Approach 1: One Pass with Min Price Tracking (Optimal)
Track minimum price, calculate profit each day. O(n) time, O(1) space.

**Algorithm:**
1. Handle edge cases (null, length < 2)
2. Initialize minPrice = prices[0], maxProfit = 0
3. For each price from index 1:
   - Calculate profit = price - minPrice
   - Update maxProfit = max(maxProfit, profit)
   - Update minPrice = min(minPrice, price)
4. Return maxProfit

**Advantages:**
- O(n) time complexity
- O(1) space complexity
- Single pass through array
- Optimal solution

### Approach 2: Brute Force (Not Recommended)
Try all buy-sell pairs. O(n²) time, O(1) space.

**Algorithm:**
1. For each buy day i
2. For each sell day j > i
3. Calculate profit = prices[j] - prices[i]
4. Update maxProfit

**Disadvantages:**
- O(n²) time (much slower)
- Not optimal
- Redundant calculations

### Approach 3: Dynamic Programming (Overkill for One Transaction)
Use DP to track best buy and sell. O(n) time, O(1) space.

**Disadvantages:**
- More complex than needed
- Same complexity as one-pass approach
- Useful for multiple transactions variant

## Key Takeaways

1. **Track minimum** price seen so far
2. **Calculate profit** at each step (current price - min price)
3. **Update maximum profit** when better profit found
4. **O(n) time, O(1) space** - optimal solution
5. **Single pass** through array
6. **Greedy approach** - locally optimal choices lead to global optimum
7. **Edge cases matter** - empty array, single day, no profit possible
8. **Must buy before selling** - constraint handled naturally
9. **Simple and elegant** - one of the classic problems
10. **Foundation for variants** - multiple transactions, fees, cooldown
