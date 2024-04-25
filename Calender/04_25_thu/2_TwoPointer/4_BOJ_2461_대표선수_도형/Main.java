package BOJ._2461_대표선수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// 버퍼드 안쓰면 시간초과
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		
		int[][] q = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int j = 0;
			
			while(st.hasMoreTokens()) {
				q[i][j++] = Integer.parseInt(st.nextToken());
			}
		}
		// 여기까지 입력!
		
		// 투포인터는 종종 정렬이 필요한데,
		// 정렬의 시간 복잡도가 O(N * logN) 이니까 내가 하던 것보다 정렬이 싸다면 시도해 볼 만하다 할 수 있겠지
		for (int i = 0; i < N; i++) {
			Arrays.sort(q[i]);
		}
		
		
		int min = Integer.MAX_VALUE;
		int minIdx= -1;
		
		int max = Integer.MIN_VALUE;
		
		int answer = Integer.MAX_VALUE;
		
		int[] idx = new int[N];
		
		out:
		while (true) {
			
			// 한 사이클 시작시에 min, max를 초기화
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			// 각 반을 돌면서 min, max 값 찾기
			// 잊지말고 minIdx도 같이 찾아주기
			for (int i = 0; i < N; i++) {
				int val = q[i][idx[i]];
				if (val < min) {
					min = val;
					minIdx = i;
				}
				if (val > max) {
					max = val;
				}
			}
			
			// 정답 업데이트하기
			answer = Math.min(answer, max - min);
			
			// 제일 작은 대표가 들어 있는 반의 idx를 ++
			
			idx[minIdx]++;
			
			// 증가시킨 idx가 indexOutOfBounds error를 일으키기 전에 break
			if (idx[minIdx] >= M) break out;
			
		}
		
		System.out.println(answer);
		
	}

}
