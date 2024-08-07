/*
 * [풀이 방법]
 * 1. 주어진 숫자들을 리프노드로 해서 전체 구간합을 구하는 노드(루트)까지 두개씩 짝지어 부모 노드 생성
 * 2. 숫자 변경: 인덱스로 노드에 접근해서 루트 노드까지 값 갱신
 * 3. 구간합: 루트 노드부터 접근해서 구간에 해당하는 노드 값 모두 더하기
 */
package boj_2042_구간합구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {

	static long N;
	static long M;
	static long K;
	static Node[] leaves;
	static Node root;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		N = Long.parseLong(input[0]);			// 숫자의 개수
		M = Long.parseLong(input[1]);			// 교체 횟수
		K = Long.parseLong(input[2]);			// 구간합 구하는 횟수
		leaves = new Node[(int) (N+1)];			// 리프 노드, 0인덱스 패딩
		
		// start = end = 자기 순서, sum = 숫자
		for (int i = 1; i <= N; i++) {
			long num = Long.parseLong(br.readLine());
			leaves[i] = new Node(i, i, num);
		}
		
		int[] cnt = new int[5];
		
		// 세그먼트 트리 초기화
		long idx = 1;	// 접근할 노드 인덱스
		
		// 무한반복, 기저조건 내부에 위치
		out: while(true) {
			
			// a번 노드: idx번 노드 접근 후 부모가 없는 지점까지 이동
			Node a = leaves[(int) idx];
			while(a.parent != null) a = a.parent;
			
			// a번 노드의 끝지점이 N이고, 시작점이 1이면 그 노드는 루트임 break;
			if (a.end == N) {
				if (a.start == 1) {
					root = a;
					break out;
				}
				
				// 끝지점만 같다면 다시 1번 노드부터 돌아야 함
				idx = 1;
				continue;
			}
			
			// b번 노드: a번 노드의 끝지점+1 번째 노드에 접근 후 부모가 없는 지점까지 이동
			idx = a.end + 1;
			Node b = leaves[(int) idx];
			while(b.parent != null) b = b.parent;
			
			// 두 노드의 부모 생성 및 부모 노드의 자식 노드 설정
			a.parent = b.parent = new Node(a.start, b.end, a.sum + b.sum);
			a.parent.child = new Node[2];
			a.parent.child[0] = a;
			a.parent.child[1] = b;
			
			// b번 노드의 끝지점+1이 N보다 크거나 같으면 다시 1번부터, 아니라면 계속
			idx = b.end + 1 >= N ? 1 : b.end + 1;
		}
		
		// 교체 횟수 + 구간합 구하는 횟수만큼 반복
		for (long i = 0; i < M + K; i++) {
			
			String[] tmp = br.readLine().split(" ");
			long order = Long.parseLong(tmp[0]);	// 연산 타입
			long x = Long.parseLong(tmp[1]);		// 두번째 수
			long y = Long.parseLong(tmp[2]);		// 세번째 수
			
			// 숫자 교체인 경우
			if (order == 1) {
				
				Node curr = leaves[(int) x];	// 노드 접근
				long minus = curr.sum - y;		// 빼야 하는 값 구하기
				curr.sum -= minus;				// 본인 값부터 바꾸고
				
				// 부모가 있으면 부모의 값을 수정하면서 무한 이동
				while (curr.parent != null) {
					curr.parent.sum -= minus;
					curr = curr.parent;
				}
				
			// 구간합 구하는 경우
			} else {
				long segSum = getSum(root, x, y);
				sb.append(segSum+"\n");
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	/*
	 * 구간합을 구하는 재귀함수
	 */
	private static long getSum(Node curr, long left, long right) {
		
		// 지금 노드가 구해야 하는 구간합 안에 포함되면 sum 반환
		if (curr.start >= left && curr.end <= right) return curr.sum;
		// 지금 노드가 구해야 하는 구간합 안에 전혀 포함되지 않으면 0 반환
		if (curr.start > right || curr.end < left) return 0;
		
		// 반환할 구간합
		long sum = 0;
		
		// 구간합의 부분만 포함될 경우 자식 노드 확인, 없는 자식은 확인X
		for (Node child : curr.child) {
			if (child == null) continue;
			sum += getSum(child, left, right);
		}
		
		return sum;
	}

}

class Node {
	
	long start;
	long end;
	long sum;
	Node parent;
	Node[] child;
	
	Node(long start, long end, long sum) {
		this.start = start;
		this.end = end;
		this.sum = sum;
	}
	
	@Override
	public String toString() {
		return start + "~" + end +":"+ sum;
	}
}
