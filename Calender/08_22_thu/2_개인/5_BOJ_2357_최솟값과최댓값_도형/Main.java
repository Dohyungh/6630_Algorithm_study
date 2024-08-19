package BOJ._2357_최솟값과최댓값;

import java.util.Scanner;

public class Main {
	
	// 매 요청마다 최종 출력할 최댓값/최솟값
	static int globalMax;
	static int globalMin;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 원본 배열 크기
		int N = sc.nextInt();
		
		// 요청 횟수
		int M = sc.nextInt();
		
		// 원본 배열
		int[] arr = new int[N+1];
		
		// 원본 배열 입력
		for (int i = 1; i < N+1; i++) arr[i] = sc.nextInt();
		
		// 세그먼트 트리 생성
		SegmentTree tree = new SegmentTree(N, arr);
		
		// 세그먼트 트리 초기화
		// 완전이진트리
		tree.init(1);
		
		// 매 요청마다 getAnswer함수로 globalMin, globalMax를 구해서 출력한다.
		for (int i = 0; i < M; i++) {
			
			// left
			int a = sc.nextInt();
			
			// right
			int b = sc.nextInt();
			
			// global min, max 초기화
			globalMax = Integer.MIN_VALUE;
			globalMin = Integer.MAX_VALUE;
			
			tree.getAnswer(a,b,1);
			// 여기까지 global min과 max가 구해진 상태
			
			System.out.print(globalMin + " " +globalMax + "\n");
		}
		
		sc.close();
		
	}
	
	static class Node {
		
		// 시작과 끝 인덱스 (원본 기준)
		int start;
		int end;
		
		// 해당 시작과 끝 사이에 있는 수 중에서 최댓값과 최솟값
		int max;
		int min;
		
		// min, max 값은 leaf를 찍고 다시 올라올 때 배정할 것이기 때문에
		// Node의 생성자는 start, end만 필요한다.
		Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
	}
	
	
	static class SegmentTree {
		
		// 원본 배열
		int[] arr;
		
		// 노드의 개수
		int size; // 4*N
		Node[] nodes; // = tree
		
		SegmentTree(int N, int[] arr) {
			this.size = 4*N;
			this.arr = arr;
			this.nodes = new Node[4*N];
			
			// root 노드 생성
			nodes[1] = new Node(1, N);
		}
		
		// tree 초기화
		void init(int idx) {
			
			// 이 노드의 시작과 끝을 구해서
			Node node = nodes[idx];
			
			int start = node.start;
			int end = node.end;
			
			// 같으면, leaf 노드이므로, 원본 배열 (이때 쓰려고 포함해서 segmentTree를 생성함)에서 참고해 가져오면 됨
			// max와 min이 같은 값을 가짐
			if (start == end) {
				node.max = arr[start];
				node.min = arr[start];
				return;
			}
			
			// leaf 노드가 아니라면, 소유하고 있던 범위를 반으로 쪼개 자식 노드 둘에 각각 나눠 줌
			int mid = (start + end) / 2;
			nodes[idx*2] = new Node(start,mid);
			nodes[idx*2 + 1] = new Node(mid+1, end);
			
			// max, min 값을 leaf 노드부터 가져와야 하기 때문에, 값 대입이 재귀 호출보다 뒤에 와야 함
			init(idx*2);
			init(idx*2 + 1);
			
			// max, min을 자식 노드에서 비교해서 가져옴
			node.max = Math.max(nodes[idx*2].max, nodes[idx*2+1].max);
			node.min = Math.min(nodes[idx*2].min, nodes[idx*2+1].min);
		}
		
		// left는 구하고자 하는 범위의 시작
		// right는 구하고자 하는 범위의 끝
		void getAnswer(int left, int right, int idx) {
			
			// 지금 idx의 노드를 가져와서
			Node node = nodes[idx];
			int start = node.start;
			int end = node.end;
			
			// 노드의 범위가 구하고자 하는 범위에 쏘옥 들어가면 값 비교해서 업데이트 
			if (start >= left && end <= right) {
				globalMax = Math.max(globalMax, node.max);
				globalMin = Math.min(globalMin, node.min);
				return;
			}
			
			
			// 노드의 범위가 구하고자 하는 범위 밖이면 그냥 리턴
			if (end < left || start > right) {
				return;
			}
			
			// 걸쳐 있으면, 양쪽 노드로 갈라져서 진행
			getAnswer(left,right,idx*2);
			getAnswer(left,right,idx*2 + 1);
			
		}
	}

}
