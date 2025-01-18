package boj_6064_카잉달력;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		int t = 0;

		while (t++ < T) {
			st = new StringTokenizer(br.readLine());

			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int mul = M * N;
			int a = 0;
			int b = 0;

			int num1 = M * a + x;
			int num2 = N * b + y;
			int ans = -1;
			
			// num1과 num2가 두 수의 곱보다 작을 동안만 연산 수행 
			while (num1 <= mul && num2 <= mul) {
				// num1 = num2 일 때 표현되는 해가 존재 
				if (num1 == num2) {
					ans = num1;
					break;
				} else if (num1 < num2) { // num2가 더 클 때 a + 1 
					a++;
				} else { // num1가 더 클 때 b + 1
					b++;
				}

				num1 = M * a + x;
				num2 = N * b + y;
			}
			sb.append(ans).append("\n");
		}
		System.out.print(sb);
	}
}
