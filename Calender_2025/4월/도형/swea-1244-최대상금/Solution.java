package SWEA._1244_최대상금;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {
	
	static Integer[] arr;
	static int N;
	static int answer;
	static boolean[][] visited;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			char[] num = st.nextToken().toCharArray();
			N = Integer.parseInt(st.nextToken());
			
			arr = new Integer[num.length];
			for (int i = 0; i < num.length; i++) {
				arr[i] = num[i] - '0';
			}
			
			
			answer = 0;
			// 여기까지 입력입니다.
			
			visited = new boolean[(int)Math.pow(10, arr.length)][N+1];
			
			search(0);
			
			System.out.printf("#%d %d\n", tc, answer);
			
			
		}
		
		br.close();
		
		
	}
	
	public static void search(int depth) {
		
		int ans = 0;
		String str = "";
		for (int i = 0; i < arr.length; i++) str += arr[i];
		ans = Integer.parseInt(str);
		if (visited[ans][depth]) return;
		visited[ans][depth] = true;
		
		if (depth == N) {

			answer = Math.max(answer, ans);
			return;
		}
		
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i+1; j < arr.length; j++) {
				swap(i,j);
				search(depth + 1);
				swap(i,j);
			}
		}
		
	}
	
	public static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		
	}

}
