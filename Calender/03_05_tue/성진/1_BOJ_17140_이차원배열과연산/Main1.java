package boj_17140_이차원배열과연산;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main1 {

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt()-1;
		int c = sc.nextInt()-1;
		int k = sc.nextInt();
		
		int[][] arr = new int[100][100];
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		operation(arr[0]);
		
		
		
		
		
		
	}
	
	
	static void operation(int[] arr) {
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (int i=0; i<arr.length; i++) {
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], 1);
			}
			else {
				int tmp = map.get(arr[i]);
				map.put(arr[i], ++tmp);
			}
		}


		for (int value:map.values()) {
//			System.out.println(map.);
		}
		
	}
	
	
	
}
