/*
 * 트라이 구조를 사용해 이전까지의 닉네임의 어미인지, 같은 닉네임이 몇번 생성되었는지 파악한다.
 */
package boj_16934_게임닉네임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Node root = new Node();		// 빈 루트 노드 생성
		
		for (int i = 0; i < N; i++) {
			
			// 닉네임 입력, 문자 배열로 변환
			char[] input = br.readLine().toCharArray();
			int idx = 0;					// 문자 배열을 순회할 인덱스
			boolean flag = false;			// 별칭이 가능한 prefix가 있었는지
			Node curr = root;				// 루트부터 시작
			
			// 닉네임 순회가 끝날때까지 반복
			while (idx < input.length) {
				
				// 현재 문자에 해당하는 자식이 있으면 가져오고, 아니면 null
				Node next = curr.child.getOrDefault(input[idx], null);
				
				// 이미 있었으면 여기까지 다른 닉네임의 prefix임
				// 해당 노드로 현재 위치 옮기고, 알파벳 출력, idx 증가
				if (next != null) {
					curr = next;
					sb.append(curr.alpha);
					idx++;
					continue;
					
				// 지금까지 없던 prefix면 새로 생성하고, 새로 생성 표시(flag = true)
				// 그리고 뒤에 남은 문자들이 있다면 전부 새로 생성해서 연결
				} else {
					curr = getNext(curr, input[idx++]);
					sb.append(curr.alpha + "\n");
					flag = true;
					
					while (idx < input.length) {
						curr = getNext(curr, input[idx++]);
					}
				}
			}
			
			// 마지막 노드에 end++ 즉, 같은 닉네임의 개수
			curr.end++;
			
			// 사용가능한 prefix가 있고, 같은 닉네임이 2개 이상이면 뒤에 숫자 붙이기
			if (!flag) {
				int count = curr.end;
				sb.append(count > 1 ? count + "\n" : "\n");
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	/**
	 * 새 자식 노드를 생성하고 생성된 자식 노드를 반환하는 메서드 
	 * @param curr
	 * @param key
	 * @return
	 */
	static Node getNext(Node curr, char key) {
		curr.child.put(key, new Node(key));
		return curr.child.get(key);
	}
}

class Node {
	char alpha;
	Map<Character, Node> child;
	int end;
	
	Node(){
		child = new HashMap<>();
	}
	
	Node(char alpha) {
		this.alpha = alpha;
		child = new HashMap<>();
		int end = 0;
	}
}