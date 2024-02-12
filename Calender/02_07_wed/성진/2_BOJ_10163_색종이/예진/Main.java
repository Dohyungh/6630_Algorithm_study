package 색종이_10163_240207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] paper = new int [N][5];
		int[][] large = new int[1001][1001];
		
		// 색종이 위치 및 너비 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 가장 위에 있는 종이부터 종의의 면적을 순회하며 덮여있지 않은 부분의 면적만 더한다.
		for (int i = 1; i <= N; i++) {
			for (int r = paper[i-1][0]+1; r <= paper[i-1][0]+paper[i-1][2]; r++) {
				for (int c = paper[i-1][1]+1; c <= paper[i-1][1]+paper[i-1][3]; c++) {
					large[r][c] = i;
				}
			}
		}
		
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				paper[large[i][j]][4]++;
			}
		}
		
		// 출력
		for (int i = 0; i < N; i++) {
			System.out.println(paper[i][4]);
		}
		br.close();
	}
}
