package SWEA._1215_회문1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
	public static void main(String[] args) throws Exception {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for (int tc = 1; tc<=T;tc++) {
			int n = Integer.parseInt(bf.readLine());
			char[][] arr = new char[8][]; // 8 x 8 사이즈 고정
			
			for (int i = 0; i<8;i++) {
				arr[i] = bf.readLine().toCharArray(); // 한글자니까 char 를 쓰는 게 좋음.
				// char는 equals안쓰고 == 써도 됨. 기본형이라.
			}
			
			int ans = 0;
			
			Deque<Character> deque = new ArrayDeque<>(); //디큐? 데크?
			
			//범위가 다르기 때문에 가로 세로를 분리해야함.

			// 가로 (열이 8-n까지)
			for (int i = 0; i<8; i++) {
				for (int j = 0; j<=8-n; j++) {
					for (int k = 0; k<n; k++) {
						deque.add(arr[i][j+k]);						
					}
					
					boolean ispalindrome = true; // 한번이라도 대칭이 안맞으면 false 로 반환되도록, 초기값을 true로.
					while (deque.size()>1) { // 제시된 길이가 짝수냐 홀수냐로 나눌 수는 있을듯.
						char L = deque.pollFirst();
						char R = deque.pollLast();
						
						if (!(L==R)) {
							ispalindrome = !ispalindrome;
							break;
						}
					}
					deque.clear();
					if (ispalindrome) {
						ans++;
					}
					
				}
			}
			

			//세로 (행이 8-n까지)
			for (int j = 0; j<8; j++) {
				for (int i = 0; i<=8-n; i++) {
					for (int k = 0; k<n; k++) {
						deque.add(arr[i+k][j]);						
					}
					
					boolean ispalindrome = true;
					while (deque.size()>1) {
						char L = deque.pollFirst();
						char R = deque.pollLast();
						
						if (!(L==R)) {
							ispalindrome = !ispalindrome;
							break;
						}
					}
					deque.clear();
					if (ispalindrome) {
						ans++;
					}
					
				}
			}
			
			System.out.printf("#%d %d%n", tc,ans);
		}
		
		
		
		
		
	}

}
