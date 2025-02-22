package boj_18428_감시피하기;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static boolean flag;
	static char[][] arr;
	static List<int[]> students;
	static List<int[]> obstacles;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new char[N][N];
		students = new ArrayList<int[]>();
		obstacles = new ArrayList<int[]>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < N; c++) {
				arr[r][c] = st.nextToken().charAt(0);

				if (arr[r][c] == 'S') {
					students.add(new int[] { r, c });
				}
			}
		}

		for (int[] student : students) {
			// 학생 위치에서 상하좌우 방향으로 탐색
			for (int d = 0; d < 4; d++) {
				for (int i = 1; i < N; i++) {
					int nr = student[0] + dr[d] * i;
					int nc = student[1] + dc[d] * i;

					if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
						// 선생님이나 학생을 만날 경우
						if (arr[nr][nc] != 'X') {
							// 선생님을 만나면 학생과 선생님 사이에 있는 지점들은 장애물을 설치해야 하므로 obstacles List에 추가
							if (arr[nr][nc] == 'T') {
								// 선생님과 학생의 위치 차이가 1이면 피할 수 없으므로 "NO" 출력 후 return
								if (i == 1) {
									System.out.println("NO");
									return;
								}

								for (int j = 1; j < i; j++) {
									int r_idx = student[0] + dr[d] * j;
									int c_idx = student[1] + dc[d] * j;

									obstacles.add(new int[] { r_idx, c_idx });
								}
							}

							break; // 학생을 만나더라도 더 이상 탐색은 필요하지 않으므로 break
						}
					}
				}
			}
		}

		// 장애물이 필요한 곳이 3보다 작거나 같으면 무조건 감시를 피할 수 있다.
		if (obstacles.size() <= 3) {
			flag = true;
		} else {
			sol(0, 0);
		}

		System.out.println(flag ? "YES" : "NO");
	}

	public static void sol(int idx, int s_idx) {
		// 한 번 가능한 경우를 찾으면 탐색 중단
		if (flag) {
			return;
		}

		// 위치 3곳을 고르면 모든 학생이 감시를 피할 수 있는지 check
		if (s_idx == 3) {
			if (check()) {
				flag = true;
			}
			return;
		}

		for (int i = idx; i <= obstacles.size() - 3 + s_idx; i++) {
			int[] obstacle = obstacles.get(i);

			// 장애물을 아직 놓지 않은 곳 고르기 (방문 check)
			if (arr[obstacle[0]][obstacle[1]] == 'X') {
				arr[obstacle[0]][obstacle[1]] = 'O'; // 장애물 놓기
				sol(i + 1, s_idx + 1); // 다음 위치 찾기
				arr[obstacle[0]][obstacle[1]] = 'X'; // 원상복구
			}
		}

	}

	public static boolean check() {
		// 모든 학생에 대해 상하좌우 방향 탐색
		for (int[] student : students) {
			for (int d = 0; d < 4; d++) {
				for (int i = 1; i < N; i++) {
					int nr = student[0] + dr[d] * i;
					int nc = student[1] + dc[d] * i;

					if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
						// 장애물 만나면 다음 방향 탐색
						if (arr[nr][nc] == 'O') {
							break;
						}
						// 선생님 만나면 불가능
						if (arr[nr][nc] == 'T') {
							return false;
						}
					}
				}
			}
		}

		// 모든 학생 탐색이 끝나면 감시 피하기 가능
		return true;
	}
}
