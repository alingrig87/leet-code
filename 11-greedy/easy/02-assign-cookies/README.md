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
This is a classic **greedy assignment** problem. The optimal strategy is to sort both arrays and use two pointers to match the smallest cookie that satisfies each child's greed.

#### Key Insight: Greedy Matching
- **Sort both arrays**: Children by greed, cookies by size
- **Match smallest to smallest**: Use smallest cookie that satisfies smallest unsatisfied greed
- **Why it works**: Using smallest cookie leaves larger cookies for children with higher greed
- **Maximizes count**: This greedy choice maximizes number of satisfied children

#### Building Greedy Solution from Scratch (Conceptual)
```java
// Conceptual implementation of cookie assignment
class CookieAssigner {
    
    public int findContentChildren(int[] g, int[] s) {
        // Sort both arrays
        Arrays.sort(g);  // Children by greed
        Arrays.sort(s);  // Cookies by size
        
        int childIndex = 0;
        int cookieIndex = 0;
        int contentChildren = 0;
        
        // Match cookies to children
        while (childIndex < g.length && cookieIndex < s.length) {
            // If cookie satisfies child's greed
            if (s[cookieIndex] >= g[childIndex]) {
                contentChildren++;
                childIndex++;  // Child is satisfied
            }
            // Move to next cookie (whether used or not)
            cookieIndex++;
        }
        
        return contentChildren;
    }
    
    // Why greedy works?
    // - If we can satisfy a child, we should do it
    // - Using smallest cookie that works leaves larger cookies
    //   for children with higher greed
    // - This maximizes the number of satisfied children
    
    // Alternative: Match largest to largest (less optimal)
    // Would work but less intuitive
}
```

### Time & Space Complexity

#### Approach: Greedy with Sorting
- **Time Complexity**: O(n log n + m log m) - Sorting both arrays
  - Sorting children: O(n log n)
  - Sorting cookies: O(m log m)
  - Matching: O(n + m)
  - Total: O(n log n + m log m)
- **Space Complexity**: O(1) - Only pointers (excluding input)
  - Sorting in-place: O(1)
  - Only variables for indices

## Interview Simulation

### Initial Discussion

**Interviewer**: "Maximize the number of content children by assigning cookies."

**Candidate**: "I'll use a greedy approach. I'll sort both arrays - children by greed factor, cookies by size. Then I'll use two pointers to match the smallest cookie that satisfies each child's greed. This ensures we use cookies efficiently and maximize the number of satisfied children."

**Interviewer**: "Why does this maximize the count?"

**Candidate**: "By using the smallest cookie that works, we leave larger cookies available for children with higher greed factors. This greedy choice is optimal because if a cookie can satisfy a child, we should use it rather than saving it for a potentially non-existent child with higher greed. If we save a cookie and it can't satisfy any remaining child, we've wasted an opportunity."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For g=[1,2,3], s=[1,1], after sorting: g=[1,2,3], s=[1,1]. Match s[0]=1 to g[0]=1: satisfied, count=1. Try s[1]=1 to g[1]=2: 1<2, not satisfied, skip child. Try s[1]=1 to g[2]=3: 1<3, not satisfied. Result: 1 satisfied child."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n log n + m log m) for sorting, then O(n + m) for matching, so overall O(n log n + m log m). Space complexity is O(1) excluding input."

### Follow-up Questions

**Interviewer**: "What if we need to maximize total satisfaction instead of count?"

**Candidate**: "Then we'd use a different strategy - maybe assign largest cookies to children with highest greed to maximize the sum of (cookie_size - greed_factor), or use a different matching algorithm."

**Interviewer**: "What if cookies can be split?"

**Candidate**: "Then it becomes a fractional knapsack problem, and we'd use greedy by ratio - assign cookies based on the ratio of satisfaction to cookie size."

**Interviewer**: "What if each child can get multiple cookies?"

**Candidate**: "Then we'd need to track which children are satisfied and continue assigning until all cookies are used or all children are satisfied. The greedy approach would still work."

### Tricky Edge Cases

1. **No cookies**: Return 0
2. **No children**: Return 0
3. **All cookies too small**: Return 0
4. **All children satisfied**: Return min(children count, cookies count)
5. **Some cookies unused**: Handle correctly
6. **Equal greed factors**: Handle correctly
7. **Equal cookie sizes**: Handle correctly

## Solution Approaches

### Approach: Greedy with Sorting (Optimal)
Sort both arrays, match greedily. O(n log n + m log m) time, O(1) space.

**Algorithm:**
1. Sort children by greed factor
2. Sort cookies by size
3. Use two pointers:
   - If cookie >= greed: assign, move both pointers
   - Else: move cookie pointer only
4. Return count of satisfied children

**Advantages:**
- O(1) space complexity
- Simple and efficient
- Optimal solution

## Key Takeaways

1. **Greedy matching** is optimal
2. **Sort both arrays** for efficient matching
3. **Use smallest cookie** that works
4. **Maximizes count** of satisfied children
5. **O(n log n + m log m) time, O(1) space**
6. **Foundation for** assignment and matching problems
