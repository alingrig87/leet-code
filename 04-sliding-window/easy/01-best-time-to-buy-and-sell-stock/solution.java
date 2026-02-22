/**
 * LeetCode 121: Best Time to Buy and Sell Stock
 * 
 * Problem: Find maximum profit from one buy and one sell.
 * 
 * Solution Approach: Track minimum price
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int minPrice = prices[0]; // Minimum price seen so far
        int maxProfit = 0; // Maximum profit achievable
        
        // For each day, calculate profit if selling today
        for (int i = 1; i < prices.length; i++) {
            // Update minimum price (best buy price so far)
            minPrice = Math.min(minPrice, prices[i]);
            
            // Calculate profit if selling today
            int profit = prices[i] - minPrice;
            
            // Update maximum profit
            maxProfit = Math.max(maxProfit, profit);
        }
        
        return maxProfit;
    }
}
