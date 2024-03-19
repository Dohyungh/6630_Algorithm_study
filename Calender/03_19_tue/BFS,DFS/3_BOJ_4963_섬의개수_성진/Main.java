package boj_4963_섬의개수;

import java.util.Scanner;

public class Main {

	static int w;
	static int h;
	static int[][] map;
	// 팔방 탐색... 앞으로 확인 잘 하겠습니다.
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 입력이 반복된다.
		// 이 안에 스캐너 쓰면 망가짐?
		while (true) {
			// w는 너비, h는 높이
			// 즉, w는 col, h는 row에 해당
			w = sc.nextInt();
			h = sc.nextInt();

			// 입력이 반복되고, 0 0이 입력되면 끝나므로 종료 조건 명시!
			if (w == 0 && h == 0) {
				break;
			}

			// padding
			map = new int[h + 2][w + 2];

			// 인덱스 1부터 h까지 행 순회, 인덱스 1부터 w까지 열 순회하며 섬과 바다 입력
			// 0(바다)으로 초기화이므로 padding 값 안 채워줘도 됨!
			for (int i = 1; i < h + 1; i++) {
				for (int j = 1; j < w + 1; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			// 출력할 답
			int answer = 0;

			// map을 돌면서
			for (int r = 1; r < h + 1; r++) {
				for (int c = 1; c < w + 1; c++) {
					// 만약 섬이라면 findIsland 메소드 실행
					if (map[r][c] == 1) {
						findIsland(r, c);
						answer++;
					}

				}
			}
			System.out.println(answer);
		}
		sc.close();
	}

	// 현재 r, c를 입력받아 dfs로 1개 섬의 범위를 찾고, 해당 섬을 전부 바다로 바꿔버림
	static void findIsland(int r, int c) {
		// 현재 위치를 0으로(바다로 바꾸기)
		map[r][c] = 0;

		// 팔방 탐색 실행
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			// 만약 팔방 탐색한 부분이 1이라면 dfs로 퍼져나가기
			if (map[nr][nc] == 1) {
				findIsland(nr, nc);
			}

		}
		

	}

}
