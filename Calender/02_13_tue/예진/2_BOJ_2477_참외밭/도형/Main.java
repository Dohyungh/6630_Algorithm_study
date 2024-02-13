package BOJ._2477_참외밭;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[][] distMatrix = new int[6][6]; // 거리 행렬 생성
		
		int K = sc.nextInt();
		

		int[] lens = new int[6];
		int max1 = 0;
		int max2 = 0;
		for (int i = 0; i < 6; i++) {
			int dir = sc.nextInt();
			int len = sc.nextInt();
			if (dir==1 || dir==2) {  // 방향이 1, 2 인 것 중에 최댓값
				max1 = Math.max(max1, len);
			}
			if (dir==3 || dir==4) { // 방향이 3, 4 인 것 중에 최댓값
				max2 = Math.max(max2, len);
			}
			
			lens[i] = len; // 이 lens 배열에서 값을 받아서 거리행렬에 입력할 것임.
		}
		
		// 거리행렬 입력, 각꼭짓점이 몇번인지는 상관없음.
		// 어떤 변에 붙어있는지만 중요함.
		for(int i = 0; i < 6; i++) {
			distMatrix[i][(i+1)%6] = lens[i]; //입력!
		}
		distMatrix[0][5] = distMatrix[5][0]; //예외 처리하기 귀찮음!
		for(int i = 1; i < 6; i++) { // 1부터 그냥 대칭성 이용해서 채워넣어줌.
			distMatrix[i][i-1] = distMatrix[i-1][i];	
		}

		int topIndex = -1;
		for (int i = 0; i < 6; i++) {
			int sum = 0;
			for (int j = 0; j < 6; j++) {
				sum += distMatrix[i][j];			
			}
			if (sum == max1+max2) topIndex = i; // 거리행렬에서 행 원소의 합이 max1+max2랑 같으면, 그게 top
			
			
		}
		
		int bottomIndex = (topIndex+3) %6; // bottom 인덱스 계산
		
		int cut = 1; // 작은 변 2개 곱해줄 것임. 따라서 0이 아니라 1로 초기화
		for (int j = 0; j < 6; j++) {
			if (distMatrix[bottomIndex][j] !=0) { // 0 은 곱해주면 안되고
				cut *=distMatrix[bottomIndex][j]; // 0아닌 2개를 곱해주면 넓이가 나옴.
			}
		}
		
		System.out.println(K *(max1*max2 - cut));
		sc.close();
		
		
		
		
	}

}