package SWEA._7272_안경이없어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution_1등 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            
            int check[] = {1, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
            
            
            String A = st.nextToken();
            String B = st.nextToken();
            String result = "SAME";
            if (A.length() == B.length()) {
                for (int i = 0; i < A.length(); i++) {
                    if (check[A.charAt(i) - 'A'] != check[B.charAt(i) - 'A']) {
                        result = "DIFF";
                        break;
                    }
                }
            } else result = "DIFF";
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}