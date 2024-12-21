package boj_1620_나는야포켓몬마스터이다솜;

import java.io.*;
import java.util.*;

public class Main2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashMap<String, String> map = new HashMap<String, String>();

		for (int i = 1; i <= N; i++) {
			String name = br.readLine();
			String num = String.valueOf(i);
			
			// map 하나에 key와 value 양방향으로 매핑 
			map.put(name, num);
			map.put(num, name);
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < M; i++) {
			sb.append(map.get(br.readLine())).append("\n");
		}
		
		System.out.println(sb);
	}
}
