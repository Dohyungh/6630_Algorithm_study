package SWEA._1984_중간평균값구하기;

import java.util.Scanner;

public class Solution1 {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int T = sc.nextInt();
		

				
		for (int tc =1; tc<=T; tc++) {
			int min = 10001;
			int max = -1;
			int sum = 0;
			
			
//			int[] li = new int[10];
			
			for (int i =0;i<10;i++) {
				int num = sc.nextInt();
				sum += num;
				if (num<min) {
					min = num;
				} else if (num>max) {
					max = num;
				}	
			}
			
//			System.out.printf("%d, %d, %d%n", min, max, sum);
			
			double ans = ((sum -min -max) / 8.0); 
			System.out.printf("#%d %.0f%n",tc,ans);
			
			
		}

	}

}
// 첫번째 값이 Min or Max일 경우 문제
// 1. 안에 있는 값으로 해야겠다.
// 2. else 빼야겠다.