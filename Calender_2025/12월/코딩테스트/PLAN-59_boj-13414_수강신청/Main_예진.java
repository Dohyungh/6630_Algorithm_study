package 코테_boj_13414_수강신청;

/**
 * Author: yngbao97, Yuk Yejin
 * Date: 2024.12.26
 */

import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new InputStreamReader(new FileInputStream("src/"+Main.class.getPackage().getName()+"/input.txt")));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		int k = Integer.parseInt(input[0]);
		int l = Integer.parseInt(input[1]);
		
		Queue<String> queue = new ArrayDeque<>();
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < l; i++) {
			String num = br.readLine();
			queue.offer(num);
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		StringBuilder sb = new StringBuilder();
		int completeCnt = 0;
		while (!queue.isEmpty() && completeCnt < k) {
			String num = queue.poll();
			int cnt = map.get(num);
			if (cnt == 1) {
				sb.append(num + "\n");
				completeCnt++;
			} else {
				map.put(num, cnt - 1);
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}
}
