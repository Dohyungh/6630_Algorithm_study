package BOJ._19237_어른상어;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	// 오랜만에 객체 구현 말고 Map 구현으로 해보자
	
	static int[][] sharkMap;// sharkMap
	static int[][] smellMap;// smellMap
	static int[][] timeMap;// timeMap
	static int[] sharkDir;
	static int[][][] sharkInfo;
	
	static int N;
	static int M;
	static int K;
	
	static int leftShark;
	
	static boolean[] dead; // 디버깅해보니 실수 발견해서 어쩔 수 없이 넣어줌!
	// 상어가 계속 0,0 에서 리스폰 함..
	// nextLoc 가 계속 새로 생성되다 보니..
	
	static int[] dr = {-1,1,0,0}; // 위/ 아래/ 왼/ 오
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) {
		// 아니 뭐 조건이 이래
		// 드럽게 빡센 시뮬레이션
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // map 크기 
		M = sc.nextInt(); // 상어 마리수
		K = sc.nextInt(); // 냄새가 유지되는 턴 수
		
		leftShark = M; // 남은 상어 마리 수
		
		sharkMap = new int[N][N];
		smellMap = new int[N][N];
		timeMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int numShark = sc.nextInt();
				sharkMap[i][j] = numShark;
				if (sharkMap[i][j] != 0) { // 상어가 있으면
					timeMap[i][j] = K; // 냄새 남기고
					smellMap[i][j] = numShark; // 누구 냄샌지 남기고
				}
			}
		}
		sharkInfo = new int[M+1][4][4]; // 방향전환 우선순위를 배열에 저장
		sharkDir = new int[M+1]; // 상어 방향
		dead = new boolean[M+1]; // 상어 쫓겨났는지 여부 체크
		for (int i = 1; i <= M; i++) {
			sharkDir[i] = sc.nextInt()-1;
		}
		
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					sharkInfo[i][j][k] = sc.nextInt()-1;
				}
			}
		}
		
		int cnt = 0;
		while (cnt < 1000) { // 1000턴을 돌려
			simul();
			cnt++;
			if (leftShark ==1) break; // 만약 남은 상어가 1마리면 나가
		}

		int answer = leftShark > 1 ? -1 : cnt; // 킹항연산자
		System.out.println(answer);
		
	}

	private static void simul() {
		int[][] nextLoc = new int[M+1][2]; // 다음 위치를 담을 배열
		
		for (int i = 0; i <N; i++) {
			for (int j = 0; j <N; j++) {
				
				if (sharkMap[i][j] != 0) { // 상어네!
					int sharkIdx = sharkMap[i][j];
					int dir = -1;
					boolean foundEmpty = false; // 비어있으면 가면 되는데 안 비어 있으면 한번 더 돌아야함
					int nr = -1;
					int nc = -1;
					for (int d = 0; d < 4; d++) {
						dir = sharkInfo[sharkIdx][sharkDir[sharkIdx]][d]; // 우선순위에 따라 방향을 일단 받아
						nr = i + dr[dir];
						nc = j + dc[dir];
						if (nr >= 0 && nr < N && nc >= 0 && nc <N) {
							
							if (smellMap[nr][nc] == 0) { // 누구의 냄새도 없어 청정하다면
								foundEmpty = true; // 빈칸을 찾았어!
								break;
							}
						}
						
					}
					if (!foundEmpty) { // 빈칸을 못찾았어..
						for (int d = 0; d < 4; d++) {
							dir = sharkInfo[sharkIdx][sharkDir[sharkIdx]][d];
							nr = i + dr[dir];
							nc = j + dc[dir];
							if (nr >= 0 && nr < N && nc >= 0 && nc <N) {
								if (smellMap[nr][nc] == sharkIdx) { // 우선순위 순서대로 돌다가 내 냄새면 글로 간다.
									break;
								}
								
							}
							
						}
					}
					// 상어를 옮기고
					sharkDir[sharkIdx] = dir;
					nextLoc[sharkIdx][0] = nr;
					nextLoc[sharkIdx][1] = nc;
					sharkMap[i][j] = 0; // 상어 지워줘
					
				}
			}
		}
		
		// 여기까지. 모든 상어의 다음 위치를 찾았다.
		
		for (int i = 1; i <= M; i++) {
			for (int j = i+1; j <=M; j++) {
				if (nextLoc[i][0] == nextLoc[j][0] && nextLoc[i][1] == nextLoc[j][1] && !dead[j] && !dead[i]) { // i 도 죽었으면 안되네.. 여기서 한참걸림
					nextLoc[j][0] = -1; // 죽었으면, -1로 초기화.. 시켜놨는데 사실 일회성이고 다음번 시뮬떄 다시 살아난걸로 됨 (0,0) 에서 리스폰
					nextLoc[j][1] = -1;
					dead[j] = true; // 그래서 이거 추가해서 TRUE로 진짜 주겨버림
					leftShark--; // 남은 상어수 빼줌
				}
			}
		}
		
		// 모든 쫓겨날 상어가 쫓겨났다.

		// 상어 이동 완료
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (timeMap[i][j] != 0) { // 시간이 남은 냄새
					if (timeMap[i][j] == 1) { // 1이면 냄새 맵에서도 0으로 바꿔줘
						smellMap[i][j] = 0;
					}
					timeMap[i][j]--; // 시간 하나씩 줄여줘
				}
			}
		}
		for (int i = 1; i <=M; i++) {
			if (nextLoc[i][0] !=-1 && !dead[i]) { // 살아있고, 이번 턴에 쫓겨나지 않았다면, 
				sharkMap[nextLoc[i][0]][nextLoc[i][1]] = i; // 상어 맵 업데이트
				timeMap[nextLoc[i][0]][nextLoc[i][1]] = K;	// 시간 맵 업데이트
				smellMap[nextLoc[i][0]][nextLoc[i][1]] = i; // 냄새 맵 업데이트
				
			}
		}

	}

}
