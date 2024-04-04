/*
 * 그냥 구현문제다. 문제에서 시키는대로 하면된다.
 * 단, 구현문제에서 가장 중요한 것은 어떤 자료구조를 활용해서 구현할 것인가 이다.
 * 자료구조만 올바르게 판단해도 수정시간이 많이 들지 않는다.
 * 한번 잘못 걸려들면 코드를 짜는중에 변수 타입을 계속 바꾸면서 실험하게 된다 ㅠ
 * 
 * 물론 코드를 직접 짜면서 예상했던 모양대로 안되는 경우가 허다하지만, 최대한 잘 설계해서 시작하는게 중요할 것 같다.
 * 동작 순서 매우 중요.
 */
package boj_20056_마법사상어와파이어볼;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static int N;
	static int M;
	static int K;
	static List<int[]>[][] map;
	static boolean[] moved;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); 		// 격자 크기
		M = sc.nextInt();		// 파이어볼 개수
		K = sc.nextInt();		// 명령 횟수
		
		map = new ArrayList[N][N];	// 각 칸에 있는 파이어볼 정보
		
		// 맵 List 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		// 각 파이어볼의 좌표, 질량, 속도, 방향 입력
		for (int i = 0; i < M; i++) {
			int r = sc.nextInt()-1;
			int c = sc.nextInt()-1;
			int m = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt();
			
			// 맵에 해당 좌표에 모든 정보를 담은 int배열을 추가
			map[r][c].add(new int[] {r, c, m, s, d});
		}
		
		for (int i = 0; i < K; i++) {
			move();
			bomb();
		}
		
		int answer = cal();
		System.out.println(answer);
		sc.close();
	}
	
	/*
	 * 남아있는 파이어볼의 질량의 총합을 구하는 함수
	 */
	private static int cal() {
		int sum = 0;
		
		// 맵 전체를 돌며
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				// 리스트가 널이거나 비어있지 않으면
				if (map[i][j] != null && map[i][j].size() > 0) {
					
					// 리스트에 있는 파이어볼의 질량을 sum에 추가
					for (int[] balls : map[i][j]) {
						sum += balls[2];
					}
				}
			}
		}
		return sum;
	}

	/*
	 * 같은 칸에 2개 이상의 파이어볼이 있다면 합치고 분해하는 함수
	 */
	private static void bomb() {
		
		// 맵을 모두 돌며
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				// 파이어볼이 2개 이상 있는 곳을 발견하면 동작
				if (map[i][j].size() > 1) {
					int cnt = 0;				// 방향이 홀수인 파이어볼 카운트
					int m = 0;					// 파이어볼들이 공통적으로 갖게 될 질량
					int s = 0;					// 파이어볼들이 공통적으로 갖게 될 속도
					
					// 모든 파이어볼 돌면서 질량, 속도 합하고 방향 홀짝여부 판단
					for (int[] ball : map[i][j]) {
						m += ball[2];
						s += ball[3];
						if (ball[4] % 2 != 0) cnt++;
					}
					
					// 질량은 5로 나누고, 속도는 파이어볼 개수만큼 나누기
					m /= 5;
					s /= map[i][j].size();
					
					boolean allSame = false;
					
					// 파이어볼이 모두 홀수거나 모두 짝수이면 allSame 참
					if (cnt == map[i][j].size() || cnt == 0) allSame = true;

					// 현재 파이어볼 모두 지우기
					map[i][j].clear();
					
					// 질량이 0이 아니면 다시 생성(4개!)
					if (m != 0) {
						
						if (allSame) {
							for (int k = 0; k <= 6; k+=2) {
								map[i][j].add(new int[] {i, j, m, s, k});
							}
						} else {
							for (int k = 1; k <= 7; k+=2) {
								map[i][j].add(new int[] {i, j, m, s, k});
							}
						}
					}
				}
			}
		}
	}

	/*
	 * 모든 파이어볼을 이동시키는 함수
	 */
	private static void move() {
		
		// 맵전체를 돌며
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				// 맵에 파이어볼이 하나라도 있으면 동작
				if (map[i][j] != null && map[i][j].size() > 0) {
					
					// 해당 좌표의 모든 파이어볼을 대상으로 방향과 속도 정보를 통해 이동좌표 구하기
					for (int[] balls : map[i][j]) {
						int d = balls[4];
						int s = balls[3];
						
						balls[0] = (i + (dr[d] * (s%N)) + N) % N;
						balls[1] = (j + (dc[d] * (s%N)) + N) % N;
						
						// 아직 진짜 옮기진 않고 좌표정보만 갱신해줌
						// 여기서 옮겨두면 다른 칸 돌때 또 옮길수도 있음
					}
				}
			}
		}
		
		// 이동할 좌표로 정보만 모두 갱신해뒀으면 실제 이동 시작
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null && map[i][j].size() > 0) {
					
					/// 거꾸로 돌아야 리스트에서 삭제시 인덱스 에러가 안남
					for (int k = map[i][j].size()-1; k >= 0; k--) {
						
						// 변경해둔 좌표 정보 불러와서
						int r = map[i][j].get(k)[0];
						int c = map[i][j].get(k)[1];
						
						// 좌표가 지금 위치와 달라졌다면 실제로 이동하고, 현재 위치에서 삭제
						if (r != i || c != j) {
							map[r][c].add(map[i][j].get(k));
							map[i][j].remove(k);
						}
					}
				}
			}
		}
	}
}
