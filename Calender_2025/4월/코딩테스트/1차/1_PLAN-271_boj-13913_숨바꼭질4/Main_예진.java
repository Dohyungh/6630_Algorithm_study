/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 숨바꼭질 4_13913
 * Date: 2025.04.05
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
        int start = Integer.parseInt(input[0]);
        int end = Integer.parseInt(input[1]);

        int[] dp = new int[200_001];
        int[] pre = new int[200_001];
        Arrays.fill(dp, 100_001);
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        dp[start] = 0;

        int dist = 0;
        int answer = 0;

        out : while (!queue.isEmpty()) {

            int size = queue.size();
            dist++;
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();

                if (curr == end) {
                    answer = dp[curr];
                    break out;
                }

                int next = curr - 1;
                if (next >= 0 && dp[next] > dist) {
                    dp[next] = dist;
                    pre[next] = curr;
                    queue.add(next);
                }
                next = curr + 1;
                if (next <= 100_000 && dp[next] > dist) {
                    dp[next] = dist;
                    pre[next] = curr;
                    queue.add(next);
                }
                next = curr * 2;
                if (next <= 200_000 && dp[next] > dist) {
                    dp[next] = dist;
                    pre[next] = curr;
                    queue.add(next);
                }
            }

        }
		bw.write(String.valueOf(answer) + " \n");
        int[] route = new int[answer+1];
        int curr = end;
        for (int i = answer; i >= 0; i--) {
            route[i] = curr;
            curr = pre[curr];
        }
        for (int i = 0; i <= answer; i++) bw.write(String.valueOf(route[i]) + " ");
		bw.flush();
		bw.close();
		br.close();
	}
}