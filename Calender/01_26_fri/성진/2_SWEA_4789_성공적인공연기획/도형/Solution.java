package SWEA._4789_성공적인공연기획;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc<=T; tc++) {
			//입력
			String str = sc.next();
			
			int[] li = new int[str.length()];
			for (int i =0; i<str.length();i++) {
				li[i] = Integer.parseInt(""+ str.toCharArray()[i]); // 한글자씩 끊고, 각 인덱스를 ""에 더해 자동 형변환, parseInt로 Int로 변환
			}
			
			int app =li[0]; //무조건 박수치는 사람
			int ans = 0; //고용해야 하는 사람
			for (int i = 1; i<li.length;i++) {
				if (app>=i) app+=li[i]; //충분하면 그냥 더해만 짐
				else { //부족하면
					ans += i-app; // 부족한 만큼 사람을 고용해서
					app = i; // 필요한 만큼을 충족시키고,
					app += li[i]; // 이제 박수 칠 수 있게 된 사람들을 더해 줌.
				}
			}
			System.out.printf("#%d %d%n",tc,ans);
		}
	}
}
