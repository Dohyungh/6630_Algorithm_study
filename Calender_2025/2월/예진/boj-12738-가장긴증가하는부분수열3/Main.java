/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 가장 긴 증가하는 부분 수열 3_12738
 * Date: 2025.02.14
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static int[] dp;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());

        dp = new int[n];
        dp[0] = nums[0];
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > dp[cnt-1]) dp[cnt++] = nums[i];
            else if (nums[i] < dp[cnt-1]) {
                dp[binarySearch(nums[i], cnt-1)] = nums[i];
            }
        }

        bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();
		br.close();
	}

    public static int binarySearch(int num, int h) {
        int low = 0;
        int high = h;

        int mid;
        while (low < high) {
            mid = (low + high) / 2;

            if (dp[mid] > num) high = mid;
            else if (dp[mid] < num) low = mid + 1;
            else return mid;
        }

        return high;
    }
}