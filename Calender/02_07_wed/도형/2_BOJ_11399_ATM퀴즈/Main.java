package BOJ._11399_ATM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//SPT
		//Shortest Processing Time
		
		//EDD (납기일 기준)
		
		//항상 입력은 가장 익숙한 방식으로 하는데
		//더 좋은 방법이 있을 것 같음.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] times = new int[N];
		String[] temp = br.readLine().split(" ");
		
		for (int i = 0; i<N; i++) {
			times[i] = Integer.parseInt(temp[i]);
		}
		
		//정렬이 핵심!
		Arrays.sort(times);
		
		//누적합의 누적
		int sum = 0;
		int ans = 0;
		for (int i = 0; i<N; i++) {
			sum += times[i];
			ans += sum;
		}
		
		System.out.println(ans);
		br.close();
	}

}
