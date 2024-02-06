package BOJ_10163_색종이;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		// 색종이를 올릴 평면 전체를 2차원 배열로 선언(1001 x 1001)
		int arr[][] = new int[1001][1001];

		// 놓여지는 색종이 순서에 따라 위에서 보여질 색종이의 면적을 확인하기 위한 번호 num선언
		int num = 0;
		
		// N개의 색종이만큼 for문을 반복
		for (int i = 0; i < N; i++) {
			// num을 1씩 증가
			num++;
			// 색종이의 정보는 총 4개
			// paper[0]은 왼쪽 아래 행번호, paper[1]은 왼쪽 아래 열번호, 나머지는 색종이의 크기
			int[] paper = new int[4];

			// 색종이의 정보 입력받기
			for (int k = 0; k < 4; k++) {
				paper[k] = sc.nextInt();
			}

			
			// 좌표 + 너비만큼, 즉 색종이의 크기만큼 해당하는 arr[idx][idx2]에 각 색종이의 번호, num을 할당
			// for문을 반복하며 색종이의 좌표가 겹친다면 번호는 뒤에 등장하는 색종이의 번호로 갱신될 것임
			for (int idx = paper[0]; idx < paper[0] + paper[2]; idx++) {
				for (int idx2 = paper[1]; idx2 < paper[1] + paper[3]; idx2++) {
					arr[idx][idx2] = num;
				}
			}

			

		}
		
		// 전체 색종이의 개수만큼 sum을 계산
		for (int n = 1; n <= num; n++) {
			// 번호별로 sum을 0으로 초기화
			int sum = 0;
			// arr를 탐색하며 각 번호에 맞는 면적을 계산
			for (int r = 0; r < arr.length; r++) {
				for (int c = 0; c < arr[0].length; c++) {
					if (arr[r][c] == n) {
						sum++;

					}
				}
			}
			System.out.println(sum);
		}
		
		sc.close();

	}

}
