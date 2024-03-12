package BOJ._2606_바이러스;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static int answer = 0;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
//		List<Integer>[] link = new ArrayList<Integer>[N]; 불가!
//		입력이 이렇게 주어지면 인접행렬을 안쓸 수가 있나??? 있네!!
		
		int[][] link = new int[N+1][]; // 컴퓨터가 1번부터, 0번은 더미로 쓰자
		
		int numLinks = sc.nextInt();
		
		for (int n = 0; n < numLinks; n++) {
			int idx1 = sc.nextInt();
			int idx2 = sc.nextInt();
			
			if (link[idx1] == null) { // null 이면 한칸 짜리 배열 만들어서 넣어주기
				link[idx1] = new int[] {idx2};
			} else {
				int[] temp = new int[link[idx1].length+1]; // null이 아니었으면, 1칸 더 큰 배열 만들어서 복사해주기
				System.arraycopy(link[idx1], 0, temp, 0, link[idx1].length);
				temp[link[idx1].length+1-1] = idx2; // 맨 마지막에 넣어주기 -> 결국 이 방식은 문제가 입력을 정렬해서 주는게 아니면 정렬이 안된다.
				link[idx1] = temp;
			}
			if (link[idx2] == null) {
				link[idx2] = new int[] {idx1};
			} else {
				int[] temp = new int[link[idx2].length+1]; 
				System.arraycopy(link[idx2], 0, temp, 0, link[idx2].length);
				temp[link[idx2].length+1-1] = idx1;
				link[idx2] = temp;
			}
		}
		
//		null
//		[2, 5]
//		[1, 3, 5]
//		[2]
//		[7]
//		[1, 2, 6]
//		[5]
//		[4]
		
		int start = 1;
		
		visited = new boolean[N+1];
		answer = 0;
		DFS(start, link);
		System.out.println(answer-1);
		
		
		visited = new boolean[N+1];
		answer = 0;
		BFS(start, link);
		System.out.println(answer-1);
		
		
		
	}
	private static void DFS(int i, int[][] link) {
		
		// 호출할 때마다 (방문할 때마다) 
		answer++;
		
		visited[i] = true;
		
		if (link[i] == null) return; // leaf 이면 return;
		
		
		for (int n = 0; n < link[i].length; n++) {
			
			int linkedNode = link[i][n];
			
			if (!visited[linkedNode]) {
				DFS(linkedNode,link);
//				visited[n] = false; 중복 방문 하면 안되니까 필요 없음
			}
		}
		
		// DFS 잘 모르겠다..
		
		
		
	}
	private static void BFS(int i, int[][] link) {
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(i);
		visited[i] = true;
		
		while(!queue.isEmpty()) {
			int nowNode = queue.poll();
			
			answer++;
			if (link[nowNode] != null) { // null 포인터에러 뜸ㅠㅠ
				for (int n = 0; n < link[nowNode].length; n++) { // 그래프니까 neighborhood의 n
					
					int linkedNode = link[nowNode][n];
					
					if (!visited[linkedNode]) {
						queue.add(linkedNode);
						visited[linkedNode] = true;					
					}
				}				
			}
			
		}
		
		
	}

}
