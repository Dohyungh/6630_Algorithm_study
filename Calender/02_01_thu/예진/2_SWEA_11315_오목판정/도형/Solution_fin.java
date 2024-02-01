package SWEA._11315_오목판정;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_fin {
	public static int tf_r(int i, int j) {
		return ((i-j)/2);
		
	}
	public static int tf_c (int i , int j) {
		return ((i+j)/2);
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			char[][] arr = new char[N][];
						
			for (int i = 0; i < N; i++) {
				arr[i] = br.readLine().toCharArray();
			}
			
			boolean five = false;
			
			//가로
			int count;
			for (int i = 0; i<N; i++) {
				count = 0; // 행 넘어가면 자동 리셋
				int j = 0;
				while (j<N) {// 2. &&!five 조건 넣어주니까 통과함. 오목의 개수가 짝수개일 때 계속 다시 뒤집으니까 문제였던 듯. 
					if (arr[i][j] == 'o') {
						count++;
						if (count == 5) {
							five = true; // 3. 그러니까 이걸 그냥 five = true; 로 했으면 됐음. !five 가 아니라. 
							//4. 스위치 켜고 끄는 거 같이 여러번 뒤집는 게 아니면 그냥 true나 false로 지정하자.
							break; //1. break 가 문제였음
						}
						
					} else {
						count = 0;
					}
					
					j++;
				}
			}
			//세로
			for (int j = 0; j<N; j++) {
				count = 0; // 열 넘어가면 자동 리셋
				int i = 0;
				while (i<N) {
					if (arr[i][j] == 'o') {
						count++;
						if (count == 5) {
							five = true;
							break;
						}
						
					} else {
						count = 0;
					}
					
					i++;
				}
			}
			
			//대각 / 방향
			int left = 0;
			int right = 0;
			int i = 0;
			count = 0;
			while (i<2*N-1) {
				count = 0;
				for (int j =left; j<=right; j+=2) {
					if (arr[tf_r(i,j)][tf_c(i,j)] == 'o') {
						count++;
						if (count == 5) {
							five = true;
							break;
									
						}
					} else {
						count = 0;
					}
					
				}
				if (i<N-1) {
					left--;
					right++;
					
				} else {
					left++;
					right--;
				}
				i++;
				
			}
			
			//대각 \ 방향
			int top = N;
			int bottom = N;
			int k = -N+1;
			count = 0;
			while (k<=N-1) {
				count = 0;
				for (int l =bottom; l<=top; l+=2) {
					if (arr[tf_r(l,k)][tf_c(l,k)] == 'o') {
						count++;
						if (count == 5) {
							five = true;
							break;
							
						}
					} else {
						count = 0;
					}
					
				}
				if (k<0) {
					bottom--;
					top++;
					
				} else {
					bottom++;
					top--;
				}
				k++;
				
			}

			
			if (five) {
				System.out.printf("#%d %s%n",tc,"YES");
			} else {
				System.out.printf("#%d %s%n",tc,"NO");
			}
			
			
		}
	}
}