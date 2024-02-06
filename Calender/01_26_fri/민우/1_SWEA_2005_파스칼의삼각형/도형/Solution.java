package SWEA._2005_파스칼의삼각형;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

//		index놀이를 해야겠다 (점화식) -> 복잡해보이면 그냥 일단 써보자!
//		21 10+11
//		
//		31 20+21
//		32 21+22
//		
//		41 30+31
//		42 31+32
//		43 32+33
//		x[i][j] = x[i-1][j-1]+ x[i-1][j]
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc<=T; tc++) {
			int N = sc.nextInt();
			
			int[][] arr = new int[N][];
			for(int j = 1; j<=N;j++) { //j에 각 행의 length를 넣어주자. length = (행 index)+1 인 것에 유의
				int[] temp_arr = new int[j];
				for(int i = 0; i<j;i++) { //i는 무조건 0부터 (행 index) 까지
					if (i==0 || i==j-1) { //항상 점화식에 경계조건은 따로
						temp_arr[i] = 1;
					} else temp_arr[i] = arr[j-2][i-1]+arr[j-2][i]; // 점화식 temp_arr는 [j-1]행
				arr[j-1] = temp_arr;
				}	
			}
			
			System.out.printf("#%d%n", tc); //tc 출력
			
			for(int i = 0; i<arr.length;i++) {
				for(int j = 0; j<arr[i].length;j++) {
					System.out.printf("%d ",arr[i][j]); //2칸확보 왼쪽부터
				}
				System.out.println();
			}
		}
		
	}
	
}
