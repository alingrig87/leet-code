# Can Place Flowers

## Problem Statement
You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.

Given an integer array `flowerbed` containing `0`'s and `1`'s, where `0` means empty and `1` means not empty, and an integer `n`, return `true` if `n` new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and `false` otherwise.

**Example 1:**
```
Input: flowerbed = [1,0,0,0,1], n = 1
Output: true
```

**Example 2:**
```
Input: flowerbed = [1,0,0,0,1], n = 2
Output: false
```

## Theory & Data Structures

### Greedy Placement
Greedy strategy: plant flowers as early as possible when a valid spot is found. Check if current position and neighbors are empty.

### Time & Space Complexity

#### Approach: Greedy
- **Time Complexity**: O(n) - Single pass
- **Space Complexity**: O(1) - Only variables

## Interview Simulation

### Initial Discussion

**Interviewer**: "Can we plant n flowers without adjacent constraint?"

**Candidate**: "I'll use greedy - plant flowers as early as possible. For each position, if it and its neighbors are empty, plant a flower there and mark it. Count how many we can plant."

**Interviewer**: "Why greedy?"

**Candidate**: "Planting early maximizes remaining spots. If we skip a valid spot, we might not be able to use it later due to constraints."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n) time and O(1) space - single pass with constant space."

### Follow-up Questions

**Interviewer**: "What if we need to maximize number of flowers?"

**Candidate**: "Same greedy approach - it already maximizes by planting as early as possible."

### Tricky Edge Cases

1. **Empty flowerbed**: `[0,0,0]`, n=2 → Return true
2. **Full flowerbed**: `[1,1,1]`, n=1 → Return false
3. **Edges**: `[0,0,1]` → Can plant at start
4. **Single spot**: `[0]`, n=1 → Return true

## Solution Approaches

### Approach: Greedy (Optimal)
Plant flowers greedily when valid spot found. O(n) time, O(1) space.

## Key Takeaways

1. **Greedy placement** maximizes count
2. **Check neighbors** before planting
3. **Handle edges** carefully
4. **O(n) time, O(1) space**
