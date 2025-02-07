import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());

        long answer = 0;
        int small;
        for (int i = 0; i < n; i++) {
            small = 0;
            for (int j = n-1; j > i; j--) {
                if (nums[j] < nums[i]) small++;
                else answer += small;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
