package 부먹왕국의차원관문_7964_240127;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3_다른사람풀이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		System.setIn(new FileInputStream("src/부먹왕국의차원관문_7964_240127/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			int ans = 0;
			int temp = 1;

			for (int i = 0; i < N; i++) {
				if (br.read() == '0') {
					if (temp == dist) {
						ans++;
						temp = 1;
					} else temp++;
				} else temp =1;
				if (i < N-1) br.read();
				else br.readLine();   // 마지막에 줄읽기 해서 br 오류 방지
			}
			
			System.out.printf("#%d %d\n", tc, ans);
		}
		br.close();
	}

}
