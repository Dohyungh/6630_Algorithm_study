import java.util.*;

public class Main {
    static char[][] matrix = new char[5][5];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        String key = sc.nextLine();

        createMatrix(key);

        String pairs = preparePairs(message);

        System.out.println(encrypt(pairs));
    }
    
    static void createMatrix(String key) {
        boolean[] used = new boolean[26];
        used['J' - 'A'] = true;
        int row = 0, col = 0;
        
       
        for (char c : (key + "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray()) {
            if (c == 'J') continue;
            if (!used[c - 'A']) {
                matrix[row][col] = c;
                used[c - 'A'] = true;
                if (++col == 5) {
                    col = 0;
                    row++;
                }
            }
        }
    }
    
    static String preparePairs(String message) {
        StringBuilder pairs = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            pairs.append(message.charAt(i));
            if (i == message.length() - 1) {
                pairs.append('X');
                break;
            }
            
            if (message.charAt(i) == message.charAt(i + 1)) {
                if (message.charAt(i) == 'X')
                    pairs.append('Q');
                else
                    pairs.append('X');
            } else {
                pairs.append(message.charAt(++i));
            }
        }
        return pairs.toString();
    }
    
    static String encrypt(String pairs) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < pairs.length(); i += 2) {
            char c1 = pairs.charAt(i);
            char c2 = pairs.charAt(i + 1);
            int[] pos1 = findPosition(c1);
            int[] pos2 = findPosition(c2);
            
            if (pos1[0] == pos2[0]) { 
                result.append(matrix[pos1[0]][(pos1[1] + 1) % 5]);
                result.append(matrix[pos2[0]][(pos2[1] + 1) % 5]);
            }
            else if (pos1[1] == pos2[1]) {
                result.append(matrix[(pos1[0] + 1) % 5][pos1[1]]);
                result.append(matrix[(pos2[0] + 1) % 5][pos2[1]]);
            }
            else { 
                result.append(matrix[pos1[0]][pos2[1]]);
                result.append(matrix[pos2[0]][pos1[1]]);
            }
        }
        
        return result.toString();
    }
    
    static int[] findPosition(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}