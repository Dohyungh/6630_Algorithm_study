package BOJ._14696_딱지놀이;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//정렬
		
		// counting 배열 : 0, 1,2,3,4
		
		int T = sc.nextInt();
		for(int tc = 1; tc <=T; tc++) {
			int [] countA = new int[5];
			int [] countB = new int[5];
			
			int A = sc.nextInt();
			for (int i = 0; i<A; i++) {
				countA[sc.nextInt()]++;
			}
			
			int B = sc.nextInt();
			for (int i = 0; i<B; i++) {
				countB[sc.nextInt()]++;
			}
			
			if (Arrays.equals(countA, countB)) {
				System.out.println('D');
				continue;
			}
			int[][] arr = new int[2][];
			arr[0] = countA;
			arr[1] = countB;
			//그냥 정렬이 하고 싶어서..
			Arrays.sort(arr, (o1,o2)->{
				if(o1[4]==o2[4] && o1[3] == o2[3] && o1[2]==o2[2]) {
					return o1[1] - o2[1];					
				}else if (o1[4] == o2[4] &&o1[3] == o2[3]) {
					return o1[2] - o2[2];
					
				}else if (o1[4] == o2[4]) {
					return o1[3] - o2[3];
					
				}else{
					return o1[4] - o2[4];
				}
			});
			
			// Arrays.equals 가 쓰고 싶어서..
			if (Arrays.equals(arr[1], countA)) {
				System.out.println('A');
			} else {
				System.out.println('B');
			}
		}
		
		
		
		sc.close();
	}

}
