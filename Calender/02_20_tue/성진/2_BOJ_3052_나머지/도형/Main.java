package BOJ._3052_나머지;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// 뭐하자는건지
		
		Scanner sc = new Scanner(System.in);
		int[] 축배성진졸업 = new int[42];
		for (int i = 0; i<10; i++) {
			축배성진졸업[sc.nextInt()%42] = 1;
			
		}
		
		int sum = 0;
		for (int i = 0; i<42; i++) {
			sum +=축배성진졸업[i];
		}
		
		System.out.println(sum);
	}

}
