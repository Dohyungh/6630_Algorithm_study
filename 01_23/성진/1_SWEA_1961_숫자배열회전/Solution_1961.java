package _1961;

import java.io.FileInputStream;
import java.util.Scanner;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		/*
		   테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		System.setIn(new FileInputStream("src/_1961/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
			
			
			int N = sc.nextInt();
			// N x N 행렬 선언
			int[][] arr = new int[N][N];
			
			// N x N 행렬 값 입력하기
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			System.out.printf("#%d\n", test_case);
			
			// 기준값 n을 0부터 N-1까지 증가하며 90도, 180도, 270도 회전행렬 한줄씩 출력
			for (int n=0; n<N; n++) {
				
				
				
				// 90도 회전한 행렬 한 줄 출력
				// i = N-1부터 0까지 감소
				// 행 우선 감소, 이후 열 증가
				for (int i=N-1; i>=0; i--) {
					System.out.print(arr[i][n]);		
				}
				// 공백 출력
				System.out.print(" ");
				
				
				// 180도 회전한 행렬 한 줄 출력
				// i = N-1부터 0까지 감소
				// 열 우선 감소, 이후 행 감소
				for (int i=N-1; i>=0; i--) {
					System.out.print(arr[N-1-n][i]);
				}
				// 공백 출력
				System.out.print(" ");
				
				
				// 270도 회전한 행렬 한 줄 출력
				// i = 0부터 N-1까지 증가
				// 행 우선 증가, 이후 열 감소
				for (int i=0; i<N; i++) {
					System.out.print(arr[i][N-1-n]);
				}
				// 줄바꿈
				System.out.println();
				
			}
			
			
			

		}
	}
}
