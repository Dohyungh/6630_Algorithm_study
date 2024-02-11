package BOJ._2798_블랙잭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int answer = 0;
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
		
		boolean[] visited = new boolean[N];
		combination(arr,visited,0,3,M);
		
		System.out.println(answer);
		br.close();

	}
	//O(2^n)
	public static void combination(int[] arr, boolean[] visited, int depth, int r, int M) {
		if (answer!=M) {
			if (depth == r) {
				int sum = 0;
				for (int i = 0; i < arr.length; i++) {
					if (visited[i]) sum+=arr[i];
				}
				if (sum > M) {
					sum = 0;
				} else {
					answer = Math.max(answer, sum);
				}
			}
			
			for (int i = 0; i < arr.length; i++) {
				if (!visited[i]) {
					visited[i] = true;
					combination(arr, visited, depth+1, r, M);
					visited[i] = false;
				}
			}
		}
	}
	

}
