package BOJ._14501_퇴사;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] T = new int[N];
        int[] P = new int[N];

        int[][] dp = new int[N][2];
        for (int i = 0; i < N; i++) {
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }

        if (T[N-1] == 1) {
            dp[N-1][1] = P[N-1];
        } else {
            dp[N-1][1] = 0;
        }

        dp[N-1][0] = 0;

        for (int i = N-2; i >= 0; i--) {
            int currP = P[i];
            int currT = T[i];

            // 넘어갔으면,
            if (i + currT > N && i+1 < N) {
                dp[i][0] = Math.max(dp[i+1][0], dp[i+1][1]);
                dp[i][1] = Math.max(dp[i+1][0], dp[i+1][1]);
                continue;
            }
            dp[i][0] = Math.max(dp[i+1][0], dp[i+1][1]);
            if (i + currT >= N) {
                dp[i][1] = currP;
            } else {

                dp[i][1] = Math.max(dp[i+currT][0], dp[i+currT][1]) + currP;
            }


        }

        System.out.println(Math.max(dp[0][0], dp[0][1]));
    }
}
