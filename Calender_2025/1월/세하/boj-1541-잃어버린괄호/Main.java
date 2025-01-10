package boj_1541_잃어버린괄호;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] op = br.readLine().split("-");
		String[] tmp;

		int ans = 0;

		for (int i = 0; i < op.length; i++) {
			tmp = op[i].split("\\+");
			int sum = 0;

			for (int j = 0; j < tmp.length; j++) {
				int num = Integer.parseInt(tmp[j]);
				sum += num;
			}

			if (i == 0) {
				ans += sum;
			} else {
				ans -= sum;
			}
		}

		System.out.println(ans);
	}
}
