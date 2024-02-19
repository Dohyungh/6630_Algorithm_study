package JOL._1202_조커;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
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
		
		if(numJoker == N) {
			System.out.println(N);
			sc.close(); //틈새 문닫기
			return;
		}
		
		// 최대 길이 1000개 시간 제한 1s
		// 내가 2중 for문을 병적으로 안쓸라 그러긴 함.
		// 근데 이게 최선인가..
		int answer = 1;
		for (int i = numJoker; i <N; i++) {  // 그냥 각 i 마다 (완전탐색)
			int leftJoker = numJoker;
			int min = arr[i]; // 최솟값을 i 위치로 고정하고
			int max = arr[i];
			for (int j = i+1; j<N; j++) { //최대로 멀리 어디까지 갈 수 있는가를 보는 거임.
				if (arr[j] > max && arr[j] - max <= leftJoker+1) { // max보다 클때 (즉, 갱신될 여지가 있을때) 남은 조커로 건너갈 수 있는지를 보는 거임.
					leftJoker -= arr[j] - max -1;
					max = arr[j];
				}
			}
			answer = Math.max(answer, max -min +1 +leftJoker);
		}
		System.out.println(answer);
		
		sc.close();
	}

}
