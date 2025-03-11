package boj_1987_알파벳;

import java.io.*;
import java.util.*;

public class Main {
	static int R, C, ans;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static char[][] alphabets;
	static boolean[][] visited;
	static Map<Character, Boolean> alphabetVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		alphabets = new char[R][C];
		visited = new boolean[R][C]; // 보드 칸 방문 체크
		alphabetVisited = new HashMap<>(); // Map으로 알파벳 방문 체크

		for (int r = 0; r < R; r++) {
			alphabets[r] = br.readLine().toCharArray();
		}

		// <key: 알파벳 A-Z, value: 방문 여부> 가 되도록 Map에 값 입력
		for (char c = 'A'; c <= 'Z'; c++) {
			alphabetVisited.put(c, false);
		}

		ans = 1;

		visited[0][0] = true;
		alphabetVisited.put(alphabets[0][0], true);

		dfs(0, 0, 1);

		System.out.println(ans);
	}

	public static void dfs(int r, int c, int move) {
		ans = Math.max(ans, move); // 이동한 칸 수의 최대값 저장

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 아직 보드의 (nr, nc)에 방문하지 않았고, alphabets[nr][nc]에 해당하는 알파벳이 경로에 포함되지 않을 때 DFS 시행
			if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && !alphabetVisited.get(alphabets[nr][nc])) {
				visited[nr][nc] = true; // 보드 방문 체크
				alphabetVisited.put(alphabets[nr][nc], true); // 알파벳 방문 체크
				dfs(nr, nc, move + 1);
				visited[nr][nc] = false; // 보드 방문 취소
				alphabetVisited.put(alphabets[nr][nc], false); // 알파벳 방문 취소
			}
		}
	}
}
