package BOJ._11057_오르막수;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[][] answers = new int[1002][10]; // 0 ~ 9 로 시작.
		Arrays.fill(answers[1], 1);
		
		for (int i = 2; i<=1001; i++) {
			for (int j = 0; j <= 9; j++) {
				int sum = 0;
				for (int k = j; k <= 9; k++) {
					sum += answers[i-1][k];
				}
				answers[i][j] = sum % 10007;
			}
		}
		
//		int answer = 0;
//		for (int i = 0; i < 10; i++) {
//			answer+=answers[N][i];
//		}
		
		// 이렇게 하면 안됨. 하나의 원소는 모두 10007 보다 작지만
		// 합치면 커질 수 있기 때문에 
		// 다시 answer를 10007 나눠주거나.
		
		// 이렇게 아래줄에서 참고해서 써야 함.
		System.out.println(answers[N+1][0]);
		
		
		
		
		
		
	}
	

}
