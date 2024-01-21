package SWEA._1984_중간평균값구하기;

import java.util.Scanner;

public class Solution_fin2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		

				
		for (int tc = 1; tc<=T; tc++) {
			int min = 10001;
			int max = -1;
			int sum = 0;
			
			for (int i = 0;i<10;i++) {
				int num = sc.nextInt();
				sum += num;
				if (num<min) {
					min = num;
				} 
				if (num>max) {
					max = num;
				}	
			}
				
			double ans = ((sum -min -max) / 8.0); 
			System.out.printf("#%d %.0f%n",tc,ans);
			
			
		}

	}

}
