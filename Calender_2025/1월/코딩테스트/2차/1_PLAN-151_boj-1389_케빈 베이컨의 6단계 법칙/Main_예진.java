package 코테_boj_1389_케빈베이컨의6단계법칙;

/**
 * Author: yngbao97, Yuk Yejin
 */

import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static final int MAX = 20000;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new InputStreamReader(new FileInputStream("src/"+Main.class.getPackage().getName()+"/input.txt")));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		
		int[][] dist = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) Arrays.fill(dist[i], MAX);
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			dist[a][b] = 1;
			dist[b][a] = 1;
		}
		
		for (int mid = 1; mid <= n; mid++) {
			for (int start = 1; start <= n; start++) {
				if (mid == start) continue;
				for (int end = 1; end <= n; end++) {
					if (end == start || end == mid) continue;
					dist[start][end] = Math.min(dist[start][end], dist[start][mid] + dist[mid][end]);
				}
			}
		}
		
		int answer = 0;
		int max = 20000;
		for (int i = 1; i <= n; i++) {
			int sum = 0;
			for (int j = 1; j <= n; j++) {
				if (i == j) continue;
				sum += dist[i][j];
			}
			if (sum < max) {
				max = sum;
				answer = i;
			}
		}

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();

	}
}
