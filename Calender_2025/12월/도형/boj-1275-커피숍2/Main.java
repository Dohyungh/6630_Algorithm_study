package BOJ._1275_커피숍2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int start;
		int end;
		long sum;
		
		Node(int start, int end, long sum) {
			this.start =start;
			this.end = end;
			this.sum = sum;
		}
	}
	
	static Node[] tree;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		long[] arr = new long[N+1];
		
		tree = new Node[4*N];
		
		for (int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		init(1, arr, 1, N);
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			System.out.println(getSum(1, Math.min(A, B), Math.max(A,B)));
			int loc = Integer.parseInt(st.nextToken());
			long target = Long.parseLong(st.nextToken());
			change(1, loc, target - arr[loc]);
			arr[loc] = target;
		}
		
	}
	
	public static void init(int idx, long[] arr, int start, int end) {
		if (start == end) {
			tree[idx] = new Node (start, end, arr[start]);
			return;
		}
		
		init(2*idx, arr, start, (start+end)/2);
		init(2*idx+1, arr, (start+end)/2 + 1, end);
		
		tree[idx] = new Node(start, end, tree[2*idx].sum + tree[2*idx+1].sum);
	}
	
	public static long getSum(int idx, int start, int end) {
		int currStart = tree[idx].start;
		int currEnd = tree[idx].end;
		
		if (currStart >= start && currEnd <= end) {
			return tree[idx].sum;
		}
		
		if (currEnd < start || currStart > end) {
			return 0L;
		}
		
		if (currStart <= start || currEnd >= end) {
			return getSum(2*idx, start, end) + getSum(2*idx + 1, start, end);
		}
		
		return 0L;
	}
	
	public static void change(int idx, int loc, long gap) {
		
		int currStart = tree[idx].start;
		int currEnd = tree[idx].end;
		
		if (currStart == currEnd) {
			tree[idx].sum += gap;
			return;
		}
		
		tree[idx].sum += gap;
		if (loc <= (currStart + currEnd) / 2) {
			change(2*idx, loc, gap);
		} else {
			change(2*idx+1, loc, gap);
		}
		
	}

}
