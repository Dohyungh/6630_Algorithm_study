/*
 * [풀이 방법]
 * 필요한 두개의 배열
 * 	1. 주어진 숫자들 배열
 * 	2. 주어진 숫자의 구간합을 나타내는 세그먼트 트리(이진트리)
 * 
 * 트리의 최대 크기를 구하려면 트리의 높이를 알면 됨.
 * 리프 노드 개수를 통해 트리 높이 구하기 : hieght = log밑2값N = logN/log2
 * 트리 높이를 통해 트리의 최대 노드수 구하기 : (2^hieght)-1
 * 
 * 이진트리 특성을 활용해서 init, update, sum 메서드 구현
 * 이진트리의 노드들을 루트(1)부터 순서대로 배열화 했을 때,
 * 자식 노드 : 부모노드*2, 부모노드*2 +1
 * 
 */
package boj_2042_구간합구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_Better {

	static long[] arr;
	static long[] tree;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);			// 숫자의 개수
		int M = Integer.parseInt(input[1]);			// 교체 횟수
		int K = Integer.parseInt(input[2]);			// 구간합 구하는 횟수
		
		arr = new long[N+1];
		
		for (int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		double hieght = Math.ceil(Math.log(N) / Math.log(2));
		long cnt = Math.round(Math.pow(2, hieght+1)-1);
		tree = new long[Math.toIntExact(cnt)+1];
		
		init(1, N, 1);
		
		for (int i = 0; i < M+K; i++) {
			String[] tmp = br.readLine().split(" ");
			long a = Long.parseLong(tmp[0]);
			long b = Long.parseLong(tmp[1]);
			long c = Long.parseLong(tmp[2]);
			
			if (a == 1) {
				
				long plus = c - arr[(int)b];
				arr[(int)b] = c;
				
				update(1, N, 1, (int)b, plus);
				
			} else {
				
				long sum = getSum(1, N, 1, (int)b, (int)c);
				sb.append(sum+"\n");
				
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

	/**
	 * 
	 * @param start	해당 노드가 가진 구간의 시작
	 * @param end 해당 노드가 가진 구간의 끝
	 * @param node 해당 노드 번호 - tree기준
	 * @return
	 */
	private static long init(int start, int end, int node) {
		
		// 리프 노드면 arr의 해당하는 위치의 값을 저장
		if (start == end) return tree[node] = arr[start];
		
		int mid = (start + end) / 2;
		
		// 리프 노드가 아니라면 자식 노드 값의 합을 저장
		return tree[node] = init(start, mid, node*2) 
				+ init(mid+1, end, node*2+1);
	}

	/**
	 * 
	 * @param start	해당 노드가 가진 구간의 시작
	 * @param end 해당 노드가 가진 구간의 끝
	 * @param node 해당 노드 번호 - tree기준
	 * @param chIdx 바꾸려는 숫자의 인덱스 - arr기준
	 * @param plus 수 변화
	 */
	private static void update(int start, int end, int node, int chIdx, long plus) {
		
		// 해당 노드의 구간에 바꾸려는 인덱스가 없으면 패스
		if (chIdx < start || chIdx > end) return;
		
		// 포함이 된다면 값 갱신
		tree[node] += plus;
		
		// 리프노드라면 리턴
		if (start == end) return;
		
		// 리프노드가 아니라면 자식 노드도 갱신 확인
		int mid = (start + end) / 2;
		update(start, mid, node*2, chIdx, plus);
		update(mid+1, end, node*2+1, chIdx, plus);
		
	}
	
	/**
	 * 
	 * @param start	해당 노드가 가진 구간의 시작
	 * @param end 해당 노드가 가진 구간의 끝
	 * @param node 해당 노드 번호 - tree기준
	 * @param left 구하려는 구간의 시작
	 * @param right 구하려는 구간의 끝
	 * @return
	 */
	private static long getSum(int start, int end, int node, int left, int right) {
		
		// 구하려는 구간이 해당 노드의 구간에 전혀 겹치지 않으면 0반환
		if (left > end || right < start) return 0;
		
		// 해당 노드의 구간이 구하려는 구간 안에 완전히 속하면 해당 노드값 반환
		if (start >= left && end <= right) return tree[node];
		
		int mid = (start + end) / 2;
		
		// 일부만 포함되어 있으면 자식 노드 탐색
		return getSum(start, mid, node*2, left, right) 
				+ getSum(mid+1, end, node*2+1, left, right);
	}

}
