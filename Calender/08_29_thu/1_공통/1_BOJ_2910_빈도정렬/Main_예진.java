/*
 * 각 숫자에 해당하는 Node를 Map에 저장, Node에는 해당 숫자, 입력된 순서, 빈도가 저장됨
 * List를 통해 Node들을 정렬해서 개수만큼 차례대로 출력
 */
package boj_2910_빈도정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();	// 어차피 안써서 날리기
		
		Map<Integer, Node> cntMap = new HashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int idx = 0;
		
		while (st.hasMoreTokens()) {
			
			int tmp = Integer.parseInt(st.nextToken());
			
			// 숫자에 해당하는 노드가 있으면 가져오고, 없으면 새로 생성
			Node curr = cntMap.getOrDefault(tmp, new Node(tmp, idx++));
			// 빈도 증가
			curr.count++;
			// 맵 갱신
			cntMap.put(tmp, curr);
		}
		
		// Map에 저장된 Node들을 List로 정렬
		List<Node> nodes = new ArrayList<>(cntMap.values());
		Collections.sort(nodes);
		
		// 각 빈도만큼 반복 출력
		StringBuilder sb = new StringBuilder();
		for (Node n : nodes) {
			for (int i = 0; i < n.count; i++) {
				sb.append(n.num + " ");
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}

class Node implements Comparable<Node>{
	int num;
	int order;
	int count;
	
	Node(int num, int order) {
		this.num = num;
		this.order = order;
		this.count = 0;
	}

	// 빈도 내림차순, 빈도가 같다면 입력 순서 오름차순
	@Override
	public int compareTo(Node o) {
		if (Integer.compare(o.count, this.count) == 0) 
			return Integer.compare(this.order, o.order);
		return Integer.compare(o.count, this.count);
	}
}