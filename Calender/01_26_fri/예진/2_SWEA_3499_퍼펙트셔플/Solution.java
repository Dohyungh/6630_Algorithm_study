package 퍼펙트셔플_3499_240125;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("src/input.txt"));
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = Integer.parseInt(bf.readLine());
			String input = bf.readLine();
			String[] arr = input.split(" ");
			
			System.out.print("#"+test_case+" ");
			if (N%2 == 0) {
				for (int i = 0; i < N/2; i++) {
					System.out.print(arr[i]+" "+arr[N/2+i]+" ");
				}
			} else {
				for (int i = 0; i < (N/2); i++) {
					System.out.print(arr[i]+" "+arr[(N/2+1)+i]+" ");
				}
				System.out.print(arr[N/2]);
			}
			System.out.println();
		}
		bf.close();
	}
}
