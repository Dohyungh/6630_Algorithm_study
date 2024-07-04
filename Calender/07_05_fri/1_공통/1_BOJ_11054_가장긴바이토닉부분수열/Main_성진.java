package boj_11054_가장긴바이토닉부분수열;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[] seq = new int[N];

		for (int i = 0; i < N; i++) {
			seq[i] = sc.nextInt();
		}

		int[] incSeqLen = new int[N];

		incSeqLen[0] = 1;

		for (int i = 1; i < N; i++) {
			int temp = 1;
			for (int j = 1; j <= i; j++) {
				if (seq[i] - seq[i - j] > 0) {
					temp = Math.max(temp, incSeqLen[i - j] + 1);
				}
			}
			incSeqLen[i] = temp;
		}

		int[] decSeqLen = new int[N];

		decSeqLen[N - 1] = 1;

		for (int i = N - 2; i >= 0; i--) {
			int temp = 1;
			for (int j = 1; j < N - i; j++) {
				if (seq[i] - seq[i + j] > 0) {
					temp = Math.max(temp, decSeqLen[i + j] + 1);
				}
			}
			decSeqLen[i] = temp;
		}

		int answer = 0;

		for (int i = 0; i < N; i++) {
			answer = Math.max(answer, incSeqLen[i] + decSeqLen[i] - 1);
		}

		System.out.println(answer);
	}

}
