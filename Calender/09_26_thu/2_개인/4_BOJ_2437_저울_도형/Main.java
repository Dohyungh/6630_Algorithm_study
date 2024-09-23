package BOJ._2437_저울;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] arr = new int[N];
		
		
		for (int i = 0; i < N; i++ ) {
			arr[i] = sc.nextInt();
		}
		
		// 추의 무게를 정렬해서 스트릭이 끊기는 지점을 찾자..
		Arrays.sort(arr);
		
		// 새로운 추가 생기면, 해당 추의 무게 부터 그 이전까지의 모든 만들 수 있는 무게 스트릭에 + (추가된 추의 무게)를 해줘야 한다.
		// 추를 정렬해 놓았기 때문에,
		// 그 이전의 스트릭의 최댓값이 추가된 추의 무게보다 작은 순간 못 만드는 무게이다. 
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i] - max > 1) break;
			max += arr[i];
		}
		System.out.println(max + 1);
	}

}
