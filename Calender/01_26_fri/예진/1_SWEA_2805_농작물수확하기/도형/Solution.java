package SWEA._2805_농작물수확하기;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc<=T;tc++) {
			int N = sc.nextInt();
			String[] strArr;
			int[][] arr = new int[N][N];
			for (int i = 0; i<N;i++) {				
				strArr = sc.next().split(""); // 다 붙어있음ㅠㅠ
				for (int j = 0; j<N;j++) {
					arr[i][j] = Integer.parseInt(strArr[j]); //String 배열을 Int 배열로 만들어줌
				}
			}
			
			int top = N/2; //가운데에서 동시 시작
			int bottom = N/2; 
			
			int left = 0; //왼쪽 끝
			int right = N-1; // 오른쪽 끝
			
			int sum = 0;
			
			while (true) {
				if(top ==-1) {
					break;
				}
				
				for (int i =left; i<=right;i++) {//맨처음 시작을 if 문 나누기가 귀찮았음
					sum+=arr[top][i];
					sum+=arr[bottom][i];
				}
				//쭈욱 업데이트
				left++;
				right--;
				top--;
				bottom++;
			}
			
			for (int i = 0; i<N;i++) { // 귀찮아서 한번 더 더해준거 빼줌
				sum-=arr[N/2][i];
			}
			
			System.out.printf("#%d %d%n",tc,sum);
			
		}
		sc.close();
	}
}
