package boj_17140_이차원배열과연산;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	// 우선순위 큐를 사용해서 두 개의 정렬 조건을 구현하자.
	static PriorityQueue<int[]> queue;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		// 문제의 행과 열의 인덱스는 1부터 시작하므로 1을 빼주고 시작.
		int r = sc.nextInt() - 1;
		int c = sc.nextInt() - 1;
		int k = sc.nextInt();

		// 100 x 100 0행렬을 초기화
		int[][] arr = new int[100][100];

		// 행/열 3칸씩만 입력받기
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		// 총 100초 동안 움직일 것
		int sec = 0;

		// 최종 출력할 정답 변수 초기화
		int answer = -1;

		// 만약 연산을 수행하지 않아도 정답이라면 0을 출력
		if (arr[r][c] == k) {
			answer = 0;
		}

		// 연산을 수행한다면 아래 구문을 실행
		else {
			// sec = 100초까지 가능(등호 주의)
			while (sec <= 100) {
				// 초를 증가하며 계산하다가 r, c에 k가 위치하는 순간 answer를 갱신하고 break;
				if (arr[r][c] == k) {
					answer = sec;
					break;
				}
				// 1초 증가
				sec++;

				// 만약 R 연산을 수행해야한다면
				if (isOperationR(arr)) {
					
					// arr의 행을 돌며 operation을 수행
					for (int i = 0; i < arr.length; i++) {
						// operation의 수행 결과인 1차원 배열을 newArr에 저장
						int[] newArr = operation(arr[i]);
						// newArr의 정보를 arr에 다시 옮겨서 저장
						for (int j = 0; j < arr[i].length; j++) {
							arr[i][j] = newArr[j];
						}

					}
				} else {	// 만약 C 연산을 수행해야한다면

					// 열 우선순회로 colArr를 생성
					for (int i = 0; i < arr[0].length; i++) {
						int[] colArr = new int[arr[0].length];
						for (int j = 0; j < arr.length; j++) {
							colArr[j] = arr[j][i];
						}
						// operation의 수행 결과인 1차원 배열을 newArr에 저장
						int[] newArr = operation(colArr);
						// newArr의 정보를 arr에 다시 옮겨서 저장
						for (int j = 0; j < arr[i].length; j++) {
							arr[j][i] = newArr[j];
						}

					}

				}

			}
		}

		System.out.println(answer);

		sc.close();

	}

	// 1차원 배열을 파라미터로 받아 문제의 연산을 수행하는 메소드 operation정의
	static int[] operation(int[] arr) {
		// opration 메소드를 호출하면 우선순위 queue를 초기화
		queue = new PriorityQueue<int[]>((arr1, arr2) -> {	// 람다식으로 정렬의 우선순위를 설정
			// 만약 빈도수(등장횟수)가 다르다면
			if (arr1[1] != arr2[1]) {
				return arr1[1] - arr2[1]; // 빈도수가 작으면 먼저
			} else {
				return arr1[0] - arr2[0]; // 빈도수가 같으면 값이 작은 순으로 정렬
			}
		});

		// 카운팅 배열 생성
		int[] cntArr = new int[101];

		// 1차원 배열을 돌며 카운팅 배열을 채움.
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				cntArr[arr[i]]++;
			}
		}

		// 카운팅 배열을 통해 각 숫자와 해당 숫자의 등장횟수를 freqArr 배열에 담고, 우선순위 큐에 add하기.
		// ex) arr가 [1, 2, 1, 0, ..., 0]이었다면 [2, 1], [1, 2] 순서대로 add.
		for (int i = 0; i < cntArr.length; i++) {
			if (cntArr[i] != 0) {
				int[] freqArr = { i, cntArr[i] };
				queue.add(freqArr);
			}
		}

		// 연산을 수행하고 난 뒤 arr의 값을 바꿔줄 인덱스의 위치 초기화
		int idx = 0;

		// 우선순위 큐가 빌 때까지 아래를 수행
		while (!queue.isEmpty()) {
			// 우선순위 큐에서 배열을 하나 꺼내온다.
			int[] tempArr = queue.poll();
			// 만약 arr의 인덱스가 100을 넘어가면 빠져나오기
			if (idx >= 100) {
				break;
			}
			// arr의 인덱스에 숫자와 해당 숫자의 빈도수를 차례대로 입력
			arr[idx++] = tempArr[0];
			arr[idx++] = tempArr[1];
		}

		// 숫자와 빈도수 입력이 끝났다면 1차원 배열의 나머지 공간들은 0으로 채움
		for (int i = idx; i < arr.length; i++) {
			arr[i] = 0;
		}

		// 연산을 수행한 후 바뀐 arr를 반환
		return arr;
	}

	// 최대 행의 길이, 최대 열의 길이를 비교해서 행의 길이가 열의 길이보다 길거나 같으면 true를 반환하는 메소드
	// 파라미터는 2차원 배열 arr
	static boolean isOperationR(int[][] arr) {
		
		// row에 대한 max값 0으로 초기화
		int maxRowLength = 0;
		for (int i = 0; i < arr.length; i++) {
			// 각 row를 돌며 앞에서부터 0인지 아닌지를 검사
			int rowLength = 0;
			for (int j = 0; j < arr[i].length; j++) {
				// 0이 등장할 때까지 rowLen을 갱신
				if (arr[i][j] != 0) {
					rowLength = i;
				}
			}
			// max값 갱신
			maxRowLength = Math.max(maxRowLength, rowLength);
		}

		// 열의 길이 계산도 위와 동일.
		int maxColLength = 0;
		for (int i = 0; i < arr[0].length; i++) {
			int colLength = 0;
			for (int j = 0; j < arr.length; j++) {
				if (arr[j][i] != 0) {
					colLength = i;
				}
			}
			maxColLength = Math.max(maxColLength, colLength);
		}

		// 행의 길이가 열의 길이보다 길거나 같다면 true를 출력
		return maxRowLength >= maxColLength;
	}

}
