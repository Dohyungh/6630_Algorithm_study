/*
 * 처음에 단순히 입력받은 수들을 정렬해서 앞에서부터 계산했는데 틀렸다.
 * 한번만 정렬하는게 아니라 합해진 카드덩이의 수도 계속 정렬하면서 
 * 그때그때 가장 작은 두개의 카드더미를 합해 나가야 한다.
 * 그래야 큰 수들의 반복 합산을 최소화 할 수 있다.
 * 
 * 따라서 우선순위 큐를 사용했는데, 만약 비교할 수의 개수가 훨씬 커진다면
 * 다른 방법을 찾아야할 수도 있을 것 같다.
 */

package boj_1715_카드정렬하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 합한 수를 계속 넣으면 Integer 범위 넘을 것 같아서 Long으로 선언
		PriorityQueue<Long> cards = new PriorityQueue<>();
		
		//입력
		for (int i = 0; i < N; i++) {
			cards.add(Long.parseLong(br.readLine()));
		}
		
		Long answer = 0L;
		
		// 큐 안에 남은 수가 2개 이상이면 계산
		while (cards.size() > 1) {
			
			// 가장 작은 수 수를 뽑아 더한다.
			Long sum = cards.poll() + cards.poll();
			
			// 합한 수를 우선순위 큐에 다시 넣는다.
			cards.add(sum);
			
			// 답 갱신
			answer += sum;
		}
		
		// 출력
		System.out.println(answer);
		br.close();
	}
}
