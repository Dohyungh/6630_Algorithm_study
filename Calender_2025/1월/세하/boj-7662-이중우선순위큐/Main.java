package boj_7662_이중우선순위큐;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int k = Integer.parseInt(br.readLine());

			TreeMap<Integer, Integer> queue = new TreeMap<>();

			while (k-- > 0) {
				st = new StringTokenizer(br.readLine());

				String command = st.nextToken();
				int n = Integer.parseInt(st.nextToken());

				if (command.equals("I")) {
					queue.put(n, queue.getOrDefault(n, 0) + 1);
				} else {
					if (!queue.isEmpty()) {
						int key = n == 1 ? queue.lastKey() : queue.firstKey();
						int count = queue.get(key);

						if (count == 1) {
							queue.remove(key);
						} else {
							queue.put(key, count - 1);
						}
					}
				}
			}

			if (queue.isEmpty()) {
				sb.append("EMPTY\n");
			} else {
				sb.append(queue.lastKey() + " " + queue.firstKey() + "\n");
			}
		}

		System.out.println(sb);
	}
}
