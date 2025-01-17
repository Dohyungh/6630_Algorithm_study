/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 앱_7579
 * Date: 2025.01.16
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
        int m = Integer.parseInt(input[1]);

        Program[] programs = new Program[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            programs[i] = new Program(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            programs[i].cost = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(programs);
        int[][] dp = new int[n+1][10001];
        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            int memo = programs[i-1].memory;
            int cost = programs[i-1].cost;

            for (int j = 0; j <= 10000; j++) {
                if (j < cost) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.max(dp[i-1][j-cost] + memo, dp[i-1][j]);

                if (dp[i][j] >= m) {
                    min = Math.min(min, j);
                    break;
                }
            }
        }

        bw.write(String.valueOf(min));
		bw.flush();
		bw.close();
		br.close();
	}
}

class Program implements Comparable<Program>{
    int memory;
    int cost;

    Program() {}

    Program(int memory) {
        this.memory = memory;
        this.cost = 0;
    }

    @Override
    public int compareTo(Program o) {
        return Integer.compare(this.cost, o.cost);
    }
}