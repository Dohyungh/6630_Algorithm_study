package BOJ._11729_하노이탑이동순서;

import java.util.Scanner;

public class Main_fin {
	
	static int cnt = 0;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		calcHanoi(n,1,3);
		System.out.println(cnt);
		System.out.print(sb.toString());
		sc.close();

	}
	
	public static void calcHanoi(int n, int start, int end) {
		if (n == 1) {
			sb.append(start + " " + end+"\n");
			cnt++;
			return;
		}
		
		int middle = 6 - start - end;
		
		calcHanoi(n-1, start, middle);
		sb.append(start + " " + end + "\n");
		cnt++;
		calcHanoi(n-1, middle, end);
		
	}

}
