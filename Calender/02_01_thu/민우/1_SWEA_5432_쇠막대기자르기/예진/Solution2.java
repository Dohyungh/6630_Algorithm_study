package swea_5432_쇠막대기자르기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader
				(new FileInputStream("src/swea_5432_쇠막대기자르기/input.txt")));
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), "()", true);
			int ans = 0;
			int pipe = 0;
			int open = 0;
			String temp = null;

			while (st.hasMoreTokens()) {     // 토큰이 남아 있다면
				String input = st.nextToken();
				if (input.equals("(")) {
					open++;
				}
				else {
					if (temp.equals("(")) {
						open--;
						ans += open;
					} else if (temp.equals(")")) {
						open--;
						pipe++;
					}
				}
				temp = input;
			}
			ans += pipe;
			System.out.printf("#%d %d\n", tc, ans);
		}
		br.close();
	}

}
