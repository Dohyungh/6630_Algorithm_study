package SWEA._1220_Magnetic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			boolean has1; // 1이 한개든 두개든 상관없음. 1이 하나라도 있는 상태에서 2를 만나느냐가 중요함.
			int temp;
			int ans = 0;
			for (int j = 0; j < N; j++) {
				has1 = false;
				temp = 0;
				for (int i = 0; i < N; i++) {
					if (arr[i][j] == 1 && !has1) { // 1을 만났는데 기다리던게 없으면
						has1 = !has1;	
					} else if (arr[i][j] ==2 && has1) { // 2를 만났고 기다리던게 있으면
						temp++;
						has1 = !has1;
					} // 그 이외 경우 아무것도 안함
				}
				
				ans += temp;
				
			}			
			System.out.printf("#%d %d%n",tc,ans);
			
		}
		
		br.close();
		
		
	}
}
