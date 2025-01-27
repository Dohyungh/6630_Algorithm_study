/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 동전 2_2294
 * Date: 2025.01.28
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
		
		String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < n; i++) nums.add(Integer.parseInt(br.readLine()));
        List<Integer> coins = new ArrayList<>(nums);
        Collections.sort(coins);

        int[] dp = new int[k+1];
        Arrays.fill(dp, -1);
        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                if (i % coin == 0) dp[i] = i / coin;
                else if (dp[i-coin] > 0) {
                    if (dp[i] > 0) dp[i] = Math.min(dp[i], dp[i-coin] + 1);
                    else dp[i] = dp[i-coin] + 1;
                }
            }
//            System.out.println(Arrays.toString(dp));
        }


        bw.write(String.valueOf(dp[k]));
		bw.flush();
		bw.close();
		br.close();
	}
}