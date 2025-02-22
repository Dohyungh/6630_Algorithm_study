package boj_5052_전화번호목록;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());

			String[] phoneNumbers = new String[n];

			for (int i = 0; i < n; i++) {
				phoneNumbers[i] = br.readLine();
			}

			Arrays.sort(phoneNumbers); // 사전순 정렬

			boolean consistence = true;

			for (int i = 1; i < n; i++) {
				if (phoneNumbers[i].startsWith(phoneNumbers[i - 1])) {
					consistence = false;
				}
			}

			bw.write(consistence ? "YES\n" : "NO\n");
		}
		
		bw.flush();
	}
}
