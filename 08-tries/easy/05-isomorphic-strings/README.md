# Isomorphic Strings

## Problem Statement
Given two strings `s` and `t`, determine if they are isomorphic.

Two strings `s` and `t` are isomorphic if the characters in `s` can be replaced to get `t`.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

**Example 1:**
```
Input: s = "egg", t = "add"
Output: true
Explanation: e -> a, g -> d
```

**Example 2:**
```
Input: s = "foo", t = "bar"
Output: false
Explanation: f -> b, o -> a, but second o also maps to a, which conflicts
```

## Theory & Data Structures

### Two HashMaps
This problem uses **two HashMaps** to track mappings in both directions. We need to ensure a one-to-one mapping: each character in s maps to exactly one character in t, and each character in t is mapped from exactly one character in s.

#### Key Insight: Bidirectional Mapping
- **s to t mapping**: Track what each character in s maps to in t
- **t to s mapping**: Track what each character in t is mapped from in s
- **Check consistency**: Ensure mappings are consistent
- **One-to-one**: No two characters map to same character

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of isomorphic strings
class IsomorphicChecker {
    
    // Two HashMaps approach
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        // Maps for s->t and t->s
        Map<Character, Character> sToT = new HashMap<>();
        Map<Character, Character> tToS = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            
            // Check s->t mapping
            if (sToT.containsKey(sChar)) {
                if (sToT.get(sChar) != tChar) {
                    return false;  // Conflict in s->t
                }
            } else {
                sToT.put(sChar, tChar);
            }
            
            // Check t->s mapping
            if (tToS.containsKey(tChar)) {
                if (tToS.get(tChar) != sChar) {
                    return false;  // Conflict in t->s
                }
            } else {
                tToS.put(tChar, sChar);
            }
        }
        
        return true;
    }
    
    // Why two maps?
    // To ensure one-to-one mapping
    // Example: s="ab", t="aa"
    // Without t->s map: s->t would allow a->a, b->a (invalid)
    // With t->s map: we detect that 'a' in t is already mapped from 'a' in s
    
    // Alternative: Using arrays (for ASCII)
    public boolean isIsomorphicArray(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int[] sToT = new int[256];
        int[] tToS = new int[256];
        Arrays.fill(sToT, -1);
        Arrays.fill(tToS, -1);
        
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            
            if (sToT[sChar] != -1 && sToT[sChar] != tChar) {
                return false;
            }
            if (tToS[tChar] != -1 && tToS[tChar] != sChar) {
                return false;
            }
            
            sToT[sChar] = tChar;
            tToS[tChar] = sChar;
        }
        
        return true;
    }
}
```

### Time & Space Complexity

#### Approach: Two HashMaps
- **Time Complexity**: O(n) - Single pass through strings
  - n = length of strings
- **Space Complexity**: O(1) - Fixed size maps
  - At most 256 characters (ASCII)
  - O(1) space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if two strings are isomorphic."

**Candidate**: "I'll use two HashMaps - one maps characters from s to t, the other maps characters from t to s. For each character pair, I'll check if the mappings are consistent. If there's a conflict, I'll return false."

**Interviewer**: "Why two maps?"

**Candidate**: "To ensure a one-to-one mapping. For example, if s='ab' and t='aa', without the t->s map, we might allow a->a and b->a, which violates the constraint that no two characters in s can map to the same character in t."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For s='egg', t='add', i=0: e->a (new), a->e (new). i=1: g->d (new), d->g (new). i=2: g->d (exists, matches), d->g (exists, matches). All consistent, return true."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n) since we make a single pass. Space complexity is O(1) since the maps are bounded by the character set size (256 for ASCII)."

### Follow-up Questions

**Interviewer**: "What if strings are very long?"

**Candidate**: "The approach still works efficiently. The maps are bounded by character set size, not string length, so space complexity remains O(1)."

### Tricky Edge Cases

1. **Different lengths**: Return false
2. **Same string**: Return true (each char maps to itself)
3. **Multiple mappings conflict**: Return false
4. **Circular mapping**: Handle correctly
5. **Empty strings**: Return true

## Solution Approaches

### Approach: Two HashMaps (Optimal)
Track mappings in both directions. O(n) time, O(1) space.

**Algorithm:**
1. Check lengths equal
2. Create two maps
3. For each character pair:
   - Check s->t mapping consistency
   - Check t->s mapping consistency
   - Update maps if new
4. Return true if all consistent

**Advantages:**
- O(n) time complexity
- O(1) space complexity
- Simple and efficient

## Key Takeaways

1. **Two maps** for bidirectional mapping
2. **Check consistency** at each step
3. **One-to-one** mapping required
4. **O(n) time, O(1) space**
5. **Foundation for** string pattern matching
