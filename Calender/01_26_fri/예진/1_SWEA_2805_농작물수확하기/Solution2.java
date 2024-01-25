package 농작물수확하기_2805_240124;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2 {

	public static void main(String[] args) throws IOException {
		
		 System.setIn(new FileInputStream("src/input.txt"));
		
		// 버퍼드리더 사용법 익히기 - 입력받을 값이 많아질수록 효율차이가 심함
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine()); // BufferedReader로 받으면 String으로 밖에 못받아서 형변환 필수
		
		for(int test_case = 1; test_case <= T; test_case++) {
		
			int N = Integer.parseInt(bf.readLine()); // 그냥 read 했다가 다음 입력때 빈문자열이 입력돼서 에러났었음
			int[][] arr = new int[N][N];
			int ans = 0;
			
			// StringTokenizer 사용해봄
			for(int r=0; r<N; r++) {
				String input = bf.readLine();  // 한줄씩 통째로 문자열을 입력받음
				StringTokenizer st = new StringTokenizer(input, "0123456789", true);
				for(int c=0; c<N; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 각 열 인덱스의 시작과 끝을 변수로 제한하도록 함. 이 변수를 변화시키면서 마름모꼴 만들거임
			int start = N/2; // 배열 길이의 1/2이 정확히 중앙 인덱스. 마름모는 중앙부터 시작
			int end = N/2;
			int[] dr = {-1, 1}; // 델타값 생성
			
			for (int r = 0; r < N; r++) {
				for (int c = start; c <= end; c++) 
					ans += arr[r][c]; // 인덱스 안에 해당하는 값들을 ans에 합한다
				
				// 한 행을 순환할 때마다 시작지점과 끝지점을 재설정 하도록 함.
				// 해당 행이 배열의 반보다 위에 있으면 시작지점은 -1, 끝지점은 +1 한다.
				if (r < N/2) {
					start += dr[0];
					end += dr[1];
				} else {  // 해당 행이 배열의 반부터 그 아래이면 시작지점은 +1, 끝지점은 -1한다.
					start += dr[1];
					end += dr[0];
				}
			}
			System.out.printf("#%d %d\n", test_case, ans);
		}
		bf.close();
	}
}
