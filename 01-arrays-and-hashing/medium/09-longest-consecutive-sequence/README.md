# Longest Consecutive Sequence

## Problem Statement
Given an unsorted array of integers `nums`, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

**Example 1:**
```
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
```

**Example 2:**
```
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
```

## Theory & Data Structures

### HashSet for O(1) Lookup
Convert array to HashSet for O(1) membership testing.

### Sequence Detection Strategy
For each number, check if it's the start of a sequence (number-1 doesn't exist). If yes, expand the sequence by checking number+1, number+2, etc.

### Time & Space Complexity

#### Approach: HashSet with Sequence Expansion
- **Time Complexity**: O(n) - Each number is visited at most twice
- **Space Complexity**: O(n) - HashSet storage

## Interview Simulation

### Initial Discussion

**Interviewer**: "Find longest consecutive sequence in O(n) time."

**Candidate**: "I'll use a HashSet for O(1) lookups. For each number, I'll check if it's the start of a sequence - meaning number-1 doesn't exist. If it is, I'll expand the sequence by checking number+1, number+2, etc., and track the maximum length."

**Interviewer**: "Why check only if it's the start?"

**Candidate**: "To avoid duplicate work. If we check every number, we'd process the same sequence multiple times. By only starting from sequence beginnings, each sequence is processed exactly once."

**Interviewer**: "What's the time complexity?"

**Candidate**: "O(n) - each number is visited at most twice: once when we check if it's a start, and once when we expand a sequence that includes it."

### Follow-up Questions

**Interviewer**: "What if the array is sorted?"

**Candidate**: "Then we could do a single pass in O(n) time with O(1) space, just tracking the current sequence length."

**Interviewer**: "What about duplicates?"

**Candidate**: "HashSet automatically handles duplicates - each number appears once. This doesn't affect the algorithm."

### Tricky Edge Cases

1. **Empty array**: `[]` → Return 0
2. **Single element**: `[1]` → Return 1
3. **All same**: `[1,1,1]` → Return 1
4. **No consecutive**: `[1,3,5,7]` → Return 1
5. **Negative numbers**: `[-1,0,1]` → Works fine

## Solution Approaches

### Approach: HashSet with Sequence Expansion (Optimal)
Convert to HashSet, find sequence starts, expand sequences. O(n) time, O(n) space.

## Key Takeaways

1. **HashSet enables O(1) lookup** for membership testing
2. **Start from sequence beginnings** to avoid duplicate work
3. **Each number visited at most twice** ensures O(n) time
4. **Expand sequences** by checking consecutive numbers
