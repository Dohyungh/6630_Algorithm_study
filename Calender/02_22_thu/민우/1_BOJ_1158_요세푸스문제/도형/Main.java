package BOJ._1158_요세푸스문제;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] arr = new int[N];
		for (int i = 0; i<N; i++) {
			arr[i] = i+1;
		}
		
		
		boolean[] visited = new boolean[N];
		
		System.out.print("<");
		
		int cnt = 0;
		int idx = 0;
		int k = 0;
		while (cnt < N) { // 횟수 계산 귀찮으니까 (while 문 + 포인터)st 로
			if (!visited[idx]) {
				k++;
			}
			if (k ==K) {
				if (cnt == N-1) {
					System.out.print(arr[idx]+">"); // 이래야만 했나
					break;
				}
				System.out.print(arr[idx]+", ");
				k = 0;
				cnt++;
				visited[idx] = true;
			}
			idx = (idx+1)%N; // 뱅글뱅글
		}
	}
}
