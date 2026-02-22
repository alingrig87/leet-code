# Solution Explanation: Top K Frequent Elements

## Approach 1: HashMap + Min Heap

### Intuition
Count frequencies, then use min heap to maintain top k. When heap exceeds k, remove least frequent.

### Algorithm
1. Count frequencies using HashMap
2. Create min heap (priority queue) of size k
3. For each frequency entry:
   - If heap size < k: add entry
   - Else if frequency > min in heap: replace min
4. Extract all elements from heap

### Complexity
- **Time**: O(n log k) - n elements, heap operations O(log k)
- **Space**: O(n) - HashMap and heap

## Approach 2: HashMap + Bucket Sort (Optimal)

### Intuition
Frequencies are at most n. Create buckets indexed by frequency, then traverse from highest to lowest.

### Algorithm
1. Count frequencies using HashMap
2. Create array of lists (buckets) of size n+1
3. Place each element in bucket at index = frequency
4. Traverse buckets from highest to lowest, collect k elements

### Complexity
- **Time**: O(n) - counting and bucket traversal
- **Space**: O(n) - buckets and HashMap

## Why Bucket Sort is Better

- O(n) vs O(n log k) time
- More efficient when k is large
- Simpler implementation in some cases
