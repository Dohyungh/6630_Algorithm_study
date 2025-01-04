/*
 * 모든 칸에서 해당칸의 높이에서 갈 수 있는 가장 먼 왼쪽 변의 위치와 오른쪽 변의 위치를 알면
 * 너비가 나오고 넓이를 구할 수 있음.
 * 
 * 이를 스택으로 구현한다.
 * 
 * 왼쪽 변의 위치를 구할 때의 방법을 알면 똑같이 오른쪽에도 적용하면 되는데,
 * 
 * 예를 들어 왼쪽 변의 위치를 구하고 싶다면,
 * 
 * 스택에 인덱스를 하나씩 담아가되,
 * 넣고자 하는 인덱스의 높이보다 높은 것이 들어가 있다면 모두 빼내고 난 후에 인덱스를 스택에 넣는다.
 * 
 * 높이가 나보다 낮은 애가 남았으면 그 남은 애를 기준으로 왼쪽 변의 위치를 정하고
 * 높이가 같은 애가 남았으면 그냥 그 인덱스를 넣었을 때 구했을 값을 참조해서 넣는다.
 */

package BOJ._6549_히스토그램에서가장큰직사각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[N];
			
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			if (N == 0) return;
			
			// 각 칸에 대해 그릴 수 있는
			// 가장 먼 왼쪽 변과 오른쪽 변을 저장
			int[] left = new int[N];
			int[] right = new int[N];
			
			
			// Stack 용 Deque
			ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
			
			
			// 하나씩 돌면서
			for (int i = 0; i < N; i++) {
				
				// 나보다 큰 애들 다 꺼내 (더 멀리 갈 수 있다는 뜻)
				
				while(!deque.isEmpty() && arr[deque.peek()] > arr[i]) deque.pop();
				
				// 하나도 안남았으면
				// 끝에 도달한 거고
				if (deque.isEmpty()) {
					left[i] = 0;
					
				// 더 작은게 남았으면
				// 거기서 끊긴 거고
				} else if (arr[deque.peek()] < arr[i]) {
					left[i] = deque.peek()+1;
					
				// 나머지 경우는 같을 때인데
				// 이미 구해 놨을 값을 그냥 적어줌 (DP 처럼)
				} else {
					left[i] = left[deque.peek()];
				}
				deque.push(i);
			}
			
			deque.clear();
			
			// 오른쪽 변도 숫자만 살짝 바꿔서 돌려줌
			for (int i = N-1; i >= 0; i--) {
				while(!deque.isEmpty() && arr[deque.peek()] > arr[i]) deque.pop();
				if (deque.isEmpty()) {
					right[i] = N;
				} else if (arr[deque.peek()] < arr[i]) {
					right[i] = deque.peek();
				} else {
					right[i] = right[deque.peek()];
				}
				deque.push(i);
			}
			
			// 마지막으로 쭉 돌면서 최대 넓이를 구해주면 끝.
			long max = Long.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				max = Math.max(max, (long) arr[i] * (right[i] - left[i]));
				
			}
			System.out.println(max);
			
		}
		
		
	}

}
