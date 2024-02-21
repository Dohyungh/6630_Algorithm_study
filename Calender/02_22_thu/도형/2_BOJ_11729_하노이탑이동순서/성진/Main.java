package boj_11729_하노이탑이동순서;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();

//		int answer = 1;
//		for (int i=1; i<N; i++) {
//			answer = 2 * answer + 1;
//		}
//		
//		System.out.println(answer);
		System.out.println((int)Math.pow(2, N) - 1);
		
//		System.out.println(findHanoi(N));
		
		printMove(N, 1, 3);
		
		sc.close();
		bw.close();
		
	}
	
	public static int findHanoi(int n) {
		if (n ==1) {
			return 1;
		}
		return 2*findHanoi(n-1)+1;
	}
	
	
	public static void printMove(int n, int x, int y) throws IOException{
		if (n==1) {
//			System.out.printf("%d %d\n", x, y);
			bw.write(x + " " + y + "\n");
			return;
		}
		else {
			printMove(n-1, x, 6-(x+y));
			printMove(1, x, y);
			printMove(n-1, 6-(x+y), y);
		}
	}
}
