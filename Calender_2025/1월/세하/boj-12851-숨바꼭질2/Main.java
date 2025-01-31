package boj_12851_숨바꼭질2;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		if (N >= K) {
			bw.write(N - K + "\n" + 1);
			bw.flush();

			return;
		}

		int ans = 987654321;
		int count = 0; // 최단시간으로 도달하는 경우의 수를 세는 변수

		int[] visited = new int[100001];

		Queue<int[]> queue = new ArrayDeque<>();

		queue.add(new int[] { N, 0 });
		visited[N]++;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();

			if (curr[0] == K) {
				if (ans == curr[1]) {
					count++;
				} else if (ans > curr[1]) {
					count = 1;
					ans = curr[1];
				}
			}

			int[] move = { curr[0] - 1, curr[0] + 1, curr[0] * 2 };
			int second = curr[1] + 1;

			for (int i = 0; i < 3; i++) {
				if (move[i] > 0 && move[i] <= 100000 && visited[move[i]] < 4) {
					visited[move[i]]++;
					queue.add(new int[] { move[i], second });
				}
			}
		}

		bw.write(ans + "\n" + count);
		bw.flush();
	}
}
