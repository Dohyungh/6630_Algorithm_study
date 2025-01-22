/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 1의 개수 세기_9527
 * Date: 2025.01.22
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static long[] dp;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init();

        String[] input = br.readLine().split(" ");
        long A = Long.parseLong(input[0]);
        long B = Long.parseLong(input[1]);

        long fx_A = getOne(A - 1);
        long fx_B = getOne(B);

        bw.write(String.valueOf(fx_B - fx_A));
		bw.flush();
		bw.close();
		br.close();
	}

    private static void init()
    {
        dp = new long[56];
        dp[0] = 1;
        for (int i = 1; i < 56; i++) dp[i] = ((long) Math.pow(2, i)) + (dp[i-1] * 2);
    }

    private static long getOne(long num) {
        long copy = num;

        long cnt = 0;
        for (int i = 55; i > 0; i--) {
            if (((1L << i) & num) > 0) {
                copy -= (1L << i);
                cnt += dp[i-1] + (copy + 1);
            }
        }
        if ((num & 1) > 0) cnt++;
        return cnt;
    }
}