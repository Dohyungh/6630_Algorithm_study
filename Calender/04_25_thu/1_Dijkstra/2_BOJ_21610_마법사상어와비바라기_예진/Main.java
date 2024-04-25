/*
* 설계 : 5분
* 풀이 : 55분
*
* - 그냥 문제에서 시키는대로 함
* - 구름 생성 위치를 Cloud 클래스로 List에 저장해서 하나씩 이동했다.
* - % 연산으로 이동 시 끝과 끝이 연결되도록 했다. (스피드에 먼저 %연산을 해서 인덱스가 마이너스로 가지 않도록하기)
* - 구름 이동 후 2. 바구니 물 증가시 changed 배열에 체크해서 새구름 생성시 중복되지 않도록 했다.
*/

package boj_21610_마법사상어와비바라기;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] field = new int[N][N];
		
		// 필드 각 바구니 물양 입력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				field[i][j] = sc.nextInt();
			}
		}
		
		// 주문 입력(방향, 속도)
		int[][] order = new int[M][];
		for (int i = 0; i < M; i++) {
			order[i] = new int[] {sc.nextInt()-1, sc.nextInt()};
		}
		
		// 구름 리스트
		List<Cloud> clouds = new ArrayList<>();
		
		// 구름 리스트 초기화
		clouds.add(new Cloud(N-1, 0));
		clouds.add(new Cloud(N-1, 1));
		clouds.add(new Cloud(N-2, 0));
		clouds.add(new Cloud(N-2, 1));
		
		// 진행할 주문만큼 반복
		for (int i = 0; i < M; i++) {
			boolean[][] changed = new boolean[N][N];
			
			// 1. 구름 이동
			int direc = order[i][0];
			int speed = order[i][1];
			
			for (Cloud cloud : clouds) {
				cloud.r = (cloud.r + (dr[direc] * (speed%N)) + N) % N;
				cloud.c = (cloud.c + (dc[direc] * (speed%N)) + N) % N;
				
				// 2. 이동한 곳 바구니 물 증가
				field[cloud.r][cloud.c]++;
				changed[cloud.r][cloud.c] = true;
			}

			// 4. 물복사 버그 마법
			for (Cloud cloud : clouds) {
				for (int d = 1; d <= 8; d += 2) {
					int nr = cloud.r + dr[d];
					int nc = cloud.c + dc[d];
					
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && 
							field[nr][nc] > 0) {
						field[cloud.r][cloud.c]++;
					}
				}
			}
			
			// 3. 이제 구름 사라짐
			clouds.clear();
			
			// 5. 새로운 구름 생성, 물의 양 -2 (방금 사라진 곳 빼고)
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (field[r][c] >= 2 && !changed[r][c]) {
						clouds.add(new Cloud(r, c));
						field[r][c] -= 2;
					}
				}
			}
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer += field[i][j];
			}
		}
		
		System.out.println(answer);
		sc.close();
	}

}

class Cloud {
	int r;
	int c;
	
	Cloud(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Cloud [r=" + r + ", c=" + c + "]";
	}
}
