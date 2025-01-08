package boj_7662_이중우선순위큐;

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
			int K = Integer.parseInt(br.readLine());
			int k = 0;

			TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

			while (k++ < K) {
				st = new StringTokenizer(br.readLine());

				char op = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());

				if (op == 'I') {
					if (map.containsKey(num)) {
						map.replace(num, map.get(num) + 1);
					} else {
						map.put(num, 1);
					}
				} else {
					if (!map.isEmpty()) {
						int key = num == -1 ? map.firstKey() : map.lastKey();
						int value = map.get(key); // map에 저장된 key 값의 개수
						
						// map에 key 값이 1개만 저장되어 있으면 제거
						if (value == 1) {
							map.remove(key);
						}
						// 그렇지 않으면 개수 1 감소
						else {
							map.replace(key, value - 1);
						}
					}
				}
			}

			// Queue가 비었을 경우 EMPTY 출력
			if (map.isEmpty()) {
				sb.append("EMPTY\n");
			} else {
				sb.append(map.lastKey() + " " + map.firstKey() + "\n");
			}
		}

		System.out.println(sb);
	}
}
