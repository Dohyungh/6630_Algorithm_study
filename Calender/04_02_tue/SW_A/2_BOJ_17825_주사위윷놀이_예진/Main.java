/*
 * 그래프를 구현하기 힘들었다. 
 * 노드로 구현한 사람들도 있었는데, 노드가 다양한 정보를 내포하고 있을 수는 있어도
 * 하드코딩은 다들 어쩔 수 없었다.
 * 각 이동 방향 별 노드들를 ArrayList로 만들어 List배열에 담았다.
 * 
 * 처음에 이동할 말의 순서를 순열로 구해놓고 시뮬을 돌리다가 너무 헛돈다고 생각했다.
 * 물론 답도 안나옴.. 답이 틀리는데 헛도는게 많아서 디버깅이 100000배 어려웠음
 * 
 * 시뮬을 돌리면서 순열을 사용했는데, 백트래킹이 확실히 잘 된다.
 * 다만 인덱스 싸움의 구현문제이다보니 중간에 정신 살짝만 놓으면 디버깅도 힘들고 나락으로 간다..
 * 자릿값에 따라 다음 턴의 시작위치를 설정해주는 방식이었는데,
 * 자릿값이 중복되는 노드들의 구분조건을 섬세하게 확인하지 못했다. 그래도 결국 성불!
 * 
 */
package boj_17825_주사위윷놀이;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int[] order;				// 10라운드에 걸쳐 나오는 주사위 수
	static int[][] marker;			// 각 말의 위치 정보
	static boolean[] goal;			// 각 말의 골인 여부
	static List<Integer>[] map;		// 게임 맵
	static boolean[][] visited;		// 맵에서 해당 위치에 다른 말이 있는지 여부
	static int answer;				// 답
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		order = new int[10];
		
		// 주사위 수 입력
		for (int i = 0; i < 10; i++) {
			order[i] = sc.nextInt();
		}
		
		// 게임판 입력... 바보같다. 규칙못찾아서 냅다 입력
		map = new ArrayList[5];
		map[0] = new ArrayList<>();
		for (int j = 2; j <= 40; j += 2) {
			map[0].add(j);
		}
		map[1] = new ArrayList<>(List.of(10, 13, 16, 19, 25));
		map[2] = new ArrayList<>(List.of(20, 22, 24, 25));
		map[3] = new ArrayList<>(List.of(30, 28, 27, 26, 25));
		map[4] = new ArrayList<>(List.of(25, 30, 35, 40));
		
		// 말 위치 초기화: 모든 말은 아직 맵에 들어가지 않았으므로 행인덱스는 -1
		marker = new int[4][2];
		for (int i = 0; i < 4; i++) {
			marker[i][1] = -1;
		}
		
		// 재귀 전 초기화 (각 말의 골인여부, 위치 중복 여부, 답)
		goal = new boolean[4];
		visited = new boolean[5][20];
		answer = 0;
		game(0, 0);
		
		System.out.println(answer);
		
		sc.close();
	}
	
	/*
	 * 순열(각 순서에 이동할 말 선택)에 백트래킹(이동할 수 있는지 유망성 검사)을 더한 함수
	 * 10라운드를 돌며 모든 경우의 수를 탐색
	 */
	private static void game(int round, int sum) {
		// 10라운드 다 돌았으면 결과 갱신
		if (round >= 10) {
			answer = Math.max(answer, sum);
			return;
		}
		
		// 말은 4개 중에 선택할거야~
		for (int p = 0; p < 4; p++) {
			// 이미 골인한 말이면 패스하자
			if (goal[p]) continue;
			
			// 이 말이 이동할 곳을 알려줘!
			int[] toBe = doRound(p, order[round]);
			
			// 이동 전 말의 좌표: 매번 불러오기 귀찮으니까 변수로 빼놓을게!
			int asR = marker[p][0];
			int asC = marker[p][1];
			
			// 골인지점으로 이동하는 거면
			if (toBe[1] == 20) {
				
				// 지금 있는 자리에서 사라지고, 골인 처리해
				visited[asR][asC] = false;
				goal[p] = true;
				
				// 도착지점에는 더할 숫자가 없어!, 다음 라운드 확인해
				game(round+1, sum);
				
				// 원상복구
				visited[asR][asC] = true;
				goal[p] = false;
				
				
			// 골인은 아니래! 이동하자!
			} else {
				
				// 근데 거기 이미 누가 있어? 난 못가네,, 다음 말한테 기회 주자
				if (visited[toBe[0]][toBe[1]]) continue;
				
				// 나 원래 맵에 내 자리 있었어!
				if (asC > -1) {
					
					// 현재자리 취소, 이동할 자리에 체크, 실제 좌표 변경
					visited[asR][asC] = false;
					visited[toBe[0]][toBe[1]] = true;
					marker[p] = new int[] {toBe[0], toBe[1]};
					
					// 나 잘 도착했어! 자릿수 더하고 다음 라운드 확인해줘~
					game(round+1, sum+map[toBe[0]].get(toBe[1]));
					
					// 원상복구
					visited[asR][asC] = true;
					visited[toBe[0]][toBe[1]] = false;
					marker[p] = new int[] {asR, asC};
					
				// 나 원래 맵에 없었어! 이제 들어가려고!
				} else {
					
					// 원래 자리가 없었으므로 자리 취소 및 복구 과정 생략
					// 나머지는 위와 동일
					visited[toBe[0]][toBe[1]] = true;
					marker[p] = new int[] {toBe[0], toBe[1]};
					
					game(round+1, sum+map[toBe[0]].get(toBe[1]));
					
					visited[toBe[0]][toBe[1]] = false;
					marker[p] = new int[] {asR, asC};
				}
			}
		}
	}

	/*
	 * pawn 번째 말이 cnt만큼 이동했을 때 도착 좌표를 반환해주는 함수
	 */
	private static int[] doRound(int pawn, int cnt) {
		
		// 지금 실제 좌표를 바꿀건 아니고, 시뮬레이션으로 이동할 위치만 확인할거야
		// 지역변수로 미리 빼두자
		int tobeR = marker[pawn][0];
		int tobeC = marker[pawn][1];
		
		// 이동해야하는 횟수만큼 반복
		for (int j = 0; j < cnt; j++) {
			// 마지막 위치에 있으면(더 이상 갈 곳이 없으면) 골인지점으로 이동
			if (tobeR == 0 && tobeC == 19) {
				return new int[] {0, 20};
			}
			
			// 열 좌표 증가
			tobeC++;
			
			// 이동 중에 만난 자릿값이 25면 (4, 0)으로 가!
			if (map[tobeR].get(tobeC) == 25) {
				tobeR = 4;
				tobeC = 0;
				
			// 40이면 (0, 19)로 가!
			} else if (map[tobeR].get(tobeC) == 40) {
				tobeR = 0;
				tobeC = 19;
			} 
		}
		
		
		// 최종 도착위치의 자리값이 10이면 (1, 0)으로 가!
		if (map[tobeR].get(tobeC) == 10) {
			tobeR = 1;
			tobeC = 0;
			
		// 20이면 (2, 0)으로 가!
		} else if (map[tobeR].get(tobeC) == 20) {
			tobeR = 2;
			tobeC = 0;
			
		// 오던 길이 지름길이 아니고, 자릿값이 30이면 (3, 0)으로 가!
		} else if (tobeR == 0 && map[tobeR].get(tobeC) == 30) {
			tobeR = 3;
			tobeC = 0;
		}
		
		// 최종 도착 위치를 반환해줘
		return new int[] {tobeR, tobeC};
	}
}
