package boj_1389_케빈베이컨의6단계법칙;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Integer>[] relation = new ArrayList[N + 1];
		int[][] minArr = new int[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			Arrays.fill(minArr[r], 987654321);
		}

		minArr[N][N] = 0;

		for (int i = 1; i <= N; i++) {
			relation[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			relation[A].add(B);
			relation[B].add(A);
		}

		for (int i = 1; i < N; i++) {
			boolean[] visited = new boolean[N + 1];

			Queue<int[]> queue = new ArrayDeque<>();

			queue.add(new int[] { i, 0 });

			while (!queue.isEmpty()) {
				int[] curr = queue.poll();

				minArr[i][curr[0]] = Math.min(minArr[i][curr[0]], curr[1]);
				minArr[curr[0]][i] = minArr[i][curr[0]];

				for (Integer j : relation[curr[0]]) {
					if (!visited[j]) {
						queue.add(new int[] { j, curr[1] + 1 });
						visited[j] = true;
					}
				}
			}
		}

		int ans = -1;
		int min = 987654321;

		for (int r = 1; r <= N; r++) {
			int sum = 0;

			for (int c = 1; c <= N; c++) {
				sum += minArr[r][c];
			}

			if (sum < min) {
				min = sum;
				ans = r;
			}

			ans = Math.min(ans, sum);
		}

		System.out.println(ans);
	}
}
