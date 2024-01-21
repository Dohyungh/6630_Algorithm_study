package SWEA._1984_중간평균값구하기;

import java.util.Scanner;

public class Solution2 {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int T = sc.nextInt();
		

				
		for (int tc =1; tc<=T; tc++) {

			int sum = 0;

			for (int i =0;i<10;i++) {

				int num = sc.nextInt();
				int min = num;
				int max = num;
				
				sum += num;
				if (num<min) {
					min = num;
				} else if (num>max) {
					max = num;
				}
			}
		
			double ans = ((sum -min -max) / 8.0d);
			System.out.printf("#%d %.0f%n",tc,ans);
			
			
		}

	}

}
//1.
// for 문 안에서 선언하면 못 갖고 나감
// for 문 밖에서 선언 후, for 문 안에서 대입, 다시 for문 밖에서 결과값을 이용해야함.
// 그래서 cannot be resolved to a variable 에러가 뜸
//2.
// min max 가 계속 대입 됨
