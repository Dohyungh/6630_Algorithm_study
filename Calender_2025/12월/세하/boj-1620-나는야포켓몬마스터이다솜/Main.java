package boj_1620_나는야포켓몬마스터이다솜;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashMap<Integer, String> numToString = new HashMap<Integer, String>(); // 숫자로 문자 찾기 
		HashMap<String, Integer> stringToNum = new HashMap<String, Integer>(); // 문자로 숫자 찾기 

		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			numToString.put(i, str);
			stringToNum.put(str, i);
		}
		
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < M; i++) {
			String input = br.readLine();

			try {
				// input이 숫자인 경우
				int num = Integer.parseInt(input);
				sb.append(numToString.get(num) + "\n");
			} catch (NumberFormatException e) {
				// input이 문자열인 경우
				sb.append(stringToNum.get(input) + "\n");
			}
		}
		
		System.out.println(sb);
	}
}
