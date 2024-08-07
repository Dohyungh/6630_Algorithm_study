package boj_1846_장기;

import java.util.Scanner;

public class Main {
	
	static int[][] map;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		StringBuilder sb = new StringBuilder();
		
		int N = sc.nextInt();
		
		
		if (N == 3) {
			System.out.println(-1);
			return;
		}
		
		// 짝수 판의 경우
		if (N % 2 == 0) {
			
			sb.append(N/2 + "\n");
			for (int i=1; i<N/2; i++) {
				sb.append(i + "\n");
			}
			for (int i=N/2+2; i<=N; i++) {
				sb.append(i + "\n");
			}
			sb.append(N/2+1);
						
			
		}
		
		else {
			sb.append(N/2 + 1 + "\n");
			for (int i=1; i<N/2+1; i++) {
				sb.append(i + "\n");
			}
			sb.append(N + "\n");
			for (int i=N-(N/2-1); i<N; i++) {
				sb.append(i + "\n");
			}
			
		}
		
		
		System.out.println(sb);
		
		
		
	}
	
	
	
}
