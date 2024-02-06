package swea_11315_오목판정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/swea_11315_오목판정/input.txt")));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			boolean isOmok = false;
			
			// 돌 상태 입력
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					arr[r][c] = br.read();
				}
				br.readLine();
			}
			
			// 델타 배열 (상, 하, 좌, 우)
			int[] dr = {-1, 1, 0, 0};
			int[] dc = {0, 0, -1, 1};
			
			// 수평, \대각선 오목 판단
			out:  // 수평과 \방향 대각선 오목이 가능한 범위만 확인
			for (int r = 0; r < N; r++) {
				for (int c = 0; c <= N-5; c++) {
					int cnt = 0;
					if (arr[r][c] == 'o') {  // 돌이 있으면
						int nr, nc;
						if ( r <= N-5 && c <= N-5) {  // 좌표가 대각선 오목 유효범위이면
							cnt = 1;                  // 이미 하나가 동일한 상태여서 cnt = 1;
							for (int i = 1; i < 5; i++) {  // 4개 더 연속해 있는지 확인
								// 대각선 방향으로 델타 이동
								nr = r + dr[1]*i;
								nc = c + dc[3]*i;
								if (arr[nr][nc] == 'o') cnt++;  // 다음 좌표도 돌이 있으면 카운트 +1
								else break;                     // 아니라면 순회 종료
							}
							isOmok = true;        // 중도 종료 없이 끝났다면 오목은 성공
							break out;            // 반복 종료
						}
						
						// 대각선이 아니라면 수평 검토, cnt 초기화, 이미 돌이 하나 있는 상태
						cnt = 1;
						for (int i = 1; i < 5; i++) {  // 연속된 돌이 5개냐?
							nc = c + dc[3]*i;          // 오른쪽으로 순차 이동
							if (arr[r][nc] == 'o') cnt++;  // 돌이 연속으로 있으면 카운트 +1
							else break;                    // 아니라면 순회 종료
						}
						if (cnt == 5) {          // 카운트가 5라면 연속된 돌이 5개이므로
							isOmok = true;       // 오목 성공
							break out;           // 순회 종료
						}
					}
				}
			}
			
			// 수직, /대각선
			out:  // 수직과 /대각선 방향 오목이 가능한 범위만 확인
			for (int r = 0; r <= N-5; r++) {
				if (isOmok) break out;            // 수평, \대각선 검토에서 오목에 성공했으면 순회 종료
				for (int c = 0; c < N; c++) {
					int cnt = 0;
					if (arr[r][c] == 'o') {         // 돌이 있으면
						int nr, nc;
						if ( r <= N-5 && c >= 4) {      // \대각선 오목 유효범위이면
							cnt = 1;                    // 이미 찾은 돌 하나
							for (int i = 1; i < 5; i++) {   // 돌이 5개 연속되어있냐?
								nr = r + dr[1]*i;          // 좌측 아래로 순차적 이동
								nc = c + dc[2]*i;
								if (arr[nr][nc] == 'o') cnt++;   // 돌이 있다면 카운트 +1
								else break;                      // 아니라면 반복 종료
							}
							isOmok = true;                 // 중도 종료 없이 무사히 끝났다면 오목 성공
							break out;                     // 순회 종료
						}
						
						// 대각선 오목이 아니라면 수직 방향 확인
						cnt = 1;                         // 이미 찾은 돌 하나
						for (int i = 1; i < 5; i++) {    // 돌이 5개 연속되어 있냐?
							nr = r + dr[1]*i;            // 아래 방향으로 순차 이동
							if (arr[nr][c] == 'o') cnt++;  // 돌이 있으면 카운드 +1
							else break;                    // 아니라면 반복 종료
						}
						if (cnt == 5) {                 // 카운트가 5이면 연속된 돌이 5개이므로
							isOmok = true;              // 오목 성공
							break out;                  // 순회 종료
						}
					}
				}
			}
			if (isOmok) System.out.printf("#%d %s\n", tc, "YES");  // 오목이 성공이면 "YES"출력
			else System.out.printf("#%d %s\n", tc, "NO");          // 실패이면 "NO"출력
		}
		br.close();
	}

}
