package prgs_외벽점검;

import java.util.*;

public class Solution {
	public int solution(int n, int[] weak, int[] dist) {
		
		
		int[] edges = new int[weak.length-1];
		
		
		
		
		for (int i=0; i<edges.length; i++) {
			if (i == edges.length-1) {
				edges[i] = (n + weak[0]) - weak[i];
			}
			else {
				edges[i] = weak[i+1] - weak[i];
			}
		}
		
		int answer = -1;
		
		boolean[] check = new boolean[weak.length];
		
		for (int d=dist.length-1; d>=0; d--) {
			
			int cnt = 0;
			int maxCnt = Integer.MIN_VALUE;
			int nw = -1;
			
			for (int w=0; w<weak.length; w++) {
				goClockwise(n, w, weak, d, check);
				for (int c=0; c<check.length; c++) {
					if (check[c]==true) {
						check[c] = false;
						cnt++;
					}
				}
				if (cnt > maxCnt) {
					maxCnt = cnt;
					nw = w;
				}
				cnt = 0;
				goCounterClockwise(n, w, weak, d, check);
				for (int c=0; c<check.length; c++) {
					if (check[c]==true) {
						check[c] = false;
						cnt++;
					}
				}
				if (cnt > maxCnt) {
					maxCnt = cnt;
					nw = w;
				}
				
			}
			
			for (int c=nw; c<nw+maxCnt; c++) {
				check[c] = true;
				
			}
			
			
			
			
		}
		
		
		
		
		
		return answer;
	}
	
	public static void goClockwise(int n, int w, int[] weak, int d, boolean[] check) {
		
		int distSum = 0;
		
		while (distSum <= d) {
			check[w] = true;
			if (w == weak.length - 1) {
				distSum += (n + weak[0]) - weak[w];
			}
			else {
				distSum += weak[w+1] - weak[w];
			}
			w++;
		}
		
	}
	
	public static void goCounterClockwise(int n, int w, int[] weak, int d, boolean[] check) {
		int distSum = 0;
		
		while (distSum <= d) {
			check[w] = true;
			if (w == 0) {
				distSum += (n + weak[0]) - weak[weak.length-1];
			}
			else {
				distSum += weak[w] - weak[w-1];
			}
			w++;
		}
	}

}
