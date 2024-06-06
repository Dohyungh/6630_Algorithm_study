package boj_7562_나이트의이동;

import java.io.*;
import java.util.*;

public class Main {
	static int I, cnt;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = { -1, 1, -1, 1, 2, 2, -2, -2 };
	static int[] dc = { 2, 2, -2, -2, -1, 1, -1, 1 };
	static Queue<Pos> q;

	// 나이트 위치 및 이동횟수에 대한 클래스 선언
	static class Pos {
		int r;
		int c;
		int cnt;

		Pos(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		int r, c;

		for (int t = 0; t < T; t++) {
			cnt = 0;
			I = Integer.parseInt(br.readLine());

			arr = new int[I][I];
			visited = new boolean[I][I];
			q = new LinkedList<>();

			st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			// 현재 위치에 대한 Pos 객체 선언 및 초기화
			Pos start = new Pos(r, c, 0);

			st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			// 도착 위치에 대한 Pos 객체 선언 및 초기화
			Pos end = new Pos(r, c, 0);

			int result = bfs(start, end);
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}

	// bfs 구현
	private static int bfs(Pos start, Pos end) {

		// 현재 위치 queue에 삽입
		q.offer(start);
		// 방문 check
		visited[start.r][start.c] = true;

		// queue가 비어 있지 않으면 반복
		while (!q.isEmpty()) {
			// queue에서 꺼낸 위치로 이동
			Pos now = q.poll();

			// 도착 지점에 도달하면 종료
			if (now.r == end.r && now.c == end.c) {
				cnt = now.cnt;
				break;
			}

			// 방문 가능한 위치로 이동하여 queue에 삽입
			for (int i = 0; i < 8; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (checkRange(nr, nc) && !visited[nr][nc]) {
					q.offer(new Pos(nr, nc, now.cnt + 1));
					visited[nr][nc] = true;
				}
			}
		}
		return cnt;
	}

	// 인덱스가 배열의 범위 내에 있는지 확인
	private static boolean checkRange(int r, int c) {
		if (-1 < r && r < I && -1 < c && c < I)
			return true;
		return false;
	}
}
