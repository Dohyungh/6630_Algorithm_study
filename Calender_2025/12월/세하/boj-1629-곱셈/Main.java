package boj_1629_곱셈;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());

		// 결과 계산 및 출력
		long result = power(A, B, C);

		System.out.println(result);

	}

	public static long power(long A, long B, long C) {
		if (B == 1) {
			return A % C;
		}

		long tmp = power(A, B / 2, C) % C;

		if (B % 2 == 0) {
			return tmp * tmp % C;
		} else {
			return tmp * tmp % C * A % C;
		}
	}
}
