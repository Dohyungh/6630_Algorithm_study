package BOJ._8958_OX퀴즈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		//하하하
		for (int tc = 1 ; tc <= T; tc++) {
			char[] ox = br.readLine().toCharArray();
			
			int idx = 0;
			int cnt = 0;
			int sum = 0;
			while (idx < ox.length) {
				if (ox[idx] == 'O') {
					cnt++;
					sum+=cnt;
				}
				if (ox[idx] == 'X') {
					cnt = 0;
				}
				idx++;
			}
			
			System.out.println(sum);
		}
		
		br.close();
	}

}
