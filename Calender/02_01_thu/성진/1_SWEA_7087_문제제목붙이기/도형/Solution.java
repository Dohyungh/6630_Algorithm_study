package SWEA._7087_문제제목붙이기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//ASCII , charAt 써보자
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			char[] firsts = new char[N];
			
			for (int i = 0; i < N; i++) {
				firsts[i] = br.readLine().charAt(0);
			}
			
			// 'A' : 32 , 'Z' : 57 // 26개
			
			int[] count = new int[27]; // 굳이 27개 할 필요 없을 듯 // 이 아니라 있네?
			
			for (char first : firsts) {
				count[first-'A']++;
			}
			
			for (int i = 0; i<27; i++) {
				if (count[i] ==0) {
					System.out.printf("#%d %d%n",tc,i);
					break;
				}
			}
		}
	}
}
