package BOJ._1244_스위치켜고끄기;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_fin {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int N = sc.nextInt();
	int[] li = new int[N];
	for (int i = 0; i<N; i++) {
		li[i] = sc.nextInt();
		
	}
	int num = sc.nextInt();
	for (int i = 0; i<num;i++) {
		int gender = sc.nextInt();
		int index = sc.nextInt()-1;

		if (gender ==1) {
			for (int j =0;j<N;j++) {
				if ((j+1)%(index+1)==0) {
					li[j] = 1-li[j];
				}
			}

		} else if (gender ==2) {
			int start = index-1;
			int end = index+1;
			li[index] = 1-li[index];
			
			while ( start>=0&& end<N&&li[start] == li[end]) {
				li[start] = 1-li[start];
				li[end] = 1-li[end];
				start--;
				end++;

			}

		}
	}
	for (int k=1; k<N+1;k++) {
		
		if (k%20 ==0) {
			System.out.printf("%d\n",li[k-1]);

		} else if(k==N) {
					
			System.out.printf("%d",li[k-1]);
			
		} else {
			System.out.printf(li[k-1]+" ");
		}
	}
	
	sc.close();
}
}
