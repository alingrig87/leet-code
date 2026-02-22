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
Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
```

## Theory & Data Structures

### Max Heap (Priority Queue)
This problem uses a **max heap** to efficiently get the two heaviest stones at each step. The heap automatically maintains the largest elements at the top.

#### Key Insight: Repeatedly Get Two Largest
- **Max heap**: Always gives us the largest element
- **Extract two**: Get two largest stones
- **Smash and add back**: Calculate result, add back if non-zero
- **Continue**: Until one or zero stones remain

#### Building Max Heap Solution from Scratch (Conceptual)
```java
// Conceptual implementation of last stone weight
class LastStoneWeight {
    
    public int lastStoneWeight(int[] stones) {
        // Max heap: largest element at top
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        
        // Add all stones to heap
        for (int stone : stones) {
            maxHeap.offer(stone);
        }
        
        // Repeatedly smash two heaviest stones
        while (maxHeap.size() > 1) {
            int first = maxHeap.poll();   // Heaviest
            int second = maxHeap.poll();  // Second heaviest
            
            // Smash: if different, add difference back
            if (first != second) {
                maxHeap.offer(first - second);
            }
            // If equal, both destroyed (don't add anything)
        }
        
        // Return remaining stone weight, or 0 if none
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
    
    // Why max heap?
    // - We need the two largest elements repeatedly
    // - Max heap gives us largest in O(log n) time
    // - More efficient than sorting repeatedly
    
    // Alternative: Sort repeatedly (less efficient)
    // Would be O(n² log n) - sort n times
}
```

### Time & Space Complexity

#### Approach: Max Heap
- **Time Complexity**: O(n log n) - n operations on heap
  - Building heap: O(n log n)
  - Each smash: O(log n) for two extracts and one insert
  - Worst case: O(n) smashes
  - Total: O(n log n)
- **Space Complexity**: O(n) - Heap storage
  - Stores all stones initially
  - Decreases as stones are destroyed

## Interview Simulation

### Initial Discussion

**Interviewer**: "Simulate the stone smashing game and return the last stone weight."

**Candidate**: "I'll use a max heap to efficiently get the two heaviest stones. I'll repeatedly extract the two largest stones, smash them together, and if the result is non-zero, add it back to the heap. I'll continue until one or zero stones remain."

**Interviewer**: "Why use a max heap?"

**Candidate**: "A max heap allows us to get the two largest elements in O(log n) time. Without it, we'd need to sort the array repeatedly, which would be O(n² log n) time. The heap approach is much more efficient."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For [2,7,4,1,8,1], heap=[8,7,4,2,1,1]. Extract 8 and 7: smash to get 1, heap=[4,2,1,1,1]. Extract 4 and 2: smash to get 2, heap=[2,1,1,1]. Extract 2 and 1: smash to get 1, heap=[1,1,1]. Extract 1 and 1: both destroyed, heap=[1]. Return 1."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n log n) for heap operations. Space complexity is O(n) for the heap."

### Follow-up Questions

**Interviewer**: "What if we need to track which stones remain?"

**Candidate**: "We could store stone objects with IDs instead of just weights. The algorithm would be similar, but we'd track stone identities."

**Interviewer**: "What if stones can have fractional weights?"

**Candidate**: "The algorithm still works - we'd use double or float instead of int, and the logic remains the same."

### Tricky Edge Cases

1. **All stones destroyed**: Return 0
2. **Single stone**: Return its weight
3. **All same weight**: All destroyed, return 0
4. **One remains**: Return its weight
5. **Two stones, different**: Return difference
6. **Two stones, same**: Return 0

## Solution Approaches

### Approach: Max Heap (Optimal)
Use max heap, repeatedly smash two largest. O(n log n) time, O(n) space.

**Algorithm:**
1. Build max heap from stones
2. While heap size > 1:
   - Extract two largest
   - Smash them
   - Add result back if non-zero
3. Return remaining stone or 0

**Advantages:**
- O(n log n) time complexity
- Efficient heap operations
- Optimal solution

## Key Takeaways

1. **Max heap** for getting largest elements
2. **Repeatedly extract** two largest
3. **Add result back** if non-zero
4. **Simulate game** until end
5. **O(n log n) time, O(n) space**
6. **Foundation for** heap-based simulation problems
