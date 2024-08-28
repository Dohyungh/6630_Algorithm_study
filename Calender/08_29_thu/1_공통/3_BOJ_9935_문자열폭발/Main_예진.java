/*
 * - 주어진 문자열의 각 문자 인덱스마다 지금까지 폭발문자열과 몇개나 연속으로 일치하는지 저장한다.
 * 	 일치하는 연속된 문자열이 없는 경우(첫번째 문자도 불일치) -1을 저장한다.
 * 	 계산된 문자는 Deque안에 차례로 넣는다.
 * - 저장된 값이 폭발문자열의 길이와 같아지는 때에 Deque의 뒤에서부터 폭발문자열 길이만큼 문자를 제거한다.
 * - Deque에 남은 마지막 문자인덱스를 확인해 다음 문자는 폭발 문자열의 몇번째 문자와 비교해야 하는지 정한다.
 * 	 연속된 인덱스의 문자와 같지 않을 경우 폭발 문자열의 첫번째 문자와 다시 비교를 시작한다.
 */
package boj_9935_문자열폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] input = br.readLine().toCharArray();		// 문자열
		char[] bomb = br.readLine().toCharArray();		// 폭발문자열
		int N = bomb.length;	// 폭발문자열 길이
		
		int[] dp = new int[input.length];			// 폭발문자열과 연속으로 일치하는 문자개수 저장
		Deque<Character> dq = new LinkedList<>();	// 최종적으로 출력될 문자열
		
		// 폭발 문자열 포인터
		int bombIdx = 0;
		// 문자열 포인터
		int idx = 0;
		
		// 문자열 순회
		for (int i = 0; i < input.length; i++) {
			
			// 해당 순서의 문자 Deque에 넣기
			dq.addLast(input[i]);
			
			// 해당 순서의 문자가 폭발문자열 포인터가 가리키는 문자와 같으면
			// dp테이블에 해당 폭발문자열 포인터 인덱스 저장
			if (input[i] == bomb[bombIdx]) dp[idx++] = bombIdx++;
			// 다를 경우
			else {
				// 폭발문자열 포인터 0으로 이동
				bombIdx = 0;
				// 다시 비교해서 같으면 폭발문자열 포인터 인덱스 저장
				if (input[i] == bomb[bombIdx]) dp[idx++] = bombIdx++;
				
				// 전혀 일치하지 않으면 -1 저장
				else dp[idx++] = -1;
			}
			
			// 더 이상 비교할 폭발문자열이 없으면 즉, 폭발문자열만큼 일치하는 패턴이 나왔으면
			if (bombIdx == N) {
				
				// Deque에서 해당 길이만큼 삭제
				for (int b = 0; b < N; b++) dq.pollLast();
				
				// dp인덱스도 그만큼 다시 앞으로 이동
				idx -= N;
				
				// 다음 문자열와 비교할 폭발문자열 포인터 인덱스는 dp에 저장된 값에 따름
				if (idx > 0) bombIdx = dp[idx-1] + 1;
				
				// 남아있는 문자열이 하나도 없으면 포인터 0부터 시작
				else bombIdx = 0;
				
			}
		}
		
		// Deque에 남아있는 문자열 출력, Deque가 비었다면 "FRULA" 출력
		StringBuilder sb = new StringBuilder();
		if (dq.isEmpty()) sb.append("FRULA");
		else while (!dq.isEmpty()) sb.append(dq.pollFirst());
		
		System.out.println(sb.toString());
		br.close();
	}
}
