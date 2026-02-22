# Valid Anagram

## Problem Statement
Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

**Example 1:**
```
Input: s = "anagram", t = "nagaram"
Output: true
```

**Example 2:**
```
Input: s = "rat", t = "car"
Output: false
```

## Theory & Data Structures

### Character Frequency Counting
An anagram means both strings have the same characters with the same frequencies. We can count character frequencies and compare.

### Array as Frequency Map
For lowercase English letters (a-z), we can use an array of size 26 as a frequency counter. This is more space-efficient than HashMap when the character set is limited.

#### How Array Frequency Counter Works Internally
1. **Character Mapping**: Each lowercase letter (a-z) maps to an index (0-25) using the formula `char - 'a'`
   - 'a' → index 0
   - 'b' → index 1
   - 'z' → index 25
2. **Frequency Counting**: The array stores how many times each character appears
3. **Comparison Strategy**: Count characters in first string (increment), then count characters in second string (decrement)
4. **Validation**: If all frequencies end at zero, strings are anagrams

#### Building a Frequency Counter from Scratch (Conceptual)
```java
// Conceptual implementation of frequency counting
class FrequencyCounter {
    private static final int ALPHABET_SIZE = 26;
    private int[] frequencies;
    
    FrequencyCounter() {
        // Initialize array with zeros for all 26 lowercase letters
        // Each index represents count of a specific character
        frequencies = new int[ALPHABET_SIZE];
    }
    
    // Map character to array index
    // 'a' (ASCII 97) - 'a' (ASCII 97) = 0
    // 'b' (ASCII 98) - 'a' (ASCII 97) = 1
    // 'z' (ASCII 122) - 'a' (ASCII 97) = 25
    private int getIndex(char c) {
        if (c < 'a' || c > 'z') {
            throw new IllegalArgumentException("Character must be lowercase a-z");
        }
        return c - 'a';
    }
    
    // Increment frequency for a character
    public void increment(char c) {
        int index = getIndex(c);
        frequencies[index]++;
    }
    
    // Decrement frequency for a character
    // Returns true if count becomes negative (mismatch detected)
    public boolean decrement(char c) {
        int index = getIndex(c);
        frequencies[index]--;
        return frequencies[index] < 0;
    }
    
    // Check if all frequencies are zero
    public boolean allZero() {
        for (int freq : frequencies) {
            if (freq != 0) {
                return false;
            }
        }
        return true;
    }
    
    // Reset all frequencies to zero
    public void reset() {
        Arrays.fill(frequencies, 0);
    }
}
```

#### Building Frequency Counter (Simple Approach)
```java
// For lowercase letters a-z
int[] freq = new int[26];

// Count frequency of each character in first string
for (char c : s.toCharArray()) {
    freq[c - 'a']++;  // 'a' maps to index 0, 'b' to 1, etc.
}

// Decrement frequency for each character in second string
for (char c : t.toCharArray()) {
    freq[c - 'a']--;  // Decrement for characters in t
    if (freq[c - 'a'] < 0) {
        // More occurrences in t than in s - early exit optimization
        return false;
    }
}
```

### HashMap for General Characters
If characters can be Unicode or mixed case, HashMap is more flexible. HashMap allows us to:
1. Handle any character set (Unicode, uppercase, lowercase, numbers, symbols)
2. Dynamically allocate space only for characters that appear
3. Use O(k) space where k is the number of unique characters (vs O(26) for array)

### Time & Space Complexity

#### Approach 1: Array Frequency Counter (Lowercase only)
- **Time Complexity**: O(n) - Two passes through strings
- **Space Complexity**: O(1) - Fixed size array of 26

#### Approach 2: HashMap (General)
- **Time Complexity**: O(n) - Two passes
- **Space Complexity**: O(k) where k is unique character count

#### Approach 3: Sorting
- **Time Complexity**: O(n log n) - Sorting dominates
- **Space Complexity**: O(n) - Need space for sorted arrays

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given two strings, determine if one is an anagram of the other."

**Candidate**: "An anagram means both strings have the same characters with the same frequencies. I can count character frequencies in both strings and compare them."

