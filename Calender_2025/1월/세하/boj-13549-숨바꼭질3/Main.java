package boj_13549_숨바꼭질3;

import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100001;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // 각 위치까지의 최단 시간을 저장할 배열
        int[] time = new int[MAX];
        Arrays.fill(time, -1);  // -1로 초기화하여 방문하지 않은 상태 표시
        
        // 0초 이동과 1초 이동을 구분하기 위해 덱(Deque) 사용
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(N);
        time[N] = 0;
        
        while (!deque.isEmpty()) {
            int current = deque.poll();
            
            if (current == K) {
                System.out.println(time[current]);
                return;
            }
            
            // 순간이동 (0초) - 우선적으로 처리하기 위해 덱의 앞쪽에 추가
            if (current * 2 < MAX && time[current * 2] == -1) {
                time[current * 2] = time[current];
                deque.addFirst(current * 2);
            }
            
            // 걷기 (1초) - 덱의 뒤쪽에 추가
            // 뒤로 한 칸
            if (current - 1 >= 0 && time[current - 1] == -1) {
                time[current - 1] = time[current] + 1;
                deque.addLast(current - 1);
            }
            
            // 앞으로 한 칸
            if (current + 1 < MAX && time[current + 1] == -1) {
                time[current + 1] = time[current] + 1;
                deque.addLast(current + 1);
            }
        }
    }
}