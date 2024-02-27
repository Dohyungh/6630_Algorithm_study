package BOJ._15683_감시;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main_fin {

	static int count = 0;
	static List<int[]> cases = new ArrayList<>();
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] arr = new int[N][M];
		 
		// 입력받으면서 cctv 개수 파악
		int numcctvs = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int num = sc.nextInt();
				arr[i][j] = num;
				if (num != 0 && num != 6 && num !=5) {
					numcctvs++;
				}
			}
			
		}
		
		// cctv 배열 생성
		int[][] cctvs = new int[numcctvs][3]; // 배열 크기 때문에 numcctvs 변수를 만들었는데, 그냥 List 쓰면 됨
		

		// cctv 종류 파악
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != 0 && arr[i][j] !=6 && arr[i][j] != 5) {
					cctvs[cnt][0] = arr[i][j];
					cctvs[cnt][1] = i;
					cctvs[cnt][2] = j;
					cnt++;
					
				}
			}
		}
		// 첫번째 원소로 cctv 타입, 2, 3 번째에 좌표 입력한 2차원 배열
		// 예시) [[1,0,0], [1,1,1], [4,1,2]]
		
		// 5번 cctv 선반영
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				if (arr[i][j] == 5) {
					for (int dir = 0; dir < 4; dir++) {
						arr = fill(arr, i, j, dir);
					}
				}
			}
		}
		
		boolean[][] visited = new boolean[numcctvs][];
		// ccty 타입에 따라 길이가 다른 boolean 배열로 초기화
		for(int i = 0; i < numcctvs; i++) {
			if (cctvs[i][0] == 1 || cctvs[i][0] == 3 || cctvs[i][0] == 4) {
				visited[i] = new boolean[4]; 
				
			} else { // 2일 경우
				visited[i] = new boolean[2];
			}
		}
		// visited 가 어떻게 생겼냐면..
		
		// 카메라 타입이 1,3,2,1,4 라면
		// [[f,f,f,f],
		// [f,f,f,f,],
		// [f,f],
		// [f,f,f,f],
		// [f,f,f,f]] 로 2 만 길이가 2로 생겼음.
		
		
		//가능한 경우의 수 불러오기
		getCases(0, numcctvs, visited);

		int answer = N*M; // 최댓값으로 초기화
		for (int[] aCase : cases) {
			// aCase는 어떻게 생겼냐면..
			// [3,0,1,0,2] 요런식으로 cctv 마다 방향을 정해줌.
			
			//복사 안하면 통과가 안되는데 어디서 얕은 복사 생기는지를 모르겠음ㅠㅠ
			// 그냥 arr 넣으면 계속 중복됨.
			int[][] temp = new int[N][];
			for (int i = 0; i < arr.length; i++) {
				temp[i] = Arrays.copyOf(arr[i], arr[i].length);
			}
			temp = getResult(temp, aCase, cctvs);
			if (answer > countZero(temp)) {
				answer = Math.min(answer, countZero(temp));
			}
			
		}
		
		System.out.println(answer);
		
		
		
		
		
	}
	// 1 이면 column이 4개
	// 2 이면 column이 2개
	// 3 이면 column이 4개
	// 4 이면 column이 4개

	public static int countZero(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j]==0) cnt++;
			}
		}
		return cnt;
	}
	
	// 방향과 좌표를 주면 그 방향으로 쭉 채워주는 함수
	// cctv 타입은 아직 고려x
	public static int[][] fill (int[][] arr, int i, int j, int dir) {
		int[] dr = {-1,0,1,0};
		int[] dc = {0,-1,0,1};
		int nr = i;
		int nc = j;
		int N = arr.length;
		int M = arr[0].length;
		while(true) {
			nr = nr+dr[dir];
			nc = nc+dc[dir];
			if(nr < N && nr >= 0 && nc < M && nc>=0) {
				if (arr[nr][nc] == 6) {
					break;
				}
				if (arr[nr][nc] == 0) arr[nr][nc] = 7; //그냥 아무 숫자
			}else {
				break;
			}
		}
		return arr;
	}
	
	
	public static void getCases(int depth, int r, boolean[][] visited) {
		if (depth == r) {
			// aCase 배열이 permutaion 에서 output 역할을 하고 있음.
			// 완성된 visited 배열을 보면서 aCase 
			// visited.length == cctv 개수
			int[] aCase = new int[visited.length];
			for(int i = 0; i < visited.length; i++) {
				for (int j = 0; j < visited[i].length; j++) {
					if (visited[i][j]) aCase[i] = j;
				}
			}
			cases.add(aCase);
			return;
		}
		
		for (int i = 0; i < visited[depth].length; i++) {
			if (!visited[depth][i]) {
				visited[depth][i] = true;
				getCases(depth+1,r,visited);
				visited[depth][i] = false;
			}
		}
	
	}
	
	public static int[][] oneFill (int[][] arr, int i, int j, int dir) {
		arr = fill(arr, i, j, dir);
		
		return arr;
	}
	public static int[][] twoFill (int[][] arr, int i, int j, int dir) {
		// 0 0, 2
		// 1 1, 3
		arr = fill(arr, i, j, dir);
		arr = fill(arr, i, j, dir+2);
		return arr;
	}
	public static int[][] threeFill (int[][] arr, int i, int j, int dir) {
		// 3번은 90도니까 4방향이랑 그 방향 바로 다음꺼. 마지막만 모듈러 처리해주면 됨.
		arr = fill(arr,i,j,dir);
		arr = fill(arr,i,j,(dir+1)%4);
		return arr;
	}
	public static int[][] fourFill (int[][] arr, int i, int j, int dir) {
		// 4번은 한방향빼고 모두니까 입력을 그 뺴는 방향 쪽으로 잡고 나머지를 감시
		for(int k = 0; k < 4; k++) {
			if (k!= dir) {
				arr = fill(arr,i,j,k);
			}
		}
		return arr;
	}
	
	public static int[][] getResult(int[][] arr, int[] aCase, int[][] cctvs) {
		int[][] temp = arr;
		for(int i = 0; i < cctvs.length; i++) {
			if(cctvs[i][0] ==1) {
				temp = oneFill(temp, cctvs[i][1], cctvs[i][2], aCase[i]);
			}
			if(cctvs[i][0] ==2) {
				temp = twoFill(temp, cctvs[i][1], cctvs[i][2], aCase[i]);
			}
			if(cctvs[i][0] ==3) {
				temp = threeFill(temp, cctvs[i][1], cctvs[i][2], aCase[i]);
			}
			if(cctvs[i][0] ==4) {
				temp = fourFill(temp, cctvs[i][1], cctvs[i][2], aCase[i]);
			}
			
		}
		return temp;
	}
	
	

}