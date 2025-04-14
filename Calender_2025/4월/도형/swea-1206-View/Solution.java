package SWEA._1206_View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {		
			int answer = 0;
			
			int N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int center = 2;
			
			while(center <= N-3) {
				
				PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder() );
				
				for (int i = center - 2; i <= center +2; i++) {
					pq.add(arr[i]);
				}
				
				
				if (pq.peek() != arr[center++]) continue;
				answer += pq.poll() - pq.poll();
			}
			
			System.out.printf("#%d %d\n", tc, answer);
		}
		
		br.close();
		
	}

}
