/**
 * LeetCode 605: Can Place Flowers
 * 
 * Problem: Check if n flowers can be planted without adjacent constraint.
 * 
 * Solution Approach: Greedy placement
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        
        if (flowerbed == null || flowerbed.length == 0) {
            return false;
        }
        
        int count = 0;
        
        for (int i = 0; i < flowerbed.length; i++) {
            // Check if current spot is empty
            if (flowerbed[i] == 0) {
                // Check left neighbor (or if at start)
                boolean leftEmpty = (i == 0) || (flowerbed[i - 1] == 0);
                // Check right neighbor (or if at end)
                boolean rightEmpty = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);
                
                if (leftEmpty && rightEmpty) {
                    flowerbed[i] = 1; // Plant flower
                    count++;
                    if (count >= n) {
                        return true;
                    }
                }
            }
        }
        
        return count >= n;
    }
}
