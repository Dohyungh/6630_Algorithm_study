package BOJ._2170_선긋기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		// 안쓰면 시간초과나는 익숙한 맛
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 시작 0열, 끝 1열 
		long[][] arr = new long[N][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Long.parseLong(st.nextToken());
			arr[i][1] = Long.parseLong(st.nextToken());
		}
		
		// 시작점 기준 정렬
		Arrays.sort(arr, (o1, o2) -> {return Long.compare(o1[0], o2[0]);} );
		
		// 어디부터
		long start = arr[0][0];
		
		// 어디까지 인지를 계속 저장
		long end = arr[0][1];
		
		// 선분의 길이의 합
		long answer = 0;
		
		for (int i = 1; i < N; i++) {
			
			// 다음 선분의 시작점이 이전의 끝점 이후면
			if (arr[i][0] > end) {
				
				// 선분이 끊겼으니까 그때까지의 길이를 더해주고
				answer +=(end - start);
				
				// 시작점과 끝점을 재설정
				start = arr[i][0];
				end = arr[i][1];
				continue;
			}
			
			// 아니면 끝점만 업데이트 해준다.
			// 끝점이 이전 끝점보다 작을 수도 있기 때문에 무조건 업데이트 하지말고 둘 중에 큰 값으로 업데이트 하자.
			end = Math.max(end,arr[i][1]);
			
		}
		
		// 다 돌고 나왔을 때 정답 한번더 업데이트
		answer += (end-start);
		
		System.out.println(answer);
		
		
		
	}

}
