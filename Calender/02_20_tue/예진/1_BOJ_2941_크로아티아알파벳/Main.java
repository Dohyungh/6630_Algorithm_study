package BOJ_2941_크로아티아알파벳;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		// 비교 목록
		String[] croatian = {"c=" , "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
		
		Scanner sc = new Scanner(System.in);
		char[] input = sc.nextLine().toCharArray();						// 입력을 문자 배열로 저장
		
		int start = 0;													// 비교 시작지점
		int answer = 0;													// 답(크로아티아 알파벳의 개수)
		
		while(start < input.length) {									// 시작지점이 문자배열 마지막 문자를 넘어가면 종료
			
			boolean same = false;										// 비교 목록에 일치하는 것이 없으면 flase 반환할 것
			
			for (String alpa : croatian) {								// 비교 목록 순회
				
				if (start + alpa.length()-1 < input.length) {			// 시작지점에서 비교할 문자길이만큼 배열길이가 남아있으면 비교 시작
					
					int idx = start;									// 각 문자별 비교인덱스 별도 초기화
					same = true;										// 비교 중 다른 문자를 찾지 못하면 일치로 판명
					
					for (int i = 0; i < alpa.length(); i++) {
						if (input[idx+i] != alpa.charAt(i)) {
							same = false;
							break;
						}
					}
					
					if (same) {											// 일치하면 카운트 +1, 일치하는 문자 길이만큼 시작지점 갱신
						answer++;
						start += alpa.length();
						break;
					}
				}
			}
			
			if (!same) {												// 일치하는 알파벳을 찾지 못하면 카운트 +1, 시작지점 +1
				answer++;
				start++;
			}
			
		}
		System.out.println(answer);										// 출력
		
		sc.close();														// 문단속 
	}
}