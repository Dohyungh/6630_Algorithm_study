package swea_2117_홈방범서비스;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_fin {

	static int N;
	static int M;

	static int[][] map;

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static int house;
	static int ans;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt(); // N x N 맵
			M = sc.nextInt(); // 집 한 채당 이익 M
			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = sc.nextInt();
				}
			}

			// 정답 초기화
			ans = 0;

			// 맵을 순회하며 완전 탐색
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// depth는 1부터 N+1까지
					// N x N의 맵을 전부 뒤덮기 위해 N+1의 depth가 필요!
					for (int k = 1; k <= N + 1; k++) {
						bfs(r, c, k); // bfs 실행
					}

				}
			}

			System.out.println("#" + test_case + " " + ans);

		}

	}

	// 맵의 좌표마다 k만큼의 depth의 탐색을 실행
	// 각 실행마다 무엇을 초기화해야하는지를 집중해서 구현할 것
	static void bfs(int row, int col, int k) {

		Queue<Integer> queue = new LinkedList<Integer>();
		// visited를 초기화
		boolean[][] visited = new boolean[N][N];
		// 홈 방범 서비스 내에 위치한 house의 개수
		house = 0;

		// 2차원 -> 1차원으로 변환해서 queue에 삽입
		int num = row * N + col;
		queue.add(num);
		// 탐색 시작 위치도 방문 체크하는 것 까먹지 말기
		visited[row][col] = true;

		// 깊이 생성 및 1로 초기화
		int depth = 1;

		// 시작 위치에 집이 존재했다면 house개수 늘려주기
		if (map[row][col] == 1) {
			house++;
			ans = Math.max(ans, 1);
		}

		// queue가 빌 때까지, depth가 k에 도달할 때까지
		while (!queue.isEmpty()) {

			// 현재 queue의 크기를 size 변수로 초기화
			int size = queue.size();

			// size 만큼 아래 반복문 실행
			for (int sz = 0; sz < size; sz++) {
				// queue에서 하나 꺼내와서 bfs 탐색할 것
				int n = queue.poll();
				int r = n / N;
				int c = n % N;

				for (int d = 0; d < 4; d++) {
					// nr, nc를 새로 선언
					int nr = r + dr[d];
					int nc = c + dc[d];

					// 아직 방문하지 않은 인접 노드라면 탐색 실시
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
						queue.add(nr * N + nc);
						// 방문 체크
						visited[nr][nc] = true;
						// 만약 탐색한 위치가 집이었다면 카운트 + 1
						if (map[nr][nc] == 1) {
							house++;
						}

					}

				}

			}

			// depth가 하나 늘었다!
			depth++;

			if (depth == k) {
				// 깊이에 따른 비용 계산 및 정답 갱신
				compareCost(depth, k);
				break;
			}

		}

	}

	// 비용 비교 메서드
	static void compareCost(int depth, int k) {
		int cost = k * k + (k - 1) * (k - 1);
		// 만약 방범 비용이 싸거나 같다면(손해가 없다면)
		// ans(house의 개수)를 갱신
		if (cost <= M * house) {
			ans = Math.max(ans, house);
		}

	}

}
