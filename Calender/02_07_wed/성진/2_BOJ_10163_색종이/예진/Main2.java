package 색종이_10163_240207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] paper = new int [N][5];
		boolean[][] isCovered = new boolean[1001][1001];		// 가려진 영역을 체크하는 전체 면적
		
		// 색종이 위치 및 너비 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 가장 위에 있는 종이부터 종의의 면적을 순회하며 덮여있지 않은 부분의 면적만 더한다.
		// 인덱스 문제
		for (int i = N-1; i >= 0; i--) {
//			틀린거
//			for (int r = paper[i][0]+1; r <= paper[i][0]+paper[i][2]; r++) {
//				for (int c = paper[i][1]+1; c <= paper[i][1]+paper[i][3]; c++) {
			for (int r = paper[i][0]; r < paper[i][0]+paper[i][2]; r++) {
				for (int c = paper[i][1]; c < paper[i][1]+paper[i][3]; c++) {
					if (!isCovered[r][c]) {
						paper[i][4]++;
						isCovered[r][c] = true;
					}
				}
			}
		}
		
		// 출력
		for (int i = 0; i < N; i++) {
			System.out.println(paper[i][4]);
		}
		br.close();
	}
}
