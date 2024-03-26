package BOJ._12100_2048;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N; // 맵의 크기
	static List<Integer[]> cases = new ArrayList<>(); // 경우의 수 모아놓아
	static int[] dirs = {1,2,3,4}; // 상, 하, 좌, 우

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		 N = sc.nextInt();
		 
		 int[][] arr = new int[N][N];
		 
		 for (int i = 0; i < N; i++) {
			 for (int j = 0; j < N; j++) {
				 arr[i][j] = sc.nextInt();
			 }
		 }
		 
		 Integer[] output = new Integer[5];
		 updateCases(0, output); 
		 // 이 경우의 수는 모든 테스트케이스에 4의 5제곱으로 똑같기 때문에, (상상상상상 부터 ~ 우우우우우 까지)
		 // 만약 SWEA 문제라면, (tc <= T)
		 // 이 경우의 수를 for(tc = 1 ; tc <= T; tc++){ } 밖에서 실행하고 계속 가져다 쓰면 된다. (시간 차이가 좀 날거임)

		 
		 int final_answer = 0;
		 for (int i = 0; i < cases.size(); i++) {
			 
			 //복사해서
			 int[][] tempArr = new int[N][N];
			 
			 for (int k = 0; k < N; k++) {
				 tempArr[k] = Arrays.copyOf(arr[k], N);
			 }
			 
			 // 한 가지 경우의 수에 대해서
			 Integer[] aCase = cases.get(i);
			 
			 // 시뮬레이션을 돌려본다.
			 for (int c = 0; c < 5; c++) {
				 play(tempArr, aCase[c]);
			 }
			 
			 // 정답을 최댓값으로 업데이트한다.
			 for (int r = 0; r < N; r++) {
				 for (int c = 0; c< N; c++) {
					 final_answer = Math.max(final_answer, tempArr[r][c]);
				 }
			 }
		 }
		 System.out.println(final_answer);
		 
		 sc.close();
	}
	
	private static void updateCases(int depth, Integer[] output) {
		// 기저조건
		if (depth ==5) { // 0, 1, 2, 3, 4 // 5가 depth 에 들어오면 끝난 것.
			cases.add(Arrays.copyOf(output, output.length)); // output 복사해서 넣어줘야지 안그러면 다 똑같은거 들어가 있다.
			return;
		}
		
		// 중복 순열
		for (int i = 1; i <=4; i++) {
			output[depth] = i;
			updateCases(depth+1, output);
		}
	}

	public static void play(int[][] arr, int dir) { // 1,2,3,4 // 상, 하, 좌, 우

		// 하나만 같이 해보자
		if (dir ==1) { // 위쪽 버튼을 눌렀어
			for (int j = 0; j < N; j++) { // 모든 "열"에 대해서 같은 조작을 해줄 건데
				// 위쪽 꺼부터 합쳐지니까
				// 위에서부터 아래로 읽으면서
				// 포인터를 2개 두고 (엄밀히는 1개. 그 데이터의 값과 index를 기억하는 거니까.)
				// now 포인터에는 지금의 숫자 (똑같은게 나오는 순간 2배 시켜줄)
				// before_index 에는 now에 저장된 놈의 index
					// 두개가 '합쳐진다' 는 개념이니까 이전 놈과 같은 놈을 찾는 순간 둘 모두에게 조작을 가해야 함!
					// 따라서 이전의 index를 저장할 필요가 있음
				
				int idx = 0;
				int now = -1;
				int before_index = -1;
				while (idx < N) {

					// 기본적으로 0이 아닌 값을 만나는 것만 '이벤트'임

					// 아무것도 없는 상태
					// 2가지 경우가 있는데, 최초 시작 일 때와 방금 2개를 합쳤을 때가 있음
					if (arr[idx][j] != 0 && now == -1) { // 그냥 지금 값을 변수들에 저장 하면 됨
						now = arr[idx][j];
						before_index = idx;
						idx++;
						continue;
					}
					if (arr[idx][j] != 0 && now != arr[idx][j]) { // 다른 값을 만났을 때 (나가리) 일 때 도 같음
						now = arr[idx][j];
						before_index = idx;
						idx++;
						continue;
					}
					if (arr[idx][j] != 0 && now == arr[idx][j]) { // 똑같은 값을 만났을 때!
						arr[before_index][j] *= 2; // 2배 해주고
						arr[idx][j] = 0; // 지금 놈을 0으로 바꿔주고 (before_index 를 0으로 바꿔도 됨)
						now = -1; // 리셋
						before_index = -1; // 리셋
						idx++;
						continue;
					}
					idx++;
					
					
				}
				// 이제 다 땡기러 다시 출발
				idx = 0;
				out:
				while(idx < N) {
					if (arr[idx][j] == 0) {
						// 0을 만났어
						boolean flag = false;
						// 다음부터 순회하면서
						// 0이 아닌 값을 만나면 땡겨줄 거임
						for (int k = idx+1; k <N; k++) {
							if (arr[k][j] != 0) {
								arr[idx][j] = arr[k][j];
								arr[k][j] = 0;
								flag = true;
								break;
							}
						}
						// 뒤에가 전부 다 0 이면, 끝내
						if (!flag) break out;
					}
					idx++;
				}
				
			}
			
		}
		if (dir ==2) {
			for (int j = 0; j < N; j++) {
				int idx = N-1;
				int now = -1;
				int before_index = -1;
				while (idx >= 0) {
					if (arr[idx][j] != 0 && now == -1) { // 최초로 0 이 아닌 원소 탐색 이건 그냥 따로 빼서 써도 되긴 함
						now = arr[idx][j];
						before_index = idx;
						idx--;
						continue;
					}
					if (arr[idx][j] != 0 && now != arr[idx][j]) {
						now = arr[idx][j];
						before_index = idx;
						idx--;
						continue;
					}
					if (arr[idx][j] != 0 && now == arr[idx][j]) {
						arr[before_index][j] *= 2;
						arr[idx][j] = 0;
						now = -1;
						before_index = -1;
						idx--;
						continue;
					}
					idx--;
					
					
				}
				// 이제 다 땡기러 다시 출발
				idx = N-1;
				out:
				while(idx >=0) {
					if (arr[idx][j] == 0) {
						boolean flag = false;
						for (int k = idx-1; k >=0; k--) {
							if (arr[k][j] != 0) {
								arr[idx][j] = arr[k][j];
								arr[k][j] = 0;
								flag = true;
								break;
							}
						}
						if (!flag) break out;
					}
					idx--;
				}
				
			}
			
		}
		if (dir ==3) {
			for (int i = 0; i < N; i++) {
				int idx = 0;
				int now = -1;
				int before_index = -1;
				while (idx < N) {
					if (arr[i][idx] != 0 && now == -1) { // 최초로 0 이 아닌 원소 탐색 이건 그냥 따로 빼서 써도 되긴 함
						now = arr[i][idx];
						before_index = idx;
						idx++;
						continue;
					}
					if (arr[i][idx] != 0 && now != arr[i][idx]) {
						now = arr[i][idx];
						before_index = idx;
						idx++;
						continue;
					}
					if (arr[i][idx] != 0 && now == arr[i][idx]) {
						arr[i][before_index] *= 2;
						arr[i][idx] = 0;
						now = -1;
						before_index = -1;
						idx++;
						continue;
					}
					idx++;
					
					
				}
				// 이제 다 땡기러 다시 출발
				idx = 0;
				out:
				while(idx < N) {
					if (arr[i][idx] == 0) {
						boolean flag = false;
						for (int k = idx+1; k < N; k++) {
							if (arr[i][k] != 0) {
								arr[i][idx] = arr[i][k];
								arr[i][k] = 0;
								flag = true;
								break;
							}
						}
						if (!flag) break out;
					}
					idx++;
				}
				
			}
			
		}
		if (dir ==4) {
			for (int i = 0; i < N; i++) {
				int idx = N-1;
				int now = -1;
				int before_index = -1;
				while (idx >= 0) {
					if (arr[i][idx] != 0 && now == -1) { // 최초로 0 이 아닌 원소 탐색 이건 그냥 따로 빼서 써도 되긴 함
						now = arr[i][idx];
						before_index = idx;
						idx--;
						continue;
					}
					if (arr[i][idx] != 0 && now != arr[i][idx]) {
						now = arr[i][idx];
						before_index = idx;
						idx--;
						continue;
					}
					if (arr[i][idx] != 0 && now == arr[i][idx]) {
						arr[i][before_index] *= 2;
						arr[i][idx] = 0;
						now = -1;
						before_index = -1;
						idx--;
						continue;
					}
					idx--;
					
					
				}
			
				// 이제 다 땡기러 다시 출발
				idx = N-1;
				out:
				while(idx >= 0) {
					if (arr[i][idx] == 0) {
						boolean flag = false;
						for (int k = idx-1; k >=0; k--) {
							if (arr[i][k] != 0) {
								arr[i][idx] = arr[i][k];
								arr[i][k] = 0;
								flag = true;
								break;
							}
						}
						if (!flag) break out;
					}
					idx--;
				}
				
			}
			
		}
		
		
		
	}

}
