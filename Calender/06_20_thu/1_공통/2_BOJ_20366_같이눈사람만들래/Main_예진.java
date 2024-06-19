/*
 * [1차시도] 조합+조합
 * 1. 눈덩이 4개 조합으로 고르기 : O(N^4)
 * 2. 2개씩 짝짓는 3가지 경우를 계산 (*3)
 * -> 4개의 조합을 찾아놓고 그 안에서 또 2개씩 짝짓는 조합, 반복이 많고 중복계산이 있음.
 * 
 * [2차시도] 조합+정렬+투포인터
 * 1. 눈덩이 2개 조합으로 모든 눈사람 경우의 수 구하기 : O(N^2)
 * 2. 눈사람 키 순서대로 오름차순 정렬
 * 3. 눈사람 1개를 남기고 모든 눈사람을 순회하며 바로 다음 눈사람과의 키 비교 : O(N)
 *   단, 다음 눈사람은 눈덩이 idx가 겹치지 않는 조건 안에서 바로 다음 눈사람을 말한다.
 *   
 * 결론적으로 봤을 때 1차시도때는 시간복잡도가 O(N^4)이고, 2차시도는 O(N^3)이다.
 * N의 최대값이 600이므로 N^3은 약 2억1600 정도인가? 0.5초 걸렸다.
 * 이거 맞아,,? 설명 필요!! 도움!!
 */


package boj_20366_같이눈사람만들래;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	
	static boolean[] elsa;
	static int answer;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());	// 전체 눈덩이 수
		int[] balls = new int[N];					// 눈덩이 크기 정보 배열
		
		// 문제 입력
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			balls[i] = Integer.parseInt(input[i]);
		}
		
		List<Snowman> snowman = new ArrayList<>();	// 만들 수 있는 눈사람 종류 리스트
		
		// 눈덩이 배열에서 2개를 골라 만들 수 있는 눈사람 종류를 리스트에 모두 저장
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				snowman.add(new Snowman(i, j, balls[i]+balls[j]));
			}
		}
		
		// 눈사람 키를 기준으로 오름차순 정렬
		Collections.sort(snowman);
		
		// 답 초기화, 최댓값
		answer = Integer.MAX_VALUE;
		
		// 마지막 하나를 남기고 모든 눈사람을 순회
		for (int i = 0; i < snowman.size()-1; i++) {
			
			// 이번 순서의 눈사람
			Snowman curr = snowman.get(i);
			
			// 다음으로 키가 큰 눈사람의 인덱스
			int next = i+1;
			
			// 두 눈사람을 이루는 4개의 눈덩이가 겹치지 않을 때까지 인덱스를 이동하여 다음으로 키가 큰 눈사람 찾기, 유효한 인덱스 범위 안에서
			while (next < snowman.size() &&
					(curr.idx1 == snowman.get(next).idx1 ||
					curr.idx1 == snowman.get(next).idx2 ||
					curr.idx2 == snowman.get(next).idx1 ||
					curr.idx2 == snowman.get(next).idx2)) {
				next++;
			}
			
			// 인덱스가 유효한 범위 안에 있으면 두 눈사람의 키 차이를 구해 최솟값을 갱신
			if (next < snowman.size()) {
				answer = Math.min(answer, snowman.get(next).tall - curr.tall);
			}
		}
		
		// 출력
		System.out.println(answer);
		
		// 문단속
		br.close();
	}
}

class Snowman implements Comparable<Snowman>{
	int idx1;	// 1번 눈덩이 배열 인덱스
	int idx2;	// 2번 눈덩이 배열 인덱스
	int tall;	// 눈사람의 키: 두 눈덩이 지름의 합
	
	Snowman(int idx1, int idx2, int tall) {
		this.idx1 = idx1;
		this.idx2= idx2;
		this.tall = tall;
	}

	@Override
	public String toString() {
		return "Snowman [idx1=" + idx1 + ", idx2=" + idx2 + ", tall=" + tall + "]";
	}

	// 눈사람 키를 기준으로 정렬 가능
	@Override
	public int compareTo(Snowman o) {
		return Integer.compare(this.tall, o.tall);
	}
}