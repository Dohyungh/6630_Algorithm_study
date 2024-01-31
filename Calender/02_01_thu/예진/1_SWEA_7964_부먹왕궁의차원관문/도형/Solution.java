package SWEA._7964_부먹왕궁의차원관문;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			String[] temp = br.readLine().split(" ");
			int N = Integer.parseInt(temp[0]);
			int d = Integer.parseInt(temp[1]);
			
			int[] map = new int[N+2]; // 2개 더 만들어
			map[0] = 1; // 시작
			map[N+1] = 1; // 끝
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i < N+1; i++) {
				map[i] = Integer.parseInt(st.nextToken()); 
			}
			
			int past = 0;
			int ans = 0;
			for (int i = 1; i < N+2; i++) { // 0 들어가면 1나와서 안됨.
				if (map[i] == 1) {
					if (i-past > d) { //소숫점 오류 때문에 불안하니까 나누자.
						if (((i-past)%d) ==0) { // 딱 n+1 나오면 n
							ans+=(((i-past)/d)-1);
						} else { // 그냥 유리수 나오면 몫(값보다 작은 정수)
							ans+=((i-past)/d);
						}
					}
					past = i; // past 는 커버가 되든 안되든 항상 업데이트
				}
			}
			System.out.printf("#%d %d%n",tc,ans);
		}
	}

}
