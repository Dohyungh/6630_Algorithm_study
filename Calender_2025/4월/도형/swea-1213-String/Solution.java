package SWEA._1213_String;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;

		for (int tc = 1; tc <= T; tc++) {
			br.readLine(); // 테스트케이스 번호 무시

			char[] pattern = br.readLine().toCharArray();
			char[] text = br.readLine().toCharArray();

			int[] failure = makeFunction(pattern);

			int answer = 0;
			int j = 0;

			for (int i = 0; i < text.length; i++) {
				while (j > 0 && text[i] != pattern[j]) {
					j = failure[j - 1];
				}

				if (text[i] == pattern[j]) {
					if (j == pattern.length - 1) {
						answer++;
						j = failure[j]; // 다음 탐색으로
					} else {
						j++;
					}
				}
			}

			bw.write(String.format("#%d %d\n", tc, answer));
		}

		bw.flush();
		br.close();
	}

	public static int[] makeFunction(char[] pattern) {
		int[] failure = new int[pattern.length];
		int j = 0;

		for (int i = 1; i < pattern.length; i++) {
			while (j > 0 && pattern[i] != pattern[j]) {
				j = failure[j - 1];
			}
			if (pattern[i] == pattern[j]) {
				failure[i] = ++j;
			}
		}

		return failure;
	}
}
