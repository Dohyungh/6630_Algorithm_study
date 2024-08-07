package BOJ._2251_물통;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	
	// 현재 물통
	static int[] now = new int[3];
	
	// 물통의 크기
	static int[] full = new int[3];
	
	// 방문체크
	static boolean[][][] visited = new boolean[201][201][201];
	
	// 정답 셋 (중복 체크 + 정렬) 자동으로 해주는 트리 셋
 	static Set<Integer> answers = new TreeSet<Integer>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		full[0] = sc.nextInt();
		full[1] = sc.nextInt();
		full[2] = sc.nextInt();
		
		now[2] = full[2];
		
		// 최초 상태도 조건에 해당하기 때문에 정답에 추가
		answers.add(full[2]);
		
		// 수행
		pour(2,1);
		pour(2,0);
		
		// 정답 출력
		
		// pop 같은게 없어서 array로 바꿔서 출력해야 함
		for (int i = 0; i<answers.size(); i++) {
			System.out.print(answers.toArray()[i]);
			if (i != answers.size()-1) System.out.print(" ");
		}
		
	}
	
	// 어디에서 어디로 부을 지가 결정돼서 함수로 들어옴
	public static void pour (int from, int to) {
		
		// 방문 체크
		visited[now[0]][now[1]][now[2]] = true;
		
		// 부을 수 있는 물의 양
		int residual = Math.min(now[from], full[to]-now[to]);
		
		// 물을 붓자
		now[from] -= residual;
		now[to] += residual;
		
		// 정답 조건에 부합하면 answers에 담자
		if (now[0] == 0) answers.add(now[2]);
		
		// 모든 부을 수 있는 경우의 수를 체크하자.
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				
				// 다음 경우가 이미 간 경우면 가면 안된다.
				// cycle이 생기면서 무한 루프에 빠질 수 있기 때문에 처음 pour 함수 진입 때 방문 체크를 했으면
				// 다음 갈 곳이 갔던 곳이면 안 가는 로직을 쓴다.
				int[] simul = Arrays.copyOf(now, 3);
				int residualTest = Math.min(now[i], full[j]-now[j]);
				simul[i] -= residualTest;
				simul[j] += residualTest;
				if (visited[simul[0]][simul[1]][simul[2]]) continue;
				
				// 재귀 호출 한다.
				pour(i,j);
			}
		}
		
		// 안부었지롱
		now[from] += residual;
		now[to] -= residual;
		

	}

}
