package boj_13565_침투;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int M = sc.nextInt();

		int N = sc.nextInt();

		int[][] map = new int[M][N];

		for (int r = 0; r < M; r++) {
			// 문자열로 입력받고
			String str = sc.next();
			// charAt 메서드로 2차원 배열 map에 int형으로 저장하기
			for (int c = 0; c < N; c++) {
				int num = str.charAt(c);
				if (num == '0') {
					map[r][c] = 0;
				} else {
					map[r][c] = 1;
				}
			}
		}

		// BFS
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			if (map[0][i] == 0) {
				int num = 0 + i;
				queue.add(num);
			}
		}
		while (!queue.isEmpty()) {
			int num = queue.poll();
			int nr = num / N;
			int nc = num % N;
			// 검사한 곳은 2로 변환
			map[nr][nc] = 2;
			for (int d = 0; d < 4; d++) {

				int r = nr + dr[d];
				int c = nc + dc[d];

				if (r >= 0 && r < M && c >= 0 && c < N && map[r][c] == 0) {
					// 마지막 줄에 도달한 경우(침투된 경우)
					if (r == M - 1) {
						System.out.println("YES");	// YES를 출력하고, 종료
						return;
					}
					map[r][c] = 2;
					num = r * N + c;
					queue.add(num);
				}
			}

		}

		// 전부 검사했지만, YES를 출력하지 못한 경우 NO를 출력
		System.out.println("NO");

	}



}
