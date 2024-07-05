package BOJ._11054_가장긴바이토닉부분수열;

import java.util.Scanner;

public class Main {
	
	// 1. 매개변수 탐색?
		// 꼭짓점 숫자를 만났을 때 pass nonpass 고려하기가 어려울 것 같음
	// 2. 선택/비선택 증가/감소/꼭짓점 으로 dp 테이블 만들기
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		int[][] forward = new int[N][2]; // 선택했을때 최장 증가부분수열[0] / 선택안했을떄 최장 증가 부분수열[1]
		int[][] backward = new int[N][2];
		
		
		
		// 앞으로 최장 증가 부분 수열
		// 초기화
		forward[0][1] = 0;
		
		// 선택했을 때 최솟값 1
		for (int i = 0; i < N; i++) {
			forward[i][0] = 1;
		}
		
		// dp 업데이트 출발
		for (int i = 1; i < N; i++) {
			
			//선택 안 할 경우는 그냥 이전 까지의 최대 길이를 가져오면 됨
			forward[i][1] = Math.max(forward[i-1][0], forward[i-1][1]);
			
			// i 번째 숫자를 선택할 경우, 0 ~ i-1 번째 숫자들 중에 선택했고, i 번째 숫자보다 작은 경우 중에 가장 긴 길이를 찾아서
			// +1 해줌
			int max = 0;
			for (int j = i-1; j>=0; j--) {
				if (arr[i] > arr[j]) {
					max = Math.max(max, forward[j][0]);
				}
			}
			forward[i][0] = max+1;
		}
		
		
		
		// 뒤로 최장 증가 부분 수열
		backward[N-1][1] = 0;
		for (int i = N-1; i>=0; i--) {
			backward[i][0] = 1;
		}
		for (int i = N-2; i>=0; i--) {
			backward[i][1] = Math.max(backward[i+1][0], backward[i+1][1]);
			
			int max = 0;
			for (int j = i+1; j < N; j++) {
				if (arr[i] > arr[j]) {
					max = Math.max(max, backward[j][0]);
				}
			}
			backward[i][0] = max+1;
		}
		
		
		
		// 합했을 때 가장 긴 거 (꼭짓점 숫자가 두번 카운팅 됐으므로 -1)
		int answer = 0;
		for (int i = 0; i < N; i++) {
			answer = Math.max(answer, forward[i][0] + backward[i][0] -1);
		}
		
		
		System.out.println(answer);
		
		sc.close();
	}

}
