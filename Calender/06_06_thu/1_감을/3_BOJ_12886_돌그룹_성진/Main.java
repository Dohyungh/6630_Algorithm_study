package boj_12886_돌그룹;

import java.util.Scanner;

public class Main {

	static int total;
	static boolean[][] visited;
	static boolean flag;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 초기 돌의 개수 입력받기
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();

		// 전체 돌의 개수의 합을 계산해 total에 저장
		total = A + B + C;

		// 강호가 돌을 같은 개수로 만들었는지 판단하는 변수 flag
		// true라면 돌을 같은 개수로 만들었음을 의미
		flag = false;

		// 이미 계산한 그룹인지를 방문 체크할 변수 선언
		// a, b 조합을 이미 방문했다면 해당 조합은 탐색하지 않아도 됨
		visited = new boolean[1501][1501];

		// 같은 개수의 돌로 쪼갤 수 있는 경우(돌 개수의 합이 3의 배수)에만 탐색 실시
		if (total % 3 == 0) {
			grouping(A, B);
			grouping(B, C);
			grouping(A, C);
		}

		// 강호가 돌을 같은 개수로 만들었다면 1을 출력
		if (flag) {
			System.out.println(1);
		}
		// 아니라면 0을 출력
		else {
			System.out.println(0);
		}

	}

	// 다른 그룹의 돌 2개의 개수를 각각 인자로 받아 flag를 바꿔주는 grouping 메소드
	// 모든 그룹의 돌 개수를 동일하게 만들었다면 flag를 true로 바꾸고 탐색을 종료
	static void grouping(int a, int b) {
		// 1. a, b가 같다면
		if (a == b) {
			// 1-1. 모든 그룹의 돌 개수를 동일하게 만들었는지를 먼저 체크
			// 이 때 a가 (전체 개수/3)개라면 a, b, c 값이 모두 같을 것이므로 flag를 true로 바꾸고, 종료
			if (a == total / 3) {
				flag = true;
				return;
			}
			// 1-2. 모든 그룹의 돌 개수가 동일하지 않은 경우
			else {
				// 방문 체크
				visited[a][b] = true;
				// 같지 않은 두 개의 돌 그룹을 다시 grouping해야 함
				grouping(a, total - a - b);
			}
		}

		// flag가 이미 true라면 탐색 진행 X
		// 이미 방문한 조합이라면 탐색 진행 X
		if (flag || visited[a][b] || visited[b][a]) {
			return;
		}

		else {
			// 아직 방문하지 않았다면
			if (!visited[a][b] || !visited[b][a]) {
				// 방문 체크
				visited[a][b] = true;
				visited[b][a] = true;

				// min, max 값을 찾고
				int min = Math.min(a, b);
				int max = Math.max(a, b);

				// 돌 그룹을 갱신 (X+X, Y-X)
				int nA = min + min;
				int nB = max - min;
				int nC = total - nA - nB;

				// 다시 세 개의 조합으로 grouping 메소드 실행
				grouping(nA, nB);
				grouping(nA, nC);
				grouping(nB, nC);
			}
		}
	}

}
