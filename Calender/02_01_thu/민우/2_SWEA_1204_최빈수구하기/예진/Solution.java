package swea_1204_최빈수구하기;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {
		
//		File file = new File("src/swea_1204_최빈수구하기/input.txt");
//		Scanner sc = new Scanner(file);
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int caseNum = sc.nextInt();          // 테스트 케이스 넘버 입력
			int[] count = new int[100+1];        // 점수에 따른 카운드 배열 생성 (점수는 0이상 100이하)
			int max = 0;                         // 인덱스 0 부터 비교하여 최빈수 찾기
			
			// 학생 수 1000명의 점수 카운팅
			for (int s = 0; s < 1000; s++) {
				count[sc.nextInt()]++;
			}
			
			// 카운팅 배열에서 최대값 구하기.
			for (int i = 1; i <= 100; i++) {
				if (count[i] == count[max]) {    // 단, 빈도수가 같으면
					if (i > max) max = i;        // 점수가 높은 것을 선택
				} else if (count[i] > count[max]) max = i;   // 최빈수 갱신
			}
			// 출력
			System.out.printf("#%d %d\n", caseNum, max);
		}
		sc.close();
	}
}