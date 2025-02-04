package soft_6250_성적평가;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] score = new int[4][N];

		// 개별 대회 점수 입력
		for (int r = 0; r < 3; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < N; c++) {
				score[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 모든 대회의 점수 총 합 입력
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < 3; r++) {
				score[3][c] += score[r][c];
			}
		}

		for (int r = 0; r < 4; r++) {
			// r번째 점수 복사
			int[] sortedScore = Arrays.copyOf(score[r], N);

			Arrays.sort(sortedScore); // 복사한 점수 오름차순으로 정렬

			for (int c = 0; c < N; c++) {
				int targetScore = score[r][c]; // 등수를 찾으려는 점수
				int lft = 0;
				int rgt = N - 1;
				int rank = -1; // 등수

				// 이분탐색
				while (lft <= rgt) {
					int mid = (lft + rgt) / 2;

					// target 점수 발견하면 오른쪽에서 다시 찾기
					// 가장 높은 등수를 찾기 위해서
					if (sortedScore[mid] == targetScore) {
						rank = N - mid;
						lft = mid + 1;
					} else if (sortedScore[mid] < targetScore) {
						lft = mid + 1;
					} else {
						rgt = mid - 1;
					}
				}
				bw.write(rank + " ");
			}
			bw.write("\n");
		}

		bw.flush();
	}
}
