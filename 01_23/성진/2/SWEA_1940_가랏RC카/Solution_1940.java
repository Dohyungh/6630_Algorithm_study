package _1940;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		System.setIn(new FileInputStream("src/_1940/input.txt"));

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
			
			// command의 수 N
			int N = sc.nextInt();
			// 이동거리
			int answer = 0;
			// 속도
			int vel = 0;
			
			
			// command의 수 N만큼 반복
			for (int n=0; n<N; n++) {
				// cmd 번호 입력값 저장
				int cmd = sc.nextInt();
				
				// command가 0(현재 속도 유지)인 경우 아무것도 실행 X
				if (cmd==0) {
					
				}
				
				// command가 0이 아닌 경우
				else {
					
					// 가속도(acc) command 추가적으로 입력받기
					int acc = sc.nextInt();
					// command가 1(가속)인 경우 기존 속도(vel)에 가속도(acc) 더하기
					if (cmd==1) {
						vel += acc;
					}
					// command가 2(감속)인 경우 기존 속도(vel)에 가속도(acc) 빼기
					else {
						vel -= acc;
						// 만약 속도가 0보다 작아질 경우 속도는 0으로 조정
						if (vel<0) {
							vel = 0;
						}
					}
				}
				
				
				
				// 각 command 실행될 때마다 계산한 vel을 answer에 더하기
				answer += vel;
				
			}
			
			
			System.out.printf("#%d %d\n", test_case, answer);
			
			
		
			
		}
	}
}
