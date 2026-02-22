# Group Anagrams

## Problem Statement
Given an array of strings `strs`, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

**Example 1:**
```
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
```

**Example 2:**
```
Input: strs = [""]
Output: [[""]]
```

**Example 3:**
```
Input: strs = ["a"]
Output: [["a"]]
```

## Theory & Data Structures

### HashMap for Grouping
Use HashMap where:
- **Key**: A representation of the anagram (sorted string or frequency string)
- **Value**: List of strings that are anagrams of each other

### Key Generation Strategies
1. **Sorted String**: Sort characters, use as key
2. **Frequency String**: Create string like "a2b1c3" representing character counts
3. **Character Array**: Convert to char array, sort, convert back

### Time & Space Complexity

#### Approach: HashMap with Sorted Key
- **Time Complexity**: O(n * k log k) where n is strings count, k is average string length
- **Space Complexity**: O(n * k) - HashMap stores all strings

## Interview Simulation

### Initial Discussion

**Interviewer**: "Group strings that are anagrams of each other."

**Candidate**: "I'll use a HashMap. For each string, I'll create a key by sorting its characters. All anagrams will have the same sorted key, so I'll group them together."

**Interviewer**: "What's the time complexity?"

**Candidate**: "O(n * k log k) where n is number of strings and k is average length, because we sort each string."

**Interviewer**: "Can you optimize?"

**Candidate**: "We could use a frequency string instead of sorting - count each character and create a key like 'a2b1c3'. This would be O(n * k) time."

### Follow-up Questions

**Interviewer**: "What if strings are very long?"

**Candidate**: "The frequency string approach becomes more attractive since it's O(k) instead of O(k log k) per string."

**Interviewer**: "What about memory constraints?"

**Candidate**: "We could use a more compact representation, like a 26-character array converted to a string, or even use prime number encoding."

### Tricky Edge Cases

1. **Empty strings**: `[""]` → Group together
2. **Single character**: `["a"]` → Single group
3. **All same**: `["abc","abc","abc"]` → One group
4. **No anagrams**: `["abc","def","ghi"]` → Three separate groups
5. **Mixed lengths**: Need to handle strings of different lengths

## Solution Approaches

### Approach 1: HashMap with Sorted Key
Sort each string, use as key. O(n * k log k) time, O(n * k) space.

### Approach 2: HashMap with Frequency String
Count characters, create frequency string as key. O(n * k) time, O(n * k) space.

## Key Takeaways

1. **HashMap for grouping** is the key pattern
2. **Key generation** determines time complexity
3. **Frequency string** can be faster than sorting
4. **Trade-off** between time and code simplicity
