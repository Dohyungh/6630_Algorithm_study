package BOJ._17837_새로운게임2;

import java.util.Scanner;

public class Main {
	
	static int[][] colorMap; // 색깔 맵
	static int[][] stackMap; // 쌓인 개수 맵
	static int[][] pieces; // 
	
	static int[] dr = {0,0,-1,1}; // 우 좌 상 하
	static int[] dc = {1,-1,0,0};
	
	static int N; // 맵의 크기
	static int K; // 체스말의 개수
	
	static boolean end = false; // 게임이 종료됐는지 여부
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		colorMap = new int[N+2][N+2]; // 패딩. 2로 채워줄 것임.
		
		for (int i = 0; i < N+2; i++) {
			for (int j = 0; j < N+2; j++) {
				colorMap[i][j] = 2;
			}
		}
		
		stackMap = new int[N][N]; // 각 좌표에 있는 체스 말의 개수
		
		pieces = new int[K][4]; // r, c, stack, dir 
		// 각 체스말의 정보를 따로 관리.
		// 좌표, 해당 위치에 있는 몇번째 말인지, 방향
		
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				colorMap[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 0; i < K; i++) {
			pieces[i][0] = sc.nextInt()-1;
			pieces[i][1] = sc.nextInt()-1;
			pieces[i][2] = 1; // 2개 이상인 경우는 없다 했다.
			pieces[i][3] = sc.nextInt()-1;
			
			stackMap[pieces[i][0]][pieces[i][1]] = 1;
		}
		
		int turn = 0;
		out:
		while(turn<=1000) {
			turn++;
			for (int i = 0; i < K; i++) {
				move(i);
				if (end) break out;
			}
		}
		if (turn <= 1000) System.out.println(turn);
		else System.out.println(-1);
		
		
		
	}

	private static void move(int i) {
		if (!end) {
			
			int r = pieces[i][0];
			int c = pieces[i][1];
			int stack = pieces[i][2];
			int dir = pieces[i][3];
			
			int numMovingPieces = stackMap[r][c] - stack + 1;
			// 움직이는 말의 개수

			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if (colorMap[nr+1][nc+1] == 0) { // 흰색
				// pieces 배열을 순회 하면서 위치가 같고, 나보다 위쪽에 쌓여 있는 말들을 모두 움직여줌
				for (int j = 0; j < K; j++) {
					if (r == pieces[j][0] && c == pieces[j][1] && stack <= pieces[j][2]) {
						pieces[j][0] = nr;
						pieces[j][1] = nc;
						pieces[j][2] = stackMap[nr][nc] + pieces[j][2] - stack + 1;
						if (pieces[j][2] >= 4) {
							end = true;
							return;
						}
					}
				}
				
				
			} else if (colorMap[nr+1][nc+1] == 1) { // 빨간색
				
				
				for (int j = 0; j < K; j++) {
					if (r == pieces[j][0] && c == pieces[j][1] && stack <= pieces[j][2]) {
						pieces[j][0] = nr;
						pieces[j][1] = nc;
						pieces[j][2] = stackMap[nr][nc] + (numMovingPieces - pieces[j][2] +stack); // 여기 식 헷갈려서 1시간
						if (pieces[j][2] >= 4) {
							end = true;
							return;
						}
					}
				}
				
				
				
			} else if (colorMap[nr+1][nc+1] == 2) { // 파란색
				
				// 방향 뒤집기.
				// 0 -> 1, 1 -> 0
				// 2 -> 3, 3 -> 2
				
				if (dir <= 1) dir = 1 - dir; 
				else dir = 5 - dir;
				
				pieces[i][3] = dir;
				
				//뒤집은 방향으로 다시 검사
				nr = r + dr[dir];
				nc = c + dc[dir];
				
				//했는데 거기가 파란색이면 스탑
				if (colorMap[nr+1][nc+1] ==2) {
					return;
				}
				
				
				
				/////////////////////////////////////////////////
				// 방향을 뒤집었을 때 거기가 빨간색이라면 빨간색 알고리즘을 써줘야 함.
				// 이거 몰라서 한시간
				if (colorMap[nr+1][nc+1] == 1) { // 빨간색
					
					
					for (int j = 0; j < K; j++) {
						if (r == pieces[j][0] && c == pieces[j][1] && stack <= pieces[j][2]) {
							pieces[j][0] = nr;
							pieces[j][1] = nc;
							pieces[j][2] = stackMap[nr][nc] + (numMovingPieces - pieces[j][2] +stack); // 여기 식 헷갈려서 1시간
							if (pieces[j][2] >= 4) {
								end = true;
								return;
							}
						}
					}
					stackMap[nr][nc] += numMovingPieces;
					stackMap[r][c] -= numMovingPieces;
					return;
					// 밑에 부분 실행 안시키고 싶어서 그냥 리턴
					
				}
				
				/////////////////////////////////////////////////
				
				//흰색이면 그대로 복붙
				
				for (int j = 0; j < K; j++) {
					if (r == pieces[j][0] && c == pieces[j][1] && stack <= pieces[j][2]) {
						pieces[j][0] = nr;
						pieces[j][1] = nc;
						pieces[j][2] = stackMap[nr][nc] + pieces[j][2] - stack + 1;
						if (pieces[j][2] >= 4) {
							end = true;
							return;
						}
					}
				}
				

				
			}
			
			// 마지막에 개수 업데이트 해줘야 함
			stackMap[nr][nc] += numMovingPieces;
			stackMap[r][c] -= numMovingPieces;
		}
	}

}
