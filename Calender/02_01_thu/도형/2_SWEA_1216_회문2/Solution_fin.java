package SWEA._1216_회문2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_fin {
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
			
			int left = 0;
			int right = 0;
			int max = 0;
			for (int i = 0; i<N; i++) {
				for (int j = 0; j<N; j++) {
					left = j;
					right = j;
					while (left>=0 && right<=N-1) {
						if (arr[i][left] == arr[i][right]) {
							max = Math.max(right-left+1,max);
							left--;
							right++;
						} else {
							break;
						}
					}
				}
				
			}
			for (int i = 0; i<N-1; i++) {
				for (int j = 0; j<N; j++) {
					left = j;
					right = j+1;
					while (left>=0 && right<=N-1) {
						if (arr[i][left] == arr[i][right]) {
							max = Math.max(right-left+1,max);
							left--;
							right++;
						} else {
							break;
						}
					}
				}
				
			}
			
			char[][] vert_arr = new char[N][N];
			for (int i = 0; i<N; i++) {
				for (int j = 0; j<N; j++) {
					vert_arr[j][i] = arr[i][j];
				}
			}
			
			for (int i = 0; i<N; i++) {
				for (int j = 0; j<N; j++) {
					left = j;
					right = j;
					while (left>=0 && right<=N-1) {
						if (vert_arr[i][left] == vert_arr[i][right]) {
							max = Math.max(right-left+1,max);
							left--;
							right++;
						} else {
							break;
						}
					}
				}
				
			}
			for (int i = 0; i<N-1; i++) {
				for (int j = 0; j<N; j++) {
					left = j;
					right = j+1;
					while (left>=0 && right<=N-1) {
						if (vert_arr[i][left] == vert_arr[i][right]) {
							max = Math.max(right-left+1,max);
							left--;
							right++;
						} else {
							break;
						}
					}
				}
				
			}
			System.out.printf("#%d %d%n",tc,max);
			
		}
	}

}
