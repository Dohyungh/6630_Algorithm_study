package boj_11049_행렬곱셈순서;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[][] matrix = new int[N + 1][3];

		for (int i = 0; i < N; i++) {

			int row = sc.nextInt();
			int col = sc.nextInt();

			matrix[i + 1][0] = row;
			matrix[i + 1][1] = col;

			if (i == 0) {
				matrix[i + 1][2] = 0;
			}

			else if (i == 1) {
				matrix[i + 1][2] = matrix[i][0] * row * col;
			}

			else {

				matrix[i + 1][2] = Math.min(matrix[i][2] + matrix[1][0] * row * col,
						matrix[i - 1][2] + matrix[i][0] * row * col + matrix[1][0] * matrix[i][0] * col);

			}
			printMap(matrix);

		}
		System.out.println(matrix[N][2]);

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
