package boj_14673_CrushFever;

import java.util.Scanner;

public class Main {

	// 3번의 터치로 얻을 수 있는 최대 점수
	static int maxScore = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 너비 M, 높이 N
		int M = sc.nextInt();
		int N = sc.nextInt();

		// N개의 행, M개의 열
		int[][] puzzleMap = new int[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				puzzleMap[r][c] = sc.nextInt();
			}
		}

		// dfs를 반복하며 최대 점수를 갱신하는 메소드 실행
		doDfs(puzzleMap, 0, 0);

		
		System.out.println(maxScore);

		sc.close();

	}

	// dfs
	static int dfs(int[][] map, int r, int c) {

		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };
		
		// 현재 위치의 num(1~5)을 저장하고, 해당 위치를 0으로 바꿈(방문 O)
		int num = map[r][c];
		map[r][c] = 0;

		// 파괴한 퍼즐 개수
		int destroyedPuzzle = 1;

		// 사방 탐색으로 num과 같은 곳들을 dfs
		for (int d = 0; d < 4; d++) {
			if (r + dr[d] >= 0 && r + dr[d] < map.length && c + dc[d] >= 0 && c + dc[d] < map[0].length
					&& map[r + dr[d]][c + dc[d]] == num) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				destroyedPuzzle += dfs(map, nr, nc);
			}

		}

		// 총 파괴한 퍼즐 개수를 반환
		return destroyedPuzzle;
	}


	// dfs를 3번 실행할 doDfs 메서드를 정의
	// 파라미터로 맵, 현재 진행한 dfs 횟수, 현재 진행 단계의 점수가 필요
	static void doDfs(int[][] map, int cnt, int score) {
		
		// 만약 세 번의 dfs가 끝났다면, maxScore를 갱신하고 출력
		if (cnt == 3) {
			maxScore = Math.max(maxScore, score);
			return;
		}

		// (추가) 만약 3번의 dfs가 끝나기 전에 모든 퍼즐이 파괴됐다면?
		// 해당 점수를 고려하기 위한 flag 변수
		boolean flag = true;
		for (int r=0; r<map.length; r++) {
			for (int c=0; c<map[r].length; c++) {
				if (map[r][c] != 0) {
					flag = false;
				}
			}
		}
		
		// flag가 참이라면 maxScore를 갱신한다.
		if (flag) {
			maxScore = score;
			return;
		}
		
		// dfs 시작 전, 공중에 뜬 조각을 아래로 가라앉히는 메소드 goDown을 실행
		goDown(map);

		// 맵을 돌며 dfs를 실행하고, 이 내부에서 doDfs를 재귀적으로 호출
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if (map[r][c] != 0) {
					// mapCopy 메소드로 기존 map을 복사
					int[][] copyMap = mapCopy(map);
					// dfs 메소드를 실행해 파괴된 퍼즐의 개수를 출력하고, 이를 num이라는 변수에 저장
					int num = dfs(copyMap, r, c);
					// 현재 score에 파괴한 퍼즐 개수의 제곱만큼 점수를 증가시키고, cnt를 증가한 후에 doDfs를 호출
					doDfs(copyMap, cnt + 1, score + num * num);

				}
			}
		}

	}

	// 공중에 뜬 조각을 아래로 가라앉히는 메소드 goDown
	static void goDown(int[][] map) {
		// 열 우선, 행 감소 순회로 퍼즐 맵의 맨 아래부터 파괴됐는지(0인지)를 검사
		for (int c = 0; c < map[0].length; c++) {
			for (int r = map.length - 1; r >= 0; r--) {
				// 파괴된 조각이 있다면
				if (map[r][c] == 0) {
					// 그 윗 줄을 nr이라 칭하고
					int nr = r - 1;
					// 위로 올라가며 0인지를 검사
					while (nr >= 0 && map[nr][c] == 0) {
						nr--;
					}
					// 파괴된 부분을 swap해 위로 올려준다.
					if (nr >= 0) {
						map[r][c] = map[nr][c];
						map[nr][c] = 0;
					}

				}
			}
		}

	}

	// 맵을 복사하는 함수
	static int[][] mapCopy(int[][] map) {
		int[][] copyMap = new int[map.length][map[0].length];

		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				copyMap[r][c] = map[r][c];
			}
		}

		return copyMap;
	}

	static void printMap(int[][] map) {

		System.out.println("---start---");
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {

				System.out.print(map[r][c] + " ");

			}
			System.out.println();
		}

	}

}
