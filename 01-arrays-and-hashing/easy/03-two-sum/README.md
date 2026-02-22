# Two Sum

## Problem Statement
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

**Example 1:**
```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
```

**Example 2:**
```
Input: nums = [3,2,4], target = 6
Output: [1,2]
```

**Example 3:**
```
Input: nums = [3,3], target = 6
Output: [0,1]
```

## Theory & Data Structures

### Hash Map (HashMap in Java)
A **Hash Map** stores key-value pairs and provides average O(1) time complexity for insertion, deletion, and lookup operations.

#### How HashMap Works Internally
1. **Hash Function**: Computes hash code from the key using `hashCode()` method
2. **Buckets**: Hash code determines which bucket to store the entry
3. **Collision Handling**: Uses chaining (linked list) or open addressing for collisions
4. **Load Factor**: When load factor exceeds threshold (default 0.75), HashMap resizes and rehashes

#### Building a HashMap from Scratch (Conceptual)
```java
class SimpleHashMap {
    private static final int INITIAL_CAPACITY = 16;
    private Entry[] buckets;
    
    class Entry {
        int key;
        int value;
        Entry next;
        
        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    SimpleHashMap() {
        buckets = new Entry[INITIAL_CAPACITY];
    }
    
    private int hash(int key) {
        return Math.abs(key) % buckets.length;
    }
    
    public void put(int key, int value) {
        int index = hash(key);
        Entry current = buckets[index];
        
        // Check if key exists, update value
        while (current != null) {
            if (current.key == key) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        
        // Add new entry
        Entry newEntry = new Entry(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
    }
    
    public Integer get(int key) {
        int index = hash(key);
        Entry current = buckets[index];
        
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
}
```

### Time & Space Complexity

#### Approach 1: HashMap (One Pass)
- **Time Complexity**: O(n) - Single iteration through array
- **Space Complexity**: O(n) - HashMap stores at most n elements

#### Approach 2: Brute Force
- **Time Complexity**: O(n²) - Nested loops
- **Space Complexity**: O(1) - No extra space

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given an array and a target sum, find two numbers that add up to the target."

**Candidate**: "I can solve this using a HashMap. As I iterate through the array, for each number, I'll check if its complement (target - current number) exists in the map. If it does, I've found the pair. If not, I'll store the current number and its index in the map."

**Interviewer**: "Why HashMap and not just iterate twice?"

**Candidate**: "Using HashMap gives us O(n) time complexity instead of O(n²). We trade space for time - use O(n) extra space to achieve O(n) time instead of O(n²)."

### Follow-up Questions

**Interviewer**: "What if the array is sorted?"

**Candidate**: "If sorted, we can use two pointers - one at the start, one at the end. Move them based on whether the sum is less than or greater than target. This gives O(n) time and O(1) space."

**Interviewer**: "What if we need to return the numbers, not indices?"

**Candidate**: "Same approach, but we can store the numbers themselves instead of indices. Or if we need indices, we can still use the same HashMap approach."

**Interviewer**: "What if there are multiple solutions?"

**Candidate**: "The problem states there's exactly one solution, but if there were multiple, we'd return a list of all pairs. We'd need to be careful about using the same element twice."

### Tricky Edge Cases

1. **Same Element Twice**: `[3,3], target=6` → Must use different indices
2. **Negative Numbers**: `[-1, -2, -3, -4], target=-7` → HashMap handles negatives
3. **Zero in Array**: `[0, 1, 2], target=1` → Zero can be part of solution
4. **Target is Zero**: `[-1, 1], target=0` → Negative and positive pairs
5. **Large Numbers**: Need to watch for integer overflow in sum

### Code Walkthrough

**Interviewer**: "Walk me through your code as you write it."

**Candidate**: "I'll create a HashMap to store number-to-index mappings. As I iterate, for each number, I calculate complement = target - current number. If complement exists in map, I return both indices. Otherwise, I store current number and its index for future lookups."

## Solution Approaches

### Approach 1: HashMap (Optimal)
Use HashMap to store number-index pairs. For each number, check if complement exists. O(n) time, O(n) space.

### Approach 2: Two Pointers (Sorted Array)
If array is sorted, use two pointers. O(n) time, O(1) space.

### Approach 3: Brute Force
Check all pairs. O(n²) time, O(1) space.

## Key Takeaways

1. **HashMap enables O(n) solution** by trading space for time
2. **One-pass optimization** - check and store in same iteration
3. **Complement strategy** - look for target - current instead of checking all pairs
4. **Index vs Value** - Store index in map, not just value
