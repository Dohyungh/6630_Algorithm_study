package boj_13414_수강신청;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		TreeMap<Integer, String> treeMap = new TreeMap<>();
		HashMap<String, Integer> map = new HashMap<>();

		for (int i = 0; i < L; i++) {
			String student = br.readLine();
			if (map.containsKey(student)) {
				treeMap.remove(map.get(student));
			}
			map.put(student, i);
			treeMap.put(i, student);
		}

		StringBuilder sb = new StringBuilder();

		int count = 0;
		for (String student : treeMap.values()) {
			if (count >= K)
				break;
			sb.append(student).append("\n");
			count++;
		}

		System.out.print(sb);
	}
}
