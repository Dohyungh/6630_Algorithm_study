/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 로봇 청소기_4991
 * Date: 2025.03.04
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int h;
	static int w;
	static int[][] room;
	static int[][] dist;
	static int answer;
	static List<int[]> dirt;
	static boolean[] used;
	static int count;

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		h = Integer.parseInt(input[1]);
		w = Integer.parseInt(input[0]);

		// 입력된 값에 따라 테케 진행 여부 판단
		while (h != 0 && w != 0) {

			// 사방탐색을 위한 패딩처리
			room = new int[h+2][w+2];
			Arrays.fill(room[0], -1);
			Arrays.fill(room[h+1], -1);

			// 방 상태 및 더러운 칸(청소기 초기위치 포함) 목록 입력
			dirt = new ArrayList<>();
			dirt.add(new int[] {0, 0});							// 청소기 초기위치는 인덱스 0에 저장할 것, 미리 채워두기
			for (int i = 1; i <= h; i++) {
				room[i][0] = room[i][w+1] = -1;
				char[] tmp = br.readLine().toCharArray();
				for (int j = 1; j <= w; j++) {
					char c = tmp[j-1];
					if (c == 'o') {								// 시작점 저장 (방 상태에는 빈곳으로 0)
						room[i][j] = 0;
						dirt.set(0, new int[] {i, j});
					} else if (c == '*') {						// 더러운 칸 저장 (방 상태에는 dirt 인덱스로 저장)
						room[i][j] = dirt.size();
						dirt.add(new int[] {i, j});
					} else if (c == 'x') room[i][j] = -1;		// 벽은 -1로, 패딩과 동일하게
				}
			}

			count = dirt.size();								// 더러운 칸의 개수(청소기 위치 포함)
			dist = new int[count][count];						// dirt의 인덱스를 사용해서 모든 칸 사이의 최단거리 저장
			for (int[] start : dirt) {							// 모든 칸에서 시작하여 탐색
				bfs(start);
			}

			answer = Integer.MAX_VALUE;
			used = new boolean[count];
			dfs(0, 1, 0);						// 청소기 출발지부터 시작하여 모든 더러운 칸을 방문하는 순서 탐색

			if (answer == Integer.MAX_VALUE) bw.write("-1\n");
			else bw.write(String.valueOf(answer) + "\n");

			input = br.readLine().split(" ");
			h = Integer.parseInt(input[1]);
			w = Integer.parseInt(input[0]);
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int currIdx, int cnt, int sum) {
		if(sum >= answer) return;

		if(cnt >= count) {
			answer = sum;
			return;
		}

		// 청소기 시작 위치는 다시 갈 필요 없으므로, 1부터 순회
		for (int i = 1; i < count; i++) {
			if (used[i] || dist[currIdx][i] == 0) continue;

			used[i] = true;
			dfs(i, cnt + 1, sum + dist[currIdx][i]);
			used[i] = false;
		}
	}

	public static void bfs (int[] start) {
		boolean[][] visited = new boolean[h+2][w+2];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start[0]][start[1]] = true;

		int depth = 0;
		while (!queue.isEmpty()) {

			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] curr = queue.poll();

				if (room[curr[0]][curr[1]] > 0) dist[room[start[0]][start[1]]][room[curr[0]][curr[1]]] = depth;

				for (int d = 0; d < 4; d++) {
					int nr = curr[0] + dr[d];
					int nc = curr[1] + dc[d];

					if (room[nr][nc] < 0 || visited[nr][nc]) continue;

					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
			depth++;
		}
	}
}