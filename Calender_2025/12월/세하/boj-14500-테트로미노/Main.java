package boj_14500_테트로미노;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, ans;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		visited = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		ans = Integer.MIN_VALUE;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				visited[r][c] = true; // 해당 칸 방문 체크
				sol(r, c, 1, arr[r][c]);
				visited[r][c] = false; // 탐색 후 방문 체크 취소
				solT(r, c);
			}
		}

		System.out.println(ans);
	}

	public static void sol(int r, int c, int cnt, int sum) {
		// 최적화
		// 현재까지의 합 + 남은 칸에 가능한 최대값이 현재 최대값보다 작으면 더 진행할 필요 없음
		if (sum + (4 - cnt) * 1000 <= ans) { // 1000은 문제의 최대값
			return;
		}

		// 4칸이 되면 최댓값 비교 후 return
		if (cnt >= 4) {
			ans = Math.max(ans, sum);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
				visited[nr][nc] = true; // 인접 칸 방문 체크
				sol(nr, nc, cnt + 1, sum + arr[nr][nc]); // 재귀 실행
				visited[nr][nc] = false; // 인접 칸 방문 체크 취소
			}
		}
	}

	public static void solT(int r, int c) {
		// 'ㅜ' 모양 계산
		if (r >= 0 && r < N - 1 && c > 0 && c < M - 1) {
			ans = Math.max(ans, arr[r][c] + arr[r][c - 1] + arr[r][c + 1] + arr[r + 1][c]);
		}

		// 'ㅗ' 모양 계산
		if (r > 0 && r < N && c > 0 && c < M - 1) {
			ans = Math.max(ans, arr[r][c] + arr[r][c - 1] + arr[r][c + 1] + arr[r - 1][c]);
		}

		// 'ㅓ' 모양 계산
		if (r > 0 && r < N - 1 && c > 0 && c < M) {
			ans = Math.max(ans, arr[r][c] + arr[r - 1][c] + arr[r + 1][c] + arr[r][c - 1]);
		}

		// 'ㅏ' 모양 계산
		if (r > 0 && r < N - 1 && c >= 0 && c < M - 1) {
			ans = Math.max(ans, arr[r][c] + arr[r - 1][c] + arr[r + 1][c] + arr[r][c + 1]);
		}
	}
}