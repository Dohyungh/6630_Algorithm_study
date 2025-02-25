/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 가운데를 말해요_1655
 * Date: 2025.02.25
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

		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> big = new PriorityQueue<>();

		small.add(Integer.parseInt(br.readLine()));
		bw.write(String.valueOf(small.peek()) + "\n");

		for (int i = 0; i < n-1; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num <= small.peek()) small.add(num);
			else big.add(num);

			int gap = small.size() - big.size();
			if (gap > 1) big.add(small.poll());
			else if (gap < 0) small.add(big.poll());

			bw.write(String.valueOf(small.peek()) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}