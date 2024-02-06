package 설탕배달_2839_240205;

import java.util.Scanner;

public class Main2 {
	static int N;
	static int answer;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		answer = Integer.MAX_VALUE;
		
		dfs(0, 0);
		
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else System.out.println(answer);
		
		sc.close();
	}
	
	public static void dfs(int five, int three) {
		if (N == (five*5)+(three*3)) {
			answer = Math.min(answer, five+three);
			return;
		} else if (N < (five*5)+(three*3)) return;
		
		dfs(five + 1, three);
		dfs(five, three+1);
	}

}
