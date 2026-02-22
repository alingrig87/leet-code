/**
 * LeetCode 66: Plus One
 * 
 * Problem: Given array representing a number, increment it by one.
 * 
 * Solution Approach: Process from right to left, handle carries
 * Time Complexity: O(n) worst case, O(1) best case
 * Space Complexity: O(1) if no expansion, O(n) if new array needed
 */
class Solution {
    
    /**
     * Main solution
     * 
     * Key insight: Process digits from right to left (least to most significant).
     * Add 1 to last digit. If result is 10, set to 0 and carry 1.
     * If all digits were 9, we need a new array.
     * 
     * @param digits Array representing a number
     * @return Array representing number + 1
     */
    public int[] plusOne(int[] digits) {
        // Edge case: empty array
        if (digits == null || digits.length == 0) {
            return new int[]{1};
        }
        
        // Start from rightmost (least significant) digit
        // Process digits from right to left
        for (int i = digits.length - 1; i >= 0; i--) {
            // Add 1 to current digit
            digits[i]++;
            
            // If digit is less than 10, no carry needed
            // We can return immediately (early exit optimization)
            if (digits[i] < 10) {
                return digits;
            }
            
            // If digit is 10, we have a carry
            // Set current digit to 0 and continue to next (left) digit
            // The carry will be handled by the increment in next iteration
            digits[i] = 0;
        }
        
        // If we reach here, all digits were 9
        // Example: [9,9,9] -> we need [1,0,0,0]
        // We need to create a new array with 1 at the beginning
        // followed by zeros
        int[] result = new int[digits.length + 1];
        result[0] = 1; // First digit is 1
        // Rest are zeros by default (Java initializes arrays with 0)
        
        return result;
    }
}

/**
 * Alternative: More explicit version
 */
class SolutionExplicit {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return new int[]{1};
        }
        
        int carry = 1; // Start with carry of 1 (the "plus one")
        
        // Process from right to left
        for (int i = digits.length - 1; i >= 0; i--) {
            // Add carry to current digit
            int sum = digits[i] + carry;
            
            // Update digit and calculate new carry
            digits[i] = sum % 10; // Remainder is the new digit
            carry = sum / 10;     // Quotient is the carry
            
            // If no carry, we're done (early exit)
            if (carry == 0) {
                return digits;
            }
        }
        
        // If carry is still 1 after processing all digits,
        // all digits were 9, need new array
        if (carry == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }
        
        return digits;
    }
}

/**
 * Follow-up: What if we need to add a different number?
 * 
 * We can generalize to add any number digit by digit.
 */
class SolutionAddNumber {
    public int[] addNumber(int[] digits, int number) {
        if (digits == null || digits.length == 0) {
            return new int[]{number};
        }
        
        // Convert number to array of digits
        List<Integer> numberDigits = new ArrayList<>();
        while (number > 0) {
            numberDigits.add(0, number % 10);
            number /= 10;
        }
        
        // Add two arrays digit by digit
        List<Integer> result = new ArrayList<>();
        int carry = 0;
        int i = digits.length - 1;
        int j = numberDigits.size() - 1;
        
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) sum += digits[i--];
            if (j >= 0) sum += numberDigits.get(j--);
            
            result.add(0, sum % 10);
            carry = sum / 10;
        }
        
        return result.stream().mapToInt(x -> x).toArray();
    }
}
