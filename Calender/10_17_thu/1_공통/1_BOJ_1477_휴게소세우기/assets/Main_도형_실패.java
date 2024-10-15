package BOJ._1477_휴게소세우기;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static class Node implements Comparable<Node>{
        int length;
        int count;

        Node(int length, int count) {
            this.length = length;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            if (this.length == o.length) return o.count - this.count;
            return o.length - this.length;
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

        Arrays.sort(arr);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        if (N != 0) {
            pq.add(new Node(arr[0], 0));
            pq.add(new Node(L - arr[N-1], 0));
        } else {
            System.out.println(L%(M+1) == 0 ? L/(M+1) : L/(M+1)+1);
            return;
        }

        for (int i = 1; i < N; i++) {
            pq.add(new Node(arr[i] - arr[i-1], 0));
        }

        // 다 개별로 관리하니까 틀리는데 왜 틀리는 지는 모르겠음ㅠㅠ (반례를 못찾겠음)
        for (int i = 0; i < M; i++) {
            Node temp = pq.poll();
            int cnt = temp.count;
            int lengthSum = temp.length;
            
            for (int j = 0; j <cnt; j++) {
                lengthSum += pq.poll().length;
            }
            
            for (int j = 0; j < lengthSum%(cnt+2); j++) {
                pq.offer(new Node(lengthSum/(cnt+2) + 1, cnt+1));

            }
            for (int j = 0; j <cnt + 2 - lengthSum%(cnt+2); j++) {
                pq.offer(new Node(lengthSum/(cnt+2), cnt+1));
            }
        }
        System.out.println(pq.poll().length);

    }
}
