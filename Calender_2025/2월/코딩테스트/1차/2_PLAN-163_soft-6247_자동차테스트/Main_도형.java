package SOFT._6247_자동차테스트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int[][] query = new int[Q][3];
		for (int i = 0; i < Q; i++) {
			query[i][0] = i;
			query[i][1] = Integer.parseInt(br.readLine());
			query[i][2] = -1;
		}
		
		Arrays.sort(query, (o1, o2) -> o1[1] - o2[1]);
		
		int pointer = 0;
		
		for (int i = 0; i < Q; i++) {
			int m = query[i][1];
			while (pointer < N && arr[pointer] < m) {
				pointer++;
			}
			
			
			if (pointer == N || arr[pointer] > m) {
				query[i][2] = 0;
				continue;
			}
			
			query[i][2] = pointer * (N - pointer - 1);
		}
		Arrays.sort(query, (o1,o2) -> o1[0] - o2[0]);
		
		for (int i = 0; i < Q; i++) {
			System.out.println(query[i][2]);
		}
	}

}
