package BOJ._2533_사회망서비스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		List<Integer>[] adj = new List[N+1];
		for (int i = 1; i < N+1; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		int[] degree = new int[N+1];

		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int no = Integer.parseInt(st.nextToken());
			int de = Integer.parseInt(st.nextToken());
			
			adj[no].add(de);
			adj[de].add(no);
			degree[no]++;
			degree[de]++;
		}
		int answer = 0;

		while(true) {
			boolean flag = true;
			// 여기부터
			for (int i = 1; i <= N; i++) {
				if (degree[i] == 1) {
					// 여기까지를 우선순위큐로 개선할 것임
					answer++;
					
					
					int adopt = -1;
					for (int connectedNode : adj[i]) {
						if (degree[connectedNode] > 0) {
							adopt = connectedNode;
							break;
						}
					}
					
					degree[adopt] = 0;
					for (int connectedNode : adj[adopt]) {
						degree[connectedNode]--;
					}
					flag = false;
				}
			}
			if (flag) break;
		}
		System.out.println(answer);
		
		
		
		br.close();
	}

}
