package BOJ._11003_최솟값찾기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
		
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			while(!dq.isEmpty() && arr[dq.peekLast()] > arr[i]) {
				dq.pollLast();
			}
			dq.addLast(i);
			while(dq.peekFirst() <= i - L) {
				dq.pollFirst();
			}
			bw.write(arr[dq.peekFirst()] + " ");
			
			
		}
		bw.flush();
		bw.close();
		
	}

}
