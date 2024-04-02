package boj_11057_오르막수;

import java.util.Scanner;

public class Main_성진 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[][] table = new int[N + 1][10];

		for (int i = 0; i < 10; i++) {
			table[1][i] = 1;
		}

		for (int i = 2; i < N + 1; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = j; k >= 0; k--) {
					table[i][j] += (table[i - 1][k] % 10007);
				}
			}
		}
		
		int answer = 0;

		for (int i = 0; i < 10; i++) {
			answer += table[N][i];
		}

		System.out.println(answer % 10007);

	}
	
}
