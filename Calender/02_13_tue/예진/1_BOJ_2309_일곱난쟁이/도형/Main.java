package BOJ._2309_일곱난쟁이;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int[] nanjangs = new int[9];
		
		
		int sum = 0;
		for (int i = 0; i<9; i++) {
			int a = sc.nextInt();
			nanjangs[i] = a;
			sum +=a;
		}
		Arrays.sort(nanjangs);
		out:
		for (int i = 0; i<9; i++) {
			for (int j = 0; j<9; j++) {
				if (i!=j) {
					int minus = nanjangs[i] + nanjangs[j];
					if ((sum - minus) ==100) {
						for (int k = 0 ; k < 9; k++) {
							if (k ==i || k==j) continue;
							System.out.println(nanjangs[k]);
							
						}
						break out;
						
					}
				}
				
				
			}
		}
		
		
		
	}

}
