/**
 * LeetCode 122: Best Time to Buy and Sell Stock II
 * 
 * Problem: Find maximum profit with unlimited buy/sell transactions.
 * 
 * Solution Approach: Greedy - sum all positive price differences
 * Time Complexity: O(n) - single pass
 * Space Complexity: O(1) - only variables
 */
class Solution {
    
    /**
     * Main solution using greedy approach
     * 
     * Key insight: Since we can make unlimited transactions, the optimal strategy
     * is to capture every price increase. This is equivalent to:
     * - Buying before every price increase
     * - Selling before every price decrease
     * 
     * Mathematical proof:
     * If prices go from a to d: [a, b, c, d] where a < b < c < d
     * - Strategy 1: Buy at a, sell at d → Profit = d - a
     * - Strategy 2: Buy at a, sell at b, buy at b, sell at c, buy at c, sell at d
     *   → Profit = (b-a) + (c-b) + (d-c) = d - a (same!)
     * 
     * Therefore, we can simply sum all positive day-to-day price differences.
     * This gives us the maximum profit achievable.
     * 
     * Algorithm:
     * - For each day, calculate price difference from previous day
     * - If difference is positive (price increased), add it to profit
     * - Sum of all positive differences = maximum profit
     * 
     * @param prices Array of stock prices for each day
     * @return Maximum profit achievable
     */
    public int maxProfit(int[] prices) {
        // Edge case: empty or single day
        if (prices == null || prices.length <= 1) {
            return 0; // No transactions possible
        }
        
        int maxProfit = 0;
        
        // Process each day starting from day 1
        // Compare with previous day to find price increases
        for (int i = 1; i < prices.length; i++) {
            // Calculate price difference from previous day
            int priceDifference = prices[i] - prices[i - 1];
            
            // If price increased, we can profit from this increase
            // This represents: buy at prices[i-1], sell at prices[i]
            // Add this profit to our total
            if (priceDifference > 0) {
                maxProfit += priceDifference;
            }
            // If price decreased or stayed same, we don't trade (no profit)
            // This is the greedy choice: only capture increases
        }
        
        return maxProfit;
    }
}

/**
 * Alternative: More explicit version showing the trading strategy
 */
class SolutionExplicit {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int profit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            // If today's price is higher than yesterday, we can profit
            // Strategy: buy yesterday, sell today
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        
        return profit;
    }
}

/**
 * Follow-up: What if there's a transaction fee?
 * 
 * Then we need dynamic programming to decide when to trade.
 */
class SolutionWithFee {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        // DP: two states
        // hold: maximum profit while holding stock
        // cash: maximum profit while not holding stock
        int hold = -prices[0]; // Buy on first day
        int cash = 0; // Start with no stock
        
        for (int i = 1; i < prices.length; i++) {
            // Either keep holding or buy today
            hold = Math.max(hold, cash - prices[i]);
            
            // Either keep cash or sell today (pay fee)
            cash = Math.max(cash, hold + prices[i] - fee);
        }
        
        return cash; // Final state: not holding stock
    }
}

/**
 * Follow-up: What if there's a cooldown period after selling?
 * 
 * Need to track three states: can buy, just sold (cooldown), holding.
 */
class SolutionWithCooldown {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        // Three states
        int canBuy = 0;        // Can buy (not holding, no cooldown)
        int holding = -prices[0]; // Holding stock
        int cooldown = 0;      // Just sold (cooldown period)
        
        for (int i = 1; i < prices.length; i++) {
            int prevCanBuy = canBuy;
            int prevHolding = holding;
            int prevCooldown = cooldown;
            
            // State transitions
            canBuy = Math.max(prevCanBuy, prevCooldown); // Stay or exit cooldown
            holding = Math.max(prevHolding, prevCanBuy - prices[i]); // Keep or buy
            cooldown = prevHolding + prices[i]; // Sell (enter cooldown)
        }
        
        return Math.max(canBuy, cooldown); // Best final state
    }
}
