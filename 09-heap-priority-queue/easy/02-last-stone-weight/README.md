# Last Stone Weight

## Problem Statement
You are given an array of integers `stones` where `stones[i]` is the weight of the `i`th stone.

We are playing a game with the stones. On each turn, we choose the two heaviest stones and smash them together. Suppose the two heaviest stones have weights `x` and `y` with `x <= y`. The result of this smash is:
- If `x == y`, both stones are totally destroyed;
- If `x != y`, the stone of weight `x` is totally destroyed, and the stone of weight `y` has new weight `y - x`.

At the end of the game, there is at most one stone left. Return the weight of the last remaining stone. If there are no stones left, return `0`.

**Example 1:**
```
Input: stones = [2,7,4,1,8,1]
Output: 1
```

## Theory & Data Structures

### Max Heap
Use max heap to always get two heaviest stones. Smash them, add result back if non-zero.

### Time & Space Complexity

#### Approach: Max Heap
- **Time Complexity**: O(n log n) - n operations on heap
- **Space Complexity**: O(n) - Heap storage

## Interview Simulation

### Initial Discussion

**Interviewer**: "Simulate stone smashing game."

**Candidate**: "I'll use a max heap. Repeatedly extract two largest stones, smash them, add result back if non-zero. Continue until one or zero stones remain."

**Interviewer**: "What's the complexity?"

**Candidate**: "O(n log n) time for heap operations, O(n) space for heap."

### Follow-up Questions

**Interviewer**: "What if we need to track which stones remain?"

**Candidate**: "We could store stone objects with IDs instead of just weights."

### Tricky Edge Cases

1. **All stones destroyed**: Return 0
2. **Single stone**: Return its weight
3. **All same weight**: All destroyed, return 0
4. **One remains**: Return its weight

## Solution Approaches

### Approach: Max Heap (Optimal)
Use max heap, repeatedly smash two largest. O(n log n) time, O(n) space.

## Key Takeaways

1. **Max heap** for getting largest
2. **Repeatedly extract** two largest
3. **Add result back** if non-zero
4. **Simulate game** until end
