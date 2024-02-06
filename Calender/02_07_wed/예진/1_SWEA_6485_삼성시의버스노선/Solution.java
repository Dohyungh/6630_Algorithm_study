package 삼성시의버스노선_6485_240205;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		
//		File file = new File("src/삼성시의버스노선_6485_240205/input.txt");
//		Scanner sc = new Scanner(file);
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			// 버스노선이 갈 수 있는 최대 버스정류장 개수
			int[] busLine = new int[5001];
			
			// 각 버스 노선 별 지나가는 버스정류장 카운트 +1
			int N = sc.nextInt();
			for (int i = 0; i < N; i++) {
				int Ai = sc.nextInt();
				int Bi = sc.nextInt();
				for (int j = Ai; j <= Bi; j++) {
					busLine[j]++;
				}
			}
			
			// 지정된 버스 정류장의 노선 수 저장
			int P = sc.nextInt();
			int[] busStop = new int[P];
			for (int i = 0; i < P; i++) {
				busStop[i] = busLine[sc.nextInt()];
			}
			
			// 출력
			System.out.printf("#%d ",tc);
			for (int lineCnt : busStop) {
				System.out.print(lineCnt + " ");
			}
			System.out.println();
		}
		sc.close();
	}
}
