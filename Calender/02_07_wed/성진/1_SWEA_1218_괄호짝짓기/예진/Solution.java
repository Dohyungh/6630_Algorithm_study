package swea_1218_괄호짝짓기;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	static char[] mark;
	static int top;
	public static void main(String[] args) throws FileNotFoundException {
		
//		File file = new File("src/swea_1218_괄호짝짓기/input.txt");
//		Scanner sc = new Scanner(file);
		
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(sc.nextLine());
			mark = new char[N];			// 스택 배열 최대 크기 N
			top = -1;					// 스택 최상단 인덱스 초기화
			int ans = 1;				// 유효성 초기화
			
			// 전체 괄호 토큰화
			st = new StringTokenizer(sc.nextLine(), "()<>{}[]", true);
			
			// 토큰 순서대로 반환
			while (st.hasMoreTokens()) {
				char token = st.nextToken().charAt(0);
				
				// 토큰이 열린 괄호면 스택 배열에 push
				if (token == '(' || token == '<' || token == '{' || token == '[') push(token);
				
				// 토큰이 닫힌 괄호면
				else {
					// 스택 배열에 원소가 남아있고, 가장 최상단 원소와 닫힌 괄호의 종류가 일치하면 pass
					if (!isEmpty() && isMatched(token, pop())) continue;
					// 스택 배열에 남은 원소가 없거나, 닫힌 괄호와 종류가 일치하지 않는 경우 유효성 0, 검증 종료
					else {
						ans = 0;
						break;
					}
				}
			}
			if (!isEmpty()) ans = 0;
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	
	// 스택형 배열 가장 위에 추가
	public static void push(char input) {
		mark[++top] = input;
	}
	
	// 스택형 배열 가장 위 값 반환
	public static char pop() {
		return mark[top--];
	}
	
	// 스택이 비었는지 확인
	public static boolean isEmpty() {
		return top == -1;
	}
	
	// 열린 괄호와 닫힌 괄호의 종류가 일치하는지 확인
	public static boolean isMatched(char token, char output) {
		return (token == ')' && output == '(') || (token == '>' && output == '<') || 
				(token == '}' && output == '{') || (token == ']' && output == '[');
	}
}