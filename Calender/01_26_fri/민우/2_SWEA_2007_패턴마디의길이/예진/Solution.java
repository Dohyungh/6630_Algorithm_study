package 패턴마디의길이_2007_240125;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("src/input.txt"));

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		int ans = 0;
		
		for (int tc = 1; tc <= T; tc++) {
			char[] inputs = bf.readLine().toCharArray();
			
			out: // 사실 굳이 필요없지만, 답을 찾았을 때 순회를 종료하는 것을 명시하려고
			for (int i = 1; i < 10; i++) { // 마디 최대 길이
				if (inputs[0] == inputs[i]) {  // 첫번째 문자와 같은 문자를 찾으면 ans를 1로 초기화 하고,
					ans = 1;
					for (int j = 1; j < i; j++) { // 찾은 위치 전까지 모든 모든 문자가 동일한지 확인
						if ((i+j) >= 30 || inputs[j] == inputs[i+j]) ans++; // 이 문제에서는 굳이 필요없지만, 조건이 달라지면 i+j가 유효인덱스를 넘을 수도 있어서
						else break; // 다른 문자가 발견되면 중단하고, 같은 문자 찾기를 다시 시작함
					}
					if (ans == i) break out;
				}
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
		bf.close();
	}

}
