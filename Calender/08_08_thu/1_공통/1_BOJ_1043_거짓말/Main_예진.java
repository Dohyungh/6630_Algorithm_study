/*
 * [풀이 방법]
 * 진실을 아는 사람을 기준으로 큐에 거짓말 불가능한 파티 번호를 추가해간다.
 * 거짓말 불가능한 파티를 하나씩 뽑아 그곳에 참석한 사람들이 참석하는 
 * 또 다른 파티번호를 큐에 추가하며 반복한다.
 * 큐에 파티를 추가할때마다 거짓말할 수 있는 파티 수는 전체 파티수에서 하나씩 감소한다.
 */
package boj_1043_거짓말;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);			// 사람 수
		int M = Integer.parseInt(input[1]);			// 파티 수
		
		List<Integer>[] person = new List[N+1];		// 각 사람이 참여하는 파티
		for (int i = 0; i <= N; i++) {
			person[i] = new ArrayList<>();
		}
		
		int[][] party = new int[M+1][];				// 각 파티에 참석하는 사람
		boolean[] know = new boolean[N+1];			// 진실을 아는 사람
		boolean[] disable = new boolean[M+1];		// 거짓말 할 수 없는 파티
		
		// 일단 파티 수만큼 초기화
		int answer = M;
		
		// 진실을 아는 사람 체크
		input = br.readLine().split(" ");
		int k = Integer.parseInt(input[0]);
		for (int i = 1; i <= k; i++) {
			int p = Integer.parseInt(input[i]);
			know[p] = true;
		}
		
		// 모든 파티 명단 확인 및 각 사람의 참석 파티 리스트 초기화
		for (int i = 1; i <= M; i++) {
			
			input = br.readLine().split(" ");
			int cnt = Integer.parseInt(input[0]);
			party[i] = new int[cnt];
			
			for (int j = 0; j < cnt; j++) {
				int p = Integer.parseInt(input[j+1]);
				party[i][j] = p;
				person[p].add(i);
			}
		}
		
		// 거짓말 불가 처리할 파티 넣기
		Queue<Integer> queue = new LinkedList<>();
		
		// 모든 사람 중
		for (int i = 1; i <= N; i++) {
			// 진실을 아는 사람의
			if (know[i]) {
				// 참석 파티 리스트를 순회
				for (int pot : person[i]) {
					// 이미 거짓말 불가이면 패스
					if (disable[pot]) continue;
					// 거짓말 불가 처리 및 큐에 추가, answer -1
					disable[pot] = true;
					queue.add(pot);
					answer--;
				}
				
			}
		}
		
		// 큐에 넣어진 초기 데이터로 순회
		while (!queue.isEmpty()) {
			
			// 진실을 말할 파티를 하나 뽑아서
			int pot = queue.poll();
			
			// 참석자를 순회
			for (int p : party[pot]) {
				// 이미 진실을 알고있다면 패스
				if (know[p]) continue;
				// 그 사람이 참석하는 파티 순회
				for (int g : person[p]) {
					// 이미 진실을 말한 파티면 패스
					if (disable[g]) continue;
					
					// 아니라면 큐에 추가, 거짓말 불가처리, answer-1
					queue.add(g);
					disable[g] = true;
					answer--;
				}
			}
		}
		
		System.out.println(answer);
		br.close();
	}

}
