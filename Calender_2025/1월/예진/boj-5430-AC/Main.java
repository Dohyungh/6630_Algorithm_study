/**
 * Author: yngbao97, Yuk Yejin
 * Problem: AC_5430
 * Date: 2025.01.30
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {

            char[] order = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            Deque<String> queue = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine(), "[],");
            for (int i = 0; i < n; i++) queue.addLast(st.nextToken());

            int dec = 0;
            boolean error = false;
            for (char c : order) {
                if (c == 'R') {
                    dec = 1 - dec;
                } else if (queue.isEmpty()) {
                    error = true;
                    break;
                }
                else if (dec == 1) queue.pollLast();
                else queue.pollFirst();
            }

            if (error) bw.write("error\n");
            else {
                List<String> strs = new ArrayList<>();
                if (dec == 1) while(!queue.isEmpty()) strs.add(queue.pollLast());
                else while(!queue.isEmpty()) strs.add(queue.pollFirst());
                bw.write("[" + String.join(",", strs) + "]\n");
            }
        }

		bw.flush();
		bw.close();
		br.close();
	}
}