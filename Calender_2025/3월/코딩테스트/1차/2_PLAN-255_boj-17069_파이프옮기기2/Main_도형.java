package BOJ._17069_파이프옮기기2;

// 실패..
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class State {
		int[] left;
		int[] right;
		
		State(int[] left, int[] right) {
			this.left = left;
			this.right = right;
		}
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		boolean[][] isBlank = new boolean[N+1][N+1];
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				isBlank[i][j] = sc.nextInt() == 0 ? true : false; 
			}
		}
		
		int[] left = {1,1};
		int[] right = {1,2};
		
		State init = new State(left, right);
		
		Queue<State> q = new LinkedList<State>();
		
		q.add(init);
		
		int answer = 0;
		while(!q.isEmpty()) {
			
			State curr = q.poll();
			
			
			
			int[] l = curr.left;
			int[] r = curr.right;
			
			int lr = l[0];
			int lc = l[1];
			int rr = r[0];
			int rc = r[1];
			if (rr == N && rc == N) {
				answer++;
				continue;
			}
			
			// 가로
			if (lr == rr) {
				if (rc + 1 < N+1 && isBlank[rr][rc+1]) {
					q.add(new State(r, new int[] {rr, rc+1}));
				}
				if (rc + 1 < N+1 && rr + 1 < N+1 && isBlank[rr+1][rc+1] && isBlank[rr][rc+1] && isBlank[rr+1][rc]) {
					q.add(new State(r, new int[] {rr+1, rc+1}));
				}
				
			}
			
			if (lc == rc) {
				if (rr + 1 < N+1 && isBlank[rr+1][rc]) {
					q.add(new State(r, new int[] {rr+1, rc}));
				}
				if (rc + 1 < N+1 && rr + 1 < N+1 && isBlank[rr+1][rc+1] && isBlank[rr][rc+1] && isBlank[rr+1][rc]) {
					q.add(new State(r, new int[] {rr+1,rc+1}));
				}
			}
			
			if(lr + 1 == rr && lc + 1 == rc) {
				if (rc + 1 < N+1 && isBlank[rr][rc+1]) {
					q.add(new State(r, new int[] {rr, rc+1}));
				}
				if (rr + 1 < N+1 && isBlank[rr+1][rc]) {
					q.add(new State(r, new int[] {rr+1, rc}));
				}
				if (rr + 1 < N+1 && rc + 1 < N+1 && isBlank[rr][rc+1] && isBlank[rr+1][rc] && isBlank[rr+1][rc+1]) {
					q.add(new State(r, new int[] {rr+1, rc+1}));
				}
			}
			
		}
		
		System.out.println(answer);
		
	}

}
