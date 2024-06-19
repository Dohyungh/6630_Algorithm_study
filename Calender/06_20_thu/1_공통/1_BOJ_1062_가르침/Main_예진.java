/*
 * [1차 시도] : Set 사용 (약 0.8초)
 * [2차 시도] : boolean 배열과 각 알파벳의 유니코드 사용 (약 0.5초)
 * 
 * 1. 어두와 어미를 제외한 단어 목록을 저장한다.
 * 2. 어두/어미에 사용된 문자수가 5개임으로 가르칠 수 있는 문자의 수(K)가 5 미만이면 answer는 0이다.
 * 3. 단어들을 이루는 문자들을 Set을 사용해서 중복없이 배열로 만든다.
 * 4. 배열에서 더 가르칠 수 있는 문자수만큼 문자를 선택한 경우마다(조합)의 읽을 수 있는 단어수를 계산한다.
 */
package boj_1062_가르침;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	
	static String[] words;			// 어두와 어미를 제외한 단어목록
	static boolean[] canTeach;		// 각 알파벳을 읽을 수 있는지 여부
	static Character[] middles;		// 단어목록을 이루는 문자들의 목록(중복X, 어두/어미 문자 제외)
	static int leng;				// 추가로 가르쳐야 하는 문자의 개수
	static int selectCnt;			// 추가로 가르칠 수 있는 문자의 개수
	static int answer;				// 읽을 수 있는 최대 단어 수
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);			// 단어 개수
		int K = Integer.parseInt(input[1]);			// 가르칠 수 있는 문자의 개수
		
		// 어두와 어미를 제외한 문자 목록
		words = new String[N];
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			words[i] = tmp.substring(4, tmp.length()-4);
		}
		
		// 답 초기화, 현재 읽을 수 있는 단어 수 0개
		answer = 0;
		
		// 가르칠 수 있는 단어가 5개 이상이면 로직 수행, 아니라면 읽을 수 있는 단어가 없음
		if (K >= 5) {
			
			char[] fix = {'a', 'n', 't', 'i', 'c'};		// 어두, 어미를 이루는 공통 문자 - 필수 문자
			Set<Character> cSet = new HashSet<>();		// 단어를 이루는 문자 목록, 중복제거용
			canTeach = new boolean[26];					// 모든 알파벳의 가독 여부
			
			// 단어들을 이루는 모든 문자를 Set에 추가
			for (String s : words) {
				for (char c : s.toCharArray()) {
					cSet.add(c);
				}
			}
			
			// 필수 문자들의 가독여부 true, (유니코드-97) 을 통해 인덱스 조정
			// Set에 해당 문자가 있다면 삭제(필수 문자니까 추가로 가르칠 필요가 없음)
			for (char c : fix) {
				canTeach[idx(c)] = true;
				cSet.remove(c);
			}
			
			middles = cSet.toArray(new Character[0]);	// 추가로 가르쳐야 하는 문자 배열
			leng = middles.length;						// 추가로 가르쳐야 하는 문자 개수
			selectCnt = K - 5;							// 추가로 가르칠 수 있는 문자 개수
			
			// 가르칠 수 있는 문자의 수가 가르쳐야 하는 문자수보다 크다면 모든 단어를 읽을 수 있음.
			if (selectCnt > leng) {
				answer = words.length;
				
			// 그렇지 않다면, 조합을 구해 각 경우별 읽을 수 있는 최대 단어 수 계산
			} else comb(0,0);
			
		}
		
		System.out.println(answer);
		
		br.close();
	}

	// 가르쳐야 하는 문자 배열 중 가르칠 수 있는 문자만큼 고르는 재귀 함수
	private static void comb(int idx, int cnt) {
		if (cnt >= selectCnt) {
			answer = Math.max(answer, check());
			return;
		}
		
		if (idx >= leng) return;
		
		canTeach[idx(middles[idx])] = true;
		comb(idx+1, cnt+1);
		canTeach[idx(middles[idx])] = false;
		comb(idx+1, cnt);
	}

	// 각 조합별 읽을 수 있는 단어의 수를 구하는 함수
	private static int check() {
		int result = 0;
		
		// 단어 목록을 순회
		here:for (String word : words) {
			// 해당 단어의 문자들을 순회하며 읽을 수 없는 문자가 하나라도 발견되면 다음 단어로 넘어가기
			for (char c : word.toCharArray()) {
				if (!canTeach[idx(c)]) continue here;
			}
			// 정상적으로 확인이 끝났다면 단어 수 +1
			result++;
		}
		
		return result;
	}
	
	// 알파벳 배열에서 해당 문자의 인덱스를 반환하는 함수
	private static int idx(char c) {
		return c - 97;
	}
}