/*
 * kmp알고리즘의 실패함수 구하는 공식을 사용했다.
 * j는 기존 문자열의 비교인덱스를 나타내고, i는 비교대상 문자열의 비교 인덱스를 나타낸다.
 * 따라서 j는 접두사 부분을, i는 접미사 부분을 가리킨다.
 * 한 글자 짜리는 해당하지 않는다. -> j가 0일때, i는 1부터 시작
 * i를 늘려가며 비교할 접미사를 변경한다.
 * j는 i의 문자와 같으면 연속으로 일치한 문자 개수를 저장하고, 
 *  i와 같지 않으면 이전 문자에서 연속으로 일치한 접두사의 다음인덱스를 가리켜 i와 다시 비교한다.
 *  j의 위치가 더이상 접두사 앞으로 갈 수 없거나 i의 위치와 같을때까지 반복한다.
 *  저장되는 숫자 중 최대값을 갱신해간다.
 *  
 *  패턴이 되는 문자열은 어느 인덱스부터라도 시작할 수 있기 때문에 j의 최소 인덱스를 늘려가며 순회한다.
 *  즉, 문자열을 전부 순회하며 패턴 시작지점을 변경한다.
 *  연속된 문자의 개수를 저장할때 j의 인덱스로 저장할 경우 시작지점에 따라 오류가 생길 수 있기 때문
 *  저장하거나 접근할 인덱스에서 시작지점의 인덱스를 적절히 더하거나 빼야 한다.
 */
package boj_1701_Cubeditor;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		char[] input = sc.nextLine().toCharArray();
		int answer = 0;
		
		// 패턴의 시작 지점 제한
		for (int k = 0 ; k < input.length - 1; k++) {
			
			// 실패함수 배열 초기화
			int[] dp = new int[input.length];
			// 접두사 비교 인덱스
			int j = k;
			
			// 접미사 비교 인덱스(패턴의 길이를 설정)
			for (int i = k+1; i < input.length; i++) {
				
				// 더이상 앞에 비교할 문자가 없거나 같은 문자를 찾을때까지 이전 문자의 실패함수인덱스 다음 문자에 접근
				while (j > k && input[i] != input[j]) j = dp[j-1] + k;
				
				// 최종적으로 두 문자가 같으면 현재 j+1 위치에서 시작지점 인덱스 k를 뺀값이 실패함수
				if (input[i] == input[j]) dp[i] = ++j - k;
				
				// 최댓값 갱신(두번이상 등장하는 문자열의 최대길이)
				answer = Math.max(answer, dp[i]);
			}
		}
		
		System.out.println(answer);
		sc.close();
	}
}
