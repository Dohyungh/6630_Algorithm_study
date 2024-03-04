package boj_17142_연구소3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main2 {

	static int N;
	static int M;
	static int[][] lab;

	static List<Integer> virusX;
	static List<Integer> virusY;

	static List<int[]> selectX;
	static List<int[]> selectY;

	static int[] activeVirusX;
	static int[] activeVirusY;

	static Queue<Integer> queue;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		virusX = new ArrayList<Integer>();
		virusY = new ArrayList<Integer>();

		lab = new int[N][N];

		int[][] originLab = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				int num = sc.nextInt();

				// 벽이면 -1을 저장
				if (num == 1) {
					lab[i][j] = -1;
					originLab[i][j] = -1;

				}
				// 바이러스면 -2를 저장
				else if (num == 2) {
					lab[i][j] = -2;
					originLab[i][j] = -2;
					virusX.add(i);
					virusY.add(j);
				}
				// 빈 공간이면 -5를 저장
				else {
					lab[i][j] = -5;
					originLab[i][j] = -5;
				}

			}
		}

		activeVirusX = new int[M];
		activeVirusY = new int[M];

		selectX = new ArrayList<int[]>();
		selectY = new ArrayList<int[]>();

		comb(0, 0);

		int nr = -1;
		int nc = -1;

		int answer = Integer.MAX_VALUE;

		int canAnswer = -1;

		for (int i = 0; i < selectX.size(); i++) {

			int[] activeArrX = selectX.get(i);
			int[] activeArrY = selectY.get(i);

			queue = new LinkedList<Integer>();

			for (int j = 0; j < M; j++) {
				nr = activeArrX[j];
				nc = activeArrY[j];
				lab[nr][nc] = 0;
				int num = convertToOneDim(nr, nc);
				queue.add(num);

			}

			bfs();
//			printMap(originLab);
//			printMap(lab);
			if (checkInfectedAll()) {

				int max = -1;
				for (int r = 0; r < lab.length; r++) {
					for (int c = 0; c < lab.length; c++) {
						if (lab[r][c] > max) {
							max = lab[r][c];
						}
					}
				}
				canAnswer = max;
				if (canAnswer < answer) {
					answer = canAnswer;

				}
			}

			// 원본 연구실 정보로 초기화
			for (int r = 0; r < lab.length; r++) {
				for (int c = 0; c < lab[r].length; c++) {
					lab[r][c] = originLab[r][c];
				}
			}

		}

		if (answer == Integer.MAX_VALUE) {
			answer = -1;
		}
		System.out.println(answer);

		sc.close();

	}

	// activeVirus를 조합
	static void comb(int idx, int sidx) {

		if (sidx == M) {
			selectX.add(activeVirusX.clone());
			selectY.add(activeVirusY.clone());
			return;
		}

		if (idx >= virusX.size()) {
			return;
		}

		comb(idx + 1, sidx);
		activeVirusX[sidx] = virusX.get(idx);
		activeVirusY[sidx] = virusY.get(idx);
		comb(idx + 1, sidx + 1);

	}

	static void bfs() {
		int[] dr = { 1, -1, 0, 0 }; // 상, 하, 좌, 우 이동을 위한 배열
		int[] dc = { 0, 0, 1, -1 };

	    
		while (!queue.isEmpty()) {
			
			
			printMap(lab);
			int num = queue.poll();
			int r = convertToTwoDim(num)[0]; // 현재 행 위치
			int c = convertToTwoDim(num)[1]; // 현재 열 위치

			
			for (int d = 0; d < 4; d++) { // 4방향 탐색
				int nr = r + dr[d]; // 다음 행 위치
				int nc = c + dc[d]; // 다음 열 위치

				if (nr >= 0 && nc >= 0 && nr < N && nc < N) { // 범위 내에 있고
					// 빈 칸이거나 비활성 바이러스인 경우
					if (lab[nr][nc] == -5) {
						lab[nr][nc] = lab[r][c] + 1;
						queue.add(convertToOneDim(nr, nc));
					}
					if (lab[nr][nc] == -2) {
						lab[nr][nc] = lab[r][c];
						queue.add(convertToOneDim(nr, nc));
					}
				}
			}
		}
	}

	static boolean checkInfectedAll() {
		for (int i = 0; i < lab.length; i++) {
			for (int j = 0; j < lab[i].length; j++) {
				if (lab[i][j] == -5) {
					return false;
				}
			}
		}
		return true;
	}

	static int convertToOneDim(int r, int c) {
		return r * N + c;
	}

	static int[] convertToTwoDim(int num) {
		int[] twodim = new int[2];
		twodim[0] = num / N;
		twodim[1] = num % N;
		return twodim;
	}

	static void printMap(int[][] map) {

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("--------------");
	}

}
