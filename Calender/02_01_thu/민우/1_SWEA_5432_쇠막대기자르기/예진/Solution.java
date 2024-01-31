package swea_5432_쇠막대기자르기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
//		BufferedReader br = new BufferedReader(new InputStreamReader
//				(new FileInputStream("src/swea_5432_쇠막대기자르기/input.txt")));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), "()", true);
			Stack<Integer> temp = new Stack<>();
			List<Integer> laz = new ArrayList<>();
			
			int ans = 0;
			int i = 0;   // 반복 횟수를 체크
			while (st.hasMoreTokens()) {     // 토큰이 남아 있다면
				String input = st.nextToken();     // 토큰 하나를 가져와서
				if (input.equals("(")) {           // 토큰이 "("이면
					temp.push(i);                  // Stack에 인덱스 번호로 저장한다.
				} else {
					if (i - temp.peek() == 1) {     // 아니라면 Stack의 가장 최근값을 조회해서 현재 인덱스와 차이가 1인지 확인
						laz.add(temp.pop());        // 차이가 1이면 레이저이므로 레이저 인덱스를 저장한다.
					} else {                        // 차이가 1이 아니라면 '쇠막대기'
						for(int l : laz) {          // 현재까지 발견된 레이저 인덱스 리스트를 순회하면서 
							if (l < i && l > temp.peek()) ans++;   // 쇠막대기 범위 안에 있는 레이저 수를 더한다.
						}
						ans++;                      // 잘린 쇠막대기 수 = 레이저 수 + 1
						temp.pop();                 // 잘린 쇠막대기 수를 구했으면 해당 stack 제거
					}
				}
				i++;     // 반복횟수 증가
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
		br.close();
	}

}
