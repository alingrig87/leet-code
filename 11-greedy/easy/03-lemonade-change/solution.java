/**
 * LeetCode 860: Lemonade Change
 * 
 * Problem: Determine if you can provide correct change to all customers.
 * Lemonade costs $5. Customers pay with $5, $10, or $20 bills.
 * 
 * Solution Approach: Greedy change making
 * Time Complexity: O(n) - single pass
 * Space Complexity: O(1) - only two counters
 */
class Solution {
    
    /**
     * Main solution using greedy approach
     * 
     * Key insight: This is a greedy change-making problem. The strategy is:
     * 1. Track count of $5 and $10 bills (we don't need to track $20 as change)
     * 2. For each payment:
     *    - $5: Just add to our cash (no change needed)
     *    - $10: Need to give $5 change (use one $5 bill)
     *    - $20: Prefer $10+$5, otherwise use three $5 bills
     * 
     * Why prefer $10+$5 for $20?
     * - $5 bills are more versatile (needed for $10 and $20 change)
     * - Using $10+$5 preserves $5 bills for future $10 payments
     * - This greedy choice maximizes our ability to give change
     * 
     * Algorithm:
     * - Maintain counters for $5 and $10 bills
     * - Process each payment in order
     * - If we can't provide change, return false immediately
     * 
     * @param bills Array of bill denominations customers pay
     * @return true if can provide change to all, false otherwise
     */
    public boolean lemonadeChange(int[] bills) {
        // Edge case: empty array
        if (bills == null || bills.length == 0) {
            return true; // No customers, can provide change to all (vacuously true)
        }
        
        // Track count of $5 and $10 bills we have
        // We don't need to track $20 bills because we never give them as change
        int fiveCount = 0; // Count of $5 bills
        int tenCount = 0;  // Count of $10 bills
        
        // Process each customer's payment
        for (int bill : bills) {
            if (bill == 5) {
                // Customer pays with $5
                // No change needed, just add $5 to our cash
                fiveCount++;
                
            } else if (bill == 10) {
                // Customer pays with $10
                // Need to give $5 change
                if (fiveCount > 0) {
                    // We have $5 bills, give one as change
                    fiveCount--;  // Give away one $5
                    tenCount++;   // Receive $10
                } else {
                    // Can't provide change - no $5 bills available
                    return false;
                }
                
            } else { // bill == 20
                // Customer pays with $20
                // Need to give $15 change
                // Greedy strategy: prefer $10 + $5 over three $5s
                // This preserves $5 bills for future $10 payments
                
                if (tenCount > 0 && fiveCount > 0) {
                    // Option 1: Give $10 + $5 (preferred)
                    // This preserves more $5 bills
                    tenCount--;  // Give away one $10
                    fiveCount--; // Give away one $5
                    // We receive $20, but don't track it (not used as change)
                } else if (fiveCount >= 3) {
                    // Option 2: Give three $5 bills
                    // Only use this if we don't have $10+$5 combination
                    fiveCount -= 3; // Give away three $5s
                } else {
                    // Can't provide change - neither option available
                    return false;
                }
            }
        }
        
        // If we've processed all customers successfully, return true
        return true;
    }
}

/**
 * Alternative: More explicit version with detailed comments
 */
class SolutionExplicit {
    public boolean lemonadeChange(int[] bills) {
        if (bills == null || bills.length == 0) {
            return true;
        }
        
        int fives = 0;
        int tens = 0;
        
        for (int bill : bills) {
            switch (bill) {
                case 5:
                    fives++;
                    break;
                case 10:
                    if (fives == 0) return false;
                    fives--;
                    tens++;
                    break;
                case 20:
                    // Greedy: use $10+$5 if available
                    if (tens > 0 && fives > 0) {
                        tens--;
                        fives--;
                    } else if (fives >= 3) {
                        fives -= 3;
                    } else {
                        return false;
                    }
                    break;
            }
        }
        
        return true;
    }
}

/**
 * Follow-up: What if we start with some initial change?
 * 
 * Initialize counters with starting bills.
 */
class SolutionWithInitialChange {
    public boolean lemonadeChange(int[] bills, int initialFives, int initialTens) {
        int fiveCount = initialFives;
        int tenCount = initialTens;
        
        for (int bill : bills) {
            if (bill == 5) {
                fiveCount++;
            } else if (bill == 10) {
                if (fiveCount > 0) {
                    fiveCount--;
                    tenCount++;
                } else {
                    return false;
                }
            } else { // 20
                if (tenCount > 0 && fiveCount > 0) {
                    tenCount--;
                    fiveCount--;
                } else if (fiveCount >= 3) {
                    fiveCount -= 3;
                } else {
                    return false;
                }
            }
        }
        
        return true;
    }
}
