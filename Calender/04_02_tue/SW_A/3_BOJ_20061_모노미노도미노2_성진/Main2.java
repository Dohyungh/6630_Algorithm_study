package boj_20061_모노미노도미노;

import java.util.Scanner;

// Main 버전보다 약간은 줄였을지도...
public class Main2 {

	static int N;

	static int[][] greenMap;
	static int[][] blueMap;

	static int score;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		greenMap = new int[6][4];
		// blueMap을 90도 반시계방향 돌려서 greenMap과 같은 로직을 수행하게끔...!
		blueMap = new int[6][4];

		for (int i = 0; i < N; i++) {

			int commandGreen = sc.nextInt();
			int rowGreen = sc.nextInt();
			int colGreen = sc.nextInt();
			
			// 같은 로직을 수행하기 위한 blueMap으로의 커맨드 변환
			int commandBlue = commandGreen;
			if (commandGreen == 2) {
				commandBlue = 3;
			}
			else if (commandGreen == 3) {
				commandBlue = 2;
			}
			
			// row, col도 90도 회전해줄 것
			int rowBlue = colGreen;
			int colBlue = 3 - rowGreen;
			
			// 기존 command가 3인 경우의 시작점을 주의할 것!
			if (commandBlue == 2) {
				colBlue--;
			}
				
//			System.out.println(commandBlue + " " + rowBlue + " " + colBlue);
			
			moveBlock(greenMap, rowGreen, colGreen, commandGreen);
			moveBlock(blueMap, rowBlue, colBlue, commandBlue);
			
			
			getScore(greenMap);
            getScore(blueMap);
            
            clearLight(greenMap);
            clearLight(blueMap);
			
			
//			printMap(greenMap);
            

		}
		System.out.println(score);
		System.out.println(countBlocks(greenMap) + countBlocks(blueMap));

	}

	// 문제의 정답을 출력하기 위한 map안의 블록 개수 세는 메소드
	private static int countBlocks(int[][] map) {
		int cnt = 0;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] == 1) {
					cnt++;
				}
			}
		}

		return cnt;
	}

	// 연한 부분에 블록이 존재한다면 아래로 밀어서 블록을 움직이자.
	private static void clearLight(int[][] map) {

		int inLightColor = 0;
		for (int i = 0; i < 4; i++) {
			if (map[0][i] == 1) {
				inLightColor++;
				break;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (map[1][i] == 1) {
				inLightColor++;
				break;
			}
		}

		// 연한 부분에 블록이 존재하는 행의 길이가 1 또는 2라면
		if (inLightColor > 0) {
			int move = inLightColor;
			for (int r = 5; r >= move; r--) {
				for (int c = 0; c < 4; c++) {
					map[r][c] = map[r - move][c];
				}
			}
			for (int r = 0; r < 2; r++) {
				for (int c = 0; c < 4; c++) {
					map[r][c] = 0;
				}
			}

		}
	}
	
	// 블록이 제거가능한지 판단하면서, 제거가능하다면 바로 그 줄을 삭제해주는 메소드
	private static void getScore(int[][] map) {
		
		for (int i = 0; i < 6; i++) {
			int idx = 0;
			boolean isFull = true;
			while (idx < 4) {
				if (map[i][idx] == 0) {
					isFull = false;
				}
				idx++;
			}
			// 제거가능한 행이라면 제거
			if (isFull) {
				for (int j = i; j >= 0; j--) {
					for (int c = 0; c < 4; c++) {
						if (j == 0) {
							map[j][c] = 0;
						} else {
							// 아래의 행을 위의 행의 값으로 대체
							map[j][c] = map[j - 1][c];
						}
					}
				}
				// 점수 증가
				score++;
			}
		}
	}

	private static void moveBlock(int[][] map, int row, int col, int command) {

		// 1번 블록이라면?
		if (command == 1) {
			for (int i = 0; i < 6; i++) {
				// 내가 블록을 내려놓을 수 있을 때까지 행을 증가
				if (i == 5) {
					map[i][col] = 1;
					break;
				}
				if (map[i][col] == 0 && map[i + 1][col] == 1) {
					map[i][col] = 1;
					break;
				}
			}
		}

		// 2번 블록이라면?(가로로 긴)
		else if (command == 2) {
			for (int i = 0; i < 6; i++) {
				if (i == 5) {
					map[i][col] = map[i][col + 1] = 1;
					break;
				}
				// 1x2 블록을 내려놓을 수 있는 곳은?
				// 현재 자리(i, col), (i, col+1)가 비어있고, 그 아래 자리 중 한군데라도 한 블록이 지탱해줄 수 있는 곳!
				if ((map[i][col] == 0 && map[i][col + 1] == 0)
						&& (map[i + 1][col] == 1 || map[i + 1][col + 1] == 1)) {
					map[i][col] = map[i][col + 1] = 1;
					break;
				}
			}
		}

		// 3번 블록이라면?(세로로 긴)
		else {
			for (int i = 0; i < 6; i++) {
				if (i == 4) {
					map[i][col] = map[i + 1][col] = 1;
					break;
				}
				// 2x1 블록을 내려놓을 수 있는 곳은?
				// 현재 자리(i, col), (i+1, col)가 비어있고, 그 밑 자리(i+2, col)가 채워져 있어야 함!
				if (map[i][col] == 0 && map[i + 1][col] == 0 && map[i + 2][col] == 1) {
					map[i][col] = map[i + 1][col] = 1;
					break;
				}
			}
		}
	}
	
	
	

	static void printMap(int[][] map) {
		System.out.println("---start---");
		for (int i=0; i<map.length; i++) {
			for (int j=0; j<map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
	}
}
