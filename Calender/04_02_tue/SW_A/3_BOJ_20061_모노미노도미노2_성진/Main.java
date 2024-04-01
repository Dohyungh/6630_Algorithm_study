package boj_20061_모노미노도미노;

import java.util.Scanner;

public class Main {

	static int N;
	static int command;
	static int row;
	static int col;

	static int[][] greenMap;
	static int[][] blueMap;

	static int score;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		greenMap = new int[6][4];
		blueMap = new int[4][6];

		for (int i = 0; i < N; i++) {

			command = sc.nextInt();
			row = sc.nextInt();
			col = sc.nextInt();

			moveBlock();
			getScore();
			clearLight();

		}

		System.out.println(score);
		System.out.println(countBlocks());

	}

	private static int countBlocks() {
		int cnt = 0;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (blueMap[j][i] == 1) {
					cnt++;
				}
				if (greenMap[i][j] == 1) {
					cnt++;
				}
			}
		}

		return cnt;
	}

	private static void clearLight() {

		int inLightGreen = 0;
		int inLightBlue = 0;
		for (int i = 0; i < 4; i++) {
			if (greenMap[0][i] == 1) {
				inLightGreen++;
				break;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (greenMap[1][i] == 1) {
				inLightGreen++;
				break;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (blueMap[i][0] == 1) {
				inLightBlue++;
				break;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (blueMap[i][1] == 1) {
				inLightBlue++;
				break;
			}
		}

		if (inLightGreen > 0) {
			int move = inLightGreen;
			for (int r = 5; r >= move; r--) {
				for (int c = 0; c < 4; c++) {
					greenMap[r][c] = greenMap[r - move][c];
				}
			}
			for (int r = 0; r < 2; r++) {
				for (int c = 0; c < 4; c++) {
					greenMap[r][c] = 0;
				}
			}

		}
		if (inLightBlue > 0) {
			int move = inLightBlue;
			for (int c = 5; c >= move; c--) {
				for (int r = 0; r < 4; r++) {
					blueMap[r][c] = blueMap[r][c - move];
				}
			}
			for (int c = 0; c < 2; c++) {
				for (int r = 0; r < 4; r++) {
					blueMap[r][c] = 0;
				}
			}
		}

	}

	private static void getScore() {

		for (int i = 0; i < 6; i++) {
			int idx = 0;
			boolean isGreenFull = true;
			boolean isBlueFull = true;
			while (idx < 4) {
				if (greenMap[i][idx] == 0) {
					isGreenFull = false;
				}
				if (blueMap[idx][i] == 0) {
					isBlueFull = false;
				}
				idx++;
			}
			if (isGreenFull) {
				for (int j = i; j >= 0; j--) {
					for (int c = 0; c < 4; c++) {
						if (j == 0) {
							greenMap[j][c] = 0;
						} else {
							greenMap[j][c] = greenMap[j - 1][c];
						}
					}
				}
				score++;
			}
			if (isBlueFull) {
				for (int j = i; j >= 0; j--) {
					for (int r = 0; r < 4; r++) {
						if (j == 0) {
							blueMap[r][j] = 0;
						} else {
							blueMap[r][j] = blueMap[r][j - 1];
						}
					}
				}
				score++;
			}
		}

	}

	private static void moveBlock() {

		if (command == 1) {
			for (int i = 0; i < 6; i++) {
				if (i == 5) {
					greenMap[i][col] = 1;
					break;
				}
				if (greenMap[i][col] == 0 && greenMap[i + 1][col] == 1) {
					greenMap[i][col] = 1;
					break;
				}
			}

			for (int i = 0; i < 6; i++) {
				if (i == 5) {
					blueMap[row][i] = 1;
					break;
				}
				if (blueMap[row][i] == 0 && blueMap[row][i + 1] == 1) {
					blueMap[row][i] = 1;
					break;
				}
			}
		}

		else if (command == 2) {
			for (int i = 0; i < 6; i++) {
				if (i == 5) {
					greenMap[i][col] = greenMap[i][col + 1] = 1;
					break;
				}
				if ((greenMap[i][col] == 0 && greenMap[i][col + 1] == 0)
						&& (greenMap[i + 1][col] == 1 || greenMap[i + 1][col + 1] == 1)) {
					greenMap[i][col] = greenMap[i][col + 1] = 1;
					break;
				}
			}
			for (int i = 0; i < 6; i++) {
				if (i == 4) {
					blueMap[row][i] = blueMap[row][i + 1] = 1;
					break;
				}
				if (blueMap[row][i] == 0 && blueMap[row][i + 1] == 0 && blueMap[row][i + 2] == 1) {
					blueMap[row][i] = blueMap[row][i + 1] = 1;
					break;
				}
			}

		}

		else {
			for (int i = 0; i < 6; i++) {
				if (i == 4) {
					greenMap[i][col] = greenMap[i + 1][col] = 1;
					break;
				}
				if (greenMap[i][col] == 0 && greenMap[i + 1][col] == 0 && greenMap[i + 2][col] == 1) {
					greenMap[i][col] = greenMap[i + 1][col] = 1;
					break;
				}
			}
			for (int i = 0; i < 6; i++) {
				if (i == 5) {
					blueMap[row][i] = blueMap[row + 1][i] = 1;
					break;
				}
				if ((blueMap[row][i] == 0 && blueMap[row + 1][i] == 0)
						&& (blueMap[row][i + 1] == 1 || blueMap[row + 1][i + 1] == 1)) {
					blueMap[row][i] = blueMap[row + 1][i] = 1;
					break;
				}
			}

		}

	}

	static void printMap(int[][] map) {
		System.out.println("--start--");
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
