package boj_16953_A_B;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int ans = -1;
		int num = 0;

		while (A < B) {
			// 일의 자리가 홀수일 때
			if (B % 2 == 1) {
				// 일의 자리가 1이면 1 제거 후 오른쪽으로 이동
				if (B % 10 == 1) {
					B /= 10;
					num++;
				} else { // 그 외의 경우에는 2로 나누어지지 않기 때문에 실패
					break;
				}
			} else { // 일의 자리가 짝수일 때는 2로 나누기
				B /= 2;
				num++;
			}

			// A와 B가 같아지면 성공
			if (A == B) {
				ans = num + 1;
			}
		}

		System.out.println(ans);
	}
}
