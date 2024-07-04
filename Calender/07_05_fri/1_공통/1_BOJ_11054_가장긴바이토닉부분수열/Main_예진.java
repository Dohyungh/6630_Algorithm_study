/*
 * [풀이방법]
 * 가장 긴 증가하는 부분수열을 구하는 dp테이블을 양쪽 단방향으로 한번씩 각각 구한다.
 * 우향 증가표와 좌향 증가표의 각 인덱스를 더해서 그 합-1(겹치는 부분)이 가장 클 때가 가장 긴 바이토닉부분수열이다.
 * 
 * answer를 초기화 시키는 부분이 있는데, 이부분 초기화 숫자를 잘못 지정해서 가장긴증가하는, 바이토닉 부분수열 모두 틀렸었다.
 * 최소값이 1또는 2일거다 라고 생각해서 설정했었는데, 그냥 0으로 초기화 하는게 나을 것 같다.
 */

package boj_11054_가장긴바이토닉부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] incre = new int[N];	// 증가하는 부분수열
		int[] decre = new int[N];	// 감소하는 부분수열
		
		String[] tmp = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(tmp[i]);
			
			// 모든 인덱스 dp테이블 초기값은 1
			incre[i] = 1;
			
			// 입력받는대로 증가하는 부분수열 dp테이블 갱신
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					incre[i] = Math.max(incre[i], incre[j]+1);
				}
			}
		}
		
		// 답 초기화
		int answer = 0;
		
		// 감소하는 부분수열 dp테이블 갱신과 동시에 두 dp테이블의 합-1이 가장 큰 경우를 갱신
		// 같은 로직을 거꾸로 순회
		for (int i = N-1; i >= 0; i--) {
			decre[i] = 1;
			
			for (int j = N-1; j > i; j--) {
				if (arr[i] > arr[j]) {
					decre[i] = Math.max(decre[i], decre[j]+1);
				}
			}
			answer = Math.max(answer, incre[i] + decre[i] - 1);
		}
		
		System.out.println(answer);
		br.close();
	}
}