package swea_1289_원재의메모리복구;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		// 시간초과 났음.
		
//		BufferedReader br = new BufferedReader(
//				new InputStreamReader(new FileInputStream("src/swea_1289_원재의메모리복구/input.txt")));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int cnt = 0;
			st = new StringTokenizer(br.readLine(), "01", true);
			
			int front = Integer.parseInt(st.nextToken());
			if (front != 0) cnt++;
			
			while(st.hasMoreTokens()) {
				int temp = Integer.parseInt(st.nextToken());
				if (front != temp) cnt++;
				front = temp;
			}
			System.out.printf("#%d %d\n", tc, cnt);
		}
		br.close();
	}
}
