/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 미네랄_2933
 * Date: 2025.03.11
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static boolean[][] mineral;
	static Queue<int[]> cluster;
	static int[] bottom;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		mineral = new boolean[n+1][m];
		for (int i = n; i >= 1; i--) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (tmp[j] == 'x') mineral[i][j] = true;
			}
		}

		int t = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < t; i++) {
			int hieght = Integer.parseInt(st.nextToken());

			// 막대기 던지기
			int col = getBroken(hieght, i % 2 == 0);

			// 깨진 미네랄이 없는 경우
			if (col < 0 || col >= m) continue;
			mineral[hieght][col] = false;

			// 분리된 클러스터를 찾으면 추락 높이 탐색 및 이동
			if (separate(hieght, col)) {
				int gap = getGap();
				move(gap);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = n; i >= 1; i--) {
			for (int j = 0; j < m; j++) {
				if (mineral[i][j]) sb.append("x");
				else sb.append(".");
			}
			if (i > 1) sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static void move(int gap) {
		int size = cluster.size();
		for (int cnt = 0; cnt < size; cnt++) {
			int[] curr = cluster.poll();
			mineral[curr[0]][curr[1]] = false;
			cluster.add(curr);
		}

		while (!cluster.isEmpty()) {
			int[] curr = cluster.poll();
			mineral[curr[0] - gap][curr[1]] = true;
		}
	}

	// 열별 가장 아래 미네랄들을 기준으로 추락할 수 있는 높이 구하기
	public static int getGap() {
		int gap = mineral.length;
		for (int i = 0; i < bottom.length; i++) {
			if (bottom[i] == mineral.length) continue;
			int h = bottom[i] - 1;
			while (h > 0 && !mineral[h][i]) h--;
			gap = Math.min(gap, bottom[i] - h - 1);
		}
		return gap;
	}

	public static boolean separate(int row, int col) {
		boolean[][] checked = new boolean[mineral.length][mineral[0].length];
		// 사방의 위치하는 미네랄로부터 클러스터 탐색
		for (int i = 0; i < 4; i++) {
			int r = row + dr[i];
			int c = col + dc[i];

			if (r < 1 || r >= mineral.length || c < 0 || c >= mineral[0].length
					|| checked[r][c] || !mineral[r][c]) continue;

			// r, c부터 클러스터 탐색
			cluster = new ArrayDeque<>();
			bottom = new int[mineral[0].length];
			int bott = mineral.length;
			Arrays.fill(bottom, mineral.length);
			Queue<int[]> queue = new ArrayDeque<>();
			queue.add(new int[] {r, c});
			checked[r][c] = true;
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();

				// 같은 열 최저 값 갱신
				bott = Math.min(bott, curr[0]);
				bottom[curr[1]] = Math.min(bottom[curr[1]], curr[0]);
				cluster.add(new int[] {curr[0], curr[1]});

				for (int d = 0; d < 4; d++) {
					int nr = curr[0] + dr[d];
					int nc = curr[1] + dc[d];

					if (nr < 1 || nr >= mineral.length || nc < 0 || nc >= mineral[0].length
							|| checked[nr][nc] || !mineral[nr][nc]) continue;
					checked[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
			// 클러스터 가장 아래가 땅이 아니면 분리된 클러스터
			if (bott > 1) return true;
		}
		// 분리된 클러스터를 찾지 못하면
		return false;
	}

	public static int getBroken(int h, boolean fromLeft) {
		int broken = 0;
		if (fromLeft) {
			// 왼쪽에서 던지기
			while (broken < mineral[h].length && !mineral[h][broken]) broken++;
		}
		else {
			// 오른쪽에서 던지기
			broken = mineral[h].length - 1;
			while (broken >= 0 && !mineral[h][broken]) broken--;
		}
		return broken;
	}
}