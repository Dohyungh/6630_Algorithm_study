import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[][] map;
	static int N;
	static int Q;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static int[] p;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
        // 알고 보니 입력이 지수 (2^N) 에서 N 을 주는 거였는데
        // 코드를 N을 변의 길이라고 짜놔서 입력을 요렇게 받았음!
		N = (int) Math.pow(2, sc.nextInt());
		Q = sc.nextInt();
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
        //q번 시뮬레이션을 돌리는데
        //작은 격자의 변의 길이의 지수를 또 입력으로 받는다.
		for (int q = 0; q < Q; q++) {
			simul(sc.nextInt());
		}
		// 맵의 총합과
		System.out.println(sum(map));
        // 가장 큰 덩어리의 크기를 출력한다.
		System.out.println(largestFamily(map));
	}

	private static void simul(int nextInt) {
		// 역시 변의 길이로 바꿔주고,
		int len = (int) Math.pow(2, nextInt);
		
        // 변의 길이만큼 건너뛰면서 시작점 (맨 왼쪽 위) 을 잡아준다.
		for (int i = 0; i < N; i+=len) { 
			for (int j = 0; j < N; j+=len) {
				//복사복사
				int[][] tmp = new int[len][len];
				
				for (int k = 0; k < len; k++) {
					for (int l = 0; l < len; l++) {
						tmp[k][l] = map[i+k][j+l];
					}
				}
				//돌려돌려
				for (int k = 0; k < len; k++) {
					for (int l = 0; l < len; l++) {
						//땡스 투 2월의 성진이
						map[i+k][j+l] = tmp[len-l-1][k];
						
					}
				}
			}
		}
		// 여기까지 일단 다 돌렸고,

        // 이제 인접한 얼음이 3개 가 안되는 친구들을 1씩 빼줘야 함
		
        // 다시 복사복사
        // 여기도 다시 복사 해줘야함!!
        // 행 우선순회로 전부 돌텐데 앞에 친구 줄인거를 뒤에서 알아버리면 안되기 때문에!

		int[][] tmp = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			tmp[i] = Arrays.copyOf(map[i], map[i].length);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int adjIce = 0;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					
					if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
						if (map[nr][nc] >0) adjIce++; // 0보다 큰 친구 개수를 세서
					}
					
				}
				
				if (!(adjIce >=3)) { // 3 보다 크지 않으면 (일부러 문제 조건이랑 똑같이 적었음)
					tmp[i][j] = map[i][j]-1; // 하나 빼줘
				}
			}
		}
        // 마지막에 참조 주소만 바꿔주면 끝!
		map = tmp;
		

		
	}

    // 다 더하는 거
	private static int sum(int[][] map) {
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer+=Math.max(map[i][j], 0);
			}
		}
		return answer;
	}

	// Union-find로 가장 큰 덩어리 찾기
	private static int largestFamily(int[][] map) {
		p = new int[N*N]; // 쭉늘뜨! 근데 맵이 커지면 int 범위 벗어날까 살짝 무섭긴 하네
		for (int i = 0; i < p.length; i++) { // make-set
			p[i] = i;
		}
		
		for (int i = 0; i < N*N; i++) {
			int r = i / N;
			int c = i % N;
			
			for (int d = 0; d < 4; d++) {
				int adjR = r + dr[d];
				int adjC = c + dc[d];
				if (adjR < N && adjR >= 0 && adjC < N && adjC >=0) {
					if (map[adjR][adjC] > 0 && map[r][c] > 0) { // 인접한 친구들이 모두 얼음이 있으면
						
						int p1 = adjR * N + adjC;
						int p2 = i; // r * N + c 로 써도 됨. 똑같은 거임
						
						union(p1,p2); // 부모를 찾아서 합쳐줘
					}
				}
			}	
		}

        // ! 예외 주의
        // 전부 0일 때에는 얼음이 하나도 없는 거니까 0을 출력.
		out:
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 0) break out;
				if (i == N-1 && j == N-1) return 0;
			}
		}
        
        
        // 이거 잘 할 수 있는 방법 있을 거 같은데?!
        
        //가장 큰 덩어리를 찾기 위해 카운팅 배열을 만들어서
        // 부모를 찾았을 때 카운팅 배열 값을 1씩 증가시켜서 덩어리의 크기를 구했음
		int max = 0;
        int[] count = new int [N*N];
		for (int i = 0; i < count.length; i++) {
			int p = find(i);
			count[p]++;
			max = Math.max(max, count[p]); // 귀찮으니까 max 는 1씩 늘려줄 때마다 비교했음
		}
		
		return max;
	}
    // 유니온!
	private static void union(int p1, int p2) {
		p[find(p1)] = find(p2);
		
	}
    // 파인드!
	private static int find(int p1) {
		if (p[p1] == p1) return p1;
		else {			
			return p[p1] = find(p[p1]); // 패쓰 콤푸레쑌느
		}
		

		
		
	}

	
}
