/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 가장 큰 증가하는 부분 수열_11055
 * Date: 2025.03.20
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());

        int answer = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + nums[i]);
            }
            answer = Math.max(answer, dp[i]);
        }

        bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}