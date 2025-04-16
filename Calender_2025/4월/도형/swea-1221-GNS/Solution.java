package SWEA._1221_GNS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		HashMap<String, Integer> toIntMap = new HashMap<String, Integer>();
		HashMap<Integer, String> toStringMap = new HashMap<Integer, String>();
		
		toIntMap.put("ZRO", 0);
		toStringMap.put(0, "ZRO");
		toIntMap.put("ONE", 1);
		toStringMap.put(1, "ONE");
		toIntMap.put("TWO", 2);
		toStringMap.put(2, "TWO");
		toIntMap.put("THR", 3);
		toStringMap.put(3, "THR");
		toIntMap.put("FOR", 4);
		toStringMap.put(4, "FOR");
		toIntMap.put("FIV", 5);
		toStringMap.put(5, "FIV");
		toIntMap.put("SIX", 6);
		toStringMap.put(6, "SIX");
		toIntMap.put("SVN", 7);
		toStringMap.put(7, "SVN");
		toIntMap.put("EGT", 8);
		toStringMap.put(8, "EGT");
		toIntMap.put("NIN", 9);
		toStringMap.put(9, "NIN");
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <=T; tc++) {
			
			bw.write(String.format("#%d\n", tc));
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			st.nextToken();
			
			st.nextToken();
			
			st = new StringTokenizer(br.readLine());
			
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			
			while(st.hasMoreTokens()) {
				pq.add(toIntMap.get(st.nextToken()));
			}
			
			while(!pq.isEmpty()) {
				bw.write(toStringMap.get(pq.poll()));
				bw.write(" ");
			}
			
			bw.write("\n");
			
		}
		
		
		
		bw.flush();
		br.close();
		bw.close();
	}

}
