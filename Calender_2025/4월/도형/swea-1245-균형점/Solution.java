package SWEA._1245_균형점;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			
			
			double[] loc = new double[N];
			double[] mass = new double[N];
			
			for (int i = 0; i < N; i++) {
				loc[i] = Double.parseDouble(st.nextToken());
			}
			
			for (int i = 0; i < N; i++) {
				mass[i] = Double.parseDouble(st.nextToken());
			}
			
			System.out.printf("#%d ", tc);
			
			for (int i = 0; i < N-1; i++) {
				double left = loc[i];
				double right = loc[i+1];
				
				
				while(left < right) {
					
					double mid = (left + right) / 2;
					if ((right - left) <= Math.pow(10, -12) ) {
						System.out.printf("%.10f ", mid);
						break;
					}
					
					double leftPowers = 0.0d;
					double rightPowers = 0.0d;
					
					for (int l = 0; l <= i; l++) {
						leftPowers += getPower(mass[l], mid - loc[l]);
					}
					
					for (int r = i+1; r < N; r++) {
						rightPowers += getPower(mass[r], loc[r] - mid);
					}
					
					if (leftPowers < rightPowers) {
						right = mid;
						continue;
					}
					
					if (leftPowers >= rightPowers) {
						left = mid;
						continue;
					}
					
				}
				
			}
			System.out.println();
			
		}
		
		
		br.close();
		
	}
	
	public static double getPower(double m, double d) {
		return m/Math.pow(d, 2);
		
	}

}
