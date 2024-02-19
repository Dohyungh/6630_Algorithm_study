package JOL._1202_조커;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] arr = new int[N];
		int numJoker = 0;
		for (int i = 0; i<N; i++) {
			int num = sc.nextInt();
			if (num ==0) numJoker++;
			arr[i] = num;
		}
		
		Arrays.sort(arr);
		
		// 틀린 테케에 끼워맞추는.. 방식으로 하나하나씩 추가
		
		
		if(numJoker == N) {
			System.out.println(N);
			return;
		}
		
		
		// 1000 개짜리 괴랄한 테케에서 3개차이로 모자라게 오답나옴 
		// 정답 : 829
		// 출력 : 826
		int answer = 1;
		int min = arr[numJoker];
		int max = arr[numJoker];
		int idx = numJoker;
		int leftJoker = numJoker;
		while (idx < N-1) {
			if (arr[idx+1] - arr[idx] == 1) {
				max = arr[idx+1];
				answer = Math.max(answer, max-min+1+leftJoker);
				idx++;
				continue;
			}
			if (arr[idx+1] - max > leftJoker+1) {
				answer = Math.max(answer, max - min+1+leftJoker);
				leftJoker = numJoker;
				min = arr[idx+1];
				idx++;
				continue;
				
			}
			if (arr[idx+1] - max <= leftJoker+1 && arr[idx+1] != arr[idx]) {
				max = arr[idx+1];
				leftJoker -= (arr[idx+1] - arr[idx]-1);
				answer = Math.max(answer, max - min+1+leftJoker);

						
				
			}
			idx++;
			
			
		}
		
		System.out.println(answer);
		
		
	}

}
