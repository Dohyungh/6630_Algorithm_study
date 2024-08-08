package boj_9466_텀프로젝트;

import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int[] next, before, isPossible; // isPossible이 1이면 불가능, 2이면 가능
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());

			next = new int[n + 1];
			before = new int[n + 1];
			isPossible = new int[n + 1];
			visited = new boolean[n + 1];

			st = new StringTokenizer(br.readLine());

			for (int i = 1; i <= n; i++) {
				next[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= n; i++) {
				if (!visited[i]) {
					dfs(i, 0);
				}
			}

			int ans = 0;

			for (int i = 1; i <= n; i++) {
				if (isPossible[i] == 1) {
					ans++;
				}
			}

			System.out.println(ans);
		}
	}

	public static void dfs(int i, int be) {
		if (visited[i]) {
			// 방문할 노드에서 isPossible 값이 있으면 이미 팀에 속한 것이므로 이전 노드들은 전부 불가능 
			if (isPossible[i] != 0) {
				setImpossible(be);
				return;
			}
			// isPossible 값이 없으면 next 노드들끼리 팀을 이룰 수 있다.
			setPossible(next[i]);
			// before 노드들은 팀을 이룰 수 없다. 
			setImpossible(before[i]);
			return;
		}

		visited[i] = true;
		before[i] = be;
		dfs(next[i], i);
	}

	public static void setPossible(int i) {
		// isPossible 값이 있으면 중단 
		if (isPossible[i] != 0)
			return;
		
		isPossible[i] = 2;
		setPossible(next[i]);
	}

	public static void setImpossible(int i) {
		// 이전 노드가 없거나 isPossible 값이 있으면 중단 
		if (i == 0 || isPossible[i] != 0)
			return;

		isPossible[i] = 1;
		setImpossible(before[i]);
	}
}