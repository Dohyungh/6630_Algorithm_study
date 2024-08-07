package BOJ._1846_장기;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int middle = N /2;
		System.out.println(middle);
		for (int i = 1; i <= middle-1; i++) System.out.println(i);
		
		System.out.println(N);
		for (int i = middle+1; i <=N-1; i++) System.out.println(i);
		
		sc.close();
	}

}
                      