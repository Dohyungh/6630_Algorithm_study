package SWEA._1266_소수완제품확률;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static double[][] comb = new double[19][];
	static int[] nonPrimes = new int[] {0,1,4,6,8,9,10,12,14,15,16,18};
	
	public static void main(String[] args) throws Exception {
		
		build();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			
			double answer = 0.0d;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			double A = Double.parseDouble(st.nextToken()) / 100;
			double B = Double.parseDouble(st.nextToken()) / 100;
			
			double[] probA = new double[19];
			double[] probB = new double[19];
			
			for (int i = 0; i < 19; i++) {
				probA[i] = comb[18][i] * Math.pow(A, i) * Math.pow((1.0 - A), 18 - i);
				probB[i] = comb[18][i] * Math.pow(B, i) * Math.pow((1.0 - B), 18 - i);
			}
			
			for (int i = 0; i < nonPrimes.length; i++) {
				for (int j = 0; j < nonPrimes.length; j++) {
					answer += probA[nonPrimes[i]] * probB[nonPrimes[j]];
				}
				
			}
			
			System.out.printf("#%d %f\n", tc, 1.0d - answer);
		}
	}
	
	static void build() {
		for (int i = 1; i < 19; i++) {
			comb[i] = new double[i+1];
			
			comb[i][0] = 1.0d;
			comb[i][1] = i;
			comb[i][i] = 1.0d;
			
			for (int j = 2; j <= i-1; j++) {
				comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
			}
			
		}
		
	}

}
