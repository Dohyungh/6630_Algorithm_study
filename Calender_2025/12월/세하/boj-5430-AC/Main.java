package boj_5430_AC;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			char[] p = br.readLine().toCharArray();
			int n = Integer.parseInt(br.readLine());

			String str = br.readLine();
			str = str.substring(1, str.length() - 1); // 중괄호 제거
			String[] strArr = str.split(","); // 배열로 변경

			boolean dir = true; // 방향 변수 (정방향이면 true)
			int lft = 0; // 왼쪽 포인터
			int rgt = n - 1; // 오른쪽 포인터
			boolean isError = false; // 에러인지 아닌지 판단

			for (int i = 0; i < p.length; i++) {
				if (p[i] == 'R') { // 'R'일 때 방향 변경
					dir = !dir;
				} else if (p[i] == 'D') { // 'D'일 때 숫자 제거
					// 배열이 비었을 경우 (즉, lft가 rgt보다 커지는 순간) error
					if (lft > rgt) {
						isError = true;
						break;
					}

					if (dir) { // 순방향일 땐 왼쪽에서 숫자 한 개 제거
						lft++;
					} else {
						rgt--; // 역방향일 땐 오른쪽에서 숫자 한 개 제거
					}
				}
			}

			// 에러일 때 error 출력
			if (isError) {
				sb.append("error\n");
			} else {
				sb.append("[");

				// 순방향일 때 왼쪽 포인터부터 차레대로 출력
				if (dir) {
					for (int i = lft; i <= rgt; i++) {
						sb.append(strArr[i]);

						// 마지막 숫자가 아니면 쉼표 삽입
						if (i != rgt) {
							sb.append(",");
						}
					}
				}
				// 역방향일 때 오른쪽 포인터부터 반대로 출력
				else {
					for (int i = rgt; i >= lft; i--) {
						sb.append(strArr[i]);

						// 마지막 숫자가 아니면 쉼표 삽입
						if (i != lft) {
							sb.append(",");
						}
					}
				}

				sb.append("]\n");
			}
		}

		System.out.println(sb);
	}
}
