package BOJ._1092_배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] crane = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) crane[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(crane);
		
		int M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		int[] box = new int[M];
		for (int i = 0; i < M; i++) box[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(box);
		
		if(box[M-1] > crane[N-1]) {
			System.out.println(-1);
			return;
		}
		
		boolean[] visited = new boolean[M];
		int[] craneCapa = new int[N];
		
		Arrays.fill(craneCapa, -1);
		
		label:
		for (int i = 0; i < N; i++) {
			for (int j = M-1; j >=0; j--) {
				if (crane[i] >= box[j]) {
					craneCapa[i] = j;
					continue label;
				}
			}
		}
		
		int answer = 0;
		while(M > 0) {
			for (int i = N-1; i>=0; i--) {
				for (int j = craneCapa[i]; j>= 0; j--) {
					if (visited[j]) continue;
					visited[j] = true;
					M--;
					break;
				}
				
			}
			answer++;
			
		}
		
		System.out.println(answer);
		
	}

}
