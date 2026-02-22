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
```

## Theory & Data Structures

### Greedy Change Making
Use greedy strategy for change: always use largest bills first when giving change. Track count of $5 and $10 bills.

### Why Greedy Works
- $5 bills are most versatile (used for $5, $10, $20 change)
- $10 bills only used for $20 change
- Always prefer using $10 for $20 change to preserve $5 bills

### Time & Space Complexity

#### Approach: Greedy
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Only counters

## Interview Simulation

### Initial Discussion

**Interviewer**: "Can you provide change to all customers?"

**Candidate**: "I'll track count of $5 and $10 bills. For $5 payment, just add a $5. For $10, need one $5 as change. For $20, prefer using $10+$5, otherwise three $5s."

**Interviewer**: "Why prefer $10+$5 for $20?"

**Candidate**: "Because $5 bills are more versatile. If we use three $5s, we might run out when we need them for $10 change later."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n) time and O(1) space - single pass with two counters."

### Follow-up Questions

**Interviewer**: "What if we have different bill denominations?"

**Candidate**: "Then we'd need a more general change-making algorithm, possibly dynamic programming if greedy doesn't work."

**Interviewer**: "What if we start with some change?"

**Candidate**: "Same algorithm, just initialize counters with starting bills."

### Tricky Edge Cases

1. **All $5**: Always true
2. **Early $20 without $10**: Need three $5s
3. **Many $10s early**: Might run out of $5s
4. **Exact sequence**: `[5,5,10,20]` → Need to check carefully

## Solution Approaches

### Approach: Greedy (Optimal)
Track $5 and $10 counts, give change greedily. O(n) time, O(1) space.

## Key Takeaways

1. **Greedy change making** - use largest bills first
2. **Preserve $5 bills** - they're most versatile
3. **Track counts** of each denomination
4. **O(n) time, O(1) space**
