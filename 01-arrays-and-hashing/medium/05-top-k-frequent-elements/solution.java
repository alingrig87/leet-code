import java.util.*;

/**
 * LeetCode 347: Top K Frequent Elements
 * 
 * Problem: Find k most frequent elements in array.
 * 
 * Solution Approach: HashMap + Min Heap
 * Time Complexity: O(n log k) - n elements, heap operations O(log k)
 * Space Complexity: O(n) - HashMap and heap
 */
class Solution {
    
    /**
     * Main solution using HashMap + Min Heap
     * 
     * Key insight: Count frequencies, then use min heap of size k
     * to maintain top k frequent elements. When heap exceeds k,
     * remove the least frequent element.
     * 
     * @param nums Input array
     * @param k Number of top frequent elements to return
     * @return Array of k most frequent elements
     */
    public int[] topKFrequent(int[] nums, int k) {
        // Edge case: k equals array length, return all
        if (k == nums.length) {
            return nums;
        }
        
        // Step 1: Count frequency of each element
        // HashMap: key = element, value = frequency count
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            // Increment frequency count for this element
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Step 2: Use min heap (priority queue) to maintain top k frequent elements
        // Min heap means smallest frequency is at top
        // We'll keep only k elements in heap
        // Comparator compares by frequency (smallest frequency at top)
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = 
            new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        
        // Step 3: Process each frequency entry
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            // Add entry to heap
            minHeap.offer(entry);
            
            // If heap size exceeds k, remove the least frequent element
            // This keeps only the k most frequent elements
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove element with smallest frequency
            }
        }
        
        // Step 4: Extract elements from heap
        // Heap now contains exactly k most frequent elements
        int[] result = new int[k];
        int index = 0;
        
        // Extract from heap (they come out in reverse order of frequency)
        while (!minHeap.isEmpty()) {
            result[index++] = minHeap.poll().getKey();
        }
        
        return result;
    }
}

/**
 * Alternative Solution: HashMap + Bucket Sort (Optimal)
 * 
 * Since frequencies are at most n (array length), we can use bucket sort.
 * Create buckets indexed by frequency, then traverse from highest to lowest.
 * 
 * Time Complexity: O(n) - counting and bucket traversal
 * Space Complexity: O(n) - buckets and HashMap
 */
class SolutionBucketSort {
    public int[] topKFrequent(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }
        
        // Step 1: Count frequencies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Step 2: Create buckets
        // Buckets are indexed by frequency (0 to n)
        // Each bucket contains list of elements with that frequency
        // Size is nums.length + 1 because frequency can be at most nums.length
        List<Integer>[] buckets = new List[nums.length + 1];
        
        // Initialize buckets
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        
        // Place each element in bucket at index = its frequency
        // Example: element with frequency 3 goes in buckets[3]
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int element = entry.getKey();
            int frequency = entry.getValue();
            buckets[frequency].add(element);
        }
        
        // Step 3: Traverse buckets from highest frequency to lowest
        // Collect k most frequent elements
        List<Integer> result = new ArrayList<>();
        
        // Start from highest frequency (end of buckets array)
        for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
            // Add all elements in this frequency bucket
            for (int element : buckets[i]) {
                result.add(element);
                if (result.size() == k) {
                    break; // We have k elements
                }
            }
        }
        
        // Convert list to array
        return result.stream().mapToInt(i -> i).toArray();
    }
}

/**
 * Alternative: Using Max Heap (less efficient)
 * 
 * Build max heap with all elements, then extract k elements.
 * Time: O(n log n), Space: O(n)
 */
class SolutionMaxHeap {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Max heap: largest frequency at top
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = 
            new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        
        // Add all entries to max heap
        maxHeap.addAll(freqMap.entrySet());
        
        // Extract top k elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll().getKey();
        }
        
        return result;
    }
}

/**
 * Follow-up: What if we need to return in order of frequency?
 * 
 * We can sort the result or use a different data structure.
 */
class SolutionSorted {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Create list of entries and sort by frequency (descending)
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(freqMap.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());
        
        // Extract top k
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = entries.get(i).getKey();
        }
        
        return result;
    }
}
