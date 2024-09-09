package BOJ._1759_암호만들기;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	// 만들어야 되는 문자열의 길이
	static int L;
	
	// 주어진 Character들의 개수 
	static int C;
	
	static StringBuilder sb;
	static char[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		C = sc.nextInt();
		
		sb = new StringBuilder();
		
		arr = new char[C];
		for (int i = 0 ; i < C; i++) {
			arr[i] = sc.next().charAt(0);
		}
		
		// 정렬해서 재귀 돌림
		Arrays.sort(arr);
		
		Solve(0, "", 0, 0);
		sb.delete(sb.length()-1, sb.length());
		System.out.print(sb.toString());

	}
	
	public static void Solve(int start, String str, int vowel, int consonant) {
		if(str.length() == L) {
			
			// 규칙 처리
			if (vowel >= 1 && consonant >= 2) {
				sb.insert(0, str + "\n");				
			}
			return;
		}
		
		// 넘어가면 끝 (무한호출 방지)
		if (start >= C) return;
		
		String newStr = "";
		newStr += str;
		
		// 안골랐지롱
		Solve(start+1, str, vowel, consonant);
		
		// 골랐지롱
		if (arr[start] == 'a' || arr[start] == 'e' || arr[start] == 'i' || arr[start] == 'o' || arr[start] == 'u') {
			Solve(start+1, newStr + arr[start], vowel+1, consonant);
		} else {
			Solve(start+1, newStr + arr[start], vowel, consonant+1);
		}
		
		
		
	}

}
