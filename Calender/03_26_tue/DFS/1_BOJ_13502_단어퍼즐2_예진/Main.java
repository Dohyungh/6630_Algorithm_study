package boj_13502_단어퍼즐2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	// 팔방 탐색 델타 배열
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

	static Trie trie;
	static char[][] alpha;
	static boolean[][] visited;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("src/boj_13502_단어퍼즐2/dict.txt"));
		trie = new Trie();
		
		while(br.ready()) {
			trie.insert(br.readLine());
		}
		
		br.close();

		alpha = new char[5][5];
		visited = new boolean[5][5];
		answer = 0;
		
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < 5; i++) {
			char[] tmp = sc.nextLine().toCharArray();
			int idx = 0;
			for (int j = 0; j < 9; j+=2) {
				alpha[i][idx++] = tmp[j];
			}
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Node curr = trie.root;
				visited[i][j] = true;
				search(i, j, curr.child.getOrDefault(alpha[i][j], null));
				visited[i][j] = false;
			}
		}
		
		System.out.println(answer);
		
		sc.close();
	}
	
	private static void search(int r, int c, Node curr) {
		if (curr == null) return;
		
		if (curr.endOfWord) {
			curr.endOfWord = false;
			answer++;
		}
		
		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || visited[nr][nc]) continue;
			
			visited[nr][nc] = true;
			search(nr, nc, curr.child.getOrDefault(alpha[nr][nc], null));
			visited[nr][nc] = false;
		}
	}
}

class Trie {
	
	// Trie 자료구조에서 root 노드 기본 생성
	Node root = new Node();
	
	void insert(String str) {
		
		// 루트부터 시작
		Node curr = this.root;
		
		for (int i = 0; i < str.length(); i++) {
			
			// computeIfAbsent(존재하는지 확인할 key값, key가 존재하지 않을때 실행할 함수)
			// key가 있으면 child의 해당 key값(Node)로 이동하겠다는거고,
			// key가 없으면 child의 해당 key값을 새로 생성(new Node())해서 이동하겠다는 의미
			curr = curr.child.computeIfAbsent(str.charAt(i), key -> new Node());
		}
		
		// 저장할 문자열의 마지막 문자와 매핑되는 노드에 단어 끝 표시
		curr.endOfWord = true;
	}
	
//	// Trie에서 문자열 검색
//	boolean search(String str) {
//		
//		// 루트부터 시작
//		Node curr = this.root;
//		
//		// 매개변수로 받은 문자열을 순회하며
//		for (int i = 0; i < str.length(); i++) {
//			
//			// 해당 인덱스의 문자를 key로 하는 자식 노드가 있으면 자식 노드로 이동하고,
//			// 없으면 null 표시 한다.
//			curr = curr.child.getOrDefault(str.charAt(i), null);
//			
//			// curr가 null이라는 건
//			// 문자열의 문자를 전부 확인하기 전에 노드 연결이 끊겼다는 의미(그 단어 이 Trie에 없다고)
//			// false 반환
//			if (curr == null) return false;
//		}
//		
//		// 문자열의 모든 문자가 Trie에 존재한다면
//		// 마지막 문자에 '여기까지 한 단어에요'라는 표시가 있는지를 반환
//		return curr.endOfWord;
//	}
	
}

class Node {
	// 문자를 key로 Node링크를 매핑
	Map<Character, Node> child = new HashMap<>();
	
	// 단어의 끝문자인지 여부 체크
	boolean endOfWord;
}
