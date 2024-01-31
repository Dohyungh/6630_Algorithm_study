package SWEA._5432_쇠막대기자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_fin {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			String[] str = br.readLine().split("");
			int stack = 0;
			int ans = 0;
			for (int i = 0; i<str.length; i++) {
				if (i<str.length-1&& str[i].equals("(") && !str[i+1].equals(")")) { // 끝이 ( 인 경우가 있을까..
					stack++;
				} else if (i>1 && str[i].equals(")") && !str[i-1].equals("(")) { // 처음이 ) 인 경우가 있을까..
					stack--;
					ans++;
				} else if (str[i].equals("(") && str[i+1].equals(")")) { // 레이저 파바박
					ans += stack;
				}
			}
			
			System.out.printf("#%d %d%n",tc,ans);
			
		}
	}
}
