/*
 * 현재 화면의 이모티콘 개수, 지금까지의 연산 횟수, 클립보드 상태를 담은 객체로 BFS
 * visited[현재 화면의 이모티콘 개수][클립보드 상태]로 방문체크를 한다. 
 * BFS이기 때문에 연산수가 적은 상태부터 순회하게 되므로, 방문체크를 하기에 적절한 조건이다.
 * 단, 화면의 이모티콘 개수는 목표한 개수를 넘어설 수 있으므로 방문체크 범위를 넉넉히 잡았다.
 */
package boj_14226_이모티콘;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int S = sc.nextInt();
		boolean[][] visited = new boolean[1500][1001];
		
		Queue<Status> queue = new ArrayDeque<>();
		// 초기 상태 queue 추가 및 방문체크
		queue.add(new Status(1, 0, 0));
		visited[1][0] = true;

		// 답안 초기화
		int answer = 0;
		
		out: while (!queue.isEmpty()) {
			// 같은 연산 횟수를 가진 상태의 개수
			int repeat = queue.size();
			
			// 만큼 반복
			for (int i = 0; i < repeat; i++) {
				Status s = queue.poll();
				
				// 목표한 숫자를 찾으면 연산 횟수를 답안에 저장한 후 종료
				if (s.num == S) {
					answer = s.calCnt;
					break out;
				}
				
				// 복사
				// 화면의 이모티콘 개수를 클립보드에 저장한 방문 확인 및 추가
				if (s.num < 1001 && !visited[s.num][s.num]) {
					queue.add(new Status(s.num, s.calCnt + 1, s.num));
					visited[s.num][s.num] = true;
				}
				
				// 붙여넣기
				// 클립보드가 비어있지 않고, 현재 화면의 이모티콘에 클립보드 이모티콘을 더한 값이 범위를 넘지 않으면
				// 해당 상태 방문 확인 및 추가
				if (s.clipboard != 0 && s.num + s.clipboard < 1500
						&& !visited[s.num + s.clipboard][s.clipboard]) {
					queue.add(new Status(s.num + s.clipboard, s.calCnt + 1, s.clipboard));
					visited[s.num + s.clipboard][s.clipboard] = true;
				}
				
				// -1
				// 현재 이모티콘 개수에서 1을 뺀 개수가 0보다 크면 해당 상태 방문 확인 및 추가 
				if (s.num - 1 > 0 &&!visited[s.num - 1][s.clipboard]) {
					queue.add(new Status(s.num - 1, s.calCnt + 1, s.clipboard));
					visited[s.num - 1][s.clipboard] = true;
				}
			}
		}
		
		System.out.println(answer);
		sc.close();
	}
}

class Status {
	int num;
	int calCnt;
	int clipboard;
	
	Status (int num, int calCnt, int clipboard) {
		this.num = num;
		this.calCnt = calCnt;
		this.clipboard = clipboard;
	}
}
