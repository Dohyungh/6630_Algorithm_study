package boj_20366_같이눈사람만들래;

import java.util.*;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 눈덩이의 개수 입력 받기
		int N = sc.nextInt();

		// 눈덩이 크기를 저장할 배열
		int[] snowArr = new int[N];

		// 눈덩이 크기 입력 받기
		for (int i = 0; i < N; i++) {
			snowArr[i] = sc.nextInt();
		}
		
		// 눈덩이 크기 배열을 오름차순으로 정렬
		Arrays.sort(snowArr);

		// 최소 차이를 저장할 변수 초기화
		int answer = Integer.MAX_VALUE;

	
		// 두 눈사람을 만들 수 있는 모든 조합을 확인
		// 눈덩이 크기 배열을 정렬했으므로 (1) (2) (2) (1)로 눈사람을 만들 수 있을 것
		// l1, r1, l2, r2에 해당하는 인덱스를 적절히 조정해서 표현하기!
		for (int l1 = 0; l1 < N - 3; l1++) {
			for (int r1 = N - 1; r1 > l1 + 2; r1--) {
				for (int l2 = l1 + 1; l2 < r1 - 1; l2++) {
					for (int r2 = r1 - 1; r2 > l2; r2--) {

						// 첫 번째 눈사람의 높이 계산
						int height1 = snowArr[l1] + snowArr[r1];
						// 두 번째 눈사람의 높이 계산
						int height2 = snowArr[l2] + snowArr[r2];

						// 두 눈사람의 높이 차이의 절대값을 구하고, 최소값 갱신
						answer = Math.min(Math.abs(height1 - height2), answer);

						// 높이 차이가 0이면 더 이상 계산할 필요가 없으므로 즉시 종료
						if (answer == 0) {
							System.out.println(0);
							return;
						}
					}
				}
			}
		}

		// 모든 조합을 계산한 후 최소 차이를 출력
		System.out.println(answer);

	}
}
