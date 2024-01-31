package SWEA._1216_회문2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution2 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 10; 
		int N = 100;
		
		for (int tc = 1; tc<=T;tc++) {
			bf.readLine(); // tc는 버리고
			char[][] arr = new char[N][];
			
			for (int i = 0; i<N;i++) {
				arr[i] = bf.readLine().toCharArray();
			}
			
			int[] new_arr = new int[N];
			
			int max = 0;
			for(int j = 0; j<N;j++) {
				for (int i =0; i<N-1; i++) {
					if (arr[j][i] == arr[j][i+1]) {
						new_arr[i] = 2;
					} else { ㅡ
						new_arr[i] = 1;
					}
				}
	//			System.out.println(Arrays.toString(new_arr));
				
	// 여기만 보고 넘어가자
				int flag = 1;
				int cycle = 1; // 이전 사이클이 한 사이클
				while (flag == 1) {
					flag = 0;
					cycle++;
					
					for (int i = 1; i<N; i++) {
						// 조사하는 i의 현재 가장 긴 회문의 길이에서  +1 한 곳과 i-1을 비교.
						if (i+new_arr[i] <N && (new_arr[i]==2*cycle-3 || new_arr[i]==2*cycle-2)) {
							if (arr[j][i-1]==arr[j][i+new_arr[i]]) {
								new_arr[i-1] +=2;
								
								flag = 1;
								max = Math.max(max, new_arr[i-1]);
								// 웬만한 case 맞는 것은 확인.
								System.out.println(Arrays.toString(new_arr));
								System.out.println(max);
							}
						}
					}
				}
			}
//			
//			for (int i =0; i<N; i++) {
//				char[] vertical_arr = new char[N];
//				for (int j = 0; j<N; j++) {
//					vertical_arr[j] = arr[j][i];
//				}
//				
//
//				for (int k =0; k<N-1; k++) {
//					if (arr[k][i] == arr[k+1][i]) {
//						new_arr[k] = 2;
//					} else {
//						new_arr[k] = 1;
//					}
//				}
//	//			System.out.println(Arrays.toString(new_arr));
//				
//				int flag = 1;
//				int cycle = 1; // 이전 사이클이 한 사이클
//				while (flag == 1) {
//					flag = 0;
//					cycle++;
//					
//					for (int l = 1; l<N; l++) {
//						if (i+new_arr[l] <N && (new_arr[l]==2*cycle-3 || new_arr[l]==2*cycle-2)) {
//							if (arr[l-1][i]==arr[l+new_arr[l]][i]) {
//								new_arr[l-1] +=2;
//								flag = 1;
//								max = Math.max(max, new_arr[l-1]);
//	//							System.out.println(Arrays.toString(new_arr));
//	//							System.out.println(max);
//							}
//						}
//					}
//				}
//			}
//				
				
			
			System.out.println(max);
			
		}
	}	
}
