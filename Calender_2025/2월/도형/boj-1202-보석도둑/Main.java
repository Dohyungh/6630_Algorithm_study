package BOJ._1202_보석도둑;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] jams = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			jams[i][0] = Integer.parseInt(st.nextToken());
			jams[i][1] = Integer.parseInt(st.nextToken());
			
			
		}
		
		// 무게로 정렬
		// 사실상 무게를 오름차순으로 바라봐야 중복검사를 안할 수 있음
		Arrays.sort(jams, (o1, o2) -> o1[0] - o2[0] );
		
		int[] bags = new int[K];
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bags);
		
		
		// 대신에 PQ 에 넣을 때 가치에 내림차순으로 "뽑"는 것임
		// 즉, 넣는 기준과 뽑는 기준을 다르게 해서 시간복잡도를 줄이는 메커니즘
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {				
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[1] - o1[1];
			}
		});
		
		int jamPointer = 0;
		long answer = 0;
		for (int i = 0; i < K; i++) {
			while(jamPointer < N && jams[jamPointer][0] <= bags[i]) {
				pq.add(jams[jamPointer++]);
			}
			
			if(!pq.isEmpty()) answer += pq.poll()[1];
			
			
		}
		System.out.println(answer);
		
	}

}
