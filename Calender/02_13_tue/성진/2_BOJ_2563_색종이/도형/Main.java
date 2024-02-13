package BOJ._2563_색종이;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] arr = new int [102][102]; //padding
		
		int N = sc.nextInt();
		
		for (int n = 0; n<N; n++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			for (int i = x; i<x+10; i++) {
				for (int j = y; j<y+10; j++) {
					arr[i+1][j+1] = 1;
				}
			}
		}
		
		int answer =0;
		for (int i = 1; i<101; i++) { // padding 했으니까 1부터 100까지 검사 가능
			for (int j = 1; j<101; j++) {
				if (arr[i][j]==1) {
					answer++;
				}
			}
		}
		System.out.println(answer);
		sc.close();
	}

}