**Interviewer**: "How would you implement this?"

**Candidate**: "If we're dealing with lowercase English letters, I'd use an array of size 26 as a frequency counter. For each character in the first string, I increment its count. For the second string, I decrement. If all counts end up at zero, they're anagrams."

**Interviewer**: "What if the strings can have any characters?"

**Candidate**: "Then I'd use a HashMap to count frequencies, which handles any character set including Unicode."

### Follow-up Questions

**Interviewer**: "Can you solve this with O(1) extra space?"

**Candidate**: "We could sort both strings and compare. But sorting takes O(n log n) time and O(n) space for the sorted arrays. There's no true O(1) space solution that maintains O(n) time."

**Interviewer**: "What if strings are very long?"

**Candidate**: "The array approach is still efficient - O(n) time and O(1) space for lowercase letters. For very long strings, we could also use early exit: if strings have different lengths, they can't be anagrams."

**Interviewer**: "What about case sensitivity?"

**Candidate**: "We need to clarify requirements. If case matters, 'A' and 'a' are different. If not, we'd convert both to lowercase before counting."

### Tricky Edge Cases

1. **Different Lengths**: `"abc"` vs `"abcd"` → Return `false` immediately (early exit optimization)
2. **Empty Strings**: `""` vs `""` → Return `true` (both empty, technically anagrams)
3. **Single Character**: `"a"` vs `"a"` → Return `true`
4. **Same String**: `"hello"` vs `"hello"` → Return `true` (same string is an anagram of itself)
5. **Unicode Characters**: `"café"` vs `"éfac"` → Need HashMap approach (array only handles a-z)
6. **Whitespace**: `"anagram"` vs `"nag a ram"` → Depends on requirements (usually whitespace is ignored or treated as character)
7. **Case Sensitivity**: `"Hello"` vs `"hello"` → Depends on requirements (usually case-insensitive)
8. **Special Characters**: `"a!b"` vs `"b!a"` → Need HashMap or character filtering
9. **Numbers**: `"a1b"` vs `"b1a"` → Need HashMap or extended array
10. **All Same Character**: `"aaaa"` vs `"aaaa"` → Return `true`
11. **No Common Characters**: `"abc"` vs `"def"` → Return `false` (different characters)
12. **One Character Different Frequency**: `"aabb"` vs `"abbb"` → Return `false` (frequency mismatch)

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll start by checking if the strings have equal lengths - this is an early exit optimization. If lengths differ, they can't be anagrams. Then I'll create a frequency array of size 26 for lowercase letters. I'll iterate through the first string and increment counts for each character. Then I'll iterate through the second string and decrement counts. If any count goes negative during the second pass, it means the second string has more of that character than the first, so they're not anagrams. If we complete both passes without any negative counts and the lengths are equal, all counts must be zero, meaning they're anagrams."

**Interviewer**: "Why use an array instead of a HashMap?"

**Candidate**: "For lowercase English letters, an array is more space-efficient - O(1) space with fixed size 26, versus O(k) for HashMap where k is unique character count. Array access is also faster - direct index access O(1) versus hash computation and potential collision handling in HashMap. However, if we need to handle Unicode or mixed case, HashMap is the better choice."

**Interviewer**: "Can you optimize this further?"

**Candidate**: "Yes, we can eliminate the final check. Since we verify lengths are equal at the start, and we check for negative counts during the second pass, if we complete both passes without finding negatives, all counts must be zero. We don't need a third pass to verify."

## Solution Approaches

### Approach 1: Array Frequency Counter (Optimal for lowercase)
Use array[26] to count frequencies. O(n) time, O(1) space.

### Approach 2: HashMap (General case)
Use HashMap for any character set. O(n) time, O(k) space.

### Approach 3: Sorting
Sort both strings and compare. O(n log n) time, O(n) space.

## Key Takeaways

1. **Array is optimal** when character set is limited (e.g., a-z)
2. **HashMap is flexible** for any character set
3. **Early exit optimization** - check lengths first
4. **Frequency counting** is the key insight for anagram problems
