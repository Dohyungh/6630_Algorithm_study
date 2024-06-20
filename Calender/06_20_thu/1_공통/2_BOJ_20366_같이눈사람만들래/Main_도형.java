package BOJ._20366_같이눈사람만들래;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		List<int[]> coupleArr = new ArrayList<int[]>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i < j) {
					coupleArr.add(new int[] {i, j, arr[i]+arr[j]});
				}
			}
		}
		
		Collections.sort(coupleArr, (o1, o2) -> {return o1[2] - o2[2];});
		
		int answer = Integer.MAX_VALUE;
		
		for (int i = 0 ; i <coupleArr.size(); i++) {
			int[] a = coupleArr.get(i);
			
			for (int j = i+1; j < coupleArr.size(); j++) {
				int[] b = coupleArr.get(j);
				
				if (a[0] != b[0] && a[0] != b[1] && a[1] != b[0] && a[1] != b[1]) {
					answer = Math.min(answer, Math.abs(a[2] - b[2]));
					break;
				}
			}
		}
		System.out.println(answer);
		
		sc.close();
		
	}

}
