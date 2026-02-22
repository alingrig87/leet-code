# Valid Palindrome

## Problem Statement
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

**Example 1:**
```
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
```

**Example 2:**
```
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
```

## Theory & Data Structures

### Two Pointers
This problem uses **two pointers** starting from both ends of the string, moving towards the center while comparing characters.

#### Key Insight: Clean and Compare
- **Skip non-alphanumeric**: Ignore characters that aren't letters or digits
- **Case insensitive**: Convert to lowercase before comparison
- **Two pointers**: One from start, one from end
- **Compare**: Check if characters match

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of palindrome check
class PalindromeChecker {
    
    // Two pointers approach
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            
            // Skip non-alphanumeric from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare characters (case insensitive)
            if (Character.toLowerCase(s.charAt(left)) != 
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    // Alternative: Clean string first, then check
    public boolean isPalindromeCleanFirst(String s) {
        // Clean string: remove non-alphanumeric, convert to lowercase
        StringBuilder cleaned = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        
        String cleanedStr = cleaned.toString();
        int left = 0, right = cleanedStr.length() - 1;
        
        while (left < right) {
            if (cleanedStr.charAt(left) != cleanedStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    // Using built-in methods
    public boolean isPalindromeBuiltIn(String s) {
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }
}
```

### Time & Space Complexity

#### Approach: Two Pointers
- **Time Complexity**: O(n) - Single pass through string
  - Best case: O(n/2) - Mismatch early
  - Average case: O(n)
  - Worst case: O(n) - Must check all characters
- **Space Complexity**: O(1) - Only pointers
  - No extra data structures
  - Constant space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Check if a string is a palindrome after cleaning."

**Candidate**: "I'll use two pointers starting from both ends. I'll skip non-alphanumeric characters, convert to lowercase, and compare characters. If all characters match, it's a palindrome."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For 'A man, a plan, a canal: Panama', I start with left=0 ('A') and right=length-1 ('a'). I skip non-alphanumeric if needed, convert to lowercase, compare. 'a' == 'a', move pointers. Continue until pointers meet. If all match, return true."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n) since we make a single pass through the string. Space complexity is O(1) since we only use pointers."

### Follow-up Questions

**Interviewer**: "Can you do it with O(1) space?"

**Candidate**: "Yes, the two-pointer approach already uses O(1) space. We don't create a new string - we just skip and compare in place."

**Interviewer**: "What if we need to preserve the original string?"

**Candidate**: "We'd need to create a cleaned version first, which would use O(n) space. But for just checking, the two-pointer approach is optimal."

### Tricky Edge Cases

1. **Empty string**: Return true (empty is palindrome)
2. **Only non-alphanumeric**: Return true (empty after cleaning)
3. **Mixed case**: Handle with lowercase conversion
4. **Numbers**: Include in comparison
5. **Single character**: Return true
6. **All same character**: Return true

## Solution Approaches

### Approach 1: Two Pointers (Optimal)
Two pointers from ends, skip non-alphanumeric, compare. O(n) time, O(1) space.

**Algorithm:**
1. Initialize left=0, right=length-1
2. While left < right:
   - Skip non-alphanumeric from left
   - Skip non-alphanumeric from right
   - Compare lowercase characters
   - If mismatch, return false
   - Move pointers
3. Return true

**Advantages:**
- O(1) space complexity
- O(n) time complexity
- Optimal solution

### Approach 2: Clean First
Clean string, then check. O(n) time, O(n) space.

**Disadvantages:**
- O(n) space for cleaned string
- Less efficient

## Key Takeaways

1. **Two pointers** for palindrome check
2. **Skip non-alphanumeric** characters
3. **Case insensitive** comparison
4. **O(1) space** solution
5. **O(n) time** complexity
6. **Handle edge cases** - empty, only non-alphanumeric
7. **Foundation for** palindrome-related problems
