package 참외밭_2477_240209;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int K = sc.nextInt();		// 면적당 참외 개수
		int[] sides = new int[6];

		int firstIdx = 0;		// 가장 긴 길이
		int secondIdx = 0;		// 그 다음 길이
		int idxGap = 0;			// 뒤로 갈지 앞으로 갈지
		
		for (int i = 0; i < 6; i++) {
			sc.nextInt();
			int side = sc.nextInt();
			sides[i] = side;
			// 값 넣으면서 최대값 인덱스 저장
			if (sides[i] > sides[firstIdx]) firstIdx = i;
		}
		
		// 가장 긴 값 양쪽으로 누가 더 긴지 비교, 뭐가 더 긴지에 따라 인덱스 이동 방향 결정
		if (sides[(firstIdx-1+6)%6] > sides[(firstIdx+1)%6]) {
			secondIdx = (firstIdx-1+6)%6;
			idxGap = 1;
		}
		else {
			secondIdx = (firstIdx+1)%6;
			idxGap = -1;
		}
		
		// 바깥 변 두개를 하나씩 건너뛴 두개의 값
		int insideIdx_1 = (firstIdx+idxGap+idxGap+6) % 6;
		int insideIdx_2 = (insideIdx_1+idxGap+6) % 6;
		
		// 넓이 계산
		int large = (sides[firstIdx] * sides[secondIdx]) 
				- (sides[insideIdx_1] * sides[insideIdx_2]);
		
		// 면적당 참외 개수와 곱하기
		System.out.println(K*large);
		sc.close();
	}
}
