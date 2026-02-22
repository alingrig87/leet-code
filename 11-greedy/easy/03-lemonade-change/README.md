# Lemonade Change

## Problem Statement
At a lemonade stand, each lemonade costs `$5`. Customers are standing in a queue to buy from you and order one at a time (in the order specified by bills). Each customer will only buy one lemonade and pay with either a `$5`, `$10`, or `$20` bill. You must provide the correct change to each customer so that the net transaction is that the customer pays `$5`.

Note that you do not have any change in hand at first.

Given an integer array `bills` where `bills[i]` is the bill the `i`th customer pays, return `true` if you can provide every customer with the correct change, or `false` otherwise.

**Example 1:**
```
Input: bills = [5,5,5,10,20]
Output: true
Explanation: 
From the first 3 customers, we collect three $5 bills in order.
From the fourth customer, we collect a $10 bill and give back a $5.
From the fifth customer, we collect a $20 bill and give back a $10 and a $5.
Since all customers got correct change, we output true.
```

**Example 2:**
```
Input: bills = [5,5,10,10,20]
Output: false
Explanation: 
From the first two customers, we collect two $5 bills.
For the third customer, we collect a $10 bill and give back a $5.
For the fourth customer, we collect a $10 bill and give back a $5.
For the fifth customer, we collect a $20 bill but we don't have a $10 and a $5 to give back.
Since we cannot provide correct change, we output false.
```

## Theory & Data Structures

### Greedy Change Making
This problem uses a **greedy strategy** for making change. The key insight is to always prefer using larger bills when giving change to preserve smaller bills for future transactions.

#### Key Insight: Preserve $5 Bills
- **$5 bills are most versatile**: Used for $5, $10, and $20 change
- **$10 bills**: Only used for $20 change
- **Greedy choice**: When giving $15 change for $20, prefer $10+$5 over three $5s
- **Why**: Preserving $5 bills ensures we can handle future $10 payments

#### Building Greedy Solution from Scratch (Conceptual)
```java
// Conceptual implementation of lemonade change
class LemonadeChange {
    
    public boolean lemonadeChange(int[] bills) {
        int fiveCount = 0;  // Count of $5 bills
        int tenCount = 0;    // Count of $10 bills
        
        for (int bill : bills) {
            if (bill == 5) {
                // $5 payment: no change needed, just collect
                fiveCount++;
            } else if (bill == 10) {
                // $10 payment: need $5 change
                if (fiveCount > 0) {
                    fiveCount--;
                    tenCount++;
                } else {
                    return false;  // Cannot provide change
                }
            } else {  // bill == 20
                // $20 payment: need $15 change
                // Prefer $10 + $5 over three $5s
                if (tenCount > 0 && fiveCount > 0) {
                    // Use $10 + $5 (greedy choice)
                    tenCount--;
                    fiveCount--;
                } else if (fiveCount >= 3) {
                    // Use three $5s (fallback)
                    fiveCount -= 3;
                } else {
                    return false;  // Cannot provide change
                }
            }
        }
        
        return true;
    }
    
    // Why prefer $10 + $5 for $20 change?
    // - $5 bills are more versatile
    // - If we use three $5s, we might run out when we need them for $10 change
    // - Example: [5,5,5,10,10,20]
    //   If we use three $5s for first $20, we can't handle second $10
    //   But if we use $10+$5, we preserve $5s for future $10 payments
}
```

### Time & Space Complexity

#### Approach: Greedy
- **Time Complexity**: O(n) - Single pass through bills
  - Process each bill once
  - O(1) work per bill
- **Space Complexity**: O(1) - Only counters
  - Two integer counters
  - Constant space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Can you provide change to all customers at a lemonade stand?"

**Candidate**: "I'll track the count of $5 and $10 bills I have. For a $5 payment, I just collect it. For a $10 payment, I need to give back one $5. For a $20 payment, I need to give back $15. I'll prefer using a $10 and a $5 rather than three $5s to preserve $5 bills for future $10 payments."

**Interviewer**: "Why prefer $10+$5 for $20 change?"

**Candidate**: "Because $5 bills are more versatile - they're needed for both $10 and $20 change. If I use three $5s for a $20 payment, I might run out of $5s when I need them for a $10 payment later. By using a $10 when possible, I preserve $5 bills."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For [5,5,5,10,20], first three customers pay $5: fiveCount=3. Fourth customer pays $10: need $5 change, fiveCount=2, tenCount=1. Fifth customer pays $20: prefer $10+$5, tenCount=0, fiveCount=1. All customers satisfied, return true."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n) since we process each bill once. Space complexity is O(1) since we only use two counters."

### Follow-up Questions

**Interviewer**: "What if we have different bill denominations?"

**Candidate**: "Then we'd need a more general change-making algorithm. For some denominations, greedy works (like US currency), but for others, we might need dynamic programming to find the optimal change."

**Interviewer**: "What if we start with some change?"

**Candidate**: "Same algorithm, just initialize the counters with the starting bills. The logic remains the same."

**Interviewer**: "What if customers can pay with any denomination?"

**Candidate**: "Then it becomes more complex - we'd need to track all denominations and use a more sophisticated change-making algorithm, possibly DP."

### Tricky Edge Cases

1. **All $5 payments**: Always true (no change needed)
2. **Early $20 without $10**: Need three $5s
3. **Many $10s early**: Might run out of $5s
4. **Exact sequence**: `[5,5,10,20]` → Need to check carefully
5. **Multiple $20s**: Handle correctly with greedy choice
6. **No $5s for $10**: Return false immediately

## Solution Approaches

### Approach: Greedy (Optimal)
Track $5 and $10 counts, give change greedily. O(n) time, O(1) space.

**Algorithm:**
1. Initialize fiveCount=0, tenCount=0
2. For each bill:
   - $5: increment fiveCount
   - $10: if fiveCount>0, decrement fiveCount, increment tenCount; else return false
   - $20: prefer $10+$5, else three $5s, else return false
3. Return true if all processed

**Advantages:**
- O(n) time complexity
- O(1) space complexity
- Simple and efficient
- Optimal solution

## Key Takeaways

1. **Greedy change making** - use largest bills first when possible
2. **Preserve $5 bills** - they're most versatile
3. **Track counts** of each denomination
4. **O(n) time, O(1) space**
5. **Greedy choice is optimal** for this problem
6. **Foundation for** change-making problems
