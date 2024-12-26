/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 플로이드_11404
 * Date: 2024.12.26
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
    static final int MAX = 1_000_000_000;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], MAX);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            cost[start][end] = Math.min(cost[start][end], c);
        }

        for (int v = 0; v < n; v++) {
            for (int start = 0; start < n; start++) {
                if (start == v) continue;
                for (int end = 0; end < n; end++) {
                    if (start == end || v == end) continue;
                    cost[start][end] = Math.min(cost[start][end], cost[start][v] + cost[v][end]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cost[i][j] == MAX) cost[i][j] = 0;
                sb.append(cost[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}