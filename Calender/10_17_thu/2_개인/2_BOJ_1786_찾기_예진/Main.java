import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] sentence = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		int[] p = new int[pattern.length];
		int answer = 0;
		StringBuilder sb = new StringBuilder();
		
		// 실패함수 만들기
		int j = 0;
		for (int i = 1; i < p.length; i++) {
			while (pattern[j] != pattern[i] && j > 0) j = p[j-1];
			if (pattern[j] == pattern[i]) p[i] = ++j;
		}
		
		// 패턴 추출
		int start = 0;
		int i = 0;
		while (start <= sentence.length - pattern.length) {
			
			while (i < pattern.length) {
				if (sentence[start + i] != pattern[i]) {
					if (i == 0) start++;
					else {
						start = (start + i) - p[i-1];
						i = p[i-1];
					}
					break;
				}
				i++;
			}
			
			if (i == pattern.length) {
				answer++;
				sb.append(start+1 + " ");
				start = (start + i) - p[i-1];
				i = p[i-1];
			}
		}
		
		System.out.println(answer);
		System.out.println(sb.toString());
		
		br.close();

	}

}