package BOJ._13414_수강신청;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
		
		for (int i = 0; i < L; i++) {
			tm.put(br.readLine(), i);
		}

		TreeMap<Integer, String> reverseTm = new TreeMap<Integer, String>();
		
		while(!tm.isEmpty()) {
			Entry<String, Integer> entry = tm.pollFirstEntry();
			reverseTm.put(entry.getValue(), entry.getKey());
		}
		
		for (int i = 0; i < K; i++) {
			if (reverseTm.isEmpty()) break;
			sb.append(reverseTm.pollFirstEntry().getValue());
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}
