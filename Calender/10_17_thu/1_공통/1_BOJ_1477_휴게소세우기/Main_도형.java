package BOJ._1477_휴게소세우기;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static class Node implements Comparable<Node>{
        int length; // 길이를
        int count; // 몇개로 나누었는지

        Node(int length, int count) {
            this.length = length;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
        	// 구간의 최대 길이로 비교해서 pq 정렬
        	return o.getVal() - this.getVal();
        }
        
        public int getVal() {
        	// 그렇게 나누었을 때의 해당 구간에서 최대 길이
        	return length % count == 0 ? length / count : length / count + 1;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int L = sc.nextInt();

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        // 인접한 휴게소 사이의 거리를 구할 수 있게 정렬해줌
        Arrays.sort(arr);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 예외 처리
        if (N == 0) {
        	System.out.println(L%(M+1) == 0 ? L/(M+1) : L/(M+1)+1);
            return;    
        }
        
        // pq 초기화
        pq.add(new Node(arr[0], 1)); // 0 ~ 첫번째
        pq.add(new Node(L - arr[N-1], 1)); // 마지막 ~ L(끝)

        for (int i = 1; i < N; i++) {
            pq.add(new Node(arr[i] - arr[i-1], 1));
        }
        // 여기까지 pq 초기화

        // 휴게소 세운 횟수를 카운트
        int count = 0;
        
        while (count < M) {
        	Node curr = pq.poll();
        	pq.offer(new Node(curr.length, curr.count+1));
        	count++;
        }
        
        System.out.println(pq.poll().getVal());

    }
}
