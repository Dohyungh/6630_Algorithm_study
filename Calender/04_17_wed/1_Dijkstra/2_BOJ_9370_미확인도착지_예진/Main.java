/*
 * [풀이 방법]
 * 1. 그래프의 인접 상태와 가중치를 입력받아 저장한다.
 * 2. 시작점으로부터 다익스트라를 실행한다. (각 노드까지의 거리 저장)
 * 3. 반드시 지나야 하는 거리의 양끝점 중 더 먼곳을 재시작점으로 지정한다. 
 *  (필수 거리를 지난 후 거리 재기, 
 *   시작점부터 특정 지점까지의 최단거리가 여러개일 수 있고, 
 *   필수거리를 반드시 지나야 하기 때문에 2차 다익스트라 필요)
 * 4. 재시작점으로부터 다익스트라를 실행한다.
 *  (출발점에서 재시작점 까지의 거리 + 재시작점에서 도착지까지의 거리 == 출발점에서 도착점까지의 거리 이면,
 *  출발점 -> 필수거리 -> 도착지의 경로가 최단거리라는 의미다)
 *  
 * 5. 도착지 후보들 중 두 다익스트라 결과를 비교해 최단거리가 같으면 답에 추가
 * 6. 도착가능 후보지 리스트를 오름차순으로 정렬하여 출력
 *  
 * [이슈]
 * PriorityQueue 자료구조에 대한 이해 부족..
 * 아직도 이해안됨 근데..
 * 
 * 처음엔 g와 h의 출발점으로부터의 거리가 같을 때를 고려하지 않아서 생기는 문제라고 생각했다.
 * 하지만, 문제에서 g와 h의 가중치가 같은 경우는 주어지지 않는 듯 하다. (지워도 통과함)
 * 
 * 두번째는 후보지점에 도착할 수 없는 경우를 고려하지 않아서인줄 알았다.
 * (초기값이 갱신되지 않으면 도착못하는데 마지막에 비교할 때 값이 같을테니까?)
 * 하지만, 문제에서 도착점에 도착할 수 없는 경우는 주어지지 않는 듯 하다. (지워도 통과함)
 * 
 * 문제는 PriorityQueue의 정렬 기준 문제였다.
 * 정렬 기준을 (o1, o2) -> {return fromSt[o1] - fromSt[o2];}
 * 이렇게 작성해뒀는데, 큐에 넣은 객체 자체값으로 비교하교 정렬하는게 아니라
 * 다른 배열을 참조해서 값을 비교하는 게 문제였다. 
 * 큐에 넣는 시점에 순서가 정해져야 하는데, 저렇게 하면 넣어둔 상태에서 참조한 배열 값이 변하면
 * 우선순위 큐 내부 정렬도 계속 변화한다는 것이다. 
 * 
 * 그렇구나 하고 이해는 되는데, 주어진 테케가 다 맞기도 하고 
 * 어차피 값이 갱신되면 그 값도 큐에 들어오는데 뭐가 다른건가 싶다.
 * 찾은 테스트케이스에서는 해당 예외를 찾을 수 없었다.
 * 
 * ***하지만 앞으로 정렬 기준을 정할때는 변할 수 있는 다른 객체를 참조하지 말 것.***
 * 
 */
package boj_9370_미확인도착지;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	static final int INF = 20_000_000;
	static int[] fromSt;
	static int[] fromRe;
	static boolean[] visited;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			
			int n = sc.nextInt();	// 교차로 수
			int m = sc.nextInt();	// 도로 수
			int t = sc.nextInt();	// 목적지 후보 수
			
			int st = sc.nextInt();
			int g = sc.nextInt();
			int h = sc.nextInt();
			
			int[][] adj = new int[n+1][n+1];
			
			for (int i = 0; i < m; i++) {
				int A = sc.nextInt();
				int B = sc.nextInt();
				int W = sc.nextInt();
				
				adj[A][B] = W;
				adj[B][A] = W;
			}
			int[] dep = new int[t];
			
			for (int i = 0; i < t; i++) {
				dep[i] = sc.nextInt();
			}
			
			fromSt = new int[n+1];
			visited = new boolean[n+1];
			
			PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
				return o1[1] - o2[1];
			});
			
			
			for (int i = 1; i <= n; i++) {
				fromSt[i] = INF;
			}
			
			fromSt[st] = 0;
			queue.add(new int[] {st, fromSt[st]});
			
			while (!queue.isEmpty()) {
				int curr = queue.poll()[0];
				
				if (visited[curr]) continue;
				visited[curr] = true;
				
				for (int i = 1; i <= n; i++) {
					if (adj[curr][i] != 0 && !visited[i] && 
							fromSt[i] > fromSt[curr] + adj[curr][i]) {
						fromSt[i] = fromSt[curr] + adj[curr][i];
						queue.add(new int[] {i, fromSt[i]});
					}
				}
			}
			
			int reSt = 0;
			List<Integer> answer = new ArrayList<>();
			if (fromSt[g] > fromSt[h]) reSt = g;
			else reSt = h;
			
			fromRe = new int[n+1];
			visited = new boolean[n+1];
			
			queue = new PriorityQueue<>((o1, o2) -> {
				return o1[1] - o2[1];
			});
			
			queue.add(new int[] {reSt, 0});
			for (int i = 1; i <= n; i++) {
				fromRe[i] = INF;
			}
			fromRe[reSt] = 0;
			
			while (!queue.isEmpty()) {
				int curr = queue.poll()[0];
				
				if (visited[curr]) continue;
				visited[curr] = true;
				
				for (int i = 1; i <= n; i++) {
					if (adj[curr][i] != 0 && !visited[i] &&
							fromRe[i] > fromRe[curr]+adj[curr][i]) {
						fromRe[i] = fromRe[curr]+adj[curr][i];
						queue.add(new int[] {i, fromRe[i]});
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < t; i++) {
				if (fromSt[reSt]+fromRe[dep[i]] == fromSt[dep[i]]) {
					answer.add(dep[i]);
				}
			}
			
			Collections.sort(answer);
			for (int a : answer) {
				sb.append(a+" ");
			}
			System.out.println(sb.toString());
		}
		sc.close();
	}
}
//private static void print(int[][] arr) {
//	System.out.println(arr.length);
//	for (int i = 0; i < arr.length; i++) {
//		System.out.println(Arrays.toString(arr[i]));
//	}
//}
