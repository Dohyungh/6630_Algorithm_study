package 색종이_2563_240212;

import java.util.Scanner;

public class Main {
	
	static boolean[][] covered;
	static int answer;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		covered = new boolean[101][101];
		answer = 0;
		
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			int start = sc.nextInt();
			int line = sc.nextInt();
			fill(start, line);
		}
		
		System.out.println(answer);
		sc.close();
	}

	public static void fill(int start, int line) {
		for (int r = line; r < line + 10; r++) {
			for (int c = start; c < start + 10; c++) {
				if (!covered[r][c]) {
					covered[r][c] = true;
					answer++;
				}
			}
		}
	}
}
