/*
 *  [1트 시간초과]: 예상했음. 돌릴때부터 답이 늦게 나옴.
 *  어디서 시간을 줄일 수 있을까 고민이 필요함..
 */

package boj_17281_야구공;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_TimeOut {
	
	static int inning;
	static int answer;
	static int[][] runner;
	static int[] order;
	static boolean[] visited;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		inning = sc.nextInt();
		runner = new int[inning][9];	// 선수번호때문에 패딩하려다가, %연산 고려해서 포기
		answer = 0;
		
		for (int i = 0; i < inning; i++) {
			for (int r = 0; r < 9; r++) {
				runner[i][r] = sc.nextInt();
			}
		}
		
		// 선수번호, 타자번호 -1씩 당겨서 생각
		order = new int[9];				// 타자 순서, 선수번호로 표시
		visited = new boolean[9];		// 선수의 순서확인 여부
		order[3] = 0;					// 3번 타자, 0번 선수로 정함
		visited[0] = true;				// 0번 선수 순서 정해짐
		perm(0);

		System.out.println(answer);
		sc.close();
	}
	
	private static void perm(int cnt) {
		if (cnt >= 9) {

			answer = Math.max(answer, play());
			return;
		}
		
		// 3번 타자는 정했으니까 패스
		if (cnt == 3) perm(cnt+1);
		
		else {
			//모든 선수를 돌면서(0번선수는 정했으니까 제외)
			for (int i = 1; i < 9; i++) {
				if (!visited[i]) {
					order[cnt] = i;
					visited[i] = true;
					perm(cnt+1);
					visited[i] = false;
				}
			}
		}
	}

	private static int play() {
		int result = 0;
		int cnt = 0;	// 회차
		int number = 0;	// 타자순서번호
		
		while (cnt < inning) {
			Queue<Boolean> ground = new LinkedList<>();
			
			for (int i = 0; i < 3; i++) {
				ground.add(false);
			}
			
			int out = 0;
			
			while (out < 3) {
				// 이번 회차에 몇번째 타자의 예상 타점
				int score = runner[cnt][order[number++]];
				
				if (score == 0) out++;
				else {
					// 주자를 내보내고 하나씩 이동하고
					ground.add(true);
					if (ground.poll()) result++;
					
					// 이동만 한다
					for (int i = 1; i < score; i++) {
						ground.add(false);
						if (ground.poll()) result++;
					}
				}
				
				// 선수 순서 로테이션
				number %= 9;
			}
			
			// 아웃 3개 되면 회차 증가
			cnt++;
		}
		
		return result;
	}

}