package _7087;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
	public static void main(String args[]) throws Exception {
		
		System.setIn(new FileInputStream("SWEA/src/_7087/sample_input.txt"));
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		int T = Integer.parseInt(bf.readLine());
		
		
		for (int test_case = 1; test_case <= T; test_case++) {
			
			// 입력받을 제목의 개수 N 입력 받기
			int N = Integer.parseInt(bf.readLine());
			
			
			// 제목의 앞글자만 저장할 리스트인 char형의 title_list를 생성. 
			List<Character> title_list = new ArrayList<>();
			
			
			for (int i=0; i<N; i++) {
				// 문자열로 제목(title)을 입력받고, 문자열의 첫번째 글자를 char형으로 title_list에 순서대로 add
				String title = bf.readLine();
				title_list.add(title.charAt(0));
			}
			
			// 최대 문제 제목의 개수(출력해야할 정답)
			int answer = 0;
			
			// List형의 title_list를 Collections.sort로 오름차순(ASCII 코드순) 정렬
			Collections.sort(title_list);
		
			// 'A'의 ASCII 코드값은 65
			int ascii = 'A';
			// 정렬된 리스트에서 순서대로 A, B, C, ...의 아스키 코드값을 비교하고자 ascii_compare 변수를 생성
			int ascii_compare = title_list.get(0) - ascii;
			// 리스트의 인덱스
			int idx = 0;
			
			
			// title_list의 첫번째 원소가 A일 때 answer에 1을 더해줌.
			if (title_list.get(0) == 'A') {
				answer += 1;
			}
			
			// title_list의 첫번째 원소가 A가 아니거나, 아스키 코드값이 2 이상 차이나거나, idx가 벗어나면 while문 실행 X
			while (title_list.get(0) == 'A' && ascii_compare <= 1 && idx < title_list.size()) {
				
				// 현재 idx에 해당하는 제목의 첫글자의 아스키코드값을 비교.
				ascii_compare = title_list.get(idx) - ascii;
				
				// 1 차이가 난다면, (ex: 'B'-'A'=1, 'C'-'B'=1) 
				if (ascii_compare == 1) {
					// answer에 1을 더하고, 비교할 아스키코드 1 증가
					answer += 1;
					ascii++;
				}
				// 다음 인덱스 검사
				idx++;
			}
			System.out.printf("#%d %d\n", test_case, answer);
		}
		bf.close();
		
		
	}
}