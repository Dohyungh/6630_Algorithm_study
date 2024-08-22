package boj_2342_DanceDanceRevolution;

import java.util.Scanner;



// 각 스텝별 k에 따른 (왼발, 오른발) 위치에 따른 사용되는 최소의 힘을 저장하는 arr를 사용
// (왼발, 오른발) 좌우 바뀌는 것 고려 안해도 모든 경우의 수를 커버할 수 있음

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int[][][] arr = new int[5][5][100001];

		int k = 0;

		int num = sc.nextInt();

		// 0이 입력되는 경우 처리
		if (num == 0) {
			System.out.println(0);
			return;
		}

		while (num != 0) {

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					arr[i][j][k] = Integer.MAX_VALUE;
				}
			}

			if (k == 0) {
				arr[0][num][k] = 2;
//				arr[num][0][k] = 2;
			}

			else {
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {

						if (arr[i][j][k - 1] != Integer.MAX_VALUE) {

							// 이미 발이 놓여져 있는 경우(필요한 힘: 1)를 먼저 처리
							if (num == i || num == j) {
								arr[i][j][k] = arr[i][j][k - 1] + 1;
							}

							// 이미 발이 놓여져 있지 않은 경우
							else {
								
								// 아직 한 발이 중앙에 남아있는 경우 1
								if (i == 0) {
									// 중앙의 발을 움직이는 경우(필요한 힘: 2)
									arr[num][j][k] = Math.min(arr[num][j][k], arr[i][j][k - 1] + 2);

									// 중앙의 발을 움직이지 않고, j 위치의 발을 움직여 밟는 경우(필요한 힘: 3 또는 4)
									if (Math.abs(num - j) == 2) {
										// 반대편 이동
										arr[i][num][k] = Math.min(arr[i][num][k], arr[i][j][k - 1] + 4);
									} else {
										// 인접한 지점 이동
										arr[i][num][k] = Math.min(arr[i][num][k], arr[i][j][k - 1] + 3);
									}
								}
								// 아직 한 발이 중앙에 남아있는 경우 2
								// 위와 동일
								else if (j == 0) {									
									arr[i][num][k] = Math.min(arr[i][num][k], arr[i][j][k - 1] + 2);
									if (Math.abs(num - i) == 2) {
										arr[num][j][k] = Math.min(arr[num][j][k], arr[i][j][k - 1] + 4);
									} else {
										arr[num][j][k] = Math.min(arr[num][j][k], arr[i][j][k - 1] + 3);
									}
								}

								// 두 발 전부 중앙에 없는 경우
								else {
									// i 위치의 발을 옮기는 경우
									if (Math.abs(num - i) == 2) {
										arr[num][j][k] = Math.min(arr[num][j][k], arr[i][j][k - 1] + 4);
									} else {
										arr[num][j][k] = Math.min(arr[num][j][k], arr[i][j][k - 1] + 3);
									}
									// j 위치의 발을 옮기는 경우
									if (Math.abs(num - j) == 2) {
										arr[i][num][k] = Math.min(arr[i][num][k], arr[i][j][k - 1] + 4);
									} else {
										arr[i][num][k] = Math.min(arr[i][num][k], arr[i][j][k - 1] + 3);
									}

								}
							}

						}
					}
				}
			}

			k++;

			num = sc.nextInt();
			
		}

		int answer = Integer.MAX_VALUE;

		for (int i = 0; i < 5; i++) {
//			System.out.println();
			for (int j = 0; j < 5; j++) {
//				System.out.print(arr[i][j][k-1] + " ");

				if (answer > arr[i][j][k - 1]) {
					answer = arr[i][j][k - 1];
				}
			}
		}

		System.out.println(answer);

	}
}



// 반례....
/*
1 2 2 4 0
8

1 0
2

0
0

1 2 1 0
5

1 2 1 2 1 2 0
8

1 3 2 4 1 2 0
14

1 3 2 2 0
8

1 3 2 0
7

1 2 3 1 2 0
11

연속선택
1 3 2 2 2 1 0
10

1 3 2 2 2 4 0
12

1 3 2 2 2 4 2 0
13

1 3 2 2 2 3 0
10

1 3 2 2 1 1 0
10

1 3 2 2 1 1 0
10

1 3 2 4 1 3 2 4 0
20

1 2 3 0
7

4 4 4 0
4

첫두번을 한쪽발로만 밟는 경우
1 2 3 2 0
8

1 2 3 2 1 0
11

*/
