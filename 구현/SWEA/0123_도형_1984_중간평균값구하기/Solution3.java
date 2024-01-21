package SWEA._1984_중간평균값구하기;

import java.util.Scanner;

public class Solution3 {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int T = sc.nextInt();
		

				
		for (int tc =1; tc<=T; tc++) {
			int min;
			int max;
			int sum = 0;
			
			for (int i =0;i<10;i++) {
				int num = sc.nextInt();
				if (i==0) {
					min = num;
					max = num;
				}
//				} else {
//					min = num;
//					max = num;
//				}
				sum += num;
			
			if (num<min) {
				min = num;
			} else if (num>max) {
				max = num;
			}
//			} else {
//				min =0;
//				max =0;
			
			}
			double ans = ((sum -min -max) / 8.0d); 
			System.out.printf("#%d %.0f%n",tc,ans);

	}

	}
}

// if 문 내에서 초기화 하는 것도 밖에서 인식 못함.
// else로 무조건 초기화 되게 해주면 되긴 함.
// if, else if, else 는 또 안되는 듯.
// 