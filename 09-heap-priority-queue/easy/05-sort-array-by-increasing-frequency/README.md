# Sort Array by Increasing Frequency

## Problem Statement
Given an array of integers `nums`, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

**Example 1:**
```
Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
```

**Example 2:**
```
Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
```

## Theory & Data Structures

### HashMap + Custom Sort
This problem uses a **HashMap** to count frequencies, then sorts the array using a **custom comparator** that compares frequencies first, then values for ties.

#### Key Insight: Two-Level Sorting
- **Primary sort**: By frequency (ascending)
- **Secondary sort**: By value (descending) when frequencies are equal
- **Custom comparator**: Implements both criteria

#### Building Solution from Scratch (Conceptual)
```java
// Conceptual implementation of frequency sorting
class FrequencySorter {
    
    public int[] frequencySort(int[] nums) {
        // Count frequencies
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        // Convert to Integer array for sorting with custom comparator
        Integer[] numsInteger = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsInteger[i] = nums[i];
        }
        
        // Sort with custom comparator
        Arrays.sort(numsInteger, (a, b) -> {
            int freqA = freq.get(a);
            int freqB = freq.get(b);
            // Primary: compare frequencies
            if (freqA != freqB) {
                return freqA - freqB;  // Ascending
            }
            // Secondary: compare values (descending for ties)
            return b - a;  // Descending
        });
        
        // Convert back to int array
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = numsInteger[i];
        }
        
        return result;
    }
    
    // Alternative: Using List and Collections.sort
    public int[] frequencySortList(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        
        Collections.sort(list, (a, b) -> {
            int freqA = freq.get(a);
            int freqB = freq.get(b);
            if (freqA != freqB) {
                return freqA - freqB;
            }
            return b - a;
        });
        
        return list.stream().mapToInt(i -> i).toArray();
    }
}
```

### Time & Space Complexity

#### Approach: HashMap + Custom Sort
- **Time Complexity**: O(n log n) - Sorting dominates
  - Frequency counting: O(n)
  - Sorting: O(n log n)
  - Total: O(n log n)
- **Space Complexity**: O(n) - HashMap and result
  - HashMap: O(n) worst case
  - Result: O(n)

## Interview Simulation

### Initial Discussion

**Interviewer**: "Sort array by frequency, then by value for ties."

**Candidate**: "I'll count frequencies using a HashMap. Then I'll sort the array using a custom comparator that compares frequencies first (ascending), and for ties, compares values in descending order."

**Interviewer**: "Why descending for value ties?"

**Candidate**: "The problem states that if multiple values have the same frequency, they should be sorted in decreasing order. So when frequencies are equal, larger values come first."

**Interviewer**: "Can you walk me through an example?"

**Candidate**: "Sure. For [1,1,2,2,2,3], frequencies: 1->2, 2->3, 3->1. Sort by frequency: 3 (freq=1), then 1 (freq=2), then 2 (freq=3). Result: [3,1,1,2,2,2]."

**Interviewer**: "What's the complexity?"

**Candidate**: "Time complexity is O(n log n) for sorting, O(n) for frequency counting, so overall O(n log n). Space complexity is O(n) for the HashMap and result."

### Follow-up Questions

**Interviewer**: "Can you do it without sorting?"

**Candidate**: "We could use bucket sort if frequencies are bounded. We'd create buckets for each frequency, then sort values within each bucket. But for the general case, comparison-based sorting is needed."

**Interviewer**: "What if we need to sort by decreasing frequency?"

**Candidate**: "Then we'd reverse the frequency comparison - use `freqB - freqA` instead of `freqA - freqB`."

### Tricky Edge Cases

1. **All same frequency**: Sort by value descending
2. **All unique**: Sort by value descending
3. **Mixed frequencies**: Sort by frequency ascending
4. **Empty array**: Return empty
5. **Single element**: Return as is
6. **All same value**: Return as is

## Solution Approaches

### Approach: HashMap + Custom Sort (Optimal)
Count frequencies, sort with custom comparator. O(n log n) time, O(n) space.

**Algorithm:**
1. Count frequencies using HashMap
2. Sort array with custom comparator:
   - Primary: frequency (ascending)
   - Secondary: value (descending)
3. Return sorted array

**Advantages:**
- O(n log n) time complexity
- Simple and efficient
- Optimal solution

## Key Takeaways

1. **HashMap** for frequency counting
2. **Custom comparator** for sorting
3. **Frequency first**, then value
4. **O(n log n)** solution
5. **Foundation for** sorting problems with multiple criteria
