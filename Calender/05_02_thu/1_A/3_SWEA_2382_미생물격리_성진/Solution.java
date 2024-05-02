package swea_2382_미생물격리;



import java.util.Scanner;

public class Solution {

	// "",상,하,좌,우
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };

	static int N;
	static int M;
	static int K;

	static int[][] map;
	static int[][] dirMap;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();

			map = new int[N][N];
			dirMap = new int[N][N];

			for (int i = 0; i < K; i++) {
				int row = sc.nextInt();
				int col = sc.nextInt();
				int micro = sc.nextInt();
				int dir = sc.nextInt();

				map[row][col] = micro;
				dirMap[row][col] = dir;

			}
			
			// 반복 횟수 만큼
			int cnt = 0;
			while (cnt < M) {
				cnt++;
				// 임시 이동 후의 배열을 따로 만든다.
				// map은 micro의 정보들을
				// dirMap은 방향의 정보들을 담았음
				int[][] tempMap = new int[N][N];
				int[][] tempDirMap = new int[N][N];

				// 우선 움직인 후의 정보들을 temp에 저장
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (map[r][c] > 0) {
							move(r, c, tempMap, tempDirMap);
						}
					}
				}

				// 저장된 temp를 활용해서 본 맵을 갱신
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						map[r][c] = tempMap[r][c];
						dirMap[r][c] = tempDirMap[r][c];
					}
				}

			}

			
			// 답을 출력
			int ans = 0;
			for (int r = 0; r < map.length; r++) {
				for (int c = 0; c < map[r].length; c++) {
					ans += map[r][c];
				}
			}

			System.out.println("#" + test_case + " " + ans);

		}

	}

	public static void move(int row, int col, int[][] tempMap, int[][] tempDirMap) {

		int dir = dirMap[row][col];
		int micro = map[row][col];

		int nr = row + dr[dir];
		int nc = col + dc[dir];

		// 각 변마다 방향을 지정해서 바꿔줬어도 괜찮았을 듯
		if (nr == 0 || nc == 0 || nr == N - 1 || nc == N - 1) {

			if (dir == 1) {
				dir = 2;
			} else if (dir == 2) {
				dir = 1;
			} else if (dir == 3) {
				dir = 4;
			} else {
				dir = 3;
			}
			micro /= 2;
			if (micro == 0) {
				tempMap[nr][nc] = 0;
				tempDirMap[nr][nc] = 0;
				return;
			}

		}

		tempMap[nr][nc] += micro;
		// 미생물이 안 겹치는 경우
		if (tempMap[nr][nc] == micro) {
			tempDirMap[nr][nc] = dir;
		}
		// 미생물이 겹치는 경우
		else {
			int maxMicro = -987654321;	// max 값 초기화!!!!!
			int maxDir = -1;	// max값일 때 나타낼 방향 정보 초기화!!!
			// 델타배열 0부터 아니고 1부터 4까지임을 주의!
			for (int d = 1; d < 5; d++) {
				// 내가 검사해야할 부분을 기준으로 상하좌우 새로 탐색
				int nnr = nr + dr[d];
				int nnc = nc + dc[d];
				// 범위 안에 있고, 방향값이 존재하는 위치라면
				if (nnr >= 0 && nnr < N && nnc >= 0 && nnc < N && dirMap[nnr][nnc] > 0) {
					// 방향을 임시로 저장하고,
					int tmpDir = dirMap[nnr][nnc];
					// 해당 셀에서 방향만큼 움직였을 때 nr, nc로 올 수 있다면
					if (nnr + dr[tmpDir] == nr && nnc + dc[tmpDir] == nc && maxMicro < map[nnr][nnc]) {
						// 최댓값을 찾아 갱신해 저장하기
						maxDir = tmpDir;
						maxMicro = map[nnr][nnc];
					}
				}
				dir = maxDir;
			}
			// 최대 군집의 방향으로 dir을 갱신
			tempDirMap[nr][nc] = dir;
		}

	}

	public static void printMap(int[][] map) {
		System.out.println("---start---");
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

}
