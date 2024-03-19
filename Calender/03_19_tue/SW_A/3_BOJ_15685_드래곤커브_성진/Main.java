package boj_15685_드래곤커브;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	// 시작점부터 드래곤 커브를 다 그릴 때까지 방향값(d)을 담을 리스트
	static List<Integer> list;
	// 드래곤 커브가 위치한 꼭짓점을 true로 표현할 101 x 101의 false 맵 선언
	static boolean[][] map;

	// 우상좌하 순서대로
	// 문제의 방향값(d)와 델타 배열의 인덱스를 맞춰 줌
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		map = new boolean[101][101];

		// 드래곤 커브의 개수
		int N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			// x, y, d, g를 순서대로 입력받음
			// x가 col, y가 row임을 주의
			int c = sc.nextInt();
			int r = sc.nextInt();
			int d = sc.nextInt();
			int g = sc.nextInt();

			// 각 드래곤 커브의 방향 정보를 담을 list 초기화
			list = new ArrayList<Integer>();
			// 초기 위치의 방향값 d, 드래곤 커브의 세대 g를 파라미터로 입력해 방향을 계산
			calcDirection(d, g);

			// 계산된 방향(list에 담김)에 따라 움직임을 수행
			doMove(r, c);

		}
		// calcAnswer() 메소드로 답을 계산해 출력
		System.out.println(calcAnswer());

		sc.close();
	}

	// 초기 방향값과 세대 수를 입력받아 g세대 드래곤 커브를 그리기 위한 방향 정보를 list에 저장
	static void calcDirection(int dir, int gen) {
		// 초기 방향 더해주기
		list.add(dir);

		// 세대가 0보다 클 때 동안 반복
		while (gen > 0) {
			// gen을 1씩 감소
			gen--;
			// 현재의 list 사이즈를 저장해두고
			int s = list.size();
			// 중요!!
			// list의 마지막 원소부터 하나씩 접근해 방향값을 1씩 증가(mod 4)
			for (int i = s - 1; i >= 0; i--) {
				int d = list.get(i);
				d = (d + 1) % 4;
				list.add(d);

			}

		}

	}

	// 움직임을 수행하기 위한 메소드 doMove, 초기 위치 r, c를 입력받음
	static void doMove(int r, int c) {

		int nr = r;
		int nc = c;
		// 드래곤 커브가 지나가는 꼭짓점을 true로 표현
		map[nr][nc] = true;

		// list의 원소들을 돌면서 순서대로 경로(꼭짓점)를 표현
		for (int d = 0; d < list.size(); d++) {
			nr += dr[list.get(d)];
			nc += dc[list.get(d)];
			map[nr][nc] = true;

		}

	}

	// 전체 정답을 출력하기 위한 메소드, 꼭짓점 4개가 모두 true인 1 x 1 정사각형을 찾자
	static int calcAnswer() {
		int answer = 0;
		for (int i = 0; i < map.length - 1; i++) {
			for (int j = 0; j < map[i].length - 1; j++) {
				if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
					answer++;
				}
			}
		}

		return answer;
	}

}
