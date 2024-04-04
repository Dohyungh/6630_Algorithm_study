/*
 * Scanner 입력 느려 이슈..
 * 팰린드롬 다른 방법으로 풀어봤지만 그냥 이게 정석인듯 하다
 * 사용했거나 배워본 점화식 중에 뭔가 가장 신기했다.
 * 실제 구조랑 논리적으로 나타낸 dp테이블의 구조가 달랐는데, 이게 된다고? 싶었다.
 * 
 * 처음에 풀어본 방식은 dp테이블을 1차원으로 만들고 각 칸을 문장의 중앙인덱스라고 가정한 후 확인한 최대 길이를 적어두는 방식이었다.
 * 같은 구간을 두번 검사하지 않기 위한 아이디어였는데, 점화식은 생각도 못했다.
 * 사실 첫번째 방식 테게 예외 찾다가 블로그에서 계속 점화식이랑 풀이 얘기하는데 흐린눈으로 다 지나침.. 내꺼 맞을 줄 ㅠ
 * 
 * 예외테케 있는거 알고 고쳐봤지만, 느낌이 왔다 이건 틀린 풀이다
 * 두번째 풀이는 문장의 시작점(행)과 끝점(열)을 의미하는 dp테이블을 만들어 점화식으로 모든 회문을 미리 판단해뒀다.
 * 회문 여부는 시작점+1 부터 끝점-1 까지 회문이고, 시작점과 끝점의 수가 같은지 확인을 통해 판단한다. (이러한 규칙 -> 점화식 가능)
 * 
 * dp는 하나의 풀이방식이라기보단 진짜 다양한 방식으로 존재하는 것 같다.
 * 자존심상 직접 다 풀어보고 싶지만 이렇게 모를때는 그냥 30분 생각해도 안되면 빨리 풀이 보고 다양한 방법을 알아야겠다
 * 하지만 다시 풀어보기 중요!! 
 */
package boj_10942_팰린드롬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N+2];
		boolean[][] pal = new boolean[N+2][N+2];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 숫자 입력
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp테이블 초기화, 길이가 1인 문장은 모두 회문이다.
		// 길이가 2인 문장은 두 수가 같으면 회문이다.
		for (int i = 1; i <= N; i++) {
			pal[i][i] = true;
			if (nums[i] == nums[i+1]) {
				pal[i][i+1] = true;
			}
		}
		
		// 문장의 시작점과 끝점의 안쪽이 회문이고 두 점의 값이 일치하면 회문이다.
		// 그려보기로는 문장의 길이를 늘려가며 순서대로 확인하지만, 
		// dp테이블상으로 보면 순서에 맞지 않게 확인해서 잘못 체크되는 부분이 있음
		// 행을 아래서부터 거꾸로 올라와야 제대로 잘 확인됨.
		for (int i = N-2; i >= 1; i--) {
			for (int j = i+2; j <= N; j++) {
				pal[i][j] = pal[i+1][j-1] && (nums[i] == nums[j]);
			}
		}
		
		// 스캐너 입력 느려이슈로 굉장히 복잡해진 입력과정..
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			String[] range = br.readLine().split(" ");
			int start = Integer.parseInt(range[0]);
			int end = Integer.parseInt(range[1]);
			
			if (pal[start][end]) sb.append("1\n");
			else sb.append("0\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
