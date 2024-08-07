package boj_1846_장기;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();

		// N이 3보다 작은 경우는 배치할 수 없으므로 -1 출력
		if (N <= 3) {
			System.out.println(-1);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// N이 짝수일 경우
		if (N % 2 == 0) {
			for (int i = 0; i < N; i++) {
				if (i < N / 2 - 1)
					sb.append(i + 2).append("\n"); // (i+1) 번째 인덱스에 가능 -> 정답 인덱스는 해당값에 +1을
				else if (i == N / 2 - 1)
					sb.append(1).append("\n");
				else if (i == N / 2)
					sb.append(N).append("\n");
				else
					sb.append(i).append("\n");; // (i-1)번째 인덱스에 가능 -> 정답 인덱스는 해당값에 +1
			}
		}
		// N이 홀수일 경우
		else {
			sb.append(N / 2 + 1).append("\n");
			for (int i = 1; i < N; i++) {
				if (i == N / 2 + 1)
					sb.append(N).append("\n");
				else
					sb.append(i).append("\n");;
			}
		}
		
		bw.write(sb.toString());
		bw.flush();

		scanner.close();
	}
}