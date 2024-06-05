/*
 *  [2트 이틀차,,성공]: 주자 상태 Queue로 구현했던 부분을 배열로 하니까 성공했다.
 *  Queue에 값을 넣었다뺐다 할때 크기 조정하는게(?) 시간이 오래걸린다고 한다.
 *  매 타자마자 예상타점 별로 배열을 순회하면서 구현하나, 큐를 돌리나 별차이 없을거라고 생각했는데 어림없이 차이났다.
 *  코드를 단순하게 구현하는데 있어서는 큐가 더 효율적으로 보였는데 짦은 코드가 좋은것만은 아닌가보다.
 *  
 *  [풀이방법]
 *  1. 4번타자(1번선수)를 제외한 나머지 타자(8명)의 순서를 순열로 정한다.
 *  2. 전체 순서가 정해질 때마다 해당 경우로 play 시뮬레이션을 통해 예상 득점을 얻는다.
 *  3. 최대 예상득점을 갱신한다.
 */

package boj_17281_야구공;

import java.util.Scanner;

public class Main {
	
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
			int[] ground = new int[5];
			
			while (ground[0] < 3) {
				// 이번 회차에 몇번째 타자의 예상 타점
				int score = runner[cnt][order[number++]];
				
				// 아웃이면 0번 인덱스 수 증가
				if (score == 0) ground[0]++;
				
				// 홈런이면 모든 주자 득점 및 타자도 득점
				// 0인덱스는 아웃수라서 다른 타점 처리 코드와 일반화 하지못함
				else if(score == 4) {
					for (int i = 1; i < 4; i++) {
						if (ground[i] == 1) {
							ground[i] = 0;
							ground[4]++;
						}
					}
					ground[4]++;
				
				// 아웃도 아니고 홈런도 아니면
				} else {
					
					// 해당 타점으로 점수를 낼 수 있는 위치의 주자는 모두 득점
					int start = 4 - score;
					for (int i = start; i < 4; i++) {
						if (ground[i] == 1) {
							ground[i] = 0;
							ground[4]++;
						}
					}
					
					// 득점은 내지 못하지만 이동하는 주자들 처리
					// 앞 인덱스부터 검사하면 바뀐값이 뒤에 적용되서, 거꾸로 확인
					for (int i = start-1; i > 0; i--) {
						if (ground[i] == 1) {
							ground[i] = 0;
							ground[i+score] = 1;
						}
					}
					
					// 타자 진출
					ground[score] = 1;
				}
				
				// 선수 순서 로테이션
				number %= 9;
			}
			
			// 아웃 3개 되면 회차 증가
			cnt++;
			result += ground[4];
		}
		
		return result;
	}

}