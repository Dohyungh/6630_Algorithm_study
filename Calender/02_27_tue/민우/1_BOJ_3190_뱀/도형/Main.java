package BOJ._3190_뱀;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<int[]> apples = new ArrayList<>(); // 사과 리스트
	// 원래 contains 를 쓸라 그랬는데,
	// 배열은 어차피 참조형이라 안먹어서
	// 원소 하나씩 비교하는 걸로 결국 바꿈
	// 결론: 그냥 안에다 배열로 선언해도 됨.
	
	public static void main(String[] args) {
		// 오른쪽이 왜 R이 아니라 D죠??
		// 어렵다잉
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();

		int dir = 1; // 방향
		
		int length = 1; // 길이
		
		Deque<int[]> snake = new LinkedList<>(); // 뱀 몸통을 담을 배열
		
		int[] head = {0,0}; // 대가리
		
		int k = sc.nextInt(); // 사과 개수
		for (int i = 0; i < k; i++) {
			int[] apple = new int[2];
			apple[0] = sc.nextInt()-1; // index 라서 하나 뻄
			apple[1] = sc.nextInt()-1;
			apples.add(apple);
		}
		
		snake.add(head); // 머리 넣고 시작
		
		int numOrd = sc.nextInt(); // 방향바꾸는 명령의 개수
		int[][] orders = new int[numOrd][2];
		for (int i = 0; i < numOrd; i++) {
			orders[i][0] = sc.nextInt();
			if(sc.next().equals("D")) { 
				orders[i][1] = 1; // 델타 배열에서 한칸 오른쪽으로 갈거임
				
			} else {
				orders[i][1] = -1; // 델타 배열에서 한칸 왼쪽으로 갈거임
			}
		}
		// 이렇게 만든 순간 델타배열의 '순서' 가 의미가 생김.
		// 12시 기준으로 시계방향
		
		
		int cnt = 0;
		int order_done = 0; // 수행한 명령
		int next_event = orders[order_done][0]; // 다음에 방향을 바꿀 타이밍
		while (true) {
			cnt++; // 시간 ++

			snake = move(snake,dir); // 머리가 한칸 먼저 늘어나는데,
			// 그 머리가
			if (terminate(snake,N)) break; // 종료조건에 닿으면 종료
			for (int i = 0; i < k; i++) {
				int[] apple = apples.get(i);
				head = snake.getLast(); // 뱀 배열의 마지막 = 대가리
				if(head[0] == apple[0] && head[1] == apple[1]) {
					int[] del = {-1,-1};
					apples.set(i, del); // 사과 리스트에서 {-1,-1}로 만드는 걸로 삭제
					length++; // 길이 ++
				}
			}
			
			if (snake.size() > length) {
				snake.pollFirst(); // 머리가 한칸 갔으니까 꼬리가 한칸 줄어듦.
				// 만약 위에서 사과를 먹었으면 안줄어드는 거지.
			}
			
			if (cnt == next_event) {
				if(orders[order_done][1] == 1) {
					dir = (dir+1)%4; // 델타 배열 모듈러
					
				} else {
					dir = (dir-1+4)%4; //델타 배열 모듈러
				}
				order_done++; // 수행한 명령개수
				if (order_done < orders.length) { // index 에러 방지
					next_event = orders[order_done][0];					
				}				
			}
		}
		System.out.println(cnt);
		sc.close();
		
		
	}
	
	public static boolean terminate(Deque<int[]>snake, int N) { // 게임이 끝난다.
		boolean die = false;
		// 대가리를 뽑아서 head에 저장
		int[] head = snake.pollLast();
		
		// 지 몸에 닿거나
		// 뱀 배열안에 head 가 포함되거나
		for (int i = 0; i < snake.size(); i++) {
			int[] temp = snake.pollFirst();
			if (temp[0] == head[0] && temp[1] == head[1]) {
				die = true;
			}
			// 뽑았다가 다시 다 넣어줘야함
			// 앞에서 뽑아서 뒤로 넣어야 순서가 유지됨
			snake.addLast(temp);
			
		}
		// 벽에 닿거나
		if (head[0] >= N || head[1] >=N || head[0] < 0 || head[1] < 0) {
			die = true;	
		}
		
		// 다시 대가리 넣어줌
		snake.addLast(head); // 여기서 snake 를 건드려도 snake 가 바뀌네
		return die;
	}
	
	public static Deque<int[]> move(Deque<int[]>snake, int dir) { // 머리를 늘려 다음칸에 위치
		int[] dr = {-1,0,1,0};
		int[] dc = {0,1,0,-1};
		
		// 새로운 대가리
		int[] newhead = {snake.getLast()[0]+dr[dir], snake.getLast()[1]+dc[dir]};
		snake.addLast(newhead);
		
		return snake;
	}
	
	
	
	
	

}
