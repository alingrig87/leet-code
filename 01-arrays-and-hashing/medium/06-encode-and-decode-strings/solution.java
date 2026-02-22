import java.util.*;

/**
 * LeetCode 271: Encode and Decode Strings
 * 
 * Problem: Design encode/decode for list of strings.
 * 
 * Solution Approach: Length prefix encoding
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class Codec {
    
    /**
     * Encodes a list of strings to a single string.
     * Format: "length#string" for each string
     */
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append('#').append(str);
        }
        return sb.toString();
    }
    
    /**
     * Decodes a single string to a list of strings.
     */
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;
        
        while (i < s.length()) {
            // Read length until '#'
            int j = i;
            while (s.charAt(j) != '#') {
                j++;
            }
            
            int length = Integer.parseInt(s.substring(i, j));
            i = j + 1; // Skip '#'
            
            // Read 'length' characters
            result.add(s.substring(i, i + length));
            i += length;
        }
        
        return result;
    }
}
