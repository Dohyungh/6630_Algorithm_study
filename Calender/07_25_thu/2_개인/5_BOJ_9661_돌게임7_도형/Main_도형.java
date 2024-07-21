package BOJ._9661_돌게임7;

import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		
		long answer = N % 5L;
		
		if (answer == 0 || answer ==2) System.out.println("CY");
		else System.out.println("SK");
		
		

		
	}

}
