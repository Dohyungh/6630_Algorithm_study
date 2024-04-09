package boj_2098_외판원순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int N;
	static int answer = Integer.MAX_VALUE;
	static int[][] map, dp;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][1<<N];
		
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		// 첫번째 노드 먼저 방문하고 시작, 나부터니까 비용은 0
		// 현재 노드가 0번이고, 방문체크 했다는 의미
		// 출발 위치를 하나만 봐도 되는 이유는, 어디서 시작해도 사이클 돌면 결국 똑같아서
		dp[0][1] = 0;
		dfs(0, 1);
		
		System.out.println(answer);
		br.close();
		
	}
	
	private static void dfs(int now, int visited) {
		if (visited == (1<<N)-1) {
			if (map[now][0] == 0) return;
			int compare = dp[now][visited] + map[now][0];
			answer = Math.min(answer, compare);
			
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(dp[i]));
//			}
//			System.out.println();
			return;
		}
		
		for (int i = 0; i < N; i++) {
			int next = 1<<i;
			if ((visited | next) == visited) continue;
			if (map[now][i] == 0) continue;
			if (dp[now][visited] + map[now][i] < dp[i][visited | next]) {
				dp[i][visited | next] = dp[now][visited] + map[now][i];
				dfs(i, visited | next);
			}
		}
	}
}