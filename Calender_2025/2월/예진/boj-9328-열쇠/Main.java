/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 열쇠_문제번호
 * Date: 2025.02.27
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int keys;
	static Map<Integer, List<int[]>> lockedDoors;
	static boolean[][] visited;
	static char[][] map;
	static Queue<int[]> queue;
	static final int DOOR_TO_KEY = -65;
	static final int KEY_TO_KEY = -97;
	static final int KEY_TO_DOOR = 65;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {

			String[] input = br.readLine().split(" ");
			int n = Integer.parseInt(input[0]);
			int m = Integer.parseInt(input[1]);

			map = new char[n+2][m+2];
			visited = new boolean[n+2][m+2];
			lockedDoors = new HashMap<>();
			keys = 0;
			queue = new ArrayDeque<>();

			// map을 입력, 안팎을 드나들 수 있는 곳(시작점) 저장
			for (int i = 1; i <= n; i++) {
				char[] tmp = br.readLine().toCharArray();
				for (int j = 1; j <= m; j++) {
					map[i][j] = tmp[j-1];
					if ((i == 1 || j == 1 || i == n || j == m) && map[i][j] != '*') {
						check(i, j);
					}
				}
			}

			char[] keyInput = br.readLine().toCharArray();
			if (keyInput[0] != '0') {
				for (char k : keyInput) {
					int key = k + KEY_TO_KEY;
					open(key);
					keys |= (1 << key);
				}
			}

			int answer = 0;
			while (!queue.isEmpty()) {

				int[] curr = queue.poll();
				if (map[curr[0]][curr[1]] == '$') answer++;

				for (int d = 0; d < 4; d++) {
					int nr = curr[0] + dr[d];
					int nc = curr[1] + dc[d];

					if (visited[nr][nc] || map[nr][nc] == '\u0000' || map[nr][nc] == '*') continue;
					check(nr, nc);
				}
			}

			bw.write(String.valueOf(answer) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static void check(int r, int c) {
		// 문인데 열쇠가 없으면 잠긴 문 리스트에 추가
		if (map[r][c] >= 65 && map[r][c] <= 90
				&& (keys & (1 << (map[r][c] + DOOR_TO_KEY))) == 0) {
			List<int[]> doors = lockedDoors.getOrDefault(map[r][c] + DOOR_TO_KEY, new ArrayList<>());
			doors.add(new int[] {r, c});
			lockedDoors.put(map[r][c] + DOOR_TO_KEY, doors);
			visited[r][c] = true;
			return;

		// 키인데
		} else if (map[r][c] >= 97 && map[r][c] <= 122) {
			int key = map[r][c] + KEY_TO_KEY;
			// 처음 발견한 키면 지금까지 방문한 문 중에 맞는 문이 있는지 확인
			if ((keys & (1 << key)) == 0) open(key);
			// 키 추가
			keys |= (1 << key);
		}
		queue.add(new int[] {r, c});
		visited[r][c] = true;
	}

	public static void open(int keyIdx) {
		List<int[]> doors = lockedDoors.getOrDefault(keyIdx, new ArrayList<>());
		int door = keyIdx + KEY_TO_DOOR;
		for (int[] d : doors) {
			if (map[d[0]][d[1]] == door) queue.add(d);
		}
	}
}