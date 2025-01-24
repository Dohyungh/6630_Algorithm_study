package BOJ._14928_큰수;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[] num = sc.next().split("");
		
		int temp = Integer.parseInt(num[0]);
		
		for (int i = 0; i < num.length - 1; i++) {
			temp = (temp * 10 + Integer.parseInt(num[i+1])) % 20000303;
			
		}
		System.out.println(temp % 20000303);
	}

}
