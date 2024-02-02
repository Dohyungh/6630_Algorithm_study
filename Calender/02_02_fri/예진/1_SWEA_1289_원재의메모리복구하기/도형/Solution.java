package SWEA._1289_원재의메모리복구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		//왼 - > 오로 읽으면서 스위칭 횟수를 세야되는 거군!
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			String[] str = br.readLine().split("");
			
			int[] arr = new int[str.length];
			
			for (int i = 0; i < str.length; i++) {
				arr[i] = Integer.parseInt(str[i]);
			}
			
			int now = 1;
			int ans = 0;
			for (int i = 0; i < str.length; i++) {
				if (arr[i] == now) {
					ans++;
					now = 1-now;
				}
			}
			
			System.out.printf("#%d %d%n",tc,ans);
			
		}
		
	}
}
