package 일곱난쟁이_2309_240209;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int[] dwarf = new int[9];			// 난쟁이들 키
		boolean[] fake = new boolean[9];	// 가짜 난쟁이 판별
		
		// 난쟁이들 키 입력
		for (int i = 0; i < 9; i++) {
			dwarf[i] = sc.nextInt();
		}
		
		Arrays.sort(dwarf);		// 오름차순 정렬
		
		// 7명씩만 더해볼거니까 더하지 않을 난쟁이 2명을 2중반복문으로 설정
		out:
		for (int i = 0; i < 8; i++) {
			for (int j = i+1; j < 9; j++) {
				
				// i번째, j번째 난쟁이 빼고 키를 더해봐서
				int sum = 0;
				for (int k = 0; k < 9; k++) {
					if (k != i && k != j) sum += dwarf[k];
				}
				
				// 합이 100이면 가짜 난쟁이 표시하고 반복 아웃!
				if (sum == 100) {
					fake[i] = true;
					fake[j] = true;
					break out;
				}
			}
		}
		
		// 가짜 난쟁이 빼고 키 출력
		for (int i = 0; i < 9; i++) {
			if (!fake[i]) {
				System.out.println(dwarf[i]);
			}
		}
		sc.close();
	}
}
