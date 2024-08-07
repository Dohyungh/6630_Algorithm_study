/*
 * 한 면의 칸 수가 짝수/홀수인 게임판 별로 가능한 경우의 수의 규칙이 있었다.
 * 그 규칙을 코드로 구현했음.
 */
package boj_1846_장기;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		// N이 4보다 작으면 불가
		if (N < 4) sb.append(-1);

		// N이 짝수인 경우
		else if (N % 2 == 0){

			sb.append(N/2 + "\n");
			for (int i = 1; i < N/2; i++) {
				sb.append(i + "\n");
			}
			for (int i = N/2; i < N-1; i++) {
				sb.append(i+2 + "\n");
			}
			sb.append(N/2 + 1 + "\n");

		// N이 홀수인 경우
		} else {

			sb.append(N/2 + 1 + "\n");
			for (int i = 1; i <= N/2; i++) {
				sb.append(i + "\n");
			}
			sb.append(N + "\n");
			for (int i = N/2 + 2; i < N; i++) {
				sb.append(i + "\n");
			}
		}

		System.out.println(sb.toString());
		sc.close();
	}
}