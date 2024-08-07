package BOJ._1043_거짓말;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 사람
		int N = sc.nextInt() + 1;
		
		// 파티 수
		int M = sc.nextInt();
		
		// 진실을 아는, 혹은 알아버린 사람들
		Queue<Integer> factPeople = new LinkedList<>();
		
		// 최초에 진실을 이미 알고 있는 사람들 큐에 추가
		int num = sc.nextInt();
		for (int i = 0; i < num; i++) {
			factPeople.add(sc.nextInt());
		}
		
		// 파티 행렬
		// 행이 파티 번호
		// 열에 그 사람이 파티에 오는지 안 오는지
		// 행으로 읽으면 그 파티에 온 모든 사람들을 알 수 있고,
		// 열로 읽으면 그 사람이 간 파티를 알 수 있음.
		boolean[][] party = new boolean[M][N];
		
		for (int i = 0; i< M; i++) {
			num = sc.nextInt();
			for (int j = 0; j <num; j++) {
				party[i][sc.nextInt()] = true;				
			}
		}
		
		// 방문 체크
		boolean[] visited = new boolean[N];
		
		// 진실 파티
		boolean[] truthParty = new boolean[M];
		
		// 진실을 아는 사람을 하나 뽑아서
		// 그 사람이 참가한 모든 파티에 대해 (열 검사)
		// 해당 파티에 참가한 사람들을 큐에 추가 (= 진실을 알아버린 사람들) (해당 행의 모든 사람들에 대해 열 검사 수행)
		// 반복
		while(!factPeople.isEmpty()) {
			// 하나 뽑아서
			int p = factPeople.poll();
			// 방문 체크
			if (visited[p]) continue;
			visited[p] = true;
			
			// 모든 파티에 대해
			for (int i = 0; i < M; i++) {
				// 이 진실을 아는 사람이 간 파티면
				if (party[i][p]) {
					// 이 파티는 진실 파티이고,
					truthParty[i] = true;
					
					// 해당 파티에 온 모든 사람에 대해 다시 진실을 아는 사람 큐에 추가해서 반복될 수 있게 함
					for (int j = 1; j < N; j++) {
						if (party[i][j]) {
							factPeople.add(j);
						}
					}
				}
			}
		}
		
		// 마지막으로 진실 파티가 아닌 파티의 개수를 세면 끝!
		int cnt = 0;
		for (int i =0; i<M; i++) {
			if (!truthParty[i]) cnt++;
		}
		
		System.out.println(cnt);
	}

}
