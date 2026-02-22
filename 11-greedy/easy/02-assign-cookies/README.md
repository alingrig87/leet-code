# Assign Cookies

## Problem Statement
Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.

Each child `i` has a greed factor `g[i]`, which is the minimum size of a cookie that the child will be content with; and each cookie `j` has a size `s[j]`. If `s[j] >= g[i]`, we can assign the cookie `j` to the child `i`, and the child `i` will be content. Your goal is to maximize the number of content children and output the maximum number.

**Example 1:**
```
Input: g = [1,2,3], s = [1,1]
Output: 1
Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.
```

**Example 2:**
```
Input: g = [1,2], s = [1,2,3]
Output: 2
Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
You have 3 cookies and their sizes are big enough to gratify all of the children, 
You need to output 2.
```

## Theory & Data Structures

### Greedy Algorithm
This is a classic **greedy assignment** problem. The optimal strategy is:
1. Sort both arrays
2. Use two pointers to match smallest cookie to smallest greed that it satisfies

### Why Greedy Works
- If we can satisfy a child with a cookie, we should do it
- Using smallest cookie that works leaves larger cookies for children with higher greed
- This maximizes the number of satisfied children

### Time & Space Complexity

#### Approach: Greedy with Sorting
- **Time Complexity**: O(n log n + m log m) - Sorting both arrays
- **Space Complexity**: O(1) - Only pointers (excluding input)

## Interview Simulation

### Initial Discussion

**Interviewer**: "Maximize number of content children by assigning cookies."

**Candidate**: "I'll use a greedy approach. Sort both arrays - children by greed factor, cookies by size. Use two pointers to match smallest cookie to smallest greed it can satisfy. This ensures we use cookies efficiently."

**Interviewer**: "Why does this maximize the count?"

**Candidate**: "By using the smallest cookie that satisfies a child, we leave larger cookies available for children with higher greed factors. This greedy choice is optimal because if a cookie can satisfy a child, we should use it rather than saving it for a potentially non-existent child with higher greed."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n log n + m log m) for sorting, then O(n + m) for matching, so overall O(n log n + m log m). Space is O(1) excluding input."

### Follow-up Questions

**Interviewer**: "What if we need to maximize total satisfaction instead of count?"

**Candidate**: "Then we'd use a different strategy - maybe assign largest cookies to children with highest greed to maximize the sum of (cookie_size - greed_factor)."

**Interviewer**: "What if cookies can be split?"

**Candidate**: "Then it becomes a fractional knapsack problem, and we'd use greedy by ratio."

### Tricky Edge Cases

1. **No cookies**: Return 0
2. **No children**: Return 0
3. **All cookies too small**: Return 0
4. **All children satisfied**: Return min(children count, cookies count)
5. **Some cookies unused**: Handle correctly

## Solution Approaches

### Approach: Greedy with Sorting (Optimal)
Sort both arrays, match greedily. O(n log n + m log m) time, O(1) space.

## Key Takeaways

1. **Greedy matching** is optimal
2. **Sort both arrays** for efficient matching
3. **Use smallest cookie** that works
4. **Maximizes count** of satisfied children
