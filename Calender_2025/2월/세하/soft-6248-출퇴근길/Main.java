package soft_6248_출퇴근길;

import java.io.*;
import java.util.*;

public class Main {
	static int n, m, S, T;
	static ArrayList<Integer>[] forward;
	static ArrayList<Integer>[] backward;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		forward = new ArrayList[n + 1];
		backward = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			forward[i] = new ArrayList<>();
			backward[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			forward[x].add(y);
			backward[y].add(x);
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		boolean[] fromStart = bfs(S, T, forward);
		boolean[] fromEnd = bfs(T, S, backward);

		int answer = 0;
		for (int i = 1; i <= n; i++) {
			if (fromStart[i] && fromEnd[i])
				answer++;
		}

		System.out.println(answer);
	}

	static boolean[] bfs(int start, int end, ArrayList<Integer>[] graph) {
		boolean[] visited = new boolean[n + 1];
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			int curr = q.poll();

			for (int next : graph[curr]) {
				if (!visited[next] && next != end) {
					visited[next] = true;
					q.offer(next);
				}
			}
		}

		// 도착점도 방문 가능한지 확인
		if (!visited[end]) {
			for (int prev : graph[end]) {
				if (visited[prev]) {
					visited[end] = true;
					break;
				}
			}
		}

		return visited;
	}
}
