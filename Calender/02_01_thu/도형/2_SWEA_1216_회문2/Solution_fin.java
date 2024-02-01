package SWEA_1216_회문2;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
	/**
	 * 주어진 arr를 행 우선 탐색해 매 행 마다 가장 긴 회문의 길이 (max) 를 업데이트하고,
	 * 최종적으로 모든 행에서 가장 긴 회문의 길이를 반환합니다.
	 * @param arr 주어진 arr
	 * @param c 구하고 싶은 회문의 길이가 짝수인지 홀수인지에 따라 시작하는 가장 작은 회문의 길이가 1 혹은 2로 변화합니다. 
	 * @return 가장 긴 회문의 길이
	 */
	public static int getMaxLength(char[][] arr, int c) {
		int max = 0; // 계속 업데이트 해나갈 길이 변수
		int left = 0; // 회문의 끝과 끝을 비교해나가면서 길이를 늘려갈텐데, 왼쪽 끝과 오른쪽 끝을 index 포인터로 관리합니다.
		int right = 0;
		int N = arr.length;
		for (int i = 0; i<N; i++) {
			for (int j = 0; j<N; j++) {
				left = j; // 초기 설정
				right = j+c; // 초기 설정. c가 0이면 두 포인터가 같은 곳에서 출발합니다. 즉, 홀수 길이의 회문을 조사합니다. c가 1이면 한칸 어긋난 상태로 출발합니다. 즉, 짝수 길이의 회문을 조사합니다.
				while (left>=0 && right<=N-1) {
					if (arr[i][left] == arr[i][right]) { // 양쪽 끝 포인터의 내용물이 같을 때. char 자료형이기 때문에 단순 == 비교가 가능합니다.
						max = Math.max(right-left+1,max); // 최대의 회문 길이를 update 하고
						left--; //다음 index를 조사합니다.
						right++; //다음 index를 조사합니다.
					} else {
						break; // 양쪽 끝 포인터의 내용물이 다르면, 회문은 더 이상 연장되지 못합니다.
					}
				}
			}
			
		}
		return max;
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int T = 10; 
		int N = 100;
		
		for (int tc = 1; tc<=T;tc++) {
			tc =sc.nextInt(); // #tc는 받아서 버립니다.
			sc.nextLine(); // 앞에 nextInt 가 받아온 개행문자가 버퍼에 남아있는데, nextLine()을 한 번 더 호출해 제거합니다. 
			
			char[][] arr = new char[N][];
			for (int i = 0; i<N;i++) {
				arr[i] = sc.nextLine().toCharArray();
			}
			
			// transpose, 작성된 getMaxLength 함수가 행 기준으로 탐색하기 때문에, 원래 주어진 arr를 뒤집을 필요가 있습니다.
			char[][] vert_arr = new char[N][N];
			for (int i = 0; i<N; i++) {
				for (int j = 0; j<N; j++) {
					vert_arr[j][i] = arr[i][j]; // 행과 열 index를 바꿔 저장하면 행렬이 뒤집힙니다.
				}
			}
			
			int max = 0;
			max = Math.max(max, getMaxLength(arr,0)); //arr 홀
			max = Math.max(max, getMaxLength(arr,1)); //arr 짝
			max = Math.max(max, getMaxLength(vert_arr,0)); //뒤집힌 arr 홀
			max = Math.max(max, getMaxLength(vert_arr,1)); //뒤집힌 arr 짝
			
			System.out.printf("#%d %d%n",tc,max);
			
		}
	}

}