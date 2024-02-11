package BOJ._2798_블랙잭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_fin {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr); // 정렬 한번 // 근데 정렬 안하고 막 돌려도 시간초과 안남.
		int answer = 0;
		
		//O(n^3)
		for (int i = 0; i<N-2; i++) { // 한계조건 N-3, N-2, N-1
			if(arr[i] >M-arr[0]-arr[1]) break; // 단일 최댓값 = 목표 - 최소1 - 최소2 ,, 넘으면 break

			for(int j =i+1; j<N-1; j++) { // 했던거 또 안하게 i+1부터
				if (arr[i]+arr[j] > M-arr[0]) break; // 2개까지 최댓값 = 목표 - 최소1 ,, 넘으면 break

				for(int r =j+1; r<N; r++) { // j+1부터
					int sum = arr[i] + arr[j] + arr[r];

					if (sum >M) break; // 넘는 순간 더 커질 일만 남았으므로 break

					answer = Math.max(sum, answer);
					
					
				}
			
			}
		}
		System.out.println(answer);
		br.close();

	}
	
}
