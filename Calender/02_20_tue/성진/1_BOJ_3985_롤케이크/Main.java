package boj_3985_롤케이크;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 롤케이크의 길이 L
		int L = sc.nextInt();

		// 길이 + 1만큼의 배열 생성
		// 인덱스에 맞춰 케이크 조각을 나눌 예정
		int[] cakeArr = new int[L + 1];

		// 방청객의 수 N
		int N = sc.nextInt();

		// 가장 많은 조각을 받을 것으로 기대하고 있던 방청객의 번호
		int expectMaxNum = 0;
		// 실제로 가장 많은 조각을 받은 방청객의 번호
		int realMaxNum = 0;

		// 최댓값 갱신 알고리즘을 위한 최댓값 초기화
		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;

		// 방청객의 수만큼 for문을 반복
		// num을 1부터 N까지로 방청객의 번호를 문제 상황과 동일하게 생성
		for (int num = 1; num <= N; num++) {

			// 각 방청객이 실제로 가져갈 조각의 개수 초기화
			int piece = 0;

			// 각 방청객이 원하는 P번 조각부터 K번 조각 입력
			int p = sc.nextInt();
			int k = sc.nextInt();

			// 가장 많은 조건을 받을 것으로 기대하던 방청객을 갱신
			// 최댓값 알고리즘에서 부등식 등호가 없어야만 작은 방청객의 번호를 출력할 수 있음
			if (k - p > max1) {
				max1 = k - p;
				expectMaxNum = num;
			}

			// 입력받은 p와 k값을 활용해 각 방청객이 실제로 가져가게 될 케이크 수를 계산
			// 각 방청객은 최종적으로 piece만큼 가져가게 될 것임
			for (int x = p; x < k + 1; x++) {
				if (cakeArr[x] == 0) {
					cakeArr[x] = num;
					piece++;
				}
			}

			// 실제로 가장 많은 조각을 가져간 방청객의 번호를 갱신
			if (piece > max2) {
				max2 = piece;
				realMaxNum = num;
			}

		}

		// 정답을 출력
		System.out.println(expectMaxNum);
		System.out.println(realMaxNum);

		sc.close();

	}

}
