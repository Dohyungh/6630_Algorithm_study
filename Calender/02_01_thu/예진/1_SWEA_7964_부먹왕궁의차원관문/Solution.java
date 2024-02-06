package 부먹왕국의차원관문_7964_240127;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		System.setIn(new FileInputStream("src/부먹왕국의차원관문_7964_240127/input.txt"));
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			String[] input = bf.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int dist = Integer.parseInt(input[1]);
			
			StringTokenizer st = new StringTokenizer(bf.readLine()," ");
			
			int ans = 0;
			
			int start = 0;    // 0연속의 시작점
			int temp = 0;     // 연속된 0의 개수
			while (start < N){   // 0의 개수를 셀 시작점이 인덱스 범위 안에 있을때까지
				if (Integer.parseInt(st.nextToken()) == 0) {  // 0을 찾으면
					temp=1;                                   // +1
					for (int i = start+1; i < N; i++) {     // 그 다음 인덱스부터 확인
						if (Integer.parseInt(st.nextToken()) != 0) { // 0이 끝나면
							temp= i-start;                    // 0의 길이는 i-start
							break;                            // 연속된 0찾기 종료
						}
						temp++;                      // 0이 계속되면 개수 올리기(조건을 빠져나갈 수 있어서)
					}
					ans += temp/dist;                // 필요한 차원관문 개수 더해주기
					start += temp+1;                 // start 위치 옮기기
				} else start++;                     // 0이 아니라면 start위치를 다시 하나 앞으로
			}
			
			System.out.printf("#%d %d\n", tc, ans);
		}
		bf.close();
	}

}
