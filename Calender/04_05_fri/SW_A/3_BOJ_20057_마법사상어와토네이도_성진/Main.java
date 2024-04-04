package boj_20057_마법사상어와토네이도;

import java.util.Scanner;

public class Main {

	// 회전 방향은 문제처럼 좌하우상 순서대로!
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };

	// 최종 제출할 정답: 격자의 밖으로 나간 모래의 양
	static int answer = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// N x N의 격자 생성
		int N = sc.nextInt();
		int[][] sandMap = new int[N][N];

		
		// 격자 정보 입력 받으면서 맵 안 전체 모래의 양 저장
		int totalSand = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < sandMap[i].length; j++) {
				sandMap[i][j] = sc.nextInt();
				totalSand += sandMap[i][j];
			}
		}

		// 맵의 정중앙에서 시작한다고 했음
		// N은 홀수이므로~
		int nr = N / 2;
		int nc = N / 2;

		// 초기 방향은 d=0, dr, dc에 대입하면 좌측이겠지!
		int d = 0;

		// 토네이도의 방향을 전환시키기 위한 변수들
		int dCnt = 0;
		int dChange = 0;
		int dLimit = 1;

		while (true) {

			// 방향에 맞게 이동하기
			nr += dr[d];
			nc += dc[d];

			// 이동 후 해당 방향 이동 카운트 증가
			dCnt++;

			// 만약 (0, -1)라면, 토네이도의 이동이 끝나므로 break
			if (nr == 0 && nc == -1) {
				break;
			}

			// 만약 토네이도가 x -> y(nr, nc)로 이동했을 때 모래가 존재했다면? 
			if (sandMap[nr][nc] != 0) {
				// spreadSand 메소드를 실행!
				spreadSand(sandMap, nr, nc, d);
			}
//			printMap(sandMap);

			// 모래를 흩뿌리고 나면 dCnt와 dLimit을 비교한다.
			// 해당 방향으로 갈 수 있는 최대 dLimit에 도달했다면 방향을 바꾸자. 
			if (dCnt == dLimit) {
				// 다음 방향으로 이동하기 위해 d를 증가
				d = (d + 1) % 4;
				// d가 바뀌었다면 dChange를 1 증가
				dChange++;
				// 새로운 방향으로 바뀌었으므로, 방향 이동 카운트 dCnt 다시 0으로 초기화
				dCnt = 0;
				if (dChange == 2) {	// 만약 방향이 2번 바뀌었다면 limit을 증가해야해!
					dLimit++;
					dChange = 0;	// 다시 초기화해주는 것 잊지 말기
				}
			}

		}

		// 맵 안에 남아있는 모래의 양을 계산하자.
		int remainSand = 0;
		for (int i = 0; i < sandMap.length; i++) {
			for (int j = 0; j < sandMap[i].length; j++) {
				if (sandMap[i][j] > 0) {
					remainSand += sandMap[i][j];
				}
			}
		}

		// 토네이도 이동 전의 전체 모래의 양에서 남은 모래의 양을 빼서 답을 계산
		answer = totalSand - remainSand;
		System.out.println(answer);

	}

	// 모래를 흩뿌리는 메소드...!
	// 똑똑하게 탐색하면서 적절하게 %비율값을 줄 수 있을 것 같은데 그냥 구현했음...
	static void spreadSand(int[][] sandMap, int row, int col, int dir) {

		int N = sandMap.length;

		// 일단 현재 위치의 모래의 양을 sand에 저장
		int sand = sandMap[row][col];

		// remainSand를 정의하고, 아래 로직을 수행하며 계속 갱신할 것임
		int remainSand = sand;

		// 현재 위치를 nr, nc로 저장하고, 이걸로 위치를 계속 이동할 것임
		int nr = row;
		int nc = col;

		// 현재 방향을 d로 저장
		int d = dir;

		// x -> y로 가는 방향을 정방향이라 정의하고, y를 중심으로 시계방향을 정의하자.
		// 정방향: d, 시계방향 90도: d-1, 반시계방향 90도: d+1
		// 각 방향을 이용해서 비율에 맞게 차근차근 채워봅시다.

		// 1. 시계방향 90도 직각 부분(7%, 2%) 먼저
		// 1-1. 7% 부분
		nr += dr[((d - 1) + 4) % 4];
		nc += dc[((d - 1) + 4) % 4];
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
			sandMap[nr][nc] += (int) (sand * 0.07);
		}
		remainSand -= (int) (sand * 0.07);

		// 1-2. 2% 부분
		nr += dr[((d - 1) + 4) % 4];
		nc += dc[((d - 1) + 4) % 4];
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
			sandMap[nr][nc] += (int) (sand * 0.02);
		}
		remainSand -= (int) (sand * 0.02);

		
		// 2. 반시계방향 90도 부분(7%, 2%)
		// nr과 nc를 다시 원위치로
		nr = row;
		nc = col;
		// 2-1. 7% 부분
		nr += dr[(d + 1) % 4];
		nc += dc[(d + 1) % 4];
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
			sandMap[nr][nc] += (int) (sand * 0.07);
		}
		remainSand -= (int) (sand * 0.07);

		// 2-2. 2% 부분
		nr += dr[(d + 1) % 4];
		nc += dc[(d + 1) % 4];
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
			sandMap[nr][nc] += (int) (sand * 0.02);
		} 
		remainSand -= (int) (sand * 0.02);

		// 3. 대각선(10%, 1%) 부분
		// nr과 nc를 다시 원위치로
		nr = row;
		nc = col;
		// 3-1. 1% 부분
		nr = nr + dr[((d - 1) + 4) % 4] - dr[d];
		nc = nc + dc[((d - 1) + 4) % 4] - dc[d];
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
			sandMap[nr][nc] += (int) (sand * 0.01);
		}
		remainSand -= (int) (sand * 0.01);
		// nr과 nc를 다시 원위치로
		nr = row;
		nc = col;
		nr = nr + dr[(d + 1) % 4] - dr[d];
		nc = nc + dc[(d + 1) % 4] - dc[d];
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
			sandMap[nr][nc] += (int) (sand * 0.01);
		}
		remainSand -= (int) (sand * 0.01);

		
		// 3-2. 10% 부분
		// nr과 nc를 다시 원위치로
		nr = row;
		nc = col;
		nr = nr + dr[((d - 1) + 4) % 4] + dr[d];
		nc = nc + dc[((d - 1) + 4) % 4] + dc[d];
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
			sandMap[nr][nc] += (int) (sand * 0.10);
		} 
		remainSand -= (int) (sand * 0.10);
		// nr과 nc를 다시 원위치로
		nr = row;
		nc = col;
		nr = nr + dr[(d + 1) % 4] + dr[d];
		nc = nc + dc[(d + 1) % 4] + dc[d];
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
			sandMap[nr][nc] += (int) (sand * 0.10);
		} 
		remainSand -= (int) (sand * 0.10);

		
		// 4. 정방향 부분(5%, alpha)
		nr = row;
		nc = col;
		// 4-1. 5% 부분
		// 2칸 앞으로
		nr += 2 * dr[d];
		nc += 2 * dc[d];
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
			sandMap[nr][nc] += (int) (sand * 0.05);
		}
		remainSand -= (int) (sand * 0.05);

		// 4-2. 정방향 alpha
		// alpha를 제일 나중에 수행해야 함
		nr = row;
		nc = col;
		nr += dr[d];
		nc += dc[d];
		if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
			// 계속 계산해왔던 remainSand를 옮겨 담아준다.
			sandMap[nr][nc] += remainSand;
		} 

		// 현재 위치의 모래의 양은 0으로!
		sandMap[row][col] = 0;

	}

	static void printMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("---end---");
	}

}
