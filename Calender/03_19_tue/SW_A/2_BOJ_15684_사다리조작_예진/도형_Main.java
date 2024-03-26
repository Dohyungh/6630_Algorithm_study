package BOJ._15684_사다리조작;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	// 첫인상
	//깊이가 30 이니까 웬만하면 완탐 아닐 듯.
	//이게 골3?
	//뭔가 있긴 있다
	//시뮬이 아닌거 가틈
	
	// 결론
	// ㄴㄴ 완탐임
	// 머리 굴려서 계산을 좀 하자.
	// 재귀류(DFS, DP)로 풀라 하면.. 시간초과 빡세게 날거 같음
	//300 * 300 * 300 / 6 = 450 만 개밖에 안됨.
	
	static int M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		M = sc.nextInt();
		int H = sc.nextInt();
		
		// 원본 스왑들
		int[][] oriSwaps = new int[M][2];
		
		
		for (int m = 0; m < M; m++) {
			oriSwaps[m][0]=sc.nextInt();
			oriSwaps[m][1]=sc.nextInt();
		}
		
		int[] arr = new int[N+1];
		for (int i = 1; i <N+1; i++) {
			arr[i] = i; // 1,2,3,4,5,6...N 에서 출발
		}
		
		
		int[][] swaps; // new int[M+3][2] , M개의 swap에 3개까지 추가될 수 있음. 0번 열은 가로선의 행번호, 1번열은 가로선의 왼쪽 점의 열번호를 의미함.
		
		
		int answer = -1;
		
		sorts(oriSwaps); // swap 방식을 사용하려면, 순서가 중요함. 먼저 만나는 가로선의 swap을 먼저 수행해주기 위해 정렬.

		if (getResult(oriSwaps, arr, 0)) {
			System.out.println(0); // 이미 완성되어 있으면 그냥 0 출력
			return;
		}
		
		for (int i = 1; i<=H; i++) {
			for (int j = 1; j<N; j++) {
				
				int first = i * N + j; // 쭉 늘어뜨린 인덱스
				
				swaps= copy(oriSwaps); // 복사
				
				if (contains(swaps,i,j)) continue; // 인접, 혹은 겹치는 가로선이 이미 있는지 확인
				swaps[M][0] = i; // 없으면 추가
				swaps[M][1] = j;

				sorts(swaps); // 다시 정렬..ㅜ
				
				if (getResult(swaps, arr, 1)) { // 결과 확인
					System.out.println(1);
					return;
				}
				
				for (int k = i; k <=H; k++) { // 했던거 또 하지 않게 정렬
					for (int l = 1; l <N; l++) { // 이거 j+1 했다가 한참 디버깅함. 행번호가 더 크면 열번호는 작아도 됨.
						
						int second = k*N +l;
						if (second <= first) continue; // 했던거 또 하지 않게 정렬
						
						swaps= copy(oriSwaps); // 복사

						swaps[M][0] = i; // 넣고, (이건 위에서 검사 해서 걸러졌음.)
						swaps[M][1] = j;
						if (contains(swaps,k,l)) continue; // 두번째 가로선 추가해도 되는지 검사
						swaps[M+1][0] = k; // 없으면 추가
						swaps[M+1][1] = l;

						sorts(swaps); // 다시 정렬..!
						
						if (getResult(swaps,arr,2)) { // 결과 확인
							if (answer == -1) answer = 2; // 아직 완성을 못했으면 그냥 답이 되는데.
							else {
								answer = Math.min(answer, 2); // 3개 가로선의 경우에서 답을 찾았었을 경우 2로 업데이트			
							}
						}
						
						// 이하 주석 생략
						for (int a = k; a <=H; a++) {
							for (int b = 1; b < N; b++) {
								int third = a*N+b;
								if (third <= first || third <= second) continue;
								
								swaps= copy(oriSwaps);

								swaps[M][0] = i;
								swaps[M][1] = j;
								swaps[M+1][0] = k;
								swaps[M+1][1] = l;
								if (contains(swaps,a,b)) continue;
								swaps[M+2][0] = a;
								swaps[M+2][1] = b;
								
								sorts(swaps);
								
								if (getResult(swaps,arr,3)) {
									if (answer == -1) answer = 3;
									else {
										answer = Math.min(answer, 3);										
									}
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println(answer);
	}
	
	public static boolean getResult(int[][] swaps, int[] arr, int numAdd) {
		
		int[] tempArr = Arrays.copyOf(arr, arr.length);
		
		boolean correct = true;
		
		for (int n = 0; n < swaps.length; n++) {
			
			int i = swaps[n][1];
			
			if(i==0) continue; // 0번 인덱스 더미인데 swap 시키면 안됨 0,0 만나면 0과 0+1(=1)을 자꾸 스왑해서 한참 또 디버깅
			
			int tmp = tempArr[i];
			tempArr[i] = tempArr[i+1];
			tempArr[i+1] = tmp;
			
		}
		
		for (int i =0; i<tempArr.length; i++) {
			if (tempArr[i] != i) correct = false;
		}
		
		return correct;
	}
	
	public static void sorts(int[][] swaps) { // 행번호 기준으로 정렬. 열번호 기준으로는 어차피 연결된 가로선은 불가능하기 때문에 정렬안해도 상관없음. (독립적으로 swap이 이루어진다!)
		Arrays.sort(swaps, (o1, o2)-> {
	        return o1[0] - o2[0];

		});
	}
	
	public static int[][] copy(int[][] oriSwaps) {
		int[][] swaps = new int[M+3][2]; // 복사할때, 나머지는 0, 0 으로 초기화 됨
		
		for (int i = 0; i<M; i++) {
			swaps[i] = Arrays.copyOf(oriSwaps[i], 2);
		}
		
		return swaps;
	}
	
	public static boolean contains(int[][] swaps, int i, int j) { // 이미 인접 혹은 그 위치에 가로선이 있는지 검사
		boolean impossible = false; // 가로선을 그을 수 있는가?
		for (int k = 0; k < swaps.length; k++) {
			if (swaps[k][0] == i && (swaps[k][1] == j || swaps[k][1] == j-1 || swaps[k][1] == j+1)) { // 인접, 혹은 그 위치에 가로선이 있으면
				impossible = true; // 불가능 하다!
				break;
			}
			
		}
		
		return impossible;
	}
}
