package boj_11725_트리의부모찾기;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();		// �끂�뱶�쓽 媛쒖닔
		
		// 媛� �끂�뱶�쓽 �뿰寃� 援ъ“, �씤�젒�뻾�젹怨� �씉�궗�븿
		List<Integer>[] nodes = new List[N+1];
		
		// �끂�뱶 諛⑸Ц �뿬遺� 泥댄겕
		boolean[] visited = new boolean[N+1];
		
		// 媛� �끂�뱶�쓽 遺�紐� �끂�뱶 �젙蹂�
		int[] parentMap = new int[N+1];
		
		// �엯�젰
		for (int i = 0; i < N-1; i++) {
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			
			// �꽌濡쒖쓽 �뿰寃� �끂�뱶 由ъ뒪�듃�뿉 異붽�, �븘吏� �늻媛� 遺�紐⑥씤吏� 紐⑤Ⅴ�땲源� 怨듯룊�븯寃�
			if (nodes[num1] == null) {
				nodes[num1] = new ArrayList<>();
			}
			if (nodes[num2] == null) {
				nodes[num2] = new ArrayList<>();
			}
			nodes[num1].add(num2);
			nodes[num2].add(num1);
		}
		
		// bfs �깘�깋�쓣 �쐞�븳 �걧
		Queue<Integer> queue = new LinkedList<>();
		
		// 猷⑦듃 1�� �꽔怨� �떆�옉�븿
		queue.add(1);
		visited[1] = true;
		
		// �걧媛� 鍮� �븣源뚯� 諛섎났
		while (!queue.isEmpty()) {
			
			// 媛� �븯�굹 戮묒븘�꽌
			int nodeNum = queue.poll();
			
			// �빐�떦�븯�뒗 �끂�뱶�뿉 �뿰寃곕맂 �끂�뱶�뱾�쓣 �걧�뿉 異붽�, �떒 諛⑸Ц�븯吏� �븡�� 怨노쭔
			if (nodes[nodeNum] != null) {
				for (int i : nodes[nodeNum]) {
					if (!visited[i]) {
						queue.add(i);
						visited[i] = true;
						
						// �뿰寃곕맂 �끂�뱶�뱾�쓽 遺�紐� �끂�뱶�뒗 �쁽�옱 �끂�뱶�씠誘�濡�, 遺�紐⑤㏊�뿉 ���옣
						parentMap[i] = nodeNum;
					}
				}
			}
		}
		
		// 遺�紐⑤㏊ 2踰덈��꽣 異쒕젰
		for (int i = 2; i <= N; i++) {
			System.out.println(parentMap[i]);
		}
		
		// 臾몃떒�냽
		sc.close();
	}

}
