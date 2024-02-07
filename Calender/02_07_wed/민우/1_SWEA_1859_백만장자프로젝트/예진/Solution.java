package 백만장자프로젝트_1859_240206;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		System.setIn(new FileInputStream("src/백만장자프로젝트_1859_240206/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			int[] price = new int[N];
			for (int i = 0; i < N; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			long profit = 0;
			int sale = N-1;
			for (int i = N-2; i >= 0; i--) {
				int gap = price[sale] - price[i];
				if (gap > 0) profit += gap;
				else sale = i;
			}
			
			System.out.printf("#%d %d\n", tc, profit);
		}
		br.close();
	}

}
