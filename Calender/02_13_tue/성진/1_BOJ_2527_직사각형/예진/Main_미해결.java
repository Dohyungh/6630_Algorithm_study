package 직사각형_2527_240212;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// 미해결 입니당
		
		Scanner sc = new Scanner(System.in);
		
		for (int tc = 0; tc < 4; tc++) {
			int[] A = new int[4];
			int[] B = new int[4];
			for (int i = 0; i < 4; i++) {
				A[i] = sc.nextInt();
			}
			for (int i = 0; i < 4; i++) {
				B[i] = sc.nextInt();
			}
			
			char answer;
			if ((A[2] == B[0] && (A[1] == B[3] || A[3] == B[1])) ||
					(A[0] == B[2] && (A[3] == B[1] || A[1] == B[3]))) answer = 'c';
			
			else if (((B[3] == A[1] || B[1] == A[3]) && 
					((A[0] <= B[0] && B[0] < A[2]) || (A[0] < B[2] && B[2] <= A[2])))
					||
					((B[0] == A[2] || B[2] == A[0]) && 
					((A[1] <= B[1] && B[1] < A[3]) || (A[1] < B[3] && B[3] <= A[3])))) answer = 'b';
			
			else if (A[2] < B[0] || B[2] < A[0] || A[1] > B[3] || B[1] > A[3]) answer = 'd';
			else answer = 'a';
			
			System.out.println(answer);
		}
		sc.close();
	}
}
