package BOJ_17413_단어뒤집기2;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 전체 문자열을 '<', '>', ' ' 3가지 구분자로 분리해서 토큰화, 구분자도 토큰에 포함
		StringTokenizer st = new StringTokenizer(sc.nextLine(), "<> ", true);
		
		// 토큰이 다 떨어질 때까지 반복
		while(st.hasMoreTokens()) {
			
			// 토큰 하나 꺼내서
			String token = st.nextToken();
			
			// 그게 '<' 이면
			if (token.equals("<")) {
				
				// 일단 그대로 출력하고
				System.out.print(token);
				
				// 하나 더 뽑아서
				String next = st.nextToken();
				
				// 그게 '>'가 아니면 계속 그냥 출력
				while (!next.equals(">")) {
					System.out.print(next);
					next = st.nextToken();
				}
				
				// '>'가 나오면 반복문 탈출하니까 마저 출력
				System.out.print(next);
				
			// 뽑은 토큰이 공백이면 그대로 출력(괄호 밖에 있는 공백일것이다)
			} else if (token.equals(" ")){
				System.out.print(token);
				
			// 다 아니라면 단어일 것, 토큰을 문자 배열로 변환해서 거꾸로 출력
			} else {
				char[] word = token.toCharArray();
				for (int i = word.length-1; i >= 0; i--) {
					System.out.print(word[i]);
				}
			}
		}
		// 문단속
		sc.close();
	}
}