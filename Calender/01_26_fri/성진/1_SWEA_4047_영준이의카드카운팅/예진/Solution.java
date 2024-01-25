package 영준이의카드카운팅_4047_240125;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		
//		System.setIn(new FileInputStream("src/input.txt"));
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int[][] cards = new int[4][14];  // 카드종류 4가지, 종류 별 13장(카드번호대로 인덱스 하려고 14)
			int[] ans = new int[4];  // 카드종류 별 부족한 개수를 담을 배열
			boolean error = false;  // 에러 여부: 기본값 에러 아님
			
			String input = bf.readLine();  // testCase 한줄 입력
			
			// StringTokenizer(문자열, 분리 기준이 되는 문자(여러개 가능), [기준문자 토큰 포함여부])
			StringTokenizer st = new StringTokenizer(input, "SDHC", true);
			// hasMoreTokens: 다음 토큰이 존재하는지 여부 판단(boolean)
			while(st.hasMoreTokens()) {
				String kind = st.nextToken();  // 문자는 카드종류 결정 기준
				int num = Integer.parseInt(st.nextToken());  // 문자열 -> 정수 형변환
				// kind 값에 따라 해당하는 ans 배열에 인덱스 번호를 1증가
				if (kind.equals("S")) cards[0][num]++;
				else if (kind.equals("D")) cards[1][num]++;
				else if (kind.equals("H")) cards[2][num]++;
				else if (kind.equals("C")) cards[3][num]++;
			}
			
			out:
			for (int i = 0; i < 4; i++) {
				for(int j = 1; j <= 13; j++) {
					if (cards[i][j] == 0) ans[i]++; // 인덱스의 값이 0이면 해당 배열에 부족한 카드 개수 1증가
					else if (cards[i][j] != 1) {  // 0제외, 1이 아니면 중복값이 있으므로 에러상태 변경 후 순회 종료
						error = true;
						break out;
					}
				}
			}
			
			System.out.printf("#%d ",tc);
			// 에러가 났으면 에러출력, 그렇지 않으면 ans 배열 출력
			if (error) {
				System.out.print("ERROR");
			} else {
				for (int i = 0; i < 4; i++) {
					System.out.print(ans[i]+" ");
				}
			}
			System.out.println();
		}
		bf.close();
	}
}
