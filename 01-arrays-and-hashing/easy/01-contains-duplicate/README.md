# Contains Duplicate

## Problem Statement
Given an integer array `nums`, return `true` if any value appears at least twice in the array, and return `false` if every element is distinct.

**Example 1:**
```
Input: nums = [1,2,3,1]
Output: true
```

**Example 2:**
```
Input: nums = [1,2,3,4]
Output: false
```

**Example 3:**
```
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
```

## Theory & Data Structures

### Hash Set (HashSet in Java)
A **Hash Set** is a data structure that stores unique elements. It provides average O(1) time complexity for insertion, deletion, and lookup operations.

#### How HashSet Works Internally
1. **Hash Function**: When you add an element, Java computes a hash code using the element's `hashCode()` method
2. **Buckets**: The hash code is used to determine which "bucket" to store the element in
3. **Collision Handling**: If two elements have the same hash code (collision), Java uses a linked list or tree structure within that bucket
4. **Uniqueness**: Before adding, HashSet checks if an element with the same hash code and equals() comparison already exists

#### Building a HashSet from Scratch (Conceptual)
```java
// Simplified conceptual implementation
class SimpleHashSet {
    private static final int INITIAL_CAPACITY = 16;
    private Node[] buckets;
    private int size;
    
    class Node {
        int key;
        Node next;
        
        Node(int key) {
            this.key = key;
        }
    }
    
    SimpleHashSet() {
        buckets = new Node[INITIAL_CAPACITY];
        size = 0;
    }
    
    // Hash function to determine bucket index
    private int hash(int key) {
        return Math.abs(key) % buckets.length;
    }
    
    // Add element
    public boolean add(int key) {
        int index = hash(key);
        Node current = buckets[index];
        
        // Check if key already exists
        while (current != null) {
            if (current.key == key) {
                return false; // Already exists
            }
            current = current.next;
        }
        
        // Add new node at the beginning of the bucket
        Node newNode = new Node(key);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
        return true;
    }
    
    // Check if key exists
    public boolean contains(int key) {
        int index = hash(key);
        Node current = buckets[index];
        
        while (current != null) {
            if (current.key == key) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
```

### Time & Space Complexity

#### Approach 1: Using HashSet
- **Time Complexity**: O(n) - We iterate through the array once
- **Space Complexity**: O(n) - In the worst case, we store all n elements in the HashSet

#### Approach 2: Sorting
- **Time Complexity**: O(n log n) - Sorting takes O(n log n) time
- **Space Complexity**: O(1) - If we use in-place sorting, or O(n) if we create a new sorted array

## Interview Simulation

### Initial Discussion

**Interviewer**: "Given an array of integers, can you determine if there are any duplicates?"

**Candidate**: "Yes, I can solve this in a few ways. The most straightforward approach would be to use a HashSet to track elements we've seen. As we iterate through the array, if we encounter an element that's already in the set, we know there's a duplicate."

**Interviewer**: "Good. Can you walk me through your solution?"

**Candidate**: "Sure. I'll iterate through the array once. For each element, I'll check if it exists in the HashSet. If it does, return true immediately. If not, add it to the HashSet and continue. If we finish the iteration without finding duplicates, return false."

**Interviewer**: "What's the time and space complexity?"

**Candidate**: "Time complexity is O(n) since we visit each element once. Space complexity is O(n) in the worst case when all elements are unique and we store them all."

### Follow-up Questions

**Interviewer**: "Can you solve this with O(1) extra space?"

**Candidate**: "Yes, we can sort the array first, then check adjacent elements. If any two adjacent elements are equal, we have a duplicate. However, sorting modifies the input array and takes O(n log n) time."

**Interviewer**: "What if the array is very large and memory is a concern?"

**Candidate**: "If memory is a constraint, sorting might be better since we can do in-place sorting with O(1) extra space, though it's slower. Alternatively, we could use a Bloom filter for a probabilistic approach, but that might have false positives."

**Interviewer**: "What edge cases should we consider?"

**Candidate**: "Edge cases include:
- Empty array: should return false
- Single element: should return false
- Array with all same elements: should return true
- Array with negative numbers: HashSet handles this fine
- Very large array: need to consider memory constraints"

**Interviewer**: "Can you handle this if the array contains very large numbers?"

**Candidate**: "Yes, HashSet in Java can handle integers up to Integer.MAX_VALUE. If we're dealing with long values, we'd use HashSet<Long>. The hash function will still work correctly."

### Tricky Edge Cases

1. **Empty Array**: `[]` → Returns `false` (no duplicates in empty array)
2. **Single Element**: `[5]` → Returns `false` (can't have duplicate with one element)
3. **All Same Elements**: `[1,1,1,1]` → Returns `true`
4. **Negative Numbers**: `[-1, 2, -1]` → Returns `true` (HashSet handles negatives)
5. **Large Numbers**: `[Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE]` → Returns `true`
6. **Mixed Signs**: `[0, -0, 0]` → Returns `true` (though -0 and 0 are equal in Java)

### Code Walkthrough (What Interviewer Expects)

**Interviewer**: "As you write the code, explain your thought process."

**Candidate**: "I'll start by creating a HashSet to store seen elements. Then I'll iterate through the array. For each number, I check if it's already in the set. If yes, we found a duplicate, so return true. Otherwise, add it to the set. After the loop, if we haven't returned, there are no duplicates."

**Interviewer**: "Why HashSet and not ArrayList?"

**Candidate**: "HashSet provides O(1) average-case lookup time, while ArrayList would require O(n) time to check if an element exists, making the overall solution O(n²)."

## Solution Approaches

### Approach 1: HashSet (Optimal for Time)
Use a HashSet to track seen elements. O(n) time, O(n) space.

### Approach 2: Sorting
Sort the array and check adjacent elements. O(n log n) time, O(1) space if in-place.

### Approach 3: Brute Force
Compare each element with all others. O(n²) time, O(1) space. Not recommended.

## Key Takeaways

1. **HashSet is ideal** when you need O(1) lookup time
2. **Trade-off**: Time vs Space - HashSet is faster but uses more memory
3. **Sorting alternative** when memory is constrained
4. **Always consider edge cases** before coding
