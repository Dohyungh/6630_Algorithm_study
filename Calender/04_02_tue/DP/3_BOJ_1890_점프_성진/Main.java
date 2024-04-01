package boj_1890_점프;

import java.util.Scanner;

// 기존처럼 최대한 시간 복잡도를 줄인 일반적인 반복문으로 푼다 해도 시간 초과!!
public class Main {

	static long[][] pathMap;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		// 게임 판 초기화
		int[][] map = new int[N][N];

		// 게임 판 정보 입력받기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		// 경로의 개수가 2^63-1보다 작거나 같다는 조건
		// 2^31-1까지가 int형, 2^63-1까지가 long형의 범위!
		pathMap = new long[N][N];

		// 가장 왼쪽 위 칸(출발점)의 현재 경로의 개수를 1로 설정.
		pathMap[0][0] = 1;

		// jump할 수 있는 값(게임판의 숫자)을 변수로 저장
		int jump = map[0][0];
		

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
//				printMap(pathMap);
				// 해당 칸까지 올 수 있는 경로가 존재하고, 진행이 가능한 칸에 위치했다면
				if (pathMap[i][j] != 0 && map[i][j] != 0) {
					// 점프값을 갱신
					jump = map[i][j];
					// 가로(오른쪽)로 점프가 가능하다면
					if (i+jump < N) {
						pathMap[i+jump][j] += pathMap[i][j];
					}
					// 세로(아래쪽)로 점프가 가능하다면
					if (j+jump < N) {
						pathMap[i][j+jump] += pathMap[i][j];
					}
				}

			}
		}

		// 도착점에 계산된 경로의 개수를 출력
		System.out.println(pathMap[N - 1][N - 1]);

	}
	
//	static void printMap(int[][] map) {
//		System.out.println("---start---");
//		for (int i=0; i<map.length; i++) {
//			for (int j=0; j<map[i].length; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		
//	}

}
