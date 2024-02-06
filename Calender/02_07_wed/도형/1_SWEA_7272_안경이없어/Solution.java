package SWEA._7272_안경이없어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution {
	// 구상 없음
	
	
	// 구멍개수 0개, 1개, 2개 짜리 그룹으로 나누고 한글자 받으면 어떤 그룹에 들어가는지 리턴
	public static int which_group(String a) {
		
		String hole_0 = "CEFGHIJKLMNSTUVWXYZ";
		
		String hole_1 = "ADOPQR";
		
		if (hole_0.contains(a)) {
			return 0;
		} else if (hole_1.contains(a)) {
			return 1;
		} else return 2;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			// 입력
			// 다 한글자씩 쪼개서 배열로 만들자
			String[] temp = br.readLine().split(" ");
			String[] one = temp[0].split("");
			String[] two = temp[1].split("");
			
			boolean same = true;
			
			// 길이가 다르면 무조건 false
			if (one.length != two.length) {
				same = false;
				
			}else { // 어떤 그룹에 들어가는지 한 글자씩 확인. 다르면 false
				for (int i = 0; i<one.length; i++) {
					if(which_group(one[i]) != which_group(two[i])) {
						same = false;
						break;
					}
				}
			}
			if (same) {
				System.out.printf("#%d %s\n",tc,"SAME");
			} else {
				System.out.printf("#%d %s\n",tc,"DIFF");
			}

		}
		br.close();
	}

}
