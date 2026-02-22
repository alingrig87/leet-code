import java.util.*;

/**
 * LeetCode 36: Valid Sudoku
 * 
 * Problem: Determine if a 9x9 Sudoku board is valid.
 * 
 * Solution Approach: Use HashSet to track seen digits in rows, columns, and boxes
 * Time Complexity: O(1) - Fixed 9x9 board (81 cells)
 * Space Complexity: O(1) - Fixed number of HashSets
 */
class Solution {
    
    /**
     * Main solution using HashSet validation
     * 
     * Key insight: For each cell, we need to check if its digit
     * already exists in its row, column, or 3x3 box.
     * We use HashSet to track seen digits for each row, column, and box.
     * 
     * @param board 9x9 Sudoku board
     * @return true if board is valid, false otherwise
     */
    public boolean isValidSudoku(char[][] board) {
        // Edge case: null or invalid board
        if (board == null || board.length != 9 || board[0].length != 9) {
            return false;
        }
        
        // Create HashSet arrays to track seen digits
        // rows[i] = HashSet of digits seen in row i
        // cols[i] = HashSet of digits seen in column i
        // boxes[i] = HashSet of digits seen in box i (boxes numbered 0-8)
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] boxes = new HashSet[9];
        
        // Initialize all HashSets
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }
        
        // Iterate through each cell in the board
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char cell = board[row][col];
                
                // Skip empty cells (represented by '.')
                if (cell == '.') {
                    continue;
                }
                
                // Calculate which 3x3 box this cell belongs to
                // Boxes are numbered 0-8:
                // 0 1 2
                // 3 4 5
                // 6 7 8
                // Formula: boxIndex = (row / 3) * 3 + (col / 3)
                // row / 3 gives box row (0, 1, or 2)
                // col / 3 gives box column (0, 1, or 2)
                // Multiply box row by 3 and add box column to get box index
                int boxIndex = (row / 3) * 3 + (col / 3);
                
                // Check if this digit already exists in row, column, or box
                // If it exists in any of them, board is invalid
                if (rows[row].contains(cell) || 
                    cols[col].contains(cell) || 
                    boxes[boxIndex].contains(cell)) {
                    return false; // Duplicate found
                }
                
                // Add digit to row, column, and box HashSets
                // This marks that we've seen this digit in these locations
                rows[row].add(cell);
                cols[col].add(cell);
                boxes[boxIndex].add(cell);
            }
        }
        
        // If we've processed all cells without finding duplicates, board is valid
        return true;
    }
}

/**
 * Alternative: Using string encoding for boxes
 * 
 * Instead of calculating box index, encode box position as string.
 */
class SolutionStringEncoding {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9) {
            return false;
        }
        
        // Use single HashSet with encoded strings
        // Format: "row-5", "col-3", "box-2-1" (box row 2, box col 1)
        Set<String> seen = new HashSet<>();
        
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char cell = board[row][col];
                if (cell == '.') {
                    continue;
                }
                
                // Encode row, column, and box as strings
                String rowKey = "row-" + row + "-" + cell;
                String colKey = "col-" + col + "-" + cell;
                String boxKey = "box-" + (row / 3) + "-" + (col / 3) + "-" + cell;
                
                // Check if any encoding already exists
                if (!seen.add(rowKey) || !seen.add(colKey) || !seen.add(boxKey)) {
                    return false; // Duplicate found (add returns false if element exists)
                }
            }
        }
        
        return true;
    }
}

/**
 * Alternative: Using bit masks for space optimization
 * 
 * Use integers where each bit represents a digit (1-9).
 * More space efficient but code is more complex.
 */
class SolutionBitMask {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9) {
            return false;
        }
        
        // Use integer arrays where each bit represents a digit
        // Bit 0 = digit 1, bit 1 = digit 2, ..., bit 8 = digit 9
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];
        
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char cell = board[row][col];
                if (cell == '.') {
                    continue;
                }
                
                // Convert char to digit (1-9)
                int digit = cell - '0';
                
                // Create bit mask for this digit
                // digit 1 -> bit 0, digit 2 -> bit 1, etc.
                int mask = 1 << (digit - 1);
                
                // Calculate box index
                int boxIndex = (row / 3) * 3 + (col / 3);
                
                // Check if bit is already set (digit already seen)
                if ((rows[row] & mask) != 0 || 
                    (cols[col] & mask) != 0 || 
                    (boxes[boxIndex] & mask) != 0) {
                    return false;
                }
                
                // Set the bit to mark digit as seen
                rows[row] |= mask;
                cols[col] |= mask;
                boxes[boxIndex] |= mask;
            }
        }
        
        return true;
    }
}
